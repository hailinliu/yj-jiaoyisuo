package com.sskj.lib.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.ui.activity.FingerLoginActivity;
import com.sskj.lib.util.CommonUtil;


public class FingerLoginActivityPresenter extends BasePresenter<FingerLoginActivity> {

    public void login(String pwd) {
        OkGo.<HttpData>post(BaseHttpConfig.BASE_URL + "/Home/Qbw/check_opwd")
                .params("opwd",pwd,true)
                .execute(new JsonCallBack<HttpData>(this) {
                    @Override
                    public void onSuccess(Response<HttpData> response) {
                        HttpData httpData = response.body();
                        ToastUtil.showShort(httpData.getMsg());
                        if (httpData.getStatus() == BaseHttpConfig.OK) {
                            mView.loginSuccess();
                        }
                    }
                });
    }

}
