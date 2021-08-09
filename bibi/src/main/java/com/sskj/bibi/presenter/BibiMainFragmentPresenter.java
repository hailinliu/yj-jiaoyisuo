package com.sskj.bibi.presenter;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.BibiMainFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class BibiMainFragmentPresenter extends BasePresenter<BibiMainFragment> {




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
    private StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    public void initSocket(String code) {
        if (code==null)
            return;
        code= CommonUtil.dealReuqestCode(code);
        String url = "/topic/market/trade-plate/"+code;
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
                    WSFiveBean1.BidBean bidBean1 =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean1.BidBean.class);
                    // bean1.setBid(bidBean);
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
                    //  LogUtil.i("数据是:"+topicMessage.getPayload());
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
