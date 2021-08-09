package com.sskj.level.bean;

public class LevelBean {

    /**
     * data : {"id":1,"memberId":43,"memberName":null,"mobilePhone":null,"email":null,"coinId":"USDT","balance":2589.86723508,"frozenBalance":248.54519999,"isLock":0,"status":0,"foldBtc":null,"eqBtc":0.070495}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private DataBean data;
    private int code;
    private int errCode;
    private String message;
    private Object total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * id : 1
         * memberId : 43
         * memberName : null
         * mobilePhone : null
         * email : null
         * coinId : USDT
         * balance : 2589.86723508
         * frozenBalance : 248.54519999
         * isLock : 0
         * status : 0
         * foldBtc : null
         * eqBtc : 0.070495
         */

        private int id;
        private int memberId;
        private Object memberName;
        private Object mobilePhone;
        private Object email;
        private String coinId;
        private double balance;
        private double frozenBalance;
        private int isLock;
        private int status;
        private Object foldBtc;
        private double eqBtc;

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

        public Object getMemberName() {
            return memberName;
        }

        public void setMemberName(Object memberName) {
            this.memberName = memberName;
        }

        public Object getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(Object mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getCoinId() {
            return coinId;
        }

        public void setCoinId(String coinId) {
            this.coinId = coinId;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getFrozenBalance() {
            return frozenBalance;
        }

        public void setFrozenBalance(double frozenBalance) {
            this.frozenBalance = frozenBalance;
        }

        public int getIsLock() {
            return isLock;
        }

        public void setIsLock(int isLock) {
            this.isLock = isLock;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getFoldBtc() {
            return foldBtc;
        }

        public void setFoldBtc(Object foldBtc) {
            this.foldBtc = foldBtc;
        }

        public double getEqBtc() {
            return eqBtc;
        }

        public void setEqBtc(double eqBtc) {
            this.eqBtc = eqBtc;
        }
    }
}
