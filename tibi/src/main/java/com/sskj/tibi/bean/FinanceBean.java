package com.sskj.tibi.bean;

import com.sskj.lib.util.CoinUtil;

public class FinanceBean {

    private String balance;
    private String frost;
    private String rmb;
    private String fee;

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getBalance() {
        return CoinUtil.keepUSDT(balance);
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFrost() {
        return CoinUtil.keepUSDT(frost);
    }

    public void setFrost(String frost) {
        this.frost = frost;
    }

    public String getRmb() {
        return rmb;
    }

    public void setRmb(String rmb) {
        this.rmb = rmb;
    }
}
