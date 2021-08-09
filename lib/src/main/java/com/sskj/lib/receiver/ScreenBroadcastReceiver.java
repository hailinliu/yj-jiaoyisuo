package com.sskj.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.sskj.common.util.SPUtil;
import com.sskj.lib.SPConfig;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-17 08:54
 */
public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = ScreenBroadcastReceiver.class.getSimpleName();
    private boolean isRegisterReceiver = false;

    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            // 开屏


        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            // 锁屏
            if (SPUtil.get(SPConfig.IS_FINGER_ON,false)) {
                SPUtil.put(SPConfig.IS_LOCK, true);
            }

        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            // 解锁
//            System.out.println(App.INSTANCE.getString(R.string.strScreenBroadcastReceiver2));
//            if (MyAppLication.isLogin() && (Boolean) SPUtils.get(Constants.IS_GESTURE_ON, false, MyAppLication.getID())) {
//                LockActivity.check(context);
//            }
        }
    }

    public void registerScreenBroadcastReceiver(Context mContext) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            mContext.registerReceiver(ScreenBroadcastReceiver.this, filter);
        }
    }

    public void unRegisterScreenBroadcastReceiver(Context mContext) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false;
            mContext.unregisterReceiver(ScreenBroadcastReceiver.this);
        }
    }
}
