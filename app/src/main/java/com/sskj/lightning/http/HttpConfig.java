package com.sskj.lightning.http;


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
    public static final String BASE_IMG_URL = BaseHttpConfig.BASE_IMG_URL;
    public static final String WS_BASE_URL = BaseHttpConfig.WS_BASE_URL;
    public static final String WS_BASE_URL1 = "wss://www.yolocoin.uk/websocket/123123";
    /**
     * 弹屏公告
     */
    public static final String GET_GONGGAO = "/app/user/screenNotice";
    /**
     * 版本更新
     */
    public static final String VERSION = "/uc/ancillary/system/app/version/0";

/**
 * 获取汇率
 */
public static final String GET_RATE="/market/exchange-rate/";


}