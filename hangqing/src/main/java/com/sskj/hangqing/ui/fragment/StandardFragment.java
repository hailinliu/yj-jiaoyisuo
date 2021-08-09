package com.sskj.hangqing.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.StandardFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.util.HeySlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.STANDARDFRAGMENT)
public class StandardFragment extends BaseFragment<StandardFragmentPresenter> {
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    @BindView(R2.id.tabs)
    HeySlidingTabLayout tabLayout;
    @Autowired
    boolean isLevel;
    @Autowired
    boolean ishangqing;
    private ArrayList<Fragment> fragments = new ArrayList<>();
   // private List<String> titles = new ArrayList<>();
    private String[] titles = {"USDT"};
    SparseArray<Fragment> tabMap = new SparseArray<>();
    private int curPos = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_standard;
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_NRW_COIN_LIST).withInt("type", 2).withBoolean("isLevel",isLevel).withBoolean("ishangqing",ishangqing).navigation());
       // fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withString("type", "LCNY").navigation());
       // fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withString("type", "LCNY").navigation());
       //  adapter = new MyFragementPagerAdapter(getChildFragmentManager(),titles,fragments);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
       // tabMap.put(0, fragments.get(0));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setTextAllCaps(true);
        tabLayout.setTextsize(20);
        tabLayout.setIndicatorColor(getResources().getColor(R.color.hangGreen));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.hangGreen));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        tabLayout.setViewPager(viewPager,titles);

       // tabLayout.setIndicatorWidthEqualTitle(true);



        tabLayout.setOnTabSelectListener(new HeySlidingTabLayout.OnTabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
               changeTab(position);
                //ToastUtil.showShort("我已经点击了"+position);
                return false;
            }

            @Override
            public void onTabReselect(int position) {
              //  ToastUtil.showShort("我已经点击了"+position);
            }
        });
       // tabLayout.setTextSize(30);
    }
    public void changeTab(int position) {
        viewPager.setCurrentItem(position);
    }
    @Override
    protected void initData() {
        //super.initData();
    }

    @Override
    protected StandardFragmentPresenter getPresenter() {
        return new StandardFragmentPresenter();
    }

}
