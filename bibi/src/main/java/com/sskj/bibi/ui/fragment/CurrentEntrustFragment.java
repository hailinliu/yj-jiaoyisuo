package com.sskj.bibi.ui.fragment;


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
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.bean.EntrustBean;
import com.sskj.bibi.bean.HistoryBean;
import com.sskj.bibi.component.DaggerUserDataComponent;
import com.sskj.bibi.presenter.CurrentEntrustFragmentPresenter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.DisposUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.CoinUtil;
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

@Route(path = RConfig.BIBI_FRAGMENT_CURRENT_ENTRUST)
public class CurrentEntrustFragment extends BaseFragment<CurrentEntrustFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.includeEmpty)
    View includeEmpty;
    private SlimAdapter slimAdapter;
    @Autowired
    String code;
    private int curPos;
    private MaterialDialog cancelTip;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private Disposable subscribe;

    @Override
    protected int getLayoutId() {
        return R.layout.bibi_fragment_current_entrust;
    }

    @Override
    public CurrentEntrustFragmentPresenter getPresenter() {
        return new CurrentEntrustFragmentPresenter();
    }


    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, userData1 -> {
            if (userData1 != null && userData1.size() > 0) {
                userData = userData1.get(0);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 29))
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 2))
                .setPaintColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider))
        );
        Map<Integer, String> state = new HashMap<>();
        state.put(0, getString(R.string.bibi_ycf));
        state.put(1, getString(R.string.bibi_ycf));
        state.put(2, getString(R.string.bibi_ycf));
        state.put(4, getString(R.string.bibi_wcf));
        slimAdapter = SlimAdapter.create().register(R.layout.bibi_recy_item_current_entrust, new SlimInjector<HistoryBean.ContentBean>() {
            @Override
            public void onInject(HistoryBean.ContentBean data, IViewInjector injector, List payloads) {
                injector.text(R.id.tvOrderType, data.getDirection().equals("BUY")? App.INSTANCE.getString(R.string.bibi_tipBibiUtil1) : App.INSTANCE.getString(R.string.bibi_recordActivity3))
                        .textColor(R.id.tvOrderType, color(data.getDirection().equals("BUY") ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                        .text(R.id.tvCoinType, data.getSymbol())
                        .text(R.id.tvTime, TimeFormatUtil.SF_FORMAT_C.format(new Date(data.getTime())))
                        .text(R.id.tvDeal, App.INSTANCE.getString(R.string.bibi_allEntrustFragment3))
                        .text(R.id.tvTitlePrice, String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment4), data.getBaseSymbol()))
                        .text(R.id.tvTitleNum,  String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment6), data.getCoinSymbol()))
                        .text(R.id.tvTitleDealNum, String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment7), data.getCoinSymbol()))
                        .text(R.id.tvPrice,new BigDecimal(data.getPrice()).stripTrailingZeros().toPlainString())
                        .text(R.id.tvNum,new BigDecimal(data.getAmountStr()).stripTrailingZeros().toPlainString())
                        .text(R.id.tvDealNum, new BigDecimal(data.getTradedAmount()).stripTrailingZeros().toPlainString())
                       // .text(R.id.tvcfj, String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment400), data.getRCode()))
                      //  .text(R.id.tvcfjNum, data.getTouchPrice() + state.get(data.getState()))
                        .clicked(R.id.tvDeal, (v) -> {
                            dealCancel();
                           // curPos = slimAdapter.getData().indexOf(data);
                           /* if (userData.getLawWaring() == 2) {
                                EntrustBean dataItem = (EntrustBean) slimAdapter.getDataItem(curPos);
                                mPresenter.cancelEntrust(dataItem.getId(), dataItem.getTradeType() + "");
                            } else {
                                dealCancel();
                            }*/
                        });
             /*   injector.findViewById(R.id.tvcfj).setVisibility(data.getState() == 4 ? View.VISIBLE : View.GONE);
                injector.findViewById(R.id.tvcfjNum).setVisibility(data.getState() == 4 ? View.VISIBLE : View.GONE);
                injector.textColor(R.id.tvCoinType, getResources().getColor(R.color.libTextContent));*/
            }
        }).attachTo(recyclerView);
        slimAdapter.updateData(new ArrayList());

    }

    /**
     * 切换币种
     *
     * @param coinType
     */
    public void changeCoin(BibiCoinType coinType) {

        code = coinType.getCode();
        if (!TextUtils.isEmpty(code)) {
            refresh();
        }
    }

    /**
     * 刷新
     */
    public void refresh() {
        if (!TextUtils.isEmpty(code)) {
            if (mPresenter != null) {
                mPresenter.getEntrustListRefresh("1","10",code);
            } else {
                showEmpty();
            }
        }
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

    private void dealCancel() {
        if (cancelTip == null) {
            cancelTip = TipUtil.getSureTip(getActivity(), getString(R.string.bibi_cancel_title),getString(R.string.bibi_cancel), () -> {
                HistoryBean.ContentBean dataItem = (HistoryBean.ContentBean) slimAdapter.getDataItem(curPos);
                mPresenter.cancelEntrust(dataItem.getOrderId());

            });

        }
        cancelTip.show();
    }

    private void startTimer() {
        DisposUtil.close(subscribe);
        subscribe = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(i -> {
                    if (mPresenter != null) {
                        LiveDataBus.get().with(RxBusCode.REFRESH_CURRENT_ENTRUST)
                                .postValue(1);
                    }
                },e->{});
    }

    @Override
    public void initData() {
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
            refresh();
        });
        startTimer();
        LiveDataBus.get().with(RxBusCode.BIBI_FRESH).observe(this, o -> refresh());
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN,BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.REFRESH_CURRENT_ENTRUST)
                .observe(this,o -> {
                    mPresenter.getEntrustListRefresh("1","10",code);

                });

    }


    public void updateUI(List<HistoryBean.ContentBean> data) {
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

    public void cancelReturn() {
        LiveDataBus.get().with(RxBusCode.BIBI_FRESH).postValue(1);
        refresh();
    }

    public void noTips() {
        userViewModel.update();
    }
}
