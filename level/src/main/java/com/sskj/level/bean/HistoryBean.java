package com.sskj.level.bean;

import java.util.List;

public class HistoryBean {

    /**
     * content : [{"orderId":"2c90fd7276d1a8cd0176d280a3f27923","memberId":43,"type":1,"amount":1,"symbol":"BTC/USDT","tradedAmount":0,"turnover":0,"coinSymbol":"BTC","baseSymbol":"USDT","status":0,"direction":"BUY","price":1000,"triggerPrice":null,"time":1609849414637,"completedTime":null,"canceledTime":null,"marginTrade":0,"orderResource":"CUSTOMER","detail":[],"amountStr":"1.0000","priceStr":"1000.00","completed":false}]
     * pageable : {"sort":{"unsorted":false,"sorted":true,"empty":false},"pageNumber":0,"pageSize":10,"offset":0,"paged":true,"unpaged":false}
     * totalElements : 1
     * totalPages : 1
     * last : true
     * first : true
     * sort : {"unsorted":false,"sorted":true,"empty":false}
     * numberOfElements : 1
     * size : 10
     * number : 0
     * empty : false
     */

    private PageableBean pageable;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private SortBeanX sort;
    private int numberOfElements;
    private int size;
    private int number;
    private boolean empty;
    private List<ContentBean> content;

    public PageableBean getPageable() {
        return pageable;
    }

    public void setPageable(PageableBean pageable) {
        this.pageable = pageable;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public SortBeanX getSort() {
        return sort;
    }

    public void setSort(SortBeanX sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class PageableBean {
        /**
         * sort : {"unsorted":false,"sorted":true,"empty":false}
         * pageNumber : 0
         * pageSize : 10
         * offset : 0
         * paged : true
         * unpaged : false
         */

        private SortBean sort;
        private int pageNumber;
        private int pageSize;
        private int offset;
        private boolean paged;
        private boolean unpaged;

        public SortBean getSort() {
            return sort;
        }

        public void setSort(SortBean sort) {
            this.sort = sort;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isPaged() {
            return paged;
        }

        public void setPaged(boolean paged) {
            this.paged = paged;
        }

        public boolean isUnpaged() {
            return unpaged;
        }

        public void setUnpaged(boolean unpaged) {
            this.unpaged = unpaged;
        }

        public static class SortBean {
            /**
             * unsorted : false
             * sorted : true
             * empty : false
             */

            private boolean unsorted;
            private boolean sorted;
            private boolean empty;

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
            }

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }

            public boolean isEmpty() {
                return empty;
            }

            public void setEmpty(boolean empty) {
                this.empty = empty;
            }
        }
    }

    public static class SortBeanX {
        /**
         * unsorted : false
         * sorted : true
         * empty : false
         */

        private boolean unsorted;
        private boolean sorted;
        private boolean empty;

        public boolean isUnsorted() {
            return unsorted;
        }

        public void setUnsorted(boolean unsorted) {
            this.unsorted = unsorted;
        }

        public boolean isSorted() {
            return sorted;
        }

        public void setSorted(boolean sorted) {
            this.sorted = sorted;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }
    }

    public static class ContentBean {
        /**
         * orderId : 2c90fd7276d1a8cd0176d280a3f27923
         * memberId : 43
         * type : 1
         * amount : 1
         * symbol : BTC/USDT
         * tradedAmount : 0
         * turnover : 0
         * coinSymbol : BTC
         * baseSymbol : USDT
         * status : 0
         * direction : BUY
         * price : 1000
         * triggerPrice : null
         * time : 1609849414637
         * completedTime : null
         * canceledTime : null
         * marginTrade : 0
         * orderResource : CUSTOMER
         * detail : []
         * amountStr : 1.0000
         * priceStr : 1000.00
         * completed : false
         */

        private String orderId;
        private int memberId;
        private int type;
        private String amount;
        private String symbol;
        private String tradedAmount;
        private String turnover;
        private String coinSymbol;
        private String baseSymbol;
        private int status;
        private String direction;
        private String price;
        private Object triggerPrice;
        private long time;
        private Object completedTime;
        private Object canceledTime;
        private String marginTrade;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTradedAmount() {
            return tradedAmount;
        }

        public void setTradedAmount(String tradedAmount) {
            this.tradedAmount = tradedAmount;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarginTrade() {
            return marginTrade;
        }

        public void setMarginTrade(String marginTrade) {
            this.marginTrade = marginTrade;
        }

        private String orderResource;
        private String amountStr;
        private String priceStr;
        private boolean completed;
        private List<?> detail;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }



        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }



        public String getCoinSymbol() {
            return coinSymbol;
        }

        public void setCoinSymbol(String coinSymbol) {
            this.coinSymbol = coinSymbol;
        }

        public String getBaseSymbol() {
            return baseSymbol;
        }

        public void setBaseSymbol(String baseSymbol) {
            this.baseSymbol = baseSymbol;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }



        public Object getTriggerPrice() {
            return triggerPrice;
        }

        public void setTriggerPrice(Object triggerPrice) {
            this.triggerPrice = triggerPrice;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getCompletedTime() {
            return completedTime;
        }

        public void setCompletedTime(Object completedTime) {
            this.completedTime = completedTime;
        }

        public Object getCanceledTime() {
            return canceledTime;
        }

        public void setCanceledTime(Object canceledTime) {
            this.canceledTime = canceledTime;
        }



        public String getOrderResource() {
            return orderResource;
        }

        public void setOrderResource(String orderResource) {
            this.orderResource = orderResource;
        }

        public String getAmountStr() {
            return amountStr;
        }

        public void setAmountStr(String amountStr) {
            this.amountStr = amountStr;
        }

        public String getPriceStr() {
            return priceStr;
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = priceStr;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public List<?> getDetail() {
            return detail;
        }

        public void setDetail(List<?> detail) {
            this.detail = detail;
        }
    }
}
