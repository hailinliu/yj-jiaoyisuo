package com.sskj.tibi.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.http.CallBackOption;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.AddAddressActivity;


public class AddAddressActivityPresenter extends BasePresenter<AddAddressActivity> {

    public void addAddress(String type, String address, String notes) {
        httpService.addAddress(type, address, notes)
                .execute(new CallBackOption<HttpData>() {}
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == 0) {
                                mView.addSuccess();
                            }
                        }));
    }
}
