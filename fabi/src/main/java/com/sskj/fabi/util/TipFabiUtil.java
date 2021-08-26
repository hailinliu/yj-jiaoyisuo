package com.sskj.fabi.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.util.CheckUtil;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.ImgUtil;
import com.sskj.common.util.PermissionTipUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.enums.FabiBuySellEnum;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.util.StringUtil;
import com.sskj.lib.widget.MyRadioGroup;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class TipFabiUtil {

    private static boolean isPrice;
    private static String resultNum;
    private static String resultRMB;
    private static String type = "1";//1法币计价 2购买数量
    private static Disposable timerDispo;
    private static PayTypeEnum payTypeEnum;
    private static int checkId;
    private String coinType;


    /**
     * 订单列表筛选
     *
     * @param activity
     * @return
     */
    public static BottomSheetDialog showQuickPayType(Activity activity, OnInputList onInputList) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View customView = LayoutInflater.from(activity).inflate(R.layout.fabi_dialog_pay_type, null, false);
        bottomSheetDialog.setContentView(customView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        ClickUtil.click(customView.findViewById(R.id.llAlipay), () -> {
            onInputList.onSure(PayTypeEnum.ALIPAY.getQuickPayType());
        });
        ClickUtil.click(customView.findViewById(R.id.llBank), () -> {
            onInputList.onSure(PayTypeEnum.BANK.getQuickPayType());
        });
        ClickUtil.click(customView.findViewById(R.id.ivClose), () -> {
            bottomSheetDialog.dismiss();
        });

        return bottomSheetDialog;
    }

    /**
     * 订单列表筛选
     *
     * @param activity
     * @return
     */
    public static BottomSheetDialog showFilterBottom(Activity activity, String title, List<String> list, OnInputList onInputList) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View customView = LayoutInflater.from(activity).inflate(R.layout.fabi_dialog_filter_bottom, null, false);
        TextView tvTitle = customView.findViewById(R.id.tvTitle);
//        MyRadioGroup myRadioGroup = customView.findViewById(R.id.myRadioGroup);
        RecyclerView recyclerView = customView.findViewById(R.id.recyclerView);
        LinearLayout llRoot = customView.findViewById(R.id.llRoot);
        tvTitle.setText(title);

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        checkId = -1;
        SlimAdapter.create().register(R.layout.fabi_recy_item_filter, new SlimInjector<String>() {
            @Override
            public void onInject(String data, IViewInjector injector, List list) {
                injector.itemView(view -> {
                    ViewGroup viewGroup = (ViewGroup) view;


                    Button childAt = (Button) (viewGroup.getChildAt(0));
                    childAt.setId(injector.pos());
                    childAt.setText(data);
                    childAt.setOnClickListener(v -> {
                        if (checkId == childAt.getId()) {
                            checkId = -1;
                            childAt.setBackground(ContextCompat.getDrawable(App.INSTANCE, R.drawable.fabi_shape_filter_uncheck));
                            childAt.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiTextContent));
                        } else {
                            if (checkId != -1) {
                                Button checkButton = (Button) recyclerView.findViewById(checkId);
                                checkButton.setBackground(ContextCompat.getDrawable(App.INSTANCE, R.drawable.fabi_shape_filter_uncheck));
                                checkButton.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiTextContent));
                            }

                            childAt.setBackground(ContextCompat.getDrawable(App.INSTANCE, R.drawable.fabi_shape_filter_check));
                            childAt.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiSelect));
                            checkId = childAt.getId();
                        }
                        onInputList.onSure(checkId + "");

                    });
                });
            }
        }).attachTo(recyclerView).updateData(list);
//        llRoot.post(() -> {
//            llRoot.removeViewAt(0);
//            MyRadioGroup.LayoutParams layoutParams = new MyRadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            myRadioGroup.addView(recyclerView, 0, layoutParams);
//        });
//        myRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            onInputList.onSure(checkedId + "");
//        });

        bottomSheetDialog.setContentView(customView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;

    }

    /**
     * 快捷买币确定弹窗
     *
     * @param activity
     * @return
     */
    public static BottomSheetDialog showQuickBuy(Activity activity, PayTypeEnum payType, String price, String num, String money, OnInputList onInputList) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View customView = LayoutInflater.from(activity).inflate(R.layout.fabi_dialog_quick_buy, null, false);
        TextView tvPayType = customView.findViewById(R.id.tvPayType);
        TextView tvPrice = customView.findViewById(R.id.tvPrice);
        TextView tvNum = customView.findViewById(R.id.tvNum);
        TextView tvMoney = customView.findViewById(R.id.tvMoney);
        TextView tvClose = customView.findViewById(R.id.tvClose);
        tvPayType.setText(payType.getName());
        tvPrice.setText(price);
        tvNum.setText(num);
        tvMoney.setText(money);
        Button btSubmit = customView.findViewById(R.id.btSubmit);


        ClickUtil.click(btSubmit, () -> {
            bottomSheetDialog.dismiss();
            onInputList.onSure();
        });
        ClickUtil.click(tvClose, bottomSheetDialog::dismiss);
        bottomSheetDialog.setContentView(customView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;

    }

    /**
     * 拨打电话
     *
     * @param activity
     * @return
     */
    public static MaterialDialog showCall(Activity activity, String phone, OnInputList onTipSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_phone, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        TextView tvContent = customView.findViewById(R.id.tvContent);
        Button btSure = customView.findViewById(R.id.btSure);
        Button btCancel = customView.findViewById(R.id.btCancel);
        ClickUtil.click(btCancel, dialog::dismiss);
        tvContent.setText(phone);
        btSure.setText(App.INSTANCE.getString(R.string.fabitipFabiUtil1));

        ClickUtil.click(btSure, () -> {
            dialog.dismiss();
            onTipSure.onSure(phone);
        });
        return dialog;

    }
public static MaterialDialog showBottom(Activity activity,OnInputList onInputList){
    MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
            .customView(R.layout.fabi_dialog_filter, false)
            .autoDismiss(true)
            .cancelable(true)
            .show();
    return dialog;
}
    /**
     * 筛选。右侧
     *
     * @param activity
     * @return
     */
    public static MaterialDialog showFilter(Activity activity, OnInputList onInputList) {
        final RadioButton[] button = new RadioButton[1];
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_filter, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()) / 4 * 3; //设置宽度
        lp.height = (int) (display.getHeight()); //设置宽度
        lp.gravity = Gravity.RIGHT;
        dialog.getWindow().setAttributes(lp);
        View customView = dialog.getCustomView();
        EditText etMoney = customView.findViewById(R.id.etMoney);
        Button btReset = customView.findViewById(R.id.btReset);
        Button btSubmit = customView.findViewById(R.id.btSubmit);
        RadioGroup rgFilter = customView.findViewById(R.id.rgFilter);
        RadioButton rbAll= customView.findViewById(R.id.rbAll);
        RadioButton rbAlipay = customView.findViewById(R.id.rbAlipay);
        RadioButton rbBank = customView.findViewById(R.id.rbBank);
        RadioButton rbWX = customView.findViewById(R.id.rbWX);
        MyRadioGroup group = customView.findViewById(R.id.myradiogroup);
        group.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                button[0] = customView.findViewById(checkedId);
            }
        });
      /*  int id = group.getCheckedRadioButtonId();
        RadioButton button =  customView.findViewById(group.getCheckedRadioButtonId());*/

        ClickUtil.click(btReset, () -> {
            etMoney.setText("");
//            rbAlipay.setChecked(false);
//            rbWX.setChecked(false);
//            rbBank.setChecked(false);
            rgFilter.clearCheck();
        });
        payTypeEnum = null;
        etMoney.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        ClickUtil.click(btSubmit, () -> {
            ArrayList<PayTypeEnum> payTypeEnums = new ArrayList<>();
            String payType = null;
            if (rbAlipay.isChecked()) {
                payTypeEnums.add(PayTypeEnum.ALIPAY);
                payType = PayTypeEnum.ALIPAY.getName();
            }
            if (rbWX.isChecked()) {
                payTypeEnums.add(PayTypeEnum.WX);
                payType = PayTypeEnum.WX.getName();

            }
            if (rbBank.isChecked()) {
                payTypeEnums.add(PayTypeEnum.BANK);
                payType = PayTypeEnum.BANK.getName();
            }
            if(rbAll.isChecked()){
                payType = "";
            }
            onInputList.onSure(etMoney.getText().toString(), payType,button[0]!=null?button[0].getText().toString():"");
            dialog.dismiss();
        });
        return dialog;

    }
    public static MaterialDialog showFilter1(Activity activity, OnInputList onInputList) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_filter1, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()) / 3 * 2; //设置宽度
        lp.height = (int) (display.getHeight()); //设置宽度
        lp.gravity = Gravity.RIGHT;
        dialog.getWindow().setAttributes(lp);
        View customView = dialog.getCustomView();
        EditText etMoney = customView.findViewById(R.id.etMoney);
        Button btReset = customView.findViewById(R.id.btReset);
        Button btSubmit = customView.findViewById(R.id.btSubmit);
        RadioGroup rgFilter = customView.findViewById(R.id.myradio);
        RadioButton rbAlipay = customView.findViewById(R.id.rb_mairu);
        RadioButton rbBank = customView.findViewById(R.id.rb_maichu);
        RadioButton rbWX = customView.findViewById(R.id.rb_quan);
        ClickUtil.click(btReset, () -> {
            etMoney.setText("");
//            rbAlipay.setChecked(false);
//            rbWX.setChecked(false);
//            rbBank.setChecked(false);
            rgFilter.clearCheck();
        });
        payTypeEnum = null;
        etMoney.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        ClickUtil.click(btSubmit, () -> {
            ArrayList<PayTypeEnum> payTypeEnums = new ArrayList<>();
            String payType = null;
            if (rbAlipay.isChecked()) {
                payTypeEnums.add(PayTypeEnum.ALIPAY);
                payType = PayTypeEnum.ALIPAY.getType();
            }
            if (rbWX.isChecked()) {
                payTypeEnums.add(PayTypeEnum.WX);
                payType = PayTypeEnum.WX.getType();

            }
            if (rbBank.isChecked()) {
                payTypeEnums.add(PayTypeEnum.BANK);
                payType = PayTypeEnum.BANK.getType();
            }
            onInputList.onSure(etMoney.getText().toString(), payType);
            dialog.dismiss();
        });
        return dialog;

    }
    public static MaterialDialog showFilter2(List<String> list,Activity activity, OnInputList onInputList) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_filter2, false)
                .backgroundColorRes(R.color.lib_bg)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()) / 4 * 3; //设置宽度
        lp.height = (int) (display.getHeight()); //设置宽度
        lp.gravity = Gravity.RIGHT;
        dialog.getWindow().setAttributes(lp);
        View customView = dialog.getCustomView();
        RadioButton rb1 = customView.findViewById(R.id.rb1);
        rb1.setText(list.get(0));
        RadioButton rb2 =  customView.findViewById(R.id.rb2);
        rb2.setText(list.get(1));
        RadioButton rb3 =  customView.findViewById(R.id.rb3);
        rb3.setText(list.get(2));
        RadioButton rb4 = customView.findViewById(R.id.rb4);
        rb4.setText(list.get(3));
        EditText et_dingdan = customView.findViewById(R.id.et_dingdan);
        Button btReset = customView.findViewById(R.id.btReset);
        Button btSubmit = customView.findViewById(R.id.btSubmit);
        RadioGroup rgFilter = customView.findViewById(R.id.myradio);
        RadioGroup rg_status = customView.findViewById(R.id.rg_status);
        RadioButton rbwoyi = customView.findViewById(R.id.rb_woyi);
        RadioButton rb_daizhifu = customView.findViewById(R.id.rb_daizhifu);
        ClickUtil.click(btReset, () -> {
           // etMoney.setText("");
//            rbAlipay.setChecked(false);
//            rbWX.setChecked(false);
//            rbBank.setChecked(false);
            rgFilter.clearCheck();
        });
        payTypeEnum = null;
      //  etMoney.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        ClickUtil.click(btSubmit, () -> {
            ArrayList<PayTypeEnum> payTypeEnums = new ArrayList<>();
          //  String payType = null;
            RadioButton button =  rgFilter.findViewById(rgFilter.getCheckedRadioButtonId());
            RadioButton button1 =  rg_status.findViewById(rg_status.getCheckedRadioButtonId());
            onInputList.onSure(et_dingdan.getText().toString(),button1!=null?button1.getText().toString():"",button!=null?button.getText().toString():"");
            dialog.dismiss();
        });
        return dialog;

    }
    /**
     * 交易
     *
     * @param activity
     * @param isBuy       购买、出售
     * @param price       单价
     * @param minLimit    最小限额
     * @param maxLimit    最大限额
     * @param onInputList
     * @return
     */
    public static BottomSheetDialog showTrade(Activity activity,String cointype,String currency,boolean isBuy, String price,String limit,String minLimit,String maxLimit,String remain,String time,String name,OnInputList onInputList) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View rootView = LayoutInflater.from(activity).inflate(R.layout.fabi_fragment_bottom_dialog, null, false);
        LinearLayout llSelect =  rootView.findViewById(R.id.llSelect);
        TextView tv_first = rootView.findViewById(R.id.tv_first);
        TextView tv_name = rootView.findViewById(R.id.tv_name);
        TextView tv_num = rootView.findViewById(R.id.tv_num);
        TextView tv_unit2 = rootView.findViewById(R.id.tv_unit2);
        TextView tv_unit= rootView.findViewById(R.id.tv_unit);
        TextView tv_unit1 = rootView.findViewById(R.id.tv_unit1);
        TextView tv_xianzhi = rootView.findViewById(R.id.tv_xianzhi);
        EditText etNum = rootView.findViewById(R.id.et_num);
        TextView tv_xiannum =rootView.findViewById(R.id.tv_xiannum);
        ImageView imageView =  rootView.findViewById(R.id.image1);
        TextView tv_shengyu = rootView.findViewById(R.id.tv_shengyu_content);
        TextView tv_type =rootView.findViewById(R.id.tv_type);
        ImageView image1 = rootView.findViewById(R.id.image1);
        TextView tv_quanbu = rootView.findViewById(R.id.tv_quanbu);
        TextView tv_sure = rootView.findViewById(R.id.tv_sure);
        TextView tv_moneyunti = rootView.findViewById(R.id.tv_moneyunit);
        TextView tv_price = rootView.findViewById(R.id.tv_price);
        TextView tv_cancel= rootView.findViewById(R.id.tv_cancel);
        TextView tv_time = rootView.findViewById(R.id.tv_time);
        TextView tv_title = rootView.findViewById(R.id.tv_title);
        if(isBuy){
            tv_first.setBackground(activity.getResources().getDrawable(R.drawable.fabi_shape_app_oval));
            tv_num.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            tv_unit2.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            tv_xianzhi.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            tv_xiannum.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            imageView.setImageDrawable(activity.getDrawable(R.mipmap.fabi_icon_switch1));
            tv_type.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            tv_quanbu.setTextColor(activity.getResources().getColor(R.color.libGreen1));
            tv_sure.setBackgroundColor(activity.getResources().getColor(R.color.libGreen1));
            tv_sure.setText("购买");
        }else {
            tv_first.setBackground(activity.getResources().getDrawable(R.drawable.fabi_shape_app_oval1));
            tv_num.setTextColor(activity.getResources().getColor(R.color.libRed));
            tv_unit2.setTextColor(activity.getResources().getColor(R.color.libRed));
            tv_xianzhi.setTextColor(activity.getResources().getColor(R.color.libRed));
            tv_xiannum.setTextColor(activity.getResources().getColor(R.color.libRed));
            imageView.setImageDrawable(activity.getDrawable(R.mipmap.fabi_icon_switch2));
            tv_type.setTextColor(activity.getResources().getColor(R.color.libRed));
            tv_quanbu.setTextColor(activity.getResources().getColor(R.color.libRed));
            tv_sure.setBackgroundColor(activity.getResources().getColor(R.color.libRed));
            tv_sure.setText("出售");
        }
       // CommonTabLayout commonTabLayout = rootView.findViewById(R.id.commonTabLayout);
        tv_xiannum.setText(limit+currency);
        tv_shengyu.setText(remain);
        tv_unit.setText(cointype);
        tv_unit1.setText(cointype);
        tv_unit2.setText(currency);
        tv_price.setText("0");
        tv_name.setText(name);
        tv_num.setText(price);
        tv_first.setText(StringUtil.getFirstName(name));
        tv_moneyunti.setText(currency);
        tv_time.setText(time+"分钟");
        isPrice = false;
       // etNum.setHint(String.format("请输入%s",isPrice?App.INSTANCE.getString(R.string.fabitipFabiUtil5) : App.INSTANCE.getString(R.string.fabitipFabiUtil6)));
       // ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
       // tabItems.add(new TabItem(App.INSTANCE.getString(R.string.fabitipFabiUtil9), 0, 0));
       // tabItems.add(new TabItem(String.format(App.INSTANCE.getString(R.string.fabitipFabiUtil10), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()), 0, 0));
        resultNum = "0";
        resultRMB = "0";

        ClickUtil.click(tv_quanbu, () -> {
            String num = calculateAll(isPrice, price,tv_shengyu.getText().toString(),maxLimit);
            etNum.setText(num);

        });
        ClickUtil.click(tv_cancel, () -> {
            bottomSheetDialog.dismiss();
        });
       ClickUtil.click(llSelect,()->{
           if(isPrice){
               isPrice =false;
               tv_type.setText("按金额");
               etNum.setHint("请输入数量");
               tv_title.setText("购买数量");
               tv_unit.setText(cointype);
           }else {
               isPrice = true;
               tv_type.setText("按数量");
               tv_title.setText("购买金额");
               etNum.setHint("请输入金额");
               tv_unit.setText(currency);
           }
       });
        RxTextView.afterTextChangeEvents(etNum)
                .subscribe(textEdit -> {
                    String text = textEdit.editable().toString();
                if (!TextUtils.isEmpty(text)) {
                       if (isPrice) {
                            tv_price.setText(text);
                            BigDecimal usdtBig = new BigDecimal(text).divide(new BigDecimal(price), 8, BigDecimal.ROUND_DOWN);
                           // String usdt = CoinUtil.keepUSDT(usdtBig.toPlainString());
                           // tvUSDT.setText(String.format("%s USDT", CoinUtil.keepUSDT(usdt)));
                            resultNum = usdtBig.toPlainString();
                            resultRMB = text;

                          /*  tvRMB.setText(String.format("$%s", CoinUtil.keepRMB(text)));
                            resultRMB = text;*/
                        } else {
                            BigDecimal priceBig = new BigDecimal(text).multiply(new BigDecimal(price));
                            // String rmb = CoinUtil.keepRMB(priceBig.toPlainString());
                            resultNum = text;
                            resultRMB = priceBig.toPlainString();
                            tv_price.setText(String.format("%s",priceBig.toPlainString()));

                         /*   tvRMB.setText(String.format("$%s", CoinUtil.keepRMB(rmb)));
                            resultRMB = rmb;*/
                        }
                    } else {
                        resultNum = "0";
                        resultRMB = "0";
                        tv_price.setText("0");
                        // tvRMB.setText("￥0.00");
                    }
                });
        ClickUtil.click(tv_sure, () -> {
            if (TextUtils.isEmpty(etNum.getText())) {
                ToastUtil.showShort(etNum.getHint());
                return;
            }
            if (Double.valueOf(resultRMB) > Double.valueOf(maxLimit)) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil14));
                return;
            }
            if (Double.valueOf(resultRMB) < Double.valueOf(minLimit)) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil15));
                return;
            }
            if (Double.valueOf(resultNum) > Double.valueOf(remain)) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil16));
                return;
            }
            if (onInputList != null) {
                if(isPrice){
                    type="0";
                }else {
                    type="1";

                }
                onInputList.onSure(resultNum, resultRMB,type);
            }

        });

        bottomSheetDialog.setContentView(rootView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;

    }

    /**
     * 发布订单弹窗
     *
     * @param context
     * @param isBuy
     * @param price
     * @param num
     * @param onInputList
     * @return
     */
    public static MaterialDialog showPublish(Context context, boolean isBuy, String price, String num, OnInputList onInputList) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.fabi_dialog_publish_confirm, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        View view = dialog.getCustomView();
        ((ViewGroup) (view.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((TextView) view.findViewById(R.id.tv_price)).setText(String.format("%s", CoinUtil.keepRMB(price)));
        ((TextView) view.findViewById(R.id.tv_amount)).setText(String.format("%s USDT", CoinUtil.keepUSDT(num)));
        String money = CoinUtil.keepRMB(new BigDecimal(num).multiply(new BigDecimal(price)).toPlainString());
        ((TextView) view.findViewById(R.id.tv_money)).setText(String.format("%s", CoinUtil.keepUSDT(money)));
        ((TextView) view.findViewById(R.id.tv_price_left)).setText(String.format(App.INSTANCE.getString(R.string.fabitipFabiUtil18), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
        ((TextView) view.findViewById(R.id.tv_amount_left)).setText(String.format(App.INSTANCE.getString(R.string.fabitipFabiUtil10), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
        ((TextView) view.findViewById(R.id.tv_money_left)).setText(String.format(App.INSTANCE.getString(R.string.fabitipFabiUtil19), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
        EditText et_pwd = view.findViewById(R.id.et_pwd);
        ClickUtil.click(view.findViewById(R.id.tvCancel), () -> {
            dialog.dismiss();
        });
        ClickUtil.click(view.findViewById(R.id.tvSure), () -> {
            if (TextUtils.isEmpty(et_pwd.getText().toString())) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil17));
                return;
            }
            if (!CheckUtil.isPayPwd(et_pwd.getText().toString())) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil20));
                return;
            }
            dialog.dismiss();
            onInputList.onSure(et_pwd.getText().toString());
        });
        return dialog;
    }

    /**
     * 放币资金密码弹窗
     *
     * @param onInputList
     * @return
     */
    public static MaterialDialog sendCoin(Activity activity, OnInputList onInputList) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_send_coin, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        View view = dialog.getCustomView();
        ((ViewGroup) (view.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        EditText etPwd = view.findViewById(R.id.etPwd);
        ClickUtil.click(view.findViewById(R.id.btCancel), dialog::dismiss);
        TextView mSubmit = view.findViewById(R.id.btSure);
        ToastNextInputs inputs = new ToastNextInputs();
        inputs.add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd());

        ClickUtil.click(mSubmit, () -> {
            String pwd = etPwd.getText().toString().trim();
            if (inputs.test()) {
                onInputList.onSure(pwd);
            }

        });
        return dialog;
    }

    /**
     * 二维码
     *
     * @param activity
     * @param title
     * @param account
     * @param url
     */
    public static void showQR(Activity activity, String title, String account, String url) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_erweima, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        View view = dialog.getCustomView();
        ((ViewGroup) (view.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (view.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ImageView ivQR = view.findViewById(R.id.ivQRCode);
        TextView tvContent = view.findViewById(R.id.tvContent);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ClickUtil.click(ivClose, () -> {
            dialog.dismiss();
        });
        tvTitle.setText(title);
        tvContent.setText(account);
        ImageUtil.bitmap(url, bitmap -> {
            ivQR.setImageBitmap(bitmap);
//            ClickUtil.click(tvContent, () -> {
//                new RxPermissions(activity).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .subscribe(isGrant -> {
//                            if (isGrant) {
//                                if (ImgUtil.saveImageToGallery(activity, bitmap) == null) {
//                                    ToastUtil.showShort(App.INSTANCE.getString(R.string.fabitipFabiUtil21));
//                                }
//                            } else {
//                                PermissionTipUtil.tip(activity, App.INSTANCE.getString(R.string.fabitipFabiUtil22));
//                            }
//                        });
//            });
        });

//        tvContent.setText(App.INSTANCE.getString(R.string.fabitipFabiUtil23));
    }

    /**
     * 申诉
     *
     * @param activity
     * @param onInputList
     * @return
     */
    public static MaterialDialog showAppealTip(Activity activity, OnInputList onInputList) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.fabi_dialog_appeal, false)
                .autoDismiss(true)
                .cancelable(true)
                .build();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        EditText etReason = customView.findViewById(R.id.etReason);
        Button btSure = customView.findViewById(R.id.btSure);
        Button btCancel = customView.findViewById(R.id.btCancel);
        ClickUtil.click(btCancel, dialog::dismiss);

        ClickUtil.click(btSure, () -> {
            if (TextUtils.isEmpty(etReason.getText())) {
                ToastUtil.showShort(etReason.getHint());
                return;
            }
            onInputList.onSure(etReason.getText().toString());
        });
        return dialog;
    }

    public interface OnInputList {
        void onSure(String... str);

    }


    /**
     * 根据法币计价和出售数量计算总的数量与价格
     */
    private static String calculateAll(boolean isPrice, String price, String leftNum, String maxLimit) {

        String num = "0.0000";
        BigDecimal allMoney = new BigDecimal(leftNum).multiply(new BigDecimal(price));//最多购买金额
        BigDecimal allNum = new BigDecimal(maxLimit).divide(new BigDecimal(price), 8, BigDecimal.ROUND_DOWN);

        if (isPrice) {
            if (!TextUtils.isEmpty(price)) {
                double buyPrice = Math.min(Double.valueOf(maxLimit), allMoney.doubleValue());
                return CoinUtil.keepRMB(buyPrice);
            }
        } else {
            if (!TextUtils.isEmpty(price)) {
                double buyNum = Math.min(allNum.doubleValue(), Double.valueOf(leftNum));
                return CoinUtil.keepUSDT(buyNum);
            }
        }
        return num;
    }
}
