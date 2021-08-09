package com.sskj.hangqing.presenter;

import android.util.Log;

import com.facebook.stetho.common.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.SoloBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.activity.SoloActivity;
import com.sskj.lib.bean.CoinBean1;

import java.util.List;

public class SoloActivityPresenter extends BasePresenter<SoloActivity> {
    public void getSolo(String symbol){
        OkGo.<String>post(HttpConfig.BASE_URL+"/market/symbol-thumb").
                params("symbol",symbol).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
               // LogUtil.e(response.body(),new Throwable().getCause());
               // LogUtil.e(response.body());
                Gson gson = new Gson();
                List<SoloBean> list= gson.fromJson(response.body(), new TypeToken<List<SoloBean>>() {
                }.getType());
                mView.setSata(list);
              // SoloBean bean = GSonUtil.GsonToBean(response.body(), List<SoloBean>);

            }
        });

    }
}
