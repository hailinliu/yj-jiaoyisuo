package com.sskj.lib.ui.activity;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.StaticScheme;
import com.github.yoojia.inputs.WidgetAccess;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.R;
import com.sskj.lib.R2;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.presenter.FingerLoginActivityPresenter;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.SchemeUtil;

import butterknife.BindView;

@Route(path = RConfig.LIB_FINGER_LOGIN)
public class FingerLoginActivity extends BaseActivity<FingerLoginActivityPresenter> {
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.tvLogin)
    TextView tvLogin;
    private boolean isSee;

    @Override
    protected int getLayoutId() {
        return R.layout.lib_activity_finger_login;
    }

    @Override
    public FingerLoginActivityPresenter getPresenter() {
        return new FingerLoginActivityPresenter();
    }

    @Override
    protected void initView() {
        AndroidNextInputs inputs = new ToastNextInputs();
        WidgetAccess access = new WidgetAccess(this);
        inputs.clear();
        inputs
                .add(access.findEditText(R.id.etPwd), StaticScheme.NotBlank(), SchemeUtil.pwd())
        ;
        ClickUtil.click(200, ivPwdToggle, () -> {
            isSee = !isSee;
            etPwd.setTransformationMethod(isSee ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            ivPwdToggle.setImageResource(isSee ? R.mipmap.lib_icon_show : R.mipmap.lib_icon_hide);

        });
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                mPresenter.login(etPwd.getText().toString());
            }
        });
        ClickUtil.click(tvLogin, () -> {
            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout();
        });
    }

    @Override
    public void onBackPressed() {
        ARouter.getInstance().build(RConfig.LIB_FINGER).navigation();
        super.onBackPressed();
    }

    public void loginSuccess() {
        SPUtil.put(SPConfig.IS_LOCK, false);
        finish();
    }

}
