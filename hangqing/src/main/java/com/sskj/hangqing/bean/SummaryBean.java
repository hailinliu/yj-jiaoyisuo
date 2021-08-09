package com.sskj.hangqing.bean;

import android.text.TextUtils;

import com.sskj.common.util.LanguageUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class SummaryBean {

    /**
     * id : 3
     * type : 4
     * name : null
     * code : ETH/USDT
     * engname : null
     * category : null
     * market : null
     * createTime : null
     * isDeleted : false
     * timestamp : 1561362959000
     * publishTime : null
     * publishNum : 0
     * publishPrice : null
     * publishWeb : null
     * whitePaper : null
     * remark : null
     * coinType : 2
     * coinOutType : 1
     * currencyNum : null
     * queryBlock : null
     * coinArea : 1
     * coinImg : null
     */

    private String id;
    private int type;
    private String name;
    private String code;
    private String engname;
    private String category;
    private String market;
    private String createTime;
    private boolean isDeleted;
    private long timestamp;
    private String publishTime;
    private String publishNum;
    private String publishPrice;
    private String publishWeb;
    private String whitePaper;
    private String remark;
    private String remarkUS;
    private int coinType;
    private int coinOutType;
    private String currencyNum;
    private String queryBlock;
    private int coinArea;
    private String coinImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPublishTime() {
        if (TextUtils.isEmpty(publishTime)){
            publishTime="0";
        }
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(publishTime)));
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(String publishNum) {
        this.publishNum = publishNum;
    }

    public String getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(String publishPrice) {
        this.publishPrice = publishPrice;
    }

    public String getPublishWeb() {
        return publishWeb;
    }

    public void setPublishWeb(String publishWeb) {
        this.publishWeb = publishWeb;
    }

    public String getWhitePaper() {
        return whitePaper;
    }

    public void setWhitePaper(String whitePaper) {
        this.whitePaper = whitePaper;
    }

    public String getRemarkUS() {
        return remarkUS;
    }

    public void setRemarkUS(String remarkUS) {
        this.remarkUS = remarkUS;
    }

    public String getRemark() {
        return LanguageUtil.isSimpleChinese() ? remark : remarkUS;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public int getCoinOutType() {
        return coinOutType;
    }

    public void setCoinOutType(int coinOutType) {
        this.coinOutType = coinOutType;
    }

    public String getCurrencyNum() {
        return currencyNum;
    }

    public void setCurrencyNum(String currencyNum) {
        this.currencyNum = currencyNum;
    }

    public String getQueryBlock() {
        return queryBlock;
    }

    public void setQueryBlock(String queryBlock) {
        this.queryBlock = queryBlock;
    }

    public int getCoinArea() {
        return coinArea;
    }

    public void setCoinArea(int coinArea) {
        this.coinArea = coinArea;
    }

    public String getCoinImg() {
        return coinImg;
    }

    public void setCoinImg(String coinImg) {
        this.coinImg = coinImg;
    }
}
