package com.sskj.lib.base;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * 该接口  便于统一管理 activity and fragment
 * Created by Lee on 2018/1/25 0025.
 */

public interface IBaseView {
    String getText(TextView textView);

    int color(int id);
    Drawable drawable(int id);
    void setText(TextView textView, String text);
    void showLoading();
    void hideLoading();
}
