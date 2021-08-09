package com.sskj.mine.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.HideUtil;
import com.sskj.lib.util.TipUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.presenter.SecurityActivityPresenter;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 安全中心
 */
@Route(path = RConfig.MINE_SECURITY_CENTER)
public class SecurityActivity extends BaseActivity<SecurityActivityPresenter> {
    @BindView(R2.id.menu_bind_mobile)
    SuperTextView menuBindMobile;
    @BindView(R2.id.menu_chrome)
    SuperTextView menuChrome;
    @BindView(R2.id.menu_login_password)
    SuperTextView menuLoginPassword;
    @BindView(R2.id.menu_pay_password)
    SuperTextView menuPayPassword;
    @BindView(R2.id.security_img)
    ImageView securityImg;
    @BindView(R2.id.menu_seller)
    SuperTextView menuSeller;
    @BindView(R2.id.menu_bind_email)
    SuperTextView menu_bind_email;
    @BindView(R2.id.checkedTextView)
    CheckedTextView checkedTextView;
    @BindView(R2.id.iv_chrome)
    ImageView iv_chrome;
    @BindView(R2.id.frameLayoutPayType)
    FrameLayout frameLayoutPayType;
    BottomSheetDialog googleDialog;
    private SafeSettingBean userData;
    @Inject
    UserViewModel userViewModel;
    private Boolean isFingerOn;


    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_security;
    }

    @Override
    public SecurityActivityPresenter getPresenter() {
        return new SecurityActivityPresenter();
    }

    @Override
    public void initView() {
        setTitle(App.INSTANCE.getString(R.string.minesecurityActivity1));
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                updateStatus(userData);
            } else {
                userData = null;
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,code=1456)
    public void getData(){
        iv_chrome.setImageDrawable(getResources().getDrawable(R.mipmap.icon_on));
//iv_chrome.setImageResource(getResources().get(R.mipmap.icon_on));
    }
    @Override
    public void initData() {


        //绑定手机号
        ClickUtil.click(menuBindMobile, () -> {
/*
            if (TextUtils.isEmpty(userData.getTel())) {
                if (!AppCircleCheckUtil.checkTradePwd(this, userData)) {
                    return;
                }
                BindMobileActivity.start(this, true, false);
            } else {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity2));
//                if (!AppCircleCheckUtil.checkTradePwd(this, userData)) {
//                    return;
//                }
//                BindMobileActivity.start(this, true, true);
            }*/
        });
        // 邮箱验证
        ClickUtil.click(menu_bind_email, () -> {

            if (TextUtils.isEmpty(userData.getEmail())) {
                if (!AppCircleCheckUtil.checkTradePwd(this, userData)) {
                    return;
                }
                BindMobileActivity.start(this, false, false);
            } else {

                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity123));
//                if (!AppCircleCheckUtil.checkTradePwd(this, userData)) {
//                    return;
//                }
//                BindMobileActivity.start(this, true, true);
            }
        });


        //登录密码
        ClickUtil.click(menuLoginPassword, () -> {
          /*  ARouter.getInstance().build(RConfig.MINE_SET_LOGIN_PWD)
                    .withString("mobile", userData.getTel()).navigation();*/
        });


        //资金密码
        ClickUtil.click(menuPayPassword, () -> {
         /*   ARouter.getInstance().build(RConfig.MINE_SET_FUND_PWD)
                    .withInt("type", userData.getTradePswdStatus()).navigation();*/
        });

        //商家认证
        ClickUtil.click(menuSeller, () -> {  // 1.未申请 2.已申请(待审核）3.同意 4.拒绝 5.已解绑 6.解绑申请中
            if (!AppCircleCheckUtil.checkAuth(this, userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkHighAuth(this, userData)) {
                return;
            }
          /*  if (userData.getShopAuthenticationStatus() == 1 || userData.getShopAuthenticationStatus() == 4 || userData.getShopAuthenticationStatus() == 5) {
                SellerApplyActivity.start(this);
                return;
            }

            if (userData.getShopAuthenticationStatus() == 2) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity3));
                return;
            }
            if (userData.getShopAuthenticationStatus() == 6) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity4));
                return;
            }*/
            TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.minesecurityActivity5),
                    App.INSTANCE.getString(R.string.minesecurityActivity6) + "<br/>" + App.INSTANCE.getString(R.string.minesecurityActivity7)
                    , App.INSTANCE.getString(R.string.mine_security_start),() -> {
                                //解除接口
                        mPresenter.cancelShop();

                    });
        });

        // 谷歌认证
        ClickUtil.click(menuChrome, () -> {
            if (userData != null) {
            /*    if (userData.getGoogleAuthenticationStatus() == 0) {
                    startActivity(new Intent(this, VerifyGoogleActivity.class));
                } else {
                    showGoogleDialog();
                }*/
            }
        });

        isFingerOn = SPUtil.get(SPConfig.IS_FINGER_ON, false);
        checkedTextView.setChecked(isFingerOn);
        //指纹开关
        ClickUtil.click(checkedTextView, () -> {
            isFingerOn = SPUtil.get(SPConfig.IS_FINGER_ON, false);
            if (!isFingerOn) {
                FingerprintIdentify mFingerprintIdentify = new FingerprintIdentify(this);
                if (!mFingerprintIdentify.isHardwareEnable()) {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity8));
                    return;
                }
                if (!mFingerprintIdentify.isRegisteredFingerprint()) {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.minesecurityActivity9));
                    return;
                }
            }
            checkedTextView.setChecked(!isFingerOn);
            SPUtil.put(SPConfig.IS_FINGER_ON, !isFingerOn);
        });

        userViewModel.update();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutPayType, (Fragment) ARouter.getInstance().build(RConfig.PAY_PAY_TYPE).navigation())
                .commit();

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SecurityActivity.class);
        context.startActivity(intent);
    }


    //
    public void updateStatus(SafeSettingBean user) {
        if (user == null) {
            return;
        }
   /*     if (!TextUtils.isEmpty(user.getTel())) {  // 手机号
            menuBindMobile.setRightString(HideUtil.getPhoneHide(user.getTel()));
        } else {
            menuBindMobile.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity10));
            menuBindMobile.setRightTextColor(getResources().getColor(R.color.mineApp1));
        }
        if (!TextUtils.isEmpty(user.getEmail())) {  // 邮箱
            menu_bind_email.setRightString(HideUtil.getPhoneHide(user.getEmail()));
        } else {
            menu_bind_email.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity10));
            menu_bind_email.setRightTextColor(getResources().getColor(R.color.mineApp1));
        }
        if (user.getTradePswdStatus() == 1) {  // 资金密码
            menuPayPassword.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity11));
            menuPayPassword.setRightTextColor(getResources().getColor(R.color.libGreen));
        } else {
            menuPayPassword.setRightTextColor(getResources().getColor(R.color.mineApp1));
            menuPayPassword.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity12));
        }

        if (user.getShopAuthenticationStatus() == 3) {  // 商家认证
            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity13));
            menuSeller.setRightTextColor(getResources().getColor(R.color.mineApp));
        } else if (user.getShopAuthenticationStatus() == 1) {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity14));
            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        } else if (user.getShopAuthenticationStatus() == 2) {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.mineassetFragment1));
            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        } else if (user.getShopAuthenticationStatus() == 4) {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.mineassetFragment3));
            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        } else if (user.getShopAuthenticationStatus() == 5) {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity17));
//            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity14));

            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        } else if (user.getShopAuthenticationStatus() == 6) {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity18));

            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        } else {
            menuSeller.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity19));
            menuSeller.setRightTextColor(getResources().getColor(R.color.mineTextHint));
        }

        if (user.getBasicAuthenticationStatus() == 0) {  // 0  未认证   1 已认证
          //  menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity20));
        } else if (user.getBasicAuthenticationStatus() == 2) {
          //  menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity21));
        } else if (user.getBasicAuthenticationStatus() == 1) {  // 初级已认证
            if (user.getHighAuthenticationStatus() == 1) {
            //    menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity22));
            } else if (user.getHighAuthenticationStatus() == 2) {
              //  menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity23));
            } else if (user.getHighAuthenticationStatus() == 3) {
               // menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity24));
               // menuVerify.setRightTextColor(getResources().getColor(R.color.mineApp));

            } else {
               // menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity25));
            }
        } else {
           // menuVerify.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity26));
        }
        if (user.getGoogleAuthenticationStatus() == 1) {  // 谷歌绑定
            menuChrome.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity27));
            menuChrome.setRightTextColor(getResources().getColor(R.color.libGreen));
        } else {
            menuChrome.setRightString(App.INSTANCE.getString(R.string.minesecurityActivity10));
            menuChrome.setRightTextColor(getResources().getColor(R.color.libRed));
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userViewModel != null) {
            userViewModel.update();
        }
    }

    public void cancelShopSuccess() {
        if (userViewModel != null) {
            userViewModel.update();
        }
    }


    /**
     * 显示手机验证码Dialog
     */
    private void showGoogleDialog() {
        googleDialog = BottomSheetUtil.showCheck(this, App.INSTANCE.getString(R.string.minesecurityActivity28), false, true, true, CodeTypeEnum.STATUS_5, (pwd, googleCode, smsCode) -> {
            mPresenter.untriedGoogleVerify(googleCode, smsCode);
        });
        googleDialog.show();
    }

    public void setGoogleVerifySuccess() {
        if (googleDialog != null) {
            googleDialog.dismiss();
        }
        ToastUtil.showShort(getString(R.string.mine_jiebang));
        iv_chrome.setImageDrawable(getResources().getDrawable(R.mipmap.icon_off));
        userViewModel.update();
    }
}
