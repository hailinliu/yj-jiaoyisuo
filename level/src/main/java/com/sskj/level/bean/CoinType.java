package com.sskj.level.bean;

import java.io.Serializable;

public class CoinType implements Serializable {
    String code;//币种编码
    boolean isBuy = true;//true 买 false 卖,更换币种时默认买

    public CoinType(String code, boolean isBuy) {
        this.code = code;
        this.isBuy = isBuy;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public CoinType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
