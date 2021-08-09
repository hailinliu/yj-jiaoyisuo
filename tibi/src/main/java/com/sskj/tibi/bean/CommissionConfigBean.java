package com.sskj.tibi.bean;

public class CommissionConfigBean {

    /**
     * withdraw_min : 500
     * withdraw_max : 5000
     */

    private String withdraw_min;
    private String withdraw_max;

    public String getWithdraw_min() {
        return withdraw_min;
    }

    public void setWithdraw_min(String withdraw_min) {
        this.withdraw_min = withdraw_min;
    }

    public String getWithdraw_max() {
        return withdraw_max;
    }

    public void setWithdraw_max(String withdraw_max) {
        this.withdraw_max = withdraw_max;
    }
}
