package com.sskj.tibi.bean;

import android.text.TextUtils;

import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class WithdrawRecordBean {


    /**
     * id : 1
     * uid : 1
     * with_num : 123456
     * walletAddr : null
     * usdFee : 0.0000
     * handling_fee : 0.0000
     * actual : 0.0000
     * inspectStatus : 1
     * mark : 用户提币
     * createTime : 2019-08-26 20:36:30
     * updated_at : 2019-08-26 20:36:32
     * inspectTime : null
     */

    private int id;

    private String walletAddr;
    private String usdFee;

    private int inspectStatus;
    private String createTime;
    private String inspectTime;
    private String inspectReason;

    public String getInspectReason() {
        return inspectReason;
    }

    public void setInspectReason(String inspectReason) {
        this.inspectReason = inspectReason;
    }

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
        return usdFee;
    }

    public void setUsdFee(String usdFee) {
        this.usdFee = usdFee;
    }


    public int getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(int inspectStatus) {
        this.inspectStatus = inspectStatus;
    }


    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getInspectTime() {
        return TextUtils.isEmpty(inspectTime) ? "--" : TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(inspectTime)));
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }
}
