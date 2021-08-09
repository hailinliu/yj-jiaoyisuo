package com.sskj.mine.bean;

import java.util.List;

public class MyAssertDetailBean {

    /**
     * data : {"total":"1","list":[{"id":"10401472","stockUserId":"532","stockCode":"USDT","money":-12,"moneyBefore":10000,"moneyAfter":9988,"detail":"币币账户转出到法币账户","walletType":1,"type":29,"typeId":"1","createTime":1590556778000,"isDeleted":false,"timestamp":1590556777000,"marketLevel":null,"userTel":null,"startTime":null,"endTime":null}],"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
     */
        /**
         * total : 1
         * list : [{"id":"10401472","stockUserId":"532","stockCode":"USDT","money":-12,"moneyBefore":10000,"moneyAfter":9988,"detail":"币币账户转出到法币账户","walletType":1,"type":29,"typeId":"1","createTime":1590556778000,"isDeleted":false,"timestamp":1590556777000,"marketLevel":null,"userTel":null,"startTime":null,"endTime":null}]
         * pageNum : 1
         * pageSize : 10
         * size : 1
         * startRow : 1
         * endRow : 1
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
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
        private List<Integer> navigatepageNums;

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

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 10401472
             * stockUserId : 532
             * stockCode : USDT
             * money : -12
             * moneyBefore : 10000
             * moneyAfter : 9988
             * detail : 币币账户转出到法币账户
             * walletType : 1
             * type : 29
             * typeId : 1
             * createTime : 1590556778000
             * isDeleted : false
             * timestamp : 1590556777000
             * marketLevel : null
             * userTel : null
             * startTime : null
             * endTime : null
             */

            private String id;
            private String stockUserId;
            private String stockCode;
            private String money;
            private String moneyBefore;
            private String moneyAfter;
            private String detail;
            private int walletType;
            private int type;
            private String typeId;
            private String createTime;
            private boolean isDeleted;
            private long timestamp;
            private Object marketLevel;
            private Object userTel;
            private Object startTime;
            private Object endTime;
            private int status;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

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

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getMoneyBefore() {
                return moneyBefore;
            }

            public void setMoneyBefore(String moneyBefore) {
                this.moneyBefore = moneyBefore;
            }

            public String getMoneyAfter() {
                return moneyAfter;
            }

            public void setMoneyAfter(String moneyAfter) {
                this.moneyAfter = moneyAfter;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getWalletType() {
                return walletType;
            }

            public void setWalletType(int walletType) {
                this.walletType = walletType;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getCreateTime() {
                return createTime;
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

            public Object getMarketLevel() {
                return marketLevel;
            }

            public void setMarketLevel(Object marketLevel) {
                this.marketLevel = marketLevel;
            }

            public Object getUserTel() {
                return userTel;
            }

            public void setUserTel(Object userTel) {
                this.userTel = userTel;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }
        }
    }

