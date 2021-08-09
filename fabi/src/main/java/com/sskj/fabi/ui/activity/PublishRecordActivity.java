package com.sskj.fabi.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.presenter.PublishRecordActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.FabiBuySellEnum;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = RConfig.FABI_PUBLISH_RECORD)
public class PublishRecordActivity extends BaseActivity<PublishRecordActivityPresenter> {
    @BindView(R2.id.segmentTabLayout)
    SegmentTabLayout segmentTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.back_img)
    ImageView backImg;
    private String[] titles = {FabiBuySellEnum.BUY.getTitle() + App.INSTANCE.getString(R.string.fabipublishRecordActivity1), FabiBuySellEnum.SELL.getTitle() + App.INSTANCE.getString(R.string.fabipublishRecordActivity1)};

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_publish_record;
    }

    @Override
    public PublishRecordActivityPresenter getPresenter() {
        return new PublishRecordActivityPresenter();
    }

    @Override
    protected void initView() {
        segmentTabLayout.setTabData(titles);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_PUBLISH_RECORD).withBoolean(Constans.IS_BUY, true).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_PUBLISH_RECORD).withBoolean(Constans.IS_BUY, false).navigation());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ClickUtil.click(backImg, () -> {
            finish();
        });
    }
}
