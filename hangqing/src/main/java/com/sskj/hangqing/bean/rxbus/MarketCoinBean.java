package com.sskj.hangqing.bean.rxbus;

import java.io.Serializable;

public class MarketCoinBean implements Serializable {
    String code;
    String qu;

    public MarketCoinBean(String code,String qu) {
        this.code = code;
        this.qu = qu;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
