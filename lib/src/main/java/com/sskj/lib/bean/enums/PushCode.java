package com.sskj.lib.bean.enums;

import java.io.Serializable;

/**
 * 推送更改订阅
 */
public class PushCode implements Serializable {
    private String json;

    public PushCode(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
