package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.level.bean.RecordHistoryBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.http.NewJsonConverter1;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class LevelHistoryEntrustFragmentPresenter extends BasePresenter<LevelHistoryEntrustFragment> {
    public Flowable<List<RecordHistoryBean.ContentBean>> getEntrustHistoryFlow(String pageNo, String pageSize, String symbol) {
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

    }
}
