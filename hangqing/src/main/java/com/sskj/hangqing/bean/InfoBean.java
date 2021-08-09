package com.sskj.hangqing.bean;

public class InfoBean {


    /**
     * name : Bitcoin
     * nameCn : 比特币
     * unit : BTC
     * status : 0
     * minTxFee : 1
     * cnyRate : 0
     * maxTxFee : 100
     * usdRate : 0
     * sgdRate : 0
     * enableRpc : 0
     * sort : 1
     * canWithdraw : 0
     * canRecharge : 0
     * canTransfer : 1
     * canAutoWithdraw : 0
     * withdrawThreshold : 10
     * minWithdrawAmount : 10
     * maxWithdrawAmount : 5000
     * isPlatformCoin : 0
     * hasLegal : false
     * allBalance : null
     * coldWalletAddress : null
     * hotAllBalance : 0
     * minerFee : 0
     * withdrawScale : 4
     * minRechargeAmount : 0
     * masterAddress : null
     * maxDailyWithdrawRate : 50000
     * imgUrl : /file/BTC@2x.png
     * releaseAmount : null
     * releaseTime : null
     * fundPrice :
     * whitePaper : null
     * website : null
     * blockQuery : null
     * coinInfo : null
     * isSettlement : false
     * burnAmount : 0
     * circulateAmount : 0
     * blockHeight : null
     * contractAddress : null
     * contractDecimals : null
     * chainType : null
     */

    private String name;
    private String nameCn;
    private String unit;
    private int status;
    private int minTxFee;
    private int cnyRate;
    private int maxTxFee;
    private int usdRate;
    private int sgdRate;
    private int enableRpc;
    private int sort;
    private int canWithdraw;
    private int canRecharge;
    private int canTransfer;
    private int canAutoWithdraw;
    private int withdrawThreshold;
    private int minWithdrawAmount;
    private int maxWithdrawAmount;
    private int isPlatformCoin;
    private boolean hasLegal;
    private String allBalance;
    private String coldWalletAddress;
    private int hotAllBalance;
    private int minerFee;
    private int withdrawScale;
    private int minRechargeAmount;
    private String masterAddress;
    private int maxDailyWithdrawRate;
    private String imgUrl;
    private String releaseAmount;
    private String releaseTime;
    private String fundPrice;
    private String whitePaper;
    private String website;
    private String blockQuery;
    private String coinInfo;
    private boolean isSettlement;
    private int burnAmount;
    private int circulateAmount;

    public int getCirculateAmount() {
        return circulateAmount;
    }

    public void setCirculateAmount(int circulateAmount) {
        this.circulateAmount = circulateAmount;
    }

    public boolean isSettlement() {
        return isSettlement;
    }

    public void setSettlement(boolean settlement) {
        isSettlement = settlement;
    }



    private String blockHeight;
    private String contractAddress;
    private String contractDecimals;
    private String chainType;

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

    public int getMinTxFee() {
        return minTxFee;
    }

    public void setMinTxFee(int minTxFee) {
        this.minTxFee = minTxFee;
    }

    public int getCnyRate() {
        return cnyRate;
    }

    public void setCnyRate(int cnyRate) {
        this.cnyRate = cnyRate;
    }

    public int getMaxTxFee() {
        return maxTxFee;
    }

    public void setMaxTxFee(int maxTxFee) {
        this.maxTxFee = maxTxFee;
    }

    public int getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(int usdRate) {
        this.usdRate = usdRate;
    }

    public int getSgdRate() {
        return sgdRate;
    }

    public void setSgdRate(int sgdRate) {
        this.sgdRate = sgdRate;
    }

    public int getEnableRpc() {
        return enableRpc;
    }

    public void setEnableRpc(int enableRpc) {
        this.enableRpc = enableRpc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(int canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public int getCanRecharge() {
        return canRecharge;
    }

    public void setCanRecharge(int canRecharge) {
        this.canRecharge = canRecharge;
    }

    public int getCanTransfer() {
        return canTransfer;
    }

    public void setCanTransfer(int canTransfer) {
        this.canTransfer = canTransfer;
    }

    public int getCanAutoWithdraw() {
        return canAutoWithdraw;
    }

    public void setCanAutoWithdraw(int canAutoWithdraw) {
        this.canAutoWithdraw = canAutoWithdraw;
    }

    public int getWithdrawThreshold() {
        return withdrawThreshold;
    }

    public void setWithdrawThreshold(int withdrawThreshold) {
        this.withdrawThreshold = withdrawThreshold;
    }

    public int getMinWithdrawAmount() {
        return minWithdrawAmount;
    }

    public void setMinWithdrawAmount(int minWithdrawAmount) {
        this.minWithdrawAmount = minWithdrawAmount;
    }

    public int getMaxWithdrawAmount() {
        return maxWithdrawAmount;
    }

    public void setMaxWithdrawAmount(int maxWithdrawAmount) {
        this.maxWithdrawAmount = maxWithdrawAmount;
    }

    public int getIsPlatformCoin() {
        return isPlatformCoin;
    }

    public void setIsPlatformCoin(int isPlatformCoin) {
        this.isPlatformCoin = isPlatformCoin;
    }

    public boolean isHasLegal() {
        return hasLegal;
    }

    public void setHasLegal(boolean hasLegal) {
        this.hasLegal = hasLegal;
    }

    public String getAllBalance() {
        return allBalance;
    }

    public void setAllBalance(String allBalance) {
        this.allBalance = allBalance;
    }

    public String getColdWalletAddress() {
        return coldWalletAddress;
    }

    public void setColdWalletAddress(String coldWalletAddress) {
        this.coldWalletAddress = coldWalletAddress;
    }

    public int getHotAllBalance() {
        return hotAllBalance;
    }

    public void setHotAllBalance(int hotAllBalance) {
        this.hotAllBalance = hotAllBalance;
    }

    public int getMinerFee() {
        return minerFee;
    }

    public void setMinerFee(int minerFee) {
        this.minerFee = minerFee;
    }

    public int getWithdrawScale() {
        return withdrawScale;
    }

    public void setWithdrawScale(int withdrawScale) {
        this.withdrawScale = withdrawScale;
    }

    public int getMinRechargeAmount() {
        return minRechargeAmount;
    }

    public void setMinRechargeAmount(int minRechargeAmount) {
        this.minRechargeAmount = minRechargeAmount;
    }

    public String getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public int getMaxDailyWithdrawRate() {
        return maxDailyWithdrawRate;
    }

    public void setMaxDailyWithdrawRate(int maxDailyWithdrawRate) {
        this.maxDailyWithdrawRate = maxDailyWithdrawRate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getReleaseAmount() {
        return releaseAmount;
    }

    public void setReleaseAmount(String releaseAmount) {
        this.releaseAmount = releaseAmount;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFundPrice() {
        return fundPrice;
    }

    public void setFundPrice(String fundPrice) {
        this.fundPrice = fundPrice;
    }

    public String getWhitePaper() {
        return whitePaper;
    }

    public void setWhitePaper(String whitePaper) {
        this.whitePaper = whitePaper;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBlockQuery() {
        return blockQuery;
    }

    public void setBlockQuery(String blockQuery) {
        this.blockQuery = blockQuery;
    }

    public String getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(String coinInfo) {
        this.coinInfo = coinInfo;
    }

    public boolean isIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(boolean isSettlement) {
        this.isSettlement = isSettlement;
    }

    public int getBurnAmount() {
        return burnAmount;
    }

    public void setBurnAmount(int burnAmount) {
        this.burnAmount = burnAmount;
    }



    public String getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractDecimals() {
        return contractDecimals;
    }

    public void setContractDecimals(String contractDecimals) {
        this.contractDecimals = contractDecimals;
    }

    public String getChainType() {
        return chainType;
    }

    public void setChainType(String chainType) {
        this.chainType = chainType;
    }
}
