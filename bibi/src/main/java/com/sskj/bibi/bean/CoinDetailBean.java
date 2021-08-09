package com.sskj.bibi.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CoinDetailBean implements Serializable {
    @SerializedName("code")
    private Integer code;
    @SerializedName("errCode")
    private Integer errCode;
    @SerializedName("message")
    private String message;
    @SerializedName("total")
    private Object total;
    @SerializedName("data")
    private List<DataDTO> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("memberId")
        private Integer memberId;
        @SerializedName("coin")
        private CoinDTO coin;
        @SerializedName("addressList")
        private Object addressList;
        @SerializedName("canOtc")
        private Boolean canOtc;
        @SerializedName("balance")
        private String balance;
        @SerializedName("frozenBalance")
        private String frozenBalance;
        @SerializedName("address")
        private String address;
        @SerializedName("isLock")
        private Integer isLock;
        @SerializedName("releaseBalance")
        private String releaseBalance;
        @SerializedName("eqBtc")
        private Double eqBtc;
        @SerializedName("exchangeCoinList")
        private List<?> exchangeCoinList;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMemberId() {
            return memberId;
        }

        public void setMemberId(Integer memberId) {
            this.memberId = memberId;
        }

        public CoinDTO getCoin() {
            return coin;
        }

        public void setCoin(CoinDTO coin) {
            this.coin = coin;
        }

        public Object getAddressList() {
            return addressList;
        }

        public void setAddressList(Object addressList) {
            this.addressList = addressList;
        }

        public Boolean isCanOtc() {
            return canOtc;
        }

        public void setCanOtc(Boolean canOtc) {
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

        public Integer getIsLock() {
            return isLock;
        }

        public void setIsLock(Integer isLock) {
            this.isLock = isLock;
        }

        public String getReleaseBalance() {
            return releaseBalance;
        }

        public void setReleaseBalance(String releaseBalance) {
            this.releaseBalance = releaseBalance;
        }

        public Double getEqBtc() {
            return eqBtc;
        }

        public void setEqBtc(Double eqBtc) {
            this.eqBtc = eqBtc;
        }

        public List<?> getExchangeCoinList() {
            return exchangeCoinList;
        }

        public void setExchangeCoinList(List<?> exchangeCoinList) {
            this.exchangeCoinList = exchangeCoinList;
        }

        public static class CoinDTO {
            @SerializedName("name")
            private String name;
            @SerializedName("nameCn")
            private String nameCn;
            @SerializedName("unit")
            private String unit;
            @SerializedName("status")
            private Integer status;
            @SerializedName("minTxFee")
            private Integer minTxFee;
            @SerializedName("cnyRate")
            private Double cnyRate;
            @SerializedName("maxTxFee")
            private Integer maxTxFee;
            @SerializedName("usdRate")
            private Integer usdRate;
            @SerializedName("sgdRate")
            private Integer sgdRate;
            @SerializedName("enableRpc")
            private Integer enableRpc;
            @SerializedName("sort")
            private Integer sort;
            @SerializedName("canWithdraw")
            private Integer canWithdraw;
            @SerializedName("canRecharge")
            private Integer canRecharge;
            @SerializedName("canTransfer")
            private Integer canTransfer;
            @SerializedName("canAutoWithdraw")
            private Integer canAutoWithdraw;
            @SerializedName("withdrawThreshold")
            private Integer withdrawThreshold;
            @SerializedName("minWithdrawAmount")
            private Integer minWithdrawAmount;
            @SerializedName("maxWithdrawAmount")
            private Integer maxWithdrawAmount;
            @SerializedName("isPlatformCoin")
            private Integer isPlatformCoin;
            @SerializedName("hasLegal")
            private Boolean hasLegal;
            @SerializedName("allBalance")
            private Object allBalance;
            @SerializedName("coldWalletAddress")
            private Object coldWalletAddress;
            @SerializedName("hotAllBalance")
            private Integer hotAllBalance;
            @SerializedName("minerFee")
            private Object minerFee;
            @SerializedName("withdrawScale")
            private Integer withdrawScale;
            @SerializedName("minRechargeAmount")
            private Integer minRechargeAmount;
            @SerializedName("masterAddress")
            private Object masterAddress;
            @SerializedName("maxDailyWithdrawRate")
            private Integer maxDailyWithdrawRate;
            @SerializedName("imgUrl")
            private String imgUrl;
            @SerializedName("releaseAmount")
            private Object releaseAmount;
            @SerializedName("releaseTime")
            private Object releaseTime;
            @SerializedName("fundPrice")
            private Object fundPrice;
            @SerializedName("whitePaper")
            private Object whitePaper;
            @SerializedName("website")
            private Object website;
            @SerializedName("blockQuery")
            private Object blockQuery;
            @SerializedName("coinInfo")
            private Object coinInfo;
            @SerializedName("isSettlement")
            private Boolean isSettlement;
            @SerializedName("burnAmount")
            private Object burnAmount;
            @SerializedName("circulateAmount")
            private Object circulateAmount;
            @SerializedName("blockHeight")
            private Object blockHeight;
            @SerializedName("contractAddress")
            private String contractAddress;
            @SerializedName("contractDecimals")
            private Integer contractDecimals;
            @SerializedName("chainType")
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

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getMinTxFee() {
                return minTxFee;
            }

            public void setMinTxFee(Integer minTxFee) {
                this.minTxFee = minTxFee;
            }

            public Double getCnyRate() {
                return cnyRate;
            }

            public void setCnyRate(Double cnyRate) {
                this.cnyRate = cnyRate;
            }

            public Integer getMaxTxFee() {
                return maxTxFee;
            }

            public void setMaxTxFee(Integer maxTxFee) {
                this.maxTxFee = maxTxFee;
            }

            public Integer getUsdRate() {
                return usdRate;
            }

            public void setUsdRate(Integer usdRate) {
                this.usdRate = usdRate;
            }

            public Integer getSgdRate() {
                return sgdRate;
            }

            public void setSgdRate(Integer sgdRate) {
                this.sgdRate = sgdRate;
            }

            public Integer getEnableRpc() {
                return enableRpc;
            }

            public void setEnableRpc(Integer enableRpc) {
                this.enableRpc = enableRpc;
            }

            public Integer getSort() {
                return sort;
            }

            public void setSort(Integer sort) {
                this.sort = sort;
            }

            public Integer getCanWithdraw() {
                return canWithdraw;
            }

            public void setCanWithdraw(Integer canWithdraw) {
                this.canWithdraw = canWithdraw;
            }

            public Integer getCanRecharge() {
                return canRecharge;
            }

            public void setCanRecharge(Integer canRecharge) {
                this.canRecharge = canRecharge;
            }

            public Integer getCanTransfer() {
                return canTransfer;
            }

            public void setCanTransfer(Integer canTransfer) {
                this.canTransfer = canTransfer;
            }

            public Integer getCanAutoWithdraw() {
                return canAutoWithdraw;
            }

            public void setCanAutoWithdraw(Integer canAutoWithdraw) {
                this.canAutoWithdraw = canAutoWithdraw;
            }

            public Integer getWithdrawThreshold() {
                return withdrawThreshold;
            }

            public void setWithdrawThreshold(Integer withdrawThreshold) {
                this.withdrawThreshold = withdrawThreshold;
            }

            public Integer getMinWithdrawAmount() {
                return minWithdrawAmount;
            }

            public void setMinWithdrawAmount(Integer minWithdrawAmount) {
                this.minWithdrawAmount = minWithdrawAmount;
            }

            public Integer getMaxWithdrawAmount() {
                return maxWithdrawAmount;
            }

            public void setMaxWithdrawAmount(Integer maxWithdrawAmount) {
                this.maxWithdrawAmount = maxWithdrawAmount;
            }

            public Integer getIsPlatformCoin() {
                return isPlatformCoin;
            }

            public void setIsPlatformCoin(Integer isPlatformCoin) {
                this.isPlatformCoin = isPlatformCoin;
            }

            public Boolean isHasLegal() {
                return hasLegal;
            }

            public void setHasLegal(Boolean hasLegal) {
                this.hasLegal = hasLegal;
            }

            public Object getAllBalance() {
                return allBalance;
            }

            public void setAllBalance(Object allBalance) {
                this.allBalance = allBalance;
            }

            public Object getColdWalletAddress() {
                return coldWalletAddress;
            }

            public void setColdWalletAddress(Object coldWalletAddress) {
                this.coldWalletAddress = coldWalletAddress;
            }

            public Integer getHotAllBalance() {
                return hotAllBalance;
            }

            public void setHotAllBalance(Integer hotAllBalance) {
                this.hotAllBalance = hotAllBalance;
            }

            public Object getMinerFee() {
                return minerFee;
            }

            public void setMinerFee(Object minerFee) {
                this.minerFee = minerFee;
            }

            public Integer getWithdrawScale() {
                return withdrawScale;
            }

            public void setWithdrawScale(Integer withdrawScale) {
                this.withdrawScale = withdrawScale;
            }

            public Integer getMinRechargeAmount() {
                return minRechargeAmount;
            }

            public void setMinRechargeAmount(Integer minRechargeAmount) {
                this.minRechargeAmount = minRechargeAmount;
            }

            public Object getMasterAddress() {
                return masterAddress;
            }

            public void setMasterAddress(Object masterAddress) {
                this.masterAddress = masterAddress;
            }

            public Integer getMaxDailyWithdrawRate() {
                return maxDailyWithdrawRate;
            }

            public void setMaxDailyWithdrawRate(Integer maxDailyWithdrawRate) {
                this.maxDailyWithdrawRate = maxDailyWithdrawRate;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public Object getReleaseAmount() {
                return releaseAmount;
            }

            public void setReleaseAmount(Object releaseAmount) {
                this.releaseAmount = releaseAmount;
            }

            public Object getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(Object releaseTime) {
                this.releaseTime = releaseTime;
            }

            public Object getFundPrice() {
                return fundPrice;
            }

            public void setFundPrice(Object fundPrice) {
                this.fundPrice = fundPrice;
            }

            public Object getWhitePaper() {
                return whitePaper;
            }

            public void setWhitePaper(Object whitePaper) {
                this.whitePaper = whitePaper;
            }

            public Object getWebsite() {
                return website;
            }

            public void setWebsite(Object website) {
                this.website = website;
            }

            public Object getBlockQuery() {
                return blockQuery;
            }

            public void setBlockQuery(Object blockQuery) {
                this.blockQuery = blockQuery;
            }

            public Object getCoinInfo() {
                return coinInfo;
            }

            public void setCoinInfo(Object coinInfo) {
                this.coinInfo = coinInfo;
            }

            public Boolean isIsSettlement() {
                return isSettlement;
            }

            public void setIsSettlement(Boolean isSettlement) {
                this.isSettlement = isSettlement;
            }

            public Object getBurnAmount() {
                return burnAmount;
            }

            public void setBurnAmount(Object burnAmount) {
                this.burnAmount = burnAmount;
            }

            public Object getCirculateAmount() {
                return circulateAmount;
            }

            public void setCirculateAmount(Object circulateAmount) {
                this.circulateAmount = circulateAmount;
            }

            public Object getBlockHeight() {
                return blockHeight;
            }

            public void setBlockHeight(Object blockHeight) {
                this.blockHeight = blockHeight;
            }

            public String getContractAddress() {
                return contractAddress;
            }

            public void setContractAddress(String contractAddress) {
                this.contractAddress = contractAddress;
            }

            public Integer getContractDecimals() {
                return contractDecimals;
            }

            public void setContractDecimals(Integer contractDecimals) {
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

    /* *//**
     * data : [{"id":506,"memberId":43,"coin":{"name":"ADA","nameCn":"ADA","unit":"ADA","status":0,"minTxFee":1,"cnyRate":0.7,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":8,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-11","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["ADA/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":0.061716},{"id":1052,"memberId":43,"coin":{"name":"ANT","nameCn":"ANT","unit":"ANT","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-20","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["ANT/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":1.142877},{"id":331,"memberId":43,"coin":{"name":"BCH","nameCn":"BCH","unit":"BCH","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/BTC@2x.png","releaseAmount":null,"releaseTime":"2020-10-23","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["BCH/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":136.290137},{"id":336,"memberId":43,"coin":{"name":"Bitcoin","nameCn":"Bitcoin","unit":"BTC","status":0,"minTxFee":1,"cnyRate":0,"maxTxFee":100,"usdRate":0,"sgdRate":0,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":4,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/BTC@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":"","whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["BTC/USDT"],"addressList":null,"canOtc":false,"balance":10000.0388794,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":10000.038879},{"id":422,"memberId":43,"coin":{"name":"BSV","nameCn":"BSV","unit":"BSV","status":0,"minTxFee":1,"cnyRate":1026,"maxTxFee":0,"usdRate":0,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-11","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["BSV/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":63.966607},{"id":849,"memberId":43,"coin":{"name":"COMP","nameCn":"COMP","unit":"COMP","status":0,"minTxFee":1,"cnyRate":772,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-20","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["COMP/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":51.888973},{"id":590,"memberId":43,"coin":{"name":"DASH","nameCn":"DASH","unit":"DASH","status":0,"minTxFee":1,"cnyRate":460,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":12,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-11","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["DASH/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":40.553967},{"id":995,"memberId":43,"coin":{"name":"DOT","nameCn":"DOT","unit":"DOT","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-20","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["DOT/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":2.176043},{"id":332,"memberId":43,"coin":{"name":"EOS","nameCn":"EOS","unit":"EOS","status":0,"minTxFee":1,"cnyRate":0.2,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/BTC@2x.png","releaseAmount":null,"releaseTime":"2020-10-23","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["EOS/USDT"],"addressList":null,"canOtc":false,"balance":9998.8072502,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":1.051494},{"id":337,"memberId":43,"coin":{"name":"Ethereum","nameCn":"Ethereum","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":"2020-09-25","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"exchangeCoinList":["ETH/USDT"],"addressList":null,"canOtc":false,"balance":9838.2255534,"frozenBalance":0,"address":"0xe79b630e762b80ffe605c794b4790474e4bfcb46","isLock":0,"releaseBalance":0,"eqBtc":267.469493},{"id":1174,"memberId":43,"coin":{"name":"GABA","nameCn":"GABA","unit":"GABA","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":1,"sort":12,"canWithdraw":0,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201127/9326D6F12F75467EBC232A9F9EF56C7E1606460397394.png","releaseAmount":null,"releaseTime":"2020-11-27","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":"0xc6cc22f88edae6667763f7745d4ecd297d535540","contractDecimals":18,"chainType":"ERCTOKEN"},"exchangeCoinList":[],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":null},{"id":380,"memberId":43,"coin":{"name":"HT","nameCn":"HT","unit":"HT","status":0,"minTxFee":1,"cnyRate":23.94,"maxTxFee":0,"usdRate":0,"sgdRate":null,"enableRpc":0,"sort":5,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-11","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["HT/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":1.563158},{"id":904,"memberId":43,"coin":{"name":"IOTA","nameCn":"IOTA","unit":"IOTA","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":2,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-20","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["IOTA/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":0.115825},{"id":464,"memberId":43,"coin":{"name":"LINK","nameCn":"LINK","unit":"LINK","status":0,"minTxFee":10,"cnyRate":85,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":6,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201121/1E015E7C2AE244B79744A289D45E56551605926531324.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["LINK/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":4.621096},{"id":333,"memberId":43,"coin":{"name":"LTC","nameCn":"LTC","unit":"LTC","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":"2020-10-23","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["LTC/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":49.087104},{"id":334,"memberId":43,"coin":{"name":"OMG","nameCn":"OMG","unit":"OMG","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":"2020-10-23","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["OMG/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":1.032115},{"id":794,"memberId":43,"coin":{"name":"TRB","nameCn":"TRB","unit":"TRB","status":0,"minTxFee":1,"cnyRate":183,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":1,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":1,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-20","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["TRB/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":6.710533},{"id":548,"memberId":43,"coin":{"name":"TRX","nameCn":"TRX","unit":"TRX","status":0,"minTxFee":1,"cnyRate":0.16,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":12,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201121/3EADB471862C45018AF5AD686316E72B1605926515760.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["TRX/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":0.011116},{"id":338,"memberId":43,"coin":{"name":"USDT","nameCn":"USDT","unit":"USDT","status":0,"minTxFee":1,"cnyRate":6.76,"maxTxFee":100,"usdRate":0,"sgdRate":0,"enableRpc":1,"sort":2,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":1,"hasLegal":false,"allBalance":null,"coldWalletAddress":"0x2806bDA6608B0b752e0FF54344F8e6B9bf804CF4","hotAllBalance":0,"minerFee":0,"withdrawScale":4,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/USDT@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":"","whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":"0xdac17f958d2ee523a2206206994597c13d831ec7","contractDecimals":6,"chainType":"ERCTOKEN"},"exchangeCoinList":[],"addressList":null,"canOtc":false,"balance":101761.90357938818,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":3.817025},{"id":632,"memberId":43,"coin":{"name":"XMR","nameCn":"XMR","unit":"XMR","status":0,"minTxFee":1,"cnyRate":754,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":12,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201121/B56F34F9CD184C6D9EDA4D03F370F8CA1605926501440.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["XMR/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":null,"eqBtc":60.645161},{"id":335,"memberId":43,"coin":{"name":"XRP","nameCn":"XRP","unit":"XRP","status":0,"minTxFee":1,"cnyRate":1,"maxTxFee":0,"usdRate":1,"sgdRate":null,"enableRpc":0,"sort":1,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":"/file/20201120/C2F465BC0F4B455E89507639284A64EA1605874944641.jpg","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null},"exchangeCoinList":["XRP/USDT"],"addressList":null,"canOtc":false,"balance":10000,"frozenBalance":0,"address":"","isLock":0,"releaseBalance":0,"eqBtc":0.112235}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     *//*

    private int code;
    private int errCode;
    private String message;
    private String total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        *//**
         * id : 506
         * memberId : 43
         * coin : {"name":"ADA","nameCn":"ADA","unit":"ADA","status":0,"minTxFee":1,"cnyRate":0.7,"maxTxFee":0,"usdRate":null,"sgdRate":null,"enableRpc":0,"sort":8,"canWithdraw":0,"canRecharge":0,"canTransfer":1,"canAutoWithdraw":0,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":0,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":50000,"imgUrl":null,"releaseAmount":null,"releaseTime":"2020-11-11","fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":0,"circulateAmount":0,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":null}
         * exchangeCoinList : ["ADA/USDT"]
         * addressList : null
         * canOtc : false
         * balance : 10000
         * frozenBalance : 0
         * address : 
         * isLock : 0
         * releaseBalance : null
         * eqBtc : 0.061716
         *//*

        private int id;
        private int memberId;
        private CoinBean coin;
        private String addressList;
        private boolean canOtc;
        private String balance;
        private int frozenBalance;
        private String address;
        private int isLock;
        private String releaseBalance;
        private double eqBtc;
        private List<String> exchangeCoinList;

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

        public int getFrozenBalance() {
            return frozenBalance;
        }

        public void setFrozenBalance(int frozenBalance) {
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

        public List<String> getExchangeCoinList() {
            return exchangeCoinList;
        }

        public void setExchangeCoinList(List<String> exchangeCoinList) {
            this.exchangeCoinList = exchangeCoinList;
        }

        public static class CoinBean implements Serializable{
            *//**
             * name : ADA
             * nameCn : ADA
             * unit : ADA
             * status : 0
             * minTxFee : 1
             * cnyRate : 0.7
             * maxTxFee : 0
             * usdRate : null
             * sgdRate : null
             * enableRpc : 0
             * sort : 8
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
             * withdrawScale : 0
             * minRechargeAmount : 0
             * masterAddress : null
             * maxDailyWithdrawRate : 50000
             * imgUrl : null
             * releaseAmount : null
             * releaseTime : 2020-11-11
             * fundPrice : null
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
             *//*

            private String name;
            private String nameCn;
            private String unit;
            private int status;
            private String minTxFee;

            public String getMinTxFee() {
                return minTxFee;
            }

            public void setMinTxFee(String minTxFee) {
                this.minTxFee = minTxFee;
            }

            private double cnyRate;
            private String maxTxFee;
            private String usdRate;
            private String sgdRate;
            private int enableRpc;
            private int sort;
            private int canWithdraw;
            private int canRecharge;
            private int canTransfer;
            private int canAutoWithdraw;
            private String withdrawThreshold;
            private String minWithdrawAmount;
            private String maxWithdrawAmount;
            private int isPlatformCoin;
            private boolean hasLegal;
            private String allBalance;
            private String coldWalletAddress;
            private int hotAllBalance;
            private int minerFee;
            private int withdrawScale;
            private int minRechargeAmount;
            private String masterAddress;
            private String maxDailyWithdrawRate;
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



            public double getCnyRate() {
                return cnyRate;
            }

            public void setCnyRate(double cnyRate) {
                this.cnyRate = cnyRate;
            }

            public int getMaxTxFee() {
                return maxTxFee;
            }

            public void setMaxTxFee(int maxTxFee) {
                this.maxTxFee = maxTxFee;
            }

            public String getUsdRate() {
                return usdRate;
            }

            public void setUsdRate(String usdRate) {
                this.usdRate = usdRate;
            }

            public String getSgdRate() {
                return sgdRate;
            }

            public void setSgdRate(String sgdRate) {
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

            public int getCirculateAmount() {
                return circulateAmount;
            }

            public void setCirculateAmount(int circulateAmount) {
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
    }*/

}

