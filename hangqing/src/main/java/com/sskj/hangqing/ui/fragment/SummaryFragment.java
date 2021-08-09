package com.sskj.hangqing.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.InfoBean;
import com.sskj.hangqing.bean.SummaryBean;
import com.sskj.hangqing.bean.rxbus.MarketCoinBean;
import com.sskj.hangqing.presenter.SummaryFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.box.LiveDataBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RConfig.HANG_FRAGMENT_SUMMARY)
public class SummaryFragment extends BaseFragment<SummaryFragmentPresenter> {
    @Autowired
    String code;
    @Autowired
    int type;
    @BindView(R2.id.tvFirst)
    TextView tvFirst;
    @BindView(R2.id.tvSecond)
    TextView tvSecond;
    @BindView(R2.id.tvThird)
    TextView tvThird;
    @BindView(R2.id.tvFour)
    TextView tvFour;
    @BindView(R2.id.tvContent)
    TextView tvContent;
    @BindView(R2.id.tvFive)
    TextView tvFive;
    @BindView(R2.id.tvSix)
    TextView tvSix;
    @BindView(R2.id.tvSeven)
    TextView tvSeven;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_summary;
    }

    @Override
    public SummaryFragmentPresenter getPresenter() {
        return new SummaryFragmentPresenter();
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void initData() {
        mPresenter.getSummary(code);
        LiveDataBus.get().with(RxBusCode.CHANGE_MARKET_CODE, MarketCoinBean.class)
                .observe(this, this::changeCoin);
    }

    public void changeCoin(MarketCoinBean marketCoinBean) {
        if (mPresenter == null)
            return;
        mPresenter.getSummary(marketCoinBean.getCode());
    }




    public void setData(InfoBean bean) {
        if(bean!=null){

            tvFirst.setText(bean.getReleaseTime());
            tvSecond.setText(bean.getReleaseAmount());
            tvThird.setText(bean.getCirculateAmount()+"");
            tvFour.setText(bean.getBlockQuery());
            tvFive.setText(bean.getWebsite());
            tvSix.setText(bean.getWhitePaper());
            tvSeven.setText(bean.getReleaseTime());
           // tvContent.setText(bean.getRemark());
        }
    }




}
