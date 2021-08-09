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
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.bean.EntrustBean;
import com.sskj.bibi.bean.HistoryBean;
import com.sskj.bibi.component.DaggerUserDataComponent;
import com.sskj.bibi.presenter.AllEntrustPresenter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.util.TipUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;


@Route(path = RConfig.BIBI_All_ENTRUST)//全部委托
public class AllEntrustFragment extends BaseFragment<AllEntrustPresenter> {


    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;
    private int curPos;
    private MaterialDialog cancelTip;
    private MVCHelper<List<HistoryBean.ContentBean>> mvcHelper;
    @Autowired
    String qu;
    @Inject
    UserViewModel userViewModel;
    SafeSettingBean userData;
    private String type;
    @Autowired
    String code;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_entrust;
    }

    @Override
    protected AllEntrustPresenter getPresenter() {
        return new AllEntrustPresenter();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        userViewModel.getUsers().observe(this, datas -> {
            if (datas.size() > 0) {
                userData = datas.get(0);
            }
        });

        Map<Integer, String> state = new HashMap<>();
        state.put(0, getString(R.string.bibi_ycf));
        state.put(1,getString(R.string.bibi_ycf));
        state.put(2, getString(R.string.bibi_ycf));
        state.put(4, getString(R.string.bibi_wcf));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                        .setFirstDraw(false)
                        .setLastDraw(false)
                        .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                        .setPaintColor(ContextCompat.getColor(App.INSTANCE, R.color.lib_bg))
        );
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
            }
        }).attachTo(recyclerView);
        slimAdapter.updateData(new ArrayList());
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        ModelRx2DataSource<HistoryBean.ContentBean> refreshDataSource = new ModelRx2DataSource<HistoryBean.ContentBean>((ModelRx2DataSource.OnLoadSource<HistoryBean.ContentBean>) page -> {
            if (TextUtils.isEmpty(SPUtil.get(SPConfig.TOKEN, ""))) {
                return Flowable.just(new ArrayList<HistoryBean.ContentBean>());
            } else {
                return mPresenter.getEntrustList(page + "", "10", code);
            }
        }, 10);
        mvcHelper.setDataSource(refreshDataSource);
        mvcHelper.setAdapter(slimAdapter);
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

    @Override
    public void initData() {
        mvcHelper.refresh();
    }
    public void cancelReturn() {
        LiveDataBus.get().with(RxBusCode.BIBI_FRESH).postValue(1);
        mvcHelper.refresh();
    }

    public void noTips() {
        userViewModel.update();
    }
}
