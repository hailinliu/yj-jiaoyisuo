package com.sskj.hangqing.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.PaySettingActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.HeySlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.PAYSETTINGACTIVITY)
public class PaySettingActivity extends BaseActivity<PaySettingActivityPresenter> {
    @BindView(R2.id.tabs)
    HeySlidingTabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    String[] strings= {App.INSTANCE.getString(R.string.libpayTypeEnum3)};
    List<Fragment> fragments = new ArrayList<>();
    @Autowired
    String realname;
    @Autowired
    int status;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_pay_setting;
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        tabLayout.setOnTabSelectListener(new HeySlidingTabLayout.OnTabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
                changeTab(position);
                return false;
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void changeTab(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void initData() {
       // super.initData();

    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.hang_pay_set));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.libTextWhite));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BANKFRAGMENT).withString("realname",realname).withInt("status",status).navigation());
        //fragments.add((Fragment) ARouter.getInstance().build(RConfig.PAYADFRAGMENT).withString("realname",realname).withInt("status",status).navigation());
       // fragments.add((Fragment) ARouter.getInstance().build(RConfig.WEIXINFRAGMENT).withString("realname",realname).withInt("status",status).navigation());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setTabSpaceEqual(false);
        tabLayout.setIndicatorWidthEqualTitle(true);
        tabLayout.setViewPager(viewPager,strings);
    }

    @Override
    public PaySettingActivityPresenter getPresenter() {
        return new PaySettingActivityPresenter();
    }
}
