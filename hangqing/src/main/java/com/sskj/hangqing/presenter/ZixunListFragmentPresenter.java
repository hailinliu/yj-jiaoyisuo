package com.sskj.hangqing.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.ZixunListBean;
import com.sskj.hangqing.ui.fragment.ZixunListFragment;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class ZixunListFragmentPresenter extends BasePresenter<ZixunListFragment> {

    public Flowable<List<ZixunListBean>> getData(String page) {
        if (!page.equals("1")){
            return Flowable.fromIterable(new ArrayList<ZixunListBean>()).toList().toFlowable();
        }
        return httpService.getZixunList(page)
                .converter(new JsonConverter<HttpData<PageBean<ZixunListBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());
    }

    public void getBanner() {
        httpService.getBanner("3")
                .execute(new CallBackOption<HttpData<List<HangqingBannerBean>>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateBanner(httpData.getData())));
    }

}
