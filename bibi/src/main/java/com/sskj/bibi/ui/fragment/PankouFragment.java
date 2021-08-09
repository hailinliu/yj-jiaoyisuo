package com.sskj.bibi.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.presenter.PankouFragmentPresenter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//@Route(path = RConfig.BIBI_FRAGMENT_PANKOU)
public class PankouFragment extends BaseFragment<PankouFragmentPresenter> {
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvRMBPrice)
    TextView tvRMBPrice;
    @BindView(R2.id.tvRate)
    TextView tvRate;
    @BindView(R2.id.tvBuyTitle)
    TextView tvBuyTitle;
    @BindView(R2.id.tvSellTitle)
    TextView tvSellTitle;
    @BindView(R2.id.tvNumTitle)
    TextView tvNumTitle;
    @BindView(R2.id.buyRecyclerView)
    RecyclerView buyRecyclerView;
    @BindView(R2.id.sellRecyclerView)
    RecyclerView sellRecyclerView;
    @Autowired
    String code;
    @Autowired
    String qu;
    private SlimAdapter buySlimAdapter;
    private SlimAdapter sellSlimAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.bibi_fragment_pankou;
    }

    @Override
    public PankouFragmentPresenter getPresenter() {
        return new PankouFragmentPresenter();
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
     /*   buyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buySlimAdapter = SlimAdapter.create().register(R.layout.bibi_recy_item_pankou_buy, new SlimInjector<WSFiveBean.FiveBean>() {
            @Override
            public void onInject(WSFiveBean.FiveBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvPrice,data.getPrice())
                        .text(R.id.tvNum, data.getTotalSize())
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, R.color.bibiGreen))
                ;
            }
        });
        buySlimAdapter.attachTo(buyRecyclerView).updateData(new ArrayList());
        sellSlimAdapter = SlimAdapter.create().register(R.layout.bibi_recy_item_pankou_sell, new SlimInjector<WSFiveBean.FiveBean>() {
            @Override
            public void onInject(WSFiveBean.FiveBean data, IViewInjector injector, List payloads) {
                injector
                        .text(R.id.tvPrice, data.getPrice())
                        .text(R.id.tvNum, data.getTotalSize())
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, R.color.bibiRed))

                ;
            }
        });
        sellSlimAdapter.attachTo(sellRecyclerView).updateData(new ArrayList());*/
    }

    @Override
    public void initData() {
        changeCoin(new BibiCoinType(code));
        mPresenter.getProduct(qu, code);
        LiveDataBus.get().with(RxBusCode.BIBI_FRESH).observe(this, o -> refresh());
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN,BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN,CoinBean.class).observe(this, this::refreshCoin);


    }

    /**
     * 设置买五卖五UI --
     */
    public void resetFiveUI() {
      /*  WSFiveBean1 wsFiveBean = new WSFiveBean1();
        wsFiveBean.setCode(code);
        ArrayList<WSFiveBean1.ItemsBean> asks = new ArrayList<>();
       // ArrayList<WSFiveBean.FiveBean> bids = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            asks.add(new WSFiveBean1.ItemsBean(0,0,null, null));
           // bids.add(new WSFiveBean.FiveBean(null, null));
        }
        wsFiveBean.setItems(asks);
       // wsFiveBean.setBids(bids);
        updateFive(wsFiveBean);*/
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        mPresenter.getPankou(code);
    }

    public void updateFive(WSFiveBean1 wsFiveBean) {
//        if (wsFiveBean != null && wsFiveBean.getSymbol() != null) {
//            if (wsFiveBean.getSymbol().equals(code)) {
//                if(wsFiveBean.getDirection()==0){
//                    buySlimAdapter.updateData(wsFiveBean.getItems());
//                }else if(wsFiveBean.getDirection()==1){
//                    sellSlimAdapter.updateData(wsFiveBean.getItems());
//                }
//
//
//            }
//        }
    }

    public void changeCoin(BibiCoinType bibiCoinType) {
        code = bibiCoinType.getCode();
        tvBuyTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_pankouFragment1), code.replace("_", "/").split("/")[1]));
        tvSellTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_pankouFragment2), code.replace("_", "/").split("/")[1]));
        tvNumTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_pankouFragment3), code.replace("_", "/").split("/")[0]));
        resetFiveUI();
        mPresenter.getProduct(qu, code);
        mPresenter.getPankou(code);
    }

    public void refreshCoin(CoinBean coinBean) {
        if (code.equals(coinBean.getCode())) {
            updateTitle(coinBean);
        }
    }

    public void updateTitle(CoinBean stockCoinBean) {
        if (tvPrice == null)
            return;
        try {
            tvPrice.setText(CoinUtil.keepCoinPrice(code, stockCoinBean.getPrice()));
            tvRMBPrice.setText(String.format("≈%sUSDT", stockCoinBean.getCnyPrice()));
            tvRate.setText(stockCoinBean.getChangeRate());
            tvRate.setTextColor(ContextCompat.getColor(App.INSTANCE, Double.valueOf(stockCoinBean.getChange()) >= 0 ? R.color.bibiGreen : R.color.bibiRed));
            tvPrice.setTextColor(ContextCompat.getColor(App.INSTANCE, Double.valueOf(stockCoinBean.getChange()) >= 0 ? R.color.bibiGreen : R.color.bibiRed));
        } catch (Exception e) {

        }

    }


}
