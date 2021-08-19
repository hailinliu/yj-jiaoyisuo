package com.sskj.hangqing.bean;

import java.io.Serializable;

public class NewChartBean implements Serializable {


    /**
     * symbol : BTC/USDT
     * openPrice : 44250.01
     * highestPrice : 44281.34
     * lowestPrice : 44220.83
     * closePrice : 44280
     * time : 1629344160000
     * period : 1min
     * count : 264
     * volume : 8.030187315855581
     * turnover : 355243.45022869
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

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

    @Override
    public String toString() {
        return "NewChartBean{" +
                "symbol='" + symbol + '\'' +
                ", openPrice=" + openPrice +
                ", highestPrice=" + highestPrice +
                ", lowestPrice=" + lowestPrice +
                ", closePrice=" + closePrice +
                ", time=" + time +
                ", period='" + period + '\'' +
                ", count=" + count +
                ", volume=" + volume +
                ", turnover=" + turnover +
                '}';
    }
}
