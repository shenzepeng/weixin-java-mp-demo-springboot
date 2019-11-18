package com.github.binarywang.demo.wx.mp.cache;

import lombok.Data;

/**
 * @Auther: szp
 * @Date: 2019/11/17 22:10
 * @Description: 沈泽鹏写点注释吧
 */
@Data
public class AccessTokenCache {
    public static String accessToken="";
    public static void setAccessToken(String accessToken){
        AccessTokenCache.accessToken=accessToken;
    }
}
