package com.sskj.hangqing.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.ChartFragment;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class ChartFragmentPresenter extends BasePresenter<ChartFragment> {

    StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    private String url;

    public void getKData(long from,String resolution, String code,long to) {
        httpService.getKData(from, resolution, code,to,false)
                .execute(new StringCallback() {

                             @Override
                             public void onSuccess(Response<String> response) {
                                 mView.hideKLoading();
                                 Gson gson = new Gson();
                                 List<Stock> list = gson.fromJson(response.body(),new TypeToken<List<Stock>>(){}.getType());
                                 if(list!=null&&list.size()>0){
                                     mView.setChart(list);
                                 }else {
                                     List<Stock> list1 =new ArrayList<>();
                                    Stock s= new Stock();
                                    s.setTime(System.currentTimeMillis());
                                    s.setPeriod(resolution);
                                     list1.add(s);
                                     mView.setChart(list1);
                                 }


                                 }

                             @Override
                             public void onError(Response<String> response) {
                                 super.onError(response);
                                 mView.hideKLoading();
                             }

                         }
                         );
    }


    /*public void getData(String code,int type) {
        httpService.getDeepData(code,type)
                .execute(new CallBackOption<HttpData<WSFiveBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateUI(httpData.getData())));
    }*/

    public void initSocket(String code,String type) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        if(TextUtils.isEmpty(type)){
            url = "/topic/market/kline/"+code;
        }else {
            url ="/topic/market/kline/"+code+"/"+type;
        }

        if(mStompClient==null){
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/market/market-ws/websocket");
            mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                lifecycleEvent.getType();
            });
            mStompClient.withClientHeartbeat(60000).withServerHeartbeat(60000).reconnect();
        }
        resetSubscriptions();
        Disposable dispTopic = mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    NewChartBean chartBean =  GSonUtil.GsonToBean(topicMessage.getPayload(), NewChartBean.class);
                   mView.refreshCoin(chartBean);

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

    public void getPankou(String code) {
        httpService.getPankou(code)
                .tag(this)
                .execute(new StringCallback(){

                    @Override
                    public void onSuccess(Response<String> response) {
                        WSFiveBean1 httpData = GSonUtil.GsonToBean(response.body(),WSFiveBean1.class);
                        if (httpData!= null){
                            WSFiveBean1 wsFiveBean = httpData;

                            Gson gson = new Gson();
                            AskBean bean =  GSonUtil.GsonToBean(gson.toJson(wsFiveBean.getAsk()),AskBean.class);
                            BidBean bean1 = GSonUtil.GsonToBean(gson.toJson(wsFiveBean.getBid()),BidBean.class);
                            //bean.setItems();
                            mView.updateUI(bean);
                            mView.updateUI(bean1);
                        } else {
                            AskBean bean = new AskBean();
                            BidBean bean1 = new BidBean();
                            mView.updateUI(bean1);
                            mView.updateUI(bean);

                        }
                    }
                });
    }
    public void initSocket(String code) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        String url = "/topic/market/trade-plate/"+code;
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/market/market-ws/websocket");
        mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
            lifecycleEvent.getType();
        });
        mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    WSFiveBean1.BidBean bidBean1 =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean1.BidBean.class);
                    // bean1.setBid(bidBean);
                    WSFiveBean1 bean1 = new WSFiveBean1();
                    if(bidBean1.getDirection()==0){
                        WSFiveBean1.BidBean bidBean =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean1.BidBean.class);
                        Gson gson = new Gson();
                        BidBean bean = GSonUtil.GsonToBean(gson.toJson(bidBean),BidBean.class);
                        mView.updateUI(bean);
                        //bean1.setBid(bidBean);


                    }else if(bidBean1.getDirection()==1){
                        WSFiveBean1.AskBean askBean =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean1.AskBean.class);
                        Gson gson = new Gson();
                        AskBean bean = GSonUtil.GsonToBean(gson.toJson(askBean),AskBean.class);
                        mView.updateUI(bean);
                        // bean1.setAsk(askBean);

                    }
                    // mView.updateUI(bean1);
                    //LiveDataBus.get().with(RxBusCode.DEPTH,WSFiveBean1.class).postValue(bean1);
                    //  LogUtil.i("?????????:"+topicMessage.getPayload());
                },throwable -> {
                    LogUtil.e("????????????",throwable);
                });
        mStompClient.connect();
        /*if (stockSocketFive == null) {
            stockSocketFive = httpService.pushCoinFive(code);
            stockDisposableFive = stockSocketFive
                    .map(s -> new Gson().fromJson(s, WSFiveBean1.class))
                    .subscribe(data -> mView.updateFive(data));
        }*//* else {
            stockSocketFive.connect();
        }*/

    }

    public void sendCode(String code) {
        initSocket(code);
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
