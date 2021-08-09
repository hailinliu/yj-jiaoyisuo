package com.sskj.hangqing.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.ProjectCenterActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
@Route(path = RConfig.PROJECTCENTERACTIVITY)
public class ProjectCenterActivity extends BaseActivity<ProjectCenterActivityPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_gonggao_new_list;
    }

    @Override
    public ProjectCenterActivityPresenter getPresenter() {
        return new ProjectCenterActivityPresenter();
    }
}
