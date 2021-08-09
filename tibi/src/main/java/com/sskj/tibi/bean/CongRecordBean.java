package com.sskj.tibi.bean;

import java.io.Serializable;
import java.util.List;

public class CongRecordBean implements Serializable {

    /**
     * data : {"content":[{"id":2,"memberId":43,"amount":0.01,"unit":"USDT","createTime":"2020-11-24 17:03:34","txid":"0xb642c52113b9003bbe1dfa6653d8803377274caad7d651d938eaee256accbd96","address":"0xc93dd34b7d1bdd2be87a1d32b4040a023ef50d4d","username":null,"status":1,"collectStatus":2,"coin":null}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":10,"offset":0,"paged":true,"unpaged":false},"totalPages":1,"totalElements":1,"last":true,"first":true,"sort":{"sorted":true,"unsorted":false,"empty":false},"numberOfElements":1,"size":10,"number":0,"empty":false}
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

    public static class DataBean implements Serializable{
        /**
         * content : [{"id":2,"memberId":43,"amount":0.01,"unit":"USDT","createTime":"2020-11-24 17:03:34","txid":"0xb642c52113b9003bbe1dfa6653d8803377274caad7d651d938eaee256accbd96","address":"0xc93dd34b7d1bdd2be87a1d32b4040a023ef50d4d","username":null,"status":1,"collectStatus":2,"coin":null}]
         * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":10,"offset":0,"paged":true,"unpaged":false}
         * totalPages : 1
         * totalElements : 1
         * last : true
         * first : true
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * numberOfElements : 1
         * size : 10
         * number : 0
         * empty : false
         */

        private PageableBean pageable;
        private int totalPages;
        private int totalElements;
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

        public static class ContentBean implements Serializable{
            /**
             * id : 2
             * memberId : 43
             * amount : 0.01
             * unit : USDT
             * createTime : 2020-11-24 17:03:34
             * txid : 0xb642c52113b9003bbe1dfa6653d8803377274caad7d651d938eaee256accbd96
             * address : 0xc93dd34b7d1bdd2be87a1d32b4040a023ef50d4d
             * username : null
             * status : 1
             * collectStatus : 2
             * coin : null
             */

            private int id;
            private int memberId;
            private double amount;
            private String unit;
            private String createTime;
            private String txid;
            private String address;
            private Object username;
            private int status;
            private int collectStatus;
            private Object coin;

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

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getTxid() {
                return txid;
            }

            public void setTxid(String txid) {
                this.txid = txid;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCollectStatus() {
                return collectStatus;
            }

            public void setCollectStatus(int collectStatus) {
                this.collectStatus = collectStatus;
            }

            public Object getCoin() {
                return coin;
            }

            public void setCoin(Object coin) {
                this.coin = coin;
            }
        }
    }
}
