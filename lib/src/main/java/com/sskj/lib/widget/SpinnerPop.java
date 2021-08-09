package com.sskj.lib.widget;

import android.content.Context;
import android.view.View;

import com.sskj.lib.R;

import razerdp.basepopup.BasePopupWindow;

public class SpinnerPop extends BasePopupWindow {
    public SpinnerPop(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.lib_pop_spinner);
    }
}
