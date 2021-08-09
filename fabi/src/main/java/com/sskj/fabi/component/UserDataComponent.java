package com.sskj.fabi.component;

import com.sskj.fabi.ui.activity.OrderRecordActivity;
import com.sskj.fabi.ui.activity.PayOrderActivity;
import com.sskj.fabi.ui.activity.PayOrderSellActivity;
import com.sskj.fabi.ui.activity.PublishActivity;
import com.sskj.fabi.ui.fragment.BuySellFragment;
import com.sskj.fabi.ui.fragment.FaBiQuickItemFragment;
import com.sskj.fabi.ui.fragment.FabiFragment;
import com.sskj.fabi.ui.fragment.FabiMainFragmentActivity;
import com.sskj.fabi.ui.fragment.FabiRecordFragment;
import com.sskj.fabi.ui.fragment.QuickBuyFragment;
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


    void inject(FabiFragment fabiFragment);
    void inject(BuySellFragment buySellFragment);
    void inject(OrderRecordActivity orderRecordActivity);
    void inject(FabiMainFragmentActivity fabiMainFragmentActivity);
    void inject(PayOrderSellActivity payOrderSellActivity);
    void inject(PayOrderActivity payOrderActivity);
    void inject(PublishActivity publishActivity);
    void inject(FabiRecordFragment fabiRecordFragment);
    void inject(QuickBuyFragment quickBuyFragment);
    void inject(FaBiQuickItemFragment faBiQuickItemFragment);
}
