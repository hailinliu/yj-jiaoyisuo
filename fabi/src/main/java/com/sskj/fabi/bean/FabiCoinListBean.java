package com.sskj.fabi.bean;

public class FabiCoinListBean {

    /**
     * imgUrl : /file/BTC@2x.png
     * unit : BTC
     * marketPrice : 0.00
     * jpyMarketPrice : 0.00000000
     * name : Bitcoin
     * sell_min_amount : 100.000000000000000000
     * nameCn : 比特币
     * id : 12
     * sort : 0
     * buy_min_amount : 0.010000000000000000
     */

    private String imgUrl;
    private String unit;
    private String marketPrice;
    private String jpyMarketPrice;
    private String name;
    private String sell_min_amount;
    private String nameCn;
    private String id;
    private String sort;
    private String buy_min_amount;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getJpyMarketPrice() {
        return jpyMarketPrice;
    }

    public void setJpyMarketPrice(String jpyMarketPrice) {
        this.jpyMarketPrice = jpyMarketPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSell_min_amount() {
        return sell_min_amount;
    }

    public void setSell_min_amount(String sell_min_amount) {
        this.sell_min_amount = sell_min_amount;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBuy_min_amount() {
        return buy_min_amount;
    }

    public void setBuy_min_amount(String buy_min_amount) {
        this.buy_min_amount = buy_min_amount;
    }
}
