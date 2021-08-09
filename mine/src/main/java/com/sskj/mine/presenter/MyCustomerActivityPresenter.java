package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.CustomerBean;
import com.sskj.mine.ui.activity.MyCustomerActivity;

public class MyCustomerActivityPresenter extends BasePresenter<MyCustomerActivity> {
    public void getMyCustomer(String id){
        httpService.getCustomer(id).execute(new CallBackOption<HttpData<CustomerBean>>() {
        }.loadBind(mView).execute(httpData -> {
            if(httpData.getData()!=null){
                mView.setCustomer(httpData.getData().getList());
            }

        }));
    }
}
