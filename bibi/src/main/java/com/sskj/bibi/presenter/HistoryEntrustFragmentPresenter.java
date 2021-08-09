package com.sskj.bibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.bibi.bean.RecordHistoryBean;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.http.NewJsonConverter1;
import com.sskj.bibi.ui.fragment.HistoryEntrustFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;
import com.sskj.lib.http.NewJsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class HistoryEntrustFragmentPresenter extends BasePresenter<HistoryEntrustFragment> {

    public Flowable<List<RecordHistoryBean.ContentBean>> getEntrustHistoryFlow(String pageNo,String pageSize,String symbol) {
        return OkGo.<RecordHistoryBean>post(HttpConfig.BASE_URL+"/exchange/order/history")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("symbol",symbol)
                .converter(new NewJsonConverter1<RecordHistoryBean>())
                .adapt(new FlowableBody<>())
                .map(data->{
                    //RecordHistoryBean bean= GSonUtil.GsonToBean(data,RecordHistoryBean.class);
                    return data.getContent();

                })
                .onErrorReturnItem(new ArrayList<>());

       /* return httpService.getRecordHistory(p, size, code, type)
                .converter(new JsonConverter<HttpData<PageBean<RecordHistoryBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());*/
    }
    public void getEntrustHistoryFlow1(String pageNo,String pageSize,String symbol){
         OkGo.<String>post(HttpConfig.BASE_URL+"/exchange/order/history")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("symbol",symbol)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

}
