package com.sskj.mine.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.LazyLoaders;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.CaptchaUtil;
import com.sskj.lib.util.EditUtil;
import com.sskj.lib.util.HideUtil;
import com.sskj.lib.util.PatternUtils;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.util.SendCodeUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.presenter.SettingFundPwdPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;


@Route(path = RConfig.MINE_SET_FUND_PWD)
public class SettingFundPwdActivity extends BaseActivity<SettingFundPwdPresenter> {
    @Autowired
    int type = 0;//1 修改资金密码 0 设置资金密码

    @BindView(R2.id.etCode)
    EditText etCode;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.btGetCode)
    Button btGetCode;
    @BindView(R2.id.tvAccount)
    TextView tvAccount;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.etPwdAgain)
    EditText etPwdAgain;
    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
    @BindView(R2.id.ivNextPwdToggle)
    ImageView ivNextPwdToggle;
    private ToastNextInputs inputs;
    private Disposable timeDispo;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_setting_trade_pwd;
    }

    @Override
    public SettingFundPwdPresenter getPresenter() {
        return new SettingFundPwdPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        if (type == 0) {
            setTitle(App.INSTANCE.getString(R.string.minesettingFundPwdActivity1));
        } else {
            setTitle(App.INSTANCE.getString(R.string.minesettingFundPwdActivity2));
        }
        EditUtil.togglePs(etPwd, ivPwdToggle);
        EditUtil.togglePs(etPwdAgain, ivNextPwdToggle);
        inputs = new ToastNextInputs();
        inputs
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd())
                .add(etPwdAgain, SchemeUtil.notEmpty(etPwdAgain), SchemeUtil.equalsPwd(LazyLoaders.fromEditText(etPwd)))
                .add(etCode, SchemeUtil.notEmpty(etCode), SchemeUtil.smsCode());
        //手机号
        if (PatternUtils.isMobile(SPUtil.get(SPConfig.USER_ACCOUNT, ""))) {
            tvTitle.setText(App.INSTANCE.getString(R.string.minemine_setting_fund_pwd260));
            tvAccount.setText(SPUtil.get(SPConfig.USER_ACCOUNT, ""));
        } else if (PatternUtils.isEmail(SPUtil.get(SPConfig.USER_ACCOUNT, ""))) {
            //邮箱
            tvAccount.setText(SPUtil.get(SPConfig.USER_ACCOUNT, ""));
            tvTitle.setText(App.INSTANCE.getString(R.string.minemine_setting_fund_pwd261));
        } else {
            tvAccount.setText(SPUtil.get(SPConfig.USER_ACCOUNT, ""));
        }


    }

    @Override
    protected void initEvent() {
        ClickUtil.click(200, btGetCode, () -> {
            CaptchaUtil.check(this, (result, validate, msg) -> {
                mPresenter.sendCode(SPUtil.get(SPConfig.USER_ACCOUNT, ""), validate);
            });
        });

        ClickUtil.click(btSubmit, () -> {
          //  mPresenter.setFundPwd(etCode.getText().toString(), etPwd.getText().toString());
            if (inputs.test()) {
                mPresenter.setFundPwd(etCode.getText().toString(), etPwd.getText().toString());
            }
        });
    }

    public void onSendCodeSuccess() {
        startCount();
    }

    public void startCount() {
        btGetCode.setEnabled(false);
        DisposUtil.close(timeDispo);
        timeDispo = SendCodeUtil.timeCount(this, btGetCode);
    }

    public void onSetSuccess() {
        userViewModel.update();
        finish();
    }
}
