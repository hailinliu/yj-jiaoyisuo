package com.sskj.lib.bean;

import android.arch.persistence.room.Entity;

import com.sskj.lib.bean.enums.CoinEnum;
import com.sskj.lib.util.CoinUtil;

import javax.annotation.Nullable;

@Entity(tableName = "found", primaryKeys = {"id_found"})
public class FoundBean {

    /**
     * usdtTotal : 13172.27196504
     * btcTotal : 1.11205335
     */
    @Nullable
    private Integer id_found;
    private String usdtTotal;
    private String btcTotal;

    @Nullable
    public Integer getId_found() {
        return id_found;
    }

    public void setId_found(@Nullable Integer id_found) {
        this.id_found = id_found;
    }

    public String getUsdtTotal() {
        return CoinUtil.keepUSDT( usdtTotal);
    }

    public void setUsdtTotal(String usdtTotal) {
        this.usdtTotal = usdtTotal;
    }

    public String getBtcTotal() {
        return CoinUtil.keepCoinNum(CoinEnum.BTC.getCode(), btcTotal);
    }

    public void setBtcTotal(String btcTotal) {
        this.btcTotal = btcTotal;
    }
}
