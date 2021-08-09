package com.sskj.lib.util;

import android.content.Intent;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.base.App;
import com.sskj.common.util.LanguageUtil;
import com.sskj.lib.SPConfig;


public class AppUtil {
    public static void changeLanguage() {

        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
        String lan=  CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString());
        httpHeaders.put(SPConfig.LANGUAGE, CommonUtil.getHeaderLanguage(lan));

        Intent intent = new Intent();
        intent.setClassName(App.INSTANCE,"com.sskj.lightning.ui.activity.MainActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        App.INSTANCE.startActivity(intent);
    }

}
