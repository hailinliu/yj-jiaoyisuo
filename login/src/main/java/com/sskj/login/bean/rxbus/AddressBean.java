package com.sskj.login.bean.rxbus;

import java.util.List;

public class AddressBean {

    /**
     * data : [{"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0},{"zhName":"加拿大","enName":"Canada","areaCode":"1","language":"en_US","localCurrency":"USD","sort":0},{"zhName":"澳大利亚","enName":"Australia","areaCode":"61","language":"en_US","localCurrency":"USD","sort":0},{"zhName":"新加坡","enName":"New capi","areaCode":"65","language":"en_US","localCurrency":"USD","sort":1}]
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
         * zhName : 中国
         * enName : China
         * areaCode : 86
         * language : zh_CN
         * localCurrency : CNY
         * sort : 0
         */

        private String zhName;
        private String enName;
        private String areaCode;
        private String language;
        private String localCurrency;
        private int sort;

        public String getZhName() {
            return zhName;
        }

        public void setZhName(String zhName) {
            this.zhName = zhName;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLocalCurrency() {
            return localCurrency;
        }

        public void setLocalCurrency(String localCurrency) {
            this.localCurrency = localCurrency;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
