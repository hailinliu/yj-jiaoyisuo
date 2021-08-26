package com.sskj.hangqing.ui.fragment;

import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.NewHangFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.BottomSheetUtil;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = RConfig.NEWHANGFRAGMENT)
public class NewHangFragment extends BaseFragment<NewHangFragmentPresenter> {
    @BindView(R2.id.newcommonTabLayout)
    CommonTabLayout newcommonTabLayout;
    @BindView(R2.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R2.id.image)
    ImageView imageView;
    @BindView(R2.id.image_start)
    ImageView imageStart;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.image_sousuo)
    ImageView image_solo;
    private int curPos = 0;
    private ArrayList<Fragment> fragments;
    SparseArray<Fragment> tabMap = new SparseArray<>();
    private BottomSheetDialog priceModeSheet;
    private ArrayList<BottomSheetUtil.ItemBean> list = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_new;
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(tvContent, () -> {
            priceModeSheet = BottomSheetUtil.getBottomSheet1(getActivity(), App.INSTANCE.getString(R.string.lib_xuanze), (recyclerView, position, v) -> {
                priceModeSheet.dismiss();
               int d = list.get(position).id;
                imageView.setImageResource(d);
                tvContent.setText(list.get(position).content);
                mPresenter.getRate("USD",list.get(position).content);
               /* if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                    }*/
            },list);
            priceModeSheet.show();
         /*   ARouter.getInstance().build(RConfig.HANG_MARKET)
                    .withString(Constans.CODE, code)
                    .withInt(Constans.TYPE,1)
                    .navigation();*/
        });
      image_solo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ARouter.getInstance().build(RConfig.SOLOACTIVITY).navigation();
          }
      });
    }

    @Override
    protected void initView() {
      //  super.initView();
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        imageStart.setVisibility(View.INVISIBLE);
       /* list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_cny,"CNY"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_eru,"EUR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_hkd,"HKD"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_rub,"RUB"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_krw,"KRW"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_myr,"MYR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_twd,"TWD"));*/
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_usd,"USD"));
        ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
       // tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqinghang_fragment_trade1)));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqinghang_fragment_trade2)));
        //tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqinghang_fragment_trade3)));
        fragments = new ArrayList<>();
      //  fragments.add((Fragment) ARouter.getInstance().build(RConfig.MYSELECTFRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.STANDARDFRAGMENT).withBoolean("ishangqing",true).navigation());
     //   fragments.add((Fragment) ARouter.getInstance().build(RConfig.AHEADERFRAGMENT).navigation());

        newcommonTabLayout.setTabData(tabItems);
        newcommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
               changeTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, fragments.get(0))
                .show(fragments.get(0))
                .commitAllowingStateLoss();
        tabMap.put(0, fragments.get(0));
    }

    @Override
    protected void initData() {
        //super.initData();
        mPresenter.getRate("USD","USD");
    }

    public void changeTab(int position) {
        if (tabMap.get(position) == null) {
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
        curPos = position;

    }
    @Override
    protected NewHangFragmentPresenter getPresenter() {
        return new NewHangFragmentPresenter();
    }

    public void setUI(RateBean bean) {
        LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).postValue(bean);
    }
}
