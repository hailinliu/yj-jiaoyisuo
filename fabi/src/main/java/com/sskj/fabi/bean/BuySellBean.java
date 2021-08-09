package com.sskj.fabi.bean;

import java.io.Serializable;
import java.util.List;

public class BuySellBean {

    /**
     * data : {"context":[{"memberName":"康康","avatar":null,"advertiseId":4,"transactions":2,"price":6.5,"minLimit":500,"maxLimit":20000,"remainAmount":999055.46153847,"createTime":"2020-11-28 17:15:07","payMode":"支付宝,微信","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":0,"advType":0,"premiseRate":null,"top":"0","verifyLevel":null,"memberId":43,"successCount30":3,"successRete30":0.0967,"otcFeeRate":0,"remark":"测试","number":1000000,"timeLimit":30}],"currentPage":1,"totalPage":1,"pageNumber":10,"totalElement":1}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */
        /**
         * context : [{"memberName":"康康","avatar":null,"advertiseId":4,"transactions":2,"price":6.5,"minLimit":500,"maxLimit":20000,"remainAmount":999055.46153847,"createTime":"2020-11-28 17:15:07","payMode":"支付宝,微信","payModeCn":null,"coinId":13,"unit":"USDT","coinName":"USDT","coinNameCn":"泰达币","level":1,"advertiseType":0,"advType":0,"premiseRate":null,"top":"0","verifyLevel":null,"memberId":43,"successCount30":3,"successRete30":0.0967,"otcFeeRate":0,"remark":"测试","number":1000000,"timeLimit":30}]
         * currentPage : 1
         * totalPage : 1
         * pageNumber : 10
         * totalElement : 1
         */

        private String currentPage;
        private String totalPage;
        private String pageNumber;
        private String totalElement;
        private List<ContextBean> context;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(String pageNumber) {
            this.pageNumber = pageNumber;
        }

        public String getTotalElement() {
            return totalElement;
        }

        public void setTotalElement(String totalElement) {
            this.totalElement = totalElement;
        }

        public List<ContextBean> getContext() {
            return context;
        }

        public void setContext(List<ContextBean> context) {
            this.context = context;
        }

        public static class ContextBean implements Serializable{
            /**
             * memberName : 康康
             * avatar : null
             * advertiseId : 4
             * transactions : 2
             * price : 6.5
             * minLimit : 500
             * maxLimit : 20000
             * remainAmount : 999055.46153847
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
             * top : 0
             * verifyLevel : null
             * memberId : 43
             * successCount30 : 3
             * successRete30 : 0.0967
             * otcFeeRate : 0
             * remark : 测试
             * number : 1000000
             * timeLimit : 30
             */

            private String memberName;
            private String avatar;
            private String advertiseId;
            private String transactions;
            private double price;
            private String minLimit;
            private String maxLimit;
            private double remainAmount;
            private String createTime;
            private String payMode;
            private String payModeCn;
            private String coinId;
            private String unit;
            private String coinName;
            private String coinNameCn;
            private String level;
            private String advertiseType;
            private String advType;
            private String premiseRate;
            private String top;
            private String verifyLevel;
            private String memberId;
            private String successCount30;
            private double successRete30;
            private String otcFeeRate;
            private String remark;
            private String number;
            private String timeLimit;

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

            public String getAdvertiseId() {
                return advertiseId;
            }

            public void setAdvertiseId(String advertiseId) {
                this.advertiseId = advertiseId;
            }

            public String getTransactions() {
                return transactions;
            }

            public void setTransactions(String transactions) {
                this.transactions = transactions;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getMinLimit() {
                return minLimit;
            }

            public void setMinLimit(String minLimit) {
                this.minLimit = minLimit;
            }

            public String getMaxLimit() {
                return maxLimit;
            }

            public void setMaxLimit(String maxLimit) {
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

            public String getCoinId() {
                return coinId;
            }

            public void setCoinId(String coinId) {
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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAdvertiseType() {
                return advertiseType;
            }

            public void setAdvertiseType(String advertiseType) {
                this.advertiseType = advertiseType;
            }

            public String getAdvType() {
                return advType;
            }

            public void setAdvType(String advType) {
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

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getSuccessCount30() {
                return successCount30;
            }

            public void setSuccessCount30(String successCount30) {
                this.successCount30 = successCount30;
            }

            public double getSuccessRete30() {
                return successRete30;
            }

            public void setSuccessRete30(double successRete30) {
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

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getTimeLimit() {
                return timeLimit;
            }

            public void setTimeLimit(String timeLimit) {
                this.timeLimit = timeLimit;
            }
        }
    }

