package com.sskj.hangqing.presenter;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.NewCoinListFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class NewCoinFragmentPresenter extends BasePresenter<NewCoinListFragment> {
    private StompClient mStompClient;
    private StompClient mStompClient1;
    public CompositeDisposable compositeDisposable;
    public CompositeDisposable compositeDisposable1;
    /**
     * 行情数据
     *
     * @return
     */
    public void getProduct(boolean isLevel) {
        if(isLevel){
            httpService.getProduct1(null).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    if(!TextUtils.isEmpty(response.body())){
                    Gson gson = new Gson();
                    List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                    }.getType());
                    mView.updateUI(list);
                }}

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                }
            });
        }else {
            httpService.getProduct2(null).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    if(!TextUtils.isEmpty(response.body())){
                    Gson gson = new Gson();
                    List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                    }.getType());
                    mView.updateUI(list);
                }}
            });
        }

    }
    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.setUI(bean);
           // LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
            //RxBus.getDefault().send(RxBusCode.RATE,rate);
        }));
    }
  /*  public void initNewSocket(){
        stockSocket1 = httpService.pushCoin();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->mView.refreshCoin2(newcoinbean),Throwable::getMessage);
    }
    public void initNewSocket1(){
        stockSocket1 = httpService.pushCoin1();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean-> mView.refreshCoin1(newcoinbean),Throwable::getMessage);
    }*/
    @SuppressLint("CheckResult")
    public void initNewSocket(){
        String url = "/topic/market/thumb";
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, HttpConfig.WS_BASE_URL);
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                    mView.refreshCoin2(bean);
                    // mView.updateUI(bean);

                },throwable -> {
                    com.sskj.common.log.LogUtil.e("链接错误",throwable);
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
        if(mStompClient1==null){
            mStompClient1 = Stomp.over(Stomp.ConnectionProvider.OKHTTP, HttpConfig.WS_BASE_URL);
            mStompClient1.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient1.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
        }
        resetSubscriptions1();
        Disposable dispTopic =  mStompClient1.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                    mView.refreshCoin1(bean);
                    // mView.updateUI(bean);

                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable1.add(dispTopic);
        mStompClient1.connect();
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
    private void resetSubscriptions1() {
        if (compositeDisposable1 != null) {
            compositeDisposable1.dispose();
        }
        compositeDisposable1 = new CompositeDisposable();
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
        if (compositeDisposable != null){
            compositeDisposable.dispose();
        }

        if (compositeDisposable1 != null){
            compositeDisposable1.dispose();
        }


        super.detachView();
    }
}