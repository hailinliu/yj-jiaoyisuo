package com.sskj.level.bean;

import java.util.List;

public class WSFiveBean1 {

    /**
     * ask : {"direction":1,"maxAmount":1.3665,"minAmount":1.0E-4,"highestPrice":34846.06,"lowestPrice":31967.6,"symbol":"BTC/USDT","items":[{"price":31967.6,"amount":0.2116,"priceStr":"31967.60","amountStr":"0.2116"},{"price":31979.38,"amount":0.4023,"priceStr":"31979.38","amountStr":"0.4023"},{"price":32005.6,"amount":0.2831,"priceStr":"32005.60","amountStr":"0.2831"},{"price":32021.54,"amount":0.0433,"priceStr":"32021.54","amountStr":"0.0433"},{"price":32039.15,"amount":0.1813,"priceStr":"32039.15","amountStr":"0.1813"},{"price":32047.47,"amount":0.2738,"priceStr":"32047.47","amountStr":"0.2738"},{"price":32047.48,"amount":0.262,"priceStr":"32047.48","amountStr":"0.2620"},{"price":32061.92,"amount":0.3916,"priceStr":"32061.92","amountStr":"0.3916"},{"price":32066.41,"amount":0.3859,"priceStr":"32066.41","amountStr":"0.3859"},{"price":32077.46,"amount":0.2477,"priceStr":"32077.46","amountStr":"0.2477"},{"price":32086.33,"amount":0.2222,"priceStr":"32086.33","amountStr":"0.2222"},{"price":32091.61,"amount":0.049,"priceStr":"32091.61","amountStr":"0.0490"},{"price":32110.8,"amount":0.3508,"priceStr":"32110.80","amountStr":"0.3508"},{"price":32117.52,"amount":0.137,"priceStr":"32117.52","amountStr":"0.1370"},{"price":32127.09,"amount":0.9263,"priceStr":"32127.09","amountStr":"0.9263"},{"price":32127.39,"amount":0.6341,"priceStr":"32127.39","amountStr":"0.6341"},{"price":32155.01,"amount":0.3591,"priceStr":"32155.01","amountStr":"0.3591"},{"price":32155.02,"amount":0.2156,"priceStr":"32155.02","amountStr":"0.2156"},{"price":32155.1,"amount":0.3909,"priceStr":"32155.10","amountStr":"0.3909"},{"price":32176.5,"amount":0.8537,"priceStr":"32176.50","amountStr":"0.8537"},{"price":32183.31,"amount":0.0635,"priceStr":"32183.31","amountStr":"0.0635"},{"price":32184.98,"amount":0.4662,"priceStr":"32184.98","amountStr":"0.4662"},{"price":32247.21,"amount":0.2694,"priceStr":"32247.21","amountStr":"0.2694"},{"price":32281.92,"amount":0.4176,"priceStr":"32281.92","amountStr":"0.4176"}]}
     * bid : {"direction":0,"maxAmount":0.9928,"minAmount":0.0045,"highestPrice":31948.93,"lowestPrice":31432.71,"symbol":"BTC/USDT","items":[{"price":31948.93,"amount":0.1058,"priceStr":"31948.93","amountStr":"0.1058"},{"price":31946.89,"amount":0.0503,"priceStr":"31946.89","amountStr":"0.0503"},{"price":31945.89,"amount":0.3987,"priceStr":"31945.89","amountStr":"0.3987"},{"price":31942.9,"amount":0.0998,"priceStr":"31942.90","amountStr":"0.0998"},{"price":31941.53,"amount":0.0825,"priceStr":"31941.53","amountStr":"0.0825"},{"price":31940.36,"amount":0.4051,"priceStr":"31940.36","amountStr":"0.4051"},{"price":31939.8,"amount":0.1189,"priceStr":"31939.80","amountStr":"0.1189"},{"price":31936.54,"amount":0.0998,"priceStr":"31936.54","amountStr":"0.0998"},{"price":31902.84,"amount":0.2575,"priceStr":"31902.84","amountStr":"0.2575"},{"price":31900.99,"amount":0.0125,"priceStr":"31900.99","amountStr":"0.0125"},{"price":31899.39,"amount":0.0125,"priceStr":"31899.39","amountStr":"0.0125"},{"price":31893.88,"amount":0.1107,"priceStr":"31893.88","amountStr":"0.1107"},{"price":31893.87,"amount":0.1336,"priceStr":"31893.87","amountStr":"0.1336"},{"price":31891.26,"amount":0.351,"priceStr":"31891.26","amountStr":"0.3510"},{"price":31881.02,"amount":0.6932,"priceStr":"31881.02","amountStr":"0.6932"},{"price":31875.43,"amount":0.1712,"priceStr":"31875.43","amountStr":"0.1712"},{"price":31871.13,"amount":0.4383,"priceStr":"31871.13","amountStr":"0.4383"},{"price":31865.18,"amount":0.3558,"priceStr":"31865.18","amountStr":"0.3558"},{"price":31854.45,"amount":0.4632,"priceStr":"31854.45","amountStr":"0.4632"},{"price":31854.38,"amount":0.0155,"priceStr":"31854.38","amountStr":"0.0155"},{"price":31851.41,"amount":0.0664,"priceStr":"31851.41","amountStr":"0.0664"},{"price":31850.4,"amount":0.8437,"priceStr":"31850.40","amountStr":"0.8437"},{"price":31847.39,"amount":0.444,"priceStr":"31847.39","amountStr":"0.4440"},{"price":31846.39,"amount":0.3853,"priceStr":"31846.39","amountStr":"0.3853"}]}
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
         * maxAmount : 1.3665
         * minAmount : 1.0E-4
         * highestPrice : 34846.06
         * lowestPrice : 31967.6
         * symbol : BTC/USDT
         * items : [{"price":31967.6,"amount":0.2116,"priceStr":"31967.60","amountStr":"0.2116"},{"price":31979.38,"amount":0.4023,"priceStr":"31979.38","amountStr":"0.4023"},{"price":32005.6,"amount":0.2831,"priceStr":"32005.60","amountStr":"0.2831"},{"price":32021.54,"amount":0.0433,"priceStr":"32021.54","amountStr":"0.0433"},{"price":32039.15,"amount":0.1813,"priceStr":"32039.15","amountStr":"0.1813"},{"price":32047.47,"amount":0.2738,"priceStr":"32047.47","amountStr":"0.2738"},{"price":32047.48,"amount":0.262,"priceStr":"32047.48","amountStr":"0.2620"},{"price":32061.92,"amount":0.3916,"priceStr":"32061.92","amountStr":"0.3916"},{"price":32066.41,"amount":0.3859,"priceStr":"32066.41","amountStr":"0.3859"},{"price":32077.46,"amount":0.2477,"priceStr":"32077.46","amountStr":"0.2477"},{"price":32086.33,"amount":0.2222,"priceStr":"32086.33","amountStr":"0.2222"},{"price":32091.61,"amount":0.049,"priceStr":"32091.61","amountStr":"0.0490"},{"price":32110.8,"amount":0.3508,"priceStr":"32110.80","amountStr":"0.3508"},{"price":32117.52,"amount":0.137,"priceStr":"32117.52","amountStr":"0.1370"},{"price":32127.09,"amount":0.9263,"priceStr":"32127.09","amountStr":"0.9263"},{"price":32127.39,"amount":0.6341,"priceStr":"32127.39","amountStr":"0.6341"},{"price":32155.01,"amount":0.3591,"priceStr":"32155.01","amountStr":"0.3591"},{"price":32155.02,"amount":0.2156,"priceStr":"32155.02","amountStr":"0.2156"},{"price":32155.1,"amount":0.3909,"priceStr":"32155.10","amountStr":"0.3909"},{"price":32176.5,"amount":0.8537,"priceStr":"32176.50","amountStr":"0.8537"},{"price":32183.31,"amount":0.0635,"priceStr":"32183.31","amountStr":"0.0635"},{"price":32184.98,"amount":0.4662,"priceStr":"32184.98","amountStr":"0.4662"},{"price":32247.21,"amount":0.2694,"priceStr":"32247.21","amountStr":"0.2694"},{"price":32281.92,"amount":0.4176,"priceStr":"32281.92","amountStr":"0.4176"}]
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
             * price : 31967.6
             * amount : 0.2116
             * priceStr : 31967.60
             * amountStr : 0.2116
             */

            private double price;
            private double amount;
            private String priceStr;
            private String amountStr;

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

    public static class BidBean {
        /**
         * direction : 0
         * maxAmount : 0.9928
         * minAmount : 0.0045
         * highestPrice : 31948.93
         * lowestPrice : 31432.71
         * symbol : BTC/USDT
         * items : [{"price":31948.93,"amount":0.1058,"priceStr":"31948.93","amountStr":"0.1058"},{"price":31946.89,"amount":0.0503,"priceStr":"31946.89","amountStr":"0.0503"},{"price":31945.89,"amount":0.3987,"priceStr":"31945.89","amountStr":"0.3987"},{"price":31942.9,"amount":0.0998,"priceStr":"31942.90","amountStr":"0.0998"},{"price":31941.53,"amount":0.0825,"priceStr":"31941.53","amountStr":"0.0825"},{"price":31940.36,"amount":0.4051,"priceStr":"31940.36","amountStr":"0.4051"},{"price":31939.8,"amount":0.1189,"priceStr":"31939.80","amountStr":"0.1189"},{"price":31936.54,"amount":0.0998,"priceStr":"31936.54","amountStr":"0.0998"},{"price":31902.84,"amount":0.2575,"priceStr":"31902.84","amountStr":"0.2575"},{"price":31900.99,"amount":0.0125,"priceStr":"31900.99","amountStr":"0.0125"},{"price":31899.39,"amount":0.0125,"priceStr":"31899.39","amountStr":"0.0125"},{"price":31893.88,"amount":0.1107,"priceStr":"31893.88","amountStr":"0.1107"},{"price":31893.87,"amount":0.1336,"priceStr":"31893.87","amountStr":"0.1336"},{"price":31891.26,"amount":0.351,"priceStr":"31891.26","amountStr":"0.3510"},{"price":31881.02,"amount":0.6932,"priceStr":"31881.02","amountStr":"0.6932"},{"price":31875.43,"amount":0.1712,"priceStr":"31875.43","amountStr":"0.1712"},{"price":31871.13,"amount":0.4383,"priceStr":"31871.13","amountStr":"0.4383"},{"price":31865.18,"amount":0.3558,"priceStr":"31865.18","amountStr":"0.3558"},{"price":31854.45,"amount":0.4632,"priceStr":"31854.45","amountStr":"0.4632"},{"price":31854.38,"amount":0.0155,"priceStr":"31854.38","amountStr":"0.0155"},{"price":31851.41,"amount":0.0664,"priceStr":"31851.41","amountStr":"0.0664"},{"price":31850.4,"amount":0.8437,"priceStr":"31850.40","amountStr":"0.8437"},{"price":31847.39,"amount":0.444,"priceStr":"31847.39","amountStr":"0.4440"},{"price":31846.39,"amount":0.3853,"priceStr":"31846.39","amountStr":"0.3853"}]
         */

        private int direction;
        private double maxAmount;
        private double minAmount;
        private double highestPrice;
        private double lowestPrice;
        private String symbol;
        private List<ItemsBeanX> items;

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

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
        }

        public static class ItemsBeanX {
            /**
             * price : 31948.93
             * amount : 0.1058
             * priceStr : 31948.93
             * amountStr : 0.1058
             */

            private double price;
            private double amount;
            private String priceStr;
            private String amountStr;

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
