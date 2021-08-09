package com.sskj.fabi.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.fabi.R;
import com.sskj.fabi.presenter.ChartActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.CHARTACTIVITY)
public class ChartActivity extends BaseActivity<ChartActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_chart;
    }

    @Override
    public ChartActivityPresenter getPresenter() {
        return new ChartActivityPresenter();
    }
}
