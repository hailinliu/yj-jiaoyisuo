package com.sskj.hangqing.presenter;

import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.ui.activity.MarketActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;


public class MarketActivityPresenter extends BasePresenter<MarketActivity> {
    private MyWebSocketServer stockSocket1;
    public void getData(String code,boolean isLevel) {
        if(isLevel){
            //LogUtil.e(+"旧");

            httpService.getProduct1(null).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    Gson gson = new Gson();
                    List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                    }.getType());
                    mView.updateUI(list);

                }
            });
        }else {

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

    }

    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.setRate(bean);
            // LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
            //RxBus.getDefault().send(RxBusCode.RATE,rate);
        }));
    }
    public void initNewSocket(){
        stockSocket1 = httpService.pushCoin();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->mView.refreshCoin(newcoinbean),Throwable::getMessage);
    }
    public void initNewSocket1(){
        stockSocket1 = httpService.pushCoin1();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean-> mView.refreshCoin(newcoinbean),Throwable::getMessage);
    }
}
