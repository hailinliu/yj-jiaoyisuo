package com.sskj.fabi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.bean.BuySellBean;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.fragment.BuySellFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


public class BuySellFragmentPresenter extends BasePresenter<BuySellFragment> {

    public Flowable<List<BuySellBean.ContextBean>> getData(String page, String pageSize,String legalCurrency,String limit,boolean isBuy,String paymode,String pType) {
        return httpService.getSellBuy(page, pageSize,legalCurrency,limit,isBuy,paymode,pType)
                .converter(new JsonConverter<HttpData<PageBean<BuySellBean.ContextBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());
    }
 /*   public Flowable<String> getData1(String page, String pageSize, boolean isBuy, String pType) {
        return httpService.getSellBuy1(page, pageSize, isBuy, pType)
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.trim());
    }*/
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
         mView.setUIBuy(httpdata.getMessage(),httpdata.getData());

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
            mView.setUISell(httpdata.getMessage(),httpdata.getData());

        }));
    }
    public void getPositionNum() {
        httpService.getPositionNum()
                .execute(new CallBackOption<HttpData<Integer>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> mView.tryTrade(httpData.getData())));
    }

   /* // 获取支付方式列表
    public void requsetPayWayList() {
        httpService.requestPayWayList()
                .execute(new CallBackOption<HttpData<List<PayWayItem>>>() {
                }
                        .unLoadBind(mView)
                        .execute(httpData -> {
                            mView.updateUi(httpData.getData());
                        }));

    }*/


}
