package com.sskj.mine.presenter;


import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.ui.activity.MyAssertsActivity;

public class MyAssertsActivityPresenter extends BasePresenter<MyAssertsActivity> {
    public void getData(String id,int type){
        httpService.getAsserts(id,type).execute(new CallBackOption<HttpData<MyAssertBean>>() {

        }.loadBind(mView).execute(httpData -> {
            if(httpData.getData()!=null){
                mView.getAssert(httpData.getData());
            }

        }));
    }
}
