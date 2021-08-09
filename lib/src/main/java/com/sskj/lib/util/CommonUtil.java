package com.sskj.lib.util;

import android.text.TextUtils;

import com.sskj.common.base.App;
import com.sskj.lib.R;

import static com.sskj.common.util.MD5Util.getMd5Value;


public class CommonUtil {
    private static final String SALT = "c077292f-2dc5-493e-a965-00659318c889";

    /**
     * 处理钱包地址
     *
     * @param wallet
     * @return
     */
    public static String dealWallet(String wallet) {
        if (wallet == null) {
            return "";
        }
        String start = wallet.substring(0, 6);
        String end = wallet.substring(wallet.length() - 6, wallet.length());
        return start + "******" + end;
    }

    /**
     * 处理币种金额
     *
     * @param money
     * @return
     */
    public static String dealCoin(String money) {
        if (TextUtils.isEmpty(money)) {
            return "0.0000";
        }
        return NumberUtil.keep(money, 4);
    }

    /**
     * 处理人民币金额
     *
     * @param money
     * @return
     */
    public static String dealCNY(String money) {
        if (TextUtils.isEmpty(money)) {
            return "0.00";
        }
        return NumberUtil.keep(money, 2);
    }

    /**
     * 处理交易号 0x53c845d04d5a2aee3212a2554ed1e378e27b63cb36eeac0d4ebdb48c5c9af20b
     */

    public static String dealTradeNum(String tradeNum) {
        if (tradeNum == null) {
            return "";
        }
        if (tradeNum.length() < 9) {
            return "--";
        }
        String start = tradeNum.substring(0, 8);
        String end = tradeNum.substring(tradeNum.length() - 8, tradeNum.length());
        return start + "********" + end;
    }

    /**
     * 处理手机号
     */

    public static String dealTel(String tel) {
        if (tel == null) {
            return "";
        }
        if (tel.length() < 11) {
            return "--";
        }
        String start = tel.substring(0, 3);
        String end = tel.substring(tel.length() - 4, tel.length());
        return start + "****" + end;
    }

    public static String dealReuqestCode(String code) {
        if (code == null) {
            return null;
        }
        return code.replace("_", "/").toUpperCase();
    }

    public static String dealLanguage(String word) {
        switch (word) {
            case "zh_TW":
                return App.INSTANCE.getString(R.string.libcommonUtil1);
            case "zh_CN":
                return App.INSTANCE.getString(R.string.libcommonUtil2);
            case "en":
                return "English";
            case "ko":
                return "Korea";
            case "ja":
                return "Japan";
            default:
                return word;
        }
    }

    public static String rdealLanguage(String word) {
        if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil1))) {
            return "zh_tw";
        } else if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil2))) {
            return "zh_cn";
        } else if (word.equals("English")) {
            return "en";
        } else if (word.equals("Korea")) {
            return "ko";
        } else if (word.equals("Japan")) {
            return "ja";
        } else {
            return word;
        }
    }
    public static String rdealLanguage1(String word) {
        if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil1))) {
            return "zh_TW";
        } else if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil2))) {
            return "zh_CN";
        } else if (word.equals("English")) {
            return "en";
        } else if (word.equals("Korea")) {
            return "ko";
        } else if (word.equals("Japan")) {
            return "ja";
        } else {
            return word;
        }
    }
    public static String getHeaderLanguage(String word){
        if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil1))) {
            return "zh-tw";
        } else if (word.equals(App.INSTANCE.getString(R.string.libcommonUtil2))) {
            return "zh-tw";
        } else if (word.equals("English")) {
            return "en-us";
        } else if (word.equals("Korea")) {
            return "ko-kr";
        } else if (word.equals("Japan")) {
            return "ja-jp";
        } else {
            return word;
        }
    }

    public static String getFiveMd5Value(String sSecret) {

        return getMd5Value(sSecret+SALT);
    }

    public static String dealPlus(String tel) {
        if (tel == null) {
            return "";
        }
        if (tel.length() == 1) {
            return tel;
        }
        if (tel.charAt(0) == '-') {
            return tel;
        } else {
            return "+" + tel;
        }

    }



    public static String dealTradeBibi(String dealNum) {

        return NumberUtil.keepNoZero(dealNum);
    }
}
