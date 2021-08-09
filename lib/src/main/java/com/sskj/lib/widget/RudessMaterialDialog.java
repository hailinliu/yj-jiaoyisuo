package com.sskj.lib.widget;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bulong.rudeness.RudenessScreenHelper;

public class RudessMaterialDialog extends MaterialDialog {
    protected RudessMaterialDialog(Builder builder) {
        super(builder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RudenessScreenHelper.resetDensity(getContext(), 750);

    }
}
