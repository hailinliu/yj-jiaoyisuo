package com.sskj.level.bean;

import com.sskj.common.base.App;
import com.sskj.level.R;
import com.sskj.level.util.CoinUtil;
import com.sskj.lib.util.NumberUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;
import java.util.List;

public class ContractNoteBean {
//    @TableId(value = "id", type = IdType.AUTO)
//    private Long id;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean3))
//    @TableField("stock_user_id")
//    private Long stockUserId;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean4))
//    @TableField("stock_code")
//    private String stockCode;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean5))
//    @TableField("stock_name")
//    private String stockName;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean6))
//    @TableField("stock_type")
//    private Integer stockType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean5))
//    @TableField("trade_type")
//    private Integer tradeType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean7))
//    @TableField("bill_price_type")
//    private Integer billPriceType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean7))
//    @TableField("buy_bill_type")
//    private Integer buyBillType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean9))
//    @TableField("entrust_lot")
//    private Integer entrustLot;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean10))
//    @TableField("entrust_success_lot")
//    private Integer entrustSuccessLot;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean11))
//    @TableField("entrust_time")
//    private Date entrustTime;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean8))
//    @TableField("entrust_no")
//    private String entrustNo;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean12))
//    @TableField("entrust_success_time")
//    private Date entrustSuccessTime;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean12))
//    @TableField("lot_size")
//    private Integer lotSize;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean14))
//    @TableField("buy_price")
//    private BigDecimal buyPrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean26))
//    @TableField("buy_fund_type")
//    private BigDecimal buyFundType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean16))
//    @TableField("buy_fee")
//    private BigDecimal buyFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean17))
//    @TableField("buy_margin_fee")
//    private BigDecimal buyMarginFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean18))
//    @TableField("buy_deal_fee")
//    private BigDecimal buyDealFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean19))
//    @TableField("buy_deal_usd_fee")
//    private BigDecimal buyDealUsdFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean20))
//    @TableField("sell_price")
//    private BigDecimal sellPrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean21))
//    @TableField("sell_type")
//    private Integer sellType;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean25))
//    @TableField("sliding_scale_price")
//    private BigDecimal slidingScalePrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean23))
//    @TableField("float_money")
//    private BigDecimal floatMoney;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean24))
//    @TableField("win_lose_fee")
//    private BigDecimal winLoseFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean20))
//    @TableField("overnight_fee")
//    private BigDecimal overnightFee;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean21))
//    @TableField("stop_loss_price")
//    private BigDecimal stopLossPrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean22))
//    @TableField("stop_profit_price")
//    private BigDecimal stopProfitPrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean28))
//    @TableField("bill_status")
//    private Integer billStatus;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean23))
//    @TableField("create_time")
//    private Date createTime;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean24))
//    @TableField("is_deleted")
//    private Boolean isDeleted;
//
//    @TableField("timestamp")
//    private Date timestamp;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean27))
//    @TableField("stop_profit")
//    private Double stopProfit;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean28))
//    @TableField("stop_loss")
//    private Double stopLoss;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean29))
//    @TableField("spread")
//    private BigDecimal spread;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.levelcontractNoteBean34))
//    @TableField("market_price")
//    private BigDecimal marketPrice;
//
//    @ApiModelProperty(value = App.INSTANCE.getString(R.string.leveltradeListBean30))
//    @TableField("stock_lever")
//    private BigDecimal stockLever;


    /**
     * id : 12
     * tran_num : dsfds489489
     * uid : 13
     * pid : 1
     * stockCode : btc_usdt
     * buyPrice : 0.0000
     * entrustLot : 1
     * price : 0.0000
     * totalprice : 0.0000
     * buyBillType : 1
     * stopProfitPrice : 0.0000
     * stopLossPrice : 0.0000
     * sellPrice : 0.0000
     * floatMoney : 0.0000
     * buyDealFee : 0.0000
     * overnightFee : 0.0000
     * buyMarginFee : 0.0000
     * sellType : 3
     * poit_win : 0.0000
     * poit_loss : 0.0000
     * leverage : 50
     * distribute_income : 0
     * entrustSuccessTime : 2019-08-29 15:22:11
     * createTime : 2019-08-29 15:22:11
     * entrustTime : 2019-08-29 15:22:11
     */
private List<DataBean> list;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    public static class DataBean{
    private String id;
    private String stockCode;
    private String buyPrice;
    private String entrustLot;

    private String buyBillType;
    private String stopProfitPrice;
    private String stopLossPrice;
    private String sellPrice;

    private String buyDealFee;
    private String overnightFee;
    private String buyMarginFee;
    private String sellType;

    private String entrustSuccessTime;
    private String entrustTime;
    private String createTime;
    private String billPriceType;
    private String stockLever;
    private String winLoseFee;

    public String getWinLoseFee() {
        return CoinUtil.keepCoinMoney(stockCode,winLoseFee);
    }

    public void setWinLoseFee(String winLoseFee) {
        this.winLoseFee = winLoseFee;
    }

    public String getStockLever() {
        return Double.valueOf(stockLever).intValue() + "";
    }

    public void setStockLever(String stockLever) {
        this.stockLever = stockLever;
    }

    public String getBillPriceType() {
        return billPriceType;
    }

    public void setBillPriceType(String billPriceType) {
        this.billPriceType = billPriceType;
    }

    public String getMarketLimit() {
        if (billPriceType.equals(MarketLimitEnum.MARKET.getType())) {
            return App.INSTANCE.getString(R.string.leveltradeListBean1);
        } else if (billPriceType.equals(MarketLimitEnum.LIMIT.getType())) {
            return App.INSTANCE.getString(R.string.leveltradeListBean2);
        } else {
            return App.INSTANCE.getString(R.string.leveltradeListBean1);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLCode() {
        return stockCode.split("/")[0];
    }


    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getBuyPrice() {
        return CoinUtil.keepCoinPrice(stockCode,buyPrice);

    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getEntrustLot() {
        return CoinUtil.keepCoinNum(stockCode,entrustLot);
    }

    public void setEntrustLot(String entrustLot) {
        this.entrustLot = entrustLot;
    }


    public String getBuyBillType() {
        return buyBillType;
    }

    public void setBuyBillType(String buyBillType) {
        this.buyBillType = buyBillType;
    }

    public String getStopProfitPrice() {
        return CoinUtil.keepCoinPrice(stockCode,stopProfitPrice);

    }

    public void setStopProfitPrice(String stopProfitPrice) {
        this.stopProfitPrice = stopProfitPrice;
    }

    public String getStopLossPrice() {
        return CoinUtil.keepCoinPrice(stockCode,stopLossPrice);

    }

    public void setStopLossPrice(String stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public String getSellPrice() {
        return CoinUtil.keepCoinPrice(stockCode,sellPrice);

    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }


    public String getBuyDealFee() {
        return CoinUtil.keepCoinMoney(stockCode,buyDealFee);

    }

    public void setBuyDealFee(String buyDealFee) {
        this.buyDealFee = buyDealFee;
    }

    public String getOvernightFee() {
        return CoinUtil.keepCoinMoney(stockCode,overnightFee);

    }

    public void setOvernightFee(String overnightFee) {
        this.overnightFee = overnightFee;
    }

    public String getBuyMarginFee() {
        return CoinUtil.keepCoinMoney(stockCode,buyMarginFee);

    }

    public void setBuyMarginFee(String buyMarginFee) {
        this.buyMarginFee = buyMarginFee;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }


    public String getEntrustSuccessTime() {
        return TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(entrustSuccessTime)));

    }

    public void setEntrustSuccessTime(String entrustSuccessTime) {
        this.entrustSuccessTime = entrustSuccessTime;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(createTime)));

    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEntrustTime() {
        return TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(entrustTime)));
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }
}
}