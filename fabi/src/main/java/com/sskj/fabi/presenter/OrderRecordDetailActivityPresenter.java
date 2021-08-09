package com.sskj.fabi.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.activity.OrderRecordDetailActivity;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;


public class OrderRecordDetailActivityPresenter extends BasePresenter<OrderRecordDetailActivity> {
    private String orderNum;

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void getOrderInfo(String orderNum) {

        httpService.getOrderRecordInfo(orderNum)
                .execute(new CallBackOption<HttpData<OrderDetailBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateUI(httpData.getData())));
    }


    /**
     * 标记已付款
     */
    public void markPay(int position, String orderId) {
        httpService.markPay(position, orderId)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            getOrderInfo(orderNum);

                        }));
    }

    /**
     * 取消订单
     */
    public void cancelOrder(String orderId) {
        httpService.cancelOrder(orderId)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            getOrderInfo(orderNum);

                        }));
    }

    /**
     * 账单申诉
     */
    public void allegeOrder(String orderId, String remarkInfo) {
        httpService.allegeOrder(orderId, remarkInfo)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            getOrderInfo(orderNum);

                        }));
    }

    /**
     * 确认放行
     *
     * @param orderId
     * @param pwd
     */
    public void letGo(String orderId, String pwd) {
        httpService.letGo(orderId, pwd)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            getOrderInfo(orderNum);
                        }));
    }
}
