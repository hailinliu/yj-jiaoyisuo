package com.sskj.lib.util;

import com.sskj.common.base.App;
import com.sskj.common.util.LanguageUtil;

import java.util.Locale;

public class ChineseUtil {

    /**
     * 简体或者繁体中文
     *
     * @return
     */
    public static boolean isChinese() {
        boolean isSimpleChinese = LanguageUtil.isSimpleChinese();
        boolean isTaiWan = LanguageUtil.getAppLocale(App.INSTANCE) == Locale.TRADITIONAL_CHINESE;

        return isSimpleChinese || isTaiWan;
    }
}
