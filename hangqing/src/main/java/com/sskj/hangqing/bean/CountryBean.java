package com.sskj.hangqing.bean;

import java.util.List;

public class CountryBean {

    /**
     * data : [{"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0},{"zhName":"美国","enName":"America","areaCode":"1","language":"en_US","localCurrency":"USD","sort":0},{"zhName":"香港","enName":"HongKong","areaCode":"852","language":"zh_HK","localCurrency":"HKD","sort":0},{"zhName":"新加坡","enName":"Singapore","areaCode":"65","language":"en_US","localCurrency":"SGD","sort":0},{"zhName":"中国台湾","enName":"Taiwan,China","areaCode":"886","language":"zh_TW","localCurrency":"TWD","sort":0}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */


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

