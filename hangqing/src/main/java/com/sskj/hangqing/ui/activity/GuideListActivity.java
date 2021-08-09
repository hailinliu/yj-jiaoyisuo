package com.sskj.hangqing.ui.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.box.imageloader.GlideRoundImageLoader;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.GuideListBean;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.GonggaoListActivityPresenter;
import com.sskj.hangqing.presenter.GuideListFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressWarnings("ALL")
@Route(path = RConfig.HANG_FRAGMENT_GUIDE_LIST)
public class GuideListActivity extends BaseActivity<GuideListFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @BindView(R2.id.banner)
    Banner banner;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_guide_list;
    }

    @Override
    public GuideListFragmentPresenter getPresenter() {
        return new GuideListFragmentPresenter();
    }

    @Override
    public void initView() {
        banner.setImageLoader(new GlideRoundImageLoader());
        setTitle(App.INSTANCE.getString(R.string.hangqingzixunFragment2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider))
        );
        SlimAdapter<GuideListBean> slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_guide1, new SlimInjector<GuideListBean>() {
            @Override
            public void onInject(GuideListBean data, IViewInjector injector, List payloads) {
                injector
                        .text(R.id.tvTitle, data.getTitle());
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<GuideListBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<GuideListBean>)
                page -> mPresenter.getData(page + ""), 20);
        MVCCoolHelper<List<GuideListBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView1, position, v) -> {
                    int size = slimAdapter.getItemCount();
                    if (position >= size) return;

                    ARouter.getInstance()
                            .build(RConfig.HANG_GUIDE_WEB)
                            .withBoolean(Constans.IS_TRADE_GUIDE, true)
                            .withString(Constans.HEAD, App.INSTANCE.getString(R.string.hangqingzixunFragment2))
                            .withString(Constans.CONTENT, slimAdapter.getDataItem(position).getContent())
                            .withString(Constans.TIME, slimAdapter.getDataItem(position).getCreateTime())
                            .withString(Constans.TITLE, slimAdapter.getDataItem(position).getTitle())
                            .navigation();
                });
    }

    @Override
    public void initData() {
        mPresenter.getBanner();
    }


    public void updateBanner(List<HangqingBannerBean> data) {
        if (banner == null)
            return;

        ArrayList<String> strings = new ArrayList<>();
        for (HangqingBannerBean bannerItem : data) {
            strings.add(HttpConfig.BASE_IMG_URL + bannerItem.getUrl());
        }

        banner.setImages(strings);
        banner.setImageLoader(new GlideRoundImageLoader());
        banner.setOffscreenPageLimit(3);
        banner.setDelayTime(2000);
        banner.start();
    }

}
