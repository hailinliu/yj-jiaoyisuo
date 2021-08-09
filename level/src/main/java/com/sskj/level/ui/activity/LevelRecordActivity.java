package com.sskj.level.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.presenter.LevelRecordActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.LEVELRECORDACTIVITY)
public class LevelRecordActivity extends BaseActivity<LevelRecordActivityPresenter> {
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
        return R.layout.level_activity_record;
    }

    @Override
    public LevelRecordActivityPresenter getPresenter() {
        return new LevelRecordActivityPresenter();
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
