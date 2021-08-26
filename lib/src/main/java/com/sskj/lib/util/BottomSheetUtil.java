package com.sskj.lib.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.TimeUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.router.provider.SendSmsProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * @author吕志豪 .
 * @date 17-12-26  上午10:57.
 * Github :https://github.com/lvzhihao100
 * E-Mail：1030753080@qq.com
 * 简书 :http://www.jianshu.com/u/6e525b929aac
 */

public class BottomSheetUtil {

    private static Disposable timeDis;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, String... items) {
        ArrayList<ItemBean> ItemBeans = new ArrayList<>();
        for (String item : items) {
            ItemBeans.add(new ItemBean(item));
        }
        return getBottomSheet2(activity, title, itemClick, ItemBeans);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, List<String> items) {
        ArrayList<ItemBean> ItemBeans = new ArrayList<>();
        for (String item : items) {
            ItemBeans.add(new ItemBean(item));
        }
        return getBottomSheet(activity, title, itemClick, ItemBeans);
    }
  /*  @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet1(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, List<ItemBean> items) {
      *//*  ArrayList<ItemBean> ItemBeans = new ArrayList<>();
        for (String item : items) {
            ItemBeans.add(new ItemBean(item));
        }*//*
        return getBottomSheet1(activity, title, itemClick, items);
    }*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet1(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, ArrayList<ItemBean> ItemBeans) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_selector, null, false);
        TextView tvTitle = inflate.findViewById(R.id.tvTitle);
        tvTitle.setTextColor(activity.getColor(R.color.libTextWhite));
        TextView tvCancel = inflate.findViewById(R.id.tvCancel);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(activity)
                .setLastDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider)));
        SlimAdapter.create().register(R.layout.lib_recy_item_text, new SlimInjector<ItemBean>() {
            @Override
            public void onInject(ItemBean data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tv_type, data.content)
                        .image(R.id.image,data.id);
                ;

//                        .textColor(R.id.tv_content, data.color);
            }
        }).attachTo(recyclerView).updateData(ItemBeans);
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(itemClick);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }
    /**
     * 下部弹出选择框
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, ArrayList<ItemBean> ItemBeans) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_selector, null, false);
        TextView tvTitle = inflate.findViewById(R.id.tvTitle);
        tvTitle.setTextColor(activity.getColor(R.color.libTextWhite));
        TextView tvCancel = inflate.findViewById(R.id.tvCancel);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(activity)
                .setLastDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider)));
        SlimAdapter.create().register(R.layout.lib_recy_item_text, new SlimInjector<ItemBean>() {
            @Override
            public void onInject(ItemBean data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tv_type, data.content)
                        .image(R.id.image,data.id);
//                        .textColor(R.id.tv_content, data.color);
            }
        }).attachTo(recyclerView).updateData(ItemBeans);
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(itemClick);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheet2(Activity activity, String title, ItemClickSupport.OnItemClickListener itemClick, ArrayList<ItemBean> ItemBeans) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_selector, null, false);
        TextView tvTitle = inflate.findViewById(R.id.tvTitle);
        tvTitle.setTextColor(activity.getColor(R.color.libTextGray));
        TextView tvCancel = inflate.findViewById(R.id.tvCancel);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(activity)
                .setLastDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider)));
        SlimAdapter.create().register(R.layout.lib_recy_item_text1, new SlimInjector<ItemBean>() {
            @Override
            public void onInject(ItemBean data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tv_content, data.content);
//                        .textColor(R.id.tv_content, data.color);
            }
        }).attachTo(recyclerView).updateData(ItemBeans);
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(itemClick);
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }
    public static BottomSheetDialog showShopCheck(Activity activity, String content, OnSure onSure) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_shop_check, null, false);
        if (!TextUtils.isEmpty(content)) {
            ((TextView) inflate.findViewById(R.id.content)).setText(App.INSTANCE.getString(R.string.libbottomSheetUtil1));
        }
        ClickUtil.click(inflate.findViewById(R.id.btSure), () -> {
            bottomSheetDialog.dismiss();
            if (onSure != null) {
                onSure.onSure();
            }
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog showYanZhengMa(Activity activity, String content, NewOnSure onSure, GetTime getTime) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_yanzhengma, null, false);
       /* if (!TextUtils.isEmpty(content)) {
            ((TextView) inflate.findViewById(R.id.tv_yourphone)).setText(App.INSTANCE.getString(R.string.libbottomSheetUtil1));
        }*/
        TextView tv_dianji = inflate.findViewById(R.id.tv_dianji);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_phone = inflate.findViewById(R.id.tv_yourphone);
        tv_phone.setText(String.format(App.INSTANCE.getString(R.string.lib_yanzheng),content));
        ClickUtil.click(tv_cancel,()->{
            DisposUtil.close(timeDis);
            bottomSheetDialog.dismiss();
        });
        ClickUtil.click(tv_dianji,()->{
            DisposUtil.close(timeDis);
            if(getTime!=null){
                getTime.getTime();
            }
            timeDis = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                    .compose(RxSchedulersHelper.transformer())
                    .map(aLong -> 60 - aLong)
                    .subscribe(aLong -> {
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libTextGray));
                        tv_dianji.setText(String.format(App.INSTANCE.getString(R.string.lib_chongxin), aLong + ""));
                        tv_dianji.setEnabled(false);
                        tv_dianji.setClickable(false);
                        //  btAutoCancel.setText(String.format(App.INSTANCE.getString(R.string.fabi_tipFabiUtil9), aLong + ""));
                    }, e -> {
                    }, () -> {
                        tv_dianji.setText(R.string.lib_huoqu);
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libGreen1));
                        tv_dianji.setClickable(true);
                        tv_dianji.setEnabled(true);
                        // DisposUtil.close(timeDis);
                        // bottomSheetDialog.dismiss();
                    });
        });
        EditText et = inflate.findViewById(R.id.et_yanzheng);
        ClickUtil.click(inflate.findViewById(R.id.btSure), () -> {
            bottomSheetDialog.dismiss();
            if (onSure != null) {
                onSure.onSure(et.getText().toString());
            }
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }
    public static BottomSheetDialog showYanZhengMap(Activity activity, String content,String title, NewOnSure onSure, GetTime getTime) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_yanzhengma, null, false);
       /* if (!TextUtils.isEmpty(content)) {
            ((TextView) inflate.findViewById(R.id.tv_yourphone)).setText(App.INSTANCE.getString(R.string.libbottomSheetUtil1));
        }*/
        TextView tv_dianji = inflate.findViewById(R.id.tv_dianji);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_phone = inflate.findViewById(R.id.tv_yourphone);
       TextView tv_title= inflate.findViewById(R.id.content);
        tv_title.setText(title);
        tv_phone.setText(String.format(App.INSTANCE.getString(R.string.lib_yanzheng),content));
        ClickUtil.click(tv_cancel,()->{
            DisposUtil.close(timeDis);
            bottomSheetDialog.dismiss();
        });
        ClickUtil.click(tv_dianji,()->{
            DisposUtil.close(timeDis);
            if(getTime!=null){
                getTime.getTime();
            }
            timeDis = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                    .compose(RxSchedulersHelper.transformer())
                    .map(aLong -> 60 - aLong)
                    .subscribe(aLong -> {
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libTextGray));
                        tv_dianji.setText(String.format(App.INSTANCE.getString(R.string.lib_chongxin), aLong + ""));
                        tv_dianji.setEnabled(false);
                        tv_dianji.setClickable(false);
                        //  btAutoCancel.setText(String.format(App.INSTANCE.getString(R.string.fabi_tipFabiUtil9), aLong + ""));
                    }, e -> {
                    }, () -> {
                        tv_dianji.setText(R.string.lib_huoqu);
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libGreen1));
                        tv_dianji.setClickable(true);
                        tv_dianji.setEnabled(true);
                        // DisposUtil.close(timeDis);
                        // bottomSheetDialog.dismiss();
                    });
        });
        EditText et = inflate.findViewById(R.id.et_yanzheng);
        ClickUtil.click(inflate.findViewById(R.id.btSure), () -> {
            bottomSheetDialog.dismiss();
            if (onSure != null) {
                onSure.onSure(et.getText().toString());
            }
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog showYanZhengMa2(Activity activity, String content, NewOnSure onSure, GetTime getTime) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_yanzhengma, null, false);
       /* if (!TextUtils.isEmpty(content)) {
            ((TextView) inflate.findViewById(R.id.tv_yourphone)).setText(App.INSTANCE.getString(R.string.libbottomSheetUtil1));
        }*/
        TextView tv_title= inflate.findViewById(R.id.content);
        TextView tv_dianji = inflate.findViewById(R.id.tv_dianji);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_phone = inflate.findViewById(R.id.tv_yourphone);
        tv_title.setText(R.string.lib_yanzhengma);
        tv_phone.setText(String.format(App.INSTANCE.getString(R.string.lib_yanzheng),content));
        ClickUtil.click(tv_cancel,()->{
            DisposUtil.close(timeDis);
            bottomSheetDialog.dismiss();
        });
        ClickUtil.click(tv_dianji,()->{
            DisposUtil.close(timeDis);
            if(getTime!=null){
                getTime.getTime();
            }
            timeDis = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                    .compose(RxSchedulersHelper.transformer())
                    .map(aLong -> 60 - aLong)
                    .subscribe(aLong -> {
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libTextGray));
                        tv_dianji.setText(String.format(App.INSTANCE.getString(R.string.lib_chongxin), aLong + ""));
                        tv_dianji.setEnabled(false);
                        tv_dianji.setClickable(false);
                        //  btAutoCancel.setText(String.format(App.INSTANCE.getString(R.string.fabi_tipFabiUtil9), aLong + ""));
                    }, e -> {
                    }, () -> {
                        tv_dianji.setText(R.string.lib_huoqu);
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libGreen1));
                        tv_dianji.setClickable(true);
                        tv_dianji.setEnabled(true);
                        // DisposUtil.close(timeDis);
                        // bottomSheetDialog.dismiss();
                    });
        });
        EditText et = inflate.findViewById(R.id.et_yanzheng);
        ClickUtil.click(inflate.findViewById(R.id.btSure), () -> {
            bottomSheetDialog.dismiss();
            if (onSure != null) {
                onSure.onSure(et.getText().toString());
            }
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog showYanZhengMa1(Activity activity, String content,String title, NewOnSure onSure, GetTime getTime) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_yanzhengma, null, false);
       /* if (!TextUtils.isEmpty(content)) {
            ((TextView) inflate.findViewById(R.id.tv_yourphone)).setText(App.INSTANCE.getString(R.string.libbottomSheetUtil1));
        }*/
        TextView tv_dianji = inflate.findViewById(R.id.tv_dianji);
      //  tv_dianji.setVisibility(View.GONE);
        TextView tv_content = inflate.findViewById(R.id.content);
        TextView tv_cancel = inflate.findViewById(R.id.tv_cancel);
        TextView tv_phone = inflate.findViewById(R.id.tv_yourphone);
        if(title.equals("交易密码")){
            tv_dianji.setVisibility(View.INVISIBLE);
        }
        tv_phone.setText(content);
        tv_content.setText(title);
        ClickUtil.click(tv_cancel,()->{
            DisposUtil.close(timeDis);
            bottomSheetDialog.dismiss();
        });
        ClickUtil.click(tv_dianji,()->{
            DisposUtil.close(timeDis);
            if(getTime!=null){
                getTime.getTime();
            }
            timeDis = Flowable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                    .compose(RxSchedulersHelper.transformer())
                    .map(aLong -> 60 - aLong)
                    .subscribe(aLong -> {
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libTextGray));
                        tv_dianji.setText(String.format(App.INSTANCE.getString(R.string.lib_chongxin), aLong + ""));
                        tv_dianji.setEnabled(false);
                        tv_dianji.setClickable(false);
                        //  btAutoCancel.setText(String.format(App.INSTANCE.getString(R.string.fabi_tipFabiUtil9), aLong + ""));
                    }, e -> {
                    }, () -> {
                        tv_dianji.setText(R.string.lib_huoqu);
                        tv_dianji.setTextColor(inflate.getContext().getColor(R.color.libGreen1));
                        tv_dianji.setClickable(true);
                        tv_dianji.setEnabled(true);
                        // DisposUtil.close(timeDis);
                        // bottomSheetDialog.dismiss();
                    });
        });
        EditText et = inflate.findViewById(R.id.et_yanzheng);
        ClickUtil.click(inflate.findViewById(R.id.btSure), () -> {
            bottomSheetDialog.dismiss();
            if (onSure != null) {
                onSure.onSure(et.getText().toString());
            }
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }
    /**
     * @param activity
     * @param title       标题
     * @param isPwd       验证密码
     * @param isGoogle    验证谷歌
     * @param isMobile    验证手机号
     * @param onInputList 回调 str[0]
     * @return
     */
    public static BottomSheetDialog showCheck(BaseActivity activity, String title, boolean isPwd, boolean isGoogle, boolean isMobile, CodeTypeEnum codeTypeEnum, OnCheck onInputList) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.lib_bottom_sheet_pwd, null);

        TextView tvTitle = dialogView.findViewById(R.id.tvTitle);
        TextView tvCheckMobile = dialogView.findViewById(R.id.tvCheckMobile);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }


        TextView tvCancel = dialogView.findViewById(R.id.tvCancel);
        LinearLayout llPwd = dialogView.findViewById(R.id.llPwd);
        LinearLayout llGoogleCode = dialogView.findViewById(R.id.llGoogleCode);
        LinearLayout llMobileCode = dialogView.findViewById(R.id.llMobileCode);
        EditText etGoogleCode = dialogView.findViewById(R.id.etGoogleCode);
        EditText etMobileCode = dialogView.findViewById(R.id.etMobileCode);
        EditText etPwd = dialogView.findViewById(R.id.etPwd);
        Button btSendCode = dialogView.findViewById(R.id.btSendCode);
        TextView tvPaste = dialogView.findViewById(R.id.tvPaste);
        Button btSubmit = dialogView.findViewById(R.id.btSubmit);
        bottomSheetDialog.setContentView(dialogView);
        ((ViewGroup) dialogView.getParent()).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) dialogView.getParent().getParent()).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) dialogView.getParent().getParent().getParent()).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) dialogView.getParent().getParent().getParent().getParent()).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) dialogView.getParent().getParent().getParent().getParent().getParent()).setBackgroundColor(Color.TRANSPARENT);
        llPwd.setVisibility(isPwd ? View.VISIBLE : View.GONE);
        llGoogleCode.setVisibility(isGoogle ? View.VISIBLE : View.GONE);
        llMobileCode.setVisibility(isMobile ? View.VISIBLE : View.GONE);

        if (isMobile) {
            if (SPUtil.get(SPConfig.USER_ACCOUNT, "").contains("@")) {
                tvCheckMobile.setText(App.INSTANCE.getString(R.string.libbottomSheetUtil2));
                etMobileCode.setHint(App.INSTANCE.getString(R.string.libbottomSheetUtil3));
            } else {
                tvCheckMobile.setText(App.INSTANCE.getString(R.string.libbottomSheetUtil4));
                etMobileCode.setHint(App.INSTANCE.getString(R.string.libbottomSheetUtil5));

            }
        }

        ClickUtil.click(tvPaste, () -> {
            etGoogleCode.setText(CopyUtil.getTextFromClip(App.INSTANCE));
        });
        ClickUtil.click(btSendCode, () -> {
            SendSmsProvider sendSmsProvider = ARouter.getInstance().navigation(SendSmsProvider.class);

            sendSmsProvider.send(activity.mPresenter, SPUtil.get(SPConfig.MOBILE, ""), SPUtil.get(SPConfig.AREA_CODE,""),SPUtil.get("vcKey",""), codeTypeEnum, null, new SendSmsProvider.OnSend() {
                @Override
                public void onSuccess() {
                    btSendCode.setEnabled(false);
                    DisposUtil.close(timeDis);
                    timeDis = TimeUtil.newTime(activity)
                            .subscribe(aLong -> {
                                if (aLong > 0) {
                                    btSendCode.setText(aLong + "s");
                                } else {
                                    btSendCode.setText(App.INSTANCE.getString(R.string.libsendCodeUtil1));
                                    btSendCode.setEnabled(true);
                                }
                            });

                }
            });
        });
        ClickUtil.click(tvCancel, () -> {
            bottomSheetDialog.dismiss();
        });
        ToastNextInputs inputs = new ToastNextInputs();
        inputs.clear();

        if (isPwd) {
            inputs.add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd());
        }
        if (isGoogle) {
            inputs.add(etGoogleCode, SchemeUtil.notEmpty(etGoogleCode));
        }
        if (isMobile) {
            inputs.add(etMobileCode, SchemeUtil.notEmpty(etMobileCode));
        }
        ClickUtil.click(btSubmit, () -> { //提交修改
            if (inputs.test()) {
                if (onInputList != null) {
                    onInputList.onCheck(etPwd.getText().toString(), etGoogleCode.getText().toString(), etMobileCode.getText().toString());
                }
            }

        });
        return bottomSheetDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static BottomSheetDialog getBottomSheetShenSu(Activity activity, String title, OnInputList onSure) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.lib_bottom_sheet_selector_new, null, false);
        TextView tvTitle = inflate.findViewById(R.id.tvTitle);
        TextView tvCancel = inflate.findViewById(R.id.tvCancel);
       TextView tvSure = inflate.findViewById(R.id.tvSure);
          EditText etTtext = inflate.findViewById(R.id.edittext);
         RadioGroup radioGroup = inflate.findViewById(R.id.radiogroup);
         Map<String,String> map = new HashMap<>();
         map.put("我已付款成功，卖家未及时放行","NO_COIN");
         map.put("我并没有收到买家的转账","NO_PAID");
         map.put("我向卖家多转了钱","MORE_PAID");
         map.put("收到买家付款，但是金额不符","MORE_COIN");
         map.put("其他","OTHER");

        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        tvSure.setOnClickListener(v -> {
            RadioButton radioButton=radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            String value=map.get(radioButton.getText().toString());
            if(!TextUtils.isEmpty(etTtext.getText().toString())&&value!=null){
                bottomSheetDialog.dismiss();
                onSure.onSure(etTtext.getText().toString(),value);
            }else {
                ToastUtil.showShort("请填写申诉内容并选择类型");
            }

        });
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        return bottomSheetDialog;
    }

    public static class ItemBean {
        public int id;
        public String content;
        public int color = 0xffFFFFFF;
        public boolean isRedDot = false;

        public ItemBean(int id,String content) {
            this.id = id;
            this.content = content;
        }
        public ItemBean(String content) {
            this.content = content;
        }
        public ItemBean setColor(int color) {
            this.color = color;
            return this;
        }
    }

    public interface ItemClick {
        void click(int pos);
    }

    public interface OnSure {
        void onSure();
    }
    public interface NewOnSure {
        void onSure(String code);
    }
    public interface GetTime {
        void getTime();
    }
    public interface OnCheck {
        void onCheck(String pwd, String googleCode, String smsCode);
    }

    public interface OnInputList {
        void onSure(String... str);

    }

}
