package com.sskj.fabi.bean;

import java.io.Serializable;

public class NewOrderDetailBean implements Serializable {


    /**
     * data : {"orderSn":"412340225533153280","type":1,"unit":"USDT","status":4,"price":6,"money":240,"amount":40,"commission":0.4,"payInfo":{"realName":"汪汪队立大功","alipay":{"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"},"wechatPay":null,"bankInfo":null},"createTime":1612181571000,"payTime":1612181582000,"timeLimit":120,"otherSide":"15537120335","myId":97,"hisId":93,"referenceNumber":"846428","remark":"测试","appeal":{"id":11,"order":{"id":218,"orderSn":"412340225533153280","referenceNumber":"846428","advertiseType":1,"createTime":"2021-02-01 20:12:51","memberId":97,"memberName":"15515939213","memberRealName":"汪汪队立大功","customerId":93,"customerName":"15537120335","customerRealName":"xiaoxiao","coin":{"id":13,"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"jyRate":1,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg"},"price":6,"maxLimit":20000,"country":"中国","minLimit":200,"remark":null,"timeLimit":120,"money":240,"number":40,"commission":0.4,"status":4,"payTime":"2021-02-01 20:13:02","payMode":"支付宝","advertiseId":18,"cancelTime":null,"releaseTime":null,"alipay":{"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"},"bankInfo":null,"wechatPay":{"wechat":"465353212","qrWeCodeUrl":"/file/20210128/F00790462C0A4D259926A49C3F896DAD1611815401065.png"}},"createTime":"2021-02-01 20:13:10","dealWithTime":null,"remark":"89t89","initiatorId":97,"associateId":93,"isSuccess":null,"status":0,"admin":null,"imgUrls":null,"type":0},"payMode":"支付宝","serverTime":1612182387692}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

        /**
         * orderSn : 412340225533153280
         * type : 1
         * unit : USDT
         * status : 4
         * price : 6
         * money : 240
         * amount : 40
         * commission : 0.4
         * payInfo : {"realName":"汪汪队立大功","alipay":{"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"},"wechatPay":null,"bankInfo":null}
         * createTime : 1612181571000
         * payTime : 1612181582000
         * timeLimit : 120
         * otherSide : 15537120335
         * myId : 97
         * hisId : 93
         * referenceNumber : 846428
         * remark : 测试
         * appeal : {"id":11,"order":{"id":218,"orderSn":"412340225533153280","referenceNumber":"846428","advertiseType":1,"createTime":"2021-02-01 20:12:51","memberId":97,"memberName":"15515939213","memberRealName":"汪汪队立大功","customerId":93,"customerName":"15537120335","customerRealName":"xiaoxiao","coin":{"id":13,"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"jyRate":1,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg"},"price":6,"maxLimit":20000,"country":"中国","minLimit":200,"remark":null,"timeLimit":120,"money":240,"number":40,"commission":0.4,"status":4,"payTime":"2021-02-01 20:13:02","payMode":"支付宝","advertiseId":18,"cancelTime":null,"releaseTime":null,"alipay":{"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"},"bankInfo":null,"wechatPay":{"wechat":"465353212","qrWeCodeUrl":"/file/20210128/F00790462C0A4D259926A49C3F896DAD1611815401065.png"}},"createTime":"2021-02-01 20:13:10","dealWithTime":null,"remark":"89t89","initiatorId":97,"associateId":93,"isSuccess":null,"status":0,"admin":null,"imgUrls":null,"type":0}
         * payMode : 支付宝
         * serverTime : 1612182387692
         */

        private String orderSn;
        private int type;
        private String unit;
        private int status;
        private int price;
        private int money;
        private int amount;
        private double commission;
        private PayInfoBean payInfo;
        private long createTime;
        private long payTime;
        private String timeLimit;
        private String otherSide;
        private int myId;
        private int hisId;
        private String referenceNumber;
        private String remark;
        private AppealBean appeal;
        private String payMode;
        private long serverTime;

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
            this.commission = commission;
        }

        public PayInfoBean getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(PayInfoBean payInfo) {
            this.payInfo = payInfo;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }



        public String getOtherSide() {
            return otherSide;
        }

        public void setOtherSide(String otherSide) {
            this.otherSide = otherSide;
        }

        public int getMyId() {
            return myId;
        }

        public void setMyId(int myId) {
            this.myId = myId;
        }

        public int getHisId() {
            return hisId;
        }

        public void setHisId(int hisId) {
            this.hisId = hisId;
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public AppealBean getAppeal() {
            return appeal;
        }

        public void setAppeal(AppealBean appeal) {
            this.appeal = appeal;
        }

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }

        public static class PayInfoBean {
            /**
             * realName : 汪汪队立大功
             * alipay : {"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"}
             * wechatPay : null
             * bankInfo : null
             */

            private String realName;
            private AlipayBean alipay;
            private WechatPayBean wechatPay;
            private BankInfoBean bankInfo;

            public WechatPayBean getWechatPay() {
                return wechatPay;
            }

            public void setWechatPay(WechatPayBean wechatPay) {
                this.wechatPay = wechatPay;
            }

            public BankInfoBean getBankInfo() {
                return bankInfo;
            }

            public void setBankInfo(BankInfoBean bankInfo) {
                this.bankInfo = bankInfo;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public AlipayBean getAlipay() {
                return alipay;
            }

            public void setAlipay(AlipayBean alipay) {
                this.alipay = alipay;
            }


            public static class WechatPayBean {
                /**
                 * wechat : 147258369
                 * qrWeCodeUrl : /file/20201127/6CE075B4BC024381A09D275501B2A7F91606464588911.jpg
                 */

                private String wechat;
                private String qrWeCodeUrl;

                public String getWechat() {
                    return wechat;
                }

                public void setWechat(String wechat) {
                    this.wechat = wechat;
                }

                public String getQrWeCodeUrl() {
                    return qrWeCodeUrl;
                }

                public void setQrWeCodeUrl(String qrWeCodeUrl) {
                    this.qrWeCodeUrl = qrWeCodeUrl;
                }
            }

            public static class BankInfoBean {
                /**
                 * bank : asdasdsad
                 * branch : fasdsadasd
                 * cardNo : 1561651561651561561
                 */

                private String bank;
                private String branch;
                private String cardNo;

                public String getBank() {
                    return bank;
                }

                public void setBank(String bank) {
                    this.bank = bank;
                }

                public String getBranch() {
                    return branch;
                }

                public void setBranch(String branch) {
                    this.branch = branch;
                }

                public String getCardNo() {
                    return cardNo;
                }

                public void setCardNo(String cardNo) {
                    this.cardNo = cardNo;
                }
            }
            public static class AlipayBean {
                /**
                 * aliNo : 45645665161
                 * qrCodeUrl : /file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png
                 */

                private String aliNo;
                private String qrCodeUrl;

                public String getAliNo() {
                    return aliNo;
                }

                public void setAliNo(String aliNo) {
                    this.aliNo = aliNo;
                }

                public String getQrCodeUrl() {
                    return qrCodeUrl;
                }

                public void setQrCodeUrl(String qrCodeUrl) {
                    this.qrCodeUrl = qrCodeUrl;
                }
            }
        }

        public static class AppealBean {
            /**
             * id : 11
             * order : {"id":218,"orderSn":"412340225533153280","referenceNumber":"846428","advertiseType":1,"createTime":"2021-02-01 20:12:51","memberId":97,"memberName":"15515939213","memberRealName":"汪汪队立大功","customerId":93,"customerName":"15537120335","customerRealName":"xiaoxiao","coin":{"id":13,"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"jyRate":1,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg"},"price":6,"maxLimit":20000,"country":"中国","minLimit":200,"remark":null,"timeLimit":120,"money":240,"number":40,"commission":0.4,"status":4,"payTime":"2021-02-01 20:13:02","payMode":"支付宝","advertiseId":18,"cancelTime":null,"releaseTime":null,"alipay":{"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"},"bankInfo":null,"wechatPay":{"wechat":"465353212","qrWeCodeUrl":"/file/20210128/F00790462C0A4D259926A49C3F896DAD1611815401065.png"}}
             * createTime : 2021-02-01 20:13:10
             * dealWithTime : null
             * remark : 89t89
             * initiatorId : 97
             * associateId : 93
             * isSuccess : null
             * status : 0
             * admin : null
             * imgUrls : null
             * type : 0
             */

            private int id;
            private OrderBean order;
            private String createTime;
            private Object dealWithTime;
            private String remark;
            private int initiatorId;
            private int associateId;
            private Object isSuccess;
            private int status;
            private Object admin;
            private Object imgUrls;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public OrderBean getOrder() {
                return order;
            }

            public void setOrder(OrderBean order) {
                this.order = order;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getDealWithTime() {
                return dealWithTime;
            }

            public void setDealWithTime(Object dealWithTime) {
                this.dealWithTime = dealWithTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getInitiatorId() {
                return initiatorId;
            }

            public void setInitiatorId(int initiatorId) {
                this.initiatorId = initiatorId;
            }

            public int getAssociateId() {
                return associateId;
            }

            public void setAssociateId(int associateId) {
                this.associateId = associateId;
            }

            public Object getIsSuccess() {
                return isSuccess;
            }

            public void setIsSuccess(Object isSuccess) {
                this.isSuccess = isSuccess;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getAdmin() {
                return admin;
            }

            public void setAdmin(Object admin) {
                this.admin = admin;
            }

            public Object getImgUrls() {
                return imgUrls;
            }

            public void setImgUrls(Object imgUrls) {
                this.imgUrls = imgUrls;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public static class OrderBean {
                /**
                 * id : 218
                 * orderSn : 412340225533153280
                 * referenceNumber : 846428
                 * advertiseType : 1
                 * createTime : 2021-02-01 20:12:51
                 * memberId : 97
                 * memberName : 15515939213
                 * memberRealName : 汪汪队立大功
                 * customerId : 93
                 * customerName : 15537120335
                 * customerRealName : xiaoxiao
                 * coin : {"id":13,"name":"USDT","nameCn":"泰达币","unit":"USDT","status":0,"jyRate":1,"sellMinAmount":100,"buyMinAmount":0.01,"sort":0,"isPlatformCoin":0,"coinScale":8,"maxTradingTime":0,"maxVolume":0,"usdRate":null,"cnyRate":null,"imgUrl":"/file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg"}
                 * price : 6
                 * maxLimit : 20000
                 * country : 中国
                 * minLimit : 200
                 * remark : null
                 * timeLimit : 120
                 * money : 240
                 * number : 40
                 * commission : 0.4
                 * status : 4
                 * payTime : 2021-02-01 20:13:02
                 * payMode : 支付宝
                 * advertiseId : 18
                 * cancelTime : null
                 * releaseTime : null
                 * alipay : {"aliNo":"45645665161","qrCodeUrl":"/file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png"}
                 * bankInfo : null
                 * wechatPay : {"wechat":"465353212","qrWeCodeUrl":"/file/20210128/F00790462C0A4D259926A49C3F896DAD1611815401065.png"}
                 */

                private int id;
                private String orderSn;
                private String referenceNumber;
                private int advertiseType;
                private String createTime;
                private int memberId;
                private String memberName;
                private String memberRealName;
                private int customerId;
                private String customerName;
                private String customerRealName;
                private CoinBean coin;
                private int price;
                private int maxLimit;
                private String country;
                private int minLimit;
                private Object remark;
                private int timeLimit;
                private int money;
                private int number;
                private double commission;
                private int status;
                private String payTime;
                private String payMode;
                private int advertiseId;
                private Object cancelTime;
                private Object releaseTime;
                private AlipayBeanX alipay;
                private Object bankInfo;
                private WechatPayBean wechatPay;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOrderSn() {
                    return orderSn;
                }

                public void setOrderSn(String orderSn) {
                    this.orderSn = orderSn;
                }

                public String getReferenceNumber() {
                    return referenceNumber;
                }

                public void setReferenceNumber(String referenceNumber) {
                    this.referenceNumber = referenceNumber;
                }

                public int getAdvertiseType() {
                    return advertiseType;
                }

                public void setAdvertiseType(int advertiseType) {
                    this.advertiseType = advertiseType;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getMemberId() {
                    return memberId;
                }

                public void setMemberId(int memberId) {
                    this.memberId = memberId;
                }

                public String getMemberName() {
                    return memberName;
                }

                public void setMemberName(String memberName) {
                    this.memberName = memberName;
                }

                public String getMemberRealName() {
                    return memberRealName;
                }

                public void setMemberRealName(String memberRealName) {
                    this.memberRealName = memberRealName;
                }

                public int getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(int customerId) {
                    this.customerId = customerId;
                }

                public String getCustomerName() {
                    return customerName;
                }

                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }

                public String getCustomerRealName() {
                    return customerRealName;
                }

                public void setCustomerRealName(String customerRealName) {
                    this.customerRealName = customerRealName;
                }

                public CoinBean getCoin() {
                    return coin;
                }

                public void setCoin(CoinBean coin) {
                    this.coin = coin;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getMaxLimit() {
                    return maxLimit;
                }

                public void setMaxLimit(int maxLimit) {
                    this.maxLimit = maxLimit;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public int getMinLimit() {
                    return minLimit;
                }

                public void setMinLimit(int minLimit) {
                    this.minLimit = minLimit;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public int getTimeLimit() {
                    return timeLimit;
                }

                public void setTimeLimit(int timeLimit) {
                    this.timeLimit = timeLimit;
                }

                public int getMoney() {
                    return money;
                }

                public void setMoney(int money) {
                    this.money = money;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public double getCommission() {
                    return commission;
                }

                public void setCommission(double commission) {
                    this.commission = commission;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getPayTime() {
                    return payTime;
                }

                public void setPayTime(String payTime) {
                    this.payTime = payTime;
                }

                public String getPayMode() {
                    return payMode;
                }

                public void setPayMode(String payMode) {
                    this.payMode = payMode;
                }

                public int getAdvertiseId() {
                    return advertiseId;
                }

                public void setAdvertiseId(int advertiseId) {
                    this.advertiseId = advertiseId;
                }

                public Object getCancelTime() {
                    return cancelTime;
                }

                public void setCancelTime(Object cancelTime) {
                    this.cancelTime = cancelTime;
                }

                public Object getReleaseTime() {
                    return releaseTime;
                }

                public void setReleaseTime(Object releaseTime) {
                    this.releaseTime = releaseTime;
                }

                public AlipayBeanX getAlipay() {
                    return alipay;
                }

                public void setAlipay(AlipayBeanX alipay) {
                    this.alipay = alipay;
                }

                public Object getBankInfo() {
                    return bankInfo;
                }

                public void setBankInfo(Object bankInfo) {
                    this.bankInfo = bankInfo;
                }

                public WechatPayBean getWechatPay() {
                    return wechatPay;
                }

                public void setWechatPay(WechatPayBean wechatPay) {
                    this.wechatPay = wechatPay;
                }

                public static class CoinBean {
                    /**
                     * id : 13
                     * name : USDT
                     * nameCn : 泰达币
                     * unit : USDT
                     * status : 0
                     * jyRate : 1
                     * sellMinAmount : 100
                     * buyMinAmount : 0.01
                     * sort : 0
                     * isPlatformCoin : 0
                     * coinScale : 8
                     * maxTradingTime : 0
                     * maxVolume : 0
                     * usdRate : null
                     * cnyRate : null
                     * imgUrl : /file/20201120/AD5311A7EC5F4A2984EB3CE094BE45251605873225996.jpg
                     */

                    private int id;
                    private String name;
                    private String nameCn;
                    private String unit;
                    private int status;
                    private int jyRate;
                    private int sellMinAmount;
                    private double buyMinAmount;
                    private int sort;
                    private int isPlatformCoin;
                    private int coinScale;
                    private int maxTradingTime;
                    private int maxVolume;
                    private Object usdRate;
                    private Object cnyRate;
                    private String imgUrl;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

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

                    public int getJyRate() {
                        return jyRate;
                    }

                    public void setJyRate(int jyRate) {
                        this.jyRate = jyRate;
                    }

                    public int getSellMinAmount() {
                        return sellMinAmount;
                    }

                    public void setSellMinAmount(int sellMinAmount) {
                        this.sellMinAmount = sellMinAmount;
                    }

                    public double getBuyMinAmount() {
                        return buyMinAmount;
                    }

                    public void setBuyMinAmount(double buyMinAmount) {
                        this.buyMinAmount = buyMinAmount;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public int getIsPlatformCoin() {
                        return isPlatformCoin;
                    }

                    public void setIsPlatformCoin(int isPlatformCoin) {
                        this.isPlatformCoin = isPlatformCoin;
                    }

                    public int getCoinScale() {
                        return coinScale;
                    }

                    public void setCoinScale(int coinScale) {
                        this.coinScale = coinScale;
                    }

                    public int getMaxTradingTime() {
                        return maxTradingTime;
                    }

                    public void setMaxTradingTime(int maxTradingTime) {
                        this.maxTradingTime = maxTradingTime;
                    }

                    public int getMaxVolume() {
                        return maxVolume;
                    }

                    public void setMaxVolume(int maxVolume) {
                        this.maxVolume = maxVolume;
                    }

                    public Object getUsdRate() {
                        return usdRate;
                    }

                    public void setUsdRate(Object usdRate) {
                        this.usdRate = usdRate;
                    }

                    public Object getCnyRate() {
                        return cnyRate;
                    }

                    public void setCnyRate(Object cnyRate) {
                        this.cnyRate = cnyRate;
                    }

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }
                }

                public static class AlipayBeanX {
                    /**
                     * aliNo : 45645665161
                     * qrCodeUrl : /file/20210128/0694F5F969004901A35B2E997A6448371611815387318.png
                     */

                    private String aliNo;
                    private String qrCodeUrl;

                    public String getAliNo() {
                        return aliNo;
                    }

                    public void setAliNo(String aliNo) {
                        this.aliNo = aliNo;
                    }

                    public String getQrCodeUrl() {
                        return qrCodeUrl;
                    }

                    public void setQrCodeUrl(String qrCodeUrl) {
                        this.qrCodeUrl = qrCodeUrl;
                    }
                }

                public static class WechatPayBean {
                    /**
                     * wechat : 465353212
                     * qrWeCodeUrl : /file/20210128/F00790462C0A4D259926A49C3F896DAD1611815401065.png
                     */

                    private String wechat;
                    private String qrWeCodeUrl;

                    public String getWechat() {
                        return wechat;
                    }

                    public void setWechat(String wechat) {
                        this.wechat = wechat;
                    }

                    public String getQrWeCodeUrl() {
                        return qrWeCodeUrl;
                    }

                    public void setQrWeCodeUrl(String qrWeCodeUrl) {
                        this.qrWeCodeUrl = qrWeCodeUrl;
                    }
                }
            }
        }
    }


