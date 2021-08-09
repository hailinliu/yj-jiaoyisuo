package com.sskj.tibi.bean;

import java.io.Serializable;
import java.util.List;

public class TibiRecordBean implements Serializable {

    /**
     * data : {"content":[{"id":13,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":22,"fee":1,"arrivedAmount":21,"createTime":"2021-01-12 20:11:00","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":12,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":22,"fee":1,"arrivedAmount":21,"createTime":"2021-01-12 20:04:32","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":11,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":23,"fee":1,"arrivedAmount":22,"createTime":"2021-01-12 20:02:22","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":10,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":100,"fee":1,"arrivedAmount":99,"createTime":"2020-12-18 15:46:03","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":0,"transactionNumber":null,"address":"0xd66ede4a705d096079b9afceb631a42aa6f4c5c2","remark":null,"isQuick":0},{"id":9,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":100,"fee":1,"arrivedAmount":99,"createTime":"2020-12-09 15:00:10","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":0,"transactionNumber":null,"address":"0xd66ede4a705d096079b9afceb631a42aa6f4c5c2","remark":null,"isQuick":0}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false},"totalPages":1,"totalElements":5,"last":true,"first":true,"sort":{"sorted":true,"unsorted":false,"empty":false},"numberOfElements":5,"size":20,"number":0,"empty":false}
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

    public static class DataBean implements Serializable {
        /**
         * content : [{"id":13,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":22,"fee":1,"arrivedAmount":21,"createTime":"2021-01-12 20:11:00","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":12,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":22,"fee":1,"arrivedAmount":21,"createTime":"2021-01-12 20:04:32","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":11,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":23,"fee":1,"arrivedAmount":22,"createTime":"2021-01-12 20:02:22","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":null,"transactionNumber":null,"address":"353534","remark":null,"isQuick":0},{"id":10,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":100,"fee":1,"arrivedAmount":99,"createTime":"2020-12-18 15:46:03","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":0,"transactionNumber":null,"address":"0xd66ede4a705d096079b9afceb631a42aa6f4c5c2","remark":null,"isQuick":0},{"id":9,"memberId":43,"coin":{"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"},"coinKey":"ETH","totalAmount":100,"fee":1,"arrivedAmount":99,"createTime":"2020-12-09 15:00:10","dealTime":null,"status":0,"isAuto":0,"admin":null,"canAutoWithdraw":0,"transactionNumber":null,"address":"0xd66ede4a705d096079b9afceb631a42aa6f4c5c2","remark":null,"isQuick":0}]
         * pageable : {"sort":{"sorted":true,"unsorted":false,"empty":false},"pageNumber":0,"pageSize":20,"offset":0,"paged":true,"unpaged":false}
         * totalPages : 1
         * totalElements : 5
         * last : true
         * first : true
         * sort : {"sorted":true,"unsorted":false,"empty":false}
         * numberOfElements : 5
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

        public static class PageableBean implements Serializable {
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

            public static class SortBean implements Serializable {
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

        public static class SortBeanX implements Serializable {
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

        public static class ContentBean implements Serializable {
            /**
             * id : 13
             * memberId : 43
             * coin : {"name":"Ethereum","nameCn":"以太坊","unit":"ETH","status":0,"minTxFee":1,"cnyRate":2000,"maxTxFee":0,"usdRate":300,"sgdRate":null,"enableRpc":1,"sort":1,"canWithdraw":1,"canRecharge":1,"canTransfer":1,"canAutoWithdraw":null,"withdrawThreshold":10,"minWithdrawAmount":10,"maxWithdrawAmount":5000,"isPlatformCoin":0,"hasLegal":false,"allBalance":null,"coldWalletAddress":null,"hotAllBalance":0,"minerFee":null,"withdrawScale":0,"minRechargeAmount":0,"masterAddress":null,"maxDailyWithdrawRate":5000,"imgUrl":"/file/ETH@2x.png","releaseAmount":null,"releaseTime":null,"fundPrice":null,"whitePaper":null,"website":null,"blockQuery":null,"coinInfo":null,"isSettlement":false,"burnAmount":null,"circulateAmount":null,"blockHeight":null,"contractAddress":null,"contractDecimals":null,"chainType":"ERCTOKEN"}
             * coinKey : ETH
             * totalAmount : 22
             * fee : 1
             * arrivedAmount : 21
             * createTime : 2021-01-12 20:11:00
             * dealTime : null
             * status : 0
             * isAuto : 0
             * admin : null
             * canAutoWithdraw : null
             * transactionNumber : null
             * address : 353534
             * remark : null
             * isQuick : 0
             */

            private int id;
            private int memberId;
            private CoinBean coin;
            private String coinKey;
            private int totalAmount;
            private int fee;
            private int arrivedAmount;
            private String createTime;
            private Object dealTime;
            private int status;
            private int isAuto;
            private Object admin;
            private Object canAutoWithdraw;
            private Object transactionNumber;
            private String address;
            private Object remark;
            private int isQuick;

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

            public CoinBean getCoin() {
                return coin;
            }

            public void setCoin(CoinBean coin) {
                this.coin = coin;
            }

            public String getCoinKey() {
                return coinKey;
            }

            public void setCoinKey(String coinKey) {
                this.coinKey = coinKey;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getArrivedAmount() {
                return arrivedAmount;
            }

            public void setArrivedAmount(int arrivedAmount) {
                this.arrivedAmount = arrivedAmount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getDealTime() {
                return dealTime;
            }

            public void setDealTime(Object dealTime) {
                this.dealTime = dealTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIsAuto() {
                return isAuto;
            }

            public void setIsAuto(int isAuto) {
                this.isAuto = isAuto;
            }

            public Object getAdmin() {
                return admin;
            }

            public void setAdmin(Object admin) {
                this.admin = admin;
            }

            public Object getCanAutoWithdraw() {
                return canAutoWithdraw;
            }

            public void setCanAutoWithdraw(Object canAutoWithdraw) {
                this.canAutoWithdraw = canAutoWithdraw;
            }

            public Object getTransactionNumber() {
                return transactionNumber;
            }

            public void setTransactionNumber(Object transactionNumber) {
                this.transactionNumber = transactionNumber;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getIsQuick() {
                return isQuick;
            }

            public void setIsQuick(int isQuick) {
                this.isQuick = isQuick;
            }

            public static class CoinBean implements Serializable {
                /**
                 * name : Ethereum
                 * nameCn : 以太坊
                 * unit : ETH
                 * status : 0
                 * minTxFee : 1
                 * cnyRate : 2000
                 * maxTxFee : 0
                 * usdRate : 300
                 * sgdRate : null
                 * enableRpc : 1
                 * sort : 1
                 * canWithdraw : 1
                 * canRecharge : 1
                 * canTransfer : 1
                 * canAutoWithdraw : null
                 * withdrawThreshold : 10
                 * minWithdrawAmount : 10
                 * maxWithdrawAmount : 5000
                 * isPlatformCoin : 0
                 * hasLegal : false
                 * allBalance : null
                 * coldWalletAddress : null
                 * hotAllBalance : 0
                 * minerFee : null
                 * withdrawScale : 0
                 * minRechargeAmount : 0
                 * masterAddress : null
                 * maxDailyWithdrawRate : 5000
                 * imgUrl : /file/ETH@2x.png
                 * releaseAmount : null
                 * releaseTime : null
                 * fundPrice : null
                 * whitePaper : null
                 * website : null
                 * blockQuery : null
                 * coinInfo : null
                 * isSettlement : false
                 * burnAmount : null
                 * circulateAmount : null
                 * blockHeight : null
                 * contractAddress : null
                 * contractDecimals : null
                 * chainType : ERCTOKEN
                 */

                private String name;
                private String nameCn;
                private String unit;
                private int status;
                private int minTxFee;
                private int cnyRate;
                private int maxTxFee;
                private int usdRate;
                private Object sgdRate;
                private int enableRpc;
                private int sort;
                private int canWithdraw;
                private int canRecharge;
                private int canTransfer;
                private Object canAutoWithdraw;
                private int withdrawThreshold;
                private int minWithdrawAmount;
                private int maxWithdrawAmount;
                private int isPlatformCoin;
                private boolean hasLegal;
                private Object allBalance;
                private Object coldWalletAddress;
                private int hotAllBalance;
                private Object minerFee;
                private int withdrawScale;
                private int minRechargeAmount;
                private Object masterAddress;
                private int maxDailyWithdrawRate;
                private String imgUrl;
                private Object releaseAmount;
                private Object releaseTime;
                private Object fundPrice;
                private Object whitePaper;
                private Object website;
                private Object blockQuery;
                private Object coinInfo;
                private boolean isSettlement;
                private Object burnAmount;
                private Object circulateAmount;
                private Object blockHeight;
                private Object contractAddress;
                private Object contractDecimals;
                private String chainType;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNameCn() {
                    return nameCn;
                }

                public void setNameCn(String nameCn) {
                    this.nameCn = nameCn;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getMinTxFee() {
                    return minTxFee;
                }

                public void setMinTxFee(int minTxFee) {
                    this.minTxFee = minTxFee;
                }

                public int getCnyRate() {
                    return cnyRate;
                }

                public void setCnyRate(int cnyRate) {
                    this.cnyRate = cnyRate;
                }

                public int getMaxTxFee() {
                    return maxTxFee;
                }

                public void setMaxTxFee(int maxTxFee) {
                    this.maxTxFee = maxTxFee;
                }

                public int getUsdRate() {
                    return usdRate;
                }

                public void setUsdRate(int usdRate) {
                    this.usdRate = usdRate;
                }

                public Object getSgdRate() {
                    return sgdRate;
                }

                public void setSgdRate(Object sgdRate) {
                    this.sgdRate = sgdRate;
                }

                public int getEnableRpc() {
                    return enableRpc;
                }

                public void setEnableRpc(int enableRpc) {
                    this.enableRpc = enableRpc;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public int getCanWithdraw() {
                    return canWithdraw;
                }

                public void setCanWithdraw(int canWithdraw) {
                    this.canWithdraw = canWithdraw;
                }

                public int getCanRecharge() {
                    return canRecharge;
                }

                public void setCanRecharge(int canRecharge) {
                    this.canRecharge = canRecharge;
                }

                public int getCanTransfer() {
                    return canTransfer;
                }

                public void setCanTransfer(int canTransfer) {
                    this.canTransfer = canTransfer;
                }

                public Object getCanAutoWithdraw() {
                    return canAutoWithdraw;
                }

                public void setCanAutoWithdraw(Object canAutoWithdraw) {
                    this.canAutoWithdraw = canAutoWithdraw;
                }

                public int getWithdrawThreshold() {
                    return withdrawThreshold;
                }

                public void setWithdrawThreshold(int withdrawThreshold) {
                    this.withdrawThreshold = withdrawThreshold;
                }

                public int getMinWithdrawAmount() {
                    return minWithdrawAmount;
                }

                public void setMinWithdrawAmount(int minWithdrawAmount) {
                    this.minWithdrawAmount = minWithdrawAmount;
                }

                public int getMaxWithdrawAmount() {
                    return maxWithdrawAmount;
                }

                public void setMaxWithdrawAmount(int maxWithdrawAmount) {
                    this.maxWithdrawAmount = maxWithdrawAmount;
                }

                public int getIsPlatformCoin() {
                    return isPlatformCoin;
                }

                public void setIsPlatformCoin(int isPlatformCoin) {
                    this.isPlatformCoin = isPlatformCoin;
                }

                public boolean isHasLegal() {
                    return hasLegal;
                }

                public void setHasLegal(boolean hasLegal) {
                    this.hasLegal = hasLegal;
                }

                public Object getAllBalance() {
                    return allBalance;
                }

                public void setAllBalance(Object allBalance) {
                    this.allBalance = allBalance;
                }

                public Object getColdWalletAddress() {
                    return coldWalletAddress;
                }

                public void setColdWalletAddress(Object coldWalletAddress) {
                    this.coldWalletAddress = coldWalletAddress;
                }

                public int getHotAllBalance() {
                    return hotAllBalance;
                }

                public void setHotAllBalance(int hotAllBalance) {
                    this.hotAllBalance = hotAllBalance;
                }

                public Object getMinerFee() {
                    return minerFee;
                }

                public void setMinerFee(Object minerFee) {
                    this.minerFee = minerFee;
                }

                public int getWithdrawScale() {
                    return withdrawScale;
                }

                public void setWithdrawScale(int withdrawScale) {
                    this.withdrawScale = withdrawScale;
                }

                public int getMinRechargeAmount() {
                    return minRechargeAmount;
                }

                public void setMinRechargeAmount(int minRechargeAmount) {
                    this.minRechargeAmount = minRechargeAmount;
                }

                public Object getMasterAddress() {
                    return masterAddress;
                }

                public void setMasterAddress(Object masterAddress) {
                    this.masterAddress = masterAddress;
                }

                public int getMaxDailyWithdrawRate() {
                    return maxDailyWithdrawRate;
                }

                public void setMaxDailyWithdrawRate(int maxDailyWithdrawRate) {
                    this.maxDailyWithdrawRate = maxDailyWithdrawRate;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public Object getReleaseAmount() {
                    return releaseAmount;
                }

                public void setReleaseAmount(Object releaseAmount) {
                    this.releaseAmount = releaseAmount;
                }

                public Object getReleaseTime() {
                    return releaseTime;
                }

                public void setReleaseTime(Object releaseTime) {
                    this.releaseTime = releaseTime;
                }

                public Object getFundPrice() {
                    return fundPrice;
                }

                public void setFundPrice(Object fundPrice) {
                    this.fundPrice = fundPrice;
                }

                public Object getWhitePaper() {
                    return whitePaper;
                }

                public void setWhitePaper(Object whitePaper) {
                    this.whitePaper = whitePaper;
                }

                public Object getWebsite() {
                    return website;
                }

                public void setWebsite(Object website) {
                    this.website = website;
                }

                public Object getBlockQuery() {
                    return blockQuery;
                }

                public void setBlockQuery(Object blockQuery) {
                    this.blockQuery = blockQuery;
                }

                public Object getCoinInfo() {
                    return coinInfo;
                }

                public void setCoinInfo(Object coinInfo) {
                    this.coinInfo = coinInfo;
                }

                public boolean isIsSettlement() {
                    return isSettlement;
                }

                public void setIsSettlement(boolean isSettlement) {
                    this.isSettlement = isSettlement;
                }

                public Object getBurnAmount() {
                    return burnAmount;
                }

                public void setBurnAmount(Object burnAmount) {
                    this.burnAmount = burnAmount;
                }

                public Object getCirculateAmount() {
                    return circulateAmount;
                }

                public void setCirculateAmount(Object circulateAmount) {
                    this.circulateAmount = circulateAmount;
                }

                public Object getBlockHeight() {
                    return blockHeight;
                }

                public void setBlockHeight(Object blockHeight) {
                    this.blockHeight = blockHeight;
                }

                public Object getContractAddress() {
                    return contractAddress;
                }

                public void setContractAddress(Object contractAddress) {
                    this.contractAddress = contractAddress;
                }

                public Object getContractDecimals() {
                    return contractDecimals;
                }

                public void setContractDecimals(Object contractDecimals) {
                    this.contractDecimals = contractDecimals;
                }

                public String getChainType() {
                    return chainType;
                }

                public void setChainType(String chainType) {
                    this.chainType = chainType;
                }
            }
        }
    }
}
