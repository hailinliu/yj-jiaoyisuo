package com.sskj.hangqing.bean;

import java.util.List;

public class PushTradeBean {

    /**
     * code : btc_usdt
     * name : BTC/USDT
     * timestamp : 1545459681668
     * data : [{"dt":1545459681347,"dc":"buy","amount":0.0054,"price":3821.83}]
     */

    private String code;
    private String name;
    private long timestamp;
    private List<TradeBean> data;

    public String getCode() {
        return code.replace("_","/").toUpperCase();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<TradeBean> getData() {
        return data;
    }

    public void setData(List<TradeBean> data) {
        this.data = data;
    }


}
