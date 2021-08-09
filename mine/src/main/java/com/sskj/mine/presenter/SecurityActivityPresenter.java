package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.SecurityActivity;


/**
 * @author Hey
 * Create at  2019/05/01
 */
public class SecurityActivityPresenter extends BasePresenter<SecurityActivity> {


    public void cancelShop() {
        httpService.cancelShop()
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                ToastUtil.showShort(httpData.getMsg());
                                mView.cancelShopSuccess();
                            }
                        }));
    }


    //开启谷歌验证
    public void openGoogleVerify(String code) {
        httpService.openGoogleVerify(code)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            mView.setGoogleVerifySuccess();
                        }));
    }

    //关闭谷歌验证
    public void closeGoogleVerify(String code, String telCode) {
        httpService.closeGoogleVerify(code, telCode)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            mView.setGoogleVerifySuccess();
                        }));
    }

    //解绑谷歌验证
    public void untriedGoogleVerify(String code, String telCode) {
        httpService.untriedGoogleVerify(code, telCode)

                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            mView.setGoogleVerifySuccess();
                        }));
    }

}
