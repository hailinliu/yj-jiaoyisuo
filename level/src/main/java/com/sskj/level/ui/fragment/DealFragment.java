package com.sskj.level.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.BuySellEnum;
import com.sskj.level.bean.ContractNoteBean;
import com.sskj.level.bean.DealLevelBean;
import com.sskj.level.presenter.DealFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.TimeFormatUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
//15036025880

/**
 * 成交
 */
@Route(path = RConfig.LEVEL_FRAGMENT_DEAL)
public class DealFragment extends BaseFragment<DealFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    String code;
    private SlimAdapter<DealLevelBean.ContentBean> slimAdapter;
    private MVCCoolHelper<List<DealLevelBean.ContentBean>> mvcCoolHelper;

    @Override

    protected int getLayoutId() {
        return R.layout.level_fragment_deal;
    }

    @Override
    public DealFragmentPresenter getPresenter() {
        return new DealFragmentPresenter();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && mvcCoolHelper != null) {
            mvcCoolHelper.refresh();
        }
        super.onHiddenChanged(hidden);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {

        ARouter.getInstance().inject(this);
        HashMap<String, String> pcMap = new HashMap<>();
        pcMap.put("0", App.INSTANCE.getString(R.string.leveldealFragment1));
        pcMap.put("1", App.INSTANCE.getString(R.string.leveldealFragment2));
        pcMap.put("2", App.INSTANCE.getString(R.string.leveldealFragment5));
        mPresenter.getData1("1","30",code);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_deal, new SlimInjector<DealLevelBean.ContentBean>() {
            @Override
            public void onInject(DealLevelBean.ContentBean data, IViewInjector injector, List payloads) {
               ImageView imageView = (ImageView) injector.findViewById(R.id.iv_share);
               if(getResources().getConfiguration().locale.getCountry().equals("CN")){
                   imageView.setImageResource(R.mipmap.level_share);
               }else {
                   imageView.setImageResource(R.mipmap.level_share_en);
               }
                injector
                        .textColor(R.id.tv_mm, ContextCompat.getColor(App.INSTANCE, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getColorRes()))
                        .text(R.id.tv_mm, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getTitle())//买入卖出
                        .text(R.id.tv_bz, data.getSymbol().toUpperCase())//币种类型
                        .text(R.id.tvType, data.getType()==0?App.INSTANCE.getString(R.string.bibi_shi_jia):App.INSTANCE.getString(R.string.bibi_limit_price))//市价限价
                        .text(R.id.tv_time, TimeFormatUtil.SF_FORMAT_F.format(new Date(data.getTime())))//时间
                        .text(R.id.level_fd_type, App.INSTANCE.getString(R.string.leveldealFragment6) + data.getClosePositionProfit())//盈亏
                        .background(R.id.level_fd_type,ContextCompat.getDrawable(App.INSTANCE, data.getClosePositionProfit() > 0 ? R.drawable.level_shape_green : R.drawable.level_shape_red))
                        .text(R.id.tvBuyPrice, new BigDecimal(data.getOpenPositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//持仓价
                        .text(R.id.tvSellPrice, new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//最新价
                        .text(R.id.tvLevel, data.getLevel())//杠杆
                        .text(R.id.tvNum, new BigDecimal(data.getCloseAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//成交量
                        .text(R.id.tvEnsureMoney,new BigDecimal(data.getMarginMoney()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//保证金
                        .text(R.id.tvFee, new BigDecimal(data.getFee()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//手续费
                        .text(R.id.tvNightFee,/*new BigDecimal(data.getOvernightFee()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()*/"0")//过夜费
                        .text(R.id.tvWinPrice, data.getStopProfit()==null?"0":new BigDecimal(data.getStopProfit()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//止盈价
                        .text(R.id.tvLossPrice, data.getStopLoss()==null?"0":new BigDecimal(data.getStopLoss()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//止损价
                        .text(R.id.tvPingCang, pcMap.get(String.valueOf(data.getClosePositionType())))//类型.background(R.id.level_fd_type, ContextCompat.getDrawable(App.INSTANCE, Double.valueOf(data.getWinLoseFee()) > 0 ? R.drawable.level_shape_green : R.drawable.level_shape_red))
                ;
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<DealLevelBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<DealLevelBean.ContentBean>)
                page -> mPresenter.getData(page + "", "10", code),10);
        mvcCoolHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcCoolHelper.setDataSource(dataSource);
        mvcCoolHelper.setAdapter(slimAdapter);
        mvcCoolHelper.refresh();

    }

}
