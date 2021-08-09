package com.sskj.pay.http;


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

    public static final String UPLOAD_FILE = "/app/upload/file";
    public static final String ADD_PAY_TYPE = "/app/user/addPayWay";
    public static final String EDIT_PAY_TYPE = "/app/user/updatePayType";
    public static final String PAY_TYPE = "/app/lawCoin/payList";
    public static final String TOGGLE_PAY_TYPE = "/app/lawCoin/updatePayWay";
}
