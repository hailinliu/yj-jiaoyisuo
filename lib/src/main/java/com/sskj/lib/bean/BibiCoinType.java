package com.sskj.lib.bean;

import java.io.Serializable;

public class BibiCoinType implements Serializable {
    private String code;

    public BibiCoinType(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
