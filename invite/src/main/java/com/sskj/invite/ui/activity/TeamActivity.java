package com.sskj.invite.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.invite.R;
import com.sskj.invite.R2;
import com.sskj.invite.bean.TeamBean;
import com.sskj.invite.presenter.TeamPresenter;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/05/03
 */
public class TeamActivity extends BaseActivity<TeamPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.invite_activity_team;
    }

    @Override
    public TeamPresenter getPresenter() {
        return new TeamPresenter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {
        setTitle("我的团队");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setDividerColor(color(R.color.inviteDivider)));
        slimAdapter = SlimAdapter.create().register(R.layout.invite_recy_item_team, new SlimInjector<TeamBean>() {
            @Override
            public void onInject(TeamBean commonBean, IViewInjector iViewInjector, List<Object> list) {
                iViewInjector.text(R.id.id, commonBean.getUsername());
            }

        }).attachTo(recyclerView).updateData(new ArrayList());

        ModelRx2DataSource<TeamBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<TeamBean>)
                page -> mPresenter.getData(page + "", "20"), 20);
        MVCCoolHelper<List<TeamBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        context.startActivity(intent);
    }


}
