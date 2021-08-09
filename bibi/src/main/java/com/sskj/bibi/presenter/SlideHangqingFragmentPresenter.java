package com.sskj.bibi.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.bibi.ui.fragment.SlideHangqingFragment;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


public class SlideHangqingFragmentPresenter extends BasePresenter<SlideHangqingFragment> {

    public Flowable<List<CoinBean>> getProduct(String qu,String code) {
        return httpService.getProduct(qu,code)
                .converter(new JsonConverter<HttpData<List<CoinBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(HttpData::getData);

    }
}
