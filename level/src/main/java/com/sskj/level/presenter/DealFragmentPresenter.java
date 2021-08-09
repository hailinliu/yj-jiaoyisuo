package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.level.bean.ContractNoteBean;
import com.sskj.level.bean.DealLevelBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.DealFragment;
import com.sskj.level.util.JsonConver;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class DealFragmentPresenter extends BasePresenter<DealFragment> {



    public Flowable<List<DealLevelBean.ContentBean>> getData(String page, String pageSize, String stockCode) {
        Flowable<List<DealLevelBean.ContentBean>> flowable;
        flowable = httpService.getChengjiao(page, pageSize,stockCode)
                .converter(new JsonConver<DealLevelBean>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getContent())
                .onErrorReturn(throwable -> new ArrayList<>());
        return flowable;
    }
    public void getData1(String page, String pageSize,String symbol) {
        OkGo.<String>post(HttpConfig.BASE_URL + "/level/order/close-page")
                .params("pageNo", page)
                .params("pageSize", pageSize)
                .params("symbol", symbol).execute(new StringCallback(){

            @Override
            public void onSuccess(Response<String> response) {
                response.body();
            }
        });
       
      
    }

}
