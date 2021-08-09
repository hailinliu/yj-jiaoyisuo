package com.sskj.hangqing.ui.fragment;


import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.depth.bean.DepthData;
import com.sskj.depth.bean.IDepthData;
import com.sskj.depth.view.DepthMapView;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.rxbus.MarketCoinBean;
import com.sskj.hangqing.presenter.DeepthFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.bean.enums.BuySellEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.CoinUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.HANG_FRAGMENT_DEEPTH)
public class DeepthFragment extends BaseFragment<DeepthFragmentPresenter> {
    @Autowired
    String code;
    @Autowired
    int type;
    @Autowired
    String qu;
    @BindView(R2.id.tvBuyNumTitle)
    TextView tvBuyNumTitle;
    @BindView(R2.id.tvSellNumTitle)
    TextView tvSellNumTitle;
    @BindView(R2.id.recyclerViewBuy)
    RecyclerView buyRecyclerView;
    @BindView(R2.id.recyclerViewSell)
    RecyclerView sellRecyclerView;
    @BindView(R2.id.depthMapView)
    DepthMapView depthMapView;
    private SlimAdapter sellSlimAdapter;
    private SlimAdapter buySlimAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_deepth;
    }

    @Override
    public DeepthFragmentPresenter getPresenter() {
        return new DeepthFragmentPresenter();
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        String replace = TextUtils.isEmpty(code.replace("_", "/")) ? "" : code.replace("_", "/");
        tvBuyNumTitle.setText(TextUtils.isEmpty(replace) ? "" : String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2), replace.substring(0, replace.indexOf("/"))));
        tvSellNumTitle.setText(TextUtils.isEmpty(replace) ? "" : String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2), replace.substring(0, replace.indexOf("/"))));
        initRecy();
        if (code.toUpperCase().contains("DIG")) {
            depthMapView.mPriceLimit = 6;
            depthMapView.mVolumeLimit = 6;
        } else {
            depthMapView.mPriceLimit = CoinUtil.getPriceKeepNum(code);
            depthMapView.mVolumeLimit = CoinUtil.getNumKeepNum(code);
        }
    }

    @Override
    public void initData() {
       // mPresenter.getData(code,type);
        mPresenter.getPankou(code);
        mPresenter.initSocket(code);
        LiveDataBus.get().with(RxBusCode.CHANGE_MARKET_CODE, MarketCoinBean.class)
                .observe(this, this::changeCoin);
    }

    @SuppressWarnings("unchecked")
    private void initRecy() {
        sellRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buySlimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_deep_buy, new SlimInjector<BidBean.ItemsBeanX>() {
            @Override
            public void onInject(BidBean.ItemsBeanX data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tvOrderNum, buySlimAdapter.getData().indexOf(data) + 1 + "")
                        .text(R.id.tvPrice, data.getPriceStr())
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, BuySellEnum.BUY.getColorRes()))
                        .text(R.id.tvNum, data.getAmountStr())
                        .with(R.id.bibiProgressbar, view -> {
                            ProgressBar progressBar = (ProgressBar) view;
                            //获取progressBar的LayerDrawable,LayerDrawablle是我们写layer-list生成的多层级的drawable
                            LayerDrawable drawable1 = (LayerDrawable) progressBar.getProgressDrawable();

                            //新建一个GradientDrawable ，shape标签生成的drawable类型，此处用它是为了设置圆角，若不需要圆角，可以用ColorDrawable设置颜色
                            GradientDrawable gradientDrawable = new GradientDrawable();

                            gradientDrawable.setColor(getResources().getColor(R.color.lib_bg));
                            GradientDrawable gradientDrawable1 = new GradientDrawable();

                            gradientDrawable1.setColor(getResources().getColor(R.color.libGreenHint));
                            //ClipDrawable 是一个ClipDrawable是通过设置一个Drawable的当前显示比例来裁剪出另一张Drawable，
                            //你可以通过调节这个比例来控制裁剪的宽高，以及裁剪内容占整个容器的权重，不是ClipDrawable的话会无法根据我们的progress显示长度，进度条显示起来总是满进度的
                            ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable1, Gravity.RIGHT, ClipDrawable.HORIZONTAL);

                            //重点，将LayerDrawable 里id为android.R.id.progress的Drawable层替换为我们新建的Drawable，这就达到了我们改变他的颜色的目的了
                            drawable1.setDrawableByLayerId(android.R.id.progress, clipDrawable);
                            drawable1.setDrawableByLayerId(android.R.id.background,gradientDrawable);
                            //  ClipDrawable drawable = new ClipDrawable(new ColorDrawable(getResources().getColor(R.color.libBg2)), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                            progressBar.setProgressDrawable(drawable1);//必须先设置到progressbar上再设置level，告诉这个drawable的宽度有多宽，这个level才能生效
                            drawable1.setLevel(data.getPercent() * 50);
                            progressBar.setProgressDrawable(drawable1);
                            // progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.level_progress_green));
                            progressBar.setProgress(data.getPercent());
                        })
                ;
            }
        }).attachTo(buyRecyclerView).updateData(new ArrayList());
        sellSlimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_deep_sell, new SlimInjector<AskBean.ItemsBean>() {
            @Override
            public void onInject(AskBean.ItemsBean data, IViewInjector injector, List<Object> payloads) {
                injector.text(R.id.tvOrderNum, sellSlimAdapter.getData().indexOf(data) + 1 + "")
                        .textColor(R.id.tvPrice, ContextCompat.getColor(App.INSTANCE, BuySellEnum.SELL.getColorRes()))
                        .text(R.id.tvPrice, data.getPriceStr())
                        .text(R.id.tvNum, data.getAmountStr())
                        .with(R.id.bibiProgressbar, view -> {
                            ProgressBar progressBar = (ProgressBar) view;
                            LayerDrawable drawable1 = (LayerDrawable) progressBar.getProgressDrawable();

                            //新建一个GradientDrawable ，shape标签生成的drawable类型，此处用它是为了设置圆角，若不需要圆角，可以用ColorDrawable设置颜色
                            GradientDrawable gradientDrawable = new GradientDrawable();

                            gradientDrawable.setColor(getResources().getColor(R.color.lib_bg));
                            GradientDrawable gradientDrawable1 = new GradientDrawable();

                            gradientDrawable1.setColor(getResources().getColor(R.color.libRedHint));
                            //ClipDrawable 是一个ClipDrawable是通过设置一个Drawable的当前显示比例来裁剪出另一张Drawable，
                            //你可以通过调节这个比例来控制裁剪的宽高，以及裁剪内容占整个容器的权重，不是ClipDrawable的话会无法根据我们的progress显示长度，进度条显示起来总是满进度的
                            ClipDrawable clipDrawable = new ClipDrawable(gradientDrawable1, Gravity.LEFT, ClipDrawable.HORIZONTAL);

                            //重点，将LayerDrawable 里id为android.R.id.progress的Drawable层替换为我们新建的Drawable，这就达到了我们改变他的颜色的目的了
                            drawable1.setDrawableByLayerId(android.R.id.progress, clipDrawable);
                            drawable1.setDrawableByLayerId(android.R.id.background,gradientDrawable);
                            //  ClipDrawable drawable = new ClipDrawable(new ColorDrawable(getResources().getColor(R.color.libBg2)), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                            progressBar.setProgressDrawable(drawable1);//必须先设置到progressbar上再设置level，告诉这个drawable的宽度有多宽，这个level才能生效
                            drawable1.setLevel(data.getPercent() * 50);
                            progressBar.setProgressDrawable(drawable1);
                            // progressBar.setProgressDrawable(ContextCompat.getDrawable(App.INSTANCE, R.drawable.level_progress_green));
                            progressBar.setProgress(data.getPercent());
                        })
                ;
            }
        }).

                attachTo(sellRecyclerView).

                updateData(new ArrayList());
        sellRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    buyRecyclerView.scrollBy(dx, dy);
                }
            }
        });
        buyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    sellRecyclerView.scrollBy(dx, dy);
                }
            }
        });
    }

    public void changeCoin(MarketCoinBean marketCoinBean) {
        code = marketCoinBean.getCode();
        qu = marketCoinBean.getQu();
        if (tvSellNumTitle == null)
            return;
        String replace = code.replace("_", "/");
        tvBuyNumTitle.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2), replace.substring(0, replace.indexOf("/")).toUpperCase()));
        tvSellNumTitle.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2), replace.substring(0, replace.indexOf("/")).toUpperCase()));
        mPresenter.getPankou(code);
        mPresenter.sendCode(code);
    }


    private void calculatePercentAll(BidBean wsFiveBean) {
        Double full = 0d;
        for (BidBean.ItemsBeanX bid : wsFiveBean.getItems()) {
            Double aDouble = Double.valueOf(bid.getAmountStr());
            if (aDouble > full) {
                full = aDouble;
            }
        }

        for (BidBean.ItemsBeanX bid : wsFiveBean.getItems()) {
            Double aDouble = Double.valueOf(bid.getAmountStr());
            bid.setPercent((int) (aDouble / full * 100d));
        }



    }
    private void calculatePercentAll(AskBean wsFiveBean) {
        Double full = 0d;
        for (AskBean.ItemsBean ask : wsFiveBean.getItems()) {
            Double aDouble = Double.valueOf(ask.getAmountStr());
            if (aDouble > full) {
                full = aDouble;
            }
        }

        for (AskBean.ItemsBean ask : wsFiveBean.getItems()) {
            Double aDouble = Double.valueOf(ask.getAmountStr());
            ask.setPercent((int) (aDouble / full * 100d));
        }

    }

    public void updateUI(AskBean bean) {
        calculatePercentAll(bean);
        if(bean.getItems().size()>=20){
            sellSlimAdapter.updateData(bean.getItems().subList(0,20));
        }else if(bean.getItems().size()>0){
            sellSlimAdapter.updateData(bean.getItems());
        }

    }

    public void updateUI(BidBean bean1) {
        calculatePercentAll(bean1);
        if(bean1.getItems().size()>=20){
            buySlimAdapter.updateData(bean1.getItems().subList(0,20));
        }else if(bean1.getItems().size()>0){
            buySlimAdapter.updateData(bean1.getItems());
        }

    }
}
