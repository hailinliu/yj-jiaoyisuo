package com.sskj.level.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.base.App;
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.R;
import com.sskj.lib.bean.enums.BuySellEnum;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.level.util.CoinUtil;
import com.sskj.lib.widget.RudessMaterialDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TipLevelUtil {


    private static String minZYPrice;
    private static String minZSPrice;
    private static boolean isBuy;
    private static TextView tvType;
    private static TextView tvPrice;
    private static Button btSure;
    private static EditText etStopProfit;
    private static EditText etStopLoss;
    private static OnTipResultSure onTipSure;
    private static String winPrice;
    private static String lossPrice;
    private static TextView tvStopLoss;
    private static TextView tvStopProfit;
    private static Double paper;

    /**
     * 平仓
     */
    public static MaterialDialog getPingcangTip(Context context, boolean isBuy, String price, String maxPaper, OnTipNumSure onTipPwdSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.level_dialog_pingcang, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        paper = Double.valueOf(maxPaper);

        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        TextView tvType = customView.findViewById(R.id.tvType);
        TextView tvPrice = customView.findViewById(R.id.tvPrice);
        EditText etNum = customView.findViewById(R.id.etNum);
        TextView tvIncrease = customView.findViewById(R.id.tvIncrease);
        TextView tvDecrease = customView.findViewById(R.id.tvDecrease);
        tvType.setText(isBuy ? App.INSTANCE.getString(R.string.levelbuySellEnum1) : App.INSTANCE.getString(R.string.levelbuySellEnum2));
        tvType.setTextColor(ContextCompat.getColor(App.INSTANCE, isBuy ? R.color.levelGreen : R.color.levelRed));
        tvPrice.setText(price);
        Button btCancel = customView.findViewById(R.id.btCancel);
        Button btSure = customView.findViewById(R.id.btSure);
        btCancel.setTextColor(ContextCompat.getColor(App.INSTANCE,R.color.libTextWhite));
        etNum.setText(maxPaper);
        etNum.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        ClickUtil.click(10, tvDecrease, () -> {
            if (paper <= 0) {
//                ToastUtil.showShort(App.INSTANCE.getString(R.string.leveltipLevelUtil3));
                return;
            }
            paper--;
            paper = Math.max(0, paper);
            etNum.setText(paper + "");
        });
        ClickUtil.click(10, tvIncrease, () -> {
            if (paper >= Double.valueOf(maxPaper)) {
                ToastUtil.showShort(String.format(App.INSTANCE.getString(R.string.leveltipLevelUtil4), maxPaper));
                return;
            }
            paper++;
            etNum.setText(paper + "");
        });
        RxTextView.afterTextChangeEvents(etNum)
                .subscribe(textViewAfterTextChangeEvent -> {
                    String text = textViewAfterTextChangeEvent.editable().toString();
                    if (TextUtils.isEmpty(text)) {
                        paper = 0d;
                        return;
                    }
                    paper = Double.valueOf(text);
                });
        ClickUtil.click(btCancel, () -> {
            dialog.dismiss();
        });
        ClickUtil.click(btSure, () -> {
            if (paper > Double.valueOf(maxPaper)) {
                ToastUtil.showShort(String.format(App.INSTANCE.getString(R.string.leveltipLevelUtil4), maxPaper));
                return;
            }
            if (paper == 0) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.leveltipLevelUtil48));
                return;
            }
            dialog.dismiss();
            onTipPwdSure.onSure(etNum.getText().toString());
        });

        return dialog;
    }


    /**
     * 下单
     */
    public static void showTradeInfo(Context context, boolean isBuy, String type,
                                     String price, String num, String cashMoney, String fee,
                                     String level, String winPrice, String lossPrice, OnTipSure onTipSure) {

        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.level_dialog_trade_info_tip, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);

        ((TextView) customView.findViewById(R.id.tvDirection)).setTextColor(ContextCompat.getColor(App.INSTANCE, isBuy ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
        ((TextView) customView.findViewById(R.id.tvDirection)).setText(isBuy ? BuySellEnum.BUY.getTitle() : BuySellEnum.SELL.getTitle());
        ((TextView) customView.findViewById(R.id.tvType)).setText(type);
        ((TextView) customView.findViewById(R.id.tvPrice)).setText(price);
        ((TextView) customView.findViewById(R.id.tvNum)).setText(num);
        ((TextView) customView.findViewById(R.id.tvCashPrice)).setText(cashMoney);
        ((TextView) customView.findViewById(R.id.tvServicePrice)).setText(fee);
        ((TextView) customView.findViewById(R.id.tvLevel)).setText(level);
        ((TextView) customView.findViewById(R.id.tvWinPrice)).setText(winPrice);
        ((TextView) customView.findViewById(R.id.tvLossPrice)).setText(lossPrice);

        ClickUtil.click(customView.findViewById(R.id.tvCancel), dialog::dismiss);
        ClickUtil.click(customView.findViewById(R.id.tvSure), () -> {
            dialog.dismiss();
            onTipSure.onSure();
        });
    }

    /**
     * 修改止盈止损弹出框
     */
    @SuppressLint("SetTextI18n")
    public static MaterialDialog showTradeSet(Context context,
                                              String code,
                                              String type,
                                              String newPrice,
                                              String minZY,
                                              String minZS,
                                              String currentZY,
                                              String currentZS,
                                              OnTipResultSure onTipSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.level_dialog_set_price, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        minZSPrice = minZS;
        minZYPrice = minZY;
        TipLevelUtil.onTipSure = onTipSure;
        etStopProfit = customView.findViewById(R.id.etStopProfit);
        etStopLoss = customView.findViewById(R.id.etStopLoss);
        tvStopLoss = customView.findViewById(R.id.tvStopLoss);
        tvStopProfit = customView.findViewById(R.id.tvStopProfit);
        etStopProfit.setFilters(new InputFilter[]{new MoneyValueFilter(CoinUtil.getPriceKeepNum(code))});
        etStopLoss.setFilters(new InputFilter[]{new MoneyValueFilter(CoinUtil.getPriceKeepNum(code))});
        ClickUtil.click(300, customView.findViewById(R.id.ivWinDecrease), () -> {
            if (!TextUtils.isEmpty(etStopProfit.getText().toString())) {
                double v = Double.parseDouble(etStopProfit.getText().toString());
                if (v == 0) {
                    return;
                }
                v--;
                etStopProfit.setText(CoinUtil.keepUSDT(v) + "");
            }
        });
        ClickUtil.click(300, customView.findViewById(R.id.ivWinIncrease), () -> {
            if (!TextUtils.isEmpty(etStopProfit.getText().toString())) {
                double v = Double.parseDouble(etStopProfit.getText().toString());
                v++;
                etStopProfit.setText(CoinUtil.keepUSDT(v) + "");

            }
        });


        ClickUtil.click(300, customView.findViewById(R.id.ivLossDecrease), () -> {
            if (TextUtils.isEmpty(etStopLoss.getText().toString())) {
                return;
            }
            double v = Double.parseDouble(etStopLoss.getText().toString());
            if (v == 0) {
                return;
            }
            v--;
            etStopLoss.setText(CoinUtil.keepUSDT(v) + "");

        });
        ClickUtil.click(300, customView.findViewById(R.id.ivLossIncrease), () -> {
            if (TextUtils.isEmpty(etStopLoss.getText().toString())) {
                return;
            }
            double v = Double.parseDouble(etStopLoss.getText().toString());
            v++;
            etStopLoss.setText(CoinUtil.keepUSDT(v) + "");

        });


        tvType = customView.findViewById(R.id.tvType);
        tvPrice = customView.findViewById(R.id.tvPrice);
        if (Double.valueOf(currentZY) != 0) {
            etStopProfit.setText(currentZY);
        }
        if (Double.valueOf(currentZS) != 0) {
            etStopLoss.setText(currentZS);
        }


        TextView btCancel = customView.findViewById(R.id.tvCancel);
        btSure = customView.findViewById(R.id.btSure);
        isBuy = type.equals(BuySellEnum.BUY.getType());
        updatePrice(dialog, newPrice);
        ClickUtil.click(btCancel, () -> {
            dialog.dismiss();
        });
        return dialog;
    }

    /**
     * 更新修改框最新价
     *
     * @param newPrice
     */
    public static void updatePrice(MaterialDialog dialog, String newPrice) {
        tvType.setText(isBuy ? App.INSTANCE.getString(R.string.level_zuoduo): App.INSTANCE.getString(R.string.level_zuokong));
        tvType.setTextColor(ContextCompat.getColor(App.INSTANCE, isBuy ? BuySellEnum.BUY.getColorRes() : BuySellEnum.SELL.getColorRes()));
        tvPrice.setText(newPrice);

        winPrice = isBuy ?
                new BigDecimal(newPrice).add(new BigDecimal(minZYPrice)).toPlainString() :
                new BigDecimal(newPrice).subtract(new BigDecimal(minZYPrice)).toPlainString();
        if (Double.valueOf(winPrice) < 0) {
            winPrice = "0";
        }
        lossPrice = isBuy ?
                new BigDecimal(newPrice).subtract(new BigDecimal(minZSPrice)).toPlainString() :
                new BigDecimal(newPrice).add(new BigDecimal(minZSPrice)).toPlainString();
        if (Double.valueOf(lossPrice) < 0) {
            lossPrice = "0";
        }

        if (isBuy) {
            tvStopProfit.setText("≥" + winPrice);
            tvStopLoss.setText("≤" + lossPrice);
        } else {
            tvStopProfit.setText("≤" + winPrice);
            tvStopLoss.setText("≥" + lossPrice);
        }
        ClickUtil.click(btSure, () -> {
            dialog.dismiss();
            ArrayList<String> result = new ArrayList<>();
            result.add(etStopProfit.getText().toString());
            result.add(etStopLoss.getText().toString());
            onTipSure.onSure(result);
        });
    }

    /**
     * 撤单
     */
    public static MaterialDialog getCancelTip(Context context, boolean isBuy, String price, String num, OnTipSure onTipSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.level_dialog_cancel, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();

        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        TextView tvType = customView.findViewById(R.id.tvType);
        TextView tvPrice = customView.findViewById(R.id.tvPrice);
        TextView tvNum = customView.findViewById(R.id.tvNum);
        tvType.setText(isBuy ? App.INSTANCE.getString(R.string.levelbuySellEnum1) : App.INSTANCE.getString(R.string.levelbuySellEnum2));
        tvType.setTextColor(ContextCompat.getColor(App.INSTANCE, isBuy ? R.color.levelGreen : R.color.levelRed));
        tvPrice.setText(price);
        tvNum.setText(num);
        Button btCancel = customView.findViewById(R.id.btCancel);
        Button btSure = customView.findViewById(R.id.btSure);

        ClickUtil.click(btCancel, () -> {
            dialog.dismiss();
        });
        ClickUtil.click(btSure, () -> {

            onTipSure.onSure();
        });

        return dialog;
    }

    public interface OnTipSure {
        void onSure();
    }

    public interface OnTipNumSure {
        void onSure(String num);
    }

    public interface OnTipResultSure {
        void onSure(List<String> list);
    }
}
