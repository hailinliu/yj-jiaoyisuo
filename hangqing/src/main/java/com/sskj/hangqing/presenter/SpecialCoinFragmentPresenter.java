package com.sskj.hangqing.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.SpecialCoinFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class SpecialCoinFragmentPresenter extends BasePresenter<SpecialCoinFragment> {
    private StompClient mStompClient;
    public CompositeDisposable compositeDisposable;
    public void getData() {
        httpService.getProduct2(null).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(!TextUtils.isEmpty(response.body())){
                Gson gson = new Gson();
               List<CoinBean1> list = gson.fromJson(response.body(),new TypeToken<List<CoinBean1>>(){}.getType());
               mView.updateCoin(list);
            }}
        });

    }
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
                    CoinBean1 bean =  GSonUtil.GsonToBean(topicMessage.getPayload(),CoinBean1.class);
                    mView.refreshCoin(bean);
                    // mView.updateUI(bean);

                },throwable -> {
                    com.sskj.common.log.LogUtil.e("链接错误",throwable);
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
        if(mStompClient!=null&&mStompClient.isConnected()){
            mStompClient.disconnect();
            mStompClient=null;
        }
        if (compositeDisposable != null)
            compositeDisposable.dispose();

        super.detachView();
    }
}
