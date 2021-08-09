package com.sskj.hangqing.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.BIBICurrentFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
@Route(path = RConfig.BIBICURRENTFRAGMENT)
public class BIBICurrentFragment extends BaseFragment<BIBICurrentFragmentPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_current;
    }

    @Override
    protected BIBICurrentFragmentPresenter getPresenter() {
        return new BIBICurrentFragmentPresenter();
    }
}
