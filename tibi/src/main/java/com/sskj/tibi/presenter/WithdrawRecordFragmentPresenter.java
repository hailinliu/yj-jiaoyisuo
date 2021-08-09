package com.sskj.tibi.presenter;

import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;
import com.sskj.tibi.bean.WithdrawRechargeCoinBean;
import com.sskj.tibi.bean.WithdrawRecordBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.fragment.WithdrawRecordFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class WithdrawRecordFragmentPresenter extends BasePresenter<WithdrawRecordFragment> {

    public Flowable<List<WithdrawRecordBean>> getRecord(String page, String code) {
        return httpService.getWithdrawRecord(page, code)
                .converter(new JsonConverter<HttpData<PageBean<WithdrawRecordBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());
    }

    public void cancelOrder(String id) {
        httpService.cancelWithdraw(id)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            mView.cancelOrderSuccess();
                        }));
    }
}
