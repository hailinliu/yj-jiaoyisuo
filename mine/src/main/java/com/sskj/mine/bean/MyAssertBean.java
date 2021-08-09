package com.sskj.mine.bean;

import java.util.List;

public class MyAssertBean {

        /**
         * totalUsdt : 8809.94
         * fund : [{"id":null,"stockUserId":null,"stockCode":"BTC","stockName":null,"usableFund":1,"frozen":0,"inAllFee":null,"outAllFee":null,"createTime":null,"timestamp":null,"stockType":null,"miningFund":null,"toCny":62631.5634,"toUsdt":8808.94},{"id":null,"stockUserId":null,"stockCode":"USDT","stockName":null,"usableFund":1,"inAllFee":null,"outAllFee":null,"createTime":null,"timestamp":null,"stockType":null,"miningFund":null,"toCny":7.11,"toUsdt":1},{"id":null,"stockUserId":null,"stockCode":"ETH","stockName":null,"usableFund":0,"inAllFee":null,"outAllFee":null,"createTime":null,"timestamp":null,"stockType":null,"miningFund":null,"toCny":0,"toUsdt":0}]
         * totalCny : 62638.6734
         */

        private String totalUsdt;
        private String totalCny;
        private List<FundBean> fund;

        public String getTotalUsdt() {
            return totalUsdt;
        }

        public void setTotalUsdt(String totalUsdt) {
            this.totalUsdt = totalUsdt;
        }

        public String getTotalCny() {
            return totalCny;
        }

        public void setTotalCny(String totalCny) {
            this.totalCny = totalCny;
        }

        public List<FundBean> getFund() {
            return fund;
        }

        public void setFund(List<FundBean> fund) {
            this.fund = fund;
        }

        public static class FundBean {
            /**
             * id : null
             * stockUserId : null
             * stockCode : BTC
             * stockName : null
             * usableFund : 1
             * frozen : 0
             * inAllFee : null
             * outAllFee : null
             * createTime : null
             * timestamp : null
             * stockType : null
             * miningFund : null
             * toCny : 62631.5634
             * toUsdt : 8808.94
             */

            private Object id;
            private Object stockUserId;
            private String stockCode;
            private Object stockName;
            private String usableFund;
            private String frozen;
            private Object inAllFee;
            private Object outAllFee;
            private Object createTime;
            private Object timestamp;
            private Object stockType;
            private Object miningFund;
            private String toCny;
            private String toUsdt;
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getStockUserId() {
                return stockUserId;
            }

            public void setStockUserId(Object stockUserId) {
                this.stockUserId = stockUserId;
            }

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
            }

            public Object getStockName() {
                return stockName;
            }

            public void setStockName(Object stockName) {
                this.stockName = stockName;
            }

            public String getUsableFund() {
                return usableFund;
            }

            public void setUsableFund(String usableFund) {
                this.usableFund = usableFund;
            }

            public String getFrozen() {
                return frozen;
            }

            public void setFrozen(String frozen) {
                this.frozen = frozen;
            }

            public Object getInAllFee() {
                return inAllFee;
            }

            public void setInAllFee(Object inAllFee) {
                this.inAllFee = inAllFee;
            }

            public Object getOutAllFee() {
                return outAllFee;
            }

            public void setOutAllFee(Object outAllFee) {
                this.outAllFee = outAllFee;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(Object timestamp) {
                this.timestamp = timestamp;
            }

            public Object getStockType() {
                return stockType;
            }

            public void setStockType(Object stockType) {
                this.stockType = stockType;
            }

            public Object getMiningFund() {
                return miningFund;
            }

            public void setMiningFund(Object miningFund) {
                this.miningFund = miningFund;
            }

            public String getToCny() {
                return toCny;
            }

            public void setToCny(String toCny) {
                this.toCny = toCny;
            }

            public String getToUsdt() {
                return toUsdt;
            }

            public void setToUsdt(String toUsdt) {
                this.toUsdt = toUsdt;
            }
        }
    }

