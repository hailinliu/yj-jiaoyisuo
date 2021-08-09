package com.sskj.lib.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;

public class EditHintUtils {
    public static SpannedString setHintSizeAndContent(String content, int size, boolean bs){
        SpannableString ss = new SpannableString(content);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size,bs);
        ss.setSpan(ass,0,ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return new SpannedString(ss);
    }
}
