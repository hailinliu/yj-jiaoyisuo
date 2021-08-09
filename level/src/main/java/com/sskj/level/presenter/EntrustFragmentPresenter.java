package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.level.bean.ContentBean;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.EntrustFragment;
import com.sskj.level.util.JsonConver2;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class EntrustFragmentPresenter extends BasePresenter<EntrustFragment> {

    public Flowable<List<LevelHistoryBean.ContentBean>> getData(String code, String pageNum, String pageSize) {
        return httpService.getWeituo(code, pageNum, pageSize)
                .converter(new JsonConver2<LevelHistoryBean>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getContent())
                .onErrorReturn(throwable -> new ArrayList<>())
                ;
    }


    public void cancelEntrust(String orderId) {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/level/order/cancel")
                .params("orderId",orderId)
                .execute(new JsonCallBack<HttpData>(this) {
                    @Override
                    public void onSuccess(Response<HttpData> response) {
                        HttpData httpData = response.body();
                        mView.cancelReturn(httpData.getMsg());
                    }
                });

    }

}
