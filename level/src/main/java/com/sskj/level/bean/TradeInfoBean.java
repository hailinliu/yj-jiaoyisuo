package com.sskj.level.bean;

public class TradeInfoBean {

    /**
     * id : 1
     * account : 13523504011
     * allFound : 98558.0
     * usableFund : 98561.5
     * hasUsedFund : 1.5
     * proportion : 0.0
     * blowingUprate : 0
     * floatWinLoseFee : -5.0
     * closeWinLoseFee : -5.0
     */

    private String id;
    private String account;
    private String allFound;//动态权益资金
    private String usableFund;//可用资金
    private String hasUsedFund;//冻结资金
    private String proportion;//风险度
    private String blowingUprate;//爆仓率
    private String floatWinLoseFee;//浮动盈亏
    private String closeWinLoseFee;//平仓盈亏

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAllFound() {
        return allFound;
    }

    public void setAllFound(String allFound) {
        this.allFound = allFound;
    }

    public String getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(String usableFund) {
        this.usableFund = usableFund;
    }

    public String getHasUsedFund() {
        return hasUsedFund;
    }

    public void setHasUsedFund(String hasUsedFund) {
        this.hasUsedFund = hasUsedFund;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getBlowingUprate() {
        return Double.valueOf(blowingUprate)*100+"";
    }

    public void setBlowingUprate(String blowingUprate) {
        this.blowingUprate = blowingUprate;
    }

    public String getFloatWinLoseFee() {
        return floatWinLoseFee;
    }

    public void setFloatWinLoseFee(String floatWinLoseFee) {
        this.floatWinLoseFee = floatWinLoseFee;
    }

    public String getCloseWinLoseFee() {
        return closeWinLoseFee;
    }

    public void setCloseWinLoseFee(String closeWinLoseFee) {
        this.closeWinLoseFee = closeWinLoseFee;
    }
}
