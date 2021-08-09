package com.sskj.bibi.bean;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：币币交易界面所需的数值
 * 创建时间：2018-09-15 16:19
 */
public class CoinUpdateBean {
    private String marketPrice;//市价
    private String rightMoney;//用户可用usdt
    private String leftMoney;//用户可用当前币种金额
    private String fee;//手续费
    private String sellRate;
    private String buyRate;


    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }


    public String getRightMoney() {
        return rightMoney;
    }

    public void setRightMoney(String rightMoney) {
        this.rightMoney = rightMoney;
    }

    public String getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(String leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getSellRate() {
        return sellRate;
    }

    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }
}
