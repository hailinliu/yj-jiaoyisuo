package com.sskj.mine.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.bean.GoogleCode;
import com.sskj.mine.ui.activity.VerifyGoogleActivity;


public class VerifyGoogleActivityPresenter extends BasePresenter<VerifyGoogleActivity> {
    public void submit(String chromeCode, String authCode, String secret, String qrcode) {
        httpService.bindGoogle(chromeCode, authCode, secret, qrcode)
                .execute(new JsonCallBack<HttpData>(this) {
                    @Override
                    public void onSuccess(Response<HttpData> response) {
                        mView.onBindSuccess();


                    }
                });
    }

    public void requestData() {
        httpService.googleQr()
                .execute(new CallBackOption<HttpData<GoogleCode>>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            mView.onGoogleCodeSuccess(httpData.getData());
                        }));
    }


}
