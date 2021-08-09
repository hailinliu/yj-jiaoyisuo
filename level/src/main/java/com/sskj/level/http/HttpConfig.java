package com.sskj.level.http;


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
    public static final String BASE_IMG_URL = BaseHttpConfig.BASE_IMG_URL;
    public static final String WS_BASE_URL = BaseHttpConfig.WS_BASE_URL;

    public static final String PRODUCT_LIST = "/market/history";
    /**
     * 首页行情列表
     */
    public static final String GET_PRO = "/app/kline/goodInfo";
    public static final String RECORD_DETAIL = "/app/coincoin/successDealInfo";
    /**
     * 配置信息
     */
    public static final String PRODUCT_DATA = "/app/leverDeal/getLeverDealSysSet";
    /**
     * 盘口
     */
    public static final String GET_PANKOU = "/level-market/exchange-plate-mini";
    /**
     * 获取汇率
     */
    public static final String GET_RATE="/market/exchange-rate/";
    /**
     * 合约顶部信息
     */
    public static final String GET_TRADE_INFO = "/app/leverDeal/getCapitalInfo";

    /**
     * 一剑平仓
     */
    public static final String LEVEL_ALL_PC = "/app/leverDeal/closePositionAll";

    /**
     * 持仓单
     */
    public static final String CHICANG_LIST = "/app/leverDeal/getPositionList";

    /**
     * 委托单
     */
    public static final String WEITUO = "/app/leverDeal/getLimitPriceBillList";

    /**
     * 成交单
     */
    public static final String CHENGJIAO = "/app/leverDeal/getBillHistory";

    /**
     * 平仓
     */
    public static final String PINGCANG = "/app/leverDeal/closePosition";

    /**
     * 设置止盈止损
     */
    public static final String SET_POIT = "/app/leverDeal/setStopProfitPrice";

    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = "/app/leverDeal/cancelBill";

    /**
     * 创建订单
     */
    public static final String CREATE_ORDER = "/app/leverDeal/addBill";

    /**
     * 分享页
     */
    public static final String API_USER_REGISTERLINK = "/api/user/registerLink";
    /**
     * 深度数据
     */
    public static final String GET_DEEP = "/app/kline/depth";

    //分享图片
    public static final String GET_IMAGE = "/app/leverDeal/share";

    //获取推广二维码
    public static final String  GETERWEIMA="/app/user/getQrcode";
}