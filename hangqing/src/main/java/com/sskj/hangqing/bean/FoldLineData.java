package com.sskj.hangqing.bean;

import com.sskj.hangqing.widget.IFoldLineData;

public class FoldLineData implements IFoldLineData {

    private String time;
    private String price;
    private String rate;

    public FoldLineData(String time, String price, String rate) {
        this.time = time;
        this.price = price;
        this.rate = rate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getRate() {
        return rate;
    }
}
