package com.sskj.fabi.ui.activity;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.common.base.App;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.FabiRecordFilterBean;
import com.sskj.fabi.bean.FilterBean;
import com.sskj.fabi.bean.NewFabiAllBean;
import com.sskj.fabi.bean.OrderBean;
import com.sskj.fabi.presenter.OrderRecordActivityPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.FABI_ORDER_RECORD)
public class OrderRecordActivity extends BaseActivity<OrderRecordActivityPresenter> {

    @BindView(R2.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @Autowired
    boolean isPublish = false;
    private String[] titles = new String[]{App.INSTANCE.getString(R.string.fabi_goumai),App.INSTANCE.getString(R.string.fabi_chushou),App.INSTANCE.getString(R.string.fabi_sensu),App.INSTANCE.getString(R.string.fabi_already)};
    private BottomSheetDialog statusBottomDialog;
    private BottomSheetDialog typeBottomDialog;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_order_record;
    }

    @Override
    public OrderRecordActivityPresenter getPresenter() {
        return new OrderRecordActivityPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getFabiCoinAll();
        //super.initData();
       // mPresenter.queryOrder(1,20);
    }

    @Override
    protected void initView() {

        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.fabi_order));
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_ORDER_RECORD).withInt("type",1).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_ORDER_RECORD).withInt("type",2).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_ORDER_RECORD).withInt("type",3).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_ORDER_RECORD).withInt("type",4).navigation());
        //fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_PUBLISH_RECORD).navigation());
        slidingTabLayout.setViewPager(viewPager, titles, this, fragments);
        ArrayList<String> statusList = new ArrayList<>();
        ArrayList<String> typeList = new ArrayList<>();
        statusList.add(App.INSTANCE.getString(R.string.fabiorderRecordActivity3));
        statusList.add(App.INSTANCE.getString(R.string.fabiorderRecordActivity4));
        statusList.add(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum4));
        statusList.add(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum5));
        statusList.add(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum6));
        typeList.add(App.INSTANCE.getString(R.string.fabiorderRecordActivity8));
        typeList.add(App.INSTANCE.getString(R.string.fabiorderRecordActivity9));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                isPublish = position == 1;
            }
        });
        slidingTabLayout.setCurrentTab(isPublish ? 1 : 0);
        slidingTabLayout.setTabSpaceEqual(false);
        slidingTabLayout.setIndicatorWidthEqualTitle(true);
        setRightImg(R.mipmap.fabi_icon_filter, v -> {
            TipFabiUtil.showFilter2(stringList,OrderRecordActivity.this, str -> {
                    String orn = TextUtils.isEmpty(str[0])?"":str[0];
                    String cointype= TextUtils.isEmpty(str[2])?"":str[2].contains("全部")?"":str[2];
                  String status = TextUtils.isEmpty(str[1])?"":str[1];
                  if(status.contains("已付款")){
                    status = "PAID";
                  }else if(status.contains("待支付")){
                     status = "NONPAYMENT";
                  }
                for(NewFabiAllBean.DataBean bean:data){
                    if(bean.getUnit().equals(cointype)){
                        cointype = bean.getId();
                    }
                }
                LiveDataBus.get().with(RxBusCode.CHANGE_FABI_ORDER_FILTER, FabiRecordFilterBean.class).postValue(new FabiRecordFilterBean(orn, cointype,status));
            });
          /*  if (isPublish) {
                if (typeBottomDialog == null) {
                    typeBottomDialog = TipFabiUtil.showFilterBottom(this, App.INSTANCE.getString(R.string.fabiorderRecordActivity100), typeList, (str) -> {
                        typeBottomDialog.dismiss();
                        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_PUBLISH_RECORD_TYPE, Integer.class).postValue(Integer.valueOf(str[0]) + 1);
                    });
                }
                typeBottomDialog.show();
            } else {
                if (statusBottomDialog == null) {
                    statusBottomDialog = TipFabiUtil.showFilterBottom(this, App.INSTANCE.getString(R.string.fabiorderRecordActivity10), statusList, (str) -> {
                        statusBottomDialog.dismiss();
                        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_ORDER_RECORD_TYPE, Integer.class).postValue(Integer.valueOf(str[0]) + 1);
                    });
                }
                statusBottomDialog.show();

            }*/
        });

    }
private List<NewFabiAllBean.DataBean> data;
    public void setUi(List<NewFabiAllBean.DataBean> data) {
        this.data = data;
        for(NewFabiAllBean.DataBean bean:data){
            stringList.add(bean.getUnit());
        }

    }
}
