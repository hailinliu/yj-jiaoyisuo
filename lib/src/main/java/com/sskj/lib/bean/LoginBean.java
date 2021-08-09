package com.sskj.lib.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    /**
     * avatar : string
     * country : {"areaCode":"string","enName":"string","language":"string","localCurrency":"string","sort":0,"zhName":"string"}
     * email : string
     * emailRemind : string
     * googleState : 0
     * id : 0
     * integration : 0
     * kycStatus : 0
     * lastLoginTime : 2020-12-08T01:18:01.234Z
     * location : {"city":"string","country":"string","district":"string","province":"string"}
     * memberGradeId : 0
     * memberLevel : 0
     * mobile : string
     * promotionCode : string
     * promotionPrefix : string
     * realName : string
     * seFeeSwitch : true
     * signInAbility : true
     * signInActivity : true
     * smsRemind : string
     * token : string
     * tokenExpireTime : 2020-12-08T01:18:01.234Z
     * tokenWeb : string
     * tokenWebExpireTime : 2020-12-08T01:18:01.234Z
     * username : string
     */

    private String avatar;
    private CountryBean country;
    private String email;
    private String emailRemind;
    private int googleState;
    private int id;
    private int integration;
    private int kycStatus;
    private String lastLoginTime;
    private LocationBean location;
    private int memberGradeId;
    private String memberLevel;
    private String mobile;
    private String promotionCode;
    private String promotionPrefix;
    private String realName;
    private boolean seFeeSwitch;
    private boolean signInAbility;
    private boolean signInActivity;
    private String smsRemind;
    private String token;
    private String tokenExpireTime;
    private String tokenWeb;
    private String tokenWebExpireTime;
    private String username;

    public LoginBean(String avatar, CountryBean country, String email, String emailRemind, int googleState, int id, int integration, int kycStatus, String lastLoginTime, LocationBean location, int memberGradeId, String memberLevel, String mobile, String promotionCode, String promotionPrefix, String realName, boolean seFeeSwitch, boolean signInAbility, boolean signInActivity, String smsRemind, String token, String tokenExpireTime, String tokenWeb, String tokenWebExpireTime, String username) {
        this.avatar = avatar;
        this.country = country;
        this.email = email;
        this.emailRemind = emailRemind;
        this.googleState = googleState;
        this.id = id;
        this.integration = integration;
        this.kycStatus = kycStatus;
        this.lastLoginTime = lastLoginTime;
        this.location = location;
        this.memberGradeId = memberGradeId;
        this.memberLevel = memberLevel;
        this.mobile = mobile;
        this.promotionCode = promotionCode;
        this.promotionPrefix = promotionPrefix;
        this.realName = realName;
        this.seFeeSwitch = seFeeSwitch;
        this.signInAbility = signInAbility;
        this.signInActivity = signInActivity;
        this.smsRemind = smsRemind;
        this.token = token;
        this.tokenExpireTime = tokenExpireTime;
        this.tokenWeb = tokenWeb;
        this.tokenWebExpireTime = tokenWebExpireTime;
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public CountryBean getCountry() {
        return country;
    }

    public void setCountry(CountryBean country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailRemind() {
        return emailRemind;
    }

    public void setEmailRemind(String emailRemind) {
        this.emailRemind = emailRemind;
    }

    public int getGoogleState() {
        return googleState;
    }

    public void setGoogleState(int googleState) {
        this.googleState = googleState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public int getMemberGradeId() {
        return memberGradeId;
    }

    public void setMemberGradeId(int memberGradeId) {
        this.memberGradeId = memberGradeId;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getPromotionPrefix() {
        return promotionPrefix;
    }

    public void setPromotionPrefix(String promotionPrefix) {
        this.promotionPrefix = promotionPrefix;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean isSeFeeSwitch() {
        return seFeeSwitch;
    }

    public void setSeFeeSwitch(boolean seFeeSwitch) {
        this.seFeeSwitch = seFeeSwitch;
    }

    public boolean isSignInAbility() {
        return signInAbility;
    }

    public void setSignInAbility(boolean signInAbility) {
        this.signInAbility = signInAbility;
    }

    public boolean isSignInActivity() {
        return signInActivity;
    }

    public void setSignInActivity(boolean signInActivity) {
        this.signInActivity = signInActivity;
    }

    public String getSmsRemind() {
        return smsRemind;
    }

    public void setSmsRemind(String smsRemind) {
        this.smsRemind = smsRemind;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(String tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getTokenWeb() {
        return tokenWeb;
    }

    public void setTokenWeb(String tokenWeb) {
        this.tokenWeb = tokenWeb;
    }

    public String getTokenWebExpireTime() {
        return tokenWebExpireTime;
    }

    public void setTokenWebExpireTime(String tokenWebExpireTime) {
        this.tokenWebExpireTime = tokenWebExpireTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class CountryBean {
        /**
         * areaCode : string
         * enName : string
         * language : string
         * localCurrency : string
         * sort : 0
         * zhName : string
         */

        private String areaCode;
        private String enName;
        private String language;
        private String localCurrency;
        private int sort;
        private String zhName;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLocalCurrency() {
            return localCurrency;
        }

        public void setLocalCurrency(String localCurrency) {
            this.localCurrency = localCurrency;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getZhName() {
            return zhName;
        }

        public void setZhName(String zhName) {
            this.zhName = zhName;
        }
    }

    public static class LocationBean implements Serializable{
        /**
         * city : string
         * country : string
         * district : string
         * province : string
         */

        private String city;
        private String country;
        private String district;
        private String province;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
