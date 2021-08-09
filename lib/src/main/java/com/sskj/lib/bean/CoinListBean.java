package com.sskj.lib.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoinListBean implements Serializable {


    /**
     * data : [{"id":1959,"memberId":93,"coin":{"name":"Ethereum","nameCn":"Ethereum","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"exchangeCoinList":["ETH/USDT"],"addressList":null,"canOtc":false,"balance":"16.9753812","frozenBalance":"0","address":"","isLock":0,"releaseBalance":"0","eqBtc":0.693981},{"id":1968,"memberId":93,"coin":{"name":"GABA","nameCn":"GABA","unit":"GABA","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":1,"sort":12,"canWithdraw":0,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201127/9326D6F12F75467EBC232A9F9EF56C7E1606460397394.png","releaseAmount":null,"releaseTime":"2020-11-27","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":"0xc6cc22f88edae6667763f7745d4ecd297d535540","contractDecimals":18,"chainType":"ERCTOKEN"},"exchangeCoinList":["GABA/USDT"],"addressList":null,"canOtc":false,"balance":"0","frozenBalance":"0","address":"","isLock":0,"releaseBalance":"0","eqBtc":0},{"id":1961,"memberId":93,"coin":{"name":"USDT","nameCn":"USDT","unit":"USDT","status":0,"minTxFee":1,"cnyRate":6.76,"maxTxFee":100,"usdRate":0,"sgdRate":0,"enableRpc":1,"sort":2,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":1,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":null,"imgUrl":"/file/20210119/607262769947493C8213411F75F7E8571611045540873.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":"0xdac17f958d2ee523a2206206994597c13d831ec7","contractDecimals":6,"chainType":"ERCTOKEN"},"exchangeCoinList":[],"addressList":null,"canOtc":false,"balance":"9872186.973287","frozenBalance":"0","address":"","isLock":0,"releaseBalance":"0","eqBtc":317.271356}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private int code;
    private int errCode;
    private String message;
    private String total;
    private ArrayList<DataBean> data;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1959
         * memberId : 93
         * coin : {"name":"Ethereum","nameCn":"Ethereum","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"}
         * exchangeCoinList : ["ETH/USDT"]
         * addressList : null
         * canOtc : false
         * balance : 16.9753812
         * frozenBalance : 0
         * address : 
         * isLock : 0
         * releaseBalance : 0
         * eqBtc : 0.693981
         */

        private int id;
        private int memberId;
        private CoinBean coin;
        private String addressList;
        private boolean canOtc;
        private String balance;
        private String frozenBalance;
        private String address;
        private int isLock;
        private String releaseBalance;
        private double eqBtc;
        private ArrayList<String> exchangeCoinList;

        public ArrayList<String> getExchangeCoinList() {
            return exchangeCoinList;
        }

        public void setExchangeCoinList(ArrayList<String> exchangeCoinList) {
            this.exchangeCoinList = exchangeCoinList;
        }

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

        public CoinBean getCoin() {
            return coin;
        }

        public void setCoin(CoinBean coin) {
            this.coin = coin;
        }

        public String getAddressList() {
            return addressList;
        }

        public void setAddressList(String addressList) {
            this.addressList = addressList;
        }

        public boolean isCanOtc() {
            return canOtc;
        }

        public void setCanOtc(boolean canOtc) {
            this.canOtc = canOtc;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFrozenBalance() {
            return frozenBalance;
        }

        public void setFrozenBalance(String frozenBalance) {
            this.frozenBalance = frozenBalance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIsLock() {
            return isLock;
        }

        public void setIsLock(int isLock) {
            this.isLock = isLock;
        }

        public String getReleaseBalance() {
            return releaseBalance;
        }

        public void setReleaseBalance(String releaseBalance) {
            this.releaseBalance = releaseBalance;
        }

        public double getEqBtc() {
            return eqBtc;
        }

        public void setEqBtc(double eqBtc) {
            this.eqBtc = eqBtc;
        }


        public static class CoinBean implements Serializable{
            /**
             * name : Ethereum
             * nameCn : Ethereum
             * unit : ETH
             * status : 0
             * minTxFee : 1
             * cnyRate : 2000
             * maxTxFee : 0
             * usdRate : 300
             * sgdRate : null
             * enableRpc : 1
             * sort : 1
             * canWithdraw : 1
             * canRecharge : 1
             * canTransfer : 1
             * canAutoWithdraw : null
             * withdrawThreshold : 10
             * minWithdrawAmount : 10
             * maxWithdrawAmount : 5000
             * isPlatformCoin : 0
             * hasLegal : false
             * allBalance : null
             * coldWalletAddress : null
             * hotAllBalance : 0
             * minerFee : null
             * withdrawScale : 0
             * minRechargeAmount : 0
             * masterAddress : null
             * maxDailyWithdrawRate : 5000
             * imgUrl : /file/ETH@2x.png
             * releaseAmount : null
             * releaseTime : null
             * fundPrice : null
             * whitePaper : null
             * website : null
             * blockQuery : null
             * coinInfo : null
             * isSettlement : false
             * burnAmount : null
             * circulateAmount : null
             * blockHeight : null
             * contractAddress : null
             * contractDecimals : null
             * chainType : ERCTOKEN
             */

            private String name;
            private String nameCn;
            private String unit;
            private int status;
            private double minTxFee;
            private double cnyRate;
            private double maxTxFee;
            private double usdRate;
            private String sgdRate;
            private double enableRpc;
            private int sort;
            private double canWithdraw;
            private double canRecharge;
            private double canTransfer;
            private String canAutoWithdraw;
            private int withdrawThreshold;
            private double minWithdrawAmount;
            private double maxWithdrawAmount;
            private int isPlatformCoin;
            private boolean hasLegal;
            private String allBalance;
            private String coldWalletAddress;
            private int hotAllBalance;
            private String minerFee;
            private int withdrawScale;
            private double minRechargeAmount;
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
            private String burnAmount;
            private String circulateAmount;
            private String blockHeight;
            private String contractAddress;
            private String contractDecimals;
            private String chainType;

            public double getMinTxFee() {
                return minTxFee;
            }

            public void setMinTxFee(double minTxFee) {
                this.minTxFee = minTxFee;
            }

            public double getCnyRate() {
                return cnyRate;
            }

            public void setCnyRate(double cnyRate) {
                this.cnyRate = cnyRate;
            }

            public double getMaxTxFee() {
                return maxTxFee;
            }

            public void setMaxTxFee(double maxTxFee) {
                this.maxTxFee = maxTxFee;
            }

            public double getUsdRate() {
                return usdRate;
            }

            public void setUsdRate(double usdRate) {
                this.usdRate = usdRate;
            }

            public double getEnableRpc() {
                return enableRpc;
            }

            public void setEnableRpc(double enableRpc) {
                this.enableRpc = enableRpc;
            }

            public double getCanWithdraw() {
                return canWithdraw;
            }

            public void setCanWithdraw(double canWithdraw) {
                this.canWithdraw = canWithdraw;
            }

            public double getCanRecharge() {
                return canRecharge;
            }

            public void setCanRecharge(double canRecharge) {
                this.canRecharge = canRecharge;
            }

            public double getCanTransfer() {
                return canTransfer;
            }

            public void setCanTransfer(double canTransfer) {
                this.canTransfer = canTransfer;
            }

            public double getMinWithdrawAmount() {
                return minWithdrawAmount;
            }

            public void setMinWithdrawAmount(double minWithdrawAmount) {
                this.minWithdrawAmount = minWithdrawAmount;
            }

            public double getMaxWithdrawAmount() {
                return maxWithdrawAmount;
            }

            public void setMaxWithdrawAmount(double maxWithdrawAmount) {
                this.maxWithdrawAmount = maxWithdrawAmount;
            }

            public double getMinRechargeAmount() {
                return minRechargeAmount;
            }

            public void setMinRechargeAmount(double minRechargeAmount) {
                this.minRechargeAmount = minRechargeAmount;
            }

            public boolean isSettlement() {
                return isSettlement;
            }

            public void setSettlement(boolean settlement) {
                isSettlement = settlement;
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


            public String getSgdRate() {
                return sgdRate;
            }

            public void setSgdRate(String sgdRate) {
                this.sgdRate = sgdRate;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }


            public String getCanAutoWithdraw() {
                return canAutoWithdraw;
            }

            public void setCanAutoWithdraw(String canAutoWithdraw) {
                this.canAutoWithdraw = canAutoWithdraw;
            }

            public int getWithdrawThreshold() {
                return withdrawThreshold;
            }

            public void setWithdrawThreshold(int withdrawThreshold) {
                this.withdrawThreshold = withdrawThreshold;
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

            public String getMinerFee() {
                return minerFee;
            }

            public void setMinerFee(String minerFee) {
                this.minerFee = minerFee;
            }

            public int getWithdrawScale() {
                return withdrawScale;
            }

            public void setWithdrawScale(int withdrawScale) {
                this.withdrawScale = withdrawScale;
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

            public String getBurnAmount() {
                return burnAmount;
            }

            public void setBurnAmount(String burnAmount) {
                this.burnAmount = burnAmount;
            }

            public String getCirculateAmount() {
                return circulateAmount;
            }

            public void setCirculateAmount(String circulateAmount) {
                this.circulateAmount = circulateAmount;
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
    }
}
