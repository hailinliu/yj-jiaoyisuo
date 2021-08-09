package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.ui.activity.AssertsExchangeActivity;

public class AssertsExchangeActivityPresenter extends BasePresenter<AssertsExchangeActivity> {
    public void getData(String id,int type){
        httpService.getAsserts(id,type).execute(new CallBackOption<HttpData<MyAssertBean>>() {

        }.loadBind(mView).execute(httpData -> {
            if(httpData.getData()!=null){
                mView.getAssert(httpData.getData().getFund());
            }

        }));
    }

    public void submitExchange(String id,String baseCode,String dealCode,String dealNum,String dealPswd){
        httpService.getAssertsExchange(id,baseCode,dealCode,dealNum,dealPswd).execute(new CallBackOption<HttpData>() {
        }.loadBind(mView).execute(httpData -> {
            mView.isSuccess(httpData.getMsg());
        }));
    }
}
