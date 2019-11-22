package com.github.binarywang.demo.wx.mp.utils;

import com.github.binarywang.demo.wx.mp.constant.WxRequestConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @Auther: szp
 * @Date: 2019/11/17 22:36
 * @Description: 沈泽鹏写点注释吧
 */
@Slf4j
public class AccessTokenUtils {


    private static String getAccessTokenFromWxServer(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("grant_type","client_credential");
        hashMap.put("appid","wx44beca8ea3d45813");
        hashMap.put("secret","9554847d4fbae41528e7c96fbef07ccb");
        String token = HttpUtils.doGet(WxRequestConstants.GET_ACCESSON_TOKEN_REQUEST_URL, hashMap);
        log.info("请求的参数是-{},拿到的token是-{}",hashMap,token);
        return token;
    }
}
