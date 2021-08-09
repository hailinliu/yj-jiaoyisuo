package com.sskj.lib.util;

import android.text.TextUtils;

public class HideUtil {
    // 隐藏手机号
    public static String getPhoneHide(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return "";
        }
        try {
            return mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        } catch (Exception e) {
            return mobile;
        }
    }

    // 隐藏银行卡号
    public static String getBankCardHide(String idcard) {
        if (TextUtils.isEmpty(idcard)) {
            return "";
        }
        try {
            return idcard.substring(0, 4) + "********" + idcard.substring(idcard.length()-4);
        } catch (Exception e) {
            return idcard;
        }
    }
    // 隐藏微信支付宝账号
    public static String getWxAlipayHide(String idcard) {
        if (TextUtils.isEmpty(idcard)) {
            return "";
        }
        try {
            return idcard.substring(0, 3) + "****" + idcard.substring(idcard.length()-4);
        } catch (Exception e) {
            return idcard;
        }
    }

    // 隐藏邮箱号
    public static String getEmialAddress(String email) {
        if (TextUtils.isEmpty(email)) {
            return "";
        }
        try {
            String num = email.split("@")[0];
            if (num.length() <= 5) {
                return email.substring(0, 1) + "****" + email.substring(num.length(), email.length());
            } else {
                return email.substring(0, 1) + "****" + email.substring(5, email.length());
            }
        } catch (Exception e) {
            return email;
        }
    }
}
