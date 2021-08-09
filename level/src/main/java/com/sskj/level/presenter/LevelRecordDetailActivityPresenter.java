package com.sskj.level.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.level.bean.RecordDetailBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.activity.LevelRecordDetailActivity;
import com.sskj.lib.http.JsonCallBack;

public class LevelRecordDetailActivityPresenter extends BasePresenter<LevelRecordDetailActivity> {
    public void getData(String id, String tradeType) {
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
    }
}
