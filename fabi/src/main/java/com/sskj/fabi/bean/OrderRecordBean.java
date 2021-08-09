package com.sskj.fabi.bean;

import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;
import java.util.List;

public class OrderRecordBean {



        private String id;
        private String entrustNo;
        private String dealEntrustNo;
        private String sellId;
        private String buyId;
        private String price;
        private String totalNum;
        private String totalPrice;
        private String fee;
        private String payType;
        private int refer;
        private int type;
        private int status;
        private String cancelId;
        private String payTime;
        private String checkTime;
        private String cancelTime;
        private String createTime;
        private boolean isDeleted;
        private long timestamp;
        private String remarkInfo;
        private String buyPayType;
        private String statusStr;
        private String countDownTime;
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

        public String getPrice() {
            return CoinUtil.keepRMB(price);
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotalNum() {
            return CoinUtil.keepUSDT(totalNum);
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getTotalPrice() {
            return CoinUtil.keepRMB(totalPrice);
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public int getRefer() {
            return refer;
        }

        public void setRefer(int refer) {
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

        public String getCreateTime() {
            return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
        }

        public void setCreateTime(String createTime) {
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

        public String getCountDownTime() {
            return countDownTime;
        }

        public void setCountDownTime(String countDownTime) {
            this.countDownTime = countDownTime;
        }

        public String getMinPrice() {
            return CoinUtil.keepRMB(minPrice);
        }

        public void setMinPrice(String minPrice) {
            this.minPrice = minPrice;
        }

        public String getMaxPrice() {
            return CoinUtil.keepRMB(maxPrice);
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


        public List<StockUserPayWaysBean> getStockUserPayWays() {
            return stockUserPayWays;
        }

        public void setStockUserPayWays(List<StockUserPayWaysBean> stockUserPayWays) {
            this.stockUserPayWays = stockUserPayWays;
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

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
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

        public String getBuyPhone() {
            return buyPhone;
        }

        public void setBuyPhone(String buyPhone) {
            this.buyPhone = buyPhone;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public static class StockUserPayWaysBean {
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
                return aliImg;
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
                return wxImg;
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

            public boolean isDelete() {
                return isDelete;
            }

            public void setDelete(boolean delete) {
                isDelete = delete;
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
