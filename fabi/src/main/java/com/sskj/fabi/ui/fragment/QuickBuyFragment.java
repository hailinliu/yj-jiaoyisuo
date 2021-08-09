package com.sskj.fabi.ui.fragment;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.QuickBuyFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.HeySlidingTabLayout;
import com.sskj.lib.util.ViewPagerBoolean;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 快捷买币
 */
@Route(path = RConfig.FABI_FRAGMENT_QUICK_BUY)
public class QuickBuyFragment extends BaseFragment<QuickBuyFragmentPresenter> {
   /* @BindView(R2.id.tvTypeTitle)
    TextView tvTypeTitle;
    @BindView(R2.id.tvUnitCNY)
    TextView tvUnitCNY;*/
   /* @BindView(R2.id.tvUnitUSDT)
    TextView tvUnitUSDT;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvBuyType)
    TextView tvBuyType;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.tvFee)
    TextView tvFee;
    @BindView(R2.id.llFee)
    LinearLayout llFee;*/
  /*  @BindView(R2.id.tvBuy)
    TextView tvBuy;
    @BindView(R2.id.tvSell)
    TextView tvSell;*/
    @BindView(R2.id.tabs)
    HeySlidingTabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPagerBoolean viewPager;
   //private int curPos = 0;
    SparseArray<Fragment> tabMap = new SparseArray<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = {App.INSTANCE.getString(R.string.fabifabi_fragment_quick100),App.INSTANCE.getString(R.string.fabifabi_fragment_quick101)};

    private boolean isNum;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_quick_buy;
    }

    @Override
    public QuickBuyFragmentPresenter getPresenter() {
        return new QuickBuyFragmentPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
      /*  ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.fabifabi_fragment_quick100)));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.fabifabi_fragment_quick101)));*/
        //tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqinghang_fragment_trade3)));
        fragments = new ArrayList<>();
        //fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FIBINEWFRAGMENT).withInt("type",1).withInt("flag",1).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FIBINEWFRAGMENT).withInt("type",2).withInt("flag",1).navigation());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCanScrollble(false);
        viewPager.setCurrentItem(0);
        tabLayout.setTabSpaceEqual(false);
      //  tabLayout.setTextAllCaps(true);
        tabLayout.setTextsize(20);
      //  tabLayout.setIndicatorColor(getResources().getColor(R.color.libTextContent));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.libTextWhite));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        tabLayout.setViewPager(viewPager,titles);
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
       /* commonTabLayout.setTabData(tabItems);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                changeTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });*/

      //  isNum = false;

//        ClickUtil.click(20, tvBuyType, () -> {
//            isNum = !isNum;
//            etNum.setText("");
//            changeType();
//        });

    }

    @Override
    protected void initData() {
     /*   getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, fragments.get(0))
                .show(fragments.get(0))
                .commit();
        tabMap.put(0, fragments.get(0));*/
    }

    public void changeTab(int position) {
        viewPager.setCurrentItem(position);
      /*  if (tabMap.get(position) == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, fragments.get(position))
                    .hide(fragments.get(curPos))
                    .show(fragments.get(position))
                    .commitAllowingStateLoss();
            tabMap.put(position, fragments.get(position));
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .hide(fragments.get(curPos))
                    .show(fragments.get(position))
                    .commitAllowingStateLoss();
        }
        curPos = position;*/
    }
    public void buySuccess(String content) {
       /* ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean(Constans.IS_QUICK_BUY, true).withString(Constans.CONTENT, content).navigation();

        etNum.setText("");*/
    }
}
