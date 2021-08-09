package com.sskj.tibi.bean;

import java.util.List;

public class NewoinDetailBean {

    /**
     * data : [{"unit":"ETH","threshold":10,"minAmount":10,"maxAmount":5000,"minTxFee":1,"maxTxFee":null,"nameCn":"以太坊","name":"Ethereum","balance":9838.2255534,"canAutoWithdraw":null,"withdrawScale":0,"addresses":[{"address":"2424","remark":"23423423"},{"address":"353534","remark":"353445654"}]},{"unit":"TRB","threshold":10,"minAmount":10,"maxAmount":5000,"minTxFee":1,"maxTxFee":null,"nameCn":"TRB","name":"TRB","balance":10000,"canAutoWithdraw":1,"withdrawScale":0,"addresses":[]},{"unit":"USDT","threshold":10,"minAmount":10,"maxAmount":5000,"minTxFee":1,"maxTxFee":null,"nameCn":"泰达币","name":"USDT","balance":29747.60025238818,"canAutoWithdraw":0,"withdrawScale":4,"addresses":[{"address":"0x2806bDA6608B0b752e0FF54344F8e6B9bf804CF4","remark":"123456"},{"address":"0x2806bDA6608B0b752e0FF54344F8e6B9bf804CF5","remark":"康康"},{"address":"0x2806bDA6608B0b752e0FF54344F8e6B9bf804CF7","remark":"康大官人"}]}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private int code;
    private int errCode;
    private String message;
    private Object total;
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

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * unit : ETH
         * threshold : 10
         * minAmount : 10
         * maxAmount : 5000
         * minTxFee : 1
         * maxTxFee : null
         * nameCn : 以太坊
         * name : Ethereum
         * balance : 9838.2255534
         * canAutoWithdraw : null
         * withdrawScale : 0
         * addresses : [{"address":"2424","remark":"23423423"},{"address":"353534","remark":"353445654"}]
         */

        private String unit;
        private int threshold;
        private int minAmount;
        private int maxAmount;
        private int minTxFee;
        private Object maxTxFee;
        private String nameCn;
        private String name;
        private double balance;
        private Object canAutoWithdraw;
        private int withdrawScale;
        private List<AddressesBean> addresses;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }

        public int getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(int minAmount) {
            this.minAmount = minAmount;
        }

        public int getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(int maxAmount) {
            this.maxAmount = maxAmount;
        }

        public int getMinTxFee() {
            return minTxFee;
        }

        public void setMinTxFee(int minTxFee) {
            this.minTxFee = minTxFee;
        }

        public Object getMaxTxFee() {
            return maxTxFee;
        }

        public void setMaxTxFee(Object maxTxFee) {
            this.maxTxFee = maxTxFee;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Object getCanAutoWithdraw() {
            return canAutoWithdraw;
        }

        public void setCanAutoWithdraw(Object canAutoWithdraw) {
            this.canAutoWithdraw = canAutoWithdraw;
        }

        public int getWithdrawScale() {
            return withdrawScale;
        }

        public void setWithdrawScale(int withdrawScale) {
            this.withdrawScale = withdrawScale;
        }

        public List<AddressesBean> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<AddressesBean> addresses) {
            this.addresses = addresses;
        }

        public static class AddressesBean {
            /**
             * address : 2424
             * remark : 23423423
             */

            private String address;
            private String remark;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
