package com.sskj.fabi.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.PublishRecordBean;
import com.sskj.fabi.presenter.PublishRecordFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.enums.FabiBuySellEnum;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TipUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.FABI_FRAGMENT_PUBLISH_RECORD)
public class PublishRecordFragment extends BaseFragment<PublishRecordFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;

    String tradeType;
    private MVCCoolHelper<List<PublishRecordBean>> mvcHelper;
    private SlimAdapter slimAdapter;
    private MaterialDialog cancelTip;
    private int curPos;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_publish_record;
    }

    @Override
    public PublishRecordFragmentPresenter getPresenter() {
        return new PublishRecordFragmentPresenter();
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("1", App.INSTANCE.getString(R.string.fabipublishRecordFragment1));
        statusMap.put("2", App.INSTANCE.getString(R.string.fabipublishRecordFragment2));
        statusMap.put("3", App.INSTANCE.getString(R.string.fabipublishRecordFragment3));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.fabi_recy_item_order_record, new SlimInjector<PublishRecordBean>() {
            @Override
            public void onInject(PublishRecordBean data, IViewInjector injector, List payloads) {
              /*  injector
                        .text(R.id.tvStatus, statusMap.get(data.getStatus() + ""))
                        .text(R.id.tvTotalPrice, String.format("¥%s", data.getTotalPrice()))
                        .text(R.id.tvBuySellType, data.getTradeType() == 1 ?
                                FabiBuySellEnum.BUY.getTitle() :
                                FabiBuySellEnum.SELL.getTitle())
                        .textColor(R.id.tvBuySellType,
                                ContextCompat.getColor(App.INSTANCE, data.getTradeType() == 1 ?
                                        FabiBuySellEnum.BUY.getColor() :
                                        FabiBuySellEnum.SELL.getColor()))
                        .text(R.id.tvLimit, String.format("%s-%s ", data.getMinPrice(), data.getMaxPrice()))
                        .text(R.id.tvPrice, "¥" + data.getPrice())
                        .text(R.id.tvNum, data.getDealsRemainNum()+ " USDT")
                        .text(R.id.tvTime, data.getCreateTime())
                        .visibility(R.id.ivPayTypeAlipay, data.getPayType().contains(PayTypeEnum.ALIPAY.getType()) ? View.VISIBLE : View.GONE)
                        .visibility(R.id.ivPayType, data.getPayType().contains(PayTypeEnum.WX.getType()) ? View.VISIBLE : View.GONE)
                        .visibility(R.id.ivPayTypeBank, data.getPayType().contains(PayTypeEnum.BANK.getType()) ? View.VISIBLE : View.GONE)
                        .clicked(R.id.tvStatus, (v) -> {
                            if (data.getStatus().equals("1")) {
                                curPos = slimAdapter.getData().indexOf(data);
                                cancelOrder();
                            }
                        })
                ;*/

            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<PublishRecordBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<PublishRecordBean>)
                page -> mPresenter.getData(page + "", "20", tradeType), 20);
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    @Override
    protected void initData() {
        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_PUBLISH_RECORD_TYPE, Integer.class)
                .observe(this, integer -> {
                    tradeType = integer + "";
                    if (integer == 0) {
                        tradeType = null;
                    }
                    mvcHelper.refresh();
                });

    }

    private void cancelOrder() {
        if (cancelTip == null) {
            cancelTip = TipUtil.getSureTip(getActivity(), App.INSTANCE.getString(R.string.fabipublishRecordFragment4), () -> {
                cancelTip.dismiss();
                PublishRecordBean dataItem = (PublishRecordBean) slimAdapter.getDataItem(curPos);
                mPresenter.cancelPublishOrder(dataItem.getTradeType() + "", dataItem.getId());
            });
        } else {
            cancelTip.show();
        }

    }

    public void cancelSuccess() {
        mvcHelper.refresh();
    }
}
