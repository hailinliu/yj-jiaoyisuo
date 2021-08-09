package com.sskj.bibi.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.presenter.RecordActivityPresenter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单记录
 */
@Route(path = RConfig.BIBI_RECORD)
public class RecordActivity extends BaseActivity<RecordActivityPresenter> {
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    private List<Fragment> fragments;
    @Autowired
    String code;
    String[] data = null;

    @Override
    protected int getLayoutId() {
        return R.layout.bibi_activity_record;
    }

    @Override
    public RecordActivityPresenter getPresenter() {
        return new RecordActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        data = new String[]{getString(R.string.bibi_all_commission), getString(R.string.bibi_history_commission)};
        fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BIBI_All_ENTRUST).withString("code",code).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BIBI_HISTORY_ENTRUST).withString("code",code).navigation());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        slidingTabLayout.setViewPager(viewPager,data);
    }
}
