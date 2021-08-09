package com.sskj.tibi.ui.fragment;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.sskj.tibi.bean.WithdrawRecordBean;
import com.sskj.tibi.presenter.WithdrawRecordFragmentPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


@Route(path = RConfig.TIBI_FRAGMENT_WITHDRAW_RECORD)//提币记录
public class WithdrawRecordFragment extends BaseFragment<WithdrawRecordFragmentPresenter> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;

    String code;
    private BottomSheetDialog bottomSheetCoin;
    private MVCCoolHelper<List<WithdrawRecordBean>> mvcHelper;
    private int curPos;
    private SlimAdapter<WithdrawRecordBean> slimAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_fragment_withdraw_record;
    }

    @Override
    public WithdrawRecordFragmentPresenter getPresenter() {
        return new WithdrawRecordFragmentPresenter();
    }

    @Override
    protected void initView() {
        HashMap<String, String> tibiMap = new HashMap<>();

        tibiMap.put("0", App.INSTANCE.getString(R.string.tibiwithdrawRecordFragment1));
        tibiMap.put("1", App.INSTANCE.getString(R.string.tibiwithdrawRecordFragment2));
        tibiMap.put("2", App.INSTANCE.getString(R.string.tibiwithdrawRecordFragment3));
        tibiMap.put("4", App.INSTANCE.getString(R.string.tibiwithdrawRecordFragment4));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(true)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.tibi_recy_item_withdraw_record, new SlimInjector<WithdrawRecordBean>() {
            @Override
            public void onInject(WithdrawRecordBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvAddress, data.getWalletAddr())
                        .text(R.id.tvNum, data.getUsdFee())
                        .text(R.id.tvCreateTime, data.getCreateTime())
                        .text(R.id.tvCheckTime, data.getInspectTime())
                        .text(R.id.tvStatus, tibiMap.get(data.getInspectStatus() + ""))
                        .visibility(R.id.btCancel, "0".equals(data.getInspectStatus() + "") ? View.VISIBLE : View.GONE)
                        .visibility(R.id.llReason, "2".equals(data.getInspectStatus() + "") ? View.VISIBLE : View.GONE)
                        .text(R.id.tvReason, data.getInspectReason())
                        .clicked(R.id.btCancel, v -> {
                            curPos = slimAdapter.getData().indexOf(data);
                            cancelOrder();
                        })
                ;
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<WithdrawRecordBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<WithdrawRecordBean>)
                page -> mPresenter.getRecord(page + "", code), 10);
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    private void cancelOrder() {
        mPresenter.cancelOrder(slimAdapter.getDataItem(curPos).getId() + "");
    }

    @Override
    protected void initData() {
//        mPresenter.getCoinType();
    }

    public void cancelOrderSuccess() {
        mvcHelper.refresh();
    }
}
