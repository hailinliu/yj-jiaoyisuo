package com.sskj.lib.util;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;

public class FingerCheckUtil {
    public static void check(Context context) {

        if (SPUtil.get(SPConfig.IS_FINGER_ON, false) && SPUtil.get(SPConfig.IS_LOCK, false)) {
            ARouter.getInstance().build(RConfig.LIB_FINGER).navigation();
        }
    }
}
