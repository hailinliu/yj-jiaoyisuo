package com.sskj.lib.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;

/**
 * <pre>
 *     author : wk_math
 *     e-mail : wk_math@163.com
 *     time   : 2018/03/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CopyUtil {
    public static void copy(String content) {
        ClipboardManager cm = (ClipboardManager) App.INSTANCE.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setPrimaryClip(ClipData.newPlainText("", content));
        ToastUtil.showShort(App.INSTANCE.getString(R.string.libcopyUtil1));
    }


    public static String getTextFromClip(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //判断剪切版时候有内容
        if (!clipboardManager.hasPrimaryClip())
            return "";
        ClipData clipData = clipboardManager.getPrimaryClip();
        //获取 ClipDescription
//        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();
        //获取 lable
//        String lable = clipDescription.getLabel().toString();
        //获取 text
        String text = clipData.getItemAt(0).getText().toString();
        return text;
    }
}
