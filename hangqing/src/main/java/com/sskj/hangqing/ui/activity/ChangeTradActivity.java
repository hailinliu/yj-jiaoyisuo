package com.sskj.hangqing.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.ChangeTradActivityPresenter;
import com.sskj.hangqing.presenter.ChangeUserActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.EncodeUtils;
import com.sskj.lib.util.SchemeUtil;

import butterknife.BindView;

@Route(path = RConfig.CHANGETRADACTIVITY)
public class ChangeTradActivity extends BaseActivity<ChangeTradActivityPresenter> {
    @BindView(R2.id.et_newmima)
    EditText et_newPwd;
    @BindView(R2.id.et_queren)
    EditText et_queren;
    @BindView(R2.id.btLogin)
    Button button;
    @Autowired
    boolean isTrad;
    private AndroidNextInputs inputs;
    private String email;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_update_trad_pwd;
    }

    @Override
    protected void initData() {
        //super.initData();
        email = SPUtil.get(SPConfig.USER_ACCOUNT,"");
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
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
                    BottomSheetUtil.showYanZhengMa(ChangeTradActivity.this, email, new BottomSheetUtil.NewOnSure() {
                        @Override
                        public void onSure(String code) {
                            if(!et_newPwd.getText().toString().isEmpty()&&!code.isEmpty()){
                                if(EncodeUtils.encodeAES(code).equals(key)) {
                                    if (isTrad) {
                                        mPresenter.updateTradPwd1(et_newPwd.getText().toString(), code);
                                    } else
                                        mPresenter.updateTradPwd(et_newPwd.getText().toString(), code);
                                }else {
                                    ToastUtil.showShort(App.INSTANCE.getString(com.sskj.login.R.string.login_cuowu));
                                }
                                // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                            }
                        }
                    }, new BottomSheetUtil.GetTime() {
                        @Override
                        public void getTime() {
                            if(email.contains("@")){
                                mPresenter.updataUseerPwdCodeemail(email);
                            }else
                                mPresenter.updataUseerPwdCode(email);
                           // mPresenter.updataUseerPwdCode(email);
                            //  mPresenter.updateUserPwd();
                        }
                    });

                }
            }
        });
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_newPwd, SchemeUtil.notEmpty(et_newPwd));
        inputs.add(et_queren, SchemeUtil.notEmpty(et_queren));
        //inputs.add(et_pwd, SchemeUtil.notEmpty(et_pwd));
       // super.initView();
    }

    @Override
    public ChangeTradActivityPresenter getPresenter() {
        return new ChangeTradActivityPresenter();
    }
        String key;
    public void updateUI(String message) {
        key = message;
        //ToastUtil.showShort(message);
       // finish();
    }

    public void setSuccess(String message) {
        ToastUtil.showShort(message);
        finish();
    }
}
