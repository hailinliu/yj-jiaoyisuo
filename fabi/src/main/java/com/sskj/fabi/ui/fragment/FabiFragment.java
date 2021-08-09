package com.sskj.fabi.ui.fragment;


import android.content.res.ColorStateList;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.FilterBean;

import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.FabiFragmentPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.ViewPagerBoolean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.FABI_FRAGMENT)
public class FabiFragment extends BaseFragment<FabiFragmentPresenter> {
    @BindView(R2.id.viewPager)
    ViewPagerBoolean viewPager;
    @BindView(R2.id.ivFilter)
    ImageView ivFilter;
    @BindView(R2.id.ivRecord)
    ImageView ivRecord;
    @BindView(R2.id.tvBuy)
    TextView tvBuy;
    @BindView(R2.id.tvSell)
    TextView tvSell;
    @BindView(R2.id.btPublish)
    Button btPublish;

    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private boolean isBuy = true; // 购买大厅

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_fabi;
    }

    @Override
    public FabiFragmentPresenter getPresenter() {
        return new FabiFragmentPresenter();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FIBINEWFRAGMENT).withInt("type",1).withInt("flag",2).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FIBINEWFRAGMENT).withInt("type",2).withInt("flag",2).navigation());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCanScrollble(false);
        ClickUtil.click(tvBuy, () -> {
            isBuy = true;
            changeType();

        });

        ClickUtil.click(tvSell, () -> {
            isBuy = false;
            changeType();
        });


        ClickUtil.click(ivRecord, () -> {
           /* if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }*/
           if(SPUtil.get(SPConfig.ISLOGIN,false)){
               ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD).navigation();
           }else {
               ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
           }


        });

        ClickUtil.click(ivFilter, () -> { // 交易记录
            TipFabiUtil.showFilter(getActivity(), str -> {
                String minMoney = TextUtils.isEmpty(str[0]) ? null : str[0];
                String tpay = str[1];
                String currency = str[2];
                LiveDataBus.get().with(RxBusCode.CHANGE_FABI_MARKET_FILTER, FilterBean.class).postValue(new FilterBean(tpay, minMoney,currency));
            });


        });

        ClickUtil.click(btPublish, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkHighAuth(getActivity(), userData)) {  // 高级实名
                return;
            }
            if (!AppCircleCheckUtil.checkShop(getActivity(), userData)) {  //是否是商家
                return;
            }
            if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {  // 资金密码
                return;
            }
            ARouter.getInstance().build(RConfig.FABI_PUBLISH)
                    .withBoolean(Constans.IS_BUY, isBuy)
                    .navigation();
        });


    }

    private void changeType() {
        float textSize = tvBuy.getTextSize();
        tvBuy.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSell.getTextSize());
        tvSell.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        ColorStateList textColors = tvBuy.getTextColors();
        tvBuy.setTextColor(tvSell.getTextColors());
        tvSell.setTextColor(textColors);
        viewPager.setCurrentItem(isBuy ? 0 : 1);
    }

}
