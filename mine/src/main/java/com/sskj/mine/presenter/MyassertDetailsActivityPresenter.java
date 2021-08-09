package com.sskj.mine.presenter;


import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;
import com.sskj.mine.bean.MyAssertDetailBean;
import com.sskj.mine.ui.activity.MyassertDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class MyassertDetailsActivityPresenter extends BasePresenter<MyassertDetailsActivity> {
    public Flowable<List<MyAssertDetailBean.ListBean>> getMyAssertDetails(String id, String code,int pageNumber,int pageSize,int walletId) {
        return httpService.getAssertsDetails(id,code,pageNumber,pageSize,walletId)
                .converter(new JsonConverter<HttpData<MyAssertDetailBean>>() {
                })
                .adapt(new FlowableBody<>())
                .map(myAssertDetailBeanHttpData -> {
                    if(myAssertDetailBeanHttpData.getData().getList().isEmpty()){
                        myAssertDetailBeanHttpData.getData().getList().add(new MyAssertDetailBean.ListBean());
                    }
                    return myAssertDetailBeanHttpData.getData().getList();
                }).onErrorReturnItem(new ArrayList<>());
                //.map(pageBeanHttpData -> pageBeanHttpData.getData().getList()==null?new ArrayList<>().add(new MyAssertDetailBean.ListBean()):pageBeanHttpData.getData().getList());
    }
   /* public void getMyAssertDetails(String id, String code,int pageNumber,int pageSize,int walletId){
        httpService.getAssertsDetails(id,code,pageNumber,pageSize,walletId).execute(new CallBackOption<HttpData<MyAssertDetailBean>>() {
        }.loadBind(mView).execute(httpData->{
           mView.getData(httpData.getData());
        }));

    }*/
}
