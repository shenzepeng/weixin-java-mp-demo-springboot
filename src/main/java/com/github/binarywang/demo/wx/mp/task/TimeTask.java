package com.github.binarywang.demo.wx.mp.task;

import com.github.binarywang.demo.wx.mp.constant.WxRequestConstants;
import com.github.binarywang.demo.wx.mp.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: szp
 * @Date: 2019/11/16 21:34
 * @Description: 沈泽鹏写点注释吧
 */
@Component
@Slf4j
public class TimeTask {
    @Autowired
    private WxMpService wxMpService;

    private List<String> getAllOpenIds(String accessToken ){
        HashMap<String,String> msg=new HashMap<>();
        //公众微信全局的access_token
        msg.put("access_token",accessToken);
        String s = HttpUtils.doGet(WxRequestConstants.GET_ALL_OPEN_ID_URL, msg);
        System.out.println(s);
        return new ArrayList<>();
    }

}
