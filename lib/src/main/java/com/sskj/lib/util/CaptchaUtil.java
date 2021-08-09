package com.sskj.lib.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class CaptchaUtil {
    public static void check(Context context, OnValidate onValidate) {
        onValidate.onValidate(null, null, null);

//        CaptchaConfiguration configuration = new CaptchaConfiguration.Builder()
//                .captchaId("7057166a15d74781a0d8620b925436c9")
//                .listener(new CaptchaListener() {
//                    @Override
//                    public void onReady() {
//
//                    }
//
//                    @Override
//                    public void onValidate(String result, String validate, String msg) {
//                        if (onValidate != null) {
//                            AndroidSchedulers.mainThread().scheduleDirect(() -> {
//                                if (!TextUtils.isEmpty(validate)) {
//                                    onValidate.onValidate(result, validate, msg);
//                                } else {
//                                    ToastUtil.showShort(App.INSTANCE.getString(R.string.libcaptchaUtil1));
//                                }
//                            });
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(String msg) {
//                        ToastUtil.showShort(App.INSTANCE.getString(R.string.libcaptchaUtil2) + msg);
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//
//                    @Override
//                    public void onClose() {
//
//                    }
//                }) // 验证码回调监听器
//                .timeout(1000 * 20) // 超时时间，一般无需设置
//                .debug(true) // 是否启用debug模式，一般无需设置
//                .position(-1, -1, 0, 0) // 设置验证码框的位置和宽度，一般无需设置，不推荐设置宽高，后面将会将逐步废弃该接口
//                .build(context); // Context，请使用Activity实例的Context
//        Captcha captcha = Captcha.getInstance().init(configuration);
//        captcha.validate();
    }

    public interface OnValidate {
        void onValidate(String result, String validate, String msg);
    }
}
