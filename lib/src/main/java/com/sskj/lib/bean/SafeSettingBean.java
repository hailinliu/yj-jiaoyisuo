package com.sskj.lib.bean;

import android.arch.persistence.room.Entity;

import java.io.Serializable;

@Entity(primaryKeys = {"id"})
public class SafeSettingBean implements Serializable {

    /**
     * username : 15537120335
     * id : 68
     * createTime : 2020-12-07 17:33:35
     * realVerified : 0
     * emailVerified : 0
     * phoneVerified : 1
     * loginVerified : 1
     * fundsVerified : 0
     * googleVerified : 0
     * realAuditing : 0
     * mobilePhone : 155****0335
     * email : null
     * realName : null
     * realNameRejectReason : null
     * idCard : null
     * avatar : null
     * accountVerified : 0
     * googleStatus : 0
     * transactions : 0
     * transactionTime : null
     * level : 0
     * integration : 0
     * kycStatus : 0
     * memberGradeId : 1
     * securityMessage : 请至少开启2项安全验证
     * securityCount : 1
     * memberLevel : 0
     * isQuick : 1
     */

    private String username;
    private int id;
    private String createTime;
    private int realVerified;
    private int emailVerified;
    private int phoneVerified;
    private int loginVerified;
    private int fundsVerified;
    private int googleVerified;
    private int realAuditing;
    private String mobilePhone;
    private String email;
    private String realName;
    private String realNameRejectReason;
    private String idCard;
    private String avatar;
    private int accountVerified;
    private int googleStatus;
    private int transactions;
    private String transactionTime;
    private int level;
    private int integration;
    private int kycStatus;
    private int memberGradeId;
    private String securityMessage;
    private int securityCount;
    private int memberLevel;
    private int isQuick;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getRealVerified() {
        return realVerified;
    }

    public void setRealVerified(int realVerified) {
        this.realVerified = realVerified;
    }

    public int getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(int emailVerified) {
        this.emailVerified = emailVerified;
    }

    public int getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(int phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public int getLoginVerified() {
        return loginVerified;
    }

    public void setLoginVerified(int loginVerified) {
        this.loginVerified = loginVerified;
    }

    public int getFundsVerified() {
        return fundsVerified;
    }

    public void setFundsVerified(int fundsVerified) {
        this.fundsVerified = fundsVerified;
    }

    public int getGoogleVerified() {
        return googleVerified;
    }

    public void setGoogleVerified(int googleVerified) {
        this.googleVerified = googleVerified;
    }

    public int getRealAuditing() {
        return realAuditing;
    }

    public void setRealAuditing(int realAuditing) {
        this.realAuditing = realAuditing;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameRejectReason() {
        return realNameRejectReason;
    }

    public void setRealNameRejectReason(String realNameRejectReason) {
        this.realNameRejectReason = realNameRejectReason;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(int accountVerified) {
        this.accountVerified = accountVerified;
    }

    public int getGoogleStatus() {
        return googleStatus;
    }

    public void setGoogleStatus(int googleStatus) {
        this.googleStatus = googleStatus;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(int kycStatus) {
        this.kycStatus = kycStatus;
    }

    public int getMemberGradeId() {
        return memberGradeId;
    }

    public void setMemberGradeId(int memberGradeId) {
        this.memberGradeId = memberGradeId;
    }

    public String getSecurityMessage() {
        return securityMessage;
    }

    public void setSecurityMessage(String securityMessage) {
        this.securityMessage = securityMessage;
    }

    public int getSecurityCount() {
        return securityCount;
    }

    public void setSecurityCount(int securityCount) {
        this.securityCount = securityCount;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public int getIsQuick() {
        return isQuick;
    }

    public void setIsQuick(int isQuick) {
        this.isQuick = isQuick;
    }
}

