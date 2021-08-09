package com.sskj.hangqing.bean.rxbus;

import java.io.Serializable;

public class QuBean implements Serializable {
    private String qu;

    public QuBean(String qu) {
        this.qu = qu;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }
}
