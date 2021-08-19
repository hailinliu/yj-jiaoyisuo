package com.sskj.hangqing.presenter;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.ui.fragment.NewChartFragment;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.util.CommonUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class NewChartFragmentPresenter extends BasePresenter<NewChartFragment> {
    StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    public void getKData(long from,String resolution, String code,long to) {
        httpService.getKData(from, resolution, code,to,true)
                .execute(new StringCallback() {

                             @Override
                             public void onSuccess(Response<String> response) {
                                 mView.hideKLoading();
                                 Gson gson = new Gson();
                                 List<Stock> list = gson.fromJson(response.body(),new TypeToken<List<Stock>>(){}.getType());
                                 mView.setChart(list);

                             }

                             @Override
                             public void onError(Response<String> response) {
                                 super.onError(response);
                                 mView.hideKLoading();
                             }

                         }
                );
    }
    private WebSocketObserver stockSocket;

    public void initSocket(String code,String type) {
        String url;
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        if(TextUtils.isEmpty(type)){
            url ="/topic/level/kline/"+code;
        }else {
           url ="/topic/level/kline/"+code+"/"+type;
        }
        if(mStompClient==null){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/market/market-ws/websocket");
        mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
            lifecycleEvent.getType();
        });}
        resetSubscriptions();
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    //mView.refreshCoin(topicMessage.getPayload());
                   // topicMessage.getPayload();
                    if(!TextUtils.isEmpty(topicMessage.getPayload())){
                        Gson gson = new Gson();
                        NewChartBean chartBean = gson.fromJson(topicMessage.getPayload(),NewChartBean.class);
                        mView.refreshCoin(chartBean);
                    }
                       /* NewChartBean chartBean =  GSonUtil.GsonToBean(topicMessage.getPayload(), NewChartBean.class);
                        *//*

                    }*/
                  /*  Gson gson = new Gson();
                    NewChartBean chartBean = gson.fromJson(topicMessage.getPayload(),NewChartBean.class);
                    LogUtil.e("chartBean的值:"+chartBean.toString(),new Throwable());*/
                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();
    }
    @Override
    public void detachView() {
        closeWebSocket();
        super.detachView();
    }
    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }
    public void closeWebSocket() {
        if (stockSocket!=null) {
            stockSocket.close();
            stockSocket = null;
        }
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }


    }
}
