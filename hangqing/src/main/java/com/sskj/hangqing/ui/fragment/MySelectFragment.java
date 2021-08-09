package com.sskj.hangqing.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.MySelectFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
@Route(path = RConfig.MYSELECTFRAGMENT)
public class MySelectFragment extends BaseFragment<MySelectFragmentPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_coin;
    }

    @Override
    protected MySelectFragmentPresenter getPresenter() {
        return new MySelectFragmentPresenter();
    }
}
