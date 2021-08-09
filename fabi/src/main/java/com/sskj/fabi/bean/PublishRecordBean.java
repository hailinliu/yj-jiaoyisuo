package com.sskj.fabi.bean;


import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class PublishRecordBean {


    private String id;
    private int tradeType;
    private String stockUserId;
    private String stockCode;
    private String entrustNo;
    private String transNum;
    private String dealsRemainNum;
    private String price;
    private String totalPrice;
    private String minPrice;
    private String maxPrice;
    private String status;
    private String fee;
    private String payType;
    private long createTime;
    private boolean isDeleted;
    private long timestamp;
    private String remarkInfo;

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
        return CoinUtil.keepUSDT(dealsRemainNum);
    }

    public void setDealsRemainNum(String dealsRemainNum) {
        this.dealsRemainNum = dealsRemainNum;
    }

    public String getPrice() {
        return CoinUtil.keepRMB(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return CoinUtil.keepRMB(totalPrice);
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMinPrice() {
        return CoinUtil.keepRMB(minPrice);
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return CoinUtil.keepRMB(maxPrice);
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(createTime));
    }

    public void setCreateTime(long createTime) {
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

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }


}
