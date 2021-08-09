package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.mine.R;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.DataBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.bean.TotalMoneyBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.fragment.MineFragment;


/**
 * @author Hey
 * Create at  2019/05/01
 */
public class MineFragmentPresenter extends BasePresenter<MineFragment> {
    public void getData(String id,int type){
        httpService.getAsserts(id,type).execute(new CallBackOption<HttpData<MyAssertBean>>() {

        }.loadBind(mView).execute(httpData -> {
            if(httpData!=null&&httpData.getData()!=null){
                mView.getAssert(httpData.getData());
            }else {
                mView.setNodata();
            }


        }));
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
    public void getwallet(String balance,String type){
        OkGo.<BaseBean<TotalMoneyBean>>post(HttpConfig.BASE_URL+"/uc/asset/total")
                .params("balance",balance)
                .params("name",type)
                .execute(/*new StringCallback() {
                             @Override
                             public void onSuccess(Response<String> response) {
                                 TotalMoneyBean bean = GSonUtil.GsonToBean(response.body(), TotalMoneyBean.class);
                                mView.setData(bean);
                                //bean.getTotalMoney()
                                // response.body();
                            *//*     CoinDetailBean bean = GSonUtil.GsonToBean(response.body(), CoinDetailBean.class);
                                 mView.updateData(bean);*//*
                             }
                         }*/
                        new CallBackOption<BaseBean<TotalMoneyBean>>() {
        }.loadBind(mView).execute(httpdata->{
            if(httpdata!=null&&httpdata.getData()!=null){
                mView.setData(httpdata.getData());
            }

           // mView.setUI(httpdata.getMessage());

        }));
    }


    public void getContract() {
        httpService.getContract()
                .execute(new CallBackOption<HttpData<String>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.showEmail(httpData.getData());
                            }
                        }));
    }

    /**
     * 检测版本
     */
    public void checkVersion() {
        httpService.checkVersion()
                .execute(new CallBackOption<HttpData<AppVersionBean>>() {
                    @Override
                    public void onError(Response<HttpData<AppVersionBean>> response) {
                        super.onError(response);
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                    }
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK && httpData.getData() != null) {
                                String verName = APKVersionCodeUtils.getVerName(App.INSTANCE);
                                if (APKVersionCodeUtils.compareVersion(httpData.getData().getVersion(), verName) == 1) {
                                    mView.onVersionDataSuccess(httpData.getData());
                                } else {
                                    ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                                }
                            } else {
                                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                            }
                        }));
    }

}
