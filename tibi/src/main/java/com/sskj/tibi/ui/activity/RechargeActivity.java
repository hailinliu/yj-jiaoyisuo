package com.sskj.tibi.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.presenter.RechargeActivityPresenter;

import java.util.ArrayList;

import butterknife.BindView;


@Route(path = RConfig.TIBI_RECHARGE)
public class RechargeActivity extends BaseActivity<RechargeActivityPresenter> {
    @BindView(R2.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    String[] title = new String[]{App.INSTANCE.getString(R.string.tibirechargeActivity1), App.INSTANCE.getString(R.string.tibirechargeActivity2)};
    @Autowired
    boolean isQuickBuy = false;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_recharge;
    }

    @Override
    public RechargeActivityPresenter getPresenter() {
        return new RechargeActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.tibirechargeActivity3));
        ARouter.getInstance().inject(this);
        setRightImg(R.mipmap.tibi_icon_record, v -> {
            ARouter.getInstance().build(RConfig.TIBI_RECHARGE_RECORD).navigation();
        });
        //ArrayList<Fragment> fragments = new ArrayList<>();
      //  fragments.add((Fragment) ARouter.getInstance().build(RConfig.TIBI_FRAGMENT_RECHARGE).navigation());
//        fragments.add((Fragment) ARouter.getInstance().build(RConfig.TIBI_FRAGMENT_QUICKBUY).navigation());

       // slidingTabLayout.setViewPager(viewPager, title, this, fragments);
//        if (isQuickBuy){
       // slidingTabLayout.setCurrentTab(1);
//        }
    }

}
