package com.sskj.login.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.bean.LoginBean;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.LoginActivity;


public class LoginActivityPresenter extends BasePresenter<LoginActivity> {

    public void login( String pwd,String username) {

        httpService.login(pwd,username)
                .execute(new CallBackOption<BaseBean<LoginBean>>() {}
                                .loadBind(mView)
                                .execute(httpData -> {
                                    mView.loginSuccess(httpData.getData());
                                    if (httpData.getData().getGoogleState() != 0) {
                                        mView.googleCheck(httpData.getData());
                                    }
                                })
                      /*  new CallBackOption<BaseBean<LoginBean>>() {}
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getData().getGoogleState() == 0) {
                                mView.loginSuccess(httpData.getData());
                            } else {
                                mView.googleCheck(httpData.getData());
                            }
                        })*/
                );
    }


    public void checkGoogle(String code) {
        httpService.checkGoogle(code,"")
                .execute(new CallBackOption<HttpData>(){}
                .loadBind(mView)
                .execute(httpData -> {
                    if (httpData.getStatus() == HttpConfig.OK) {
                        mView.checkSuccess();
                    }
                }));
    }


    /**
     * 登录成功，发送登录短信
     * @param account
     */
    public void sendLoginSms(String account, String id, String token) {

        httpService.sendLoginSms(account, id, token)
                .execute(new CallBackOption<HttpData>(){}
                .loadBind(mView)
                .execute(httpData -> {

                }));
    }
}
