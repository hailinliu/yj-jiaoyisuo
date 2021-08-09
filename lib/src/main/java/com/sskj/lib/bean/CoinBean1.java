package com.sskj.lib.bean;

import java.io.Serializable;

public class CoinBean1 implements Serializable {


    /**
     * symbol : BTC/USDT
     * open : 18304.54
     * high : 18680
     * low : 17629.25
     * close : 18540.74
     * chg : 0.013
     * change : 236.2
     * volume : 586366
     * turnover : 35143.9475
     * lastDayClose : 0
     * usdRate : null
     * baseUsdRate : 6.7
     * closeStr : null
     * trend : null
     * proportion : null
     * cnyPrice : null
     * cnName : null
     * scale : 2
     * imgUrl : /file/BTC@2x.png
     */

    private String symbol;
    private double open;
    private double high;
    private double low;
    private double close;
    private double chg;
    private boolean isUp;
    private String LCode;

    public String getLCode() {
        return symbol.split("/")[0];
    }

    public void setLCode(String LCode) {
        this.LCode = LCode;
    }

    public boolean isUp() {
        return chg>0;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    private double change;
    private double volume;
    private double turnover;
    private int lastDayClose;
    private String usdRate;
    private double baseUsdRate;
    private String closeStr;
    private String trend;
    private String proportion;
    private String cnyPrice;
    private String cnName;
    private int scale;
    private String imgUrl;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public int getLastDayClose() {
        return lastDayClose;
    }

    public void setLastDayClose(int lastDayClose) {
        this.lastDayClose = lastDayClose;
    }

    public String getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(String usdRate) {
        this.usdRate = usdRate;
    }

    public double getBaseUsdRate() {
        return baseUsdRate;
    }

    public void setBaseUsdRate(double baseUsdRate) {
        this.baseUsdRate = baseUsdRate;
    }

    public String getCloseStr() {
        return closeStr;
    }

    public void setCloseStr(String closeStr) {
        this.closeStr = closeStr;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getCnyPrice() {
        return cnyPrice;
    }

    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
