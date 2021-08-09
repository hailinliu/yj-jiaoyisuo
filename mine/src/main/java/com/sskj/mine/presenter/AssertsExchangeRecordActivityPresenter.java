package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.ExchangeRecordBean;
import com.sskj.mine.ui.activity.AssertsExchangeRecordActivity;


public class AssertsExchangeRecordActivityPresenter extends BasePresenter<AssertsExchangeRecordActivity> {


    public void getChangeRecord(String id){
            httpService.getChangeRecord(id).execute(new CallBackOption<HttpData<ExchangeRecordBean>>() {
            }.loadBind(mView).execute(httpData->{
                if(httpData.getData()!=null){
                    mView.upDateUI(httpData.getData().getList());
                }

            }));
        }

}
