package com.sskj.hangqing.http;


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
    public static final String BASE_URL1 = BaseHttpConfig.BASE_URL1;
    public static final String BASE_IMG_URL = BaseHttpConfig.BASE_IMG_URL;
    public static final String WS_BASE_URL = BaseHttpConfig.WS_BASE_URL;
    public static final String WS_BASE_URL1 = "wss://www.yolocoin.uk/websocket/123123";
    /**
     * 上传图片
     */
    public static final String UPLOADIMAGE="/uc/imgUpload";
    /**
     * 支持国家
     */
    public static final String SUPPERTCOUNTRY="/uc/support/country";
    /**
     * 关闭谷歌绑定
     */
    public static final String UNBINDGOOGLE="/uc/google/jcgoogle";
    /**
     * g谷歌绑定
     */
    public static final String ABINDGOOGLE="/uc/google/googleAuth";
    /**
     * 谷歌验证码
     */
    public static final String GOOGLECODE="/uc/google/sendgoogle";
    /**
     * 安全设置总信息
     */
    public static final String SECURITYSETTING="/uc/approve/security/setting";
    /**
     * 修改手机号
     */
    public static final String UPDATEBINDPHONE="/uc/approve/change/phone";
    /**
     * 修改邮箱
     */
    public static final String UPDATEBINDEMAIL="/uc/approve/update/email";
    /**
     * 绑定手机号
     */
    public static final String BINDPHONE="/uc/approve/bind/phone";
    /**
     * 绑定邮箱
     */
    public static final String BINDEMAIL="/uc/approve/bind/email";
    /**
     * 更改绑定手机号验证码
     */
    public static final String UPDATEBINDPHONECODE="/uc/mobile/change/code";
    /**
     * 更改绑定邮箱验证码
     */
    public static final String UPDATEBINDEMAILCODE="/uc/email/update/email/code";
    /**
     * 绑定手机号验证码
     */
    public static final String BINDPHONECODE="/uc/mobile/bind/code";
    /**
     * 绑定邮箱验证码
     */
    public static final String BINDEMAILCODE="/uc/email/bind/email/code";

    /**
     * 设置资金密码
     */
    public static final String UPDATETRADPWD = "/uc/approve/transaction/password";
    /**
     * 重置资金密码
     */
    public static final String UPDATETRADPWD1 = "/uc/approve/update/transaction/password";
    /**
     * 修改资金密码验证码邮箱
     */
    public static final String UPDATAPWDTRADEMAIL = "/uc/email/update/jypassword/code";
    /**
 * 修改资金密码验证码手机
 */
    public static final String UPDATAPWDTRAD = "/uc/mobile/update/jypassword/code";
    /**
     * 修改密码
     */
    public static final String UPDATAPWD = "/uc/approve/update/password";
    /**
     * 修改登录密码验证码手机号
     */
    public static final String UPDATECODE = "/uc/mobile/update/password/code";
    /**
     * 修改登录密码验证码邮箱号
     */
    public static final String UPDATECODEEMAIL = "/uc/email/update/password/code";
    /**
     * 行情数据
     */
    public static final String GET_PRODUCT = "/market/symbol-thumb";
    /**
     * bibi行情数据
     */
    public static final String GET_PRODUCT1 = "/app/kline/goodInfo";
    /**
     * 获取k线数据
     */
    public static final String GET_K_DATA = "/app/kline/history";
    /**
     * 获取k线数据
     */
    public static final String GET_K_DATA1 = "/market/history";

    /**
     * 获取轮播图及公告
     */
    public static final String GET_BANNER = "/uc/ancillary/system/advertise";

    /**
     * 币种简介
     */
    public static final String GET_SUMMARY = "/app/leverDeal/getStockInfo";
    public static final String GET_SUMMARY1 = "/market/coinInfo";
    /**
     * 深度数据
     */
    public static final String GET_DEEP = "/app/kline/depth";
    public static final String GET_DEEP1 = "/app/kline/depth";
    /**
     * 实时成交
     */
    public static final String GET_ALL_TRADE = "/app/kline/originsInfo";
    public static final String GET_ALL_TRADE1 = "/app/kline/originsInfo";
    /**
     * 咨询列表
     */
    public static final String GET_ZIXUN_LIST = "/app/user/comMessage";
    /**
     * 交易指南
     */
    public static final String GET_GUIDE_LIST = "/app/user/transactionRules";

    /**
     * 轮播公告
     */
    public static final String GET_NOTICE = "/uc/announcement/page";
    /**
     * 帮助中心
     */
    public static final String HELP = "/app/user/indexContent";
    /**
     * 关于我们
     */
    public static final String ABOUT_US = "/app/user/ours";

    /**
     * 注册协议
     */
    public static final String REGISTER_AGREEMENT = "/app/user/service";
    /**
     * 隐私协议
     */
    public static final String PRIVATE_SERVICE = "/app/user/privacy";

    /**
     * 自选币列表
     */
    public static final String GET_SELF_COIN = "/app/user/getSeltCode";

    /**
     * 编辑自选币
     */
    public static final String EDIT_SELF_COIN = "/app/user/editSeltCode";

    /**
     * 行情首页交易对
     */
    public static final String CODO_HORIZATION_LIST = "/app/kline/getSeries";

    /**
     * 交易规则
     */
    public static final String TRADE_RULE = "/app/user/dealRule";

    /**
     *反洗钱
     */
    public static final String TRADE_ANTI = "/app/user/money";
    /**
     * 获取汇率
     */
    public static final String GET_RATE="/market/exchange-rate/";
    public static final String GET_PANKOU = "/market/exchange-plate-mini";
}