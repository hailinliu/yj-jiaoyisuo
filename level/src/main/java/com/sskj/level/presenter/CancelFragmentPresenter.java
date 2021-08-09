package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.level.bean.CancelBean;
import com.sskj.level.bean.DealLevelBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.CancelFragment;
import com.sskj.level.util.JsonConver;
import com.sskj.level.util.JsonConver1;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class CancelFragmentPresenter extends BasePresenter<CancelFragment> {

    public Flowable<List<CancelBean.ContentBean>> getCancel(String page, String pageSize, String symbol, String status){
        Flowable<List<CancelBean.ContentBean>> flowable;
        flowable = httpService.getCancel(page, pageSize,symbol,status)
                .converter(new JsonConver1<CancelBean>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getContent())
                .onErrorReturn(throwable -> new ArrayList<>());
        return flowable;
    }

  /*  public void getCancel1(String page, String pageSize,String symbol,String status){
        OkGo.<String>post(HttpConfig.BASE_URL + "/level/order/query-page")
                .params("pageNo", page)
                .params("pageSize", pageSize)
                .params("status",status)
                .params("symbol", symbol).execute(new StringCallback(){

            @Override
            public void onSuccess(Response<String> response) {
                response.body();
            }
        });
    }*/
}
