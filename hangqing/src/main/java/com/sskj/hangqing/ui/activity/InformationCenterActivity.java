package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.InformationCenterActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.INFORMATIONCENTERACTIVITY)
public class InformationCenterActivity extends BaseActivity<InformationCenterActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_information_center;
    }

    @Override
    public InformationCenterActivityPresenter getPresenter() {
        return new InformationCenterActivityPresenter();
    }
}
