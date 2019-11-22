package com.github.binarywang.demo.wx.mp.handler;

import com.github.binarywang.demo.wx.mp.pojo.User;
import com.github.binarywang.demo.wx.mp.service.UserService;
import com.github.binarywang.demo.wx.mp.utils.DateUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {
    @Autowired
    private UserService userService;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        this.logger.info("取消关注用户 OPENID: " + openId);
        // TODO 可以更新本地数据库为取消关注状态
        User userByOpenId = userService.findUserByOpenId(openId);
        //之前关注过公众微信，但是没有拿到相关信息，这个时候需要将信息保存到本地数据库中
        if (userByOpenId==null){
            // 获取微信用户基本信息
            try {
                WxMpUser userWxInfo = wxMpService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
                if (userWxInfo != null) {
                    // TODO 可以添加关注用户到本地数据库
                    logger.info("取消关注的用户，openID为-{}",userWxInfo.getOpenId());
                    User user=new User();
                    user.setOpenId(userWxInfo.getOpenId());
                    user.setCreateTime(DateUtils.getLocalDate());
                    user.setImgUrl(userWxInfo.getHeadImgUrl());
                    user.setSex(userWxInfo.getSex().shortValue());
                    //1 取消关注
                    user.setStatus((short)1);
                    user.setNickName(userWxInfo.getNickname());
                    user.setUpdateTime(user.getUpdateTime());
                    Integer addResult = userService.insertUser(user);
                    if (addResult.equals(1)){
                        logger.info("取消关注的用户,用户是-{}",user);
                    }
                }
            } catch (WxErrorException e) {
                logger.info("添加user信息异常，异常详情为-{}",e);
                if (e.getError().getErrorCode() == 48001) {
                    this.logger.info("该公众号没有获取用户信息权限！");
                }
            }
        }
        //取消关注
        userByOpenId.setStatus((short)1);
        userService.updateUser(userByOpenId);
        return null;



    }

}
