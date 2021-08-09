package com.sskj.tibi.bean;

import java.util.List;

public class OtherBean {

    /**
     * data : {"content":[{"id":14979439,"memberId":43,"amount":-0.3,"createTime":"2021-01-16 11:54:27","type":3,"symbol":"BTC","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979438,"memberId":43,"amount":10936.567032,"createTime":"2021-01-16 11:54:27","type":3,"symbol":"USDT","address":"","fee":21.916968,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979437,"memberId":43,"amount":-3627.174,"createTime":"2021-01-16 11:47:19","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979436,"memberId":43,"amount":0.0998,"createTime":"2021-01-16 11:47:19","type":3,"symbol":"BTC","address":"","fee":2.0E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979435,"memberId":43,"amount":-360885.9513,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979434,"memberId":43,"amount":9.931098,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.019902,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979433,"memberId":43,"amount":-2172.35137,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979432,"memberId":43,"amount":0.0597802,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":1.198E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979431,"memberId":43,"amount":-34470.975575,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979430,"memberId":43,"amount":0.948599,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.001901,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979429,"memberId":43,"amount":-25023.402,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979428,"memberId":43,"amount":0.68862,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.00138,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979427,"memberId":43,"amount":-12166.984665,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979426,"memberId":43,"amount":0.334829,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":6.71E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979425,"memberId":43,"amount":-474.88941,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979424,"memberId":43,"amount":0.0130738,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":2.62E-5,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979423,"memberId":43,"amount":-20,"createTime":"2021-01-16 11:43:51","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979422,"memberId":43,"amount":4.99E-4,"createTime":"2021-01-16 11:43:51","type":3,"symbol":"BTC","address":"","fee":1.0E-6,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979421,"memberId":43,"amount":-20,"createTime":"2021-01-16 11:43:43","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979420,"memberId":43,"amount":4.99E-4,"createTime":"2021-01-16 11:43:43","type":3,"symbol":"BTC","address":"","fee":1.0E-6,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false},"totalPages":1549,"totalElements":30976,"last":false,"first":true,"sort":{"sorted":true,"unsorted":false,"empty":false},"numberOfElements":20,"size":20,"number":0,"empty":false}
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
         * content : [{"id":14979439,"memberId":43,"amount":-0.3,"createTime":"2021-01-16 11:54:27","type":3,"symbol":"BTC","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979438,"memberId":43,"amount":10936.567032,"createTime":"2021-01-16 11:54:27","type":3,"symbol":"USDT","address":"","fee":21.916968,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979437,"memberId":43,"amount":-3627.174,"createTime":"2021-01-16 11:47:19","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979436,"memberId":43,"amount":0.0998,"createTime":"2021-01-16 11:47:19","type":3,"symbol":"BTC","address":"","fee":2.0E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979435,"memberId":43,"amount":-360885.9513,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979434,"memberId":43,"amount":9.931098,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.019902,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979433,"memberId":43,"amount":-2172.35137,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979432,"memberId":43,"amount":0.0597802,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":1.198E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979431,"memberId":43,"amount":-34470.975575,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979430,"memberId":43,"amount":0.948599,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.001901,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979429,"memberId":43,"amount":-25023.402,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979428,"memberId":43,"amount":0.68862,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":0.00138,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979427,"memberId":43,"amount":-12166.984665,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979426,"memberId":43,"amount":0.334829,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":6.71E-4,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979425,"memberId":43,"amount":-474.88941,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979424,"memberId":43,"amount":0.0130738,"createTime":"2021-01-16 11:46:25","type":3,"symbol":"BTC","address":"","fee":2.62E-5,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979423,"memberId":43,"amount":-20,"createTime":"2021-01-16 11:43:51","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979422,"memberId":43,"amount":4.99E-4,"createTime":"2021-01-16 11:43:51","type":3,"symbol":"BTC","address":"","fee":1.0E-6,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979421,"memberId":43,"amount":-20,"createTime":"2021-01-16 11:43:43","type":3,"symbol":"USDT","address":"","fee":0,"feeUnit":null,"flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null},{"id":14979420,"memberId":43,"amount":4.99E-4,"createTime":"2021-01-16 11:43:43","type":3,"symbol":"BTC","address":"","fee":1.0E-6,"feeUnit":"BTC","flag":0,"airdropId":null,"txid":null,"isQuick":null,"detail":null}]
         * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false}
         * totalPages : 1549
         * totalElements : 30976
         * last : false
         * first : true
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * numberOfElements : 20
         * size : 20
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
             * id : 14979439
             * memberId : 43
             * amount : -0.3
             * createTime : 2021-01-16 11:54:27
             * type : 3
             * symbol : BTC
             * address :
             * fee : 0
             * feeUnit : null
             * flag : 0
             * airdropId : null
             * txid : null
             * isQuick : null
             * detail : null
             */

            private int id;
            private int memberId;
            private double amount;
            private String createTime;
            private int type;
            private String symbol;
            private String address;
            private int fee;
            private Object feeUnit;
            private int flag;
            private Object airdropId;
            private Object txid;
            private Object isQuick;
            private Object detail;

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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public Object getFeeUnit() {
                return feeUnit;
            }

            public void setFeeUnit(Object feeUnit) {
                this.feeUnit = feeUnit;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public Object getAirdropId() {
                return airdropId;
            }

            public void setAirdropId(Object airdropId) {
                this.airdropId = airdropId;
            }

            public Object getTxid() {
                return txid;
            }

            public void setTxid(Object txid) {
                this.txid = txid;
            }

            public Object getIsQuick() {
                return isQuick;
            }

            public void setIsQuick(Object isQuick) {
                this.isQuick = isQuick;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }
        }
    }
}
