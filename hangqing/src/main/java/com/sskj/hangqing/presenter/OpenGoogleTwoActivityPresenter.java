package com.sskj.hangqing.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.hangqing.ui.activity.OpenGoogleTwoActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class OpenGoogleTwoActivityPresenter extends BasePresenter<OpenGoogleTwoActivity> {
    public void getGoogleCode(){

        httpService.getGoogleCode().execute(new CallBackOption<BaseBean>() {
            @Override
            public void onError(Response<BaseBean> response) {
                super.onError(response);
            }
        }.loadBind(mView).execute(httpdata->{
            mView.updataUI(httpdata.getData().toString());
        }));
    }
}
