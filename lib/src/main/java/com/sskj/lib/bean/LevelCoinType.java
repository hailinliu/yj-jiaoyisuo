package com.sskj.lib.bean;

import java.io.Serializable;

/**
 * 切换币种
 */
public class LevelCoinType implements Serializable {
    String code;
    boolean isBuy;
    String qu;

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public LevelCoinType(String code, String qu) {
        this.code = code;
        this.qu = qu;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public LevelCoinType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
