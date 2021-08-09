package com.sskj.level.bean;

import com.sskj.common.base.App;
import com.sskj.level.R;
import com.sskj.level.util.CoinUtil;
import com.sskj.lib.util.NumberUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.math.BigDecimal;
import java.util.Date;

public class TradeListBean {
    private String id;
    private String entrustNo;//撤单 修改 平仓
    private String stockCode;
    private String buyPrice;//购买价格
    private String entrustLot;//购买数量
    private String buyBillType;//买入卖出类型
    private String billPriceType;//1市价2限价类型
    private String stopProfitPrice;//止盈价
    private String stopLossPrice;//止损价
    private String buyDealFee;//手续费
    private String buyMarginFee;//保证金
    private String overnightFee;//过夜费
    private String stockLever;//杠杆

    private String entrustSuccessTime;//建仓时间
    private String entrustTime;//委托时间
    private String winLoseMoney;//浮动盈亏
    private String countUsable;//剩余数量
    private String newPrice;//最新价
    private String createTime;//持仓单建仓价
    private String lotSize;//合约面值


    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
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

    public String getNewPrice() {
        return CoinUtil.keepCoinPrice(stockCode,newPrice);
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(createTime)));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCountUsable() {
        return NumberUtil.keepNoZero(Double.valueOf(countUsable));
    }

    public void setCountUsable(String countUsable) {
        this.countUsable = countUsable;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getLCode() {
        return stockCode.split("/")[0];
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return NumberUtil.keepNoZero(entrustLot);
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

    public String getBuyDealFee() {
        return CoinUtil.keepCoinMoney(stockCode,buyDealFee);
    }

    public void setBuyDealFee(String buyDealFee) {
        this.buyDealFee = buyDealFee;
    }

    public String getBuyMarginFee() {
        return CoinUtil.keepCoinMoney(stockCode,buyMarginFee);

    }

    public void setBuyMarginFee(String buyMarginFee) {
        this.buyMarginFee = buyMarginFee;
    }

    public String getOvernightFee() {
        return CoinUtil.keepCoinMoney(stockCode,overnightFee);
    }

    public void setOvernightFee(String overnightFee) {
        this.overnightFee = overnightFee;
    }


    public String getStockLever() {
        return Double.valueOf(stockLever).intValue() + "";
    }

    public void setStockLever(String stockLever) {
        this.stockLever = stockLever;
    }

    public String getEntrustSuccessTime() {
        return entrustSuccessTime == null ? "--" : TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(entrustSuccessTime)));
    }

    public void setEntrustSuccessTime(String entrustSuccessTime) {
        this.entrustSuccessTime = entrustSuccessTime;
    }

    public String getEntrustTime() {
        return TimeFormatUtil.SF_FORMAT_G.format(new Date(Long.valueOf(entrustTime)));

    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    /**
     * 浮动盈亏
     * @return
     */
    public String getWinLoseMoney() {
        if (BuySellEnum.BUY.getType().equals(buyBillType)) {
            winLoseMoney = new BigDecimal(newPrice)
                    .subtract(new BigDecimal(buyPrice))
                    .multiply(new BigDecimal(countUsable))
                    .multiply(new BigDecimal(lotSize))
                    .toPlainString();
        } else {
            winLoseMoney = new BigDecimal(buyPrice)
                    .subtract(new BigDecimal(newPrice))
                    .multiply(new BigDecimal(countUsable))
                    .multiply(new BigDecimal(lotSize))
                    .toPlainString();

        }
        return CoinUtil.keepCoinMoney(stockCode, winLoseMoney);

    }


    public void setWinLoseMoney(String winLoseMoney) {
        this.winLoseMoney = winLoseMoney;
    }


//
//    private Long id;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean3)
//
//    private Long stockUserId;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean4))
//
//    private String stockCode;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean5))
//
//    private String stockName;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean6))
//
//    private Integer stockType;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean7))
//
//    private Integer billPriceType;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean8))
//
//    private String entrustNo;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean9))
//    private Integer entrustLot;
//
//    //App.INSTANCE.getString(R.string.leveltradeListBean10))
//    private Integer entrustUsableLot;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean11))
//
//    private Date entrustTime;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean12))
//
//    private Integer lotSize;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean13))
//
//    private Integer count;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean14))
//
//    private Integer countUsable;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean15))
//
//    private BigDecimal buyPrice;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean16))
//
//    private BigDecimal buyFee;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean17))
//
//    private BigDecimal buyMarginFee;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean18))
//
//    private Integer buyBillType;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean19))
//
//    private BigDecimal buyDealFee;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean20))
//
//    private BigDecimal overnightFee;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean21))
//
//    private BigDecimal stopLossPrice;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean22))
//
//    private BigDecimal stopProfitPrice;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean23))
//
//    private Date createTime;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean24))
//
//    private Boolean isDeleted;
//
//
//    private Date timestamp;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean25))
//
//    private BigDecimal slidingScalePrice;
//
//    private BigDecimal winLoseMoney;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean26))
//
//    private BigDecimal buyFundType;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean27))
//
//    private Double stopProfit;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean28))
//
//    private Double stopLoss;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean29))
//
//    private BigDecimal spread;
//
//    // App.INSTANCE.getString(R.string.leveltradeListBean30))
//
//    private BigDecimal stockLever;
//
//    //爆仓预处理时保存的最新价
//    private BigDecimal tempNewPrice;


}
