package com.sskj.hangqing.bean;

import java.io.Serializable;

public class NewChartBean implements Serializable {

    /**
     * symbol : BTC/USDT
     * openPrice : 16940.01
     * highestPrice : 16940.65
     * lowestPrice : 16935.17
     * closePrice : 16939.6
     * time : 1606557300
     * period : 1min
     * count : 65
     * volume : 3.5005
     * turnover : 219.8848
     */

    private String symbol;
    private double openPrice;
    private double highestPrice;
    private double lowestPrice;
    private double closePrice;
    private long time;
    private String period;
    private int count;
    private double volume;
    private double turnover;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }
}
