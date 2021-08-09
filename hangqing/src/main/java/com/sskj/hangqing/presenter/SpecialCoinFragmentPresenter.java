package com.sskj.hangqing.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.ui.fragment.SpecialCoinFragment;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;


public class SpecialCoinFragmentPresenter extends BasePresenter<SpecialCoinFragment> {
    private MyWebSocketServer stockSocket1;
    public void getData() {
        httpService.getProduct2(null).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Gson gson = new Gson();
               List<CoinBean1> list = gson.fromJson(response.body(),new TypeToken<List<CoinBean1>>(){}.getType());
               mView.updateCoin(list);
            }
        });

    }
    public void initNewSocket(){

        stockSocket1 = httpService.pushCoin();
        stockSocket1.map(s->new Gson().fromJson(s, CoinBean1.class))
                .subscribe(newcoinbean->mView.refreshCoin(newcoinbean),Throwable::getMessage);
    }
}
