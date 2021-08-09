package com.sskj.tibi.ui.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.tibi.R;
import com.sskj.tibi.presenter.RechargeRecordActivityPresenter;


@Route(path = RConfig.TIBI_RECHARGE_RECORD)
public class RechargeRecordActivity extends BaseActivity<RechargeRecordActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_recharge_record;
    }

    @Override
    public RechargeRecordActivityPresenter getPresenter() {
        return new RechargeRecordActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.tibirechargeRecordActivity1));

    }
}
