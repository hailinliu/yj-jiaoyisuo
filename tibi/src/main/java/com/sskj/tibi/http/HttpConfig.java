package com.sskj.tibi.http;


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

    public static final String RECHARGE = "/app/addr/createCoinAddr";
    public static final String COIN_LIST = "/api/resource/product";
    public static final String RECHARGE_RECORD = "/app/addr/getGoldEntryAndExitList";
    public static final String ADD_ADDRESS = "/uc/withdraw/address/add";
    public static final String WITHDRAW_RECORD = "/app/addr/getGoldEntryAndExitList";
    public static final String WITHDRAW_BALANCE = "/app/addr/getTbFee";
    public static final String ADDRESS_LIST = "/app/addr/addrList";
    public static final String WITHDRAW = "/app/addr/applyCharge";
    public static final String OTHER_RECORD = "/app/user/moneyDetail";
    public static final String SEARCH_TYPE = "/api/asset/getTypeList";
    public static final String DELETE_ADDRESS = "/app/currencyAddress/del";

    /**
     * 资产信息
     */
    public static final String API_USER_ASSETINFO= "/api/user/assetInfo";


    /**
     * 用户在线充值
     */
    public static final String API_RECHARGE_ONLINERECHARGE= "/api/recharge/onlineRecharge";


    /**
     * 获取汇率
     */
    public static final String API_GETRATE= "/api/getRate";
    public static final String API_USER_COMMISSION_CONFIG = "/api/assets/feeWithdrawInfo";


    public static final String WITHDRAW_COMMISSION = "/api/assets/feeWithdraw";


    /**
     * 资产列表
     */
    public static final String ASEET_LIST = "/app/user/captialFund";

    /**
     * 资产页面上方数据
     */
    public static final String ASEET_TOP = "/app/user/captialFundToltal";
    /**
     * 取消提币申请
     */
    public static final String CANCEL_WITHDRAW = "/app/addr/cancelCharge";
}