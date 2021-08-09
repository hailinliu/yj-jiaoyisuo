package com.sskj.fabi.bean;

public class FabiRecordFilterBean {
    private String orn;
    private String cointype;
    private String status;

    public FabiRecordFilterBean(String orn, String cointype, String status) {
        this.orn = orn;
        this.cointype = cointype;
        this.status = status;
    }

    public String getOrn() {
        return orn;
    }

    public void setOrn(String orn) {
        this.orn = orn;
    }

    public String getCointype() {
        return cointype;
    }

    public void setCointype(String cointype) {
        this.cointype = cointype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
