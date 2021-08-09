package com.sskj.tibi.ui.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.lib.RConfig;
import com.sskj.tibi.R;
import com.sskj.tibi.presenter.OtherRecordActivityPresenter;
import com.sskj.lib.base.BaseActivity;


@Route(path = RConfig.TIBI_OTHER_RECORD)
public class OtherRecordActivity extends BaseActivity<OtherRecordActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_other_record;
    }

    @Override
    public OtherRecordActivityPresenter getPresenter() {
        return new OtherRecordActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle("资产明细");
    }
}
