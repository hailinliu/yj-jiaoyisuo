package com.sskj.tibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.tibi.bean.CoinAddressBean;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.NewRechargeActivity;

public class NewRechargeActivityPresenter extends BasePresenter<NewRechargeActivity> {
    public void getCoinAdress(String unit){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/wallet/create-address")
                .params("unit",unit)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CoinAddressBean bean = GSonUtil.GsonToBean(response.body(), CoinAddressBean.class);

                            mView.setAddress(bean.getData());


                    }
                });
    }
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
    public void getRecord(String symbol,String pageNo,String pageSize){
        OkGo.<String>get(HttpConfig.BASE_URL+"/uc/asset/depositByPage")
                .params("symbol",symbol)
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CongRecordBean bean = GSonUtil.GsonToBean(response.body(), CongRecordBean.class);
                        mView.setRecord(bean);
                        //response.body();
                      /*  CoinListBean bean = GSonUtil.GsonToBean(response.body(),CoinListBean.class);
                        mView.setData(bean.getData());*/
                }

                });
    }
}

