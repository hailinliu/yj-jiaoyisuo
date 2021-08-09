package com.sskj.bibi.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.PankouUpDownFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class PankouUpDownFragmentPresenter extends BasePresenter<PankouUpDownFragment> {


    private CompositeDisposable compositeDisposable;
    StompClient mStompClient;
    public void getData(String code) {


            httpService.getProduct2(null).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Gson gson = new Gson();
                    List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                    }.getType());
                    mView.updateUI(list);

                }
            });
        }



    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.updateRate(bean);

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
    private MyWebSocketServer stockSocket1;
   public void initHangqingSocket(){
       stockSocket1 = httpService.pushCoin();
       stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
               .subscribe(newcoinbean->mView.refreshCoin(newcoinbean),Throwable::getMessage);
   }
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
       // mStompClient.lifecycle()
        Disposable dispTopic =  mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    WSFiveBean1.BidBean bidBean1 =  GSonUtil.GsonToBean(topicMessage.getPayload(),WSFiveBean1.BidBean.class);
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

                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient.connect();

    }
    StompClient mStompClient2;
    public void getStepWebSocket(String code,String i){
        if (code==null)
            return;
           closeWebSocket();
           String url = "/topic/market/trade-depth/"+code+"_step"+i;
           if(mStompClient2==null){
               mStompClient2 = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/market/market-ws/websocket");
               mStompClient2.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                   lifecycleEvent.getType();
               });
               mStompClient2.withClientHeartbeat(1000).withServerHeartbeat(1000).reconnect();
           }
        resetSubscriptions();
        // mStompClient.lifecycle()
        Disposable dispTopic =  mStompClient2.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    BidBean bidBean1 =  GSonUtil.GsonToBean(topicMessage.getPayload(),BidBean.class);
                    if(bidBean1.getDirection()==0){
                        BidBean bean = GSonUtil.GsonToBean(topicMessage.getPayload(),BidBean.class);
                        mView.updateUI(bean);
                        //bean1.setBid(bidBean);


                    }else if(bidBean1.getDirection()==1){
                        AskBean bean = GSonUtil.GsonToBean(topicMessage.getPayload(),AskBean.class);
                        mView.updateUI(bean);
                        // bean1.setAsk(askBean);

                    }

                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        compositeDisposable.add(dispTopic);
        mStompClient2.connect();

    }

    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
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
