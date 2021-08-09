package com.sskj.level.bean;


import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.CommonUtil;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-18 10:13
 */
public class EntrustBean {


    /**
     * id : 659
     * time : 2019-06-19 17:08:00
     * tradeType : 买入
     * price : 156.8929
     * num : 5
     * billPriceType : 2
     * dealNum : 5
     */

    private String id;
    private String time;
    private int tradeType;
    private String price;
    private String num;
    private int billPriceType;
    private String dealNum;
    private String stockCode;
    private String totalMoney;
    //触发价
    private String touchPrice;
    //4 未触发 0 进去委托 1 交易中 2 交易完成 3 已撤单
    private int state;


    public String getTouchPrice() {
        return touchPrice;
    }

    public void setTouchPrice(String touchPrice) {
        this.touchPrice = touchPrice;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTotalMoney() {

        return CommonUtil.dealTradeBibi(totalMoney);
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isBuyAndMarket() {
        return (tradeType == 1 && billPriceType == 1);
    }

    public String getLCode() {
        if (stockCode == null) {
            return stockCode;
        }
        return stockCode.replace("_", "/").toUpperCase().split("/")[0];
    }

    public String getRCode() {
        if (stockCode == null) {
            return stockCode;
        }
        return stockCode.replace("_", "/").toUpperCase().split("/")[1];
    }

    public boolean isBuy() {
        return tradeType == 1;
    }

    public boolean isMarket() {
        return billPriceType == 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrice() {
        return CommonUtil.dealTradeBibi(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {

        return CoinUtil.keepCoinNum(stockCode, num);
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getBillPriceType() {
        return billPriceType;
    }

    public void setBillPriceType(int billPriceType) {
        this.billPriceType = billPriceType;
    }

    public String getDealNum() {
        return CommonUtil.dealTradeBibi(dealNum);
    }

    public void setDealNum(String dealNum) {
        this.dealNum = dealNum;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
