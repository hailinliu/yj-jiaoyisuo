package com.sskj.lightning.presenter;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.log.LogUtil;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.WSFiveBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.HangQingBean;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.DisposUtil;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.GonggaoBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lib.util.MyWebSocketServer;
import com.sskj.lightning.http.HttpConfig;
import com.sskj.lightning.ui.activity.MainActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class MainActivityPresenter extends BasePresenter<MainActivity> {

    private StompClient mStompClient;
    private StompClient mStompClient1;
    public CompositeDisposable compositeDisposable;

    public void getGonggao() {
        httpService.getGonggao()
                .execute(new CallBackOption<HttpData<GonggaoBean>>() {
                }
                        .unLoadBind(mView)
                        .execute(httpData -> mView.showGonggao(httpData.getData())));
    }



    public void getRate(String fromUnit,String toUnit){
httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
}.loadBind(mView).execute(httpService->{
  String rate=  httpService.getData().toString();
  RateBean bean = new RateBean();
  bean.setName(toUnit);
  bean.setRate(rate);
    LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
    //RxBus.getDefault().send(RxBusCode.RATE,rate);
}));
    }
   /* public void initWebSocket1() {
        if (stockSocket1 == null) {
            stockSocket1 = httpService.pushCoinType1();
            stockDispo = stockSocket1
                    .map(s -> new Gson().fromJson(s, NewChartBean.class))
                    .subscribe(coinBean -> LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class)
                            .postValue(coinBean), Throwable::getMessage
                    );
        }

    }*/
    @SuppressLint("CheckResult")
    public void initNewSocket(){
        String url = "/topic/market/thumb";
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, HttpConfig.WS_BASE_URL);
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                 // Thread thread =  Thread.currentThread();
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                   // RxBus.getDefault().send(10010,bean);
                    LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class)
                            .postValue(bean);
                   // mView.updateUI(bean);

                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();
       /* if(stockSocket1==null){
            stockSocket1 = httpService.pushCoin();
        }
        stockDispo =stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class)
                        .postValue(newcoinbean),Throwable::getMessage);*/

    }
    @SuppressLint("CheckResult")
    public void initNewSocket1(){
        String url = "/topic/level/thumb";
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, HttpConfig.WS_BASE_URL);
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                    LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1,CoinBean1.class)
                            .postValue(bean);
                    // mView.updateUI(bean);

                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();
        /*stockSocket1 = httpService.pushCoin1();
        stockDispo1 =stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1,CoinBean1.class)
                        .postValue(newcoinbean),Throwable::getMessage);*/
    }

    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }

    public void getNewVersion() {
        httpService.getVersion()
                .execute(new CallBackOption<HttpData<AppVersionBean>>() {
                    @Override
                    public void onError(Response<HttpData<AppVersionBean>> response) {
                        getGonggao();
                        super.onError(response);
                    }
                }.unLoadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getData() != null) {
                                String verName = APKVersionCodeUtils.getVerName(App.INSTANCE);
                                if (APKVersionCodeUtils.compareVersion(httpData.getData().getVersion(), verName) == 1) {
                                    mView.onVersionDataSuccess(httpData.getData());
                                } else {
                                    getGonggao();
                                }
                            } else {
                                getGonggao();
                            }
                        })
                       /* new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                response.body();
                            }
                        }*/
                );

    }
    @Override
    public void detachView() {

        if(mStompClient!=null&&mStompClient.isConnected()){
            mStompClient.disconnect();
            mStompClient=null;
        }
        if(mStompClient1!=null&&mStompClient1.isConnected()){
            mStompClient1.disconnect();
            mStompClient1=null;
        }
        if (compositeDisposable != null)
            compositeDisposable.dispose();


        super.detachView();
    }

}
