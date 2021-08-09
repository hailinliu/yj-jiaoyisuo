package com.sskj.level.bean;

public class GangGanBean {

    /**
     * data : {"symbol":"BTC/USDT","baseCoinScale":2,"baseSymbol":"USDT","coinSymbol":"USDT","defaultSymbol":"0","enable":1,"enableMarketSell":1,"enableMarketBuy":1,"fee":0.002,"flag":0,"maxTradingOrder":0,"maxVolume":0,"minSellPrice":0,"minTurnover":0,"minVolume":0,"sort":2,"zone":0,"coinScale":4,"newSort":-1,"visible":1,"exchangeable":1,"maxBuyPrice":0,"areaId":1,"imgUrl":"/file/20210108/ED092BC5A7724E078DCDDF07BDBCC64B1610072087192.png","levelType":"30,50,100","spread":0.01,"tradeName":"btcusdt","risk":"0"}
     * code : 0
     * errCode : 200
     * message : 操作成功
     * total : null
     */

        /**
         * symbol : BTC/USDT
         * baseCoinScale : 2
         * baseSymbol : USDT
         * coinSymbol : USDT
         * defaultSymbol : 0
         * enable : 1
         * enableMarketSell : 1
         * enableMarketBuy : 1
         * fee : 0.002
         * flag : 0
         * maxTradingOrder : 0
         * maxVolume : 0
         * minSellPrice : 0
         * minTurnover : 0
         * minVolume : 0
         * sort : 2
         * zone : 0
         * coinScale : 4
         * newSort : -1
         * visible : 1
         * exchangeable : 1
         * maxBuyPrice : 0
         * areaId : 1
         * imgUrl : /file/20210108/ED092BC5A7724E078DCDDF07BDBCC64B1610072087192.png
         * levelType : 30,50,100
         * spread : 0.01
         * tradeName : btcusdt
         * risk : 0
         */

        private String symbol;
        private int baseCoinScale;
        private String baseSymbol;
        private String coinSymbol;
        private String defaultSymbol;
        private int enable;
        private double enableMarketSell;
        private double enableMarketBuy;
        private double fee;
        private int flag;
        private double maxTradingOrder;
        private double maxVolume;
        private double minSellPrice;
        private double minTurnover;
        private double minVolume;

    public double getEnableMarketSell() {
        return enableMarketSell;
    }

    public void setEnableMarketSell(double enableMarketSell) {
        this.enableMarketSell = enableMarketSell;
    }

    public double getEnableMarketBuy() {
        return enableMarketBuy;
    }

    public void setEnableMarketBuy(double enableMarketBuy) {
        this.enableMarketBuy = enableMarketBuy;
    }

    public double getMaxTradingOrder() {
        return maxTradingOrder;
    }

    public void setMaxTradingOrder(double maxTradingOrder) {
        this.maxTradingOrder = maxTradingOrder;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public double getMinSellPrice() {
        return minSellPrice;
    }

    public void setMinSellPrice(double minSellPrice) {
        this.minSellPrice = minSellPrice;
    }

    public double getMinTurnover() {
        return minTurnover;
    }

    public void setMinTurnover(double minTurnover) {
        this.minTurnover = minTurnover;
    }

    public double getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(double minVolume) {
        this.minVolume = minVolume;
    }

    public double getMaxBuyPrice() {
        return maxBuyPrice;
    }

    public void setMaxBuyPrice(double maxBuyPrice) {
        this.maxBuyPrice = maxBuyPrice;
    }

    private int sort;
        private int zone;
        private int coinScale;
        private int newSort;
        private int visible;
        private int exchangeable;
        private double maxBuyPrice;
        private int areaId;
        private String imgUrl;
        private String levelType;
        private double spread;
        private String tradeName;
        private String risk;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getBaseCoinScale() {
            return baseCoinScale;
        }

        public void setBaseCoinScale(int baseCoinScale) {
            this.baseCoinScale = baseCoinScale;
        }

        public String getBaseSymbol() {
            return baseSymbol;
        }

        public void setBaseSymbol(String baseSymbol) {
            this.baseSymbol = baseSymbol;
        }

        public String getCoinSymbol() {
            return coinSymbol;
        }

        public void setCoinSymbol(String coinSymbol) {
            this.coinSymbol = coinSymbol;
        }

        public String getDefaultSymbol() {
            return defaultSymbol;
        }

        public void setDefaultSymbol(String defaultSymbol) {
            this.defaultSymbol = defaultSymbol;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getZone() {
            return zone;
        }

        public void setZone(int zone) {
            this.zone = zone;
        }

        public int getCoinScale() {
            return coinScale;
        }

        public void setCoinScale(int coinScale) {
            this.coinScale = coinScale;
        }

        public int getNewSort() {
            return newSort;
        }

        public void setNewSort(int newSort) {
            this.newSort = newSort;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getExchangeable() {
            return exchangeable;
        }

        public void setExchangeable(int exchangeable) {
            this.exchangeable = exchangeable;
        }
        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLevelType() {
            return levelType;
        }

        public void setLevelType(String levelType) {
            this.levelType = levelType;
        }

        public double getSpread() {
            return spread;
        }

        public void setSpread(double spread) {
            this.spread = spread;
        }

        public String getTradeName() {
            return tradeName;
        }

        public void setTradeName(String tradeName) {
            this.tradeName = tradeName;
        }

        public String getRisk() {
            return risk;
        }

        public void setRisk(String risk) {
            this.risk = risk;
        }
    }

