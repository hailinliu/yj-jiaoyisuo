package com.sskj.login.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.sskj.login.R;

public class TipLoginUtil {


    public static MaterialDialog showGoogleCheckDialog(Context context, OnTipPwdSure onTipPwdSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.login_dialog_google_check, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        View customView = dialog.getCustomView();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        TextView tvCancel = customView.findViewById(R.id.tvCancel);
        TextView tvPaste = customView.findViewById(R.id.tvPaste);
        EditText etPwd = customView.findViewById(R.id.etPwd);
        ClickUtil.click(customView.findViewById(R.id.btSubmit), () -> {
            if (TextUtils.isEmpty(etPwd.getText())) {
                return;
            }
            onTipPwdSure.onSure(etPwd.getText().toString());
        });
        ClickUtil.click(tvPaste, () -> {
            String text = CopyUtil.getTextFromClip(context);
            if (TextUtils.isEmpty(text)) {
                etPwd.setText(text);
            }
        });
        ClickUtil.click(tvCancel, () -> {
            dialog.dismiss();
        });
        return dialog;

//        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
//        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
//        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
//        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
//        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
//
//        return dialog;
    }

    public interface OnTipSure {
        void onSure();
    }

    public interface OnTipPwdSure {
        void onSure(String pwd);
    }
}
