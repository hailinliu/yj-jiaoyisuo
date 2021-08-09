package com.sskj.bibi.ui.activity;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.presenter.SlideActivityPresenter;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@Route(path = RConfig.BIBI_SLIDE)
public class SlideActivity extends BaseActivity<SlideActivityPresenter> {
    @BindView(R2.id.ivClose)
    ImageView ivClose;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.llRoot)
    LinearLayout llRoot;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.bibi_activity_slide;
    }

    @Override
    public SlideActivityPresenter getPresenter() {
        return new SlideActivityPresenter();
    }


    @Override
    public boolean getOrientation() {
        return false;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
//        overridePendingTransition(R.anim.fabi_slide_left_in, R.anim.bibi_slide_no_move);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);//需要添加的语句
        ClickUtil.click(ivClose, () -> {
            llRoot.setBackgroundColor(Color.TRANSPARENT);
            finish();
        });

//        titles.add("USDT");
//        titles.add("BTC");
//        titles.add("ETH");
//        Disposable disposable = Flowable.fromIterable(titles)
//                .onBackpressureDrop()
//                .map(s -> "/" + s)
//                .subscribe(item -> fragments.add((Fragment) ARouter.getInstance()
//                        .build(RConfig.BIBI_FRAGMENT_SLIDE)
//                        .withString(Constans.QU, item)
//                        .navigation()));


    }

    @Override
    protected void initData() {
        mPresenter.getList();
    }

    @Override
    public void finish() {
        super.finish();
        //注释掉activity本身的过渡动画
        overridePendingTransition(0, R.anim.bibi_slide_left_out);
    }

    public void getListSuccess(List<String> data) {
        Disposable disposable = Flowable.fromIterable(data)
                .onBackpressureDrop()
                .map(s -> "/" + s)
                .subscribe(item -> fragments.add((Fragment) ARouter.getInstance()
                        .build(RConfig.BIBI_FRAGMENT_SLIDE)
                        .withString(Constans.QU, item)
                        .navigation()));
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), data, fragments));
        slidingTabLayout.setViewPager(viewPager);
    }

}
