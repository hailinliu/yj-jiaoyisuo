package com.sskj.tibi.bean;

import com.sskj.lib.util.CoinUtil;

public class WithdrawBalanceBean {

    /**
     * useFund : 96344.0000
     * tbMinRate : 0.0000
     * tbMaxRate : 0.0000
     * tbFeeRate : 0.1000
     */

    private String useFund;
    private String tbMinRate;
    private String tbMaxRate;
    private String tbFeeRate;

    public String getUseFund() {
        return CoinUtil.keepUSDT(useFund);
    }

    public void setUseFund(String useFund) {
        this.useFund = useFund;
    }

    public String getTbMinRate() {
        return tbMinRate;
    }

    public void setTbMinRate(String tbMinRate) {
        this.tbMinRate = tbMinRate;
    }

    public String getTbMaxRate() {
        return tbMaxRate;
    }

    public void setTbMaxRate(String tbMaxRate) {
        this.tbMaxRate = tbMaxRate;
    }

    public String getTbFeeRate() {
        return tbFeeRate;
    }

    public void setTbFeeRate(String tbFeeRate) {
        this.tbFeeRate = tbFeeRate;
    }
}
