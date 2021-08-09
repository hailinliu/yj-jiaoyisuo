package com.sskj.hangqing.presenter;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.hangqing.ui.activity.OPenGoogleFourActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class OPenGoogleFourActivityPresenter extends BasePresenter<OPenGoogleFourActivity> {

    public void bindgoogle(String codes,String secret){
        httpService.bindGoogle(codes, secret).execute(new CallBackOption<BaseBean>() {
            @Override
            public void onError(Response<BaseBean> response) {
                super.onError(response);
            }

            @Override
            public void onSuccess(Response<BaseBean> response) {
                super.onSuccess(response);
            }
        }.loadBind(mView).execute(httpdata->{
            mView.setUi(httpdata.getMessage());

        }));
       /* httpService.bindGoogle(codes, secret).execute(new StringCallback(){
            @Override
            public void onSuccess(Response<String> response) {
              response.body();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });*/
    }
}
