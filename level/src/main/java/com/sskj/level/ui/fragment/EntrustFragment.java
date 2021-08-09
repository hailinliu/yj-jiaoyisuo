package com.sskj.level.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.BuySellEnum;
import com.sskj.level.bean.LevelHistoryBean.ContentBean;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.EntrustFragmentPresenter;
import com.sskj.level.util.TipLevelUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.util.TipUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 委托
 */
@Route(path = RConfig.LEVEL_FRAGMENT_ENTRUST)
public class EntrustFragment extends BaseFragment<EntrustFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;

    private SlimAdapter<LevelHistoryBean.ContentBean> slimAdapter;
    private MVCCoolHelper<List<LevelHistoryBean.ContentBean>> mvcCoolHelper;
    private int curPos;
    @Inject
    UserViewModel userViewModel;
    @Autowired
    String code;
    private MaterialDialog cancelTip;

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && mvcCoolHelper != null) {
            mvcCoolHelper.refresh();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_entrust;
    }

    @Override
    public EntrustFragmentPresenter getPresenter() {

        return new EntrustFragmentPresenter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_current_entrust, new SlimInjector<LevelHistoryBean.ContentBean>() {
            @Override
            public void onInject(LevelHistoryBean.ContentBean data, IViewInjector injector, List payloads) {
                injector
                        .textColor(R.id.tv_mm, ContextCompat.getColor(App.INSTANCE, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getColorRes()))
                        .text(R.id.tv_mm, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getTitle())//买入卖出
                        .text(R.id.tv_bz, data.getSymbol().toUpperCase())//币种类型
                        .text(R.id.tvType, data.getType()==0?App.INSTANCE.getString(R.string.bibi_shi_jia):App.INSTANCE.getString(R.string.bibi_limit_price))//市价限价
                        .text(R.id.tv_time, TimeFormatUtil.SF_FORMAT_F.format(new Date(data.getTime())))//时间
                        .text(R.id.tvNum, data.getLevel())//杠杆倍数
                        .text(R.id.tvEntrustPrice,new BigDecimal(data.getPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//委托价
                        .text(R.id.tvEnsureMoney, new BigDecimal(data.getMarginMoney()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//保证金
                        .text(R.id.tvFee, new BigDecimal(data.getFee()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//手续费
                        .text(R.id.tv_weituo,new BigDecimal(data.getAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .clicked(R.id.tvCancel, v -> {
                            curPos = slimAdapter.getData().indexOf(data);
                            cancel();
                        });
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<LevelHistoryBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<LevelHistoryBean.ContentBean>) page -> mPresenter.getData(code, page + "", "10"), 10);
        mvcCoolHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcCoolHelper.setDataSource(dataSource);
        mvcCoolHelper.setAdapter(slimAdapter);
        mvcCoolHelper.refresh();
    }

    /**
     * 撤销订单
     */
    private void cancel() {

        TipUtil.getSureTip(getActivity(),App.INSTANCE.getString(R.string.levelcurrentEntrustFragment1),() -> mPresenter.cancelEntrust(slimAdapter.getDataItem(curPos).getOrderId()));
    }

    public void refresh() {
        mvcCoolHelper.refresh();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

/*    public void updateUI(List<LevelHistoryBean.ContentBean> data) {
        if (slimAdapter != null) {
            if (data.size() > 0 && slimAdapter.getData().size() == 0) {
                mvcCoolHelper.refresh();
            }
            slimAdapter.updateData(data);
        }
    }*/

    public void cancelReturn(String msg) {
        ToastUtil.showShort(msg);
        userViewModel.update();
        refresh();
    }

}
