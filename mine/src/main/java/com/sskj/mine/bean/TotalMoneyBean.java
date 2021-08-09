package com.sskj.mine.bean;

public class TotalMoneyBean {

    /**
     * data : {"total":1.0E10,"walletTotal":0,"contractTotal":1.0E10,"otcTotal":0}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */


        /**
         * total : 1.0E10
         * walletTotal : 0
         * contractTotal : 1.0E10
         * otcTotal : 0
         */

        private String total;
        private String walletTotal;
        private String contractTotal;
        private String otcTotal;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getWalletTotal() {
            return walletTotal;
        }

        public void setWalletTotal(String walletTotal) {
            this.walletTotal = walletTotal;
        }

        public String getContractTotal() {
            return contractTotal;
        }

        public void setContractTotal(String contractTotal) {
            this.contractTotal = contractTotal;
        }

        public String getOtcTotal() {
            return otcTotal;
        }

        public void setOtcTotal(String otcTotal) {
            this.otcTotal = otcTotal;
        }
    }

