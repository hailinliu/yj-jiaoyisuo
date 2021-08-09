package com.sskj.fabi.bean;

import com.sskj.fabi.http.HttpConfig;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;
import java.util.List;

public class OrderDetailBean {

    /**
     * id : 57
     * entrustNo : 1561345601533644cwjw7e
     * dealEntrustNo : 15614517216122601ztt5w
     * sellId : 187
     * buyId : 197
     * price : 6.91
     * totalNum : 2.1708
     * totalPrice : 15
     * fee : 0.217
     * payType : 1,2
     * refer : 589757
     * type : 1
     * status : 5
     * cancelId : null
     * payTime : null
     * checkTime : null
     * cancelTime : 1561455389000
     * createTime : 1561451721000
     * isDeleted : false
     * timestamp : 1561455389000
     * remarkInfo :
     * buyPayType : null
     * statusStr : null
     * stockUserPayWays : [{"id":"3","stockUserId":"187","alipayAccount":"15003890068","aliImg":"dasdsadsa","wxAccount":null,"wxImg":null,"payType":"2","createTime":null,"disable":false,"isDelete":false,"bankCardNo":null,"bankCardUnionNo":null,"bankCardType":null,"bankCardTypeCode":null,"bankCardOpenBank":null,"bankCardImg":null,"bankCardExpiredTime":null,"province":null,"city":null,"county":null,"address":null,"idCardNo":null},{"id":"4","stockUserId":"187","alipayAccount":null,"aliImg":null,"wxAccount":"15003890068","wxImg":"dasdsadsa","payType":"1","createTime":null,"disable":false,"isDelete":false,"bankCardNo":null,"bankCardUnionNo":null,"bankCardType":null,"bankCardTypeCode":null,"bankCardOpenBank":null,"bankCardImg":null,"bankCardExpiredTime":null,"province":null,"city":null,"county":null,"address":null,"idCardNo":null},{"id":"6","stockUserId":"187","alipayAccount":null,"aliImg":null,"wxAccount":null,"wxImg":null,"payType":"3","createTime":null,"disable":false,"isDelete":false,"bankCardNo":"123456789","bankCardUnionNo":App.INSTANCE.getString(R.string.fabiorderDetailBean1),"bankCardType":null,"bankCardTypeCode":null,"bankCardOpenBank":App.INSTANCE.getString(R.string.fabiorderDetailBean2),"bankCardImg":null,"bankCardExpiredTime":null,"province":null,"city":null,"county":null,"address":null,"idCardNo":null}]
     * countDownTime : null
     * minPrice : 11
     * maxPrice : 60
     * buyUsername : 刘刘刘
     * sellUsername : 康世文
     * buyPhone : null
     * sellPhone : 15003890068
     * buyEmail : 810556392@qq.com
     * sellEmail : 4545526@qq.com
     * command : null
     */

    private String id;
    private String entrustNo;
    private String dealEntrustNo;
    private String sellId;
    private String buyId;
    private double price;
    private double totalNum;
    private String totalPrice;
    private double fee;
    private String payType;
    private String refer;
    private int type;
    private int status;
    private String cancelId;
    private String payTime;
    private String checkTime;
    private long cancelTime;
    private long createTime;
    private boolean isDeleted;
    private long timestamp;
    private String remarkInfo;
    private String buyPayType;
    private String statusStr;
    private Long countDownTime;
    private String minPrice;
    private String maxPrice;
    private String buyUsername;
    private String sellUsername;
    private String buyPhone;
    private String sellPhone;
    private String buyEmail;
    private String sellEmail;
    private String command;
    private List<StockUserPayWaysBean> stockUserPayWays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getDealEntrustNo() {
        return dealEntrustNo;
    }

    public void setDealEntrustNo(String dealEntrustNo) {
        this.dealEntrustNo = dealEntrustNo;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(double totalNum) {
        this.totalNum = totalNum;
    }


    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public long getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(long cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(createTime));
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarkInfo() {
        return remarkInfo;
    }

    public void setRemarkInfo(String remarkInfo) {
        this.remarkInfo = remarkInfo;
    }

    public String getBuyPayType() {
        return buyPayType;
    }

    public void setBuyPayType(String buyPayType) {
        this.buyPayType = buyPayType;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Long getCountDownTime() {
        return countDownTime;
    }

    public void setCountDownTime(Long countDownTime) {
        this.countDownTime = countDownTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getBuyUsername() {
        return buyUsername;
    }

    public void setBuyUsername(String buyUsername) {
        this.buyUsername = buyUsername;
    }

    public String getSellUsername() {
        return sellUsername;
    }

    public void setSellUsername(String sellUsername) {
        this.sellUsername = sellUsername;
    }

    public String getBuyPhone() {
        return buyPhone;
    }

    public void setBuyPhone(String buyPhone) {
        this.buyPhone = buyPhone;
    }

    public String getSellPhone() {
        return sellPhone;
    }

    public void setSellPhone(String sellPhone) {
        this.sellPhone = sellPhone;
    }

    public String getBuyEmail() {
        return buyEmail;
    }

    public void setBuyEmail(String buyEmail) {
        this.buyEmail = buyEmail;
    }

    public String getSellEmail() {
        return sellEmail;
    }

    public void setSellEmail(String sellEmail) {
        this.sellEmail = sellEmail;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<StockUserPayWaysBean> getStockUserPayWays() {
        return stockUserPayWays;
    }

    public void setStockUserPayWays(List<StockUserPayWaysBean> stockUserPayWays) {
        this.stockUserPayWays = stockUserPayWays;
    }

    public static class StockUserPayWaysBean {
        /**
         * id : 3
         * stockUserId : 187
         * alipayAccount : 15003890068
         * aliImg : dasdsadsa
         * wxAccount : null
         * wxImg : null
         * payType : 2
         * createTime : null
         * disable : false
         * isDelete : false
         * bankCardNo : null
         * bankCardUnionNo : null
         * bankCardType : null
         * bankCardTypeCode : null
         * bankCardOpenBank : null
         * bankCardImg : null
         * bankCardExpiredTime : null
         * province : null
         * city : null
         * county : null
         * address : null
         * idCardNo : null
         */

        private String id;
        private String stockUserId;
        private String alipayAccount;
        private String aliImg;
        private String wxAccount;
        private String wxImg;
        private String payType;
        private String createTime;
        private boolean disable;
        private boolean isDelete;
        private String bankCardNo;
        private String bankCardUnionNo;
        private String bankCardType;
        private String bankCardTypeCode;
        private String bankCardOpenBank;
        private String bankCardImg;
        private String bankCardExpiredTime;
        private String province;
        private String city;
        private String county;
        private String address;
        private String idCardNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStockUserId() {
            return stockUserId;
        }

        public void setStockUserId(String stockUserId) {
            this.stockUserId = stockUserId;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public String getAliImg() {
            return HttpConfig.BASE_IMG_URL+aliImg;
        }

        public void setAliImg(String aliImg) {
            this.aliImg = aliImg;
        }

        public String getWxAccount() {
            return wxAccount;
        }

        public void setWxAccount(String wxAccount) {
            this.wxAccount = wxAccount;
        }

        public String getWxImg() {
            return HttpConfig.BASE_IMG_URL+wxImg;
        }

        public void setWxImg(String wxImg) {
            this.wxImg = wxImg;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankCardUnionNo() {
            return bankCardUnionNo;
        }

        public void setBankCardUnionNo(String bankCardUnionNo) {
            this.bankCardUnionNo = bankCardUnionNo;
        }

        public String getBankCardType() {
            return bankCardType;
        }

        public void setBankCardType(String bankCardType) {
            this.bankCardType = bankCardType;
        }

        public String getBankCardTypeCode() {
            return bankCardTypeCode;
        }

        public void setBankCardTypeCode(String bankCardTypeCode) {
            this.bankCardTypeCode = bankCardTypeCode;
        }

        public String getBankCardOpenBank() {
            return bankCardOpenBank;
        }

        public void setBankCardOpenBank(String bankCardOpenBank) {
            this.bankCardOpenBank = bankCardOpenBank;
        }

        public String getBankCardImg() {
            return bankCardImg;
        }

        public void setBankCardImg(String bankCardImg) {
            this.bankCardImg = bankCardImg;
        }

        public String getBankCardExpiredTime() {
            return bankCardExpiredTime;
        }

        public void setBankCardExpiredTime(String bankCardExpiredTime) {
            this.bankCardExpiredTime = bankCardExpiredTime;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }
    }
}
