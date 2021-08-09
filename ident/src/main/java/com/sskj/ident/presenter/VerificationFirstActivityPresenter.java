package com.sskj.ident.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.ident.http.HttpConfig;
import com.sskj.ident.ui.activity.VerificationFirstActivity;
import com.sskj.lib.http.CallBackOption;


public class VerificationFirstActivityPresenter extends BasePresenter<VerificationFirstActivity> {

    public void verificationFirst(String username, String idCardNo) {
        httpService.verificationFirst(username, idCardNo)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.certificationSuccess();
                            }
                        }));
    }


}