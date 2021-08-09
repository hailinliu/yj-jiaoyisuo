package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.fragment.BuyFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class BuyFragmentPresenter extends BasePresenter<BuyFragment> {
    public void buyCoin(String amount,String coinId,String id,String mode,String money,String payMode,String price){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/otc/order/buy")
                .params("amount",amount)
                .params("mode",mode)
                .params("money",money)
                .params("coinId",coinId)
                .params("id",id)
                .params("payMode",payMode)
                .params("price",price).execute(new CallBackOption<BaseBean<String>>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());

        }));
    }
    public void sellCoin(String amount,String coinId,String id,String mode,String money,String payMode,String price){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/otc/order/sell")
                .params("amount",amount)
                .params("mode",mode)
                .params("money",money)
                .params("coinId",coinId)
                .params("id",id)
                .params("payMode",payMode)
                .params("price",price).execute(new CallBackOption<BaseBean<String>>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());

        }));
    }
    public void getPositionNum() {
        httpService.getPositionNum()
                .execute(new CallBackOption<HttpData<Integer>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> mView.tryTrade(httpData.getData())));
    }
}
