package com.sskj.level.bean;

import java.util.List;

public class BidBean {
    /**
     * direction : 0
     * maxAmount : 2
     * minAmount : 0.0104
     * highestPrice : 18974.85
     * lowestPrice : 18000
     * symbol : BTC/USDT
     * items : [{"price":18974.85,"amount":0.9148,"priceStr":"18974.85","amountStr":"0.9148"},{"price":18973.3,"amount":0.7524,"priceStr":"18973.30","amountStr":"0.7524"},{"price":18973.03,"amount":0.731,"priceStr":"18973.03","amountStr":"0.7310"},{"price":18971.75,"amount":0.7153,"priceStr":"18971.75","amountStr":"0.7153"},{"price":18968.06,"amount":0.8996,"priceStr":"18968.06","amountStr":"0.8996"},{"price":18962.31,"amount":0.6679,"priceStr":"18962.31","amountStr":"0.6679"},{"price":18961.69,"amount":0.7352,"priceStr":"18961.69","amountStr":"0.7352"},{"price":18961.66,"amount":0.6518,"priceStr":"18961.66","amountStr":"0.6518"},{"price":18957.84,"amount":0.8392,"priceStr":"18957.84","amountStr":"0.8392"},{"price":18951.02,"amount":0.8636,"priceStr":"18951.02","amountStr":"0.8636"},{"price":18946.61,"amount":0.3762,"priceStr":"18946.61","amountStr":"0.3762"},{"price":18942.99,"amount":0.0246,"priceStr":"18942.99","amountStr":"0.0246"},{"price":18940.92,"amount":0.0576,"priceStr":"18940.92","amountStr":"0.0576"},{"price":18937.99,"amount":0.4371,"priceStr":"18937.99","amountStr":"0.4371"},{"price":18928,"amount":0.9613,"priceStr":"18928.00","amountStr":"0.9613"},{"price":18918,"amount":0.7356,"priceStr":"18918.00","amountStr":"0.7356"},{"price":18917.99,"amount":0.5941,"priceStr":"18917.99","amountStr":"0.5941"},{"price":18917.32,"amount":0.1692,"priceStr":"18917.32","amountStr":"0.1692"},{"price":18909.35,"amount":0.5791,"priceStr":"18909.35","amountStr":"0.5791"},{"price":18904.98,"amount":0.1567,"priceStr":"18904.98","amountStr":"0.1567"},{"price":18898,"amount":0.653,"priceStr":"18898.00","amountStr":"0.6530"},{"price":18890.92,"amount":0.4167,"priceStr":"18890.92","amountStr":"0.4167"},{"price":18888.12,"amount":0.731,"priceStr":"18888.12","amountStr":"0.7310"},{"price":18882.44,"amount":0.327,"priceStr":"18882.44","amountStr":"0.3270"}]
     */

    private int direction;
    private int maxAmount;
    private double minAmount;
    private double highestPrice;
    private int lowestPrice;
    private String symbol;
    private List<ItemsBeanX> items;

    public List<ItemsBeanX> getItems() {
        return items;
    }

    public void setItems(List<ItemsBeanX> items) {
        this.items = items;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
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

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static class ItemsBeanX {
        /**
         * price : 18974.85
         * amount : 0.9148
         * priceStr : 18974.85
         * amountStr : 0.9148
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
        public ItemsBeanX(double price, double amount, String priceStr, String amountStr) {
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

