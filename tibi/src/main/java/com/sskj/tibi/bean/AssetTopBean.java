package com.sskj.tibi.bean;

import com.sskj.lib.bean.enums.CoinEnum;
import com.sskj.lib.util.CoinUtil;

public class AssetTopBean {

    /**
     * usdtTotal : 13172.27196504
     * btcTotal : 1.11205335
     */

    private String usdtTotal;
    private String btcTotal;

    public String getUsdtTotal() {
        return usdtTotal;
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
