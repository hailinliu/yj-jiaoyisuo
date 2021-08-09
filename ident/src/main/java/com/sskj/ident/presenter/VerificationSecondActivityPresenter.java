package com.sskj.ident.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.ident.http.HttpConfig;
import com.sskj.ident.ui.activity.VerificationSecondActivity;
import com.sskj.lib.http.CallBackOption;

import java.io.File;


public class VerificationSecondActivityPresenter extends BasePresenter<VerificationSecondActivity> {


    /**
     * 高级认证
     *
     * @param front_img    身份证正面
     * @param back_img     身份证反面
     * @param handheld_img 手持身份证照
     * @return
     */
    public void apiAuthenticationAdvancedcertification(String front_img, String back_img, String handheld_img) {
        httpService.apiAuthenticationAdvancedcertification(front_img, back_img, handheld_img).execute(new CallBackOption<HttpData>() {
        }
                .loadBind(mView)
                .execute(httpData -> {
                    ToastUtil.showShort(httpData.getMsg());
                    if (httpData.getStatus() == HttpConfig.OK) {
                        mView.certificationSuccess();
                    }
                }));
    }

    public void submitVerify(File file) {
        httpService.submitVerify(file)
                .execute(new CallBackOption<HttpData<String>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.uploadSuccess(httpData.getData());
                            }
                        }));
    }
}
