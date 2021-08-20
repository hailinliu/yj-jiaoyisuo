package com.sskj.level.presenter;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.CoinDetailBean;
import com.sskj.level.bean.DealDetailBean;
import com.sskj.level.bean.GangGanBean;
import com.sskj.level.bean.LevelBean;
import com.sskj.level.bean.MinNumBean;
import com.sskj.level.bean.WSFiveBean;
import com.sskj.level.bean.WSFiveBean1;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.NewBuyAndSellFragment;
import com.sskj.lib.RConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.util.CommonUtil;

public class NewBuyAndSellFragmentPresenter extends BasePresenter<NewBuyAndSellFragment> {
    public void submit(String price,String amount,String direction,String symbol,String type,String level){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/level/order/add")
                .params("price",price)
                .params("amount",amount)
                .params("direction",direction)
                .params("symbol",symbol)
                .params("level",level)
                .params("type",type)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.success(httpdata.getMessage());

                }));
    }
    public void getPankou(String code) {
        OkGo.<String>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(!TextUtils.isEmpty(response.body())){
                            WSFiveBean1  bean = GSonUtil.GsonToBean(response.body(), WSFiveBean1.class);
                            mView.updateFive(bean);
                        }

                    }
                });

    }
    public void getMinNum(String code) {
        OkGo.<String>get(HttpConfig.BASE_URL + "/level-market/symbol-info")
                .params("symbol", CommonUtil.dealReuqestCode(code))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(!TextUtils.isEmpty(response.body())){
                        MinNumBean bean = GSonUtil.GsonToBean(response.body(), MinNumBean.class);
                        mView.setUi(bean);
                    }}
                });

    }

        public void getData(String balance,String name){
            OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/level-wallet")
                    .params("balance",balance)
                    .params("name",name)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if(!TextUtils.isEmpty(response.body())) {
                                LevelBean bean = GSonUtil.GsonToBean(response.body(), LevelBean.class);
                                if (bean.getCode() == 4000) {
                                    //  ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                                } else if (bean.getData() != null) {
                                    mView.updateData(bean.getData());
                                }

                                // mView.setBIBI(bean.getData());
                            }}

                    });

        }

    public void getDealDetail(String symbol){
        OkGo.<String>post(HttpConfig.BASE_URL+"/level/symbol-info")
                .params("symbol",symbol)
                .execute(new StringCallback() {
                             @Override
                             public void onSuccess(Response<String> response) {
                                 DealDetailBean bean =GSonUtil.GsonToBean(response.body(), DealDetailBean.class);

                                 mView.getDealDetail(bean);
                                 ;
                             }
                         }
                );
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
    public void getGanggan(String symbol){
        OkGo.<HttpData<GangGanBean>>post(HttpConfig.BASE_URL+"/level/lever-coin/symbol-info")
                .params("symbol",symbol)
                .execute(new CallBackOption<HttpData<GangGanBean>>() {
                }.loadBind(mView).execute(httpdata->{
                    if(httpdata!=null){
                        mView.getGangGan(httpdata.getData());
                    }

                }));
    }
}
