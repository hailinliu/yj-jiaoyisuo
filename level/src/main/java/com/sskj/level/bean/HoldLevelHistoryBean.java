package com.sskj.level.bean;

import java.util.List;

public class HoldLevelHistoryBean {

    /**
     * content : [{"orderId":"2c90fd6f76d1aa71017742801e1e001f","amount":1,"tradedAmount":0,"coinSymbol":"USDT","baseSymbol":"USDT","canceledTime":null,"completedTime":null,"direction":"BUY","marginTrade":1,"memberId":43,"orderResource":"CUSTOMER","price":31587.1,"openPositionPrice":31587.1,"closePositionPrice":0,"status":1,"symbol":"BTC/USDT","time":1611728428570,"positionTime":1611728428568,"stopProfit":null,"stopLoss":null,"overnightFee":0,"fee":63.1742,"type":0,"level":"30","marginMoney":1052.90333333,"closeAmount":null,"closeProfit":0,"orderList":[],"tranferFee":null}]
     * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false}
     * totalElements : 1
     * totalPages : 1
     * last : true
     * first : true
     * sort : {"sorted":true,"unsorted":false,"empty":false}
     * numberOfElements : 1
     * size : 20
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
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * pageNumber : 0
         * pageSize : 20
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
             * sorted : true
             * unsorted : false
             * empty : false
             */

            private boolean sorted;
            private boolean unsorted;
            private boolean empty;

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
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
         * sorted : true
         * unsorted : false
         * empty : false
         */

        private boolean sorted;
        private boolean unsorted;
        private boolean empty;

        public boolean isSorted() {
            return sorted;
        }

        public void setSorted(boolean sorted) {
            this.sorted = sorted;
        }

        public boolean isUnsorted() {
            return unsorted;
        }

        public void setUnsorted(boolean unsorted) {
            this.unsorted = unsorted;
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
         * orderId : 2c90fd6f76d1aa71017742801e1e001f
         * amount : 1
         * tradedAmount : 0
         * coinSymbol : USDT
         * baseSymbol : USDT
         * canceledTime : null
         * completedTime : null
         * direction : BUY
         * marginTrade : 1
         * memberId : 43
         * orderResource : CUSTOMER
         * price : 31587.1
         * openPositionPrice : 31587.1
         * closePositionPrice : 0
         * status : 1
         * symbol : BTC/USDT
         * time : 1611728428570
         * positionTime : 1611728428568
         * stopProfit : null
         * stopLoss : null
         * overnightFee : 0
         * fee : 63.1742
         * type : 0
         * level : 30
         * marginMoney : 1052.90333333
         * closeAmount : null
         * closeProfit : 0
         * orderList : []
         * tranferFee : null
         */

        private String orderId;
        private String amount;
        private String tradedAmount;
        private String coinSymbol;
        private String baseSymbol;
        private String canceledTime;
        private String completedTime;
        private String direction;
        private String marginTrade;
        private int memberId;
        private String orderResource;
        private String price;
        private String openPositionPrice;
        private String closePositionPrice;
        private int status;
        private String symbol;
        private long time;
        private long positionTime;
        private String stopProfit;
        private String stopLoss;
        private String overnightFee;
        private String fee;
        private int type;
        private String level;
        private String marginMoney;
        private String closeAmount;
        private String closeProfit;

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

        public String getClosePositionPrice() {
            return closePositionPrice;
        }

        public void setClosePositionPrice(String closePositionPrice) {
            this.closePositionPrice = closePositionPrice;
        }

        public String getMarginTrade() {
            return marginTrade;
        }

        public void setMarginTrade(String marginTrade) {
            this.marginTrade = marginTrade;
        }

        public String getOvernightFee() {
            return overnightFee;
        }

        public void setOvernightFee(String overnightFee) {
            this.overnightFee = overnightFee;
        }

        public String getCloseProfit() {
            return closeProfit;
        }

        public void setCloseProfit(String closeProfit) {
            this.closeProfit = closeProfit;
        }

        private String tranferFee;
        private List<?> orderList;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getCanceledTime() {
            return canceledTime;
        }

        public void setCanceledTime(String canceledTime) {
            this.canceledTime = canceledTime;
        }

        public String getCompletedTime() {
            return completedTime;
        }

        public void setCompletedTime(String completedTime) {
            this.completedTime = completedTime;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getOrderResource() {
            return orderResource;
        }

        public void setOrderResource(String orderResource) {
            this.orderResource = orderResource;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOpenPositionPrice() {
            return openPositionPrice;
        }

        public void setOpenPositionPrice(String openPositionPrice) {
            this.openPositionPrice = openPositionPrice;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getPositionTime() {
            return positionTime;
        }

        public void setPositionTime(long positionTime) {
            this.positionTime = positionTime;
        }

        public String getStopProfit() {
            return stopProfit;
        }

        public void setStopProfit(String stopProfit) {
            this.stopProfit = stopProfit;
        }

        public String getStopLoss() {
            return stopLoss;
        }

        public void setStopLoss(String stopLoss) {
            this.stopLoss = stopLoss;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMarginMoney() {
            return marginMoney;
        }

        public void setMarginMoney(String marginMoney) {
            this.marginMoney = marginMoney;
        }

        public String getCloseAmount() {
            return closeAmount;
        }

        public void setCloseAmount(String closeAmount) {
            this.closeAmount = closeAmount;
        }

        public String getTranferFee() {
            return tranferFee;
        }

        public void setTranferFee(String tranferFee) {
            this.tranferFee = tranferFee;
        }

        public List<?> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<?> orderList) {
            this.orderList = orderList;
        }
    }
}
