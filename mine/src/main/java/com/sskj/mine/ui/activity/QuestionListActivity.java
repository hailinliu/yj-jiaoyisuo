package com.sskj.mine.ui.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

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
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.QuestionBean;
import com.sskj.mine.presenter.QuestionListActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 常见问题
 */
@Route(path = RConfig.MINE_QUESTION_LIST)
public class QuestionListActivity extends BaseActivity<QuestionListActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_question_list;
    }

    @Override
    public QuestionListActivityPresenter getPresenter() {
        return new QuestionListActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);

        setTitle(App.INSTANCE.getString(R.string.minequestionListActivity1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.mineDivider))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.mine_recy_item_question, new SlimInjector<QuestionBean>() {
            @Override
            public void onInject(QuestionBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvTitle, data.getTitle())
                        .text(R.id.tvContent, Html.fromHtml(data.getContent()))
                        .visibility(R.id.tvContent, data.isShow() ? View.VISIBLE : View.GONE)
                        .clicked(R.id.ivExpand, v -> {
                            data.setShow(!data.isShow());
                            slimAdapter.notifyItemChanged(slimAdapter.getData().indexOf(data));
                        });
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<QuestionBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<QuestionBean>)
                page -> mPresenter.getData(page), 10);
        MVCCoolHelper<List<QuestionBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();

    }


}
