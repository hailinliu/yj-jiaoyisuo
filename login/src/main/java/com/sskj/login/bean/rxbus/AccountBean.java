package com.sskj.login.bean.rxbus;

import java.io.Serializable;

public class AccountBean implements Serializable {
    private String account;
    private int type;

    public AccountBean(String account) {
        this.account = account;
    }

    public AccountBean(String account, int type) {
        this.account = account;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
