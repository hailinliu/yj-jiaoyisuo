package com.sskj.fabi.bean;

public class FilterBean {
    private String pType;
    private String minMoney;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public FilterBean(String pType, String minMoney, String currency) {
        this.pType = pType;
        this.minMoney = minMoney;
        this.currency = currency;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }
}
