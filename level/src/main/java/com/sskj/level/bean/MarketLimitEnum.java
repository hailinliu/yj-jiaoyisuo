package com.sskj.level.bean;

public enum MarketLimitEnum {

    MARKET("1"),

    LIMIT("2");

    private String type;

    MarketLimitEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
