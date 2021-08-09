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
import com.sskj.invite.bean.RewardBean;
import com.sskj.invite.presenter.RewardPresenter;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.NumberUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/05/03
 */
public class RewardActivity extends BaseActivity<RewardPresenter> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.invite_activity_reward;
    }

    @Override
    public RewardPresenter getPresenter() {
        return new RewardPresenter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {
        setTitle("我的佣金");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setDividerColor((R.color.inviteDivider)));
        slimAdapter = SlimAdapter.create().register(R.layout.invite_recy_item_reward, new SlimInjector<RewardBean>() {
            @Override
            public void onInject(RewardBean commonBean, IViewInjector iViewInjector, List<Object> list) {
                String time = TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(commonBean.getCreateTime())));
                iViewInjector.text(R.id.tvName, commonBean.getUsername())
                        .text(R.id.tvNum, commonBean.getUserReturnFee())
                        .text(R.id.tvTime, time.replace(" ", "\n"));
            }

        }).attachTo(recyclerView).updateData(new ArrayList());

        ModelRx2DataSource<RewardBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<RewardBean>)
                page -> mPresenter.getData(page + "", "20"), 20);
        MVCCoolHelper<List<RewardBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RewardActivity.class);
        context.startActivity(intent);
    }


}
