package com.sskj.hangqing.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.CoinListFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CoinImgUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;


public class CoinFragmentPresenter extends BasePresenter<CoinListFragment> {
    private MyWebSocketServer stockSocket1;
    /**
     * 行情数据
     *
     * @return
     */
    public void getProduct() {
        httpService.getProduct2(null).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(!TextUtils.isEmpty(response.body())){
                    Gson gson = new Gson();
                    List<CoinBean1> list = gson.fromJson(response.body(), new TypeToken<List<CoinBean1>>() {
                    }.getType());
                    mView.updateUI(list);
                }

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
            mView.setUI(bean);
           // LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
            //RxBus.getDefault().send(RxBusCode.RATE,rate);
        }));
    }
    public void initNewSocket(){

        stockSocket1 = httpService.pushCoin();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->mView.refreshCoin(newcoinbean),Throwable::getMessage);
    }
    public void closeSocket(){
        if(stockSocket1!=null){
            stockSocket1.disconnectStomp();
            stockSocket1 =null;
        }

    }
}