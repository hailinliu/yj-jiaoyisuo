package com.sskj.bibi.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.bibi.ui.activity.SlideActivity;
import com.sskj.common.base.HttpData;
import com.sskj.lib.http.JsonCallBack;

import java.util.List;


public class SlideActivityPresenter extends BasePresenter<SlideActivity> {
    public void getList() {
        httpService.getHorization()
                .execute(new JsonCallBack<HttpData<List<String>>>(this,false) {
                    @Override
                    public void onSuccess(Response<HttpData<List<String>>> response) {
                        mView.getListSuccess(response.body().getData());
                    }
                });
    }
}
