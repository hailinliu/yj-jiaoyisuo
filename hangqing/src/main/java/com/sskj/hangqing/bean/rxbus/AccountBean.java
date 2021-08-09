package com.sskj.hangqing.bean.rxbus;

import java.io.Serializable;

public class AccountBean implements Serializable {
    private String account;

    public AccountBean(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
