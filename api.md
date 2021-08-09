 
 * Android：
 #### 接口汇总
 ###### app模块
 ```java
     /**
      * 弹屏公告
      */
     public static final String GET_GONGGAO = "/app/user/screenNotice";
     /**
      * 版本更新
      */
     public static final String VERSION = "/app/user/appVersion";
 ```
 ###### 资产模块
 ```java
    /**
      * 资产列表
      */
     public static final String ASEET_LIST = "/app/user/captialFund";
     /**
      * 左边币种列表
      */
     public static final String CASH_LEFT_LIST = "/app/user/getFundList";

     /**
      * 可兑换币种列表
      */
     public static final String EXCHANE_COIN_LIST = "/app/user/getExchange";
     /**
      * 兑换
      */
     public static final String EXCHANE_COIN = "/app/user/propertyChange";
     /**
      * 兑换记录
      */
     public static final String EXCHANE_HISTORY = "/app/user/changeRecord";
     /**
      * 资产页面上方数据
      */
     public static final String ASEET_TOP = "/app/user/captialFundToltal";
 ```
 ###### 币币模块
 ```java
    /**
      * 撤销订单
      */
     public static final String COIN_ORDER_CANCEL = "/app/coincoin/cancelBill";
     /**
      * 获取币种信息
      */
     public static final String PRODUCT_LIST = "/app/kline/goodInfo";
     /**
      * 获取筛选币种对
      */
     public static final String CURRENCYLIST = "/app/coincoin/stockPair";
     /***
      * 获取历史订单记录
      */
     public static final String COIN_ENTRUST_LIST_HISTORY = "/app/coincoin/selCoinCoinByHistory";
     /**
      * 获取未完成的订单记录
      */
     public static final String COIN_ENTRUST_LIST_ALL = "/app/coincoin/selCoinCoin";
     /**
      * 获取币币下单配置参数
      */
     public static final String COIN_FEE = "/app/coincoin/getFount";
     /**
      * 下单
      */
     public static final String CREATE_COIN_ORDER = "/app/coincoin/addBill";
     /**
      * 获取盘口
      */
     public static final String GET_PANKOU = "/app/kline/buySellFive";
     /**
      * 深度数据
      */
     public static final String GET_DEEP = "/app/kline/depth";
 
 
     /**
      * 获取订单详情
      */
     public static final String RECORD_DETAIL = "/app/coincoin/successDealInfo";
 
     /**
      * 获取侧滑栏分类币种
      */
     public static final String CODO_HORIZATION_LIST = "/app/kline/getSeries";
```
###### 法币
```java
    /**
     * 法币大厅买卖数据
     */
    public static final String FABI_BUY_SELL = "/app/lawCoin/lawCoinTrading";
    /**
     * 法币大厅下单
     */
    public static final String FABI_CREATE_ORDER = "/app/lawCoin/lawCoinDeal";
    /**
     * 支付方式列表
     */
    public static final String FABI_PAY_WAY_LIST = "/app/lawCoin/payList";
    /**
     * 商家发单
     */
    public static final String FABI_PUBLISH = "/app/lawCoin/addBill";
    /**
     * 商家撤销发布的订单
     */
    public static final String FABI_PUBLISH_ORDER_CANCEL = "/app/lawCoin/cancelLawCoinDealBill";
    /**
     * 商家发单列表
     */
    public static final String FABI_PUBLISH_RECORD = "/app/lawCoin/sellBuyRecord";
    /**
     * 商家发单默认单价
     */
    public static final String FABI_PUBLISH_PRICELIMIT = "/app/lawCoin/showPrice";
    /**
     * 商家发单限额
     */
    public static final String FABI_PUBLISH_LIMIT_NUM = "/app/lawCoin/fbMinNum";

    /**
     * 求购/售出 列表
     */
    public static final String FABI_ORDER_RECORD = "/app/lawCoin/selBuySellInfo";
    /**
     * 法币订单详情
     */
    public static final String FABI_ORDER_RECORD_INFO = "/app/lawCoin/orderDetail";
    /**
     * 标记已付款
     */
    public static final String FABI_ORDER_MARK_PAY = "/app/lawCoin/sellBuyConfirme";
    /**
     * 法币取消订单
     */
    public static final String FABI_ORDER_CANCLE = "/app/lawCoin/cancelLawCoinRecord";
    /**
     * 账单申诉
     */
    public static final String FABI_ORDER_ALLEGE = "/app/lawCoin/appealLawCoinRecord";
    /**
     * 确认放行
     */
    public static final String FABI_ORDER_LET_GO = "/app/lawCoin/sellBuyConfirme";
```
###### 行情
```java
    /**
     * 行情数据
     */
    public static final String GET_PRODUCT = "/app/kline/goodInfo";


    /**
     * 获取k线数据
     */
    public static final String GET_K_DATA = "/app/kline/history";


    /**
     * 获取轮播图及公告
     */
    public static final String GET_BANNER = "/app/user/viewpager";


    /**
     * 币种简介
     */
    public static final String GET_SUMMARY = "/app/codeInfo";
    /**
     * 深度数据
     */
    public static final String GET_DEEP = "/app/kline/depth";
    /**
     * 实时成交
     */
    public static final String GET_ALL_TRADE = "/app/kline/originsInfo";
    /**
     * 咨询列表
     */
    public static final String GET_ZIXUN_LIST = "/app/user/comMessage";
    /**
     * 交易指南
     */
    public static final String GET_GUIDE_LIST = "/app/user/transactionRules";

    /**
     * 轮播公告
     */
    public static final String GET_NOTICE = "/app/user/contentNotice";
    /**
     * 帮助中心
     */
    public static final String HELP = "/app/user/indexContent";
    /**
     * 关于我们
     */
    public static final String ABOUT_US = "/app/user/ours";
    /**
     * 涨跌幅数据
     */
    public static final String GET_PRODUCT_UP_DOWN = "/app/kline/rankList";
    /**
     * 注册协议
     */
    public static final String REGISTER_AGREEMENT = "/app/user/service";
    /**
     * 隐私协议
     */
    public static final String PRIVATE_SERVICE = "/app/user/privacy";
```
###### 登录
```java
    /**
     * 发送验证码（注册）
     */
    public static final String SEND_SMS = "/app/user/getCode";



    /**
     * 注册
     */
    public static final String REGISTER = "/app/user/register";


    /**
     * 登录
     */
    public static final String LOGIN = "/app/user/login";


    /**
     * 重置登录密码
     */
    public static final String RESET_LOGIN_PWD = "/app/user/editPswd";


    /**
     * 谷歌验证
     */
    public static final String CHECK_GOOGLE = "/app/googleAuth/loginGoogleAuth";

    /**
     * 验证验证码
     */
    public static final String CHECK_CODE = "/app/user/checkCode";

    /**
     * 登录成功，发送登录短信
     */
    public static final String SEND_LOGIN_SMS = "/app/user/loginMsg";

```
###### 个人
```java
    /**
         * 初级实名认证
         */
        public static final String Verify_FISRT = "/app/user/baseAuth";
        /**
         * 获取用户信息
         */
        public static final String GET_USER_INFO = "/app/user/userInfo";
    
        /**
         * 上传图片
         */
        public static final String HIGH_VERIFY_IMG = "/app/upload/file";
        /**
         * 高级实名认证
         */
        public static final String HIGH_VERIFY = "/app/user/inspectAuth";
    
        /**
         * 客服联系方式
         */
        public static final String CONTRACT = "/app/user/touchMe";
        /**
         * 删除地址
         */
        public static final String DELETE_ADDRESS = "/app/currencyAddress/del";
        /**
         * 更新支付方式
         */
        public static final String UPDATE_PAY_TYPE = "/app/user/updatePayType";
        /**
         * 获取轮播公告
         */
        public static final String GET_NOTICE = "/app/user/contentNotice";
    
    
        /**
         * 绑定手机号或邮箱
         */
        public static final String BIND_MOBILE_OR_EMAIL = "/app/user/bindTelOrEmail";
    
    
        /**
         * 修改登录密码
         */
        public static final String SET_LOGIN_PWD = "/app/user/updatePswd";
    
        /**
         * 我的邀请链接
         */
        public static final String INVITE = "/app/user/myInvitation";
        /**
         * 我要推广主界面
         */
        public static final String INVITEMENU = "/usdFee/detail/queryUserMarketInfo";
        /**
         * 我的推广客户
         */
        public static final String INVITECUSTOM = "/app/user/myCustom";
        /**
         * 推广奖励记录
         */
        public static final String INVITE_REWARD = "/concess/detail/search";
        /**
         * 支付方式
         */
        public static final String PAY_WAY_LIST = "/app/lawCoin/payList";
        /**
         * 添加支付方式
         */
        public static final String ADD_PAY_PWD = "/app/user/addPayWay";
        /**
         * 申请商家
         */
        public static final String SHOPVERIFY = "/app/lawCoin/applyShop";
        /**
         * 解除商家
         */
        public static final String SHOPCANCEL = "/app/user/relieveShop";
        /**
         * 成为商家保证金
         */
        public static final String GETMONEY = "/app/lawCoin/applyShopPro";
        /**
         * 版本检测
         */
        public static final String VERSION = "/app/user/appVersion";
    
    
        /**
         * 改变支付方式开关
         */
        public static String CHANGE_SWITCH = "/app/lawCoin/updatePayWay";
    
        /**
         * 充值/提币记录
         */
        public static final String GET_ASSET_RECORD = "/app/addr/getGoldEntryAndExitList";
    
        /**
         * 提币页面配置
         */
        public static final String GET_WITHDRAW_CONFIG = "/app/addr/getTbFee";
    
        /**
         * 提币
         */
        public static final String WITHDRAW = "/app/addr/applyCharge";
    
        /**
         * 充值页面
         */
        public static final String RECHARGE = "/app/addr/createCoinAddr";
    
        /**
         * 充提币币种列表
         */
        public static final String WITHDRAW_LIST = "/app/addr/stockCode";
    
        /**
         * 发送验证码（注册）
         */
        public static final String SEND_SMS = "/app/user/getCode";
    
        /**
         * 提币地址添加删除
         */
        public static final String ADD_ADDRESS = "/app/currencyAddress/add";
    
        /**
         * 提币地址列表
         */
        public static final String ADDRESS_LIST = "/app/addr/addrList";
    
        /**
         * 账单其它
         */
        public static final String OTHER_ASSET_RECORD = "/app/user/moneyDetail";
    
        /**
         * 签到
         */
        public static final String SIGNIN = "/app/user/userSign";
        /**
         * 设置资金密码
         */
        public static final String SETFUNDPWD = "/app/user/updateTradePswd";
        /**
         * 添加反馈
         */
        public static final String FEEDBACK = "/app/user/contentMessage";
        /**
         * 开启谷歌验证
         */
        public static final String OPENCHROME = "/app/googleAuth/openGoolgeAuth";
        /**
         * 关闭谷歌验证
         */
        public static final String CLOSECHROME = "/app/googleAuth/closeGoolgeAuth";
        /**
         * 打开或关闭谷歌验证
         */
        public static String SET_GOOGLE_VERIFY = "/app/googleAuth/openGoolgeAuth";
```