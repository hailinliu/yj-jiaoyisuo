package com.sskj.bibi.bean;

public class TotalCoinBean {

    /**
     * data : {"total":4.08586632878042E8,"walletTotal":4.0576382526212126E8,"contractTotal":2589.86723508,"otcTotal":2820217.74868569}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */
        /**
         * total : 4.08586632878042E8
         * walletTotal : 4.0576382526212126E8
         * contractTotal : 2589.86723508
         * otcTotal : 2820217.74868569
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

