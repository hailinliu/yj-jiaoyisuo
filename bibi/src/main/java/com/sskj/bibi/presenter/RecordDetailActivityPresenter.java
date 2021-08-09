package com.sskj.bibi.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.bibi.bean.RecordDetailBean;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.activity.RecordDetailActivity;
import com.sskj.common.base.HttpData;
import com.sskj.lib.http.JsonCallBack;


public class RecordDetailActivityPresenter extends BasePresenter<RecordDetailActivity> {

    /*public void getData(String id, String tradeType) {
        httpService.getRecordDetail(id, tradeType)
                .execute(new JsonCallBack<HttpData<RecordDetailBean>>(this) {
                    @Override
                    public void onSuccess(Response<HttpData<RecordDetailBean>> response) {
                        HttpData<RecordDetailBean> httpData = response.body();
                        if (httpData.getStatus() == HttpConfig.OK) {

                            mView.updateUI(httpData.getData());
                        }
                    }
                });
    }*/
}
