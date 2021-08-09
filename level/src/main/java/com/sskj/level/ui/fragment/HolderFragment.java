package com.sskj.level.ui.fragment;


import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.BuySellEnum;
import com.sskj.level.bean.HoldLevelHistoryBean.ContentBean;
import com.sskj.level.bean.HistoryBean;
import com.sskj.level.bean.HoldLevelHistoryBean;
import com.sskj.level.bean.HoldeContentBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.HolderFragmentPresenter;
import com.sskj.level.util.CoinUtil;
import com.sskj.level.util.TipLevelUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.util.TipUtil;
import com.sskj.lib.widget.RudessMaterialDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * 持仓界面
 */
@Route(path = RConfig.LEVEL_FRAGMENT_HOLDER)
public class HolderFragment extends BaseFragment<HolderFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter<HoldLevelHistoryBean.ContentBean> slimAdapter;
    private int curPos;
    private MVCCoolHelper<List<HoldLevelHistoryBean.ContentBean>> mvcCoolHelper;
    private MaterialDialog setTip;
    private Disposable subscribe;
    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.level_all_pc)
    TextView levelAllPc;
    @Autowired
    String code;
    private Disposable changePriceDispo;
    private boolean isBuy;

    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_holder;
    }

    @Override
    public HolderFragmentPresenter getPresenter() {
        return new HolderFragmentPresenter();
    }

/*    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && mvcCoolHelper != null) {
            mvcCoolHelper.refresh();
        }
        super.onHiddenChanged(hidden);
    }*/

    @SuppressWarnings("unchecked")
    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        mPresenter.getTradeList(1 + "", "20", code);
        mPresenter.initSocket(code);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_holder, new SlimInjector<ContentBean>() {

            private MaterialDialog dialog;

            @Override
            public void onInject(ContentBean data, IViewInjector injector, List payloads) {
                ImageView imageView = (ImageView) injector.findViewById(R.id.iv_share);
                if(getResources().getConfiguration().locale.getCountry().equals("CN")){
                    imageView.setImageResource(R.mipmap.level_share);
                }else {
                    imageView.setImageResource(R.mipmap.level_share_en);
                }
               // RxBus.getDefault().send(1);
                injector
                        .textColor(R.id.tv_mm, ContextCompat.getColor(App.INSTANCE, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getColorRes()))
                        .text(R.id.tv_mm, BuySellEnum.getByType(data.getDirection().equals("BUY")?"2":"1").getTitle())//买入卖出
                        .text(R.id.tv_bz, data.getSymbol().toUpperCase())//币种类型
                        .text(R.id.tvType, data.getType()==0?App.INSTANCE.getString(R.string.bibi_shi_jia):App.INSTANCE.getString(R.string.bibi_limit_price))//市价限价
                        .text(R.id.tv_time, TimeFormatUtil.SF_FORMAT_F.format(new Date(data.getTime())))//时间
                        .text(R.id.tvLevel, data.getLevel())//杠杆
                        .text(R.id.tvFee, new BigDecimal(data.getFee()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//手续费
                        .text(R.id.tvHoldPrice, new BigDecimal(data.getOpenPositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//持仓价
                        .text(R.id.tvNewPrice, new BigDecimal(data.getMarginMoney()).setScale(2,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//最新价
                        .text(R.id.tvNum, new BigDecimal(data.getAmount()).subtract(new BigDecimal(data.getTradedAmount())).stripTrailingZeros().toPlainString())//数量
                        .text(R.id.tvNightFee,new BigDecimal(data.getOvernightFee()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//过夜费
                        .text(R.id.tvEnsureMoney,new BigDecimal(data.getMarginMoney()).add(new BigDecimal(data.getFee())).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//保证金
                        .text(R.id.tvWinPrice, data.getStopProfit()==null?"0":new BigDecimal(data.getStopProfit()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//止盈价
                        .text(R.id.tvLossPrice, data.getStopLoss()==null?"0":new BigDecimal(data.getStopLoss()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.level_fd_type, App.INSTANCE.getString(R.string.levelholderFragment1) + new BigDecimal(data.getCloseProfit()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())//盈亏
                        .background(R.id.level_fd_type, ContextCompat.getDrawable(App.INSTANCE, Double.parseDouble(data.getCloseProfit()) > 0 ? R.drawable.level_shape_green : R.drawable.level_shape_red))
                        .clicked(R.id.tvPingCang, v -> {
                            boolean isBuy = data.getDirection().equals("BUY");
                            TipLevelUtil.getPingcangTip(getActivity(), isBuy,
                                    data.getClosePositionPrice()+"", new BigDecimal(data.getAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString(), (num) -> mPresenter.pingCang(data.getOrderId(), num));
                        })
                      ;
              TextView tvset =(TextView) injector.findViewById(R.id.tvSet);

              tvset.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      isBuy = data.getDirection().equals("BUY");
                      dialog = new RudessMaterialDialog.Builder(getActivity())
                              .customView(R.layout.level_dialog_set_price, false)
                              .autoDismiss(false)
                              .cancelable(false)
                              .show();
                      View customView = dialog.getCustomView();
                      ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
                      ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                      ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                      ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                      ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                      EditText etStopProfit = customView.findViewById(R.id.etStopProfit);
                      EditText   etStopLoss = customView.findViewById(R.id.etStopLoss);
                      etStopProfit.setFilters(new InputFilter[]{new MoneyValueFilter(CoinUtil.getPriceKeepNum(code))});
                      etStopLoss.setFilters(new InputFilter[]{new MoneyValueFilter(CoinUtil.getPriceKeepNum(code))});
                      TextView tvType = customView.findViewById(R.id.tvType);
                      TextView btCancel = customView.findViewById(R.id.tvCancel);
                      Button btSure = customView.findViewById(R.id.btSure);
                      btCancel.setTextColor(ContextCompat.getColor(App.INSTANCE,R.color.libTextWhite));
                      tvType.setText(isBuy ? App.INSTANCE.getString(R.string.level_zuoduo): App.INSTANCE.getString(R.string.level_zuokong));
                      tvType.setTextColor(ContextCompat.getColor(App.INSTANCE, isBuy ? com.sskj.lib.bean.enums.BuySellEnum.BUY.getColorRes() : com.sskj.lib.bean.enums.BuySellEnum.SELL.getColorRes()));
                      //updatePrice(dialog, newPrice);
                      ClickUtil.click(btCancel, () -> {
                          dialog.dismiss();
                      });
                      ClickUtil.click(btSure,()->{
                          mPresenter.setStop(data.getOrderId()+"",
                                  TextUtils.isEmpty(etStopProfit.getText().toString()) ? "0" : etStopProfit.getText().toString(),
                                  TextUtils.isEmpty(etStopLoss.getText().toString()) ? "0" : etStopLoss.getText().toString());
                          dialog.dismiss();
                      });

                  }

              });
              if(dialog!=null){
                  View customView = dialog.getCustomView();
                  ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
                  ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                  ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                  ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                  ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
                  TextView   tvStopLoss = customView.findViewById(R.id.tvStopLoss);
                  TextView tvStopProfit = customView.findViewById(R.id.tvStopProfit);
                  TextView tvPrice = customView.findViewById(R.id.tvPrice);
                  tvPrice.setText(new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                  if (isBuy) {
                      tvStopProfit.setText("≥" + new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                      tvStopLoss.setText("≤" + new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                  } else {
                      tvStopProfit.setText("≤" + new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                      tvStopLoss.setText("≥" + new BigDecimal(data.getClosePositionPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                  }
                  //updatePrice(dialog, newPrice);


              }

            }

        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<HoldLevelHistoryBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<HoldLevelHistoryBean.ContentBean>)
                page -> mPresenter.getTradeListFlow(page + "", "20", code), 20);

        mvcCoolHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcCoolHelper.setDataSource(dataSource);
        mvcCoolHelper.setAdapter(slimAdapter);
        mvcCoolHelper.refresh();
        //startTimer();
    }


  /*  public void updateUI(List<HoldLevelHistoryBean.ContentBean> data) {

        if (data == null || data.size() == 0) {
            LiveDataBus.get().with(RxBusCode.ALL_PINGCANG_DISMISS).postValue(1);
        } else {
            LiveDataBus.get().with(RxBusCode.ALL_PINGCANG_SHOW).postValue(1);
        }
        if (slimAdapter != null) {
            if (data.size() > 0 && slimAdapter.getData().size() == 0) {
                mvcCoolHelper.refresh();
            }
            slimAdapter.updateData(data);
        }
    }*/


    public void refresh() {
        mvcCoolHelper.refresh();
        mPresenter.initSocket(code);
        /*if (mPresenter != null) {
            mPresenter.getTradeList(1 + "", "20", code);
            mPresenter.initSocket(code);
        }*/
    }



    private MaterialDialog allTip;
    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(levelAllPc, () -> {
            allTip = TipUtil.getSureTip(getActivity(), App.INSTANCE.getString(R.string.leveltradeActivity5), () -> {
                allTip.dismiss();
                mPresenter.getAllList();
            });
            allTip.show();
        });
    }

    @Override
    protected void initData() {
        super.initData();
       // LiveDataBus.get().with(RxBusCode.LEVEL_ALL_PC).observe(this, o -> updatePrice());
        LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN, BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.LEVEL_FRESH)
                .observe(this, o -> refresh());
    }

    private void changeCoin(BibiCoinType bibiCoinType) {
        code = bibiCoinType.getCode();
        refresh();
    }

   /* public void updatePrice() {
        refresh();
    }*/

   /* private void startTimer() {
        DisposUtil.close(subscribe);
        subscribe = Flowable.interval(0, 5, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .subscribe(i -> mPresenter.getTradeList(1 + "", "1000000", code));
    }*/

    @Override
    public void onDestroy() {
        DisposUtil.close(changePriceDispo);
        DisposUtil.close(subscribe);
        super.onDestroy();
    }


    public void pingCangSuccess() {
        ToastUtil.showShort(App.INSTANCE.getString(R.string.level_pingcang_success));
        userViewModel.update();
        mvcCoolHelper.refresh();
        refresh();
    }

    public void setUI(HoldeContentBean bean) {
        List<HoldLevelHistoryBean.ContentBean> list = slimAdapter.getData();
        for(HoldLevelHistoryBean.ContentBean bean1:list){
            if(bean1.getOrderId().equals(bean.getOrderId())){
                bean1.setCloseProfit(bean.getCloseProfit());
             /*   bean1.setAmount(bean.getAmount());
                bean1.setTradedAmount(bean.getTradedAmount());*/
                bean1.setClosePositionPrice(bean.getClosePositionPrice());
            }
        }
        slimAdapter.updateData(list);
    }

    public void closeCode(String msg) {
        LiveDataBus.get().with(RxBusCode.LEVEL_ALL_PC).postValue(1);
        mvcCoolHelper.refresh();
        if(msg.contains("成功")||msg.contains("success")){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.level_success));
        }
       // ToastUtil.showShort(msg);
    }
}
