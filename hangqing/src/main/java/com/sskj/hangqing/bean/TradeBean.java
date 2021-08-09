package com.sskj.hangqing.bean;


import com.sskj.lib.util.NumberUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class TradeBean {


    /**
     * amount : 4
     * dc : sell
     * dt : 1564576139725
     * price : 9797
     */

    private String amount;
    private String dc;
    private String dt;
    private String price;

    public String getAmount() {
        return NumberUtil.keep(amount, 6);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getDt() {
        if (dt.length() == 10) {
            dt = dt + "000";
        }

        return TimeFormatUtil.SF_FORMAT_H.format(new Date(Long.parseLong(dt)));
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
