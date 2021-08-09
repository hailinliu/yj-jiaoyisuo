package com.sskj.lib.ui.activity;


import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.R2;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.presenter.BasePresenter;
import com.sskj.lib.router.provider.LogoutProvider;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import butterknife.BindView;

@Route(path = RConfig.LIB_FINGER)
public class FingerActivity extends BaseActivity<BasePresenter> {
    @BindView(R2.id.tvPwdLogin)
    TextView tvPwdLogin;
    @BindView(R2.id.viewCheck)
    View viewCheck;
    private FingerprintIdentify mFingerprintIdentify;

    @Override
    protected int getLayoutId() {
        return R.layout.lib_activity_finger;
    }

    @Override
    public BasePresenter getPresenter() {
        return new BasePresenter();
    }

    @Override
    protected void initView() {

        ClickUtil.click(tvPwdLogin, () -> {
//            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
//            logoutProvider.logout();
//            finish();
            ARouter.getInstance().build(RConfig.LIB_FINGER_LOGIN).navigation();
            finish();
        });
        ClickUtil.click(viewCheck, () -> {
            initFinger();
        });
    }

    @Override
    protected void onResume() {
        initFinger();
        super.onResume();
    }

    private void initFinger() {
        if (mFingerprintIdentify != null) {
            mFingerprintIdentify.cancelIdentify();
        }
        mFingerprintIdentify = new FingerprintIdentify(this);
        mFingerprintIdentify.startIdentify(60 * 5, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                SPUtil.put(SPConfig.IS_LOCK, false);

                finish();
            }

            @Override
            public void onNotMatch(int availableTimes) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.libfingerActivity1) + availableTimes);

            }

            @Override
            public void onFailed(boolean isDeviceLocked) {
                if (isDeviceLocked) {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.libfingerActivity2));
                    LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                    logoutProvider.logout();
                }else {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.libcaptchaUtil1));

                }
            }

            @Override
            public void onStartFailedByDeviceLocked() {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.libfingerActivity2));
                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                logoutProvider.logout();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        mFingerprintIdentify.cancelIdentify();
        super.onDestroy();
    }
}
