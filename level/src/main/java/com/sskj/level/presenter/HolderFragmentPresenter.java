package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.bean.TurnoverBean;
import com.sskj.level.bean.ContentBean;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.HoldLevelHistoryBean;
import com.sskj.level.bean.HoldeContentBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.bean.ProductDataBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.HolderFragment;
import com.sskj.level.util.JsonConver2;
import com.sskj.level.util.JsonConver3;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;
import com.sskj.lib.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class HolderFragmentPresenter extends BasePresenter<HolderFragment> {

    private StompClient mStompClient;

    public void getAllList() {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/level/order/closeAll")
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> mView.closeCode(httpData.getMsg())));

    }
    public void getTradeList(String pageNo, String pageSize,String symbol) {
        OkGo.<String>post(HttpConfig.BASE_URL+"/level/order/currentPosition")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("symbol",symbol)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        response.body();
                    }
                });
      /*  httpService.getChiCang(page, pageSize, stockCode)
                .execute(new CallBackOption<HttpData<PageBean<TradeListBean>>>() {
                    @Override
                    public void onError(Response<HttpData<PageBean<TradeListBean>>> response) {
                        super.onError(response);
                        mView.updateUI(new ArrayList<>());
                    }
                }
                        .unLoadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.updateUI(httpData.getData().getList());
                            }

                        }));*/
    }

    public Flowable<List<HoldLevelHistoryBean.ContentBean>> getTradeListFlow(String page, String pageSize, String stockCode) {
        return httpService.getChiCang(page, pageSize, stockCode)
                .converter(new JsonConver3<HoldLevelHistoryBean>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getContent())
                .onErrorReturn(throwable -> new ArrayList<>())
                ;

    }


    public void pingCang(String orderId, String num) {
        httpService.pingCang(orderId, num)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.pingCangSuccess();
                            }
                        }));
    }

    public void setStop(String orderId, String stopProfitPrice, String stopLossPrice) {
        httpService.setStop(orderId, stopProfitPrice, stopLossPrice)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.refresh();
                            }
                        }));
    }
    public void initSocket(String code) {
        if (code==null)
            return;
       // code= CommonUtil.dealReuqestCode(code);
        String id = SPUtil.get(SPConfig.ID,"");
        String url = "/topic/level/profitLoss/"+id;
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.bitflnex.pro/market/market-ws/websocket");
        mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
            lifecycleEvent.getType();
        });
        mStompClient.topic(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((StompMessage topicMessage)->{
                    HoldeContentBean bean =  GSonUtil.GsonToBean(topicMessage.getPayload(), HoldeContentBean.class);
                   mView.setUI(bean);
                /*    List<TurnoverBean> list = GSonUtil.jsonToList(topicMessage.getPayload(),TurnoverBean.class);
                    mView.updata(list);*/
                },throwable -> {
                    LogUtil.e("链接错误",throwable);
                });
        mStompClient.connect();
        /*if (stockSocketFive == null) {
            stockSocketFive = httpService.pushCoinFive(code);
            stockDisposableFive = stockSocketFive
                    .map(s -> new Gson().fromJson(s, WSFiveBean1.class))
                    .subscribe(data -> mView.updateFive(data));
        }*//* else {
            stockSocketFive.connect();
        }*/

    }

    @Override
    public void detachView() {
        closeWebSocket();
        super.detachView();
    }

    public void closeWebSocket(){
            if(mStompClient!=null){
                mStompClient.disconnect();
                mStompClient =null;
            }
    }
/*    public void getSet(String stockCode) {
        httpService.getProductData(stockCode)
                .execute(new CallBackOption<HttpData<ProductDataBean>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                String minLossPrice = httpData.getData().getMinStopLoss();
                                String minWinPrice = httpData.getData().getMinStopProfit();
                                mView.setStop(minWinPrice, minLossPrice);
                            }
                        }));
    }*/
}
