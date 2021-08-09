package com.sskj.level.bean;

public class RecordDetailBean {

    /**
     * id : 550
     * stockUserId : 183
     * stockCode : ETH/USDT
     * stockName : null
     * billPriceType : 2
     * entrustNo : 15609352807028052p1ui0
     * transNum : 1.82772004
     * dealsRemainNum : 1.82772003
     * entrustPrice : 798.5508
     * dealPrice : 798.55079999
     * totalPrice : 1459.52730011
     * fee : 14.59527292
     * dealTime : 2019-06-19 17:10:06
     * state : 2
     * createTime : 2019-06-19 17:08:00
     * isDeleted : false
     * timestamp : 1560935406000
     * iniFee : 14.595273
     * dealMoney : 1459.52729213
     * useMiningFund : 0
     * miningIniFee : 0
     * miningFee : 0
     */

    private String id;
    private String stockUserId;
    private String stockCode;
    private String stockName;
    private int billPriceType;
    private String entrustNo;
    private String transNum;
    private String dealsRemainNum;
    private String entrustPrice;
    private String dealPrice;
    private String totalPrice;
    private String fee;
    private String dealTime;
    private int state;
    private String createTime;
    private boolean isDeleted;
    private long timestamp;
    private String iniFee;
    private String dealMoney;
    private String useMiningFund;
    private String miningIniFee;
    private String miningFee;
    public String getLCode(){
        if (stockCode==null){
            return stockCode;
        }
        return stockCode.replace("_","/").toUpperCase().split("/")[0];
    }
    public String getRCode(){
        if (stockCode==null){
            return stockCode;
        }
        return stockCode.replace("_","/").toUpperCase().split("/")[1];
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockUserId() {
        return stockUserId;
    }

    public void setStockUserId(String stockUserId) {
        this.stockUserId = stockUserId;
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

    public int getBillPriceType() {
        return billPriceType;
    }

    public void setBillPriceType(int billPriceType) {
        this.billPriceType = billPriceType;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getTransNum() {
        return transNum;
    }

    public void setTransNum(String transNum) {
        this.transNum = transNum;
    }

    public String getDealsRemainNum() {
        return dealsRemainNum;
    }

    public void setDealsRemainNum(String dealsRemainNum) {
        this.dealsRemainNum = dealsRemainNum;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(String dealPrice) {
        this.dealPrice = dealPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIniFee() {
        return iniFee;
    }

    public void setIniFee(String iniFee) {
        this.iniFee = iniFee;
    }

    public String getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(String dealMoney) {
        this.dealMoney = dealMoney;
    }

    public String getUseMiningFund() {
        return useMiningFund;
    }

    public void setUseMiningFund(String useMiningFund) {
        this.useMiningFund = useMiningFund;
    }

    public String getMiningIniFee() {
        return miningIniFee;
    }

    public void setMiningIniFee(String miningIniFee) {
        this.miningIniFee = miningIniFee;
    }

    public String getMiningFee() {
        return miningFee;
    }

    public void setMiningFee(String miningFee) {
        this.miningFee = miningFee;
    }
}
