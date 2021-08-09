package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.WorkerOrderActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.WORKERORDERACTIVITY)
public class WorkerOrderActivity extends BaseActivity<WorkerOrderActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_fabi_order_list;
    }

    @Override
    public WorkerOrderActivityPresenter getPresenter() {
        return new WorkerOrderActivityPresenter();
    }
}
