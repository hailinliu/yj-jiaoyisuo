package com.sskj.login.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.login.bean.rxbus.AgreementBean;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.AgreementActivity;

public class AgreementActivityPresenter extends BasePresenter<AgreementActivity> {

    public void getContent(String sysHelpClassification){

        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/ancillary/system/help")
                .params("sysHelpClassification",sysHelpClassification)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AgreementBean bean =  GSonUtil.GsonToBean(response.body(), AgreementBean.class);
                        mView.updataUI(bean);
                    }
                });
    }
}
