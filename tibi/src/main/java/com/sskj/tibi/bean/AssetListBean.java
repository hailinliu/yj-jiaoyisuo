package com.sskj.tibi.bean;

import com.sskj.lib.util.NumberUtil;

public class AssetListBean {


    /**
     * id : 4
     * stockCode : USDT
     * stockName : null
     * stockType : null
     * usableFund : 90266.3101
     * frostFund : 1000
     * inAllFee : 0
     * outAllFee : 0
     */

    private String id;
    private String stockCode;
    private String stockName;
    private String stockType;
    private String usableFund;
    private String frostFund;
    private String inAllFee;
    private String outAllFee;
    private String cnyPrice;

    private int priority;

    public String getCnyPrice() {

        return cnyPrice;
    }

    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(String usableFund) {
        this.usableFund = usableFund;
    }

    public String getFrostFund() {
        return NumberUtil.keepNoZero(frostFund);
    }

    public void setFrostFund(String frostFund) {
        this.frostFund = frostFund;
    }

    public String getInAllFee() {
        return inAllFee;
    }

    public void setInAllFee(String inAllFee) {
        this.inAllFee = inAllFee;
    }

    public String getOutAllFee() {
        return outAllFee;
    }

    public void setOutAllFee(String outAllFee) {
        this.outAllFee = outAllFee;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
