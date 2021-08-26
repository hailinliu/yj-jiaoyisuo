package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.fabi.bean.NewOrderDetailBean;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.activity.PayOrderSellActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class PayOrderSellActivityPresenter extends BasePresenter<PayOrderSellActivity> {
    private StompClient mStompClient;
    private CompositeDisposable compositeDisposable;


    public void preOrder(String orderSn){
        OkGo.<HttpData<NewOrderDetailBean>>post(HttpConfig.BASE_URL+"/otc/order/detail")
                .params("orderSn",orderSn)
                .execute(new CallBackOption<HttpData<NewOrderDetailBean>>() {
                    @Override
                    public void onError(Response<HttpData<NewOrderDetailBean>> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onSuccess(Response<HttpData<NewOrderDetailBean>> response) {
                        super.onSuccess(response);
                    }
                }.loadBind(mView).execute(httpdata->{
                    mView.updataUI(httpdata.getData());
                }));
      /*  OkGo.<String>post(HttpConfig.BASE_URL+"/otc/order/detail")
                .params("orderSn",orderSn)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        response.body();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });*/
    }
    /*public void surePay(String orderSn,String payMode){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/otc/order/pay")
                .params("orderSn",orderSn)
                .params("payMode",payMode)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.sucess(httpdata.getData());
                }));
    }*/
    public void orderRealease(String jyPassword,String orderSn){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/otc/order/release")
                .params("orderSn",orderSn)
                .params("jyPassword",jyPassword,true)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.sucess(httpdata.getMsg());
                }));
    }
    public void initSocket(String orn){

        String url = "/topic/otc/order/"+orn;
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/otc/otc-ws/websocket");
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });

            mStompClient.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic = mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    mView.setUI();
                   /* NewOrderDetailBean bean = GSonUtil.GsonToBean(topicMessage.getPayload(),NewOrderDetailBean.class);
                    mView.updataUI(bean);*/
                    topicMessage.getPayload();
                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();
    }

    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void detachView() {
        closeWebSocket();
        super.detachView();
    }
    public void closeWebSocket() {

        if(mStompClient!=null&&mStompClient.isConnected()){
            mStompClient.disconnect();
            mStompClient=null;
        }

        if (compositeDisposable != null)
            compositeDisposable.dispose();

    }
    public void getShenru(String orderSn,String remark,String type){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/otc/order/appeal")
                .params("orderSn",orderSn)
                .params("remark",remark)
                .params("type",type)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.sucess(httpdata.getMsg());
                }));
    }
}
