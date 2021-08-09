package com.sskj.tibi.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.tibi.bean.RechargeBean;
import com.sskj.tibi.http.HttpConfig;



public class RechargeFragmentPresenter extends BasePresenter {

   /* public void getData(int type,String code) {
        httpService.recharge(type,code)
                .execute(new CallBackOption<HttpData<RechargeBean>>() {}
                        .unLoadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.updateUI(httpData.getData());
                            }
                        }));
    }*/
}
