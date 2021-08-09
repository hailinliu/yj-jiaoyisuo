package com.sskj.bibi.bean;

import com.google.gson.annotations.JsonAdapter;
import com.sskj.common.box.gson.StringNotNullZoneAdapter;

public class CoinFee {

    /**
     * left_code : 1000.0000
     * right_code : 1000.0000
     * trans_fee : 0.001
     */
    @JsonAdapter(value = StringNotNullZoneAdapter.class, nullSafe = false)
    private String left_code;
    @JsonAdapter(value = StringNotNullZoneAdapter.class, nullSafe = false)
    private String right_code;
    private String trans_fee;
    private String buy_rate;
    private String sell_rate;

    public String getLeft_code() {
        return left_code;
    }

    public void setLeft_code(String left_code) {
        this.left_code = left_code;
    }

    public String getRight_code() {
        return right_code;
    }

    public void setRight_code(String right_code) {
        this.right_code = right_code;
    }

    public String getTrans_fee() {
        return trans_fee;
    }

    public CoinFee setTrans_fee(String trans_fee) {
        this.trans_fee = trans_fee;
        return this;
    }

    public String getBuy_rate() {
        return buy_rate;
    }

    public void setBuy_rate(String buy_rate) {
        this.buy_rate = buy_rate;
    }

    public String getSell_rate() {
        return sell_rate;
    }

    public void setSell_rate(String sell_rate) {
        this.sell_rate = sell_rate;
    }
}
