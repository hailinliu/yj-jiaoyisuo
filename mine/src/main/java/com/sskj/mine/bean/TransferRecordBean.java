package com.sskj.mine.bean;

import java.util.List;

public class TransferRecordBean {

    /**
     * data : {"content":[{"id":65,"memberId":93,"coin":"USDT","direction":5,"type":"合约转入到法币","amount":2000,"createTime":"2021-01-30 13:39:49"},{"id":64,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":1212,"createTime":"2021-01-29 17:06:39"},{"id":63,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":1999,"createTime":"2021-01-29 17:02:53"},{"id":57,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":100000,"createTime":"2021-01-28 10:49:44"}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":50,"offset":0,"paged":true,"unpaged":false},"last":true,"totalPages":1,"totalElements":4,"first":true,"sort":{"sorted":true,"unsorted":false,"empty":false},"size":50,"number":0,"numberOfElements":4,"empty":false}
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
         * content : [{"id":65,"memberId":93,"coin":"USDT","direction":5,"type":"合约转入到法币","amount":2000,"createTime":"2021-01-30 13:39:49"},{"id":64,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":1212,"createTime":"2021-01-29 17:06:39"},{"id":63,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":1999,"createTime":"2021-01-29 17:02:53"},{"id":57,"memberId":93,"coin":"USDT","direction":2,"type":"币币转入到合约","amount":100000,"createTime":"2021-01-28 10:49:44"}]
         * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":50,"offset":0,"paged":true,"unpaged":false}
         * last : true
         * totalPages : 1
         * totalElements : 4
         * first : true
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * size : 50
         * number : 0
         * numberOfElements : 4
         * empty : false
         */

        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private SortBeanX sort;
        private int size;
        private int number;
        private int numberOfElements;
        private boolean empty;
        private List<ContentBean> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
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

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
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
             * pageSize : 50
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
             * id : 65
             * memberId : 93
             * coin : USDT
             * direction : 5
             * type : 合约转入到法币
             * amount : 2000
             * createTime : 2021-01-30 13:39:49
             */

            private int id;
            private int memberId;
            private String coin;
            private int direction;
            private String type;
            private int amount;
            private String createTime;

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

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }

            public int getDirection() {
                return direction;
            }

            public void setDirection(int direction) {
                this.direction = direction;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
