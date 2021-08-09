package com.sskj.fabi.http;


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
    /**
     * 分页查询我的订单
     */
    public static final String FABI_ORDER = "/otc/order/self";

    /**
     * 法币列表
     */
    public static final String FABI_ALL_COIN = "/otc/coin/all";
    /**
     * 法币大厅买卖数据
     */
    public static final String FABI_BUY_SELL = "/otc/advertise/page-by-unit";
    /**
     * 法币大厅下单
     */
    public static final String FABI_CREATE_ORDER = "/app/lawCoin/lawCoinDeal";
    /**
     * 支付方式列表
     */
    public static final String FABI_PAY_WAY_LIST = "/app/lawCoin/payList";
    /**
     * 商家发单
     */
    public static final String FABI_PUBLISH = "/app/lawCoin/addBill";
    /**
     * 商家撤销发布的订单
     */
    public static final String FABI_PUBLISH_ORDER_CANCEL = "/app/lawCoin/cancelLawCoinDealBill";
    /**
     * 商家发单列表
     */
    public static final String FABI_PUBLISH_RECORD = "/app/lawCoin/sellBuyRecord";
    /**
     * 商家发单默认单价
     */
    public static final String FABI_PUBLISH_PRICELIMIT = "/app/lawCoin/showPrice";
    /**
     * 商家发单限额
     */
    public static final String FABI_PUBLISH_LIMIT_NUM = "/app/lawCoin/fbMinNum";

    /**
     * 求购/售出 列表
     */
    public static final String FABI_ORDER_RECORD = "/app/lawCoin/selBuySellInfo";
    /**
     * 法币订单详情
     */
    public static final String FABI_ORDER_RECORD_INFO = "/app/lawCoin/orderDetail";
    /**
     * 标记已付款
     */
    public static final String FABI_ORDER_MARK_PAY = "/app/lawCoin/sellBuyConfirme";
    /**
     * 法币取消订单
     */
    public static final String FABI_ORDER_CANCLE = "/app/lawCoin/cancelLawCoinRecord";
    /**
     * 账单申诉
     */
    public static final String FABI_ORDER_ALLEGE = "/app/lawCoin/appealLawCoinRecord";
    /**
     * 确认放行
     */
    public static final String FABI_ORDER_LET_GO = "/app/lawCoin/sellBuyConfirme";

    public static final String FABI_QUICK_BUY = "/app/usdt/usdtpay";
    public static final String POSITION_NUM = "/app/leverDeal/getPositionCount";
    public static final String FABILIST="/otc/coin/all";
}