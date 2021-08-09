package com.sskj.login.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.ILoadBind;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.SettingActivity;

public class SettingActivityPresenter extends BasePresenter<SettingActivity> {
    public void register(String country, String pswd, String invitationCode, String code, String phone) {
        httpService.register(country, pswd, invitationCode, code, phone)
                .execute(new CallBackOption<BaseBean>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getCode() == 0) {
                                mView.registerSuccess(httpData.getMessage());
                            //  ToastUtil.showShort();  //    mView.registerSuccess();
                            }
                        }));
    }
    public void fundPwd(String password,String code,String account) {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/reset/phone/password")
                .params("password",password,true)
                .params("code",code)
                .params("account",account)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.result(httpData.getMsg());
                    //ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void fundEmailPwd(String password,String code,String account) {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/reset/email/password")
                .params("password",password,true)
                .params("code",code)
                .params("account",account)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.result(httpData.getMsg());
                    //ToastUtil.showShort(httpData.getMsg());
                }));
    }
    public void emailRegister(String code,String email,String password,String invitecode){
        httpService.emailregister(password,invitecode,code,email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpData->{
            if(httpData.getCode()==0){
                mView.registerSuccess(httpData.getMessage());
              //  ToastUtil.showShort(httpData.getMessage());
            }
        }));
    }
}
