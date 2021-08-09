package com.sskj.hangqing.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.BiBiHistoryFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
@Route(path = RConfig.BIBIHISTORYFRAGMENT)
public class BiBiHistoryFragment extends BaseFragment<BiBiHistoryFragmentPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_current;
    }

    @Override
    protected BiBiHistoryFragmentPresenter getPresenter() {
        return new BiBiHistoryFragmentPresenter();
    }
}
