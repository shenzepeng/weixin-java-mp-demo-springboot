package com.github.binarywang.demo.wx.mp.handler;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import com.github.binarywang.demo.wx.mp.constant.Constant;
import com.github.binarywang.demo.wx.mp.dao.*;
import com.github.binarywang.demo.wx.mp.pojo.*;
import com.github.binarywang.demo.wx.mp.service.UserService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import com.github.binarywang.demo.wx.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.Constants;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {
    @Autowired
    private MsgHistoryDao msgHistoryDao;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherStudentShipDao teacherStudentShipDao;
    @Autowired
    private CheckInDao checkInDao;
    @Autowired
    private CheckInListDao checkInListDao;
    @Autowired
    private UserDao userDao;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //将消息保存到本地
            try {
                saveMsgInLocal(wxMessage);
                addUserIfo(wxMessage.getFromUser(),weixinService,wxMessage);
            } catch (Exception e) {
                logger.info("消息保存本地失败-{}", e.getMessage());
            }
        }
        String content = "详情了解,请添加微信:id_jx520";
        if (StringUtils.isEmpty(wxMessage.getContent())) {
            return null;
        }
        //1.内容为签到
        String wxContent = wxMessage.getContent().trim();
        if (wxContent.equals("签到")) {
            //2.查找相关联老师
            String openId = wxMessage.getFromUser();
            User user = userDao.findUserByOpenId(openId).get(0);
            List<StudentTeacherShip> shipByStudentId = teacherStudentShipDao.findShipByStudentId(user.getId());
            if (CollectionUtils.isEmpty(shipByStudentId)) {
                content="您目前没有关老师，详情了解,请添加微信:id_jx520";
                return new TextBuilder().build(content, wxMessage, weixinService);
            }
            Long teacherId = shipByStudentId.get(0).getTeacherId();
            //3.拿到最近该老师最新的签到
            List<CheckIn> checkInId = checkInDao.findCheckInId(teacherId);
            CheckIn checkIn = checkInId.get(checkInId.size() - 1);
            //4.签到
            //还没过期
            if (DateUtils.getLocalDate().before(checkIn.getExpiredTime())) {
                CheckList checkList = new CheckList();
                checkList.setCheckInId(checkIn.getId());
                checkList.setStudentId(user.getId());
                checkList.setCreateTime(DateUtils.getLocalDate());
                checkList.setUpdateTime(DateUtils.getLocalDate());
                //查看该用户是否已经签过到了
                List<CheckList> checkLists = checkInListDao.hasCheckIn(user.getId(), checkIn.getId());
                if (CollectionUtils.isEmpty(checkLists)){
                    checkInListDao.addCheckInList(checkList);
                    logger.info("用户-{},完成签到，签到老师-{}", user, teacherId);
                    content="签到成功";
                    return new TextBuilder().build(content, wxMessage, weixinService);
                }else {
                    content="您以签到成功，不需要再次签到";
                    return new TextBuilder().build(content, wxMessage, weixinService);
                }
            }else {
                content="当前没有签到";
                return new TextBuilder().build(content, wxMessage, weixinService);
            }
        }
        return new TextBuilder().build(content, wxMessage, weixinService);


    }

    private void saveMsgInLocal(WxMpXmlMessage wxMessage) {

        MsgHistory msgHistory = new MsgHistory();
        msgHistory.setCreateTime(DateUtils.getLocalDate());
        msgHistory.setFromUser(wxMessage.getFromUser());
        if (Constant.image.equals(wxMessage.getMsgType())) {
            msgHistory.setMsgContent(wxMessage.getPicUrl());
        }
        if (Constant.text.equals(wxMessage.getMsgType())) {
            msgHistory.setMsgContent(wxMessage.getContent());
        }
        if (Constant.video.equals(wxMessage.getMsgType())) {
            msgHistory.setMsgContent(wxMessage.getMediaId());
        }
        msgHistory.setToUser(wxMessage.getToUser());
        msgHistory.setMsgId(wxMessage.getMsgId());
        msgHistory.setUpdateTime(DateUtils.getLocalDate());
        msgHistory.setMsgType(wxMessage.getMsgType());
        Integer addResult = msgHistoryDao.insertMsgHistory(msgHistory);
        logger.info("添加消息记录,消息内容为-{},添加结果为-{}", msgHistory, addResult);
    }

    private WxMpXmlOutMessage trasferInfoToServer(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addUserIfo(String openId,WxMpService wxMpService,WxMpXmlMessage wxMessage){
        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = wxMpService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
                logger.info("有人关注了公众微信，openID为-{}",userWxInfo.getOpenId());
                User user=new User();
                user.setOpenId(userWxInfo.getOpenId());
                user.setCreateTime(DateUtils.getLocalDate());
                user.setImgUrl(userWxInfo.getHeadImgUrl());
                user.setSex(userWxInfo.getSex().shortValue());
                //0 关注
                user.setStatus((short)0);
                user.setNickName(userWxInfo.getNickname());
                user.setUpdateTime(user.getUpdateTime());
                Integer addResult = userService.insertUser(user);
                if (addResult.equals(1)){
                    logger.info("公众微信有新关注的用户,用户是-{}",user);
                }
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }
    }

}
