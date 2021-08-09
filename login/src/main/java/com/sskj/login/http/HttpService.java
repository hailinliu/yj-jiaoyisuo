package com.sskj.login.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.LanguageUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.NewBaseBean;
import com.sskj.lib.bean.LoginBean;
import com.sskj.lib.util.CommonUtil;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {

    public PostRequest<BaseBean> register(String country, String password , String invitationCode, String code, String phone) {
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL + HttpConfig.REGISTERPHONE)
                .params("country", country)
                .params("password", password ,true)
                .params("phone",phone)
                .params("promotion", invitationCode)
                .params("code", code);
    }
    public PostRequest<BaseBean> emailregister(String password, String invitationCode, String code, String email) {
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL + HttpConfig.REGISTEREMAIL)
                .params("password", password ,true)
                .params("email",email)
                .params("promotion", invitationCode)
                .params("code", code);
    }
    public PostRequest<NewBaseBean> phoneCode(String country,String phone) {
        return OkGo.<NewBaseBean>post(HttpConfig.BASE_URL + HttpConfig.PHONECODE)
                .params("country",country)
                .params("phone", phone);
    }
    public PostRequest<NewBaseBean> emailCode(String email) {
        return OkGo.<NewBaseBean>post(HttpConfig.BASE_URL + HttpConfig.EMIALCODE)
                .params("email", email);
    }
    public PostRequest<String> isRepeat(String phone) {
        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
        String lan=  CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString());
        httpHeaders.put(SPConfig.LANGUAGE, CommonUtil.getHeaderLanguage(lan));
        return OkGo.<String>post(HttpConfig.BASE_URL + HttpConfig.REPEAT)
                .params("phone", phone);
    }
    public PostRequest<BaseBean<LoginBean>> login(String pwd, String username) {
        return OkGo.<BaseBean<LoginBean>>post(HttpConfig.BASE_URL + HttpConfig.LOGIN)
                .params("password",pwd,true)
                .params("username", username)
                ;
    }

    /**
     * 重置登录密码
     *
     * @param mobile 手机号/邮箱号
     * @param opwd   密码
     * @param code   验证码
     * @return
     */
    public PostRequest<HttpData> resetLoginPwd(String areaCode, String mobile, String opwd, String code) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.RESET_LOGIN_PWD)
                .params("areaCode", mobile.contains("@") ? null : areaCode.replace("+",""))
                .params("account", mobile)
                .params("pswd", opwd,true)
                .params("code", code);
    }
    public GetRequest<HttpData> checkGoogle(String code, String stockUserId) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.CHECK_GOOGLE)
                .params("codes", code)
                ;
    }
    public GetRequest<HttpData> sendLoginSms(String account, String id, String token) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.SEND_LOGIN_SMS)
                .headers("token", token)
                .params("account", account)
                .params("id", id)
                ;
    }

}