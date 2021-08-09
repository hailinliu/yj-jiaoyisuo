package com.sskj.lib.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.sskj.common.util.ClickUtil;
import com.sskj.lib.R;


public class EditUtil {

    public static void togglePs(EditText editText, ImageView imageView) {
        togglePs(editText, imageView,R.mipmap.lib_icon_show,R.mipmap.lib_icon_hide);
    }

    public static void togglePs(EditText editText, ImageView imageView,int resShow,int resHide) {
        ClickUtil.click(imageView, () -> {
            if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                editText.setSelection(editText.getText().length());
                imageView.setImageResource(resShow);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editText.setSelection(editText.getText().length());
                imageView.setImageResource(resHide);
            }
        });

    }
}
