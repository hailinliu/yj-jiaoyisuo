package com.sskj.hangqing.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.CoinFragmentPresenter;
import com.sskj.hangqing.presenter.NewCoinFragmentPresenter;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.CoinUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("ALL")
@Route(path = RConfig.HANG_FRAGMENT_NRW_COIN_LIST)
public class NewCoinListFragment extends BaseFragment<NewCoinFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.includeEmpty)
    FrameLayout includeEmpty;
    private SlimAdapter<CoinBean1> slimAdapter;
    private Map<String, Disposable> timeMap = new HashMap<>();
    private HashMap<String, String> coinMap;
    @Autowired
    boolean isSlide = false;
    @Autowired
    String qu;
    @Autowired
   public int type=2;
    @Autowired
    boolean isUpDown = false;
    @Autowired
    boolean isNew = false;
    @Autowired
    boolean isLevel;
    @Autowired
    boolean ishangqing;
    @Autowired
    boolean isSelf = false;
    private SafeSettingBean userData;
    @BindView(R2.id.tv_title1)
    TextView textView1;
    @BindView(R2.id.tv_title2)
    TextView textView2;
    @BindView(R2.id.tv_title3)
    TextView textView3;
    private String rate;
    private String unit;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_coin;
    }

    @Override
    public NewCoinFragmentPresenter getPresenter() {
        return new NewCoinFragmentPresenter();
    }
    public String multifyDouble(String a,String b){
    BigDecimal a1 =  new BigDecimal(a);
    BigDecimal b1 = new BigDecimal(b);
    return a1.multiply(b1).setScale(8,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
}
    @Override
    public void initView() {
        ARouter.getInstance().inject(this);

        qu = "/USDT";
        LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).observe(this,this::refreshRate);
        LiveDataBus.get().with(RxBusCode.REFRESHHANGQING,Integer.class).observe(this,this::refreshData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.hangDivider))
        );

        slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_coin, new SlimInjector<CoinBean1>() {
            @Override
            public void onInject(CoinBean1 data, IViewInjector injector, List payloads) {
                BigDecimal bigDecimal = new BigDecimal(data.getClose()).setScale(8,BigDecimal.ROUND_DOWN);
                BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
                BigDecimal b = new BigDecimal(Integer.toString(100));
                String changerate = a.multiply(b).doubleValue()+"%";

                    injector
                            .image(R.id.ivCoin, HttpConfig.BASE_URL+data.getImgUrl())
                            .text(R.id.tvUSDT,bigDecimal.stripTrailingZeros().toPlainString())
                            .text(R.id.tvRMB,unit+multifyDouble(data.getClose(),rate))
                            //.textColor(R.id.tvUSDT,data.isUp() ? getResources().getColor(R.color.hangGreen) : getResources().getColor(R.color.hangRed))
                            .text(R.id.tvCode, data.getLCode())
                          //  .text(R.id.tvRate, changerate+(data.isUp()?" ↑":" ↓"))
                            .background(R.id.llRate, data.isUp()?R.drawable.hang_shape_circle_zuixin1:R.drawable.hang_shape_circle_zuixin2)
                            .textColor(R.id.tvRate, getResources().getColor(R.color.hangBgDeep))
                    ;
                    if(type==1){
                        injector.text(R.id.tvRate, (int) data.getVolume()/1000+"M");
                    }else if(type==2){
                        injector.text(R.id.tvRate, changerate);
                    }

                ClickUtil.click(injector.findViewById(R.id.hang_main), () -> {
                    if (!isSlide) {
                      //  SPUtil.put("newcode1",data.getSymbol());
                        ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
                        if(ishangqing){
                            ARouter.getInstance().build(RConfig.HANG_MARKET)
                                    .withString(Constans.CODE, data.getSymbol())
                                    .withString(Constans.QU, qu)
                                    .withBoolean("isLevel",isLevel)
                                    .navigation();
                        }else {
                            if(isLevel){
                                LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN, BibiCoinType.class).postValue(new BibiCoinType(data.getSymbol()));
                                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                                        .postValue(3);
                            }else{
                                LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN, BibiCoinType.class).postValue(new BibiCoinType(data.getSymbol()));
                                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                                        .postValue(2);
                            }
                            getActivity().finish();
                        }


                      /*  ARouter.getInstance().build(RConfig.HANG_MARKET)
                                .withString(Constans.CODE, data.getSymbol())
                                .withString(Constans.QU, qu)
                                .withBoolean("isLevel",isLevel)
                                .navigation();*/
                    }
                });
            }
        });
        slimAdapter.attachTo(recyclerView).updateData(new ArrayList());
    }

    private void refreshData(Integer integer) {
        initData();
    }

    public void refreshRate(RateBean bean){
             rate = bean.getRate();
             unit = bean.getSimple();
             slimAdapter.notifyDataSetChanged();
        }
    public void updateUI(List<CoinBean1> data) {
        if (includeEmpty == null || slimAdapter == null) {
            return;
        }
        if (data == null || data.size() == 0) {
            slimAdapter.updateData(new ArrayList<>());
            includeEmpty.setVisibility(View.VISIBLE);
            return;
        }
        includeEmpty.setVisibility(View.GONE);
        slimAdapter.updateData(data);

    }


    public void refreshCoin1(CoinBean1 coinBean) {
        if (slimAdapter == null || slimAdapter.getData() == null || slimAdapter.getData().size() == 0) {
            return;
        }
        for(CoinBean1 bean:slimAdapter.getData()){
            if(bean.getSymbol().equals(coinBean.getSymbol())){
                coinBean.setImgUrl(bean.getImgUrl());
            }

        }
        Flowable.fromIterable(slimAdapter.getData())
                .filter(item -> item.getSymbol().equals(coinBean.getSymbol()))
                .subscribe(item -> {
                    int position = slimAdapter.getData().indexOf(item);
                    slimAdapter.getData().set(position, coinBean);
                    slimAdapter.notifyItemChanged(position, 1);
                }, e -> {
                });
    }

    public void refreshCoin2(CoinBean1 coinBean) {
        if (slimAdapter == null || slimAdapter.getData() == null || slimAdapter.getData().size() == 0) {
            return;
        }
        for(CoinBean1 bean:slimAdapter.getData()){
            if(bean.getSymbol().equals(coinBean.getSymbol())){
                coinBean.setImgUrl(bean.getImgUrl());
            }

        }
        Flowable.fromIterable(slimAdapter.getData())
                .filter(item -> item.getSymbol().equals(coinBean.getSymbol()))
                .subscribe(item -> {
                    int position = slimAdapter.getData().indexOf(item);
                    slimAdapter.getData().set(position, coinBean);
                    slimAdapter.notifyItemChanged(position, 1);
                }, e -> {
                });
    }
    @Override
    public void initData() {
        if(type==1){
            textView1.setText(R.string.hang_name);
            textView2.setText(R.string.hang_new);
            textView3.setText(R.string.hang_chengjiao);
        }else if(type==2){
            textView1.setText(R.string.hang_hang_fragment_coin1300);
            textView2.setText(R.string.hang_hang_fragment_coin150);
            textView3.setText(App.INSTANCE.getString(R.string.hang_hang_activity_market_scroll350));
        }
        mPresenter.getRate("USD","CNY");
        if(isLevel){
            mPresenter.initNewSocket1();
            /*LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1,CoinBean1.class)
                    .observe(this, this::refreshCoin1);*/
        }else {
          /*  LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class)
                    .observe(this, this::refreshCoin2);*/
          mPresenter.initNewSocket();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        mPresenter.closeSocket();
        super.onDestroy();
    }

    public void setUI(RateBean bean) {
      rate = bean.getRate();
      unit = bean.getSimple();
        mPresenter.getProduct(isLevel);
    }
}
