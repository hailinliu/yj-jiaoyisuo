package com.sskj.level.bean;

import java.io.Serializable;
import java.util.List;

public class RecordHistoryBean implements Serializable {


    /**
     * content : [{"orderId":"2c90fd7276d1a8cd0176d280a3f27923","memberId":43,"type":1,"amount":1,"symbol":"BTC/USDT","tradedAmount":0,"turnover":0,"coinSymbol":"BTC","baseSymbol":"USDT","status":2,"direction":"BUY","price":1000,"triggerPrice":null,"time":1609849414637,"completedTime":null,"canceledTime":1609912392361,"marginTrade":0,"orderResource":"CUSTOMER","detail":[],"amountStr":"1.0000","priceStr":"1000.00","completed":true},{"orderId":"2c90fd7276d1a8cd0176d276d4230cf8","memberId":43,"type":1,"amount":1,"symbol":"BTC/USDT","tradedAmount":1,"turnover":31753.951266,"coinSymbol":"BTC","baseSymbol":"USDT","status":1,"direction":"BUY","price":40000,"triggerPrice":null,"time":1609848771612,"completedTime":1609848771635,"canceledTime":null,"marginTrade":0,"orderResource":"CUSTOMER","detail":[{"id":"2c90fd7276d1a94d0176d276d4330002","orderId":"2c90fd7276d1a8cd0176d276d4230cf8","price":31753.95,"amount":0.9578,"turnover":30413.93331,"fee":0.0019156,"memberId":43,"dealMemberId":1,"feeSymbol":"BTC","time":1609848771635},{"id":"2c90fd7276d1a94d0176d276d4590004","orderId":"2c90fd7276d1a8cd0176d276d4230cf8","price":31753.98,"amount":0.0422,"turnover":1340.017956,"fee":8.44E-5,"memberId":43,"dealMemberId":1,"feeSymbol":"BTC","time":1609848771673}],"amountStr":"1.0000","priceStr":"40000.00","completed":true},{"orderId":"2c90fd7276d1a8cd0176d2687f43647a","memberId":43,"type":0,"amount":200,"symbol":"BTC/USDT","tradedAmount":0.0063,"turnover":200,"coinSymbol":"BTC","baseSymbol":"USDT","status":1,"direction":"BUY","price":0,"triggerPrice":0,"time":1609847832361,"completedTime":1609847832649,"canceledTime":null,"marginTrade":0,"orderResource":"CUSTOMER","detail":[{"id":"2c90fd7276d1a94d0176d2687f800000","orderId":"2c90fd7276d1a8cd0176d2687f43647a","price":31657.27,"amount":0.0063,"turnover":200,"fee":1.26E-5,"memberId":43,"dealMemberId":1,"feeSymbol":"BTC","time":1609847832434}],"amountStr":"200.0000","priceStr":"0.00","completed":true}]
     * pageable : {"sort":{"unsorted":false,"sorted":true,"empty":false},"pageNumber":0,"pageSize":10,"offset":0,"paged":true,"unpaged":false}
     * totalElements : 3
     * totalPages : 1
     * last : true
     * first : true
     * sort : {"unsorted":false,"sorted":true,"empty":false}
     * numberOfElements : 3
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

    public static class ContentBean implements Serializable{
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
         * status : 2
         * direction : BUY
         * price : 1000
         * triggerPrice : null
         * time : 1609849414637
         * completedTime : null
         * canceledTime : 1609912392361
         * marginTrade : 0
         * orderResource : CUSTOMER
         * detail : []
         * amountStr : 1.0000
         * priceStr : 1000.00
         * completed : true
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
        private long canceledTime;
        private String marginTrade;
        private List<DetailBean> detail;

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

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

        public long getCanceledTime() {
            return canceledTime;
        }

        public void setCanceledTime(long canceledTime) {
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
        public static class DetailBean implements Serializable{
            /**
             * amount : 0
             * dealMemberId : 0
             * fee : 0
             * feeSymbol : string
             * id : string
             * memberId : 0
             * orderId : string
             * price : 0
             * time : 0
             * turnover : 0
             */

            private int amount;
            private int dealMemberId;
            private int fee;
            private String feeSymbol;
            private String id;
            private int memberId;
            private String orderId;
            private int price;
            private int time;
            private int turnover;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getDealMemberId() {
                return dealMemberId;
            }

            public void setDealMemberId(int dealMemberId) {
                this.dealMemberId = dealMemberId;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public String getFeeSymbol() {
                return feeSymbol;
            }

            public void setFeeSymbol(String feeSymbol) {
                this.feeSymbol = feeSymbol;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getTurnover() {
                return turnover;
            }

            public void setTurnover(int turnover) {
                this.turnover = turnover;
            }
        }
    }
}
