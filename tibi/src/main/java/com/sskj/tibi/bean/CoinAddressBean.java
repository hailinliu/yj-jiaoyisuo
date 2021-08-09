package com.sskj.tibi.bean;

import java.util.List;

public class CoinAddressBean {

    /**
     * data : [{"coinKey":"ERCTOKEN","address":"0x8b2ae30fb189a00a26d612bc3c6e1f526abf1813","memberId":69,"coinId":null}]
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
         * coinKey : ERCTOKEN
         * address : 0x8b2ae30fb189a00a26d612bc3c6e1f526abf1813
         * memberId : 69
         * coinId : null
         */

        private String coinKey;
        private String address;
        private int memberId;
        private Object coinId;

        public String getCoinKey() {
            return coinKey;
        }

        public void setCoinKey(String coinKey) {
            this.coinKey = coinKey;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public Object getCoinId() {
            return coinId;
        }

        public void setCoinId(Object coinId) {
            this.coinId = coinId;
        }
    }
}
