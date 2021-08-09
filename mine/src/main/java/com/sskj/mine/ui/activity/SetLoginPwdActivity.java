package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.LazyLoaders;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.EditUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.presenter.SetLoginPwdPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改登录密码
 */
@Route(path = RConfig.MINE_SET_LOGIN_PWD)
public class SetLoginPwdActivity extends BaseActivity<SetLoginPwdPresenter> {

    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.etPwdOrigin)
    EditText etPwdOrigin;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
    @BindView(R2.id.ivNewPwdToggle)
    ImageView ivNewPwdToggle;
    @BindView(R2.id.ivNewNextPwdToggle)
    ImageView ivNewNextPwdToggle;
    private ToastNextInputs inputs;


    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_setting_pwd;
    }

    @Override
    public SetLoginPwdPresenter getPresenter() {
        return new SetLoginPwdPresenter();
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.minesetLoginPwdActivity1));
        inputs = new ToastNextInputs();
        inputs
                .add(etPwdOrigin, SchemeUtil.notEmpty(etPwdOrigin), SchemeUtil.pwd())
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd())
                .add(etPwdAgain, SchemeUtil.notEmpty(etPwdAgain), SchemeUtil.equalsPwd(LazyLoaders.fromEditText(etPwd)));

        EditUtil.togglePs(etPwdOrigin, ivPwdToggle);
        EditUtil.togglePs(etPwd, ivNewPwdToggle);
        EditUtil.togglePs(etPwdAgain, ivNewNextPwdToggle);
    }

    @Override
    protected void initEvent() {
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                mPresenter.setLoginPwd(etPwd.getText().toString(), etPwdOrigin.getText().toString());
            }
        });
    }

    public void updataUI() {
        LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
        logoutProvider.logout();
    }


}
