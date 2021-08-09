package com.sskj.tibi.bean;

import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class OtherRecordBean {


   private String detail;
   private String stockCode;
   private String createTime;
   private String money;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMoney() {
        return CoinUtil.keepUSDT(money);
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
