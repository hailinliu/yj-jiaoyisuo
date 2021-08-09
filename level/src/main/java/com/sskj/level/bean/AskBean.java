package com.sskj.level.bean;



import java.util.List;

public class AskBean {
    /**
     * direction : 1
     * maxAmount : 0.9948
     * minAmount : 0.0115
     * highestPrice : 19423.87
     * lowestPrice : 18978.85
     * symbol : BTC/USDT
     * items : [{"price":18978.85,"amount":0.4099,"priceStr":"18978.85","amountStr":"0.4099"},{"price":18982.31,"amount":0.6626,"priceStr":"18982.31","amountStr":"0.6626"},{"price":18983.62,"amount":0.7976,"priceStr":"18983.62","amountStr":"0.7976"},{"price":18987.88,"amount":0.3751,"priceStr":"18987.88","amountStr":"0.3751"},{"price":18987.94,"amount":0.3637,"priceStr":"18987.94","amountStr":"0.3637"},{"price":18987.95,"amount":0.576,"priceStr":"18987.95","amountStr":"0.5760"},{"price":18990.24,"amount":0.861,"priceStr":"18990.24","amountStr":"0.8610"},{"price":18990.26,"amount":0.4148,"priceStr":"18990.26","amountStr":"0.4148"},{"price":18990.27,"amount":0.3916,"priceStr":"18990.27","amountStr":"0.3916"},{"price":18991.89,"amount":0.062,"priceStr":"18991.89","amountStr":"0.0620"},{"price":18999.96,"amount":0.8216,"priceStr":"18999.96","amountStr":"0.8216"},{"price":19006.36,"amount":0.1878,"priceStr":"19006.36","amountStr":"0.1878"},{"price":19007.2,"amount":0.442,"priceStr":"19007.20","amountStr":"0.4420"},{"price":19007.21,"amount":0.849,"priceStr":"19007.21","amountStr":"0.8490"},{"price":19007.67,"amount":0.9151,"priceStr":"19007.67","amountStr":"0.9151"},{"price":19007.68,"amount":0.8816,"priceStr":"19007.68","amountStr":"0.8816"},{"price":19014.06,"amount":0.6741,"priceStr":"19014.06","amountStr":"0.6741"},{"price":19014.53,"amount":0.3805,"priceStr":"19014.53","amountStr":"0.3805"},{"price":19016.13,"amount":0.1016,"priceStr":"19016.13","amountStr":"0.1016"},{"price":19016.14,"amount":0.0357,"priceStr":"19016.14","amountStr":"0.0357"},{"price":19016.64,"amount":0.8264,"priceStr":"19016.64","amountStr":"0.8264"},{"price":19017,"amount":0.5663,"priceStr":"19017.00","amountStr":"0.5663"},{"price":19018.56,"amount":0.4259,"priceStr":"19018.56","amountStr":"0.4259"},{"price":19020.94,"amount":0.167,"priceStr":"19020.94","amountStr":"0.1670"}]
     */

    private int direction;
    private double maxAmount;
    private double minAmount;
    private double highestPrice;
    private double lowestPrice;
    private String symbol;
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public static class ItemsBean {
        /**
         * price : 18978.85
         * amount : 0.4099
         * priceStr : 18978.85
         * amountStr : 0.4099
         */

        private double price;
        private double amount;
        private String priceStr;
        private String amountStr;
        private int percent = 0;

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
        public ItemsBean(double price, double amount, String priceStr, String amountStr) {
            this.price = price;
            this.amount = amount;
            this.priceStr = priceStr;
            this.amountStr = amountStr;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getPriceStr() {
            return priceStr;
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = priceStr;
        }

        public String getAmountStr() {
            return amountStr;
        }

        public void setAmountStr(String amountStr) {
            this.amountStr = amountStr;
        }
    }
}
