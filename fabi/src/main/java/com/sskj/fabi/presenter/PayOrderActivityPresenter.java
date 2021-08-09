package com.sskj.fabi.presenter;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.fabi.bean.NewOrderDetailBean;
import com.sskj.fabi.bean.OrderBean;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.activity.PayOrderActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;
import com.sskj.lib.util.CommonUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class PayOrderActivityPresenter extends BasePresenter<PayOrderActivity> {

    private StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
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
    public void preOrder(String orderSn){
            OkGo.<HttpData<NewOrderDetailBean>>post(HttpConfig.BASE_URL+"/otc/order/detail")
                    .params("orderSn",orderSn)
                    .execute(new CallBackOption<HttpData<NewOrderDetailBean>>() {
                    }.loadBind(mView).execute(httpdata->{
                        mView.updataUI(httpdata.getData());

                        //mView.setUI(httpdata.getMessage());
                    }));

        }
        public void cancelOrder(String orderSn){
            OkGo.<HttpData>post(HttpConfig.BASE_URL+"/otc/order/cancel")
                    .params("orderSn",orderSn)
                    .execute(new CallBackOption<HttpData>() {
                    }.loadBind(mView).execute(httpdata->{
                       // mView.updataUI(httpdata.getData());
                        mView.cancelOrder(httpdata.getMsg());
                        //mView.setUI(httpdata.getMessage());
                    }));
        }
        public void sureOrder(String orderSn,String payMode){
            OkGo.<HttpData>post(HttpConfig.BASE_URL+"/otc/order/pay")
                    .params("orderSn",orderSn)
                    .params("payMode",payMode)
                    .execute(new CallBackOption<HttpData>() {
                    }.loadBind(mView).execute(httpdata->{
                        // mView.updataUI(httpdata.getData());
                        mView.sureOrder(httpdata.getMsg());
                        //mView.setUI(httpdata.getMessage());
                    }));
        }
        public void initSocket(String orn){

            String url = "/topic/otc/order/"+orn;
            if(mStompClient==null){
                mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/otc/otc-ws/websocket");
                mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                    lifecycleEvent.getType();
                });
                mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
            }
            resetSubscriptions();
            Disposable dispTopic =   mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe((StompMessage topicMessage)->{
                        mView.setUI();
                       // topicMessage.getPayload();
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

}
