package com.sskj.hangqing.util;

import android.app.Activity;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.sskj.hangqing.R;

public class BottomSheetAppUtil {
    public static BottomSheetDialog getLoginSheet(Activity activity) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View rootView = LayoutInflater.from(activity).inflate(R.layout.lib_bottom_sheet_selector, null, false);


        bottomSheetDialog.setContentView(rootView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }
}
