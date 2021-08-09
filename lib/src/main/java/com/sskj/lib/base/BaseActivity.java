package com.sskj.lib.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.IBaseViewLife;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.widget.MyProgressDialogs;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Date;
import java.util.Locale;
import java.util.Stack;

import butterknife.ButterKnife;


/**
 * Created by Lee on 2018/1/25 0025.
 */

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IBaseViewLife {
    public T mPresenter;
    public BaseActivity mActivity;
    private long oldTime = 0;
    private MyProgressDialogs mProgressDialogs;
    private boolean mActivityIsActive;
    private int mRequestCount;
    boolean IS_ORIENTATION_PORTRAIT = true;

    protected void dealFirstSaveInstance(Bundle savedInstanceState) {

    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getOrientation()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        dealFirstSaveInstance(savedInstanceState);
        mProgressDialogs = new MyProgressDialogs(this);
        if (getImmersion()) {
            if(this.getLocalClassName().contains("PayOrderActivity")){
                ImmersionBar.with(this).statusBarColor(R.color.libGreen1)
                        .navigationBarColor(R.color.libGreen1)
                        .statusBarDarkFont(false).init();
                setContentView(R.layout.lib_activity);
            }else if(this.getLocalClassName().contains("PayOrderSellActivity")){
                ImmersionBar.with(this).statusBarColor(R.color.libRed)
                        .navigationBarColor(R.color.libRed)
                        .statusBarDarkFont(false).init();
                setContentView(R.layout.lib_activity);
            }
            else {
                ImmersionBar.with(this).statusBarColor(R.color.libstatusbar).statusBarDarkFont(false).init();
                setContentView(R.layout.lib_activity);
            }

        } else {
            ImmersionBar.with(this).statusBarColor(R.color.libstatusbar).navigationBarColor(R.color.lib_bg_white).navigationBarAlpha(1).statusBarDarkFont(false).init();
            setContentView(R.layout.lib_immer_activity);
        }

        ViewGroup viewRoot = findViewById(R.id.flImmerRoot);
        viewRoot.addView(getLayoutInflater().inflate(getLayoutId(), null, false));
        mActivity = this;
        mPresenter = getPresenter();
        mPresenter.attachView(this, this);
        ButterKnife.bind(this);
        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
        String lan=  CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString());
        httpHeaders.put(SPConfig.LANGUAGE, CommonUtil.getHeaderLanguage(lan));
        initView();
        initData();
        initEvent();
        View back = findViewById(R.id.ivBack);
        if (back != null) {
            ClickUtil.click(back, this::onBackPressed);
        }
    }

    protected void initEvent() {
    }

    public boolean getImmersion() {
        return true;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setRightTitle(String title, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        TextView tvTitle = findViewById(R.id.right_tv);
        if (tvTitle != null) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
            tvTitle.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置顶部右边图片及点击事件
     *
     * @param res
     * @param clickListener
     */
    public void setRightImg(int res, View.OnClickListener clickListener) {

        ImageView imageView = findViewById(R.id.ivRight);
        if (imageView != null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(res);
            imageView.setOnClickListener(clickListener);
        }
    }


    @Override
    protected void onDestroy() {
        mProgressDialogs.closeDialog();
        super.onDestroy();
        mPresenter.cancelRequest();
        mPresenter.detachView();
        ImmersionBar.with(this).destroy();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
        ImmersionBar.with(this).init();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityIsActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityIsActive = false;
    }

    /**
     * 初始化布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化当前View 的 presenter
     *
     * @return
     */
    public abstract T getPresenter();

    @Override
    public void onBackPressed() {
        Stack<Activity> allActivities = AppManager.getAppManager().getAllActivities();
        if (allActivities.size() <= 1) {
            long nowTime = new Date().getTime();
            if (nowTime - oldTime <= 2000) {
                AppManager.getAppManager().AppExit(this);
            } else {
                oldTime = nowTime;
                ToastUtil.showShort(App.INSTANCE.getString(R.string.libbaseActivity1));
            }
        } else {
            super.onBackPressed();
        }
    }

    public void showLoading() {
        if (mActivityIsActive && !isFinishing()) {
            if (mProgressDialogs != null && mRequestCount <= 0) {
                mRequestCount = 1;
                mProgressDialogs.showDialog();
            } else {
                ++mRequestCount;
            }
        }
    }

    public void hideLoading() {
        if (!isFinishing()) {
            if (mRequestCount <= 1) {
                mRequestCount = 0;
                mProgressDialogs.closeDialog();
            } else {
                --mRequestCount;
            }
        }
    }

    public boolean getOrientation() {
        return IS_ORIENTATION_PORTRAIT;
    }



    @Override
    public String getText(TextView textView) {
        return textView.getText().toString();
    }


    @Override
    public int color(int id) {
        return ContextCompat.getColor(this, id);
    }

    @Override
    public Drawable drawable(int id) {
        return ContextCompat.getDrawable(this, id);
    }

    @Override
    public void setText(TextView textView, String text) {
        if (textView != null) {
            textView.setText(text);
        }
        if (textView instanceof EditText) {
            ((EditText) textView).setSelection(textView.getText().length());
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        Locale appLocaleByLanguage = LanguageUtil.getAppLocaleByLanguage(LanguageUtil.getAppLanguage(App.INSTANCE));
        super.attachBaseContext(LanguageUtil.updateResources(context, appLocaleByLanguage));
    }
}
