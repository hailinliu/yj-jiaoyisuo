package com.sskj.lib.bean;

import java.io.Serializable;
import java.util.List;

public class WSFiveBean1 implements Serializable {

    /**
     * ask : {"direction":1,"maxAmount":0.9948,"minAmount":0.0115,"highestPrice":19423.87,"lowestPrice":18978.85,"symbol":"BTC/USDT","items":[{"price":18978.85,"amount":0.4099,"priceStr":"18978.85","amountStr":"0.4099"},{"price":18982.31,"amount":0.6626,"priceStr":"18982.31","amountStr":"0.6626"},{"price":18983.62,"amount":0.7976,"priceStr":"18983.62","amountStr":"0.7976"},{"price":18987.88,"amount":0.3751,"priceStr":"18987.88","amountStr":"0.3751"},{"price":18987.94,"amount":0.3637,"priceStr":"18987.94","amountStr":"0.3637"},{"price":18987.95,"amount":0.576,"priceStr":"18987.95","amountStr":"0.5760"},{"price":18990.24,"amount":0.861,"priceStr":"18990.24","amountStr":"0.8610"},{"price":18990.26,"amount":0.4148,"priceStr":"18990.26","amountStr":"0.4148"},{"price":18990.27,"amount":0.3916,"priceStr":"18990.27","amountStr":"0.3916"},{"price":18991.89,"amount":0.062,"priceStr":"18991.89","amountStr":"0.0620"},{"price":18999.96,"amount":0.8216,"priceStr":"18999.96","amountStr":"0.8216"},{"price":19006.36,"amount":0.1878,"priceStr":"19006.36","amountStr":"0.1878"},{"price":19007.2,"amount":0.442,"priceStr":"19007.20","amountStr":"0.4420"},{"price":19007.21,"amount":0.849,"priceStr":"19007.21","amountStr":"0.8490"},{"price":19007.67,"amount":0.9151,"priceStr":"19007.67","amountStr":"0.9151"},{"price":19007.68,"amount":0.8816,"priceStr":"19007.68","amountStr":"0.8816"},{"price":19014.06,"amount":0.6741,"priceStr":"19014.06","amountStr":"0.6741"},{"price":19014.53,"amount":0.3805,"priceStr":"19014.53","amountStr":"0.3805"},{"price":19016.13,"amount":0.1016,"priceStr":"19016.13","amountStr":"0.1016"},{"price":19016.14,"amount":0.0357,"priceStr":"19016.14","amountStr":"0.0357"},{"price":19016.64,"amount":0.8264,"priceStr":"19016.64","amountStr":"0.8264"},{"price":19017,"amount":0.5663,"priceStr":"19017.00","amountStr":"0.5663"},{"price":19018.56,"amount":0.4259,"priceStr":"19018.56","amountStr":"0.4259"},{"price":19020.94,"amount":0.167,"priceStr":"19020.94","amountStr":"0.1670"}]}
     * bid : {"direction":0,"maxAmount":2,"minAmount":0.0104,"highestPrice":18974.85,"lowestPrice":18000,"symbol":"BTC/USDT","items":[{"price":18974.85,"amount":0.9148,"priceStr":"18974.85","amountStr":"0.9148"},{"price":18973.3,"amount":0.7524,"priceStr":"18973.30","amountStr":"0.7524"},{"price":18973.03,"amount":0.731,"priceStr":"18973.03","amountStr":"0.7310"},{"price":18971.75,"amount":0.7153,"priceStr":"18971.75","amountStr":"0.7153"},{"price":18968.06,"amount":0.8996,"priceStr":"18968.06","amountStr":"0.8996"},{"price":18962.31,"amount":0.6679,"priceStr":"18962.31","amountStr":"0.6679"},{"price":18961.69,"amount":0.7352,"priceStr":"18961.69","amountStr":"0.7352"},{"price":18961.66,"amount":0.6518,"priceStr":"18961.66","amountStr":"0.6518"},{"price":18957.84,"amount":0.8392,"priceStr":"18957.84","amountStr":"0.8392"},{"price":18951.02,"amount":0.8636,"priceStr":"18951.02","amountStr":"0.8636"},{"price":18946.61,"amount":0.3762,"priceStr":"18946.61","amountStr":"0.3762"},{"price":18942.99,"amount":0.0246,"priceStr":"18942.99","amountStr":"0.0246"},{"price":18940.92,"amount":0.0576,"priceStr":"18940.92","amountStr":"0.0576"},{"price":18937.99,"amount":0.4371,"priceStr":"18937.99","amountStr":"0.4371"},{"price":18928,"amount":0.9613,"priceStr":"18928.00","amountStr":"0.9613"},{"price":18918,"amount":0.7356,"priceStr":"18918.00","amountStr":"0.7356"},{"price":18917.99,"amount":0.5941,"priceStr":"18917.99","amountStr":"0.5941"},{"price":18917.32,"amount":0.1692,"priceStr":"18917.32","amountStr":"0.1692"},{"price":18909.35,"amount":0.5791,"priceStr":"18909.35","amountStr":"0.5791"},{"price":18904.98,"amount":0.1567,"priceStr":"18904.98","amountStr":"0.1567"},{"price":18898,"amount":0.653,"priceStr":"18898.00","amountStr":"0.6530"},{"price":18890.92,"amount":0.4167,"priceStr":"18890.92","amountStr":"0.4167"},{"price":18888.12,"amount":0.731,"priceStr":"18888.12","amountStr":"0.7310"},{"price":18882.44,"amount":0.327,"priceStr":"18882.44","amountStr":"0.3270"}]}
     */

    private AskBean ask;
    private BidBean bid;


    public AskBean getAsk() {
        return ask;
    }

    public void setAsk(AskBean ask) {
        this.ask = ask;
    }

    public BidBean getBid() {
        return bid;
    }

    public void setBid(BidBean bid) {
        this.bid = bid;
    }

    public static class AskBean {
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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
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

    public static class BidBean implements Serializable{
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
        private double maxAmount;
        private double minAmount;
        private double highestPrice;
        private double lowestPrice;
        private String symbol;
        private List<ItemsBeanX> items;

        public double getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(double maxAmount) {
            this.maxAmount = maxAmount;
        }

        public double getLowestPrice() {
            return lowestPrice;
        }

        public void setLowestPrice(double lowestPrice) {
            this.lowestPrice = lowestPrice;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
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

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
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
}
