package com.sskj.level.bean;

public class TradeSetBean {

    /**
     * code : ltc_usdt
     * leverage : 1
     * num_min : 10.00
     * stoploss : 100
     * stopwin : 100
     * spread : 1.0000
     * var_price : 0.0001
     * trans_ware : 70%
     * trans_fee : 0.3%
     */

    private String code;
    private String leverage;
    private String num_min;
    private String stoploss;
    private String stopwin;
    private String spread;
    private String var_price;
    private String trans_ware;
    private String trans_fee;
    private String pcs_price;


    public String getPcs_price() {
        return pcs_price;
    }

    public void setPcs_price(String pcs_price) {
        this.pcs_price = pcs_price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public String getNum_min() {
        return num_min;
    }

    public void setNum_min(String num_min) {
        this.num_min = num_min;
    }

    public String getStoploss() {
        return stoploss;
    }

    public void setStoploss(String stoploss) {
        this.stoploss = stoploss;
    }

    public String getStopwin() {
        return stopwin;
    }

    public void setStopwin(String stopwin) {
        this.stopwin = stopwin;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getVar_price() {
        return var_price;
    }

    public void setVar_price(String var_price) {
        this.var_price = var_price;
    }

    public String getTrans_ware() {
        return trans_ware;
    }

    public void setTrans_ware(String trans_ware) {
        this.trans_ware = trans_ware;
    }

    public String getTrans_fee() {
        return trans_fee;
    }

    public void setTrans_fee(String trans_fee) {
        this.trans_fee = trans_fee;
    }
}
