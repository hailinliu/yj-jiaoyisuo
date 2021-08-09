package com.sskj.fabi.bean;

import java.util.List;

public class OrderBean {



        /**
         * content : [{"orderSn":"391221709799297024","createTime":"2020-12-05 13:35:24","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":669.5,"commission":0,"amount":103,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391220511167877120","createTime":"2020-12-05 13:30:38","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":100,"commission":0,"amount":1,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391220002503659520","createTime":"2020-12-05 13:28:37","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":913,"commission":0,"amount":9.13,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391219436738187264","createTime":"2020-12-05 13:26:22","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":100,"commission":0,"amount":1,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391218254808485888","createTime":"2020-12-05 13:21:40","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":650,"commission":0,"amount":100,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391217967830011904","createTime":"2020-12-05 13:20:32","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":780,"commission":0,"amount":120,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391217627978141696","createTime":"2020-12-05 13:19:11","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":600,"commission":0,"amount":6,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391214130092183552","createTime":"2020-12-05 13:05:17","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"391179976898121728","createTime":"2020-12-05 10:49:34","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":1580,"commission":0,"amount":243.07692307,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"390928160499306496","createTime":"2020-12-04 18:08:57","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":1230,"commission":0,"amount":189.23076923,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"390926472338083840","createTime":"2020-12-04 18:02:14","unit":"USDT","type":1,"name":"请叫我村长","price":6.5,"money":1230,"commission":0,"amount":189.23076923,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"390858205791653888","createTime":"2020-12-04 13:30:58","unit":"USDT","type":1,"name":"123","price":100,"money":100,"commission":0,"amount":1,"status":0,"memberId":15,"avatar":null,"realName":"dsdasdsad"},{"orderSn":"390138978214481920","createTime":"2020-12-02 13:53:01","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"390101850935922688","createTime":"2020-12-02 11:25:29","unit":"USDT","type":1,"name":"123","price":100,"money":120,"commission":0,"amount":1.2,"status":2,"memberId":15,"avatar":null,"realName":"dsdasdsad"},{"orderSn":"389854915440283648","createTime":"2020-12-01 19:04:15","unit":"USDT","type":1,"name":"15038042749","price":100,"money":100,"commission":0,"amount":1,"status":2,"memberId":57,"avatar":null,"realName":"法外狂徒张三"},{"orderSn":"389354179887501312","createTime":"2020-11-30 09:54:30","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"389354081384271872","createTime":"2020-11-30 09:54:07","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"389354070453915648","createTime":"2020-11-30 09:54:04","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"389354039390900224","createTime":"2020-11-30 09:53:57","unit":"USDT","type":1,"name":"请叫我村长","price":100,"money":1000,"commission":0,"amount":10,"status":0,"memberId":41,"avatar":null,"realName":"马林"},{"orderSn":"388733975255977984","createTime":"2020-11-28 16:50:02","unit":"USDT","type":1,"name":"15038042749","price":100,"money":200,"commission":0,"amount":2,"status":0,"memberId":57,"avatar":null,"realName":"法外狂徒张三"}]
         * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false}
         * totalElements : 31
         * totalPages : 2
         * last : false
         * first : true
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * size : 20
         * number : 0
         * numberOfElements : 20
         * empty : false
         */

        private PageableBean pageable;
        private int totalElements;
        private int totalPages;
        private boolean last;
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
             * orderSn : 391221709799297024
             * createTime : 2020-12-05 13:35:24
             * unit : USDT
             * type : 1
             * name : 请叫我村长
             * price : 6.5
             * money : 669.5
             * commission : 0
             * amount : 103
             * status : 0
             * memberId : 41
             * avatar : null
             * realName : 马林
             */

            private String orderSn;
            private String createTime;
            private String unit;
            private int type;
            private String name;
            private double price;
            private double money;
            private int commission;
            private int amount;
            private int status;
            private int memberId;
            private String avatar;
            private String realName;
            private String imgUrl;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }
        }
    }

