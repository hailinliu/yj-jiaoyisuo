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
public class HomeBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = HomeBroadcastReceiver.class.getSimpleName();
    private boolean isRegisterReceiver = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra("reason");
            if (reason != null) {

                if (reason.equals("homekey")) {
                    //按Home按键
                    if (SPUtil.get(SPConfig.IS_FINGER_ON,false)) {
                        SPUtil.put(SPConfig.IS_LOCK, true);
                    }

                } else if (reason.equals("recentapps")) {
                    //最近任务键也就是菜单键

                } else if (reason.equals("assist")) {
                    //常按home键盘


                }

            }

        }
    }

    public void registerScreenBroadcastReceiver(Context mContext) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            mContext.registerReceiver(HomeBroadcastReceiver.this, filter);
        }
    }

    public void unRegisterScreenBroadcastReceiver(Context mContext) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false;
            mContext.unregisterReceiver(HomeBroadcastReceiver.this);
        }
    }
}
