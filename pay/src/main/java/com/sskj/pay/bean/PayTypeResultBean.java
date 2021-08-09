package com.sskj.pay.bean;

import com.sskj.pay.http.HttpConfig;

public class PayTypeResultBean {

    private String id;

    private boolean status;  //false 表示开启 true表示关闭
    private String payType;
    private String qrCode;
    private String account;
    private String AccountOpeningBranch;
    private String bankCardOpenBank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getQrCode() {
        return  qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountOpeningBranch() {
        return AccountOpeningBranch;
    }

    public void setAccountOpeningBranch(String accountOpeningBranch) {
        AccountOpeningBranch = accountOpeningBranch;
    }

    public String getBankCardOpenBank() {
        return bankCardOpenBank;
    }

    public void setBankCardOpenBank(String bankCardOpenBank) {
        this.bankCardOpenBank = bankCardOpenBank;
    }
}
