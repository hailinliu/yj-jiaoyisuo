package com.sskj.tibi.ui.fragment;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.RechargeRecordBean;
import com.sskj.tibi.presenter.RechargeRecordFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@Route(path = RConfig.TIBI_FRAGMENT_RECHARGE_RECORD)//充值记录
public class RechargeRecordFragment extends BaseFragment<RechargeRecordFragmentPresenter> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private BottomSheetDialog bottomSheetCoin;
    private MVCCoolHelper<List<RechargeRecordBean>> mvcHelper;

    String code;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_fragment_recharge_record;
    }

    @Override
    public RechargeRecordFragmentPresenter getPresenter() {
        return new RechargeRecordFragmentPresenter();
    }

    @Override
    protected void initView() {

        SparseArray<String> statusMap = new SparseArray<>();
        statusMap.put(1, App.INSTANCE.getString(R.string.tibirechargeRecordFragment1));
        statusMap.put(2, App.INSTANCE.getString(R.string.tibirechargeRecordFragment2));
        statusMap.put(3, App.INSTANCE.getString(R.string.tibirechargeRecordFragment3));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        SlimAdapter slimAdapter = SlimAdapter.create().register(R.layout.tibi_recy_item_recharge_record, new SlimInjector<RechargeRecordBean>() {
            @Override
            public void onInject(RechargeRecordBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvAddress, data.getWalletAddr())
                        .text(R.id.tvNum, data.getUsdFee())
                        .text(R.id.tvCreateTime, data.getCreateTime())

                ;
            }
        }).attachTo(recyclerView).updateData(new ArrayList());

        ModelRx2DataSource<RechargeRecordBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<RechargeRecordBean>)
                page -> mPresenter.getRechargeRecord(page + "", code), 10);
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    @Override
    protected void initData() {
//        mPresenter.getCoinType();
    }


}
