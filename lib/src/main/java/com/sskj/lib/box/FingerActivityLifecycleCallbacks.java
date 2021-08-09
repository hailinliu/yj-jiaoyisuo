package com.sskj.lib.box;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.sskj.lib.util.FingerCheckUtil;

public class FingerActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

        if (activity.getClass().getSimpleName().contains("FingerActivity")||activity.getClass().getSimpleName().contains("FingerLoginActivity")||activity.getClass().getSimpleName().contains("SplashActivity")){
            return;
        }
        FingerCheckUtil.check(activity);
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

    }
}
