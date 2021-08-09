package com.sskj.hangqing.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.ui.activity.GonggaoListActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class GonggaoListActivityPresenter extends BasePresenter<GonggaoListActivity> {
    public Flowable<List<NoticeBean.ContentBean>> getData(String page) {
        return httpService.getNotice(page,"20")
                .converter(new JsonConverter<BaseBean<NoticeBean>>() {})
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getContent())
                .onErrorReturnItem(new ArrayList<>());

    }


}
