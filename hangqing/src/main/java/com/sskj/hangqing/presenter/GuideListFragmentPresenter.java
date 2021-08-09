package com.sskj.hangqing.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.GuideListBean;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.ui.activity.GuideListActivity;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


public class GuideListFragmentPresenter extends BasePresenter<GuideListActivity> {
    public Flowable<List<GuideListBean>> getData(String page) {
        return httpService.getGuideList(page)
                .converter(new JsonConverter<HttpData<PageBean<GuideListBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());
    }

    public void getBanner() {
        httpService.getBanner("4")
                .execute(new CallBackOption<HttpData<List<HangqingBannerBean>>>() {
                }.unLoadBind(mView)
                .execute(httpData-> mView.updateBanner(httpData.getData())));
    }
}
