package com.sskj.hangqing.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.FabiBaseOrderFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
@Route(path = RConfig.FABIBASEORDERFRAGMENT)
public class FabiBaseOrderFragment extends BaseFragment<FabiBaseOrderFragmentPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_current;
    }

    @Override
    protected FabiBaseOrderFragmentPresenter getPresenter() {
        return new FabiBaseOrderFragmentPresenter();
    }
}
