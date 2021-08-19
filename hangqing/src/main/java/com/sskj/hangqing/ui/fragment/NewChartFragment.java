package com.sskj.hangqing.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.tifezh.kchartlib.chart.BaseKChartAdapter;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.depth.bean.DepthData;
import com.sskj.depth.bean.IDepthData;
import com.sskj.depth.view.DepthMapView;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.bean.rxbus.MarketCoinBean;
import com.sskj.hangqing.presenter.ChartFragmentPresenter;
import com.sskj.hangqing.presenter.NewChartFragmentPresenter;
import com.sskj.hangqing.util.DataUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.widget.KChartMorePop;
import com.sskj.lib.widget.KChartPop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.NEWCHARTFRAGMENT)
public class NewChartFragment extends BaseFragment<NewChartFragmentPresenter> {
    private static final long MAX_NUM = 30;
    @BindView(R2.id.rl_deep)
    RelativeLayout rl_deep;
    private String newPrice;
    private boolean isFirst = true;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_chart;
    }
    String[] goodsType = {"minute", "minute15", "minute60","hour4", "day","minute", "minute5", "minute30", "week", "month"};
    private boolean isUpToggle = true;
    private boolean isDownToggle = true;
    private KChartPop kChartPop;
    private KChartMorePop kChartMorePop;
    @Override
    public NewChartFragmentPresenter getPresenter() {
        return new NewChartFragmentPresenter();
    }
    @BindView(R2.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    @BindView(R2.id.tvIndicator)
    TextView tvIndicator;
    @BindView(R2.id.tv_shendu)
    TextView tvShendu;
    @BindView(R2.id.tvMore)
    TextView tvMore;
    @BindView(R2.id.llMore)
    LinearLayout llMore;
    @BindView(R2.id.kChartView)
    KChartView kChartView;
    private NewChartFragment.KChartAdapter mAdapter;
    @Autowired
    String code;
    @Autowired
    int type;
    @Autowired
    String goodType;
    @Autowired
    boolean isLevel;
    @Autowired
    boolean isCandle = true;
    @BindView(R2.id.depthMapView)
    DepthMapView depthMapView;
    private String currenttype ="1";
    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new TabItem(App.INSTANCE.getString(R.string.hangqingchartFragment1), 0, 0));
        customTabEntities.add(new TabItem("15M", 0, 0));
        customTabEntities.add(new TabItem("30M", 0, 0));
        customTabEntities.add(new TabItem("1H", 0, 0));
        // customTabEntities.add(new TabItem("30M", 0, 0));
        customTabEntities.add(new TabItem(App.INSTANCE.getString(R.string.hangqingchartFragment2), 0, 0));
        customTabEntities.add(new TabItem("", 0, 0));
        commonTabLayout.setTabData(customTabEntities);
/*commonTabLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rl_deep.setVisibility(View.GONE);
        kChartView.setVisibility(View.VISIBLE);
    }
});*/
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                rl_deep.setVisibility(View.GONE);
                tvIndicator.setClickable(true);
                tvIndicator.setEnabled(true);
                kChartView.setVisibility(View.VISIBLE);
                tabSelect(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initKChart();
        showKLoading();
        // mPresenter.getKData(System.currentTimeMillis() - 2*24 * 60 * 60 * 1000L, "1", code, System.currentTimeMillis()- 24 * 60 * 60 * 1000L);
        mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"1",code,System.currentTimeMillis());
        commonTabLayout.setCurrentTab(0);
        tabSelect(0);
    }

    @Override
    protected void initData() {
        LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class)
                .observe(this, this::refreshCoin);
        LiveDataBus.get().with(RxBusCode.NEW_LEVEL_HANG, WSFiveBean.class).observe(this,this::updateUI);
       /* if(type==1){
            LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class)
                    .observe(this, this::refreshCoin);
        }else {
            LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN, NewChartBean.class)
                    .observe(this, this::refreshCoin);
        }
        LiveDataBus.get().with(RxBusCode.CHANGE_MARKET_CODE, MarketCoinBean.class)
                .observe(this, this::changeCoin);*/
        // mPresenter.getData(code,type);
       // mPresenter.getPankou(code);
        mPresenter.initSocket(code,"");
        //mPresenter.initSocket(code);
    }

    private void tabSelect(int position) {
        showKLoading();
        if (position != 5) {
            goodType = goodsType[position];
        }
        // kChartView.setDrawMinuteStyle(false);
        if (position == 0) {
            kChartView.setDrawMinuteStyle(true);
            // mPresenter.getKData(System.currentTimeMillis() - 2*24 * 60 * 60 * 1000L, "1", code, System.currentTimeMillis()- 24 * 60 * 60 * 1000L);
            mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"1",code,System.currentTimeMillis());
            mPresenter.initSocket(code,"");
        } else {
            kChartView.setDrawMinuteStyle(false);
        }
        if(position==1){
            mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"15",code,System.currentTimeMillis());
            currenttype= "15";
            mPresenter.initSocket(code,"15min");
        }
        if(position==3){
            mPresenter.getKData(System.currentTimeMillis()-3*24*60*60*1000L,"60",code,System.currentTimeMillis());
            currenttype= "1h";
            mPresenter.initSocket(code,"60min");
        }
        if(position==2){
            mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"30",code,System.currentTimeMillis());
            currenttype= "30";
            mPresenter.initSocket(code,"30min");
           /* mPresenter.getKData(System.currentTimeMillis()-10*24*60*60*1000L,"4h",code,System.currentTimeMillis());
            currenttype= "4h";
            mPresenter.initSocket(code,"4hour");*/
        }
        if(position==4){
            mPresenter.getKData(System.currentTimeMillis()-54*24*60*60*1000L,"1d",code,System.currentTimeMillis());
            currenttype= "1d";
            mPresenter.initSocket(code,"1day");
        }
        if (position < 5) {
            tvMore.setText(App.INSTANCE.getString(R.string.hangqingchartFragment3));
        }
        // mPresenter.getKData(1604200901000L,"1",code,1606792902000L);
    }

    public void showKLoading() {
        mPresenter.cancelRequest();
        if (kChartView != null) {
            kChartView.showLoading();
        }
    }

    public void hideKLoading() {
        if (kChartView != null) {
            kChartView.refreshComplete();
        }
    }

    @Override
    protected void initEvent() {
        ClickUtil.click(tvShendu,()->{
            rl_deep.setVisibility(View.VISIBLE);
            tvIndicator.setClickable(false);
            tvIndicator.setEnabled(false);
            kChartView.setVisibility(View.GONE);
        });
        ClickUtil.click(200, llMore, () -> {
            if (kChartMorePop == null) {
                tvIndicator.setClickable(true);
                tvIndicator.setEnabled(true);
                kChartMorePop = new KChartMorePop(getActivity());
                View contentView = kChartMorePop.getContentView();
                //  View tvOne = contentView.findViewById(R.id.tvOne);
                View tvFive = contentView.findViewById(R.id.tvFive);
                View tvThirty = contentView.findViewById(R.id.tvThirty);
               // View tvThirty1 = contentView.findViewById(R.id.tvThirty1);
                View tvWeek = contentView.findViewById(R.id.tvWeek);
                View tvMonth = contentView.findViewById(R.id.tvMonth);

                ClickUtil.click(tvFive, () -> {
                    goodType = goodsType[5];
                    commonTabLayout.setCurrentTab(5);
                    tabSelect(5);
                    //mPresenter.getKData(System.currentTimeMillis() - 2*24 * 60 * 60 * 1000L, "1", code, System.currentTimeMillis()- 24 * 60 * 60 * 1000L);
                    mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"1",code,System.currentTimeMillis());
                    currenttype= "1";
                    tvMore.setText("1M");
                    mPresenter.initSocket(code,"");
                    kChartMorePop.dismiss();
                });
                ClickUtil.click(tvThirty, () -> {
                    goodType = goodsType[6];
                    tabSelect(6);
                    mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"5",code,System.currentTimeMillis());
                    commonTabLayout.setCurrentTab(5);
                    currenttype= "5";
                    tvMore.setText("5M");
                    mPresenter.initSocket(code,"5min");
                    kChartMorePop.dismiss();
                });
               /* ClickUtil.click(tvThirty1, () -> {
                    goodType = goodsType[7];
                    tabSelect(7);
                    mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"30",code,System.currentTimeMillis());
                    commonTabLayout.setCurrentTab(5);
                    currenttype= "30";
                    tvMore.setText("30M");
                    mPresenter.initSocket(code,"30min");
                    kChartMorePop.dismiss();
                });*/
                ClickUtil.click(tvWeek, () -> {
                    goodType = goodsType[7];
                    tabSelect(7);
                    mPresenter.getKData(System.currentTimeMillis()-20*7*24*60*60*1000L,"1w",code,System.currentTimeMillis());
                    commonTabLayout.setCurrentTab(5);
                    currenttype= "1w";
                    tvMore.setText(App.INSTANCE.getString(R.string.hangqingchartFragment4));
                    mPresenter.initSocket(code,"1week");
                    kChartMorePop.dismiss();
                });
                ClickUtil.click(tvMonth, () -> {
                    goodType = goodsType[8];
                    tabSelect(8);
                    mPresenter.getKData(System.currentTimeMillis()-300*7*24*60*60*1000L,"1m",code,System.currentTimeMillis());
                    currenttype= "1m";
                    mPresenter.initSocket(code,"1month");
                    commonTabLayout.setCurrentTab(5);
                    tvMore.setText(App.INSTANCE.getString(R.string.hangqingchartFragment5));
                    kChartMorePop.dismiss();
                });
                kChartMorePop.setBackground(0);
            }
            if (kChartMorePop.isShowing()) {
                kChartMorePop.dismiss();
            } else {
                kChartMorePop.showPopupWindow(commonTabLayout);
            }
        });
        try {
            ClickUtil.click(200, tvIndicator, () -> {
                if (kChartPop == null) {
                    kChartPop = new KChartPop(getActivity());
                    View contentView = kChartPop.getContentView();
                    TextView tvMa = contentView.findViewById(R.id.tvMa);
                    TextView tvBoll = contentView.findViewById(R.id.tvBoll);
                    TextView tvMacd = contentView.findViewById(R.id.tvMacd);
                    TextView tvKdj = contentView.findViewById(R.id.tvKdj);
                    TextView tvRsi = contentView.findViewById(R.id.tvRsi);
                    TextView tvWr = contentView.findViewById(R.id.tvWr);
                    ImageView ivUpToggle = contentView.findViewById(R.id.ivUpToggle);
                    ImageView ivDownToggle = contentView.findViewById(R.id.ivDownToggle);
                    ivUpToggle.setImageResource(R.mipmap.hang_icon_show);
                    ivDownToggle.setImageResource(R.mipmap.hang_icon_show);
                    ClickUtil.click(tvMa, () -> {
                        kChartView.setMainDrawMaShow();
                        ivUpToggle.setImageResource(R.mipmap.hang_icon_show);
                        kChartPop.dismiss();
                    });
                    ClickUtil.click(tvBoll, () -> {
                        kChartView.setMainDrawBollShow();
                        ivUpToggle.setImageResource(R.mipmap.hang_icon_show);
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(ivUpToggle, () -> {
                        if (isUpToggle) {
                            isUpToggle = false;
                            ivUpToggle.setImageResource(R.mipmap.hang_icon_hide);

                            kChartView.setMainDrawNoneShow();
                        } else {
                            isUpToggle = true;
                            ivUpToggle.setImageResource(R.mipmap.hang_icon_show);
                            kChartView.setMainDrawMaShow();
                        }
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(tvMacd, () -> {
                        kChartView.changeMACD();
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(tvKdj, () -> {
                        kChartView.changeKDJ();
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(tvRsi, () -> {
                        kChartView.changeRSI();
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(tvWr, () -> {
                        kChartView.changeWR();
                        kChartPop.dismiss();

                    });
                    ClickUtil.click(ivDownToggle, () -> {
                        if (isDownToggle) {
                            isDownToggle = false;
                            ivDownToggle.setImageResource(R.mipmap.hang_icon_hide);

                            kChartView.setDrawDown(isDownToggle);
                        } else {
                            isDownToggle = true;
                            ivDownToggle.setImageResource(R.mipmap.hang_icon_show);

                            kChartView.setDrawDown(isDownToggle);
                        }
                        kChartPop.dismiss();

                    });
                    kChartPop.setBackground(0);
                }
                if (kChartPop.isShowing()) {
                    kChartPop.dismiss();
                } else {
                    kChartPop.showPopupWindow(commonTabLayout);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initKChart() {
        View view = getLayoutInflater().inflate(R.layout.lib_mvc_loading_view, null, false);
        FrameLayout frameLayoutLoading = view.findViewById(R.id.frameLayoutLoading);
        ImageView ivLoading = view.findViewById(R.id.ivLoading);
        TextView loadingText = view.findViewById(R.id.loadingText);
        // GifImageView ivLoading = loadingView.findViewById(com.sskj.lib.R.id.ivLoading);

        // ImageView ivLoading = loadingView.findViewById(R.id.ivLoading);

//            ImageUtil.setImage(R.mipmap.lib_loading,ivLoading);
        RequestOptions ob = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);
        Glide.with(getActivity())
                .load(R.mipmap.lib_loading)
                .apply(ob)
                .into(ivLoading);
        loadingText.setVisibility(View.GONE);
        frameLayoutLoading.setBackgroundColor(ContextCompat.getColor(App.INSTANCE, R.color.lib_bg));
        // ImageUtil.setImage(R.mipmap.lib_loading, ivLoading);
        kChartView.setDrawDownBaseLineZoreUp(false);
        kChartView.setProgressView(view);
        kChartView.showLoading();
        kChartView.setVolumeMaGone(false);
        kChartView.setDrawTabView(false);
        kChartView.setGridRows(3);
        kChartView.setRedUpAndGreenDown(false);
        kChartView.setGridColumns(4);
        kChartView.setMinuteLineWidth(4);
        kChartView.setDrawDown(true);
        kChartView.setPointWidth(RudenessScreenHelper.pt2px(App.INSTANCE, 14));
        kChartView.setGridLineColor(Color.TRANSPARENT);
        kChartView.getmCurrentPricePaint().setTextSize(20);
        kChartView.getmLabelPaint().setColor(ContextCompat.getColor(App.INSTANCE, R.color.libApp));
        kChartView.setShader(Color.parseColor("#306185e5"), Color.parseColor("#106185e5"), Color.parseColor("#026185e5"), 1000);
        kChartView.getmCurrentLinePaint().setColor(getResources().getColor(R.color.libApp));
        kChartView.getmCurrentLinePaint().setPathEffect(null);
        kChartView.getmCurrentPricePaint().setColor(getResources().getColor(R.color.libApp));
        kChartView.setBreathColor(getResources().getColor(R.color.libApp));
        kChartView.setValueFormatter(v ->
                new BigDecimal(CoinUtil.keepCoinPrice(code, v + "")).setScale(CoinUtil.getPriceKeepNum(code), RoundingMode.DOWN).toPlainString()
        );
        if (!isCandle) {
            kChartView.setDrawMinuteStyle(true);
        } else {
        }
        kChartView.setDateTimeFormatter(new DateFormatter() {
            @Override
            public String format(Date date) {
                String s = TimeFormatUtil.SF_FORMAT_G.format(date);
                return s;
            }
        });
        kChartView.getmVolumeDraw().setValueFormatter(v ->
                new BigDecimal(CoinUtil.keepCoinNum(code, v + "")).setScale(CoinUtil.getNumKeepNum(code), RoundingMode.DOWN).toPlainString()
        );
        mAdapter = new NewChartFragment.KChartAdapter();
        kChartView.setAdapter(mAdapter);

    }


    public void setChart(List<Stock> stockList) {

        if (kChartView != null) {
            kChartView.hideLoading();
            if (stockList == null) {
                stockList = new ArrayList<>();
            }
            //  Collections.reverse(stockList);


            if (!TextUtils.isEmpty(newPrice)) {
                if (stockList.size() > 0) {
                    stockList.get(stockList.size() - 1).setClosePrice(Float.valueOf(newPrice));
                }
            }
            DataUtil.calculate(stockList);
            // mAdapter.getDatas().clear();
            mAdapter.updateData(stockList);
        }
    }


    public void changeCoin(MarketCoinBean marketCoinBean) {
        code = marketCoinBean.getCode();
        if (mPresenter != null) {
            mPresenter.getKData(System.currentTimeMillis()-24*60*60*1000L,"1",code,System.currentTimeMillis());
        }
    }

    @Override
    public void onDestroy() {
        kChartView.closeObservable();
        kChartView = null;
        super.onDestroy();

    }
    public void refreshCoin(NewChartBean data) {
        if (data.getSymbol().equals(code) && mAdapter.getCount() > 0) {
            newPrice = data.getClosePrice()+"";
            if (isLastNewData(data)) {
                List<Stock> list = mAdapter.getDatas();
                Stock stock = list.get(mAdapter.getCount() - 1);
                stock.setLowestPrice(Math.min(stock.getLowestPrice(), data.getClosePrice()));
                stock.setHighestPrice(Math.max(stock.getHighestPrice(), data.getClosePrice()));
                stock.setClosePrice(data.getClosePrice());
                stock.setOpenPrice(data.getOpenPrice());
                stock.setVolume(data.getVolume());
                stock.setTime(mAdapter.getDatas().get(mAdapter.getCount()-1).getTime());
                list.set(mAdapter.getCount() - 1,stock);
                mAdapter.updateData(list);
                DataUtil.calculate(mAdapter.getDatas());
            } else {
                Stock stock = new Stock();
                stock.setClosePrice(data.getClosePrice());
                stock.setLowestPrice(data.getLowestPrice());
                stock.setHighestPrice(data.getHighestPrice());
                stock.setOpenPrice(data.getOpenPrice());
                stock.setVolume(data.getVolume());
                switch (mAdapter.getDatas().get(mAdapter.getCount()-1).getPeriod()){
                    case "1min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+60*1000));
                        break;
                    case "5min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+5*60*1000));
                        break;
                    case "15min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+15*60*1000));
                        break;
                    case "30min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+30*60*1000));
                        break;
                    case "60min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+60*60*1000));
                        break;
                    case "4hour":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+4*60*60*1000));
                        break;
                    case "1day":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+24*60*60*1000));
                        break;
                    case "1week":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+7*24*60*60*1000));
                        break;
                    case "1month":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+30*24*60*60*1000));
                        break;
                }

                mAdapter.getDatas().add(stock);
                DataUtil.calculate(mAdapter.getDatas());

            }
        }
    }

    private boolean isLastNewData(NewChartBean data) {

        long compareTime = data.getTime();
        return isSameTime(compareTime);

    }

    private boolean isSameTime(long compareTime) {
        long dividerTime = 1;
        switch (goodType) {
            case "minute":
                dividerTime = 1000 * 60;
                break;
            case "minute5":
                dividerTime = 1000 * 60 * 5;
                break;
            case "minute15":
                dividerTime = 1000 * 60 * 15;
                break;
            case "minute30":
                dividerTime = 1000 * 60 * 30;
                break;
            case "minute60":
                dividerTime = 1000 * 60 * 60;
                break;
            case "day":
                dividerTime = 1000 * 60 * 60 * 24;
                break;
            default:
                break;
        }
        return compareTime / dividerTime == mAdapter.getDatas().get(mAdapter.getCount() - 1).getDate()/dividerTime;
    }
    private Flowable<List<IDepthData>> sellFlow;
    private Flowable<List<IDepthData>> buyFlow;
   /* public void updateUI(AskBean bean) {
        double num =0.00;
        for(AskBean.ItemsBean data:bean.getItems()){
            num = new BigDecimal(data.getAmountStr()).add(new BigDecimal(num)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().doubleValue();
            data.setAmountStr(String.valueOf(num));
        }
        sellFlow = Flowable.fromIterable(bean.getItems())
                .map(fiveBean -> (IDepthData)new DepthData(fiveBean.getAmountStr(), fiveBean.getPriceStr()))
                .toList()
                .toFlowable();
    }*/

    @SuppressLint("CheckResult")
    public void updateUI(WSFiveBean bean1) {
        if(bean1.getBids()!=null&&bean1.getBids().size()>0){
            double num =0.00;
            for(WSFiveBean.FiveBean data:bean1.getBids()){
                num = new BigDecimal(data.getAmount()).add(new BigDecimal(num)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().doubleValue();
                data.setAmount(String.valueOf(num));
            }
            buyFlow = Flowable.fromIterable(bean1.getBids())
                    .map(fiveBean -> (IDepthData)new DepthData(fiveBean.getAmount(),String.valueOf(fiveBean.getPrice())))
                    .toList()
                    .toFlowable();
        }
        if(bean1.getAsks()!=null&&bean1.getAsks().size()>0){
            double num =0.00;
            for(WSFiveBean.FiveBean data:bean1.getAsks()){
                num = new BigDecimal(data.getAmount()).add(new BigDecimal(num)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().doubleValue();
                data.setAmount(String.valueOf(num));
            }
            sellFlow = Flowable.fromIterable(bean1.getAsks())
                    .map(fiveBean -> (IDepthData)new DepthData(fiveBean.getAmount(), fiveBean.getPrice()))
                    .toList()
                    .toFlowable();
        }

        Flowable.zip(buyFlow, sellFlow, (buyData, sellData) -> {
            if (depthMapView != null) {
                if (code.toUpperCase().contains("BNR")) {
                    depthMapView.mPriceLimit = 6;
                    depthMapView.mVolumeLimit = 6;
                } else {
                    depthMapView.mPriceLimit = 4;
                    depthMapView.mVolumeLimit = 4;
                }
                depthMapView.setData(buyData, sellData);
            }
            return "1";
        }).subscribe(s -> {
        }, e -> {
            System.out.println(e);
        });
    }

    public void refreshCoin(String payload) {
        Gson gson = new Gson();
        NewChartBean data = gson.fromJson(payload,NewChartBean.class);
        if (data.getSymbol().equals(code) && mAdapter.getCount() > 0) {
            newPrice = data.getClosePrice()+"";
            if (isLastNewData(data)) {
                List<Stock> list = mAdapter.getDatas();
                Stock stock = list.get(mAdapter.getCount() - 1);
                stock.setLowestPrice(Math.min(stock.getLowestPrice(), data.getClosePrice()));
                stock.setHighestPrice(Math.max(stock.getHighestPrice(), data.getClosePrice()));
                stock.setClosePrice(data.getClosePrice());
                stock.setOpenPrice(data.getOpenPrice());
                stock.setVolume(data.getVolume());
                stock.setTime(mAdapter.getDatas().get(mAdapter.getCount()-1).getTime());
                list.set(mAdapter.getCount() - 1,stock);
                mAdapter.updateData(list);
                DataUtil.calculate(mAdapter.getDatas());
            } else {
                Stock stock = new Stock();
                stock.setClosePrice(data.getClosePrice());
                stock.setLowestPrice(data.getLowestPrice());
                stock.setHighestPrice(data.getHighestPrice());
                stock.setOpenPrice(data.getOpenPrice());
                stock.setVolume(data.getVolume());
                switch (mAdapter.getDatas().get(mAdapter.getCount()-1).getPeriod()){
                    case "1min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+60*1000));
                        break;
                    case "5min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+5*60*1000));
                        break;
                    case "15min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+15*60*1000));
                        break;
                    case "30min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+30*60*1000));
                        break;
                    case "60min":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+60*60*1000));
                        break;
                    case "4hour":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+4*60*60*1000));
                        break;
                    case "1day":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+24*60*60*1000));
                        break;
                    case "1week":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+7*24*60*60*1000));
                        break;
                    case "1month":
                        stock.setTime((mAdapter.getDatas().get(mAdapter.getCount()-1).getTime()+30*24*60*60*1000));
                        break;
                }

                mAdapter.getDatas().add(stock);
                DataUtil.calculate(mAdapter.getDatas());

            }
        }
    }

    /*public void updateUI(WSFiveBean data) {
        if (data == null)
            return;
        WSFiveBean wsFiveBean = new WSFiveBean();

        ArrayList<WSFiveBean.FiveBean> buyFiveBean = new ArrayList<>();
        ArrayList<WSFiveBean.FiveBean> sellFiveBean = new ArrayList<>();
        wsFiveBean.setCode(code);
        wsFiveBean.setBids(buyFiveBean);
        wsFiveBean.setAsks(sellFiveBean);
        Flowable.fromIterable(data.getBids())
                .take(MAX_NUM)
                .map(fiveBean -> buyFiveBean.add(new WSFiveBean.FiveBean(fiveBean.getDoubleTotalSize(), fiveBean.getPrice(), code)))
                .subscribe();
        Flowable.fromIterable(data.getAsks())
                .take(MAX_NUM)
                .map(fiveBean -> sellFiveBean.add(new WSFiveBean.FiveBean(fiveBean.getDoubleTotalSize(), fiveBean.getPrice(), code)))
                .subscribe();
        calculatePercentAll(wsFiveBean);
     //   buySlimAdapter.updateData(buyFiveBean);
       // sellSlimAdapter.updateData(sellFiveBean);
        Collections.reverse(data.getAsks());
        Collections.reverse(data.getBids());
        Flowable<List<IDepthData>> buyFlow = Flowable.fromIterable(data.getBids())
                .map(fiveBean -> (IDepthData) new DepthData(fiveBean.getDoubleTotalSize() + "", fiveBean.getPrice()))
                .toList()
                .toFlowable();
        Flowable<List<IDepthData>> sellFlow = Flowable.fromIterable(data.getAsks())
                .map(fiveBean -> (IDepthData) new DepthData(fiveBean.getDoubleTotalSize() + "", fiveBean.getPrice()))
                .toList()
                .toFlowable();

        Flowable.zip(buyFlow, sellFlow, (buyData, sellData) -> {
            if (depthMapView == null) {
                return "0";
            }
            depthMapView.setData(buyData, sellData);
            return "1";
        }).subscribe();
    }*/
   /* private void calculatePercentAll(WSFiveBean wsFiveBean) {
        Double full = 0d;
        for (WSFiveBean.FiveBean bid : wsFiveBean.getBids()) {
            Double aDouble = Double.valueOf(bid.getDoubleTotalSize());
            if (aDouble > full) {
                full = aDouble;
            }
        }
        for (WSFiveBean.FiveBean ask : wsFiveBean.getAsks()) {
            Double aDouble = Double.valueOf(ask.getDoubleTotalSize());
            if (aDouble > full) {
                full = aDouble;
            }
        }
        for (WSFiveBean.FiveBean bid : wsFiveBean.getBids()) {
            Double aDouble = Double.valueOf(bid.getDoubleTotalSize());
            bid.setPercent((int) (aDouble / full * 100d));
        }

        for (WSFiveBean.FiveBean ask : wsFiveBean.getAsks()) {
            Double aDouble = Double.valueOf(ask.getDoubleTotalSize());
            ask.setPercent((int) (aDouble / full * 100d));
        }

    }*/
    /**
     * 数据适配器
     * Created by tifezh on 2016/6/18.
     */

    public class KChartAdapter extends BaseKChartAdapter {

        private List<Stock> datas = new ArrayList<>();
        private int position;
        public KChartAdapter() {

        }

        public List<Stock> getDatas() {
            return datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            this.position = position;
            return position >= datas.size() ? datas.get(datas.size() - 1) : datas.get(position);
        }

        @Override
        public Date getDate(int position) {
            return new Date(datas.get(position).getDate());
        }
        public boolean isGetMoreData(){
            if(position<=54){
                return true;
            }else {
                return false;
            }
        }
        /**
         * 向头部添加数据
         */
        public void addNewData(List<Stock> data) {
            if (data != null && !data.isEmpty()) {
                datas.addAll(data);
                notifyItemRangeInsertedToLast();
            }
        }
        /**
         * 向尾部添加数据
         */
        public void updateData(List<Stock> data) {
            if (data != null && !data.isEmpty()) {
                datas = data;
                notifyDataSetChanged();
            }
        }

        /**
         * 改变某个点的值
         */
        public void changeLastItemClosePrice(float closePrice) {
            notifyLastItemChanged(closePrice);
        }

    }
}
