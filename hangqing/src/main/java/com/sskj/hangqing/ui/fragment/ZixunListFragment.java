package com.sskj.hangqing.ui.fragment;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.box.imageloader.GlideRoundImageLoader;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.ZixunListBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.ZixunListFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.ImageUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.HANG_FRAGMENT_ZIXUN_LIST)

public class ZixunListFragment extends BaseFragment<ZixunListFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout imageView;
    @BindView(R2.id.banner)
    Banner banner;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_zixun_list;
    }

    @Override
    public ZixunListFragmentPresenter getPresenter() {
        return new ZixunListFragmentPresenter();
    }

    @Override
    public void initView() {
        banner.setImageLoader(new GlideRoundImageLoader());
        tvTitle.setText(App.INSTANCE.getString(R.string.hang_inf));
        imageView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 270))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider))
        );
        SlimAdapter<ZixunListBean> slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_zixun, new SlimInjector<ZixunListBean>() {
            @Override
            public void onInject(ZixunListBean data, IViewInjector injector, List payloads) {
                injector
                        .with(R.id.ivImg, view -> {
                            ImageView imageView = (ImageView) view;
                            ImageUtil.setRoundImage(data.getPicAddr(), imageView);
                        })
                        .text(R.id.tvTitle, data.getBmTitle())
                        .text(R.id.tvTime, data.getIssueTime());
                ClickUtil.click(injector.findViewById(R.id.hang_main), () -> {
                    ARouter.getInstance()
                            .build(RConfig.HANG_GUIDE_WEB)
                            .withBoolean(Constans.IS_ZIXUN, true)
                            .withString(Constans.CONTENT, data.getContent())
                            .withString(Constans.TIME, data.getIssueTime())
                            .withString(Constans.TITLE, data.getBmTitle())
                            .navigation();
                });

            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<ZixunListBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<ZixunListBean>)
                page -> mPresenter.getData(page + ""), 30);
        MVCCoolHelper<List<ZixunListBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
//        ItemClickSupport.addTo(recyclerView)
//                .setOnItemClickListener((recyclerView1, position, v) -> {
//                    ARouter.getInstance()
//                            .build(RConfig.HANG_GUIDE_WEB)
//                            .withBoolean(Constans.IS_ZIXUN, true)
//                            .withString(Constans.CONTENT, slimAdapter.getDataItem(position).getContent())
//                            .withString(Constans.TIME, slimAdapter.getDataItem(position).getIssueTime())
//                            .withString(Constans.TITLE, slimAdapter.getDataItem(position).getBmTitle())
//                            .navigation();
//                });
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
