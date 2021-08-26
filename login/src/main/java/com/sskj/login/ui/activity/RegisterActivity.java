package com.sskj.login.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;
import com.github.yoojia.inputs.LazyLoaders;
import com.hey.lib.HeySpinner;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.box.glide.OkHttpUrlLoader;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.AreaCodeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CaptchaUtil;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.EditUtil;
import com.sskj.lib.util.EncodeUtils;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.util.SendCodeUtil;
import com.sskj.lib.util.TipUtil;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.bean.rxbus.AddressBean;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.presenter.RegisterActivityPresenter;
import com.sskj.login.util.CookiesManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;

/**
 * 注册
 */
@Route(path = RConfig.LOGIN_REGISTER)
public class RegisterActivity extends BaseActivity<RegisterActivityPresenter> {
  @BindView(R2.id.tv_denglu)
  TextView tvDenglu;
  @BindView(R2.id.tv_findyouxiang)
  TextView tv_youxiang;
  @BindView(R2.id.tv_findphone)
  TextView tv_phone;
  @BindView(R2.id.tv_1)
  Spinner tv_1;
  @BindView(R2.id.tv_zhucexieyi)
  TextView tvxieyi;
  @BindView(R2.id.checkbox)
  CheckBox checkbox;
  @BindView(R2.id.et_account)
  EditText etAccount;
  @BindView(R2.id.bt_next)
  Button bt_next;
  private ToastNextInputs inputs;
  private boolean isEmail =true;
  /*  @BindView(R2.id.etAccount)
    EditText etAccount;
    @BindView(R2.id.etCheckCode)
    EditText etCheckCode;
    @BindView(R2.id.tvCheckCode)
    TextView tvCheckCode;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.ivPwdToggle)
    ImageView ivPwdToggle;
    @BindView(R2.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R2.id.ivPwdToggleAgain)
    ImageView ivPwdToggleAgain;
    @BindView(R2.id.etInviteCode)
    EditText etInviteCode;
    @BindView(R2.id.checkbox)
    CheckBox checkbox;
    @BindView(R2.id.tvAgreement)
    TextView tvAgreement;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
   *//* @BindView(R2.id.etInputCode)
    EditText etInputCode;*//*
  *//*  @BindView(R2.id.imageCode)
    ImageView imageCode;*//*
    private Disposable timeDis;
    private String phoneNumber, messageCode, loginPwd, visitCode;
    ToastNextInputs inputs;

    private AreaCodeEnum areaCodeEnumChoose;
    @BindView(R2.id.heySpinner)
    HeySpinner heySpinner;
    private OkHttpClient mOkHttpClient;
    String uuid = UUID.randomUUID().toString().replaceAll("-","");*/
    @Override
    public int getLayoutId() {
        return R.layout.login_activity_register;
    }

    @Override
    public RegisterActivityPresenter getPresenter() {
        return new RegisterActivityPresenter();
    }

  @Override
  protected void initEvent() {
    //super.initEvent();
    ClickUtil.click(tvDenglu,()->{
      ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
    });
    ClickUtil.click(bt_next,()->{
      if(inputs.test()){
        if(checkbox.isChecked()){
          mPresenter.isRepetition(etAccount.getText().toString());

        }
      }
    });
    ClickUtil.click(tvxieyi,()->{
      ARouter.getInstance().build(RConfig.AGREEMENTACTIVITY).withInt("isflag",1).navigation();
    });
    ClickUtil.click(tv_youxiang,()->{
      isEmail = true;
      tv_youxiang.setTextColor(getResources().getColor(R.color.libGreen1));
      tv_phone.setTextColor(getResources().getColor(R.color.libTextGray));
      etAccount.setInputType(InputType.TYPE_CLASS_TEXT);
      etAccount.setText("");
      etAccount.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_email_address),16,true));
      inputs.add(etAccount, SchemeUtil.notEmpty(etAccount));
      tv_1.setVisibility(View.GONE);
    });
    ClickUtil.click(tv_phone,()->{
      isEmail = false;
      tv_phone.setTextColor(getResources().getColor(R.color.libGreen1));
      etAccount.setInputType(InputType.TYPE_CLASS_NUMBER);
      tv_youxiang.setTextColor(getResources().getColor(R.color.libTextGray));
      etAccount.setText("");
      etAccount.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_phone_code),16,true));
      tv_1.setVisibility(View.VISIBLE);
      inputs.add(etAccount, SchemeUtil.notEmpty(etAccount));
    });
  }

  @Override
    public boolean getImmersion() {
        return false;
    }

    @Override
    protected void initData() {
      inputs = new ToastNextInputs();
      inputs.clear();
      inputs.add(etAccount, SchemeUtil.notEmpty(etAccount));
      mPresenter.getArray();
      //  super.initData();
       /* OkGo.<HttpData<List<AreaCodeEnum>>>post(BaseHttpConfig.BASE_URL + "/app/user/getAreaCode")
                .execute(new JsonCallBack<HttpData<List<AreaCodeEnum>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<AreaCodeEnum>>> response) {
                        HttpData<List<AreaCodeEnum>> httpData = response.body();

                        SPUtil.putBean(SPConfig.AREA_CODE_LIST, (ArrayList) httpData.getData());
                        SendCodeUtil.areaCode(RegisterActivity.this, heySpinner, areaCodeEnum -> areaCodeEnumChoose = areaCodeEnum);
                    }
                });*/
    }

  public void isSuccess(String msg) {

    if(msg.contains("成功")||msg.contains("success")){
      MaterialDialog dialog = new RudessMaterialDialog.Builder(this)
              .customView(com.sskj.lib.R.layout.lib_dialog_slide, false)
              .autoDismiss(false)
              .cancelable(false)
              .show();
      View customView = dialog.getCustomView();
      SeekBar seekBar = customView.findViewById(com.sskj.lib.R.id.sb);
      TextView tv = customView.findViewById(com.sskj.lib.R.id.tv);
      final String[] coun = new String[1];
      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          if (seekBar.getProgress() == seekBar.getMax()) {
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.WHITE);
            tv.setText("完成验证");
            dialog.dismiss();
            BottomSheetUtil.showYanZhengMap(RegisterActivity.this, etAccount.getText().toString(), isEmail?App.INSTANCE.getString(R.string.login_youxiang):App.INSTANCE.getString(R.string.lib_phone_yanzheng),new BottomSheetUtil.NewOnSure() {
              @Override
              public void onSure(String code) {
                if(!etAccount.getText().toString().isEmpty()&&!code.isEmpty()){
                    if(EncodeUtils.encodeAES(code).equals(nomsg)){
                        ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("country",coun[0]).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                    }else {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.login_cuowu));
                    }

                }
              }
            }, new BottomSheetUtil.GetTime() {
              @Override
              public void getTime() {
                if(isEmail){
                mPresenter.getEmailCode(etAccount.getText().toString());
                }else{
                  String key= tv_1.getSelectedItem().toString();
                   coun[0] = map.get(key);
                  mPresenter.getPhoneCode(coun[0],etAccount.getText().toString());
                }

              }
            });
          } else {
            tv.setVisibility(View.INVISIBLE);
          }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
          if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.GRAY);
            tv.setText("向右滑动验证");
          }
        }
      });

    }else {
      //ToastUtil.showShort("已被注册!");
      ToastUtil.showShort(msg);
    }

  }
String nomsg;
  public void getPhoneSuccess(String message) {
    nomsg = message;
  }
  private List<String> list = new ArrayList<>();
  private Map<String, String> map=new HashMap<>();
  public void setData(AddressBean bean) {
    list.clear();
    for(AddressBean.DataBean data:bean.getData()){
      list.add(data.getAreaCode());
      map.put(data.getAreaCode(),data.getZhName());
    }
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.lib_list_item, list.toArray(new String[list.size()]));
    tv_1.setAdapter(adapter);
  }

/*    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        inputs = new ToastNextInputs();
        inputsCode = new ToastNextInputs();
        inputs.clear();
        inputsCode.clear();
        inputsCode.add(etAccount, SchemeUtil.notEmpty(etAccount));
        inputs.add(etAccount, SchemeUtil.notEmpty(etAccount))
                .add(etCheckCode, SchemeUtil.notEmpty(etCheckCode), SchemeUtil.smsCode())
                .add(etPwd, SchemeUtil.notEmpty(etPwd), SchemeUtil.pwd())
                .add(etPwdAgain, SchemeUtil.notEmpty(etPwdAgain), SchemeUtil.equalsPwd(LazyLoaders.fromEditText(etPwd)))
                ;
        heySpinner.setVisibility(Constans.isAreaCode ? View.VISIBLE : View.GONE);


//初始化okhttp，
       *//* mOkHttpClient = new OkHttpClient.Builder()
                   .cookieJar(new CookiesManager())   //cookie管理
                   .build();

        //不Register的话，图片请求不会经过OkHttpClient
        Glide.get(RegisterActivity.this).getRegistry().replace( GlideUrl.class
                , InputStream.class
                ,new OkHttpUrlLoader.Factory(mOkHttpClient))*//**//*.getRegistry()
                .register(
                        GlideUrl.class
                        , InputStream.class
                        ,new OkHttpUrlLoader.Factory(mOkHttpClient))*//**//*;
        Glide.with(RegisterActivity.this)
                .load(HttpConfig.BASE_URL+HttpConfig.GET_CODE)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                .into(imageCode);*//*

        //ImageUtil.setImage(HttpConfig.BASE_URL+HttpConfig.GET_CODE+"?vcKey="+uuid,imageCode,true);
    }*/

   /* @Override
    protected void initEvent() {

        ClickUtil.click(tvCheckCode, () -> {
            if (inputsCode.test()) {
                registerCheck();
            }
        });
        ClickUtil.click(200, tvAgreement, () -> {
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB)
                    .withBoolean(Constans.IS_REGISTER_AGREEMENT, true)
                    .navigation();
        });
        *//*ClickUtil.click(imageCode,()->{
            *//**//*Glide.with(RegisterActivity.this)
                    .load(HttpConfig.BASE_URL+HttpConfig.GET_CODE)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                    .into(imageCode);*//**//*
            //Math.random()*1000;
            ImageUtil.setImage(HttpConfig.BASE_URL+HttpConfig.GET_CODE+"?vcKey="+uuid,imageCode,true);
        });*//*
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                phoneNumber = getText(etAccount);
                messageCode = getText(etCheckCode);
                loginPwd = getText(etPwd);
                visitCode = getText(etInviteCode);
                mPresenter.register(phoneNumber, loginPwd, visitCode, messageCode, areaCodeEnumChoose.getAreaCode());
            }
        });

        EditUtil.togglePs(etPwd, ivPwdToggle);
        EditUtil.togglePs(etPwdAgain, ivPwdToggleAgain);
    }*/

  /*  public void sendCheckCodeSuccess() {
        ToastUtil.showShort(App.INSTANCE.getString(R.string.login_get_code_success));
        tvCheckCode.setEnabled(false);
        DisposUtil.close(timeDis);
        timeDis = SendCodeUtil.timeCount(this, tvCheckCode);
    }*/


  /*  public void registerSuccess() {
        ToastUtil.showShort(App.INSTANCE.getString(R.string.login_register_state_success));
        LiveDataBus.get().with(RxBusCode.REGISTER_ACCOUNT, String.class)
                .postValue(etAccount.getText().toString());
        finish();
    }*/

   /* public void registerCheck() {

        CaptchaUtil.check(this, (result, validate, msg) -> {
            SPUtil.put("vcKey",uuid);
            mPresenter.sendCode("", etAccount.getText().toString(), areaCodeEnumChoose.getAreaCode(),uuid);
        });
    }*/

}
