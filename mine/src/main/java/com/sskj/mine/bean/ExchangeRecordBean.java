package com.sskj.mine.bean;

import java.util.List;

public class ExchangeRecordBean {



        /**
         * total : 6
         * list : [{"id":"6","swiftNo":"1590718881159ha9985khc","stockUserId":"531","username":"康世文","baseCode":"BTC","dealCode":"USDT","stockCode":"BTC/USDT","fee":0,"createTime":1590718881000,"isDeleted":false,"dealNum":9843,"dealMoney":9.362385996E7,"changeRate":9511.72},{"id":"5","swiftNo":"1590718859344cih89uf69","stockUserId":"531","username":"康世文","baseCode":"BTC","dealCode":"USDT","stockCode":"BTC/USDT","fee":0,"createTime":1590718859000,"isDeleted":false,"dealNum":12,"dealMoney":114099.48,"changeRate":9508.29},{"id":"4","swiftNo":"159071865685306awod4vo","stockUserId":"531","username":"康世文","baseCode":"ETH","dealCode":"USDT","stockCode":"ETH/USDT","fee":0,"createTime":1590718657000,"isDeleted":false,"dealNum":121,"dealMoney":26731.32,"changeRate":220.92},{"id":"3","swiftNo":"1590718640708841j3stl8","stockUserId":"531","username":"康世文","baseCode":"BTC","dealCode":"USDT","stockCode":"BTC/USDT","fee":0,"createTime":1590718641000,"isDeleted":false,"dealNum":121,"dealMoney":1149629.47,"changeRate":9501.07},{"id":"2","swiftNo":"1590718011611s11jz8xe1","stockUserId":"531","username":"康世文","baseCode":"BTC","dealCode":"USDT","stockCode":"BTC/USDT","fee":0,"createTime":1590718012000,"isDeleted":false,"dealNum":12,"dealMoney":89317.7352,"changeRate":7443.1446},{"id":"1","swiftNo":"15907177166328w6d3gma1","stockUserId":"531","username":"康世文","baseCode":"BTC","dealCode":"USDT","stockCode":"BTC/USDT","fee":0,"createTime":1590717717000,"isDeleted":false,"dealNum":12,"dealMoney":89317.7352,"changeRate":7443.1446}]
         * pageNum : 0
         * pageSize : 0
         * size : 6
         * startRow : 1
         * endRow : 6
         * pages : 0
         * prePage : 0
         * nextPage : 0
         * isFirstPage : false
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : []
         * navigateFirstPage : 0
         * navigateLastPage : 0
         */

        private String total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private List<ListBean> list;
        private List<?> navigatepageNums;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<?> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<?> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 6
             * swiftNo : 1590718881159ha9985khc
             * stockUserId : 531
             * username : 康世文
             * baseCode : BTC
             * dealCode : USDT
             * stockCode : BTC/USDT
             * fee : 0
             * createTime : 1590718881000
             * isDeleted : false
             * dealNum : 9843
             * dealMoney : 9.362385996E7
             * changeRate : 9511.72
             */

            private String id;
            private String swiftNo;
            private String stockUserId;
            private String username;
            private String baseCode;
            private String dealCode;
            private String stockCode;
            private int fee;
            private long createTime;
            private boolean isDeleted;
            private int dealNum;
            private double dealMoney;
            private double changeRate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSwiftNo() {
                return swiftNo;
            }

            public void setSwiftNo(String swiftNo) {
                this.swiftNo = swiftNo;
            }

            public String getStockUserId() {
                return stockUserId;
            }

            public void setStockUserId(String stockUserId) {
                this.stockUserId = stockUserId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getBaseCode() {
                return baseCode;
            }

            public void setBaseCode(String baseCode) {
                this.baseCode = baseCode;
            }

            public String getDealCode() {
                return dealCode;
            }

            public void setDealCode(String dealCode) {
                this.dealCode = dealCode;
            }

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public long getCreateTime() {
                return createTime;
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

            public int getDealNum() {
                return dealNum;
            }

            public void setDealNum(int dealNum) {
                this.dealNum = dealNum;
            }

            public double getDealMoney() {
                return dealMoney;
            }

            public void setDealMoney(double dealMoney) {
                this.dealMoney = dealMoney;
            }

            public double getChangeRate() {
                return changeRate;
            }

            public void setChangeRate(double changeRate) {
                this.changeRate = changeRate;
            }
        }
    }

