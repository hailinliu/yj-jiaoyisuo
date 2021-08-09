package com.sskj.level.ui.fragment;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.presenter.BasePresenter;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.NewJsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class LevelAllEntrustPresenter extends BasePresenter<LevelAllEntrustFragment> {
    /*public Flowable<List<EntrustBean>> getEntrustList(String page,String size,String code,String status,String type) {

        return httpService.getRecordAll(page, size, code, status, type)
                .converter(new JsonConverter<HttpData<PageBean<EntrustBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());

    }*/

    public Flowable<List<HistoryBean.ContentBean>> getEntrustList(String pageNo, String pageSize, String symbol){
        return  OkGo.<HistoryBean>post(HttpConfig.BASE_URL+"/exchange/order/current")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("symbol",symbol)
                .converter(new NewJsonConverter<HistoryBean>(){})
                .adapt(new FlowableBody<>())
                .map(HistoryBean::getContent)
                .onErrorReturnItem(new ArrayList<>());
    }


    public void cancelEntrust(String orderId) {
        OkGo.<BaseBean>post(HttpConfig.BASE_URL+"/exchange/order/cancel/"+orderId).execute(new JsonCallBack<BaseBean>(this) {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                BaseBean httpData = response.body();
                ToastUtil.showShort(httpData.getMessage());
                mView.cancelReturn();
            }
        });

    }


}
