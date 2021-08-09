package com.sskj.login.ui.activity;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.AppManager;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.CaptchaUtil;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.lib.bean.LoginBean;
import com.sskj.login.component.DaggerUserDataComponent;
import com.sskj.login.presenter.LoginActivityPresenter;
import com.sskj.login.util.TipLoginUtil;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 登录
 */
@Route(path = RConfig.LOGIN_LOGIN)
public class LoginActivity extends BaseActivity<LoginActivityPresenter> {
    @BindView(R2.id.tv_wangj)
    TextView tvWangJ;
    @BindView(R2.id.tv_zhuce)
    TextView tvZhuce;
    @BindView(R2.id.etAccount)
    EditText etAccount;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.btLogin)
    Button button;
    @BindView(R2.id.llback)
    LinearLayout ivBack;
    @BindView(R2.id.image)
    ImageView image;
    boolean isEye;
   /* @BindView(R2.id.tvRegister)
    Button tvRegister;
    @BindView(R2.id.etAccount)
    EditText etAccount;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.btLogin)
    Button btLogin;
    @BindView(R2.id.tvPwdForget)
    TextView tvPwdForget;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
  *//*  @BindView(R2.id.image_diqiu)
    ImageView image_diqiu;*//*
    @BindView(R2.id.image_account)
    ImageView image_account;
    @BindView(R2.id.image_pwd)
    ImageView image_pwd;
    private long oldTime = 0;

    private AreaCodeEnum areaCodeEnumChoose;*/
   /* @BindView(R2.id.heySpinner)
    HeySpinner heySpinner;*/
 /*  @BindView(R2.id.ivBack)
   ImageView ivBack;*/
   private AndroidNextInputs inputs;
   @Inject
   UserViewModel userViewModel;
   private MaterialDialog googleCheckDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    public boolean getImmersion() {
        return false;
    }

    @Override
    public LoginActivityPresenter getPresenter() {
        return new LoginActivityPresenter();
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
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
        ClickUtil.click(tvWangJ,()->{
            ARouter.getInstance().build(RConfig.LOGIN_PWD_FORGET).navigation();
        });
      /*  ClickUtil.click(ivBack, ()->{
            ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
            finish();
        });*/
    /*  ivBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
              finish();
          }
      });*/
        ClickUtil.click(tvZhuce,()->{
            ARouter.getInstance().build(RConfig.LOGIN_REGISTER).navigation();
        });
        ClickUtil.click(button,()->{
            if(inputs.test()){
                mPresenter.login(etPwd.getText().toString(),etAccount.getText().toString());
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishCuActivity("InviteActivity");
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        RxBus.getDefault().register(this);
        etAccount.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_email),14,true));
        etPwd.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_pwd),14,true));
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd());
        DaggerUserDataComponent.create().inject(this);
       /*
        LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class).postValue(0);
        image_account.setVisibility(View.VISIBLE);
       // image_diqiu.setVisibility(View.VISIBLE);
        image_pwd.setVisibility(View.VISIBLE);
        ClickUtil.click(tvRegister, () -> {
            ARouter.getInstance().build(RConfig.LOGIN_REGISTER).navigation();
        });
        inputs = new ToastNextInputs();

        inputs.clear();
        inputs.add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd());


        ClickUtil.click(btLogin, () -> {
            if (inputs.test()) {
                mPresenter.login("", etAccount.getText().toString(), etPwd.getText().toString(), "APP");
            }
        });

        EditUtil.togglePs(etPwd, ivPwdToggle);

        ClickUtil.click(tvPwdForget, () -> {
            ARouter.getInstance().build(RConfig.LOGIN_PWD_FORGET).navigation();
        });

        LiveDataBus.get().with(RxBusCode.REGISTER_ACCOUNT, String.class)
                .observe(this, account -> {
                    if (etAccount != null && account != null) {
//                        etAccount.setText(account);
                    }
                });*/
      /*  heySpinner.setVisibility(Constans.isAreaCode ? View.VISIBLE : View.GONE);
        SendCodeUtil.areaCode(this, heySpinner, areaCodeEnum -> {
            areaCodeEnumChoose = areaCodeEnum;
        });*/

    }

    public void loginSuccess(LoginBean data) {
        saveDataAndLogin(data);
        if (data.getGoogleState() != 0) {
            googleCheck(data);
           // mPresenter.googleCheck(httpData.getData());
        }else {
            ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
            finish();
            overridePendingTransition(R.anim.lib_anim_out, R.anim.lib_anim_back);
        }
    /*    RxBus.getDefault().send(18878);
        ToastUtil.showShort(App.INSTANCE.getString(R.string.login_state_success));
        SPUtil.put("invitationCode",data.getInvitationCode());

        mPresenter.sendLoginSms(etAccount.getText().toString(), data.getId(), data.getToken());*/
    }

    public void saveDataAndLogin(LoginBean data) {
       // Object bean =  SPUtil.getBean(SPConfig.LOGIN_BEAN);
        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
      //  HttpParams httpParams = OkGo.getInstance().getCommonParams();
       String lan= CommonUtil.getHeaderLanguage(CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString()));
        httpHeaders.put(SPConfig.LANGUAGE, lan);
        httpHeaders.put(SPConfig.TOKEN, data.getToken());
        SPUtil.put(SPConfig.LOGIN_BEAN,GSonUtil.GsonString(data));
        SPUtil.put(SPConfig.ID, data.getId()+"");
        SPUtil.put(SPConfig.USER_ACCOUNT, data.getEmail()==null?"":data.getEmail());
        SPUtil.put(SPConfig.TOKEN, data.getToken());
        SPUtil.put(SPConfig.MOBILE, data.getMobile()==null?"":data.getMobile());
        SPUtil.put(SPConfig.GOOGLETYPE, data.getGoogleState()+"");
        SPUtil.put(SPConfig.LOGIN, true);
       // SPUtil.put(SPConfig.AREA_CODE, areaCodeEnumChoose.getAreaCode());
       // SPUtil.put(SPConfig.SIGN,data.getSign());
        SPUtil.put(SPConfig.ISLOGIN,true);
     //  data = (LoginBean) SPUtil.getBean(SPConfig.LOGINBEAN);
        userViewModel.update();
       // LiveDataBus.get().with(RxBusCode.ISLOGIN,LoginBean.class).postValue(data);
        //RxBus.getDefault().send(13579,data);
    }

    @Override
    public void onBackPressed() {
     /*   long nowTime = new Date().getTime();
        if (nowTime - oldTime <= 2000) {
            AppManager.getAppManager().AppExit(this);
        } else {
            oldTime = nowTime;
            ToastUtil.showShort(App.INSTANCE.getString(com.sskj.lib.R.string.libbaseActivity1));
        }
      //  super.onBackPressed();
        overridePendingTransition(R.anim.lib_anim_out, R.anim.lib_anim_back);*/

    }

    public void googleCheck(LoginBean loginBean) {
        CaptchaUtil.check(this, (result, validate, msg) -> {
            AndroidSchedulers.mainThread().scheduleDirect(() -> {
                googleCheckDialog = TipLoginUtil.showGoogleCheckDialog(LoginActivity.this, pwd -> {
                    googleCheckDialog.dismiss();
                    mPresenter.checkGoogle(pwd);
                });
                googleCheckDialog.show();
            });
        });
    }

    public void checkSuccess() {
        LiveDataBus.get().with(RxBusCode.LOGIN_MAIN,Integer.class).postValue(1);
        ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
    /*    saveDataAndLogin(data);
        mPresenter.sendLoginSms(etAccount.getText().toString(), data.getId(), data.getToken());*/
    }
}
