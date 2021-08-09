package com.sskj.lib.bean;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import java.io.Serializable;


public class NewUserData implements Serializable {


        /**
         * username : 15537120335
         * location : {"country":"中国","province":null,"city":null,"district":null}
         * memberLevel : 0
         * token : null
         * realName : null
         * country : {"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0}
         * avatar : null
         * promotionCode : U0000682K
         * id : 68
         * googleState : 0
         * kycStatus : 0
         * tokenExpireTime : 1608097507000
         * seFeeSwitch : true
         * tokenWeb : null
         * tokenWebExpireTime : null
         * email : null
         * promotionPrefix : null
         * signInAbility : true
         * signInActivity : false
         * memberGradeId : 1
         * integration : 0
         * mobile : 155****0335
         * lastLoginTime : 1607492707000
         * emailRemind : 0
         * smsRemind : 0
         */

        private String username;
        private LocationBean location;
        private int memberLevel;
        private String token;
        private String realName;
        private CountryBean country;
        private String avatar;
        private String promotionCode;
        private int id;
        private int googleState;
        private int kycStatus;
        private long tokenExpireTime;
        private boolean seFeeSwitch;
        private String tokenWeb;
        private String tokenWebExpireTime;
        private String email;
        private String promotionPrefix;
        private boolean signInAbility;
        private boolean signInActivity;
        private int memberGradeId;
        private int integration;
        private String mobile;
        private long lastLoginTime;
        private String emailRemind;
        private String smsRemind;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(int memberLevel) {
            this.memberLevel = memberLevel;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public CountryBean getCountry() {
            return country;
        }

        public void setCountry(CountryBean country) {
            this.country = country;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPromotionCode() {
            return promotionCode;
        }

        public void setPromotionCode(String promotionCode) {
            this.promotionCode = promotionCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoogleState() {
            return googleState;
        }

        public void setGoogleState(int googleState) {
            this.googleState = googleState;
        }

        public int getKycStatus() {
            return kycStatus;
        }

        public void setKycStatus(int kycStatus) {
            this.kycStatus = kycStatus;
        }

        public long getTokenExpireTime() {
            return tokenExpireTime;
        }

        public void setTokenExpireTime(long tokenExpireTime) {
            this.tokenExpireTime = tokenExpireTime;
        }

        public boolean isSeFeeSwitch() {
            return seFeeSwitch;
        }

        public void setSeFeeSwitch(boolean seFeeSwitch) {
            this.seFeeSwitch = seFeeSwitch;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPromotionPrefix() {
            return promotionPrefix;
        }

        public void setPromotionPrefix(String promotionPrefix) {
            this.promotionPrefix = promotionPrefix;
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

        public int getMemberGradeId() {
            return memberGradeId;
        }

        public void setMemberGradeId(int memberGradeId) {
            this.memberGradeId = memberGradeId;
        }

        public int getIntegration() {
            return integration;
        }

        public void setIntegration(int integration) {
            this.integration = integration;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getEmailRemind() {
            return emailRemind;
        }

        public void setEmailRemind(String emailRemind) {
            this.emailRemind = emailRemind;
        }

        public String getSmsRemind() {
            return smsRemind;
        }

        public void setSmsRemind(String smsRemind) {
            this.smsRemind = smsRemind;
        }

       /* public static class LocationBean {


            private String country;
            private String province;
            private String city;
            private String district;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }
        }*/

       /* public static class CountryBean {



            private String zhName;
            private String enName;
            private String areaCode;
            private String language;
            private String localCurrency;
            private int sort;

            public String getZhName() {
                return zhName;
            }

            public void setZhName(String zhName) {
                this.zhName = zhName;
            }

            public String getEnName() {
                return enName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
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
        }*/
    }

