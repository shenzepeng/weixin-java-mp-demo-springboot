package com.github.binarywang.demo.wx.mp.constant;

/**
 * @Auther: szp
 * @Date: 2019/11/17 21:58
 * @Description: 沈泽鹏写点注释吧
 */
public class WxRequestConstants {
    //################################基础支持##########################################################
    /**
     * 获取accesson_token的接口
     */
    public static final String GET_ACCESSON_TOKEN_REQUEST_URL="https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 上传文件的url,多媒体，支持图片，视频，音频
     */
    public static final String UPLOAD_FILE_URL="https://api.weixin.qq.com/cgi-bin/message/custom/send";
    /**
     * 下载文件请求的url
     */
    public static final String DOWNLOAD_FILE_URL="http://file.api.weixin.qq.com/cgi-bin/media/get";

    //##################################向用户发送消息#################################################
    /**
     * 向用户发送消息
     */
    public static final String SEND_MESSAGE_TO_CUSTOM="https://api.weixin.qq.com/cgi-bin/message/custom/send";

    //##################################用户管理#################################################
    /**
     * 获取全部open_id的接口
     */
    public static final String GET_ALL_OPEN_ID_URL="https://api.weixin.qq.com/cgi-bin/user/get";

    /**
     * 获取用户的基本信息
     */
    public static final String GET_USER_INF_BY_OPEN_ID="https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 获取分组接口
     */
    public static final String GET_GROUPS="https://api.weixin.qq.com/cgi-bin/groups/get";
    /**
     * 创建分组接口
     */
    public static final String CREATE_GROUPS="https://api.weixin.qq.com/cgi-bin/groups/create";
    /**
     * 修改分组名接口
     */
    public static final String UPDATE_GROUPS="https://api.weixin.qq.com/cgi-bin/groups/update";
    /**
     * 移动分组名接口
     */
    public static final String UPDATE_GROUPS_MEMBER="https://api.weixin.qq.com/cgi-bin/groups/members/update";
    /**
     * 查询分组id接口
     */
    public static final String GET_GROUPS_BY_ID="https://api.weixin.qq.com/cgi-bin/groups/getid";

    //##################################自定义菜单管理#################################################
    /**
     *自定义菜单创建接口
     */
    public static final String CREATE_MENU="https://api.weixin.qq.com/cgi-bin/menu/create";
    /**
     *查询自定义菜单
     */
    public static final String GET_MENU_IFO="https://api.weixin.qq.com/cgi-bin/menu/get";
    /**
     * 删除自定义菜单接口
     */
    public static final String DELETE_MENU="https://api.weixin.qq.com/cgi-bin/menu/delete";

    //##################################二维码管理#################################################
    /**
     * 创建二维码接口
     */
    public static final String CREATE_QRCODE="https://api.weixin.qq.com/cgi-bin/qrcode/create";
    /**
     * 换取二维码接口
     */
    public static final String SHOW_QRCODE="https://mp.weixin.qq.com/cgi-bin/showqrcode";

    //##################################消息接口管理#################################################
    /**
     * 发送文本消息
     */
    public static final String SEND_TXT_MSG="https://api.weixin.qq.com/cgi-bin/token";
    //##################################模板管理#################################################
    /**
     * 模板管理
     */
    public static final String SEND_TEMPLATE_MSG="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

}
