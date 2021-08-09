package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.LoginHistoryActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.LOGINHISTORYACTIVITY)
public class LoginHistoryActivity extends BaseActivity<LoginHistoryActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_login_history;
    }

    @Override
    public LoginHistoryActivityPresenter getPresenter() {
        return new LoginHistoryActivityPresenter();
    }
}
