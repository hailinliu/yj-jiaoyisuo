package com.sskj.lib.util;


import com.sskj.common.base.App;
import com.sskj.lib.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author Hey
 */
public class PatternUtils {


    public static boolean isLoginPs(String text) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
        if (!text.matches(regex)) {
//            ToastUtils.show(App.INSTANCE.getString(com.sskj.common.R.string.common_patternUtils1));
            return false;
        }

        return true;
    }


    public static boolean isPayPs(String text) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
        if (!text.matches(regex)) {
//            ToastUtils.show(App.INSTANCE.getString(com.sskj.common.R.string.common_patternUtils1));
            return false;
        }

        return true;
    }

    public static boolean isMobile(String text) {
        String regex = "^0?(13|14|15|16|17|18|19)[0-9]{9}$";

        if (!text.matches(regex)) {
//            ToastUtils.show(App.INSTANCE.getString(com.sskj.common.R.string.common_patternUtils2));
            return false;
        }

        return true;
    }

    public static boolean isEmail(String text) {
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!text.matches(regex)) {
//            ToastUtils.show(App.INSTANCE.getString(com.sskj.common.R.string.common_patternUtils3));
            return false;
        }
        return true;
    }

    public static boolean isRealName(String text) {

        String regex = "^[A-Za-z\\u4e00-\\u9fa5]+$";
        if (!text.matches(regex)) {
//            ToastUtils.show(App.INSTANCE.getString(com.sskj.common.R.string.common_patternUtils4));
            return false;
        }
        return true;
    }


    /**
     * 18位身份证校验,比较严格校验
     *
     * @param idCard
     * @return
     * @author lyl
     */
    public static boolean isIdcard(String idCard) {
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern1.matcher(idCard);
        int[] prefix = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int[] suffix = new int[]{1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if (matcher.matches()) {
            Map<String, String> cityMap = initCityMap();
            if (cityMap.get(idCard.substring(0, 2)) == null) {
                return false;
            }
            int idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
            for (int i = 0; i < 17; i++) {
                idCardWiSum += Integer.valueOf(idCard.substring(i, i + 1)) * prefix[i];
            }

            int idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
            String idCardLast = idCard.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if (idCardMod == 2) {
                if (idCardLast.equalsIgnoreCase("x")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if (idCardLast.equals(suffix[idCardMod] + "")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    private static Map<String, String> initCityMap() {
        Map<String, String> cityMap = new HashMap<String, String>();
        cityMap.put("11", App.INSTANCE.getString(R.string.libpatternUtils1));
        cityMap.put("12", App.INSTANCE.getString(R.string.libpatternUtils2));
        cityMap.put("13", App.INSTANCE.getString(R.string.libpatternUtils3));
        cityMap.put("14", App.INSTANCE.getString(R.string.libpatternUtils4));
        cityMap.put("15", App.INSTANCE.getString(R.string.libpatternUtils5));

        cityMap.put("21", App.INSTANCE.getString(R.string.libpatternUtils6));
        cityMap.put("22", App.INSTANCE.getString(R.string.libpatternUtils7));
        cityMap.put("23", App.INSTANCE.getString(R.string.libpatternUtils8));

        cityMap.put("31", App.INSTANCE.getString(R.string.libpatternUtils9));
        cityMap.put("32", App.INSTANCE.getString(R.string.libpatternUtils10));
        cityMap.put("33", App.INSTANCE.getString(R.string.libpatternUtils11));
        cityMap.put("34", App.INSTANCE.getString(R.string.libpatternUtils12));
        cityMap.put("35", App.INSTANCE.getString(R.string.libpatternUtils13));
        cityMap.put("36", App.INSTANCE.getString(R.string.libpatternUtils14));
        cityMap.put("37", App.INSTANCE.getString(R.string.libpatternUtils15));

        cityMap.put("41", App.INSTANCE.getString(R.string.libpatternUtils16));
        cityMap.put("42", App.INSTANCE.getString(R.string.libpatternUtils17));
        cityMap.put("43", App.INSTANCE.getString(R.string.libpatternUtils18));
        cityMap.put("44", App.INSTANCE.getString(R.string.libpatternUtils19));
        cityMap.put("45", App.INSTANCE.getString(R.string.libpatternUtils20));
        cityMap.put("46", App.INSTANCE.getString(R.string.libpatternUtils21));

        cityMap.put("50", App.INSTANCE.getString(R.string.libpatternUtils22));
        cityMap.put("51", App.INSTANCE.getString(R.string.libpatternUtils23));
        cityMap.put("52", App.INSTANCE.getString(R.string.libpatternUtils24));
        cityMap.put("53", App.INSTANCE.getString(R.string.libpatternUtils25));
        cityMap.put("54", App.INSTANCE.getString(R.string.libpatternUtils26));

        cityMap.put("61", App.INSTANCE.getString(R.string.libpatternUtils27));
        cityMap.put("62", App.INSTANCE.getString(R.string.libpatternUtils28));
        cityMap.put("63", App.INSTANCE.getString(R.string.libpatternUtils29));
        cityMap.put("64", App.INSTANCE.getString(R.string.libpatternUtils30));
        cityMap.put("65", App.INSTANCE.getString(R.string.libpatternUtils31));

        //          cityMap.put("71", App.INSTANCE.getString(R.string.libpatternUtils32));
        //          cityMap.put("81", App.INSTANCE.getString(R.string.libpatternUtils33));
        //          cityMap.put("82", App.INSTANCE.getString(R.string.libpatternUtils34));
        //          cityMap.put("91", App.INSTANCE.getString(R.string.libpatternUtils35));
        //          System.out.println(cityMap.keySet().size());
        return cityMap;
    }

}
