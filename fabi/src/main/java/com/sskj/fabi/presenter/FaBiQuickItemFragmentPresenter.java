package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.fragment.FaBiQuickItemFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class FaBiQuickItemFragmentPresenter extends BasePresenter<FaBiQuickItemFragment> {

    public void getQuickBuy(String amount,String coinId,String mode,String money,String payMode){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/otc/order/easyBuy")
                .params("amount",amount)
                .params("mode",mode)
                .params("money",money)
                .params("coinId",coinId)
                .params("payMode",payMode)
                .execute(new CallBackOption<BaseBean<String>>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());

        }));
    }
    public void getQuickSell(String amount,String coinId,String mode,String money,String payMode){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/otc/order/easySell")
                .params("amount",amount)
                .params("mode",mode)
                .params("money",money)
                .params("coinId",coinId)
                .params("payMode",payMode)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.setUI(httpdata.getMessage());

                }));
    }
}
