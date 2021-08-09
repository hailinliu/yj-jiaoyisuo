package com.sskj.login.ui.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.AppManager;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.presenter.SettingActivityPresenter;

import butterknife.BindView;

@Route(path = RConfig.SETTINGACTIVITY)
public class SettingActivity extends BaseActivity<SettingActivityPresenter> {
    @Autowired
    String account;
    @Autowired
    boolean isfund;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.et_pwd_again)
    EditText etPwdAgain;
    @BindView(R2.id.et_yaoqingma)
    EditText etYaoqing;
    @BindView(R2.id.video_view1)
    View view;
    @BindView(R2.id.ivBack)
    ImageView imageView;
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.btLogin)
    Button button;
    @Autowired
    String code;
    @Autowired
    String country;
    @Autowired
    boolean isEmail;
    boolean isEye;
    private AndroidNextInputs inputs;
    @Override
    protected int getLayoutId() {
        return R.layout.login_set_pwd;
    }

    @Override
    public SettingActivityPresenter getPresenter() {
        return new SettingActivityPresenter();
    }

    @Override
    protected void initEvent() {
        ClickUtil.click(image,()->{
            if(isEye){
                image.setImageResource(R.mipmap.login_zhengyan);
                etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                image.setImageResource(R.mipmap.login_biyan);
                etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            isEye = !isEye;
        });
       // super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfund){
                    if(isEmail){
                        mPresenter.fundEmailPwd(etPwd.getText().toString(),code,account);
                    }else {
                        mPresenter.fundPwd(etPwd.getText().toString(),code,account);
                    }
                }else {
                    if(inputs.test()){
                        if(etPwdAgain.getText().toString().equals(etPwd.getText().toString())){
                            if(isEmail){
                                mPresenter.emailRegister(code,account,etPwd.getText().toString(),etYaoqing.getText().toString());
                            }else {
                                mPresenter.register(country,etPwd.getText().toString(),etYaoqing.getText().toString(),code,account);
                            }
                        }else {
                         ToastUtil.showShort(App.INSTANCE.getString(R.string.login_shuru));
                        }

                    }

                }

            }
        });
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
        inputs = new ToastNextInputs();
        inputs.clear();

        etPwd.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_qingshuru),16,true));
        if(isfund){
            etPwdAgain.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            etYaoqing.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_shu_again),16,true));
            button.setText(R.string.login_sure);
        }else{
            inputs.add(etYaoqing, SchemeUtil.notEmpty(etYaoqing))
                    .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd());
            etPwdAgain.setVisibility(View.VISIBLE);
            etPwdAgain.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_shu_again),16,true));
            view.setVisibility(View.VISIBLE);
            button.setText(R.string.login_register);
            etYaoqing.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_yaoqingma),16,true));
        }

    }

    public void registerSuccess(String message) {
        if(message.contains("成功")||message.contains("success")){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.login_success));
            AppManager.getAppManager().finishActivity(RegisterActivity.class);
            finish();}
    }

    public void result(String msg) {
        if(msg.contains("成功")||msg.contains("success")){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.login_success));
            AppManager.getAppManager().finishActivity(FindPwdActivity.class);
            finish();
        }

    }
}
