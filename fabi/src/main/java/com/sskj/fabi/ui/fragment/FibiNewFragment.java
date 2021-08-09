package com.sskj.fabi.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.NewFabiAllBean;
import com.sskj.fabi.presenter.FibiNewFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.util.HeySlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.FIBINEWFRAGMENT)
public class FibiNewFragment extends BaseFragment<FibiNewFragmentPresenter> {
      @BindView(R2.id.tabs)
      HeySlidingTabLayout tabLayout;
        @BindView(R2.id.viewpager)
        ViewPager viewPager;
        @Autowired
        int type;//1.买，2.卖
        @Autowired
        boolean isBuy;
        @Autowired
        int flag;//1.快捷区2.自选区
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_new_fragment;
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);

    }

    @Override
    protected void initData() {
       // super.initData();
        mPresenter.getFabiCoinAll();
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
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
    }
    public void changeTab(int position) {
        viewPager.setCurrentItem(position);
    }
    @Override
    protected FibiNewFragmentPresenter getPresenter() {
        return new FibiNewFragmentPresenter();
    }

    public void setUi(List<NewFabiAllBean.DataBean> data) {
        titles=new String[data.size()];
        for(int i=0;i<data.size();i++){
            titles[i] = data.get(i).getUnit();
        }
        if(flag==1){
            fragments.clear();
            for(int i=0;i<data.size();i++){
                fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABIQUICKITEMFRAGMENT).withString("id",data.get(i).getId()).withString("type", data.get(i).getUnit()).withInt("flag",type).navigation());
            }

        }else if(flag==2){
            fragments.clear();
            for(int i=0;i<data.size();i++){
                fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_BUY_SELL).withString("type", data.get(i).getUnit()).withInt("flag",type).navigation());
            }

        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        // tabMap.put(0, fragments.get(0));
        tabLayout.setTabSpaceEqual(false);
        tabLayout.setIndicatorWidthEqualTitle(true);
        // tabLayout.setIndicatorGravity(Gravity.TOP);
        tabLayout.setTextsize(18);
        tabLayout.setUnderlineGravity(Gravity.BOTTOM);
        //tabLayout.setIndicatorMargin(0,30,0,0);
        //tabLayout.setTextAllCaps(true);
        tabLayout.setBackgroundResource(R.color.lib_bg);
        tabLayout.setIndicatorColor(getResources().getColor(R.color.libGreen));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.libGreen));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        tabLayout.setViewPager(viewPager,titles);

    }
}
