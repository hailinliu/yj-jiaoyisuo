package com.sskj.lib.util;

import com.google.gson.JsonParseException;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.box.exception.LogoutException;
import com.sskj.lib.box.exception.ToastException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-08-14 15:55
 */
public class ExceptionUtil {
    public static void dealException(Throwable e) {
        if (e instanceof JsonParseException) {
        } else if (e instanceof UnknownHostException) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libexceptionUtil1));
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libexceptionUtil2));
        } else if (e instanceof ConnectException) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libexceptionUtil1));
        }
        if (e instanceof LogoutException) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libexceptionUtil3));
        } else if (e instanceof ToastException) {
            ToastUtil.showShort(e.getMessage());
        }
    }

    public static String getExceptionMessage(Throwable e) {
        if (e instanceof JsonParseException) {
        } else if (e instanceof UnknownHostException) {
            return App.INSTANCE.getString(R.string.libexceptionUtil1);
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            return App.INSTANCE.getString(R.string.libexceptionUtil2);
        } else if (e instanceof ConnectException) {
            return App.INSTANCE.getString(R.string.libexceptionUtil1);
        }
        if (e instanceof LogoutException) {
            return App.INSTANCE.getString(R.string.libexceptionUtil3);
        } else if (e instanceof ToastException) {
            return e.getMessage();
        } else {
            return "";
        }
    }
}
