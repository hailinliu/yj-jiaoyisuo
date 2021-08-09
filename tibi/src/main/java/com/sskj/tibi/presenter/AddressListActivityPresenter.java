package com.sskj.tibi.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.tibi.bean.AddressBean;
import com.sskj.tibi.bean.AddressListBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.AddressListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AddressListActivityPresenter extends BasePresenter<AddressListActivity> {

    public void getAddressList() {
        httpService.getAddressList()
                .execute(new CallBackOption<HttpData<Map<String, List<AddressBean>>>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                ArrayList<AddressListBean> addressListBeans = new ArrayList<>();
                                addressListBeans.add(new AddressListBean("USDT", httpData.getData().get("BTC")));
                                mView.updateUI(addressListBeans);
                            }
                        }));

    }

    public void deleteAddress(String id) {
        httpService.deleteAddress(id)
                .execute(new CallBackOption<HttpData>() {
                }
                        .unLoadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.deleteSuccess();
                            }
                        }));
    }
}
