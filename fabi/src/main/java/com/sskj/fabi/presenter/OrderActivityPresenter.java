package com.sskj.fabi.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.ui.activity.OrderActivity;
import com.sskj.lib.http.CallBackOption;


public class OrderActivityPresenter extends BasePresenter<OrderActivity> {
    public void order(String payType, String tpwd, String order_no, String total_num, boolean isBuy, String min, String max, String type, String totalPrice, String price) {
        httpService.order(payType, tpwd, order_no, total_num, isBuy, min, max, type, totalPrice, price)
                .execute(new CallBackOption<HttpData<OrderDetailBean>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> mView.orderSuccess(httpData.getData())));

    }
}
