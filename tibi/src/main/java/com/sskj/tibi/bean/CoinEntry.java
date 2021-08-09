package com.sskj.tibi.bean;

public class CoinEntry {
    /**
     * id : 4
     * baseCode : USDT
     * dealCode : ETH
     * exchangeFee : 1.2
     * exchangeCoinMin : 1
     * exchangeCoinMax : 10
     * timestamp : 1561705406000
     */

    private String id;
    private String baseCode;
    private String dealCode;
    private double exchangeFee;
    private int exchangeCoinMin;
    private int exchangeCoinMax;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
        this.dealCode = dealCode;
    }

    public double getExchangeFee() {
        return exchangeFee;
    }

    public void setExchangeFee(double exchangeFee) {
        this.exchangeFee = exchangeFee;
    }

    public int getExchangeCoinMin() {
        return exchangeCoinMin;
    }

    public void setExchangeCoinMin(int exchangeCoinMin) {
        this.exchangeCoinMin = exchangeCoinMin;
    }

    public int getExchangeCoinMax() {
        return exchangeCoinMax;
    }

    public void setExchangeCoinMax(int exchangeCoinMax) {
        this.exchangeCoinMax = exchangeCoinMax;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

//    /**
//     * pid : 3
//     * code : eth_usdt
//     * pname : ETH
//     */
//
//    private String pid;
//    private String code;
//    private String name;
//
//    public String getPid() {
//        return pid;
//    }
//
//    public void setPid(String pid) {
//        this.pid = pid;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


}
