package com.sskj.lib.bean;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import java.io.Serializable;

import javax.annotation.Nullable;

/**
 * Created by lv on 18-5-7.
 */
@Entity(primaryKeys = {"id"})
public class UserData implements Serializable {

    @Nullable
    private Integer id;


    @Embedded
    private FoundBean found;//资产
    /**
     * headUrl : http://127.0.0.1/file/15585972559210883735f-bf31-47c0-9fa0-e14dec466aee.jpg
     * vipLevel : null
     * tel : 15869656985
     * userUid : 123456
     * signStatus : 0
     * useFund : 0
     * username : 123456
     * idCard :
     * email : 4216552@qq.com
     * tradePswdStatus : 1
     * basicAuthenticationStatus : 1
     * highAuthenticationStatus : 3
     * googleAuthenticationStatus : 0
     * googleOpenCloseStatus : 0
     * shopAuthenticationStatus : 1
     * payWay : 0
     * found : {"usdtTotal":13172.27196504,"btcTotal":1.11205335}
     */

    private String headUrl;
    private String vipLevel;
    private String tel;
    private String userUid;
    private int signStatus;
    private String useFund;
    private String username;
    private String idCard;
    private String email;
    private int tradePswdStatus;
    private int basicAuthenticationStatus;
    private int highAuthenticationStatus;
    private int googleAuthenticationStatus;
    private int googleOpenCloseStatus;
    private int shopAuthenticationStatus;
    private int payWay;
    private int lawWaring;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FoundBean getFound() {
        return found;
    }

    public void setFound(FoundBean found) {
        this.found = found;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getUseFund() {
        return useFund;
    }

    public void setUseFund(String useFund) {
        this.useFund = useFund;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTradePswdStatus() {
        return tradePswdStatus;
    }

    public void setTradePswdStatus(int tradePswdStatus) {
        this.tradePswdStatus = tradePswdStatus;
    }

    public int getBasicAuthenticationStatus() {
        return basicAuthenticationStatus;
    }

    public void setBasicAuthenticationStatus(int basicAuthenticationStatus) {
        this.basicAuthenticationStatus = basicAuthenticationStatus;
    }

    public int getHighAuthenticationStatus() {
        return highAuthenticationStatus;
    }

    public void setHighAuthenticationStatus(int highAuthenticationStatus) {
        this.highAuthenticationStatus = highAuthenticationStatus;
    }

    public int getGoogleAuthenticationStatus() {
        return googleAuthenticationStatus;
    }

    public void setGoogleAuthenticationStatus(int googleAuthenticationStatus) {
        this.googleAuthenticationStatus = googleAuthenticationStatus;
    }

    public int getGoogleOpenCloseStatus() {
        return googleOpenCloseStatus;
    }

    public void setGoogleOpenCloseStatus(int googleOpenCloseStatus) {
        this.googleOpenCloseStatus = googleOpenCloseStatus;
    }

    public int getShopAuthenticationStatus() {
        return shopAuthenticationStatus;
    }

    public void setShopAuthenticationStatus(int shopAuthenticationStatus) {
        this.shopAuthenticationStatus = shopAuthenticationStatus;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getLawWaring() {
        return lawWaring;
    }

    public void setLawWaring(int lawWaring) {
        this.lawWaring = lawWaring;
    }
}
