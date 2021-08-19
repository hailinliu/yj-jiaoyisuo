package com.sskj.bibi.http;


import com.sskj.lib.BaseHttpConfig;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：网络接口地址
 */
public class HttpConfig {
    public static final int OK = BaseHttpConfig.OK;
    public static final String BASE_URL = BaseHttpConfig.BASE_URL;
    public static final String BASE_URL1 = BaseHttpConfig.BASE_URL1;
    public static final String WS_BASE_URL = BaseHttpConfig.WS_BASE_URL;
    public static final String WS_BASE_URL1 = "wss://www.bitflnex.pro/websocket1123123";
    public static final String COIN_ORDER_CANCEL = "/app/coincoin/cancelBill";
    public static final String PRODUCT_LIST1 = "/app/kline/goodInfo";
    public static final String PRODUCT_LIST = "/market/history";
    public static final String CURRENCYLIST = "/app/coincoin/stockPair";
    public static final String COIN_ENTRUST_LIST_HISTORY = "/app/coincoin/selCoinCoinByHistory";
    public static final String COIN_ENTRUST_LIST_ALL = "/app/coincoin/selCoinCoin";
    public static final String COIN_FEE = "/app/coincoin/getFount";
    public static final String CREATE_COIN_ORDER = "/app/coincoin/addBill";
    public static final String GET_PANKOU = "/market/exchange-plate-mini";
    public static final String GET_DEEP = "/app/kline/depth";

    /**
     * 获取汇率
     */
    public static final String GET_RATE="/market/exchange-rate/";
    public static final String RECORD_DETAIL = "/app/coincoin/successDealInfo";

    public static final String CODO_HORIZATION_LIST = "/app/kline/getSeries";
    public static final String NO_TIPS = "/app/user/noRemind";
}