package com.sskj.hangqing.ui.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.presenter.GonggaoListActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressWarnings("ALL")
@Route(path = RConfig.HANG_GONGGAO_LIST)
public class GonggaoListActivity extends BaseActivity<GonggaoListActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_gonggao_list;
    }

    @Override
    public GonggaoListActivityPresenter getPresenter() {
        return new GonggaoListActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.hangqinggonggaoListActivity1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        SlimAdapter<NoticeBean.ContentBean> slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_guide, new SlimInjector<NoticeBean.ContentBean>() {
            @Override
            public void onInject(NoticeBean.ContentBean data, IViewInjector injector, List payloads) {
                injector
                        .text(R.id.tvTitle, data.getTitle())
                        .text(R.id.tvTime, data.getCreateTime())
                ;
                ClickUtil.click(injector.findViewById(R.id.hang_main),()->{
                    ARouter.getInstance()
                            .build(RConfig.HANG_GUIDE_WEB)
                            .greenChannel()
                            .withBoolean(Constans.IS_HANGQING_NOTICE, true)
                            .withString(Constans.HEAD, App.INSTANCE.getString(R.string.hangqinggonggaoListActivity2))
                            .withString(Constans.TITLE,data.getTitle())
                            .withString(Constans.CONTENT,data.getContent())
                            .withString(Constans.TIME, data.getCreateTime())
                            .navigation();
                });
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<NoticeBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<NoticeBean.ContentBean>)
                page -> mPresenter.getData(page + ""), 20);
        MVCCoolHelper<List<NoticeBean.ContentBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
//        ItemClickSupport.addTo(recyclerView)
//                .setOnItemClickListener((recyclerView1, position, v) -> {
//                    ARouter.getInstance()
//                            .build(RConfig.HANG_GUIDE_WEB)
//                            .greenChannel()
//                            .withBoolean(Constans.IS_HANGQING_NOTICE, true)
//                            .withString(Constans.HEAD, App.INSTANCE.getString(R.string.hangqinggonggaoListActivity2))
//                            .withString(Constans.TITLE, slimAdapter.getDataItem(position).getTitle())
//                            .withString(Constans.CONTENT, slimAdapter.getDataItem(position).getDetails())
//                            .withString(Constans.TIME, slimAdapter.getDataItem(position).getCreateTime())
//                            .navigation();
//                });
    }
}
