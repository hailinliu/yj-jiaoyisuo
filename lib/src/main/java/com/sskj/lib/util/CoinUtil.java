package com.sskj.lib.util;


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
    public static String keepCoinPrice1(String code, String price) {
        try {
            Double.valueOf(price);
        } catch (Exception e) {
            return price;
        }
        return NumberUtil.keep(price, getPriceKeepNum(code),false);
    }
    public static String keepCoinMoney(String code, String money) {
        try {
            Double.valueOf(money);
        } catch (Exception e) {
            return money;
        }
        switch (code) {
            case "BTC/USDT":
            case "BCH/USDT":
            case "ETH/USDT":
            case "LTC/USDT":
            case "ZEC/USDT":
            case "DASH/USDT":
                return NumberUtil.keep(money, 4);
            default:
                return NumberUtil.keep(money, 4);

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
                return 4;
            case "FEC/MYR":
            case "FEC/TWD":
                return 4;
            case "EOS/USDT":
            case "SETS/USDT":
            case "BSV/USDT":
            case "ETC/USDT":
            case "OMG/USDT":
            case "GNT/USDT":
            case "PST/USDT":
            case "ZRX/USDT":
            case "MANA/USDT":
            case "CQTF/USDT":
            case "HT/USDT":
            case "PBM/USDT":
                return 4;
            case "XRP/USDT":
                return 4;
            case "BCH/BTC":
            case "XMR/ETH":
            case "ETH/BTC":
            case "ETC/BTC":
            case "OMG/BTC":
            case "OMG/ETH":
            case "TRX/USDT":
            case "LTC/BTC":
            case "PST/ETH":
                return 4;
            case "XRP/BTC":
            case "TRX/ETH":
            case "EOS/ETH":
            case "EOS/BTC":
            case "ZRX/BTC":
            case "BAT/BTC":
            case "GNT/BTC":
            case "PST/BTC":
            case "MANA/BTC":
                return 4;
            case "ZRX/ETH":
            case "BAT/ETH":
            case "GNT/ETH":
            case "MANA/ETH":
                return 4;
            default:
                return 4;

        }
    }

    /**
     * 获取币种数量保留位数
     *
     * @param code
     * @return
     */
    public static int getNumKeepNum(String code) {
        code = code.replace("_", "/").toUpperCase();
        int count;
        switch (code) {
            case "BTC/USDT":
            case "ETH/USDT":
            case "LTC/USDT":
            case "BCH/USDT":
            case "btc/usdt":
            case "eth/usdt":
            case "ltc/usdt":
            case "bch/usdt":
                //2位数
                count = 4;
                break;
            case "EOS/USDT":
            case "eos/usdt":
            case "SETS/USDT":
            case "BSV/USDT":
            case "PBM/USDT":
                //四位
                count = 4;
                break;
            case "XRP/USDT":
            case "xrp/usdt":
                count = 4;
                break;
            case "ETH/BTC":
            case "LTC/BTC":
            case "BCH/BTC":
            case "ETC/BTC":
            case "eth/btc":
            case "ltc/btc":
            case "bch/btc":
            case "etc/btc":
                //六位
                count = 4;
                break;
            case "XRP/BTC":
            case "EOS/BTC":
            case "xrp/btc":
            case "eos/btc":
                //八位
                count = 4;
                break;
            case "XMR/ETH":
            case "OMG/ETH":
            case "ADA/ETH":
            case "xmr/eth":
            case "omg/eth":
            case "ada/eth":
                //六位
                count = 4;
                break;
            case "EOS/ETH":
            case "eos/eth":
                //八位
                count = 4;
                break;
            default:
                count = 4;
                break;
        }
        return count;
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
    /**
     * @param code code
     * @return 币币价格小数位
     */
    public static int PNum(String code) {
        int count;
        switch (code) {
            case "BTC/USDT":
            case "ETH/USDT":
            case "LTC/USDT":
            case "BCH/USDT":
            case "btc/usdt":
            case "eth/usdt":
            case "ltc/usdt":
            case "bch/usdt":
                //2位数
                count = 2;
                break;
            case "EOS/USDT":
            case "eos/usdt":
                //四位
                count = 4;
                break;
            case "XRP/USDT":
            case "xrp/usdt":
                count = 5;
                break;
            case "ETH/BTC":
            case "LTC/BTC":
            case "BCH/BTC":
            case "ETC/BTC":
            case "eth/btc":
            case "ltc/btc":
            case "bch/btc":
            case "etc/btc":
                //六位
                count = 6;
                break;
            case "XRP/BTC":
            case "EOS/BTC":
            case "xrp/btc":
            case "eos/btc":
                //八位
                count = 8;
                break;
            case "XMR/ETH":
            case "OMG/ETH":
            case "ADA/ETH":
            case "xmr/eth":
            case "omg/eth":
            case "ada/eth":
                //六位
                count = 6;
                break;
            case "EOS/ETH":
            case "eos/eth":
                //八位
                count = 8;
                break;
            default:
                count = 8;
                break;
        }
        return count;

    }
    /**
     * @param code  币种code
     * @param price 价钱
     * @param keep  是否保持无效0
     * @return
     */
    public static String Price(String code, String price, boolean keep) {
        // USDT: BTC 2位，ETH 2位，XRP 4位，LTC 2位，BCH 2位，EOS 4位，后续再上代币3位
        //  BTC: ETH 6位，EOS 8位、LTC 6位、BCH 6位、XRP8位、 ETC6位。
        //  ETH:EOS 8位、XMR 6位、OMG 6位、ADA  6位。
        switch (code) {
            case "BTC/USDT":
            case "ETH/USDT":
            case "LTC/USDT":
            case "BCH/USDT":
            case "btc/usdt":
            case "eth/usdt":
            case "ltc/usdt":
            case "bch/usdt":
                //2位数
                price = keep ? NumberUtil.keep(price, 2) : NumberUtil.keepNoZoreMax2(price);
                break;

            case "EOS/USDT":
            case "eos/usdt":
                //四位
                price = keep ? NumberUtil.keep(price, 4) : NumberUtil.keepNoZoreMax4(price);
                break;
            case "XRP/USDT":
            case "xrp/usdt":
                //五位
                price = keep ? NumberUtil.keep(price, 5) : NumberUtil.keepNoZoreMax4(price);
                break;

            case "ETH/BTC":
            case "LTC/BTC":
            case "BCH/BTC":
            case "ETC/BTC":
            case "eth/btc":
            case "ltc/btc":
            case "bch/btc":
            case "etc/btc":
                //六位
                price = keep ? NumberUtil.keep(price, 6) : NumberUtil.keepNoZoreMax6(price);
                break;
            case "XRP/BTC":
            case "EOS/BTC":
            case "xrp/btc":
            case "eos/btc":
                //八位
                price = keep ? NumberUtil.keep(price, 8) : NumberUtil.keepNoZoreMax8(price);
                break;
            case "XMR/ETH":
            case "OMG/ETH":
            case "ADA/ETH":
            case "xmr/eth":
            case "omg/eth":
            case "ada/eth":
                //六位
                price = keep ? NumberUtil.keep(price, 6) : NumberUtil.keepNoZoreMax6(price);
                break;
            case "EOS/ETH":
            case "eos/eth":
                //八位
                price = keep ? NumberUtil.keep(price, 8) : NumberUtil.keepNoZoreMax8(price);
                break;
            default:
                price = keep ? NumberUtil.keep(price, 6) : NumberUtil.keepNoZero(NumberUtil.keep(price, 6));
                break;
        }
        return price;
    }
    public static String keepCoinNumMax(String code, Double num) {
        return keepCoinNumMax(code, num + "");
    }

    public static String keepCoinNumMax(String code, String num) {
        code = code.replace("_", "/").toUpperCase().split("/")[0];
        switch (code) {
            case "USDT":
            case "BTC":
            case "ETH":
                return NumberUtil.keepMax4(num);
            default:
                return NumberUtil.keepMax(num, 6);
        }

    }
}
