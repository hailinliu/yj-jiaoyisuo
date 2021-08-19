package com.sskj.bibi.presenter;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.bibi.bean.CoinDetailBean;
import com.sskj.bibi.bean.CoinFee;
import com.sskj.bibi.bean.CoinUpdateBean;
import com.sskj.bibi.bean.DealDetailBean;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.BibiBuyAndSellFragment;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.AbsConverter;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.http.JsonConverter;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.ExceptionUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BibiBuyAndSellFragmentPresenter extends BasePresenter<BibiBuyAndSellFragment> {

public void submit(String price,String amount,String direction,String symbol,String type){
OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/exchange/order/add")
        .params("price",price)
        .params("amount",amount)
        .params("direction",direction)
        .params("symbol",symbol)
        .params("type",type)
        .execute(new CallBackOption<BaseBean<String>>() {
        }.loadBind(mView).execute(httpdata->{
            mView.success(httpdata.getMessage());

        }));
}
    public void getPankou(String code) {
        httpService.getPankou(code)
                .tag(this)
                .execute(new StringCallback(){

                    @Override
                    public void onSuccess(Response<String> response) {
                        if(!TextUtils.isEmpty(response.body())){
                            WSFiveBean1 httpData = GSonUtil.GsonToBean(response.body(),WSFiveBean1.class);
                            if (httpData!= null ) {
                                WSFiveBean1 wsFiveBean = httpData;
                                mView.updateFive(wsFiveBean);
                            } else {
                                WSFiveBean1 wsFiveBean = new WSFiveBean1();
                                //  wsFiveBean.setCode(code);
                                wsFiveBean.setAsk(new WSFiveBean1.AskBean());
                                wsFiveBean.setBid(new WSFiveBean1.BidBean());

                                mView.updateFive(wsFiveBean);
                            }
                        }

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
                                 if(!TextUtils.isEmpty(response.body())){
                                  CoinDetailBean bean =GSonUtil.GsonToBean(response.body(), CoinDetailBean.class);
                                 if(bean!=null&&bean.getCode()==4000){
                                     mView.setdata();
                                    /* LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                     logoutProvider.logout();
                                     LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);*/
                                 }
                                if(bean!=null&&bean.getCode()==0){
                                    mView.updateData(bean);
                                }

                             }}
                         }
                        );
    }
public void getDealDetail(String symbol){
    OkGo.<String>post(HttpConfig.BASE_URL+"/market/symbol-info")
            .params("symbol",symbol)
            .execute(new StringCallback() {
                         @Override
                         public void onSuccess(Response<String> response) {
                             if(!TextUtils.isEmpty(response.body())){
                             DealDetailBean bean =GSonUtil.GsonToBean(response.body(), DealDetailBean.class);
                             mView.getDealDetail(bean);

                         }}
                     }
                );
}
   /* @Override
    public void detachView() {
        DisposUtil.close(disposable);
        super.detachView();
    }*/
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
}
