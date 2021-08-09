package com.sskj.tibi.presenter;

import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;
import com.sskj.tibi.bean.RechargeRecordBean;
import com.sskj.tibi.bean.WithdrawRechargeCoinBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.fragment.RechargeRecordFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class RechargeRecordFragmentPresenter extends BasePresenter<RechargeRecordFragment> {

    public Flowable<List<RechargeRecordBean>> getRechargeRecord(String page,String code) {
        return httpService.getRechargeRecord(page,code)
                .converter(new JsonConverter<HttpData<PageBean<RechargeRecordBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(listHttpData -> listHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());
    }

}
