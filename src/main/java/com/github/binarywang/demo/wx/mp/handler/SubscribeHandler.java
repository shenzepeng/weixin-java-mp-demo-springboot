package com.github.binarywang.demo.wx.mp.handler;

import java.util.Map;

import com.github.binarywang.demo.wx.mp.dao.UserDao;
import com.github.binarywang.demo.wx.mp.pojo.User;
import com.github.binarywang.demo.wx.mp.service.UserService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.binarywang.demo.wx.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
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
            logger.info("添加user信息异常，异常详情为-{}",e);
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
