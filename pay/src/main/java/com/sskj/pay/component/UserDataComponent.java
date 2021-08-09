package com.sskj.pay.component;

import com.sskj.lib.dagger.module.UserModule;
import com.sskj.pay.ui.activity.AddPayTypeActivity;
import com.sskj.pay.ui.fragment.PayTypeFragment;

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
    void inject(AddPayTypeActivity addPayTypeActivity);
    void inject(PayTypeFragment payTypeFragment);


}
