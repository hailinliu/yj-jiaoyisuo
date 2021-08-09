package com.sskj.login.ui.activity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.LazyLoaders;
import com.hey.lib.HeySpinner;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.AreaCodeEnum;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.CaptchaUtil;
import com.sskj.lib.util.EditUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.util.SendCodeUtil;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.presenter.PwdForgetActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * 忘记密码
 */
//@Route(path = RConfig.LOGIN_PWD_FORGET)
public class PwdForgetActivity extends BaseActivity<PwdForgetActivityPresenter> {

    @BindView(R2.id.etAccount)
    EditText etAccount;
    @BindView(R2.id.etCheckCode)
    EditText etCode;
    @BindView(R2.id.tvCheckCode)
    TextView tvGetCheckCode;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
    @BindView(R2.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R2.id.ivPwdToggleAgain)
    ImageView ivPwdToggleAgain;
    @BindView(R2.id.btSubmit)
    Button btSubmit;

    private Disposable timeDis;
    private AndroidNextInputs inputs;
    private AndroidNextInputs inputsCheckCode;
    private LazyLoaders lazyLoaders;
    private AreaCodeEnum areaCodeEnumChoose;
    @BindView(R2.id.heySpinner)
    HeySpinner heySpinner;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_pwd_forget;
    }

    @Override
    public boolean getImmersion() {
        return false;
    }

    @Override
    public PwdForgetActivityPresenter getPresenter() {
        return new PwdForgetActivityPresenter();
    }

    @Override
    protected void initView() {

        setTitle(App.INSTANCE.getString(R.string.loginpwdForgetActivity1));
        EditUtil.togglePs(etPwd, ivPwdToggle);
        EditUtil.togglePs(etPwdAgain, ivPwdToggleAgain);
        lazyLoaders = new LazyLoaders(this);

        inputs = new ToastNextInputs();
        inputs.clear();
        inputs
                .add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etCode, SchemeUtil.notEmpty(etCode), SchemeUtil.smsCode())
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd())
                .add(etPwdAgain, SchemeUtil.notEmpty(etPwdAgain), SchemeUtil.equalsPwd(lazyLoaders.fromEditText(etPwd)))
        ;
        inputsCheckCode = new ToastNextInputs();
        inputsCheckCode.clear();
        inputsCheckCode
                .add(etAccount, SchemeUtil.notEmpty(etAccount));


        ClickUtil.click(tvGetCheckCode, () -> {
            if (inputsCheckCode.test()) {
                registerCheck();
            }
        });
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                mPresenter.resetLoginPwd(areaCodeEnumChoose.getAreaCode(),
                        etAccount.getText().toString(),
                        etPwd.getText().toString(),
                        etCode.getText().toString()
                );
            }
        });
        heySpinner.setVisibility(Constans.isAreaCode ? View.VISIBLE : View.GONE);

       /* SendCodeUtil.areaCode(this,heySpinner, areaCodeEnum -> {
            areaCodeEnumChoose = areaCodeEnum;
        });*/

    }
    public void sendCheckCodeSuccess() {
        tvGetCheckCode.setEnabled(false);
        DisposUtil.close(timeDis);
        timeDis = SendCodeUtil.timeCount(this, tvGetCheckCode);

    }

    @Override
    protected void initData() {
       // super.initData();
        OkGo.<HttpData<List<AreaCodeEnum>>>post(BaseHttpConfig.BASE_URL + "/app/user/getAreaCode")
                .execute(new JsonCallBack<HttpData<List<AreaCodeEnum>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<AreaCodeEnum>>> response) {
                        HttpData<List<AreaCodeEnum>> httpData = response.body();

                        SPUtil.putBean(SPConfig.AREA_CODE_LIST, (ArrayList) httpData.getData());
                        SendCodeUtil.areaCode(PwdForgetActivity.this, heySpinner, areaCodeEnum -> areaCodeEnumChoose = areaCodeEnum);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        DisposUtil.close(timeDis);
        super.onDestroy();
    }


    public void resetPwdSuccess() {
        finish();
    }


    public void registerCheck() {
        CaptchaUtil.check(this, (result, validate, msg) -> mPresenter.sendCode(validate, etAccount.getText().toString(),areaCodeEnumChoose.getAreaCode()));
    }
}
