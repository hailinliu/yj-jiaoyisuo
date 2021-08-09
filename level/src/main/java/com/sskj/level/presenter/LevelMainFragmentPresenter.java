package com.sskj.level.presenter;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.level.bean.AskBean;
import com.sskj.level.bean.BidBean;
import com.sskj.level.bean.TotalRiskBean;
import com.sskj.level.ui.fragment.LevelMainFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.CommonUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class LevelMainFragmentPresenter extends BasePresenter<LevelMainFragment> {
    private StompClient mStompClient;

    private CompositeDisposable compositeDisposable;
    private long date;

    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.setUI(bean);
        }));
    }

    public void initSocket(String code) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        String url = "/topic/level/trade-plate/"+code;
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/level-market/level-market-ws/websocket");
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    WSFiveBean bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean.class);
                    mView.updateUI(bean);
                    LiveDataBus.get().with(RxBusCode.NEW_LEVEL_HANG,WSFiveBean.class).postValue(bean);
                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();

    }
    public void initSocket1() {

        String url = "/topic/level/position/"+ SPUtil.get(SPConfig.ID,"");
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/level-market/level-market-ws/websocket");
        mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
            lifecycleEvent.getType();
        });
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    TotalRiskBean totalRiskBean=  GSonUtil.GsonToBean(topicMessage.getPayload(), TotalRiskBean.class);
                    mView.setRisk(totalRiskBean);
                    //date = System.currentTimeMillis();
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

