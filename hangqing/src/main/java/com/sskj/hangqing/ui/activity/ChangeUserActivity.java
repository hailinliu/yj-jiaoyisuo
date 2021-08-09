package com.sskj.hangqing.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.ChangeUserActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.EncodeUtils;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.sskj.login.ui.activity.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.CHANGEUSERACTIVITY)
public class ChangeUserActivity extends BaseActivity<ChangeUserActivityPresenter> {
    @BindView(R2.id.et_newmima)
    EditText et_newPwd;
    @BindView(R2.id.et_queren)
    EditText et_queren;
    @BindView(R2.id.etPwd)
    EditText et_pwd;
    @BindView(R2.id.btLogin)
    Button button;
    @Autowired
    String phone;
    @Autowired
    String email1;
    private AndroidNextInputs inputs;
    private String email;
    private String text;
    @Inject
    UserViewModel userViewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_update_pwd;
    }

    @Override
    public ChangeUserActivityPresenter getPresenter() {
        return new ChangeUserActivityPresenter();
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_newPwd, SchemeUtil.notEmpty(et_newPwd));
        inputs.add(et_queren, SchemeUtil.notEmpty(et_queren));
        inputs.add(et_pwd, SchemeUtil.notEmpty(et_pwd));
    }

    @Override
    protected void initEvent() {
      //  super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!et_queren.getText().toString().equals(et_newPwd.getText().toString())){
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_pwd_not));
                    return;
                }
                //super.initData();
                if(inputs.test()){
                    if(!TextUtils.isEmpty(email1)){
                        text = email1;
                    }else {
                        text = phone;
                    }
                    BottomSheetUtil.showYanZhengMa(ChangeUserActivity.this, text, new BottomSheetUtil.NewOnSure() {
                        @Override
                        public void onSure(String code) {
                            if(!text.isEmpty()&&!code.isEmpty()){
                                if(EncodeUtils.encodeAES(code).equals(key)) {
                                    mPresenter.updateUserPwd(et_newPwd.getText().toString(), code);
                                }else {
                                    ToastUtil.showShort(App.INSTANCE.getString(com.sskj.login.R.string.login_cuowu));
                                }   // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                            }
                        }
                    }, new BottomSheetUtil.GetTime() {
                        @Override
                        public void getTime() {
                            if(text.contains("@")){
                                mPresenter.updataUseerPwdCodeemail(text);
                            }else
                            mPresenter.updataUseerPwdCode(text);
                            //  mPresenter.updateUserPwd();
                        }
                    });

                }
            }
        });
    }

    @Override
    protected void initData() {
        email = SPUtil.get(SPConfig.USER_ACCOUNT,"");


    }

    public void updateUI(String message) {
        ToastUtil.showShort(message);
        finish();
    }
    String key;
    public void setdata(String message) {
        key = message;
        String d = EncodeUtils.decryptAES(message).trim();
    }
}
