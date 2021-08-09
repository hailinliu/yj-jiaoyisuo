package com.sskj.tibi.presenter;

import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;
import com.sskj.tibi.bean.OtherRecordBean;
import com.sskj.tibi.bean.SearchTypeBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.fragment.OtherRecordFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class OtherRecordFragmentPresenter extends BasePresenter<OtherRecordFragment> {

    public Flowable<List<OtherRecordBean>> getData(String page, String code) {
        return httpService.getOtherRecord(page, code)
                .converter(new JsonConverter<HttpData<PageBean<OtherRecordBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(listHttpData -> listHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());
    }
}
