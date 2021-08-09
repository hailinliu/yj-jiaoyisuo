package com.sskj.tibi.http;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.tibi.bean.AddressBean;
import com.sskj.tibi.bean.CommissionConfigBean;
import com.sskj.tibi.bean.FinanceBean;
import com.sskj.tibi.bean.OtherRecordBean;
import com.sskj.tibi.bean.RateBean;
import com.sskj.tibi.bean.RechargeBean;
import com.sskj.tibi.bean.RechargeRecordBean;
import com.sskj.tibi.bean.SearchTypeBean;
import com.sskj.tibi.bean.WithdrawBalanceBean;
import com.sskj.tibi.bean.WithdrawRechargeCoinBean;
import com.sskj.tibi.bean.WithdrawRecordBean;

import java.util.List;
import java.util.Map;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {

    /**
     * 佣金提现限制
     *
     * @return
     */
    public GetRequest<HttpData<CommissionConfigBean>> apiUserCommissionConfig() {
        return OkGo.<HttpData<CommissionConfigBean>>get(HttpConfig.BASE_URL + HttpConfig.API_USER_COMMISSION_CONFIG)
                ;
    }

    /**
     * 充币
     *
     * @param
     * @return
     */
    public PostRequest<HttpData<RechargeBean>> recharge(int type,String code) {
        return OkGo.<HttpData<RechargeBean>>post(HttpConfig.BASE_URL + HttpConfig.RECHARGE)
                .params("type", type)
                .params("code", code)
                ;
    }
    //获取划转资产这块儿
    public GetRequest<HttpData<MyAssertBean>> getAsserts(String id, String type) {
        return OkGo.<HttpData<MyAssertBean>>get(com.sskj.mine.http.HttpConfig.BASE_URL + com.sskj.mine.http.HttpConfig.GETASSERTS)
                .params("id", id)
                .params("baseCode", type);
    }
    /**
     * 充提币币种列表
     *
     * @return
     */
    public GetRequest<HttpData<List<WithdrawRechargeCoinBean>>> coinList() {
        return OkGo.<HttpData<List<WithdrawRechargeCoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.COIN_LIST)
                ;
    }

    public GetRequest<HttpData<PageBean<RechargeRecordBean>>> getRechargeRecord(String page, String code) {
        return OkGo.<HttpData<PageBean<RechargeRecordBean>>>get(HttpConfig.BASE_URL + HttpConfig.RECHARGE_RECORD)
                .params("type", "1")
                .params("pageNumber", page)
                .params("pageSize", 10)
                .params("stockCode", code)
                ;
    }

    public PostRequest<HttpData> addAddress(String type, String address, String notes) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.ADD_ADDRESS)
                .params("unit", type)
                .params("address", address)
                .params("remark", notes)
                ;
    }

    public GetRequest<HttpData<PageBean<WithdrawRecordBean>>> getWithdrawRecord(String page, String code) {
        return OkGo.<HttpData<PageBean<WithdrawRecordBean>>>get(HttpConfig.BASE_URL + HttpConfig.WITHDRAW_RECORD)
                .params("pageNumber", page)
                .params("type", "2")
                .params("pageSize", 10)
                .params("stockCode", code)
                ;
    }

    public PostRequest<HttpData<WithdrawBalanceBean>> getBalance(String code) {
        return OkGo.<HttpData<WithdrawBalanceBean>>post(HttpConfig.BASE_URL + HttpConfig.WITHDRAW_BALANCE)
                .params("code", code)
                ;
    }

    public GetRequest<HttpData<Map<String, List<AddressBean>>>> getAddressList() {
        return OkGo.<HttpData<Map<String, List<AddressBean>>>>get(HttpConfig.BASE_URL + HttpConfig.ADDRESS_LIST)

                ;
    }

    public PostRequest<HttpData> withdraw(String address, String money, String stockCode,String payment_password, String code) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.WITHDRAW)
                .params("walletAddr", address)
                .params("usdFee", money)
                .params("stockCode", stockCode)
                .params("coinType", "2")
                .params("tradePwd", payment_password, true)
                .params("code", code)
                ;
    }

    public GetRequest<HttpData<PageBean<OtherRecordBean>>> getOtherRecord(String page, String code) {
        return OkGo.<HttpData<PageBean<OtherRecordBean>>>get(HttpConfig.BASE_URL + HttpConfig.OTHER_RECORD)
                .params("code", code)
                .params("pageNumber", page)
                .params("pageSize", 20)

                ;
    }

    public GetRequest<HttpData<SearchTypeBean>> getSearchType() {
        return OkGo.<HttpData<SearchTypeBean>>get(HttpConfig.BASE_URL + HttpConfig.SEARCH_TYPE);

    }

    public PostRequest<HttpData> deleteAddress(String id) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.DELETE_ADDRESS)
                .params("id", id)
                ;
    }

    /**
     * 资产信息
     *
     * @return
     */
    public GetRequest<HttpData<FinanceBean>> apiUserAssetinfo() {
        return OkGo.<HttpData<FinanceBean>>get(HttpConfig.BASE_URL + HttpConfig.API_USER_ASSETINFO)
                ;
    }

    /**
     * 用户在线充值
     *
     * @param money 充值的USDT金额 200-200000
     * @return
     */
    public PostRequest<HttpData<String>> apiRechargeOnlinerecharge(String money) {
        return OkGo.<HttpData<String>>post(HttpConfig.BASE_URL + HttpConfig.API_RECHARGE_ONLINERECHARGE)
                .params("money", money)
                ;
    }

    /**
     * 获取汇率
     *
     * @return
     */
    public GetRequest<HttpData<RateBean>> apiGetrate() {
        return OkGo.<HttpData<RateBean>>get(HttpConfig.BASE_URL + HttpConfig.API_GETRATE)
                ;
    }

    public PostRequest<HttpData> withdrawCommission(String money, String payment_password) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.WITHDRAW_COMMISSION)
                .params("money", money)
                .params("payment_password", payment_password, true)
                ;

    }

    public PostRequest<HttpData> cancelWithdraw(String id) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.CANCEL_WITHDRAW)
                .params("id", id)
                ;
    }
}