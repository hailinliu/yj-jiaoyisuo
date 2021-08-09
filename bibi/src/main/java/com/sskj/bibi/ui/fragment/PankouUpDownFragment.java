package com.sskj.bibi.ui.fragment;


import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.common.LogUtil;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.presenter.PankouUpDownFragmentPresenter;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.BIBI_FRAGMENT_PANKOU)
public class PankouUpDownFragment extends BaseFragment<PankouUpDownFragmentPresenter> {
    private static final int MAX_SIZE = 5;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvRMBPrice)
    TextView tvRMBPrice;
    @BindView(R2.id.etPrice)
    EditText editText;
    @BindView(R2.id.buyRecyclerView)
    RecyclerView buyRecyclerView;
    @BindView(R2.id.sellRecyclerView)
    RecyclerView sellRecyclerView;
    @BindView(R2.id.loading_layout)
    FrameLayout loadingFrameLayout;
    @BindView(R2.id.llGrade)
    LinearLayout llGrade;
    @BindView(R2.id.tvNumDot)
    TextView tvNumDot;
    @BindView(R2.id.rlEditPrice)
    RelativeLayout rlEditPrice;
    @BindView(R2.id.image)
    ImageView imageView;
    @Autowired
    String code;
    @BindView(R2.id.tv_shengdie)
    TextView tvShengdie;
    private SlimAdapter buySlimAdapter;
    private SlimAdapter sellSlimAdapter;
    private String rate;
    private List<String> list1;
    private List<String> list2;
    private int type;
    private BottomSheetDialog bottomSheet1;
    private BottomSheetDialog bottomSheet;

    @Override
    protected int getLayoutId() {
        return R.layout.bibi_fragment_pankou_up_down;
    }

    @Override
    public PankouUpDownFragmentPresenter getPresenter() {
        return new PankouUpDownFragmentPresenter();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();

        ClickUtil.click(rlEditPrice, () -> {
            bottomSheet1 = BottomSheetUtil.getBottomSheet(getActivity(), App.INSTANCE.getString(R.string.bibi_xiaoshu), (recyclerView, i, view) -> {
               bottomSheet1.dismiss();
               editText.setText(list1.get(i));
               mPresenter.getStepWebSocket(code,list1.get(i));
           }, list1);
            bottomSheet1.show();
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet = BottomSheetUtil.getBottomSheet(getActivity(), "", (recyclerView, i, view) -> {
                    bottomSheet.dismiss();
                  if(i==0){
                      sellRecyclerView.setVisibility(View.VISIBLE);
                      buyRecyclerView.setVisibility(View.VISIBLE);
                      type=0;
                     /* buySlimAdapter.updateData(bidBean.getItems().subList(0,5));
                      sellSlimAdapter.updateData(bean.getItems().subList(0,5));*/
                  }else if(i==1){
                      sellRecyclerView.setVisibility(View.GONE);
                      buyRecyclerView.setVisibility(View.VISIBLE);
                      type=1;
                     // buySlimAdapter.updateData(bidBean.getItems().subList(0,12));
                      //sellSlimAdapter.updateData(bean.getItems().subList(0,12));
                  }else if(i==2){
                      buyRecyclerView.setVisibility(View.GONE);
                      sellRecyclerView.setVisibility(View.VISIBLE);
                      type=2;
                     // sellSlimAdapter.updateData(bean.getItems().subList(0,12));
                  }
                }, list2);
                bottomSheet.show();
            }
        });
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        buyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list1 = new ArrayList<>();
        list1.add("0");
        list1.add("1");
        list1.add("2");
        editText.setText("2");
        list2= new ArrayList<>();
        list2.add(App.INSTANCE.getString(R.string.bibi_moren));
        list2.add(App.INSTANCE.getString(R.string.bibi_maipan));
        list2.add(App.INSTANCE.getString(R.string.bibi_mai_pan));
        buySlimAdapter = SlimAdapter.create().register(R.layout.bibi_recy_item_pankou_up_down, new SlimInjector<BidBean.ItemsBeanX>() {
            @Override
            public void onInject(BidBean.ItemsBeanX data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvOrderNum, buySlimAdapter.getData().indexOf(data) + 1 + "")
                        .text(R.id.tvPrice, TextUtils.isEmpty(data.getPrice()) ? "---" : new BigDecimal(data.getPrice()).stripTrailingZeros().toPlainString())
                        .text(R.id.tvNum, TextUtils.isEmpty(data.getAmount())?"---":new BigDecimal(data.getAmount()).stripTrailingZeros().toPlainString())
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, R.color.bibiGreen))
                        .with(R.id.bibiProgressbar, view -> {
                         /*   ProgressBar progressBar = (ProgressBar) view;
                            progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.bibi_progress_green));
                            progressBar.setProgress(100-data.getPercent());*/
                            ProgressBar progressBar = (ProgressBar) view;
                            //获取progressBar的LayerDrawable,LayerDrawablle是我们写layer-list生成的多层级的drawable
                            LayerDrawable drawable1 = (LayerDrawable) progressBar.getProgressDrawable();

                            //新建一个GradientDrawable ，shape标签生成的drawable类型，此处用它是为了设置圆角，若不需要圆角，可以用ColorDrawable设置颜色
                            GradientDrawable gradientDrawable = new GradientDrawable();

                            gradientDrawable.setColor(getResources().getColor(com.sskj.level.R.color.lib_bg));
                            GradientDrawable gradientDrawable1 = new GradientDrawable();

                            gradientDrawable1.setColor(getResources().getColor(com.sskj.level.R.color.libGreenHint));
                            //ClipDrawable 是一个ClipDrawable是通过设置一个Drawable的当前显示比例来裁剪出另一张Drawable，
                            //你可以通过调节这个比例来控制裁剪的宽高，以及裁剪内容占整个容器的权重，不是ClipDrawable的话会无法根据我们的progress显示长度，进度条显示起来总是满进度的
                            ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);

                            //重点，将LayerDrawable 里id为android.R.id.progress的Drawable层替换为我们新建的Drawable，这就达到了我们改变他的颜色的目的了
                            drawable1.setDrawableByLayerId(android.R.id.progress, clipDrawable);
                            drawable1.setDrawableByLayerId(android.R.id.background,gradientDrawable);
                            //  ClipDrawable drawable = new ClipDrawable(new ColorDrawable(getResources().getColor(R.color.libBg2)), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                            progressBar.setProgressDrawable(drawable1);//必须先设置到progressbar上再设置level，告诉这个drawable的宽度有多宽，这个level才能生效
                            drawable1.setLevel(data.getPercent());
                            progressBar.setProgressDrawable(drawable1);
                            // progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.level_progress_green));
                            progressBar.setMax(10000);
                            progressBar.setProgress(data.getPercent());
                        })

                ;
            }
        });
        buySlimAdapter.attachTo(buyRecyclerView).updateData(new ArrayList());
        sellSlimAdapter = SlimAdapter.create().register(R.layout.bibi_recy_item_pankou_up_down, new SlimInjector<AskBean.ItemsBean>() {
            @Override
            public void onInject(AskBean.ItemsBean data, IViewInjector injector, List payloads) {
                injector
                        .text(R.id.tvOrderNum, "" + (sellSlimAdapter.getItemCount() - sellSlimAdapter.getData().indexOf(data)))
                        .text(R.id.tvPrice, TextUtils.isEmpty(data.getPrice())? "---" : new BigDecimal(data.getPrice()).stripTrailingZeros().toPlainString())
                        .text(R.id.tvNum, TextUtils.isEmpty(data.getAmount())?"---":new BigDecimal(data.getAmount()).stripTrailingZeros().toPlainString())
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, R.color.bibiRed))
                        .with(R.id.bibiProgressbar, view -> {
                          /*  ProgressBar progressBar = (ProgressBar) view;
                            progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.bibi_progress_red));
                            progressBar.setProgress(100-data.getPercent());*/
                            ProgressBar progressBar = (ProgressBar) view;
                            //  progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.level_progress_red));
                            //获取progressBar的LayerDrawable,LayerDrawablle是我们写layer-list生成的多层级的drawable
                            LayerDrawable drawable1 = (LayerDrawable) progressBar.getProgressDrawable();

                            //新建一个GradientDrawable ，shape标签生成的drawable类型，此处用它是为了设置圆角，若不需要圆角，可以用ColorDrawable设置颜色
                            GradientDrawable gradientDrawable = new GradientDrawable();

                            gradientDrawable.setColor(getResources().getColor(com.sskj.level.R.color.lib_bg));
                            GradientDrawable gradientDrawable1 = new GradientDrawable();

                            gradientDrawable1.setColor(getResources().getColor(com.sskj.level.R.color.libRedHint));
                            //ClipDrawable 是一个ClipDrawable是通过设置一个Drawable的当前显示比例来裁剪出另一张Drawable，
                            //你可以通过调节这个比例来控制裁剪的宽高，以及裁剪内容占整个容器的权重，不是ClipDrawable的话会无法根据我们的progress显示长度，进度条显示起来总是满进度的
                            ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);

                            //重点，将LayerDrawable 里id为android.R.id.progress的Drawable层替换为我们新建的Drawable，这就达到了我们改变他的颜色的目的了
                            drawable1.setDrawableByLayerId(android.R.id.progress, clipDrawable);
                            drawable1.setDrawableByLayerId(android.R.id.background,gradientDrawable);
                            //  ClipDrawable drawable = new ClipDrawable(new ColorDrawable(getResources().getColor(R.color.libBg2)), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                            progressBar.setProgressDrawable(drawable1);//必须先设置到progressbar上再设置level，告诉这个drawable的宽度有多宽，这个level才能生效
                            drawable1.setLevel(data.getPercent());
                            progressBar.setProgressDrawable(drawable1);
                            progressBar.setMax(10000);
                            // progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.level_progress_green));
                            progressBar.setProgress(data.getPercent());
                        })

                ;
            }
        });
        sellSlimAdapter.attachTo(sellRecyclerView).updateData(new ArrayList());

        ItemClickSupport.addTo(buyRecyclerView)
                .setOnItemClickListener((recyclerView, i, view) -> {
                    if (i < 0) {
                        return;
                    }
                    BidBean.ItemsBeanX dataItem = (BidBean.ItemsBeanX) buySlimAdapter.getDataItem(i);
                    LiveDataBus.get().with(RxBusCode.CHANGE_PRICE, String.class)
                            .postValue(dataItem.getPriceStr());
                });
        ItemClickSupport.addTo(sellRecyclerView)
                .setOnItemClickListener((recyclerView, i, view) -> {
                    if (i < 0) {
                        return;
                    }
                    AskBean.ItemsBean dataItem = (AskBean.ItemsBean) sellSlimAdapter.getDataItem(i);
                    LiveDataBus.get().with(RxBusCode.CHANGE_PRICE, String.class)
                            .postValue(dataItem.getPriceStr());


                });

    }
    @Override
    public void initData() {
        mPresenter.getRate("USD","CNY");
        mPresenter.initHangqingSocket();
        changeCoin(new BibiCoinType(code));
        LiveDataBus.get().with(RxBusCode.BIBI_FRESH).observe(this, o -> refresh());
        LiveDataBus.get().with(RxBusCode.NEWCODEBEAN, CoinBean1.class)
                .observe(this, this::refreshCoin);

        LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).observe(this,this::refreshRate);
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN, BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN2,BibiCoinType.class).observe(this, this::changeCoin);
    }

    private void refreshRate(RateBean rateBean) {
        rate = rateBean.getRate();
        unit = rateBean.getSimple();
    }




    /**
     * 刷新页面
     */
    public void refresh() {
        mPresenter.getPankou(code);
        mPresenter.initHangqingSocket();
        mPresenter.initSocket(code);
    }

 //改变币种刷新数据
    public void changeCoin(BibiCoinType coinType) {
        code = coinType.getCode();
        mPresenter.getPankou(code);
        mPresenter.initSocket(code);
        mPresenter.initHangqingSocket();

    }
//计算BigDecimal
    public String multifyDouble(String a,String b){
        BigDecimal a1 =  new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
    }
//升跌幅还有展示行情数据长连接刷新
    public void refreshCoin(CoinBean1 productBean) {
        if (code.equals(productBean.getSymbol())) {
            updateTitle(productBean);
            tvShengdie.setText(productBean.getChg()>0?"+"+new BigDecimal(productBean.getChg()).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+"%":
                    new BigDecimal(productBean.getChg()).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+"%");
            if(productBean.getChg()>0){
                tvShengdie.setBackgroundColor(getResources().getColor(R.color.bibiGreenHint));
                tvShengdie.setTextColor(getResources().getColor(R.color.libGreen));
            }else {
                tvShengdie.setBackgroundColor(getResources().getColor(R.color.bibiRedHint));
                tvShengdie.setTextColor(getResources().getColor(R.color.libRed));
            }
        }

    }
//更新行情数据
    public void updateTitle(CoinBean1 stockCoinBean) {
        if (tvPrice == null) {
            return;
        }
        try {
            tvPrice.setText(CoinUtil.keepCoinPrice(code, stockCoinBean.getClose()+""));
            if(rate!=null){
                tvRMBPrice.setText("≈ "+unit+multifyDouble(stockCoinBean.getClose()+"",rate));
            }

        } catch (Exception e) {

        }


    }
 //计算进度条
    private void calculatePercentAll(BidBean wsFiveBean) {
        BigDecimal full = new BigDecimal(0);
        for (BidBean.ItemsBeanX bid : wsFiveBean.getItems()) {
         /*   Double aDouble = Double.valueOf(bid.getAmountStr());
            if (aDouble > full) {
                full = aDouble;
            }*/
            BigDecimal aDouble =  new BigDecimal(bid.getAmount());
            //Double aDouble = Double.valueOf(ask.getAmountStr());
            if (aDouble.compareTo(full)>0) {
                full = aDouble;
            }
        }

        for (BidBean.ItemsBeanX bid : wsFiveBean.getItems()) {
            BigDecimal aDouble =  new BigDecimal(bid.getAmount());
            int progress = aDouble.divide(full,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(10000)).intValue();
            bid.setPercent(progress);
        }



    }
    private void calculatePercentAll(AskBean wsFiveBean) {
        BigDecimal full = new BigDecimal(0);
        for (AskBean.ItemsBean ask : wsFiveBean.getItems()) {
            BigDecimal aDouble =  new BigDecimal(ask.getAmount());
            //Double aDouble = Double.valueOf(ask.getAmountStr());
            if (aDouble.compareTo(full)>0) {
                full = aDouble;
            }
        }

        for (AskBean.ItemsBean ask : wsFiveBean.getItems()) {
            BigDecimal aDouble =  new BigDecimal(ask.getAmount());
            int progress = aDouble.divide(full,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(10000)).intValue();
            ask.setPercent(progress);
        }

    }

String unit = "￥";
 //更新切换汇率
    public void updateRate(RateBean bean) {
        rate = bean.getRate();
        unit = bean.getSimple();
        mPresenter.getData(code);
        LiveDataBus.get().with(RxBusCode.NEWCODEBEAN, CoinBean1.class)
                .observe(this, this::refreshCoin);

    }
//更新盘口长连接数据，卖盘
    public void updateUI(AskBean bean) {
       // LogUtil.e("旧的:"+bean.getItems().toString());
       // LogUtil.e("新的:"+bean.toString());
        if (loadingFrameLayout != null) {
            loadingFrameLayout.setVisibility(View.GONE);
        }
        calculatePercentAll(bean);
            // this.bean = bean;
            if(type==0){
                //sellSlimAdapter.updateData(bean.getItems().subList(0,5));

                if(bean.getItems().size()<5){
                   int size= bean.getItems().size();
                    for(int i=0;i<5-size;i++){
                        bean.getItems().add(new AskBean.ItemsBean("","",null,null));
                    }
                    sellSlimAdapter.updateData(bean.getItems());
                } else if(bean.getItems().size()>=5){
                   List<AskBean.ItemsBean> list=  bean.getItems().subList(0,5);
                    Collections.reverse(list);
                    sellSlimAdapter.updateData(list);
                }
            }else if(type==2){
                //sellSlimAdapter.updateData(bean.getItems().subList(0,12));
                if(bean.getItems().size()<10){
                    for(int i=0;i<10-bean.getItems().size();i++){
                        bean.getItems().add(new AskBean.ItemsBean("","",null,null));
                    }
                    sellSlimAdapter.updateData(bean.getItems());
                } else if(bean.getItems().size()>=10){
                    sellSlimAdapter.updateData(bean.getItems().subList(0,10));
                }

            }
        }

//买盘
    public void updateUI(BidBean bean1) {

        if (loadingFrameLayout != null) {
            loadingFrameLayout.setVisibility(View.GONE);
        }

            calculatePercentAll(bean1);
            //bidBean = bean1;
            if(type==0){
                //buySlimAdapter.updateData(bean1.getItems().subList(0, 5));
                if(bean1.getItems().size()<5){
                   int size= bean1.getItems().size();
                    for(int i=0;i<5-size;i++){
                       bean1.getItems().add(new BidBean.ItemsBeanX("","","",""));
                    }
                    buySlimAdapter.updateData(bean1.getItems());
                } else if(bean1.getItems().size()>=5) {
                    buySlimAdapter.updateData(bean1.getItems().subList(0, 5));
                }
            }else if(type==1){
                //buySlimAdapter.updateData(bean1.getItems().subList(0,12));
                if(bean1.getItems().size()<10){
                    for(int i=0;i<10-bean1.getItems().size();i++){
                        bean1.getItems().add(new BidBean.ItemsBeanX("","","",""));
                    }
                    buySlimAdapter.updateData(bean1.getItems());
                } else if(bean1.getItems().size()>=10){
                    buySlimAdapter.updateData(bean1.getItems().subList(0,10));
                }

            }
        }
    public boolean equalZero(BigDecimal decimal){
        return   decimal.compareTo(BigDecimal.ZERO)==0;
    }
    public void updateUI(List<CoinBean1> list) {
        for(CoinBean1 data:list){
            if(data.getSymbol().equals(code)){
                BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
                BigDecimal b = new BigDecimal(Integer.toString(100));
                tvPrice.setText((equalZero(new BigDecimal(data.getClose()))?"0":new BigDecimal(data.getClose()).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()));
                tvRMBPrice.setText(String.format("≈%s ","≈ ￥"+new BigDecimal(rate).multiply(new BigDecimal(data.getClose())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));


            }
        }
    }
}

