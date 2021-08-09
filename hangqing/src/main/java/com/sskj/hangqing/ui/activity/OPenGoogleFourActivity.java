package com.sskj.hangqing.ui.activity;

import android.app.Activity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.IdentifyActivityPresenter;
import com.sskj.hangqing.presenter.OPenGoogleFourActivityPresenter;
import com.sskj.hangqing.presenter.OpenGoogleActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.AppManager;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.ui.view.PhoneCode;

import java.util.Stack;

import butterknife.BindView;

@Route(path = RConfig.OPENGOOGLEFOURACTIVITY)
public class OPenGoogleFourActivity extends BaseActivity<OPenGoogleFourActivityPresenter> {
    @BindView(R2.id.phonecode)
    PhoneCode code;
    @BindView(R2.id.logout_btn)
    Button logout_btn;
    @BindView(R2.id.logout_btn1)
    Button logout_btn1;
    @BindView(R2.id.et_miyao)
    EditText et_miyao;
    @BindView(R2.id.imageeye)
    ImageView image;
    boolean isEye;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_google_four;
    }

    @Override
    public OPenGoogleFourActivityPresenter getPresenter() {
        return new OPenGoogleFourActivityPresenter();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        code.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {

            }

            @Override
            public void onInput() {

            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.bindgoogle(code.getPhoneCode(),et_miyao.getText().toString());
                //ARouter.getInstance().build(RConfig.OPENGOOGLEFOURACTIVITY).navigation();
            }
        });
        logout_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ClickUtil.click(image,()->{
            if(isEye){
                image.setImageResource(com.sskj.login.R.mipmap.login_zhengyan);
                et_miyao.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                image.setImageResource(com.sskj.login.R.mipmap.login_biyan);
                et_miyao.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            isEye = !isEye;
        });
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
    }

    public void setUi(String message) {
        ToastUtil.showShort(message);
      Stack<Activity> stack= AppManager.getAppManager().getAllActivities();
      stack.size();
        AppManager.getAppManager().finishActivity(OpenGoogleThirdActivity.class);
        AppManager.getAppManager().finishActivity(OpenGoogleTwoActivity.class);
        AppManager.getAppManager().finishActivity(OpenGoogleActivity.class);
        LiveDataBus.get().with(RxBusCode.REFRESHSAFETY,Integer.class).postValue(1);
        finish();
    }
}
