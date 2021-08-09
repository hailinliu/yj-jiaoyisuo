package com.sskj.mine.presenter;


import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.common.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.ILoadBind;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.bean.LevelBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.TransferActivity;

import java.math.BigDecimal;

public class TransferActivityPresenter extends BasePresenter<TransferActivity> {

    public void getBiBi(String balance,String type,boolean b){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/wallet")
                .params("balance",balance)
                .params("name",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null) {
                            BIBIBean bean = GSonUtil.GsonToBean(response.body(), BIBIBean.class);
                            if (bean.getCode() == 4000) {
                                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                logoutProvider.logout();
                                LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                            } else if (bean.getCode() == 0) {
                                mView.setBIBI(bean.getData(), b);
                            }
                        }
                    }
                });
    }
    public void getFaBi(String balance,String name,boolean b){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/otc/wallet/get")
                .params("balance",balance)
                .params("name",name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            FaBiBean bean = GSonUtil.GsonToBean(response.body(), FaBiBean.class);
                            if(bean.getCode()==4000){
                                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                logoutProvider.logout();
                                LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                            }else if(bean.getCode()==0){
                                mView.setFaBi(bean.getData(),b);
                            }

                        }

                        // mView.setBIBI(bean.getData());
                    }

                });
    }
    public void getLevel(String balance,String name){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/level-wallet")
                .params("balance",balance)
                .params("name",name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            LevelBean bean = GSonUtil.GsonToBean(response.body(), LevelBean.class);
                            if(bean.getCode()==4000){
                                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                logoutProvider.logout();
                                LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                            }else if(bean.getCode()==0){
                                mView.setLevel(bean.getData());
                            }
                        }


                        // mView.setBIBI(bean.getData());
                    }

                });

    }
    /*public void getCoin(String charge){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/rechargeCoin")
                .params("charge",charge)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CoinListBean bean = GSonUtil.GsonToBean(response.body(),CoinListBean.class);
                        mView.setData(bean.getData());
                    }
                });
    }*/
    public void submit(String amount,String coinName,String direction){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/otc/wallet/transfer")
                .params("amount",amount)
                .params("coinName",coinName)
                .params("direction",direction)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> mView.update(httpData.getMsg())));

    }
}
