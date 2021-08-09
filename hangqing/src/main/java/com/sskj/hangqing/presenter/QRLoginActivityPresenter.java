package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.activity.QRLoginActivity;
import com.sskj.lib.http.CallBackOption;

public class QRLoginActivityPresenter extends BasePresenter<QRLoginActivity> {
    public void saomaLogin(String qrcodeId){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/login/qrcode/login")
                .params("qrcodeId",qrcodeId)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.setMessage(httpData.getMsg());
                }));
    }
}
