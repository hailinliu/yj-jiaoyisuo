package com.sskj.hangqing.ui.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.hangqing.R;
import com.sskj.hangqing.presenter.AheaderFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
@Route(path = RConfig.AHEADERFRAGMENT)
public class AheaderFragment extends BaseFragment<AheaderFragmentPresenter> {
    @Override
    protected void initData() {
        //super.initData();
    }

    @Override
    protected void initView() {
       // super.initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_coin;
    }

    @Override
    protected AheaderFragmentPresenter getPresenter() {
        return new AheaderFragmentPresenter();
    }
}
