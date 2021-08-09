package com.sskj.ident.component;


import com.sskj.ident.ui.activity.VerificationFirstActivity;
import com.sskj.ident.ui.activity.VerificationMenuActivity;
import com.sskj.ident.ui.activity.VerificationResultActivity;
import com.sskj.ident.ui.activity.VerificationSecondActivity;
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
void inject(VerificationFirstActivity verificationFirstActivity);
void inject(VerificationSecondActivity verificationSecondActivity);
void inject(VerificationMenuActivity verificationMenuActivity);
void inject(VerificationResultActivity verificationResultActivity);
}
