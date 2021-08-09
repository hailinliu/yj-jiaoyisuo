package com.sskj.mine.component;

import com.sskj.lib.dagger.module.UserModule;
import com.sskj.mine.ui.activity.MyAssertsActivity;
import com.sskj.mine.ui.activity.PersonActivity;
import com.sskj.mine.ui.activity.SecurityActivity;
import com.sskj.mine.ui.activity.SettingActivity;
import com.sskj.mine.ui.activity.SettingFundPwdActivity;
import com.sskj.mine.ui.activity.VerifyGoogleActivity;
import com.sskj.mine.ui.fragment.MineFragment;

import dagger.Component;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-05 09:01
 */
@Component(modules = {UserModule.class})
public interface UserDataComponent {

    void inject(SecurityActivity securityActivity);

    void inject(MyAssertsActivity myAssertsActivity);
    void inject(SettingActivity settingActivity);

    void inject(MineFragment mineFragment);

    void inject(VerifyGoogleActivity verifyGoogleActivity);
    void inject(SettingFundPwdActivity settingFundPwdActivity);
    void inject(PersonActivity personActivity);


}
