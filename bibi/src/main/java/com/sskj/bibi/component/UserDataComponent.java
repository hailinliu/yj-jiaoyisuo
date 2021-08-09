package com.sskj.bibi.component;

import com.sskj.bibi.ui.fragment.AllEntrustFragment;
import com.sskj.bibi.ui.fragment.BibiBuyAndSellFragment;
import com.sskj.bibi.ui.fragment.BibiMainFragment;
import com.sskj.bibi.ui.fragment.CurrentEntrustFragment;
import com.sskj.lib.dagger.module.UserModule;

import dagger.Component;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-05 09:01
 */
@Component(modules = UserModule.class)
public interface UserDataComponent {
    void inject(BibiMainFragment levelFragment);
    void inject(CurrentEntrustFragment currentEntrustFragment);
    void inject(BibiBuyAndSellFragment bibiBuyAndSellFragment);
    void inject(AllEntrustFragment bibiBuyAndSellFragment);



}
