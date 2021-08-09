package com.sskj.level.util;

import com.sskj.lib.util.NumberUtil;

public class CoinUtil {
    public static String showName(String code) {
        return code.replace("_", "/").toUpperCase();
    }

    public static String keepRMB(String rmb) {
        return NumberUtil.keep(rmb,NumberUtil.rmbNumber);
    }

    public static String keepRMB(double rmb) {
        return keepRMB(rmb + "");
    }

    public static String keepUSDT(String coin) {
        return NumberUtil.keep(coin,NumberUtil.usdtNumber );
    }

    public static String keepUSDT(double coin) {
        return keepUSDT(coin + "");
    }
    /**
     * 币种价格
     *
     * @param code
     * @param price
     * @return
     */
    public static String keepCoinPrice(String code, String price) {
        try {
            Double.valueOf(price);
        } catch (Exception e) {
            return price;
        }
        return NumberUtil.keep(price, getPriceKeepNum(code));
    }

    public static String keepCoinMoney(String code, String money) {
        try {
            Double.valueOf(money);
        } catch (Exception e) {
            return money;
        }
        return NumberUtil.keep(money, 4);
    }
    public static String keepCoinNumMax2(String code, String num) {

        code = code.replace("_", "/").toUpperCase().split("/")[0];
        switch (code) {
            case "USDT":
            case "ETH":
            case "BTC":
                return NumberUtil.keep(num, 4);
            case "XRP":
            case "EOS":
                return NumberUtil.keep(num, 2);
            default:
                return NumberUtil.keep(num, 4);
        }
    }
    public static String keepCoinPrice3(String code, String price) {
        try {
            Double.valueOf(price);
        } catch (Exception e) {
            return price;
        }
        price = (Double.valueOf(price) + 0.00000000000001d) + "";
        code = code.replace("_", "/").toUpperCase();
        String substring = code.substring(code.indexOf("/") + 1);
        switch (substring) {
            case "USDT":
                return NumberUtil.keep(price, 6);
            default:
                return NumberUtil.keep(price, 8);

        }
    }

    /**
     * 获取币种价格保留位数
     *
     * @param code
     * @return
     */
    public static int getPriceKeepNum(String code) {
        if (code == null) {
            return 4;
        }
        code = code.replace("_", "/").toUpperCase();
        switch (code) {
            case "BCH/USDT":
            case "BTC/USDT":
            case "ETH/USDT":
            case "LTC/USDT":
            case "ZEC/USDT":
            case "DASH/USDT":
                return 2;
            case "FEC/MYR":
            case "FEC/TWD":
                return 3;
            case "EOS/USDT":
            case "ETC/USDT":
            case "OMG/USDT":
            case "GNT/USDT":
            case "PST/USDT":
            case "ZRX/USDT":
            case "MANA/USDT":
            case "CQTF/USDT":
            case "HT/USDT":
                return 4;
            case "XRP/USDT":
                return 5;
            case "BCH/BTC":
            case "XMR/ETH":
            case "ETH/BTC":
            case "ETC/BTC":
            case "OMG/BTC":
            case "OMG/ETH":
            case "TRX/USDT":
            case "LTC/BTC":
            case "PST/ETH":
                return 6;
            case "XRP/BTC":
            case "TRX/ETH":
            case "EOS/ETH":
            case "EOS/BTC":
            case "ZRX/BTC":
            case "BAT/BTC":
            case "GNT/BTC":
            case "PST/BTC":
            case "MANA/BTC":
                return 8;
            case "ZRX/ETH":
            case "BAT/ETH":
            case "GNT/ETH":
            case "MANA/ETH":
                return 4;
            default:
                return 2;

        }
    }

    public static int getPagerKeepNum(String code){
        return 2;
    }

    /**
     * @param code
     * @param num
     * @return
     */
    public static String keepCoinPaper(String code, String num) {
        try {
            Double.valueOf(num);
        } catch (Exception e) {
            return num;
        }
        return NumberUtil.keep(num, getPagerKeepNum(code));
    }

    /**
     * 获取币种数量保留位数
     *
     * @param code
     * @return
     */
    public static int getNumKeepNum(String code) {
        code = code.replace("_", "/").toUpperCase();

        switch (code) {
            case "HT/USDT":
                return 2;
            case "BTC/USDT":
                return 6;
            case "ETC/USDT":
            case "BCH/USDT":
            case "LTC/USDT":
            case "EOS/USDT":
            case "ETH/USDT":
                return 4;
            case "XRP/USDT":
                return 2;
            default:
                return 4;
        }
    }


    /**
     * @param code
     * @param num
     * @return
     */
    public static String keepCoinNum(String code, String num) {
        try {
            Double.valueOf(num);
        } catch (Exception e) {
            return num;
        }
        return NumberUtil.keep(num, getNumKeepNum(code));
    }

}
