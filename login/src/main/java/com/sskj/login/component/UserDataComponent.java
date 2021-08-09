package com.sskj.login.component;


import com.sskj.lib.dagger.module.UserModule;
import com.sskj.login.ui.activity.LoginActivity;

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
    void inject(LoginActivity loginActivity);


}
