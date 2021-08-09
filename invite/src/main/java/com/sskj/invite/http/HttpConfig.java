package com.sskj.invite.http;


import com.sskj.lib.BaseHttpConfig;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：网络接口地址
 */
public class HttpConfig {
    public static final int OK = BaseHttpConfig.OK;
    public static final String BASE_URL = BaseHttpConfig.BASE_URL;


    /**
     * 邀请菜单
     */
    public static final String INVITEMENU = "/app/money/detail/queryUserMarketInfo";
    /**
     * 我的邀请二维码
     */
    public static final String INVITE = "/app/user/myInvitation";

    public static final String INVITECUSTOM = "/app/user/myCustom";
    public static final String INVITE_REWARD = "/app/concess/detail/search";
}
