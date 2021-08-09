package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.SafetySettingActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.SAFETYSETTINGACTIVITY)
public class SafetySettingActivity extends BaseActivity<SafetySettingActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_setting_history;
    }

    @Override
    public SafetySettingActivityPresenter getPresenter() {
        return new SafetySettingActivityPresenter();
    }
}
