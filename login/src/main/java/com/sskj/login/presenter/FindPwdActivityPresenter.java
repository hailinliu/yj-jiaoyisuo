package com.sskj.login.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.CommonUtil;
import com.sskj.login.bean.rxbus.AddressBean;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.FindPwdActivity;

public class FindPwdActivityPresenter extends BasePresenter<FindPwdActivity> {
    public void getPhoneCode(String country,String phone){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/mobile/forget/password/code")
                .params("country",country)
                .params("phone",phone)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.setP(httpData.getMsg());
                   // ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void getEmailCode(String email){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/email/forget/email/code")
                .params("email",email)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.setP(httpData.getMsg());
                   // ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void refindPhonePwd(String account,String code){
        OkGo.<HttpData>get(HttpConfig.BASE_URL+"/uc/forgetPasswordStep")
                .params("account",account)
                .params("code",code)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.result(httpData.getMsg());
                    //ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void refindEmailPwd(String account,String code){
        OkGo.<HttpData>get(HttpConfig.BASE_URL+"/uc/forgetPassworPhonedStep")
                .params("account",account)
                .params("code",code)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.result(httpData.getMsg());
                   // ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void getArray(){
        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
        String lan=  CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString());
        httpHeaders.put(SPConfig.LANGUAGE, CommonUtil.getHeaderLanguage(lan));
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/support/country")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AddressBean bean = GSonUtil.GsonToBean(response.body(),AddressBean.class);
                        mView.setData(bean);
                    }
                });
    }

}
