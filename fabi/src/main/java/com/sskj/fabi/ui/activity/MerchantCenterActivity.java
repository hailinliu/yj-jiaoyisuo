package com.sskj.fabi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.MerchantCenterBean;
import com.sskj.fabi.bean.NewBaseBean;
import com.sskj.fabi.presenter.MerchantCenterActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.NewUserData;
import com.sskj.lib.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.MERCHANTCENTERACTIVITY)
public class MerchantCenterActivity extends BaseActivity<MerchantCenterActivityPresenter> {
    @Autowired
    String name;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.right_tv)
    TextView rightTv;
    @BindView(R2.id.ivRight)
    ImageView ivRight;
    @BindView(R2.id.tv_firstname)
    TextView tvFirstname;
    @BindView(R2.id.tv_username)
    TextView tvUsername;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.image1)
    ImageView imageView1;
    @BindView(R2.id.image3)
    ImageView imageView3;
    @BindView(R2.id.image2)
    ImageView imageView2;
    @BindView(R2.id.image4)
    ImageView imageView4;
    @BindView(R2.id.view)
    View view;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.tv_xinyongdu)
    TextView tvXinyongdu;
    @BindView(R2.id.tv_shensuliang)
    TextView tvShensuliang;
    @BindView(R2.id.ll_all)
    LinearLayout llAll;
    @BindView(R2.id.ll_renzheng)
    LinearLayout llRenzheng;
    @BindView(R2.id.view1)
    View view1;
    @BindView(R2.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    private String[] titles = {"购买","出售"};
    private List<Fragment> fragments;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_shang_jia;
    }

    @Override
    public MerchantCenterActivityPresenter getPresenter() {
        return new MerchantCenterActivityPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getShoppingInformation(name);
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle("商家中心");
    }

    public void setData(MerchantCenterBean data) {
        tvFirstname.setText(StringUtil.getFirstName(data.getUsername()));
        tvUsername.setText(data.getUsername());
        tvTime.setText(data.getCreateTime());
        tvNum.setText(data.getSuccessCount30()+"");
        tvXinyongdu.setText(data.getSuccessRete30());
        if(data.getEmailVerified()==1){
            imageView4.setImageResource(R.mipmap.fabi_icon_queren);
        }else {
            imageView4.setImageResource(R.mipmap.fabi_icon_unqueren);
        }
        if(data.getPhoneVerified()==1){
            imageView2.setImageResource(R.mipmap.fabi_icon_queren);
        }else {
            imageView2.setImageResource(R.mipmap.fabi_icon_unqueren);
        }
        if(data.getRealVerified()==1){
            imageView1.setImageResource(R.mipmap.fabi_icon_queren);
        }else {
            imageView1.setImageResource(R.mipmap.fabi_icon_unqueren);
        }
        if(data.getGoogleVerified()==1){
            imageView3.setImageResource(R.mipmap.fabi_icon_queren);
        }else {
            imageView3.setImageResource(R.mipmap.fabi_icon_unqueren);
        }
        ArrayList<NewBaseBean> list1 = new ArrayList<>();
        for(MerchantCenterBean.BuyBean buyBean:data.getBuy()){
            NewBaseBean baseBean = new NewBaseBean();
            baseBean.setAdvertiseId(buyBean.getAdvertiseId());
            baseBean.setAdvertiseType(buyBean.getAdvertiseType());
            baseBean.setAdvType(buyBean.getAdvType());
            baseBean.setAvatar(buyBean.getAvatar());
            baseBean.setCoinId(buyBean.getCoinId());
            baseBean.setCoinName(buyBean.getCoinName());
            baseBean.setCoinNameCn(buyBean.getCoinNameCn());
            baseBean.setCountry(buyBean.getCountry());
            baseBean.setCreateTime(buyBean.getCreateTime());
            baseBean.setLevel(buyBean.getLevel());
            baseBean.setMaxLimit(buyBean.getMaxLimit());
            baseBean.setMemberId(buyBean.getMemberId());
            baseBean.setMemberName(buyBean.getMemberName());
            baseBean.setMinLimit(buyBean.getMinLimit());
            baseBean.setNumber(buyBean.getNumber());
            baseBean.setOtcFeeRate(buyBean.getOtcFeeRate());
            baseBean.setPayMode(buyBean.getPayMode());
            baseBean.setPayModeCn(buyBean.getPayModeCn());
            baseBean.setPremiseRate(buyBean.getPremiseRate());
            baseBean.setPrice(buyBean.getPrice());
            baseBean.setRemainAmount(buyBean.getRemainAmount());
            baseBean.setRemark(buyBean.getRemark());
            baseBean.setSuccessCount30(buyBean.getSuccessCount30());
            baseBean.setSuccessRete30(buyBean.getSuccessRete30());
            baseBean.setTimeLimit(buyBean.getTimeLimit());
            baseBean.setTop(buyBean.getTop());
            baseBean.setTransactions(buyBean.getTransactions());
            baseBean.setUnit(buyBean.getUnit());
            baseBean.setVerifyLevel(buyBean.getVerifyLevel());
            list1.add(baseBean);
        }
        ArrayList<NewBaseBean> list2 = new ArrayList<>();
        for(MerchantCenterBean.SellBean buyBean:data.getSell()){
            NewBaseBean baseBean = new NewBaseBean();
            baseBean.setAdvertiseId(buyBean.getAdvertiseId());
            baseBean.setAdvertiseType(buyBean.getAdvertiseType());
            baseBean.setAdvType(buyBean.getAdvType());
            baseBean.setAvatar(buyBean.getAvatar());
            baseBean.setCoinId(buyBean.getCoinId());
            baseBean.setCoinName(buyBean.getCoinName());
            baseBean.setCoinNameCn(buyBean.getCoinNameCn());
            baseBean.setCountry(buyBean.getCountry());
            baseBean.setCreateTime(buyBean.getCreateTime());
            baseBean.setLevel(buyBean.getLevel());
            baseBean.setMaxLimit(buyBean.getMaxLimit());
            baseBean.setMemberId(buyBean.getMemberId());
            baseBean.setMemberName(buyBean.getMemberName());
            baseBean.setMinLimit(buyBean.getMinLimit());
            baseBean.setNumber(buyBean.getNumber());
            baseBean.setOtcFeeRate(buyBean.getOtcFeeRate());
            baseBean.setPayMode(buyBean.getPayMode());
            baseBean.setPayModeCn(buyBean.getPayModeCn());
            baseBean.setPremiseRate(buyBean.getPremiseRate());
            baseBean.setPrice(buyBean.getPrice());
            baseBean.setRemainAmount(buyBean.getRemainAmount());
            baseBean.setRemark(buyBean.getRemark());
            baseBean.setSuccessCount30(buyBean.getSuccessCount30());
            baseBean.setSuccessRete30(buyBean.getSuccessRete30());
            baseBean.setTimeLimit(buyBean.getTimeLimit());
            baseBean.setTop(buyBean.getTop());
            baseBean.setTransactions(buyBean.getTransactions());
            baseBean.setUnit(buyBean.getUnit());
            baseBean.setVerifyLevel(buyBean.getVerifyLevel());
            list2.add(baseBean);
        }
        fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BUYFRAGMENT).withSerializable("data",list1).withInt("type",1).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BUYFRAGMENT).withSerializable("data",list2).withInt("type",2).navigation());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        slidingTabLayout.setViewPager(viewPager,titles);
    }
}
