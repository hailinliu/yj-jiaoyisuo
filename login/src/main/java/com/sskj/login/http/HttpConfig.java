package com.sskj.login.http;


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

    /**
     * 发送验证码（注册）
     */
    public static final String SEND_SMS = "/app/user/getCode";



    /**
     * 注册手机
     */
    public static final String REGISTERPHONE = "/uc/register/phone";
    /**
     * 手机验证码
     */
    public static final String PHONECODE="/uc/mobile/code";
    /**
     * 邮箱验证码
     */
    public static final String EMIALCODE="/uc/email/register/email/code";
    /**
     * 注册邮箱
     */
    public static final String REGISTEREMAIL = "/uc/register/email";
    /**
     * 重复手机号或者邮箱
     */
    public static final String REPEAT="/uc/register/check/phone";

    /**
     * 登录
     */
    public static final String LOGIN = "/uc/login";


    /**
     * 重置登录密码
     */
    public static final String RESET_LOGIN_PWD = "/app/user/editPswd";


    /**
     * 谷歌验证
     */
    public static final String CHECK_GOOGLE = "/uc/google/yzgoogle";

    /**
     * 验证验证码
     */
    public static final String CHECK_CODE = "/app/user/checkCode";

    /**
     * 登录成功，发送登录短信
     */
    public static final String SEND_LOGIN_SMS = "/app/user/loginMsg";
    //获取图形验证码
    public static final String GET_CODE="/app/user/getVCode";
}