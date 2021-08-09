package com.sskj.bibi.presenter;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.bibi.bean.EntrustBean;
import com.sskj.bibi.bean.HistoryBean;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.CurrentEntrustFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;

import java.util.ArrayList;


public class CurrentEntrustFragmentPresenter extends BasePresenter<CurrentEntrustFragment> {

public void getEntrustListRefresh(String pageNo,String pageSize,String symbol){
    OkGo.<String>post(HttpConfig.BASE_URL+"/exchange/order/current")
            .params("pageNo",pageNo)
            .params("pageSize",pageSize)
            .params("symbol",symbol)
            .execute(/*new CallBackOption<String>() {
            }.loadBind(mView).execute(httpdata->{
                ToastUtil.showShort(httpdata);
            })*/new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    HistoryBean bean = GSonUtil.GsonToBean(response.body(), HistoryBean.class);
                    mView.updateUI(bean.getContent());


                }
            });
}
   /* public void getEntrustListRefresh(String code) {
        if (TextUtils.isEmpty(SPUtil.get(SPConfig.ID, ""))) {
            mView.updateUI(new ArrayList<>());
            return;
        }
        httpService.getRecordAll("1", null, code, "1", null)
                .tag(this)
                .execute(new JsonCallBack<HttpData<PageBean<EntrustBean>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<PageBean<EntrustBean>>> response) {
                        HttpData<PageBean<EntrustBean>> httpData = response.body();
                        if (httpData.getStatus() == HttpConfig.OK) {
                            mView.updateUI(httpData.getData().getList());
                        }
                    }
                });
    }*/

    public void cancelEntrust(String orderId) {
        OkGo.<BaseBean>post(HttpConfig.BASE_URL+"/exchange/order/cancel/"+orderId).execute(new JsonCallBack<BaseBean>(this) {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                BaseBean httpData = response.body();
                ToastUtil.showShort(httpData.getMessage());
                mView.cancelReturn();
            }
        });

    }


    public void noTips() {
        OkGo.<HttpData<Object>>get(BaseHttpConfig.BASE_URL + HttpConfig.NO_TIPS)
                .params("status", "2")
                .execute(new JsonCallBack<HttpData<Object>>() {
                    @Override
                    public void onSuccess(Response<HttpData<Object>> response) {
                        mView.noTips();
                    }
                });
    }
}
