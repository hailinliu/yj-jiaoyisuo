package com.sskj.hangqing.bean;

import com.sskj.lib.util.CoinUtil;

public class SlideCoinBean {
    private String typeFirst = "";
    private String usdtFirst = "";
    private String rateFirst = "";
    private String rmbFirst = "";
    private boolean isUpFirst = false;
    private String typeSecond = "";
    private String usdtSecond = "";
    private String rateSecond = "";
    private String rmbSecond = "";
    private boolean isUpSecond = false;
    private String typeThird = "";
    private String usdtThird = "";
    private String rateThird = "";
    private String rmbThird = "";

    private boolean isUpThird = false;


    public SlideCoinBean(String typeFirst, String typeSecond, String typeThird) {
        this.typeFirst = typeFirst;
        this.typeSecond = typeSecond;
        this.typeThird = typeThird;
    }

    public String getRmbFirst() {
        return rmbFirst;
    }

    public void setRmbFirst(String rmbFirst) {
        this.rmbFirst = rmbFirst;
    }

    public String getRmbSecond() {
        return rmbSecond;
    }

    public void setRmbSecond(String rmbSecond) {
        this.rmbSecond = rmbSecond;
    }

    public String getRmbThird() {
        return rmbThird;
    }

    public void setRmbThird(String rmbThird) {
        this.rmbThird = rmbThird;
    }

    public String getTypeFirst() {
        return CoinUtil.showName(typeFirst);
    }

    public SlideCoinBean setTypeFirst(String typeFirst) {
        this.typeFirst = typeFirst;
        return this;
    }

    public String getUsdtFirst() {
        return usdtFirst;
    }

    public SlideCoinBean setUsdtFirst(String usdtFirst) {
        this.usdtFirst = usdtFirst;
        return this;
    }

    public String getRateFirst() {
        return rateFirst;
    }

    public SlideCoinBean setRateFirst(String rateFirst) {
        this.rateFirst = rateFirst;
        return this;
    }

    public boolean isUpFirst() {
        return isUpFirst;
    }

    public SlideCoinBean setUpFirst(boolean upFirst) {
        isUpFirst = upFirst;
        return this;
    }

    public String getTypeSecond() {
        return CoinUtil.showName(typeSecond);
    }

    public SlideCoinBean setTypeSecond(String typeSecond) {
        this.typeSecond = typeSecond;
        return this;
    }

    public String getUsdtSecond() {
        return usdtSecond;
    }

    public SlideCoinBean setUsdtSecond(String usdtSecond) {
        this.usdtSecond = usdtSecond;
        return this;
    }

    public String getRateSecond() {
        return rateSecond;
    }

    public SlideCoinBean setRateSecond(String rateSecond) {
        this.rateSecond = rateSecond;
        return this;
    }

    public boolean isUpSecond() {
        return isUpSecond;
    }

    public SlideCoinBean setUpSecond(boolean upSecond) {
        isUpSecond = upSecond;
        return this;
    }

    public String getTypeThird() {
        return CoinUtil.showName(typeThird);
    }

    public SlideCoinBean setTypeThird(String typeThird) {
        this.typeThird = typeThird;
        return this;
    }

    public String getUsdtThird() {
        return usdtThird;
    }

    public SlideCoinBean setUsdtThird(String usdtThird) {
        this.usdtThird = usdtThird;
        return this;
    }

    public String getRateThird() {
        return rateThird;
    }

    public SlideCoinBean setRateThird(String rateThird) {
        this.rateThird = rateThird;
        return this;
    }

    public boolean isUpThird() {
        return isUpThird;
    }

    public SlideCoinBean setUpThird(boolean upThird) {
        isUpThird = upThird;
        return this;
    }
}
