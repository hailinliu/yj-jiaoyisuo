package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.ui.activity.ContactServiceActivity;
import com.sskj.lib.http.CallBackOption;
import com.sskj.login.http.HttpConfig;

public class ContactServiceActivityPresenter extends BasePresenter<ContactServiceActivity> {
    public void getFeedback(String remark){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/feedback")
                .params("remark",remark)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    mView.setData(httpData.getMsg());

                }));

    }
}
