package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.fabi.bean.MerchantCenterBean;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.activity.MerchantCenterActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class MerchantCenterActivityPresenter extends BasePresenter<MerchantCenterActivity> {
    public void getShoppingInformation(String name){
        OkGo.<BaseBean<MerchantCenterBean>>post(HttpConfig.BASE_URL+"/otc/advertise/member")
                .params("name",name)
                .execute(/*new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        response.body();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                }*/new CallBackOption<BaseBean<MerchantCenterBean>>() {
                        }.loadBind(mView).execute(httpdata->{
                           mView.setData(httpdata.getData());
                        })
                );
    }
}
