package com.sskj.hangqing.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.EmailBindActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.SchemeUtil;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.EMAILBINDACTIVITY)
public class EmailBindActivity extends BaseActivity<EmailBindActivityPresenter> {
    @BindView(R2.id.ivBack)
    ImageView ivBack;
    @BindView(R2.id.et_phone)
    EditText et_phone;
    @BindView(R2.id.tv_denglu)
    TextView tvDenglu;
    @BindView(R2.id.btLogin)
    Button bt;
    @Autowired
    boolean isTrad;
    private AndroidNextInputs inputs;
    @Inject
    UserViewModel userViewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_bind_email;
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        //super.initView();
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_phone, SchemeUtil.notEmpty(et_phone));
        if(isTrad){
            tvDenglu.setText(App.INSTANCE.getString(R.string.hang_update_email));
        }else {
            tvDenglu.setText(R.string.hang_email_renzheng/*App.INSTANCE.getString(R.string.e)*/);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(ivBack, this::finish);
        ClickUtil.click(bt,()->{
            if(inputs.test()){
                BottomSheetUtil.showYanZhengMa(EmailBindActivity.this, et_phone.getText().toString(), new BottomSheetUtil.NewOnSure() {
                    @Override
                    public void onSure(String code) {
                        if(code!=null){
                            if(isTrad){
                                mPresenter.setChangeEmail(et_phone.getText().toString(),code);
                            }else
                                mPresenter.setEmail(et_phone.getText().toString(),code);
                            //mPresenter.updateUserPwd(et_newPwd.getText().toString(),code);
                            // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                        }
                    }
                }, new BottomSheetUtil.GetTime() {
                    @Override
                    public void getTime() {
                        if(isTrad){
                            mPresenter.getChangeEmailCode(et_phone.getText().toString());
                        }else {
                            mPresenter.getEmailCode(et_phone.getText().toString());
                        }

                    }
                });
            }

        });
    }

    @Override
    public EmailBindActivityPresenter getPresenter() {
        return new EmailBindActivityPresenter();
    }

    public void setUI(String message) {
        ToastUtil.showShort(message);
        userViewModel.update();
        SPUtil.put(SPConfig.USER_ACCOUNT,et_phone.getText().toString());
        LiveDataBus.get().with(RxBusCode.REFRESHSAFETY,Integer.class).postValue(1);
        finish();
    }
}
