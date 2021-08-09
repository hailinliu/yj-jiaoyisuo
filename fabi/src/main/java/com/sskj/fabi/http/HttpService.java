package com.sskj.fabi.http;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.fabi.bean.BuySellBean;
import com.sskj.fabi.bean.FabiCoinListBean;
import com.sskj.fabi.bean.NewFabiAllBean;
import com.sskj.fabi.bean.OrderBean;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.bean.OrderRecordBean;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.bean.PublishRecordBean;
import com.sskj.lib.Constans;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;

import java.util.List;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {
    public PostRequest<BaseBean<OrderBean>> getOrder1(String coinId,String advertiseType,int pageNo, int pageSize,String status,String orderSn){
        return OkGo.<BaseBean<OrderBean>>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER)
                .params("coinId",coinId)
                .params("advertiseType",advertiseType)
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("status",status)
                .params("orderSn",orderSn)
                ;
    }
    public PostRequest<BaseBean<OrderBean>> getOrder2(String coinId,String advertiseType,int pageNo, int pageSize,String status,String orderSn){
        return OkGo.<BaseBean<OrderBean>>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER)
                .params("coinId",coinId)
                .params(advertiseType,advertiseType)
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("orderSn",orderSn)
                .params("status",status);
    }
    public PostRequest<NewFabiAllBean> getAllList(){
        return OkGo.<NewFabiAllBean>post(HttpConfig.BASE_URL + HttpConfig.FABI_ALL_COIN);
    }

    public GetRequest<FabiCoinListBean> getList(){
        return OkGo.<FabiCoinListBean>get(HttpConfig.BASE_URL+HttpConfig.FABILIST);
    }
    public GetRequest<HttpData<PageBean<BuySellBean.ContextBean>>> getSellBuy(String page, String pageSize,String legalCurrency,String limit,boolean isBuy,String paymode,String pType) {
        return OkGo.<HttpData<PageBean<BuySellBean.ContextBean>>>get(HttpConfig.BASE_URL + HttpConfig.FABI_BUY_SELL)
                .params("advertiseType", isBuy ? "1" : "0")
                .params("unit", pType)
                .params("pageNo", page)
                .params("legalCurrency",legalCurrency)
                .params("limit",limit)
                .params("paymode",paymode)
                .params("pageSize", pageSize);
    }

    /**
     * @param tpwd       交易密码（MD5加密）
     * @param order_no   委托单号
     * @param total_num  type=2传
     * @param isBuy      1买 2卖
     * @param min        最小交易限额
     * @param max        最大交易限额
     * @param type       1法币计价 2购买数量
     * @param totalPrice 如果type=1传 总价
     * @param price      如果type=1传 单价
     * @return
     */
    public PostRequest<HttpData<OrderDetailBean>> order(String payType, String tpwd, String order_no, String total_num, boolean isBuy, String min, String max, String type, String totalPrice, String price) {
        return OkGo.<HttpData<OrderDetailBean>>post(HttpConfig.BASE_URL + HttpConfig.FABI_CREATE_ORDER)
                .params("id", SPUtil.get(SPConfig.ID, ""))
                .params("tradePwd", tpwd, true)
                .params("payType", payType)
                .params("buyPayType", payType)
                .params("entrustNo", order_no)
                .params("transNum", total_num)
                .params("tradeType", isBuy ? 1 : 2)
                .params("type", type)
                .params("totalPrice", totalPrice)
                .params("price", price)
                .params("minPrice", min)
                .params("maxPrice", max);

    }


    public PostRequest<HttpData> fabiPublish(boolean isBuy, String remark, String sumCount, String lowCount, String hightCount, String unitPrice, String payType, String pwd) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_PUBLISH)
                .params("maxPrice", hightCount)
                .params("minPrice", lowCount)
                .params("price", unitPrice)
                .params("transNum", sumCount)
                .params("tradeType", isBuy ? 1 : 2)
                .params("payType", payType)
                .params("remarkInfo", remark)
                .params("tradePwd", pwd, true);
    }

    public PostRequest<HttpData<PageBean<PublishRecordBean>>> getPublishRecord(String page, String pageSize, String tradeType) {
        return OkGo.<HttpData<PageBean<PublishRecordBean>>>post(HttpConfig.BASE_URL + HttpConfig.FABI_PUBLISH_RECORD)
                .params("tradeType", tradeType)
                .params("pageNumber", page)
                .params("pageSize", pageSize);
    }

    /**
     * 撤单
     */
    public PostRequest<HttpData> cancelPublishOrder(String tradeType, String id) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_PUBLISH_ORDER_CANCEL)
                .params("orderId", id)
                .params("tradeType", tradeType)
                ;
    }

    public PostRequest<HttpData<OrderDetailBean>> getOrderRecordInfo(String orderNum) {
        return OkGo.<HttpData<OrderDetailBean>>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_RECORD_INFO)
                .params("userId", SPUtil.get(SPConfig.ID, ""))
                .params("dealEntrustNo", orderNum);
    }

    public PostRequest<HttpData> markPay(int payType, String orderId) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_MARK_PAY)
//                .params("payType", payType)
                .params("orderId", orderId);
    }

    public PostRequest<HttpData> cancelOrder(String orderNum) {

        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_CANCLE)
                .params("orderId", orderNum);
    }

    public PostRequest<HttpData> allegeOrder(String orderId, String remarkInfo) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_ALLEGE)
                .params("orderId", orderId)
                .params("remarkInfo", remarkInfo);
    }

    public PostRequest<HttpData> letGo(String orderId, String pwd) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_LET_GO)
                .params("orderId", orderId)
                .params("tradePwd", pwd, true);
    }

    public GetRequest<HttpData<String>> quickBuy(String payType, String code, String num) {
        return OkGo.<HttpData<String>>get(HttpConfig.BASE_URL + HttpConfig.FABI_QUICK_BUY)
                .params("customerId", SPUtil.get(Constans.ID, ""))
                .params("orderCurrency", code)
//                .params("payType", payType)
                .params("orderAmount", num);
    }

    public PostRequest<HttpData<PageBean<OrderRecordBean>>> getOrderRecord(String page, String pageSize, String status) {
        return OkGo.<HttpData<PageBean<OrderRecordBean>>>post(HttpConfig.BASE_URL + HttpConfig.FABI_ORDER_RECORD)
                .params("status", status)
                .params("pageNumber", page)
                .params("pageSize", pageSize);
    }

    public GetRequest<HttpData<Integer>> getPositionNum() {
        return OkGo.<HttpData<Integer>>get(HttpConfig.BASE_URL + HttpConfig.POSITION_NUM)

                ;
    }

    public PostRequest<HttpData<List<PayWayItem>>> requestPayWayList() {
        return OkGo.<HttpData<List<PayWayItem>>>post(HttpConfig.BASE_URL + HttpConfig.FABI_PAY_WAY_LIST)
                .params("id", SPUtil.get(SPConfig.ID, ""));
    }
}