package com.sskj.bibi.http;


import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.bibi.bean.CoinFee;
import com.sskj.bibi.bean.EntrustBean;
import com.sskj.bibi.bean.RecordDetailBean;
import com.sskj.bibi.bean.RecordHistoryBean;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.log.LogUtil;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.WebSocket;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {

    public GetRequest<String> getProduct2(String code) {
        return OkGo.<String>get(com.sskj.hangqing.http.HttpConfig.BASE_URL1 + com.sskj.hangqing.http.HttpConfig.GET_PRODUCT)
                ;
        //.params("type", 3);
    }

    public GetRequest<BaseBean> getRate(String fromUnit, String toUnit){
        return OkGo.<BaseBean>get(HttpConfig.BASE_URL+HttpConfig.GET_RATE+fromUnit+"-"+toUnit);
    }

    public PostRequest<HttpData> cancelCoinOrder(String orderId, String tradeType) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.COIN_ORDER_CANCEL)
                .params("orderId", orderId)
                .params("tradeType", tradeType)
                ;
    }

    public void pushCoinFive(String code) {

       /* MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/trade-plate/BTC/USDT");
        socketServer.connect();*/
       // return s;
    }

    public GetRequest<HttpData<List<CoinBean>>> getProduct(String qu, String code) {


        return OkGo.<HttpData<List<CoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.PRODUCT_LIST)
                .params("code", code == null ? qu : CommonUtil.dealReuqestCode(code))
                .params("type", "3")
                ;
    }

    public GetRequest<HttpData<WSFiveBean>> getDeepData(String code) {
        return OkGo.<HttpData<WSFiveBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_DEEP)
                .params("code", code)
                ;
    }


    public GetRequest<HttpData<Map<String, List<String>>>> getAllCoin() {
        return OkGo.<HttpData<Map<String, List<String>>>>get(HttpConfig.BASE_URL + HttpConfig.CURRENCYLIST)

                ;

    }


    public PostRequest<HttpData<PageBean<RecordHistoryBean>>> getRecordHistory(String p, String size, String code, String type) {

        return OkGo.<HttpData<PageBean<RecordHistoryBean>>>post(HttpConfig.BASE_URL + HttpConfig.COIN_ENTRUST_LIST_HISTORY)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                .params("type", type)
                .params("pageNumber", p)
                .params("pageSize", size);

    }


    public GetRequest<HttpData<CoinFee>> getCoinFee(String code) {
        return OkGo.<HttpData<CoinFee>>get(HttpConfig.BASE_URL + HttpConfig.COIN_FEE)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                .params("mark", CommonUtil.dealReuqestCode(code))
                ;
    }


    public PostRequest<HttpData> createCoinOrder(String code, String price,String touchPrice, String num, String tradeType, String billPriceType) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.CREATE_COIN_ORDER)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                .params("tradeType", tradeType)
                .params("billPriceType", billPriceType)
                .params("entrustNum", num)
                .params("entrustPrice", price)
                .params("touchPrice", touchPrice)
                ;

    }


    public GetRequest<HttpData<List<String>>> getHorization() {
        return OkGo.<HttpData<List<String>>>get(HttpConfig.BASE_URL + HttpConfig.CODO_HORIZATION_LIST);
    }

    public GetRequest<String> getPankou(String code) {
        return OkGo.<String>get(HttpConfig.BASE_URL1 + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code));

    }

    public MyWebSocketServer pushCoin() {
        MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/thumb");
        socketServer.connect();
        return socketServer;
    }
    public PostRequest<HttpData<PageBean<EntrustBean>>> getRecordAll(String p, String size, String code, String status, String type) {
        return OkGo.<HttpData<PageBean<EntrustBean>>>post(HttpConfig.BASE_URL + HttpConfig.COIN_ENTRUST_LIST_ALL)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                .params("type", type)
                .params("status", status)
                .params("pageNumber", p)
                .params("pageSize", size)
                ;
    }

    public PostRequest<HttpData<RecordDetailBean>> getRecordDetail(String id, String tradeType) {
        return OkGo.<HttpData<RecordDetailBean>>post(HttpConfig.BASE_URL + HttpConfig.RECORD_DETAIL)
                .params("id", id)
                .params("tradeType", tradeType)
                ;

    }
}