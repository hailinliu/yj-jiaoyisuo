package com.sskj.level.bean;

public class ProductDataBean {


    /**
     * stockCode : BTC/USDT
     * stockName : 比特币（Bitcoin）
     * stockLever : 100,200,400
     * slidingScalePrice : 0.1
     * spread : 5.0
     * contractMin : 0
     * lotSize : 1
     */

    private String stockCode;
    private String stockName;
    private String stockLever;//杠杆
    private String slidingScalePrice;//最小波动价
    private String spread;//点差
    private String contractMin;//最小交易张
    private String lotSize;//每张页数
    private String leverDealFee;//手续费
    private String minStopProfit;//最小止盈价
    private String minStopLoss;//最小止损价

    public String getMinStopProfit() {
        return minStopProfit;
    }

    public void setMinStopProfit(String minStopProfit) {
        this.minStopProfit = minStopProfit;
    }

    public String getMinStopLoss() {
        return minStopLoss;
    }

    public void setMinStopLoss(String minStopLoss) {
        this.minStopLoss = minStopLoss;
    }

    public String getLeverDealFee() {
        return leverDealFee;
    }

    public void setLeverDealFee(String leverDealFee) {
        this.leverDealFee = leverDealFee;
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

    public String getStockLever() {
        return stockLever;
    }

    public void setStockLever(String stockLever) {
        this.stockLever = stockLever;
    }

    public String getSlidingScalePrice() {
        return slidingScalePrice;
    }

    public void setSlidingScalePrice(String slidingScalePrice) {
        this.slidingScalePrice = slidingScalePrice;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getContractMin() {
        return contractMin;
    }

    public void setContractMin(String contractMin) {
        this.contractMin = contractMin;
    }

    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }
}
