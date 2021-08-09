package com.sskj.fabi.bean;

import java.io.Serializable;
import java.util.List;

public class MerchantCenterBean implements Serializable {

    /**
     * data : {"buy":[{"memberName":"康康","avatar":null,"advertiseId":4,"transactions":2,"price":6.5,"minLimit":500,"maxLimit":20000,"remainAmount":997786.23076924,"createTime":"2020-11-28 17:15:07","payMode":"支付宝,微信","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":0,"advType":0,"premiseRate":null,"top":null,"verifyLevel":null,"memberId":0,"successCount30":0,"successRete30":null,"otcFeeRate":null,"remark":null,"number":1000000,"timeLimit":30,"country":"CNY"}],"sell":[{"memberName":"康康","avatar":null,"advertiseId":3,"transactions":2,"price":100,"minLimit":100,"maxLimit":1000,"remainAmount":890.0156,"createTime":"2020-11-27 16:10:31","payMode":"银联,微信,支付宝","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":1,"advType":0,"premiseRate":null,"top":null,"verifyLevel":null,"memberId":0,"successCount30":0,"successRete30":null,"otcFeeRate":null,"remark":null,"number":1000,"timeLimit":50,"country":"CNY"}],"username":"康康","avatar":null,"realVerified":1,"emailVerified":1,"phoneVerified":0,"transactions":2,"createTime":"2020-11-11 15:41:43"}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

  
        /**
         * buy : [{"memberName":"康康","avatar":null,"advertiseId":4,"transactions":2,"price":6.5,"minLimit":500,"maxLimit":20000,"remainAmount":997786.23076924,"createTime":"2020-11-28 17:15:07","payMode":"支付宝,微信","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":0,"advType":0,"premiseRate":null,"top":null,"verifyLevel":null,"memberId":0,"successCount30":0,"successRete30":null,"otcFeeRate":null,"remark":null,"number":1000000,"timeLimit":30,"country":"CNY"}]
         * sell : [{"memberName":"康康","avatar":null,"advertiseId":3,"transactions":2,"price":100,"minLimit":100,"maxLimit":1000,"remainAmount":890.0156,"createTime":"2020-11-27 16:10:31","payMode":"银联,微信,支付宝","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":1,"advType":0,"premiseRate":null,"top":null,"verifyLevel":null,"memberId":0,"successCount30":0,"successRete30":null,"otcFeeRate":null,"remark":null,"number":1000,"timeLimit":50,"country":"CNY"}]
         * username : 康康
         * avatar : null
         * realVerified : 1
         * emailVerified : 1
         * phoneVerified : 0
         * transactions : 2
         * createTime : 2020-11-11 15:41:43
         */

        private String username;
        private String avatar;
        private int realVerified;
        private int emailVerified;
        private int phoneVerified;
        private int googleVerified;
        private int transactions;
        private int successCount30;
        private String successRete30;

    public int getSuccessCount30() {
        return successCount30;
    }

    public void setSuccessCount30(int successCount30) {
        this.successCount30 = successCount30;
    }

    public String getSuccessRete30() {
        return successRete30;
    }

    public void setSuccessRete30(String successRete30) {
        this.successRete30 = successRete30;
    }

    private String createTime;
        private List<BuyBean> buy;
        private List<SellBean> sell;

    public int getGoogleVerified() {
        return googleVerified;
    }

    public void setGoogleVerified(int googleVerified) {
        this.googleVerified = googleVerified;
    }

    public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getRealVerified() {
            return realVerified;
        }

        public void setRealVerified(int realVerified) {
            this.realVerified = realVerified;
        }

        public int getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(int emailVerified) {
            this.emailVerified = emailVerified;
        }

        public int getPhoneVerified() {
            return phoneVerified;
        }

        public void setPhoneVerified(int phoneVerified) {
            this.phoneVerified = phoneVerified;
        }

        public int getTransactions() {
            return transactions;
        }

        public void setTransactions(int transactions) {
            this.transactions = transactions;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<BuyBean> getBuy() {
            return buy;
        }

        public void setBuy(List<BuyBean> buy) {
            this.buy = buy;
        }

        public List<SellBean> getSell() {
            return sell;
        }

        public void setSell(List<SellBean> sell) {
            this.sell = sell;
        }

        public static class BuyBean implements Serializable{
            /**
             * memberName : 康康
             * avatar : null
             * advertiseId : 4
             * transactions : 2
             * price : 6.5
             * minLimit : 500
             * maxLimit : 20000
             * remainAmount : 997786.23076924
             * createTime : 2020-11-28 17:15:07
             * payMode : 支付宝,微信
             * payModeCn : null
             * coinId : 13
             * unit : USDT
             * coinName : USDT
             * coinNameCn : 泰达币
             * level : 1
             * advertiseType : 0
             * advType : 0
             * premiseRate : null
             * top : null
             * verifyLevel : null
             * memberId : 0
             * successCount30 : 0
             * successRete30 : null
             * otcFeeRate : null
             * remark : null
             * number : 1000000
             * timeLimit : 30
             * country : CNY
             */

            private String memberName;
            private String avatar;
            private int advertiseId;
            private int transactions;
            private double price;
            private int minLimit;
            private int maxLimit;
            private double remainAmount;
            private String createTime;
            private String payMode;
            private String payModeCn;
            private int coinId;
            private String unit;
            private String coinName;
            private String coinNameCn;
            private int level;
            private int advertiseType;
            private int advType;
            private String premiseRate;
            private String top;
            private String verifyLevel;
            private int memberId;
            private int successCount30;
            private String successRete30;
            private String otcFeeRate;
            private String remark;
            private int number;
            private int timeLimit;
            private String country;

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getAdvertiseId() {
                return advertiseId;
            }

            public void setAdvertiseId(int advertiseId) {
                this.advertiseId = advertiseId;
            }

            public int getTransactions() {
                return transactions;
            }

            public void setTransactions(int transactions) {
                this.transactions = transactions;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getMinLimit() {
                return minLimit;
            }

            public void setMinLimit(int minLimit) {
                this.minLimit = minLimit;
            }

            public int getMaxLimit() {
                return maxLimit;
            }

            public void setMaxLimit(int maxLimit) {
                this.maxLimit = maxLimit;
            }

            public double getRemainAmount() {
                return remainAmount;
            }

            public void setRemainAmount(double remainAmount) {
                this.remainAmount = remainAmount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPayMode() {
                return payMode;
            }

            public void setPayMode(String payMode) {
                this.payMode = payMode;
            }

            public String getPayModeCn() {
                return payModeCn;
            }

            public void setPayModeCn(String payModeCn) {
                this.payModeCn = payModeCn;
            }

            public int getCoinId() {
                return coinId;
            }

            public void setCoinId(int coinId) {
                this.coinId = coinId;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCoinName() {
                return coinName;
            }

            public void setCoinName(String coinName) {
                this.coinName = coinName;
            }

            public String getCoinNameCn() {
                return coinNameCn;
            }

            public void setCoinNameCn(String coinNameCn) {
                this.coinNameCn = coinNameCn;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getAdvertiseType() {
                return advertiseType;
            }

            public void setAdvertiseType(int advertiseType) {
                this.advertiseType = advertiseType;
            }

            public int getAdvType() {
                return advType;
            }

            public void setAdvType(int advType) {
                this.advType = advType;
            }

            public String getPremiseRate() {
                return premiseRate;
            }

            public void setPremiseRate(String premiseRate) {
                this.premiseRate = premiseRate;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getVerifyLevel() {
                return verifyLevel;
            }

            public void setVerifyLevel(String verifyLevel) {
                this.verifyLevel = verifyLevel;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getSuccessCount30() {
                return successCount30;
            }

            public void setSuccessCount30(int successCount30) {
                this.successCount30 = successCount30;
            }

            public String getSuccessRete30() {
                return successRete30;
            }

            public void setSuccessRete30(String successRete30) {
                this.successRete30 = successRete30;
            }

            public String getOtcFeeRate() {
                return otcFeeRate;
            }

            public void setOtcFeeRate(String otcFeeRate) {
                this.otcFeeRate = otcFeeRate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(int timeLimit) {
                this.timeLimit = timeLimit;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }

        public static class SellBean implements Serializable{
            /**
             * memberName : 康康
             * avatar : null
             * advertiseId : 3
             * transactions : 2
             * price : 100
             * minLimit : 100
             * maxLimit : 1000
             * remainAmount : 890.0156
             * createTime : 2020-11-27 16:10:31
             * payMode : 银联,微信,支付宝
             * payModeCn : null
             * coinId : 13
             * unit : USDT
             * coinName : USDT
             * coinNameCn : 泰达币
             * level : 1
             * advertiseType : 1
             * advType : 0
             * premiseRate : null
             * top : null
             * verifyLevel : null
             * memberId : 0
             * successCount30 : 0
             * successRete30 : null
             * otcFeeRate : null
             * remark : null
             * number : 1000
             * timeLimit : 50
             * country : CNY
             */

            private String memberName;
            private String avatar;
            private int advertiseId;
            private int transactions;
            private int price;
            private int minLimit;
            private int maxLimit;
            private double remainAmount;
            private String createTime;
            private String payMode;
            private String payModeCn;
            private int coinId;
            private String unit;
            private String coinName;
            private String coinNameCn;
            private int level;
            private int advertiseType;
            private int advType;
            private String premiseRate;
            private String top;
            private String verifyLevel;
            private int memberId;
            private int successCount30;
            private String successRete30;
            private String otcFeeRate;
            private String remark;
            private int number;
            private int timeLimit;
            private String country;

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getAdvertiseId() {
                return advertiseId;
            }

            public void setAdvertiseId(int advertiseId) {
                this.advertiseId = advertiseId;
            }

            public int getTransactions() {
                return transactions;
            }

            public void setTransactions(int transactions) {
                this.transactions = transactions;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getMinLimit() {
                return minLimit;
            }

            public void setMinLimit(int minLimit) {
                this.minLimit = minLimit;
            }

            public int getMaxLimit() {
                return maxLimit;
            }

            public void setMaxLimit(int maxLimit) {
                this.maxLimit = maxLimit;
            }

            public double getRemainAmount() {
                return remainAmount;
            }

            public void setRemainAmount(double remainAmount) {
                this.remainAmount = remainAmount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPayMode() {
                return payMode;
            }

            public void setPayMode(String payMode) {
                this.payMode = payMode;
            }

            public String getPayModeCn() {
                return payModeCn;
            }

            public void setPayModeCn(String payModeCn) {
                this.payModeCn = payModeCn;
            }

            public int getCoinId() {
                return coinId;
            }

            public void setCoinId(int coinId) {
                this.coinId = coinId;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCoinName() {
                return coinName;
            }

            public void setCoinName(String coinName) {
                this.coinName = coinName;
            }

            public String getCoinNameCn() {
                return coinNameCn;
            }

            public void setCoinNameCn(String coinNameCn) {
                this.coinNameCn = coinNameCn;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getAdvertiseType() {
                return advertiseType;
            }

            public void setAdvertiseType(int advertiseType) {
                this.advertiseType = advertiseType;
            }

            public int getAdvType() {
                return advType;
            }

            public void setAdvType(int advType) {
                this.advType = advType;
            }

            public String getPremiseRate() {
                return premiseRate;
            }

            public void setPremiseRate(String premiseRate) {
                this.premiseRate = premiseRate;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getVerifyLevel() {
                return verifyLevel;
            }

            public void setVerifyLevel(String verifyLevel) {
                this.verifyLevel = verifyLevel;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getSuccessCount30() {
                return successCount30;
            }

            public void setSuccessCount30(int successCount30) {
                this.successCount30 = successCount30;
            }

            public String getSuccessRete30() {
                return successRete30;
            }

            public void setSuccessRete30(String successRete30) {
                this.successRete30 = successRete30;
            }

            public String getOtcFeeRate() {
                return otcFeeRate;
            }

            public void setOtcFeeRate(String otcFeeRate) {
                this.otcFeeRate = otcFeeRate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(int timeLimit) {
                this.timeLimit = timeLimit;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }
    }

