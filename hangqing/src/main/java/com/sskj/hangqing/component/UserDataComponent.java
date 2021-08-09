package com.sskj.hangqing.component;

import com.sskj.hangqing.ui.activity.AccountSafetyActivity;
import com.sskj.hangqing.ui.activity.EmailBindActivity;
import com.sskj.hangqing.ui.activity.IdentifyFourActivity;
import com.sskj.hangqing.ui.activity.NewHangActivity;
import com.sskj.hangqing.ui.activity.NewSettingActivity;
import com.sskj.hangqing.ui.activity.NewSlideActivity;
import com.sskj.hangqing.ui.activity.PhoneBindActivity;
import com.sskj.hangqing.ui.fragment.BankFragment;
import com.sskj.hangqing.ui.fragment.CoinListFragment;
import com.sskj.hangqing.ui.fragment.HangqingFragment;
import com.sskj.hangqing.ui.fragment.NewHangFragment;
import com.sskj.hangqing.ui.fragment.PayAdFragment;
import com.sskj.lib.dagger.module.UserModule;

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

    void inject(HangqingFragment hangqingFragment);
    void inject(NewHangFragment newhangqingFragment);
    void inject(CoinListFragment coinListFragment);
    void inject(NewHangActivity newHangActivity);
    void inject(NewSlideActivity newSlideActivity);
    void inject(NewSettingActivity newSettingActivity);
    void inject(AccountSafetyActivity accountSafetyActivity);
    void inject(IdentifyFourActivity identifyFourActivity);
    void inject(PhoneBindActivity phoneBindActivity);
    void inject(EmailBindActivity emailBindActivity);

}
