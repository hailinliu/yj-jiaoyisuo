package com.sskj.level.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.BuySellEnum;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.LevelCurrentEntrustFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.LevelCoinType;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.util.TipUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
@Route(path = RConfig.LEVELCURRENTENTRUSTFRAGMENT)
public class LevelCurrentEntrustFragment extends BaseFragment<LevelCurrentEntrustFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.includeEmpty)
    View includeEmpty;
    private SlimAdapter<LevelHistoryBean.ContentBean> slimAdapter;
    @Autowired
    String code;
    private int curPos;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;


    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_current_entrust;
    }

    @Override
    public LevelCurrentEntrustFragmentPresenter getPresenter() {
        return new LevelCurrentEntrustFragmentPresenter();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);

        ARouter.getInstance().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_current_entrust, new SlimInjector<LevelHistoryBean.ContentBean>() {
            @Override
            public void onInject(LevelHistoryBean.ContentBean data, IViewInjector injector, List payloads) {
                injector
                        .textColor(R.id.tv_mm, ContextCompat.getColor(App.INSTANCE, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getColorRes()))
                        .text(R.id.tv_mm, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getTitle())//买入卖出
                        .text(R.id.tv_bz, data.getSymbol().toUpperCase())//币种类型
                        .text(R.id.tvType, data.getType()==0?"市价":"限价")//市价限价
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
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                refresh();
            } else {
                userData = null;
                showEmpty();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getEntrustListRefresh("1","20",code);
        LiveDataBus.get().with(RxBusCode.LEVEL_FRESH)
                .observe(this, o -> refresh());
        LiveDataBus.get().with(RxBusCode.PUSH_LEVEL_COIN_TYPE, LevelCoinType.class)
                .observe(this, this::refresh);

    }

    /**
     * 撤销订单
     */
    private void cancel() {
        TipUtil.getSureTip(getActivity(),App.INSTANCE.getString(R.string.levelcurrentEntrustFragment1),() -> mPresenter.cancelEntrust(slimAdapter.getDataItem(curPos).getOrderId()));

    }


    public void cancelOrderSuccess() {
        userViewModel.update();
        refresh();
    }

    /**
     * 刷新
     */
    public void refresh() {
        if (mPresenter != null && userData != null) {
            mPresenter.getEntrustListRefresh("1","20",code);
         //   mPresenter.getTradeList(stockCode, "1", "20");
        } else {
            showEmpty();
        }
    }

    @Override
    public void onResume() {
        refresh();
        super.onResume();
    }

    /**
     * 显示无数据布局
     */
    public void showEmpty() {
        AndroidSchedulers.mainThread().scheduleDirect(() -> {
            if (includeEmpty != null) {
                includeEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            if (slimAdapter != null) {
                slimAdapter.updateData(new ArrayList<>());
            }
        });
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    public void refresh(LevelCoinType coinType) {
        code = coinType.getCode();
        if (userData == null)
            return;
      //  mPresenter.getTradeList(stockCode, "1", "20");
    }


    public void updateUI(List<LevelHistoryBean.ContentBean> data) {
        if (data==null||data.size() == 0) {
            if (recyclerView == null)
                return;
            recyclerView.setVisibility(View.GONE);
            includeEmpty.setVisibility(View.VISIBLE);
        } else {
            if (recyclerView == null)
                return;
            recyclerView.setVisibility(View.VISIBLE);
            includeEmpty.setVisibility(View.GONE);
            slimAdapter.updateData(data);
        }
    }

    public void cancelReturn(String message) {
        ToastUtil.showShort(message);
        mPresenter.getEntrustListRefresh("1","20",code);
    }
}
