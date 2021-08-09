package com.sskj.hangqing.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.hangqing.R;

import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.BIBIOrderActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.HeySlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = RConfig.BIBIORDERACTIVITY)
public class BIBIOrderActivity extends BaseActivity<BIBIOrderActivityPresenter> {
    @BindView(R2.id.tabs)
    HeySlidingTabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    @BindView(R2.id.ivTopRight)
    LinearLayout ivTopRight;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = {App.INSTANCE.getString(R.string.hang_dangqian),App.INSTANCE.getString(R.string.hang_lishi)};
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_new_fragment;
    }

    @Override
    protected void initView() {
     //   super.initView();
        ARouter.getInstance().inject(this);
        fragments.clear();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BIBICURRENTFRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BIBIHISTORYFRAGMENT).navigation());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        // tabMap.put(0, fragments.get(0));
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setIndicatorWidthEqualTitle(false);
        // tabLayout.setIndicatorGravity(Gravity.TOP);
        tabLayout.setTextsize(20);
        tabLayout.setUnderlineGravity(Gravity.BOTTOM);
        //tabLayout.setIndicatorMargin(0,30,0,0);
        //tabLayout.setTextAllCaps(true);
        tabLayout.setTextSelectColor(getResources().getColor(R.color.libText_3));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        tabLayout.setViewPager(viewPager,titles);
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        tabLayout.setOnTabSelectListener(new HeySlidingTabLayout.OnTabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                //ToastUtil.showShort("我已经点击了"+position);
                return false;
            }

            @Override
            public void onTabReselect(int position) {
                //  ToastUtil.showShort("我已经点击了"+position);
            }
        });
        ivTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipFabiUtil.showFilter1(BIBIOrderActivity.this, str -> {

                });
            }
        });
    }

    @Override
    public BIBIOrderActivityPresenter getPresenter() {
        return new BIBIOrderActivityPresenter();
    }
}
