package com.sskj.lib.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lv on 18-6-5.
 */

public class NumberUtil {
    public static NumberBuilder rmbNumber =new NumberBuilder().setKeepNum(2).setMax(true);
    public static NumberBuilder usdtNumber =new NumberBuilder().setKeepNum(4).setMax(true);
    public static NumberBuilder bibiBIBNumber = new NumberBuilder().setKeepNum(2).setMax(false);
    public static class NumberBuilder {
        private boolean isMax=false;
        private int keepNum=4;
        private RoundingMode roundingMode=RoundingMode.DOWN;

        public boolean isMax() {
            return isMax;
        }

        public NumberBuilder setMax(boolean max) {
            isMax = max;
            return this;
        }

        public int getKeepNum() {
            return keepNum;
        }

        public NumberBuilder setKeepNum(int keepNum) {
            this.keepNum = keepNum;
            return this;
        }

        public RoundingMode getRoundingMode() {
            return roundingMode;
        }

        public NumberBuilder setRoundingMode(RoundingMode roundingMode) {
            this.roundingMode = roundingMode;
            return this;
        }
    }
    public static String keep(String num, NumberBuilder numberBuilder){
       return keep(num, numberBuilder.getKeepNum(), numberBuilder.isMax(), numberBuilder.roundingMode);
    }

    /**
     * 火币处理盘口数量逻辑
     *
     * @param num
     * @return
     */
    public static String keepPankouNum(String num) {
        if (TextUtils.isEmpty(num)) {
            return "0";
        }
        BigDecimal bigDecimal = new BigDecimal(num);
        if (bigDecimal.doubleValue() < 1000) {
            return NumberUtil.keepMax(num, 3);
        } else if (bigDecimal.doubleValue() >= 1000 && bigDecimal.doubleValue() < 1000000) {
            BigDecimal divide = bigDecimal.divide(new BigDecimal(1000), 3, BigDecimal.ROUND_DOWN);
            return NumberUtil.keepMax(divide.toPlainString(), 3) + "k";
        } else if (bigDecimal.doubleValue() >= 1000000) {
            BigDecimal divide = bigDecimal.divide(new BigDecimal(1000000), 3, BigDecimal.ROUND_DOWN);
            return NumberUtil.keepMax(divide.toPlainString(), 3) + "M";
        }
        return num;
    }


    /**
     * 保留几位小数，四舍五入
     *
     * @param number  原数据
     * @param keepNum 小数位数
     * @param isMax   是否最多保留几位 true 去除无效零 false 保留零
     * @return 处理后数据
     */
    public static String   keep(String number, int keepNum, boolean isMax) {
        return keep(number, keepNum, isMax, RoundingMode.DOWN);
    }

    /**
     * 处理小数位
     *
     * @param number  原数据
     * @param keepNum 小数位数
     * @param isMax   是否最多保留几位 true 去除无效零 false 保留零
     * @return 处理后数据
     */
    private static String keep(String number, int keepNum, boolean isMax, RoundingMode roundingMode) {
        if (TextUtils.isEmpty(number)) {
            return 0 + "";
        }
        number = number.replace(",", "");

        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(keepNum, roundingMode);
        if (isMax) {
            return keepNoZero(bigDecimal.toPlainString());
        } else {
            return bigDecimal.toPlainString();
        }
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("4.639").setScale(4);
        System.out.println(bigDecimal.toPlainString());
    }

    /**
     * 舍去
     *
     * @param number
     * @param keepNum
     * @param isMax
     * @return
     */
    public static String keepDown(String number, int keepNum, boolean isMax) {
        return keep(number, keepNum, isMax, RoundingMode.DOWN);
    }

    /**
     * 去除小数点最后的零
     *
     * @param number 原数据
     * @return 处理后数据
     */
    public static String keepNoZero(String number) {
        if (TextUtils.isEmpty(number)) {
            return 0 + "";
        }
        if (number.indexOf(".") > 0) {
            number = number.replaceAll("0+?$", "");//去掉多余的0
            number = number.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return number;
    }

    public static String keepMax(String number, int keepNum) {
        return keep(number, keepNum, true);
    }


    public static String keep(String number, int keepNum) {
        return keep(number, keepNum, true);
    }

    public static String keepMax2(double number) {
        return keep(number + "", 2, true);
    }


    public static String keepNoZero(double number) {
        return keepNoZero(number + "");
    }

    public static String keepMax4(String number) {
        return keep(number, 4, true);
    }

    public static String keepMax4(double number) {
        return keep(number + "", 4, true);
    }

    public static String keep4(double number) {
        return keep(number + "", 4, false);
    }

    public static String keep4(String number) {
        return keep(number, 4, false);
    }

    public static String keepMax2(String number) {
        return keep(number, 2, true);
    }



    public static String keep2(double number) {
        return keep(number + "", 2, false);
    }

    public static String keep2(String number) {
        return keep(number, 6, false);
    }

    public static String keep2Down(String number) {
        return keepDown(number, 2, false);
    }

    public static String keep2Down(double number) {
        return keepDown(number + "", 2, false);
    }

    public static String keepNoZoreMax4(String number) {
        return keepMax4(keepNoZero(number));
    }

    public static String keepNoZoreMax4(double number) {
        return keepMax4(keepNoZero(number));
    }

    public static String keepNoZoreMax2(String number) {
        return keepMax2(keepNoZero(number));
    }

    public static String keepNoZoreMax8(String number) {
        return keepMax8(keepNoZero(number));
    }

    public static String keepNoZoreMax6(String number) {
        return keepMax(keepNoZero(number), 6);
    }

    public static String keepMax8(String number) {
        return keepMax(number, 8);
    }


    public static String keepNoZeroMax4(String number) {
        return keepMax4(keepNoZero(number));
    }

    public static String keepNoZeroMax4(double number) {
        return keepMax4(keepNoZero(number));
    }



    private static boolean isMatch(String regex, String orginal) {
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    public static boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    public static boolean isNegativeInteger(String orginal) {
        return isMatch("^-[1-9]\\d*", orginal);
    }

    public static boolean isWholeNumber(String orginal) {
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }

    public static boolean isPositiveDecimal(String orginal) {
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
    }

    public static boolean isNegativeDecimal(String orginal) {
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
    }

    public static boolean isDecimal(String orginal) {
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
    }

    /**
     * 判断是否是整数或小数
     *
     * @param s s
     * @return 为空返回false
     */
    public static boolean isRealNumber(String s) {
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        return isWholeNumber(s) || isDecimal(s);
    }


    public static boolean isLittleNumber(String number) {
        Pattern p = Pattern.compile("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    public static boolean isThirdNumber(String number) {
        //获取小数点的位置
        int bitPos = number.indexOf(".");
        //字符串总长度减去小数点位置，再减去1，就是小数位数
        int numOfBits = number.length() - bitPos - 1;
        if (numOfBits > 2) {
            return true;
        } else {
            return false;
        }
    }


}
