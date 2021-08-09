package com.sskj.tibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.tibi.bean.CoinDetailBean;
import com.sskj.tibi.bean.WithdrawBalanceBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.WithdrawActivity;


public class WithdrawActivityPresenter extends BasePresenter<WithdrawActivity> {
    public void getCoin(String charge){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/rechargeCoin")
                .params("charge",charge)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CoinListBean bean = GSonUtil.GsonToBean(response.body(),CoinListBean.class);
                        mView.setData(bean.getData());
                    }
                });
    }
    public void getwallet(String balance,String type){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/wallet")
                .params("balance",balance)
                .params("name",type)
                .execute(new StringCallback() {
                             @Override
                             public void onSuccess(Response<String> response) {
                                 CoinDetailBean bean =GSonUtil.GsonToBean(response.body(), CoinDetailBean.class);
                                 mView.updateData(bean);
                             }
                         }
                );
    }
    public void getTodayCoinDetail(String symbol){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/withdraw/todayWithdrawSum")
                .params("symbol",symbol)
                .execute(new CallBackOption<HttpData>() {
                         }.loadBind(mView).execute(httpData -> {
                          mView.getToday(httpData.getData().toString());
                        })
                );
    }
    public void getEmailCode(){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/email/withdraw/email/code")
                .execute(new CallBackOption<HttpData>() {
                        }.loadBind(mView).execute(httpData -> {
                            mView.setP(httpData.getMsg());
                           // ToastUtil.showShort(httpData.getMsg());
                           // mView.getEmailCode(httpData.getData().toString());
                        })
                );
    }
    public void getPhoneCode(){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/mobile/withdraw/code")
                .execute(new CallBackOption<HttpData>() {
                        }.loadBind(mView).execute(httpData -> {
                        mView.setP(httpData.getMsg());
                            //ToastUtil.showShort(httpData.getMsg());
                            // mView.getEmailCode(httpData.getData().toString());
                        })
                );
    }
    public void submit(String address,String amount,String unit,String code,String jyPassword){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/withdraw/apply")
                .params("address",address)
                .params("amount",amount)
                .params("unit",unit)
                .params("code",code)
                .params("jyPassword",jyPassword,true)
                .execute(/*new StringCallback() {
                             @Override
                             public void onSuccess(Response<String> response) {
                                 mView.setUpdate();
                                 //response.body();
                              *//*   CoinDetailBean bean =GSonUtil.GsonToBean(response.body(), CoinDetailBean.class);
                                 mView.updateData(bean);*//*
                             }
                         }*/
                        new CallBackOption<HttpData>() {
                        }.loadBind(mView).execute(httpData -> {
                            mView.setUpdate(httpData.getMsg());
                        })
                );
    }
}
