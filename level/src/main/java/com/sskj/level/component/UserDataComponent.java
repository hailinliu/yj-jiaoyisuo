package com.sskj.level.component;

import com.sskj.level.ui.activity.TradeActivity;
import com.sskj.level.ui.fragment.EntrustFragment;
import com.sskj.level.ui.fragment.HolderFragment;
import com.sskj.level.ui.fragment.LevelAllEntrustFragment;
import com.sskj.level.ui.fragment.LevelCurrentEntrustFragment;
import com.sskj.level.ui.fragment.LevelMainFragment;
import com.sskj.level.ui.fragment.NewBuyAndSellFragment;
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
    void inject(LevelMainFragment levelMainFragment);
    void inject(TradeActivity tradeActivity);
    void inject(NewBuyAndSellFragment newBuyAndSellFragment);
    void inject(LevelAllEntrustFragment levelAllEntrustFragment);
    void inject(HolderFragment holderFragment);
    void inject(EntrustFragment entrustFragment);
    void inject(LevelCurrentEntrustFragment levelCurrentEntrustFragment);


}
