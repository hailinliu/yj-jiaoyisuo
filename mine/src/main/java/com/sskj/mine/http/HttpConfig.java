package com.sskj.mine.http;


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

    /**
     * 联系我们
     */
    public static final String CONTRACT = "/app/user/touchMe";

    /**
     * 常见问题
     */
    public static final String QUESTION_LIST = "/app/user/question";

    /**
     * 获取谷歌验证二维码
     */
    public static final String GOOGLE_QR = "/app/googleAuth/genSecret";


    /**
     * 绑定手机号或邮箱
     */
    public static final String BIND_MOBILE_OR_EMAIL = "/app/user/bindTelOrEmail";

    /**
     * 更新手机号或邮箱
     */
    public static final String UPDATE_MOBILE_OR_EMAIL = "/app/user/updataTelOrEmail";

    /**
     * 重置登录密码
     */
    public static final String SET_LOGIN_PWD = "/app/user/updatePswd";


    /**
     * 微信和qq的二维码获取
     */
    public static final String GET_TWO_CODE = "/app/user/touchMeTwo";

    /**
     * 申请商家
     */
    public static final String SHOPVERIFY = "/app/lawCoin/applyShop";

    /**
     * 解除商家认证
     */
    public static final String SHOPCANCEL = "/app/user/relieveShop";

    /**
     * 获取商家保证金
     */
    public static final String GETMONEY = "/app/lawCoin/applyShopPro";

    /**
     * 设置资金密码
     */
    public static final String SETFUNDPWD = "/app/user/updateTradePswd";

    /**
     * 反馈
     */
    public static final String FEEDBACK = "/app/user/contentMessage";

    /**
     * 开启谷歌认证
     */
    public static final String OPENCHROME = "/app/googleAuth/openGoolgeAuth";

    /**
     * 关闭谷歌认证
     */
    public static final String CLOSECHROME = "/app/googleAuth/closeGoolgeAuth";

    /**
     * 绑定谷歌认证
     */
    public static final String BINDCHROME = "/app/googleAuth/verify";

    /**
     * 解除谷歌认证
     */
    public static final String UNBINDCHROME = "/app/googleAuth/relieveGoolgeAuth";

    /**
     * 版本升级
     */
    public static final String VERSION = "/app/user/appVersion";
    //  获取资产
    public static final String GETASSERTS = "/app/user/accountFund";

    public static final String TRANSFERSUBMIT = "/app/user/transfer";

    public static final String GETASSERTSDETAILS ="/app/user/moneyDetail";

    public static final String GETASSERTSEXCHANGE = "/app/user/propertyChange";

    public static final String GETASSERTSEXCHANGERECORD="/app/user/changeRecord";
    //推广
    public static final String GETPROMOTION="/app/money/detail/queryUserMarketInfo";
    //获取推广
    public static final String  GETERWEIMA="/app/user/getQrcode";

    public static final String GETCUSTOMER="/app/user/myCustom";
    /**
     * 获取汇率
     */
    public static final String GET_RATE="/market/exchange-rate/";
}