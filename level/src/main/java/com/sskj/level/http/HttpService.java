package com.sskj.level.http;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.util.SPUtil;
import com.sskj.level.bean.CancelBean;
import com.sskj.level.bean.ContentBean;
import com.sskj.level.bean.ContractNoteBean;
import com.sskj.level.bean.DealLevelBean;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.HoldLevelHistoryBean;
import com.sskj.level.bean.ImageBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.bean.ProductDataBean;
import com.sskj.level.bean.RecordDetailBean;
import com.sskj.level.bean.SpreadBean;
import com.sskj.level.bean.TradeInfoBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.bean.WSFiveBean;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;

import okhttp3.WebSocket;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {


    public WebSocketObserver pushCoinFiveDeep(String code) {
        return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                if (msg == null) {
                    webSocket.send("{\"type\":\"depth_list\",\"list\":[\"" + code + "\"]}");
                } else {
                    webSocket.send(msg);
                }
            }
        };
    }

    /**
     * 首页行情列表
     *
     * @param code 标识符
     * @return
     */
    public GetRequest<HttpData<List<CoinBean>>> getPro(String code) {
        return OkGo.<HttpData<List<CoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PRO)
                .params("code", CommonUtil.dealReuqestCode(code))
                ;
    }
    public GetRequest<HttpData<List<CoinBean>>> getProduct(String qu, String code) {


        return OkGo.<HttpData<List<CoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.PRODUCT_LIST)
                .params("code", code == null ? qu : CommonUtil.dealReuqestCode(code))
                .params("type", "3")
                ;
    }
    public GetRequest<HttpData<ProductDataBean>> getProductData(String code) {
        return OkGo.<HttpData<ProductDataBean>>get(HttpConfig.BASE_URL + HttpConfig.PRODUCT_DATA)
                .params("stockCode", CommonUtil.dealReuqestCode(code));
    }
    public PostRequest<HttpData<RecordDetailBean>> getRecordDetail(String id, String tradeType) {
        return OkGo.<HttpData<RecordDetailBean>>post(HttpConfig.BASE_URL + HttpConfig.RECORD_DETAIL)
                .params("id", id)
                .params("tradeType", tradeType)
                ;

    }

    /**
     * 盘口
     *
     * @param code 币种标识符
     * @return
     */
/*    public GetRequest<HttpData<WSFiveBean>> getPankou(String code) {
        return OkGo.<HttpData<WSFiveBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code));

    }
    public GetRequest<String> getPankou1(String code) {
        return OkGo.<String>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code));

    }*/
    public GetRequest<String> getProduct1(String code) {
        return OkGo.<String>get(com.sskj.hangqing.http.HttpConfig.BASE_URL + "/level-market/symbol-thumb");
        //.params("type", 3);
    }
    public GetRequest<BaseBean> getRate(String fromUnit, String toUnit){
        return OkGo.<BaseBean>get(HttpConfig.BASE_URL+HttpConfig.GET_RATE+fromUnit+"-"+toUnit);
    }
    public GetRequest<HttpData<TradeInfoBean>> getTradeInfo() {
        return OkGo.<HttpData<TradeInfoBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_TRADE_INFO)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                ;
    }
    public MyWebSocketServer pushCoin1() {
        MyWebSocketServer socketServer = new MyWebSocketServer("wss://www.bitflnex.pro/market/market-ws/websocket","/topic/level/thumb");
        socketServer.connect();
        return socketServer;
    }
    /**
     * 一键平仓
     *
     * @return
     */
    public PostRequest<HttpData> levelAllPc(String stockCode) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.LEVEL_ALL_PC)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("stockCode", stockCode)
                ;
    }


    public PostRequest<HoldLevelHistoryBean> getChiCang(String pageNo, String pageSize, String symbol) {
        return OkGo.<HoldLevelHistoryBean>post(HttpConfig.BASE_URL + "/level/order/currentPosition")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
              //  .params("symbol",symbol)
                ;
    }

    /**
     * 平仓
     *
     * @param orderId
     * @return
     */
    public PostRequest<HttpData> pingCang(String orderId,String num) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + "/level/order/close")
                .params("orderId", orderId)
                .params("closeAmount", num)
                ;
    }


    public PostRequest<LevelHistoryBean> getWeituo(String symbol,String pageNo,String pageSize) {
        return OkGo.<LevelHistoryBean>post(HttpConfig.BASE_URL + "/level/order/current")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
               // .params("symbol",symbol)
                ;
    }

    public PostRequest<DealLevelBean> getChengjiao(String pageNo, String pageSize, String symbol) {
        return OkGo.<DealLevelBean>post(HttpConfig.BASE_URL + "/level/order/close-page")
                .params("pageNo", pageNo )
                .params("pageSize", pageSize)
               // .params("symbol", symbol)
                ;
    }
    public PostRequest<CancelBean> getCancel(String pageNo, String pageSize, String symbol, String status){
        return OkGo.<CancelBean>post(HttpConfig.BASE_URL+"/level//order/query-page")
                .params("pageNo", pageNo )
                .params("pageSize", pageSize)
               // .params("symbol", symbol)
                .params("status",status);
    }

    public PostRequest<HttpData> setStop(String orderId, String stopProfitPrice, String stopLossPrice) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + "/level//order/setPL")
                .params("orderId", orderId)
                .params("profit", stopProfitPrice)
                .params("loss", stopLossPrice)
                ;
    }

    /**
     * 撤单
     *
     * @param orderId
     * @return
     */
    public PostRequest<HttpData> cancelOrder(String orderId) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.CANCEL_ORDER)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("orderId", orderId)
                ;
    }


    public WebSocketObserver pushCoinFive(String code) {
        return new WebSocketObserver(HttpConfig.WS_BASE_URL) {

            public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                if (msg == null) {
                    webSocket.send("{\"type\":\"buy_sell_five_lever\",\"list\":[\"" + code + "\"]}");
                } else {
                    webSocket.send(msg);
                }
            }
        };
    }


    /**
     * 下单
     */
    public PostRequest<HttpData> createOrder(String entrustLot, String buyPrice, String buyBillType, String stockCode, String billPriceType, String stopProPrice, String stopLossPrice, String lever) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.CREATE_ORDER)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("buyPrice", buyPrice)
                .params("billPriceType", billPriceType)
                .params("buyBillType", buyBillType)
                .params("entrustLot", entrustLot)
                .params("stopProPrice", stopProPrice)
                .params("stopLossPrice", stopLossPrice)
                .params("stockCode", CommonUtil.dealReuqestCode(stockCode))
                .params("lever", lever)
                ;
    }


    public GetRequest<HttpData<SpreadBean>> apiUserRegisterlink() {
        return OkGo.<HttpData<SpreadBean>>get(HttpConfig.BASE_URL + HttpConfig.API_USER_REGISTERLINK)
                ;
    }

    public GetRequest<String> getImage(String type) {
        return OkGo.<String>get(HttpConfig.BASE_URL + HttpConfig.GET_IMAGE)
                .params("type", type);
    }
}