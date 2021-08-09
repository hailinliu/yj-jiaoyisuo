package com.sskj.hangqing.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.PushTradeBean;
import com.sskj.hangqing.bean.TradeBean;
import com.sskj.hangqing.bean.TurnoverBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.TradeFragment;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class TradeFragmentPresenter extends BasePresenter<TradeFragment> {

    private String code;
    StompClient mStompClient;


    public void getTurnoverAmount(String size,String symbol){
        OkGo.<String>post(HttpConfig.BASE_URL+"/market/latest-trade")
                .params("size",size)
                .params("symbol",symbol)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();

                        List<TurnoverBean> list = GSonUtil.jsonToList(response.body(),TurnoverBean.class);
                        mView.updata(list);
                        //bean.getAmount();
                    }
                });
    }
    private CompositeDisposable compositeDisposable;
    public void initSocket(String code) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        String url = "/topic/market/trade/"+code;
        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/market/market-ws/websocket");
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    List<TurnoverBean> list = GSonUtil.jsonToList(topicMessage.getPayload(),TurnoverBean.class);
                   mView.updata(list);
                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();
        /*if (stockSocketFive == null) {
            stockSocketFive = httpService.pushCoinFive(code);
            stockDisposableFive = stockSocketFive
                    .map(s -> new Gson().fromJson(s, WSFiveBean1.class))
                    .subscribe(data -> mView.updateFive(data));
        }*//* else {
            stockSocketFive.connect();
        }*/
        this.code=code;
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

    public void sendCode(String code) {

        initSocket(code);
    }



}
