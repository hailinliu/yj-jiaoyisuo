package com.sskj.mine.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.presenter.NewCoinBaseActivityPresenter;
@Route(path = RConfig.NEWCOINBASEACTIVITY)
public class NewCoinBaseActivity extends BaseActivity<NewCoinBaseActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_assets_new_details;
    }

    @Override
    public NewCoinBaseActivityPresenter getPresenter() {
        return new NewCoinBaseActivityPresenter();
    }
}
