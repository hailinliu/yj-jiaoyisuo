package com.sskj.fabi.bean;

import java.io.Serializable;
import java.util.List;

public class NewFabiAllBean implements Serializable {
    /**
     * data : [{"imgUrl":"/file/BTC@2x.png","unit":"BTC","marketPrice":"0.00","jpyMarketPrice":"0.00000000","name":"Bitcoin","sell_min_amount":"100.000000000000000000","nameCn":"比特币","id":"12","sort":"0","buy_min_amount":"0.010000000000000000"},{"imgUrl":"/file/USDT@2x.png","unit":"USDT","marketPrice":"6.43","jpyMarketPrice":"0","name":"USDT","sell_min_amount":"100.000000000000000000","nameCn":"泰达币","id":"13","sort":"0","buy_min_amount":"0.010000000000000000"},{"imgUrl":"/file/BTC@2x.png","unit":"EOS","marketPrice":"0.20","jpyMarketPrice":"0.00000000","name":"EOS","sell_min_amount":"100.000000000000000000","nameCn":"EOS","id":"14","sort":"0","buy_min_amount":"10000.000000000000000000"}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private int code;
    private int errCode;
    private String message;
    private Object total;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
}