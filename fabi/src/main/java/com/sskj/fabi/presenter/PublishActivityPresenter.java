package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.bean.PublishPrice;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.activity.PublishActivity;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;

import java.util.List;


public class PublishActivityPresenter extends BasePresenter<PublishActivity> {
    // 获取支付方式
    public void requestPayWayList() {
        httpService.requestPayWayList()
                .execute(new CallBackOption<HttpData<List<PayWayItem>>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updatePayWay(httpData.getData())));


    }

    public void publish(boolean isBuy, String remark, String num, String min, String max, String price,
                        String payType, String pwd) {
        httpService.fabiPublish(isBuy, remark, num, min, max, price, payType, pwd)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            LiveDataBus.get().with(RxBusCode.PUBLISH_SUCCESS).postValue(1);
                            mView.publishSuccess();
                        }));

    }

    // 获取单价
    public void requestUnitPrice() {
        OkGo.<HttpData<PublishPrice>>post(HttpConfig.BASE_URL + HttpConfig.FABI_PUBLISH_PRICELIMIT)
                .execute(new CallBackOption<HttpData<PublishPrice>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updatePrice(httpData.getData().getValue())));
    }

    // 获取最小购买数量
    public void requestLimitNum() {
        OkGo.<HttpData<PublishPrice>>post(HttpConfig.BASE_URL + HttpConfig.FABI_PUBLISH_LIMIT_NUM)
                .execute(new CallBackOption<HttpData<PublishPrice>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateLimitNum(httpData.getData().getValue())));
    }

}
