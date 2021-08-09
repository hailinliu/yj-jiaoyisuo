package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.WorkerOrderContentActiivtyPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.WORKERORDERCONTENTACTIIVTY)
public class WorkerOrderContentActiivty extends BaseActivity<WorkerOrderContentActiivtyPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_fabi_order_list_content;
    }

    @Override
    public WorkerOrderContentActiivtyPresenter getPresenter() {
        return new WorkerOrderContentActiivtyPresenter();
    }
}
