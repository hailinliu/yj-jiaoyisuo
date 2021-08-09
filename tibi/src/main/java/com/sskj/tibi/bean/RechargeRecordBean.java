package com.sskj.tibi.bean;

import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class RechargeRecordBean {


    /**
     * id : 1
     * uid : 1
     * ordnum : RE2019082217024617789
     * walletAddr : RE2019082217024617789
     * wallet_address : null
     * rmb : 0.0000
     * usdFee : 10.0000
     * exchange_rate : 0.0000
     * status : 1
     * mark : 客户线上入金
     * type : 2
     * createTime : 2019-08-22 17:02:46
     * updated_at : 2019-08-22 17:02:46
     * arrival_at : 2019-08-22 17:02:46
     */

    private int id;
    private String walletAddr;

    private String usdFee;

    private int status;

    private int type;
    private String createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getWalletAddr() {
        return walletAddr;
    }

    public void setWalletAddr(String walletAddr) {
        this.walletAddr = walletAddr;
    }

    public String getUsdFee() {
        return CoinUtil.keepUSDT(usdFee);
    }

    public void setUsdFee(String usdFee) {
        this.usdFee = usdFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
