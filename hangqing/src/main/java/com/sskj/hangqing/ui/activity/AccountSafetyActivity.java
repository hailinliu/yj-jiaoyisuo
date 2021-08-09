package com.sskj.hangqing.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.AccountSafetyActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.LoginBean;
import com.sskj.lib.bean.PayInfoBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.ACCOUNTSAFETYACTIVITY)
public class AccountSafetyActivity extends BaseActivity<AccountSafetyActivityPresenter> {
    @BindView(R2.id.tv_denglu)
    SuperTextView tv_denglu;
    @BindView(R2.id.tv_jiaoyi)
    SuperTextView tv_jiaoyi;
    @BindView(R2.id.tv_phonebind)
    SuperTextView tv_phonebind;
    @BindView(R2.id.tv_emailbind)
    SuperTextView tvEmailbind;
    @BindView(R2.id.tv_guge)
    SuperTextView tv_guge;
    @BindView(R2.id.tv_shenfen)
    SuperTextView tvShenfen;
    @BindView(R2.id.tv_pay)
    SuperTextView tv_pay;
    private int trad;
    private boolean isTrad;
    private String phone;
    private String realname;
    private int status;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private boolean isJiaoyi;
    private boolean isPhone;
    private boolean isEmail;
    private boolean isGoogle;
    private boolean isReal;
    private String email1;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_account_safety;
    }

    @Override
    public AccountSafetyActivityPresenter getPresenter() {
        return new AccountSafetyActivityPresenter();
    }

    @Override
    protected void initData() {
       // super.initData();
       /*  LoginBean bean = GSonUtil.GsonToBean(SPUtil.get(SPConfig.LOGIN_BEAN,""),LoginBean.class);
         if(bean!=null){
             email =  bean.getEmail();
             mPresenter.getSecuritySetting();

         }else {
          ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
         }*/
        mPresenter.getSecuritySetting();
        userViewModel.getUsers().observe(this,users->{
            if (users != null) {
                userData = users.get(0);
            }
        });
        mPresenter.getPayInfo();
        LiveDataBus.get().with(RxBusCode.REFRESHSAFETY,Integer.class).observe(this,this::refresh);
       // email = SPUtil.get(SPConfig.USER_ACCOUNT,"");
    }

    private void refresh(Integer integer) {
        mPresenter.getSecuritySetting();
    }

    @Override
    protected void initView() {
      //  super.initView();
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        setTitle(App.INSTANCE.getString(R.string.hang_account_safe));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initEvent() {
       // super.initEvent();
        ClickUtil.click(tv_denglu,()->{
            ARouter.getInstance().build(RConfig.CHANGEUSERACTIVITY).withString("phone",phone).withString("email1",email1).navigation();
        });
        ClickUtil.click(tv_jiaoyi,()->{
            ARouter.getInstance().build(RConfig.CHANGETRADACTIVITY).withBoolean("isTrad",isJiaoyi).navigation();
        });
        ClickUtil.click(tv_phonebind,()->{
            ARouter.getInstance().build(RConfig.PHONEBINDACTIVITY).withBoolean("isTrad",isPhone).navigation();

        });
        ClickUtil.click(tvEmailbind,()->{
            ARouter.getInstance().build(RConfig.EMAILBINDACTIVITY).withBoolean("isTrad",isEmail).navigation();

        });
        ClickUtil.click(tv_guge,()->{
            if(isGoogle){
                BottomSheetUtil.showYanZhengMa1(AccountSafetyActivity.this,App.INSTANCE.getString(R.string.hang_yanzheng),App.INSTANCE.getString(R.string.hang_yanzhengma), new BottomSheetUtil.NewOnSure() {
                    @Override
                    public void onSure(String code) {
                        if(code!=null){
                            mPresenter.closeGoogle(code);
                            //mPresenter.updateUserPwd(et_newPwd.getText().toString(),code);
                            // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                        }
                    }
                }, new BottomSheetUtil.GetTime() {
                    @Override
                    public void getTime() {
                        if(email1!=null){
                            mPresenter.closeGoogleEmailCode(email1);
                        }else {
                            mPresenter.closeGooglePhoneCode(phone);
                        }
                    }
                });
            }else
            ARouter.getInstance().build(RConfig.OPENGOOGLEACTIVITY).withBoolean("isTrad",isGoogle).navigation();

        });
        ClickUtil.click(tvShenfen,()->{
            if(isReal){
             ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_already));
            }else
            ARouter.getInstance().build(RConfig.IDENTIFYACTIVITY).navigation();

        });
        ClickUtil.click(tv_pay,()->{
           /* if(isTrad){
                ToastUtil.showShort("已经认证");
            }else*/
           if(realname!=null){
               ARouter.getInstance().build(RConfig.PAYSETTINGACTIVITY).withString("realname",realname).withInt("status",status).navigation();
           }else {
               ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_real_name));
           }


        });
    }

    public void updateUI(String message) {
        ToastUtil.showShort(message);
    }

    public void setUI(SafeSettingBean data) {
        //status = data.getFundsVerified();
        trad = data.getFundsVerified();
        phone = data.getMobilePhone();
        email1 = data.getEmail();
        realname = data.getRealName();
        if(trad==0){
          // tv_jiaoyi.setRightTextColor(getResources().getColor(R.color.libRed));
           tv_jiaoyi.setRightString(App.INSTANCE.getString(R.string.hang_set));
            isJiaoyi = false;

        }else {
            tv_jiaoyi.setRightTextColor(getResources().getColor(R.color.libTextWhite));
            tv_jiaoyi.setRightString(App.INSTANCE.getString(R.string.hang_hang_update));
            isJiaoyi = true;
        }
        if(data.getMobilePhone()!=null){
            isPhone = true;
            tv_phonebind.setRightString(App.INSTANCE.getString(R.string.hang_hang_update));
        }else {
            isPhone  =false;
            tv_phonebind.setRightString(App.INSTANCE.getString(R.string.hang_bind_phone));
        }
        if(data.getEmail()!=null){
            isEmail = true;
            tvEmailbind.setRightString(App.INSTANCE.getString(R.string.hang_hang_update));
        }else {
            isEmail  =false;
            tvEmailbind.setRightString(App.INSTANCE.getString(R.string.hang_bind_email));
        }
        if(data.getGoogleVerified()==0){
            tv_guge.setRightString(App.INSTANCE.getString(R.string.hang_unbind));
            isGoogle = false;
            tv_guge.setRightTextColor(getResources().getColor(R.color.libRed));
        }else {
            tv_guge.setRightString(App.INSTANCE.getString(R.string.hang_bind));
            isGoogle  =true;
            tv_guge.setRightTextColor(getResources().getColor(R.color.libRed));
        }
        if(data.getRealVerified()==0){
            tvShenfen.setRightString(App.INSTANCE.getString(R.string.hang_unready));
            isReal = false;
            tvShenfen.setRightTextColor(getResources().getColor(R.color.libRed));
        }else if(data.getRealVerified()==1){
            tvShenfen.setRightString(App.INSTANCE.getString(R.string.hang_shenhezhong));
            isReal  =false;
        }else if(data.getRealVerified()==3){
            tvShenfen.setRightString(App.INSTANCE.getString(R.string.hang_yi_jujue));
            isReal = false;
        }
        else {
            tvShenfen.setRightString(App.INSTANCE.getString(R.string.hang_yirenzheng));
            isReal  =true;
            tvShenfen.setRightTextColor(getResources().getColor(R.color.libRed));
        }

       /* if(data.get){

        }*/
    }

    public void setCloseUI(String message) {
        ToastUtil.showShort(message);
        mPresenter.getSecuritySetting();

    }

    public void setData(PayInfoBean data) {
        if(!TextUtils.isEmpty(data.getALIPAY())||!TextUtils.isEmpty(data.getBANK())||!TextUtils.isEmpty(data.getWECHAT())){
            status =1;
        }else {
           status=0;
        }
    }
}
