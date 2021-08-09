package com.sskj.lib.router.service;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.shizhefei.mvc.MVCHelper;
import com.sskj.common.base.App;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.mvchelper.LoadViewFactory;
import com.sskj.lib.util.CommonUtil;


@Route(path = "/lib/application")
public class LibService implements IProvider {
    @Override
    public void init(Context context) {
        MVCHelper.setLoadViewFractory(new LoadViewFactory());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpParams httpParams = new HttpParams();



        httpHeaders.put("systemType", "APP");
        String lan=CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString());
        String lan1= CommonUtil.getHeaderLanguage(lan);
        httpHeaders.put(SPConfig.LANGUAGE,lan1);

        String token = SPUtil.get(SPConfig.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            httpHeaders.put(SPConfig.TOKEN, token);
        }
        OkGo.getInstance().addCommonHeaders(httpHeaders);
        OkGo.getInstance().addCommonParams(httpParams);

    }
}
