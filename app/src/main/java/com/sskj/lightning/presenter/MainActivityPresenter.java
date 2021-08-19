package com.sskj.lightning.presenter;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.HangQingBean;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.DisposUtil;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.GonggaoBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lib.util.MyWebSocketServer;
import com.sskj.lightning.ui.activity.MainActivity;

import io.reactivex.disposables.Disposable;


public class MainActivityPresenter extends BasePresenter<MainActivity> {

    private MyWebSocketServer stockSocket;
    private MyWebSocketServer stockSocket1;
    private Disposable stockDispo;


    public void getGonggao() {
        httpService.getGonggao()
                .execute(new CallBackOption<HttpData<GonggaoBean>>() {
                }
                        .unLoadBind(mView)
                        .execute(httpData -> mView.showGonggao(httpData.getData())));
    }



    public void getRate(String fromUnit,String toUnit){
httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
}.loadBind(mView).execute(httpService->{
  String rate=  httpService.getData().toString();
  RateBean bean = new RateBean();
  bean.setName(toUnit);
  bean.setRate(rate);
    LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
    //RxBus.getDefault().send(RxBusCode.RATE,rate);
}));
    }
   /* public void initWebSocket1() {
        if (stockSocket1 == null) {
            stockSocket1 = httpService.pushCoinType1();
            stockDispo = stockSocket1
                    .map(s -> new Gson().fromJson(s, NewChartBean.class))
                    .subscribe(coinBean -> LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class)
                            .postValue(coinBean), Throwable::getMessage
                    );
        }

    }*/
    @SuppressLint("CheckResult")
    public void initNewSocket(){

       stockSocket1 = httpService.pushCoin();
        Disposable dispTopic =stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
               .subscribe(newcoinbean->LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class)
                       .postValue(newcoinbean),Throwable::getMessage);
    }
    @SuppressLint("CheckResult")
    public void initNewSocket1(){
        stockSocket1 = httpService.pushCoin1();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1,CoinBean1.class)
                        .postValue(newcoinbean),Throwable::getMessage);
    }
    @Override
    public void detachView() {

        DisposUtil.close(stockDispo);
        if (stockSocket != null) {
           // stockSocket.close();
            stockSocket.disconnectStomp();
            stockSocket = null;
        }
        if(stockSocket1!=null){
            stockSocket1.disconnectStomp();
            stockSocket1 = null;
        }

        super.detachView();
    }


    public void getNewVersion() {
        httpService.getVersion()
                .execute(new CallBackOption<HttpData<AppVersionBean>>() {
                    @Override
                    public void onError(Response<HttpData<AppVersionBean>> response) {
                        getGonggao();
                        super.onError(response);
                    }
                }.unLoadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getData() != null) {
                                String verName = APKVersionCodeUtils.getVerName(App.INSTANCE);
                                if (APKVersionCodeUtils.compareVersion(httpData.getData().getVersion(), verName) == 1) {
                                    mView.onVersionDataSuccess(httpData.getData());
                                } else {
                                    getGonggao();
                                }
                            } else {
                                getGonggao();
                            }
                        })
                       /* new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                response.body();
                            }
                        }*/
                );

    }
}
