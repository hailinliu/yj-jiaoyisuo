package com.sskj.hangqing.presenter;

import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.ui.activity.GonggaoActiivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class GonggaoActiivityPresenter extends BasePresenter<GonggaoActiivity> {

    public void getNotice() {
        httpService.getNotice("1", "10")
                .execute(new CallBackOption<BaseBean<NoticeBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateNotice(httpData.getData().getContent())));
    }
}
