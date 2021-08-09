package com.sskj.lib.bean;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoinBean implements Serializable {


    /**
     * code : btc_usdt
     * name : BTC
     * date : 2018-08-23
     * time : 17:54:52
     * price : 6403.4300
     * open : 6673.9800
     * close : 6403.4300
     * high : 6686.8800
     * low : 6250.0000
     * volume : 18464.7247
     * buy : 6402.9400
     * sell : 6403.4300
     * change : -270.5500
     * changeRate : -4.05%
     * "coinImg": "/file/15668149465946e81815e-48a1-4eec-8e39-f7f433fbbb13.jpg",
     * cnyPrice :
     */

    /**
     * 优先级
     */
    private int priority;
    private String code;
    private String name;
    private String date;
    private String price;
    @SerializedName(value = "open", alternate = {"openPrice"})
    private String open;
    @SerializedName(value = "close", alternate = {"closePrice"})
    private String close;
    private String high;
    private String low;
    private String volume;
    private String buy;
    private String sell;
    private String change;
    private String changeRate;
    private String cnyPrice;

    @SerializedName(value = "img", alternate = {"coinImg"})
    private String img;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getTimeStamp() {
        Date parse = null;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
        return parse.getTime();
    }


    public CoinBean(String code) {
        this.code = code;
    }

    public CoinBean() {
    }

    public String getImg() {
        return BaseHttpConfig.BASE_IMG_URL + img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public boolean isUp() {
        try {
            if (Double.valueOf(change) >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }

    }

    public int getPriority() {
        return priority;
    }

    public CoinBean setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public String getLCode() {
        if (code == null) {
            return null;
        }
        return getCode().split("/")[0];
    }

    public String getRCode() {
        if (code == null) {
            return null;
        }
        return getCode().split("/")[1];
    }

    public String getCode() {
        if (code == null) {
            return null;
        }
        return code.replace("_", "/").toUpperCase();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public boolean equalZero(BigDecimal decimal){
        return   decimal.compareTo(BigDecimal.ZERO)==0;
    }

    public String getPrice() {
        if (price == null) {
            return "100";
        } else {
            return equalZero(new BigDecimal(CoinUtil.keepCoinPrice(code, price)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN))?"0":new BigDecimal(CoinUtil.keepCoinPrice(code, price)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).stripTrailingZeros().toPlainString();
        }
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return new BigDecimal(CoinUtil.keepCoinPrice(code, high)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).toPlainString();

    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return new BigDecimal(CoinUtil.keepCoinPrice(code, low)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).toPlainString();

    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume == null ? "0" : volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBuy() {
        return new BigDecimal(CoinUtil.keepCoinPrice(code, price)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).toPlainString();
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return new BigDecimal(CoinUtil.keepCoinPrice(code, price)).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).toPlainString();

    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getChange() {
        return change;
    }

    public String getOriginChangeRate() {
        if (TextUtils.isEmpty(changeRate)) {
            return "0";
        }
        return changeRate.replace("%", "");
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangeRate() {
        return TextUtils.isEmpty(changeRate) ? "0" : CommonUtil.dealPlus(changeRate);
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = changeRate;
    }

    public String getCnyPrice() {
        return CoinUtil.keepRMB(cnyPrice);
    }

    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }


}
