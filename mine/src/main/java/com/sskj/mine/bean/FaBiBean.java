package com.sskj.mine.bean;

import java.util.List;

public class FaBiBean {

    /**
     * data : [{"id":129,"memberId":69,"eqBtc":0,"balance":0,"frozenBalance":0,"releaseBalance":0,"isLock":0,"coin":{"id":12,"name":"Bitcoin","nameCn":"比特币","unit":"BTC","status":0,"jyRate":0,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/5250C4CDC757451EB0D8A8C048BB5F9F1605873237966.jpg"}},{"id":130,"memberId":69,"eqBtc":0,"balance":0,"frozenBalance":0,"releaseBalance":0,"isLock":0,"coin":{"id":13,"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"jyRate":1,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg"}},{"id":131,"memberId":69,"eqBtc":0,"balance":0,"frozenBalance":0,"releaseBalance":0,"isLock":0,"coin":{"id":14,"name":"EOS","nameCn":"EOS","unit":"EOS","status":0,"jyRate":0,"sellMinAmount":100,"buyMinAmount":10000,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/661E6ED6F6A844D9B7DB093913136A7C1605875209785.jpg"}},{"id":226,"memberId":69,"eqBtc":0,"balance":0,"frozenBalance":0,"releaseBalance":null,"isLock":0,"coin":{"id":16,"name":"ETH","nameCn":"ETH","unit":"ETH","status":0,"jyRate":0,"sellMinAmount":1,"buyMinAmount":1,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201226/9A74CDE755D442C698792BA0FC6EF61A1608948613208.png"}}]
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
         * id : 129
         * memberId : 69
         * eqBtc : 0
         * balance : 0
         * frozenBalance : 0
         * releaseBalance : 0
         * isLock : 0
         * coin : {"id":12,"name":"Bitcoin","nameCn":"比特币","unit":"BTC","status":0,"jyRate":0,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/5250C4CDC757451EB0D8A8C048BB5F9F1605873237966.jpg"}
         */

        private int id;
        private int memberId;
        private int eqBtc;
        private String balance;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        private int frozenBalance;
        private int releaseBalance;
        private int isLock;
        private CoinBean coin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getEqBtc() {
            return eqBtc;
        }

        public void setEqBtc(int eqBtc) {
            this.eqBtc = eqBtc;
        }



        public int getFrozenBalance() {
            return frozenBalance;
        }

        public void setFrozenBalance(int frozenBalance) {
            this.frozenBalance = frozenBalance;
        }

        public int getReleaseBalance() {
            return releaseBalance;
        }

        public void setReleaseBalance(int releaseBalance) {
            this.releaseBalance = releaseBalance;
        }

        public int getIsLock() {
            return isLock;
        }

        public void setIsLock(int isLock) {
            this.isLock = isLock;
        }

        public CoinBean getCoin() {
            return coin;
        }

        public void setCoin(CoinBean coin) {
            this.coin = coin;
        }

        public static class CoinBean {
            /**
             * id : 12
             * name : Bitcoin
             * nameCn : 比特币
             * unit : BTC
             * status : 0
             * jyRate : 0
             * sellMinAmount : 100
             * buyMinAmount : 0.01
             * sort : 0
             * isPlatformCoin : 0
             * coinScale : 8
             * maxTradingTime : 0
             * maxVolume : 0
             * usdRate : null
             * cnyRate : null
             * imgUrl : /file/20201120/5250C4CDC757451EB0D8A8C048BB5F9F1605873237966.jpg
             */

            private int id;
            private String name;
            private String nameCn;
            private String unit;
            private int status;
            private int jyRate;
            private int sellMinAmount;
            private double buyMinAmount;
            private int sort;
            private int isPlatformCoin;
            private int coinScale;
            private int maxTradingTime;
            private int maxVolume;
            private Object usdRate;
            private Object cnyRate;
            private String imgUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameCn() {
                return nameCn;
            }

            public void setNameCn(String nameCn) {
                this.nameCn = nameCn;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getJyRate() {
                return jyRate;
            }

            public void setJyRate(int jyRate) {
                this.jyRate = jyRate;
            }

            public int getSellMinAmount() {
                return sellMinAmount;
            }

            public void setSellMinAmount(int sellMinAmount) {
                this.sellMinAmount = sellMinAmount;
            }

            public double getBuyMinAmount() {
                return buyMinAmount;
            }

            public void setBuyMinAmount(double buyMinAmount) {
                this.buyMinAmount = buyMinAmount;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIsPlatformCoin() {
                return isPlatformCoin;
            }

            public void setIsPlatformCoin(int isPlatformCoin) {
                this.isPlatformCoin = isPlatformCoin;
            }

            public int getCoinScale() {
                return coinScale;
            }

            public void setCoinScale(int coinScale) {
                this.coinScale = coinScale;
            }

            public int getMaxTradingTime() {
                return maxTradingTime;
            }

            public void setMaxTradingTime(int maxTradingTime) {
                this.maxTradingTime = maxTradingTime;
            }

            public int getMaxVolume() {
                return maxVolume;
            }

            public void setMaxVolume(int maxVolume) {
                this.maxVolume = maxVolume;
            }

            public Object getUsdRate() {
                return usdRate;
            }

            public void setUsdRate(Object usdRate) {
                this.usdRate = usdRate;
            }

            public Object getCnyRate() {
                return cnyRate;
            }

            public void setCnyRate(Object cnyRate) {
                this.cnyRate = cnyRate;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
