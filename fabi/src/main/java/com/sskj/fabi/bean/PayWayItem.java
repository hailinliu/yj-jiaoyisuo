package com.sskj.fabi.bean;

public class PayWayItem {
    private String id;
    private String name;
    private String number;
    private String type;
    private boolean status;
    private String tip;
    private String img;
    private String bank;
    private String branch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    private int payType;
    private String qrCode;
    private String account;
    private String AccountOpeningBranch;
    private String bankCardOpenBank;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getQrCode() {
        return qrCode;
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
