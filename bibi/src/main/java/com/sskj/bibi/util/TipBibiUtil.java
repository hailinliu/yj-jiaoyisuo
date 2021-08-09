package com.sskj.bibi.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sskj.bibi.R;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;

public class TipBibiUtil {
    public static void showTradeInfo(Context context, String direction, String type,
                                     String price, String num, String allMoney, OnTipSure onTipSure) {
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .customView(R.layout.bibi_dialog_trade_info_tip, false)
                .autoDismiss(true)
                .cancelable(false)
                .show();
        View customeView = dialog.getCustomView();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);

        ((TextView) customeView.findViewById(R.id.tvDirection)).setTextColor(ContextCompat.getColor(App.INSTANCE, "买入".equals(direction) ? R.color.bibiGreen : R.color.bibiRed));
        ((TextView) customeView.findViewById(R.id.tvDirection)).setText(direction);
        ((TextView) customeView.findViewById(R.id.tvType)).setText(type);
        ((TextView) customeView.findViewById(R.id.tvPrice)).setText(price);
        ((TextView) customeView.findViewById(R.id.tvNum)).setText(num);
        if (type.equals("市价交易") && "买入".equals(direction)) {
            ((TextView) customeView.findViewById(R.id.tvTitlePrice)).setText("金额");
            ((TextView) customeView.findViewById(R.id.tvPrice)).setText(allMoney);
        }
        ClickUtil.click(customeView.findViewById(R.id.tvCancel), dialog::dismiss);
        ClickUtil.click(customeView.findViewById(R.id.tvSure), () -> {
            dialog.dismiss();
            onTipSure.onSure();
        });
    }

    public interface OnTipSure {
        void onSure();
    }
}
