package com.sskj.lightning;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.App;
import com.sskj.common.util.LanguageUtil;
import com.sskj.lib.base.AppManager;

public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(activity);
        if (!LanguageUtil.isSameWithSetting(activity)) {
            LanguageUtil.changeAppLanguage(activity, LanguageUtil.getAppLocale(activity), true);
        }

        if (!LanguageUtil.isSameWithSetting(App.INSTANCE)) {
            LanguageUtil.changeAppLanguage(App.INSTANCE, LanguageUtil.getAppLocale(App.INSTANCE), true);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
       AppManager.getAppManager().finishActivity(activity);
        OkGo.getInstance().cancelTag(activity);
    }
}
