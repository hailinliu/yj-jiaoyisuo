package com.sskj.hangqing.bean;

public class TurnoverBean {

    /**
     * symbol : BTC/USDT
     * price : 34518
     * priceStr : 34518.00
     * amount : 0.5
     * amountStr : 0.5000
     * buyTurnover : null
     * sellTurnover : null
     * direction : null
     * isBuyerMaker : null
     * buyOrderId : null
     * sellOrderId : null
     * time : 1610528362255
     * senderUuid : null
     */

    private String symbol;
    private String price;
    private String priceStr;
    private String amount;
    private String amountStr;
    private String buyTurnover;
    private String sellTurnover;
    private String direction="BUY";
    private String isBuyerMaker;
    private String buyOrderId;
    private String sellOrderId;
    private long time;
    private String senderUuid;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountStr() {
        return amountStr;
    }

    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
    }

    public String getBuyTurnover() {
        return buyTurnover;
    }

    public void setBuyTurnover(String buyTurnover) {
        this.buyTurnover = buyTurnover;
    }

    public String getSellTurnover() {
        return sellTurnover;
    }

    public void setSellTurnover(String sellTurnover) {
        this.sellTurnover = sellTurnover;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(String isBuyerMaker) {
        this.isBuyerMaker = isBuyerMaker;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(String sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSenderUuid() {
        return senderUuid;
    }

    public void setSenderUuid(String senderUuid) {
        this.senderUuid = senderUuid;
    }
}
