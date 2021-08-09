package com.sskj.lib.util;

import android.text.TextUtils;

public class StringUtil {
    public static String getFirstName(String realname) {
        if (TextUtils.isEmpty(realname)) {
            return "";
        } else {
            return realname.charAt(0) + "";
        }
    }
    public static String getEndName(String realname) {
        if (TextUtils.isEmpty(realname)) {
            return "";
        } else {
            return realname.charAt(realname.length()-1) + "";
        }
    }
    public static String getHideName(String realname) {
        if (TextUtils.isEmpty(realname)) {
            return "";
        } else {
            return realname.charAt(0) + "**";
        }
    }
}
