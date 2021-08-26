package com.sskj.hangqing.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.DeepthFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CommonUtil;

import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class DeepthFragmentPresenter extends BasePresenter<DeepthFragment> {

    private WebSocketObserver stockSocket;
    private Disposable stockDisposable;
    private String code;
    StompClient mStompClient;


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
    private CompositeDisposable compositeDisposable;
   public void initSocket(String code) {
       if (code==null)
           return;
       code= CommonUtil.dealReuqestCode(code);
       String url = "/topic/market/trade-plate/"+code;
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
       this.code=code;
   }
    public void sendCode(String code) {
        initSocket(code);
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
