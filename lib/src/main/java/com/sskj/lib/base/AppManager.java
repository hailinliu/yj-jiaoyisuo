package com.sskj.lib.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack = new Stack();
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }

        return instance;
    }
    public void finishCuActivity(String cls) {
        Iterator iterator = activityStack.iterator();

        while(iterator.hasNext()) {
            Activity next = (Activity)iterator.next();
            String s = next.getClass().getName();
            if (s.contains(cls)) {
                next.finish();
                iterator.remove();
            }
        }

    }
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }

    }
    /**
     * 结束指定的Activity
     */
    public static void finishSingleActivity(Activity activity) {
        if (activity != null) {
            if (activityStack.contains(activity)) {
                activityStack.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator var2 = activityStack.iterator();
        while(var2.hasNext()) {
            Activity activity = (Activity)var2.next();
            if (activity.getClass().equals(cls)) {
                var2.remove();
                activity.finish();
               // this.finishActivity(activity);
            }
        }

    }

    public void finishAllActivity() {
        int i = 0;

        for(int size = activityStack.size(); i < size; ++i) {
            if (null != activityStack.get(i)) {
                ((Activity)activityStack.get(i)).finish();
            }
        }

        activityStack.clear();
    }

    public void finishAllActivity(Class<?> cls) {
        Iterator iterator = activityStack.iterator();

        while(iterator.hasNext()) {
            Activity next = (Activity)iterator.next();
            if (!next.getClass().equals(cls) && next != null) {
                next.finish();
                iterator.remove();
            }
        }

    }

    public void finishAllActivity(String cls) {
        Iterator iterator = activityStack.iterator();

        while(iterator.hasNext()) {
            Activity next = (Activity)iterator.next();
            if (!next.getClass().getName().equals(cls)) {
                next.finish();
                iterator.remove();
            }
        }

    }

    public void AppExit(Context context) {
        try {
            this.finishAllActivity();
            ActivityManager activityMgr = (ActivityManager)context.getSystemService("activity");
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception var3) {
        }

    }

    public Stack<Activity> getAllActivities() {
        return activityStack;
    }
}
