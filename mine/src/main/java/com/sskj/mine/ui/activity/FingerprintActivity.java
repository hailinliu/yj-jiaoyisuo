package com.sskj.mine.ui.activity;

import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.presenter.FingerprintPresenter;

public class FingerprintActivity extends BaseActivity<FingerprintPresenter> {


    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_fingerprint;
    }

    @Override
    public FingerprintPresenter getPresenter() {
        return new FingerprintPresenter();
    }
}
