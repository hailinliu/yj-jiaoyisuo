package com.sskj.level.presenter;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.level.bean.WSFiveBean1;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.LevelPanKouFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class LevelPanKouFragmentPresenter extends BasePresenter<LevelPanKouFragment> {

    StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    private CompositeDisposable compositeDisposable1;
  //  private MyWebSocketServer stockSocket1;
    public void getData(String code) {


        httpService.getProduct1(null).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
                List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                }.getType());
                mView.updateUI(list);

            }
        });
    }
/*    public void getPankou(String code) {
        OkGo.<String>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        com.sskj.level.bean.WSFiveBean1 bean = GSonUtil.GsonToBean(response.body(), WSFiveBean1.class);
                        mView.updateFive(bean);
                    }
                });

    }*/
    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.updateRate(bean);
            // LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
            //RxBus.getDefault().send(RxBusCode.RATE,rate);
        }));
    }
    StompClient mStompClient1;
    public void initHangqingSocket(){
        String url = "/topic/level/thumb";
        if(mStompClient1==null){
            mStompClient1 = Stomp.over(Stomp.ConnectionProvider.OKHTTP, HttpConfig.WS_BASE_URL);
            mStompClient1.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient1.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions1();
        Disposable dispTopic =  mStompClient1.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                    mView.refreshCoin(bean);
                    // mView.updateUI(bean);

                },throwable -> {
                    LogUtil.e("????????????",throwable);
                });
        compositeDisposable1.add(dispTopic);
        mStompClient1.connect();
        /*stockSocket1 = httpService.pushCoin1();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->mView.refreshCoin(newcoinbean),Throwable::getMessage);*/
    }
    public void initSocket(String code) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        String url = "/topic/level/trade-plate/"+code;
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/level-market/market-ws/websocket");
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                   // topicMessage.getPayload();
                    WSFiveBean bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean.class);
                    mView.updateUI(bean);
                    LiveDataBus.get().with(RxBusCode.NEW_LEVEL_HANG,WSFiveBean.class).postValue(bean);
                },throwable -> {
                    LogUtil.e("????????????",throwable);
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
    private void resetSubscriptions1() {
        if (compositeDisposable1 != null) {
            compositeDisposable1.dispose();
        }
        compositeDisposable1 = new CompositeDisposable();
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
        if(mStompClient1!=null&&mStompClient1.isConnected()){
            mStompClient1.disconnect();
            mStompClient1=null;
        }
        if (compositeDisposable != null){
            compositeDisposable.dispose();}
        if (compositeDisposable1 != null){
            compositeDisposable1.dispose();}


    }
}
