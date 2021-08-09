package com.sskj.bibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.bibi.bean.EntrustBean;
import com.sskj.bibi.bean.HistoryBean;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.AllEntrustFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;
import com.sskj.lib.http.NewJsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class AllEntrustPresenter extends BasePresenter<AllEntrustFragment>{
    /*public Flowable<List<EntrustBean>> getEntrustList(String page,String size,String code,String status,String type) {

        return httpService.getRecordAll(page, size, code, status, type)
                .converter(new JsonConverter<HttpData<PageBean<EntrustBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());

    }*/

    public Flowable<List<HistoryBean.ContentBean>> getEntrustList(String pageNo,String pageSize,String symbol){
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

    public void noTips() {
        OkGo.<HttpData<Object>>get(BaseHttpConfig.BASE_URL + HttpConfig.NO_TIPS)
                .params("status", "2")
                .execute(new JsonCallBack<HttpData<Object>>() {
                    @Override
                    public void onSuccess(Response<HttpData<Object>> response) {
                        mView.noTips();
                    }
                });
    }
}
