package com.sskj.hangqing.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.ZixunFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = RConfig.HANG_FRAGMENT_ZIXUN)
public class ZixunFragment extends BaseFragment<ZixunFragmentPresenter> {
    @BindView(R2.id.slidingTabLayout)
    SegmentTabLayout slidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    String[] titles = new String[]{App.INSTANCE.getString(R.string.hangqingguideWebActivity9), App.INSTANCE.getString(R.string.hangqingzixunFragment2)};


    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_zixun;
    }

    @Override
    public ZixunFragmentPresenter getPresenter() {
        return new ZixunFragmentPresenter();
    }

    @Override
    public void initView() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_ZIXUN_LIST).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_GUIDE_LIST).navigation());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragments));
        slidingTabLayout.setTabData(titles);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                slidingTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
