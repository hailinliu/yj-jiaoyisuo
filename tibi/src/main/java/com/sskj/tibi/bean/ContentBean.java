package com.sskj.tibi.bean;

import java.io.Serializable;

public class ContentBean implements Serializable {
    private int id;
    private int memberId;
    private String coinKey;
    private int totalAmount;
    private int fee;
    private int arrivedAmount;
    private String createTime;
    private Object dealTime;
    private int status;
    private int isAuto;
    private Object admin;
    private Object canAutoWithdraw;
    private Object transactionNumber;
    private String address;
    private Object remark;
    private int isQuick;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getCoinKey() {
        return coinKey;
    }

    public void setCoinKey(String coinKey) {
        this.coinKey = coinKey;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getArrivedAmount() {
        return arrivedAmount;
    }

    public void setArrivedAmount(int arrivedAmount) {
        this.arrivedAmount = arrivedAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getDealTime() {
        return dealTime;
    }

    public void setDealTime(Object dealTime) {
        this.dealTime = dealTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(int isAuto) {
        this.isAuto = isAuto;
    }

    public Object getAdmin() {
        return admin;
    }

    public void setAdmin(Object admin) {
        this.admin = admin;
    }

    public Object getCanAutoWithdraw() {
        return canAutoWithdraw;
    }

    public void setCanAutoWithdraw(Object canAutoWithdraw) {
        this.canAutoWithdraw = canAutoWithdraw;
    }

    public Object getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Object transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getIsQuick() {
        return isQuick;
    }

    public void setIsQuick(int isQuick) {
        this.isQuick = isQuick;
    }
}
