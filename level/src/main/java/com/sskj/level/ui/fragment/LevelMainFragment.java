package com.sskj.level.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.depth.bean.DepthData;
import com.sskj.depth.bean.IDepthData;
import com.sskj.depth.view.DepthMapView;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.AskBean;
import com.sskj.level.bean.BidBean;

import com.sskj.level.bean.TotalRiskBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.LevelMainFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.LEVELMAINFRAGMENT)
public class LevelMainFragment extends BaseFragment<LevelMainFragmentPresenter> {
    @BindView(R2.id.ivTopLeft)
    ImageView ivTopLeft;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivTopRight)
    LinearLayout ivTopRight;
    @BindView(R2.id.ll_button)
    LinearLayout llButton;
    @BindView(R2.id.pankouFrameLayout)
    FrameLayout pankouFrameLayout;
    @BindView(R2.id.tradeFrameLayout)
    FrameLayout tradeFrameLayout;
    @BindView(R2.id.entrustFrameLayout)
    FrameLayout entrustFrameLayout;
    @BindView(R2.id.tvEntrustCurrent)
    TextView tvEntrustCurrent;
    @BindView(R2.id.tvEntrustAll)
    TextView tvEntrustAll;
    @BindView(R2.id.ivEntrustAll)
    ImageView ivEntrustAll;
    @BindView(R2.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Autowired(required = true)
    String code;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.depthMapView)
    DepthMapView depthMapView;
    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.ll_click)
    LinearLayout llClick;
    @BindView(R2.id.imageview)
    ImageView imageView;
    @BindView(R2.id.tv_content)
    TextView textView;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    @BindView(R2.id.tv_fengxian)
    TextView tv_fengxian;
    private SafeSettingBean userData;
    private BottomSheetDialog priceModeSheet;
    private boolean isflag=false;
    private ArrayList<BottomSheetUtil.ItemBean> list = new ArrayList<>();
    private Flowable<List<IDepthData>> sellFlow;
    private Flowable<List<IDepthData>> buyFlow;
    Handler handler = new Handler();
    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_bibi_main;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        RxBus.getDefault().register(this);
        depthMapView.setDrawText(false);
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_cny,"CNY"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_eru,"EUR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_hkd,"HKD"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_rub,"RUB"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_krw,"KRW"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_myr,"MYR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_twd,"TWD"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_usd,"USD"));
        tvTitle.setText(TextUtils.isEmpty(code) ? "" : code.replace("_", "/").toUpperCase());
        SPUtil.put("cointype",TextUtils.isEmpty(code) ? "" : code.replace("_", "/").toUpperCase());
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        Fragment pankouFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.LEVELPANKOUFRAGMENT)
                .withString(Constans.CODE, code)
                .navigation();
        Fragment buyAndSellFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.LEVEL_FRAGMENT_BUY_AND_SELL)
                .withString(Constans.CODE, code)
                .navigation();
        Fragment entrustFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.LEVEL_FRAGMENT_HOLDER)
                .withString(Constans.CODE, code)
                .navigation();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.pankouFrameLayout, pankouFragment);
        fragmentTransaction.add(R.id.tradeFrameLayout, buyAndSellFragment);
        fragmentTransaction.add(R.id.entrustFrameLayout, entrustFragment);
        fragmentTransaction.show(buyAndSellFragment);
        fragmentTransaction.show(entrustFragment);
        fragmentTransaction.commitNow();
        ClickUtil.click(ivTopLeft, () -> {
            ARouter.getInstance()
                    .build(RConfig.HANG_MARKET)
                    .withBoolean("isLevel",true)
                    .withString("code",code)
                    .navigation();
        });
        ClickUtil.click(tvEntrustAll, () -> {
            if (AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                ARouter.getInstance().build(RConfig.LEVEL_TRADE).withString("code",code)
                        .navigation();
            }
        });
        ClickUtil.click(llClick,()->{
            ARouter.getInstance().build(RConfig.NEWHANGACTIVITY).withBoolean("isLevel",true).navigation();
           // LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB).postValue(1);
        });
        ClickUtil.click(llButton, () -> {
            if(isflag){
                depthMapView.setVisibility(View.VISIBLE);
            }else {

                depthMapView.setVisibility(View.GONE);
            }
            isflag = !isflag;


        });
        ClickUtil.click(ivTopRight, () -> {
            priceModeSheet = BottomSheetUtil.getBottomSheet1(getActivity(), App.INSTANCE.getString(R.string.lib_xuanze), (recyclerView, position, v) -> {
                priceModeSheet.dismiss();
                mPresenter.getRate("USD",list.get(position).content);
                imageView.setImageResource(list.get(position).id);
                textView.setText(list.get(position).content);
            },list);
            priceModeSheet.show();

        });
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
          /*  if(userData==null){
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }*/

            initData();
           // LiveDataBus.get().with(RxBusCode.BIBI_FRESH).postValue(1);
            smartRefreshLayout.finishRefresh(3);
        });
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }

        });
    }
    @Override
    public void initData() {
        //mPresenter.getPankou(code);
        imageView.setImageResource(R.mipmap.lib_usd);
        textView.setText("USD");
        mPresenter.initSocket(code);
        mPresenter.initSocket1();
        mPresenter.getRate("USD","USD");
        //LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN,BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN,BibiCoinType.class).observe(this, this::changeCoin);//改变合约交易的币种
        LiveDataBus.get().with(RxBusCode.LEVEL_ALL_PC).observe(this,this::changeText);
        LiveDataBus.get().with(RxBusCode.LEVEL_FRESH).postValue(1);
    }

    private void changeText(Object o) {
        tvNum.setText("0");
        tv_fengxian.setText("0");
    }

    public void refreshCoin(NewChartBean coinBean) {
        if (code.equals(coinBean.getSymbol())) {
            updateTitle(coinBean);
        }
    }
    /**
     * 更换币种
     *
     * @param coinType
     */
    public void changeCoin(BibiCoinType coinType) {

        code = coinType.getCode();
        tvTitle.setText(code);
        tvName.setText(code+ App.INSTANCE.getString(R.string.level_shen));
        mPresenter.initSocket(code);
        mPresenter.initSocket1();
        mPresenter.getRate("USD","USD");
        //mPresenter.getPankou(coinType.getCode());

    }
    /**
     * 更新顶部币种信息
     *
     * @param productBean
     */
    public void updateTitle(NewChartBean productBean) {
        if (tvTitle != null) {
            tvTitle.setText(productBean.getSymbol().replace("_", "/").toUpperCase());
            SPUtil.put("cointype",productBean.getSymbol());
            code = productBean.getSymbol();
        }
    }
    public void setUI(RateBean bean) {
        LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).postValue(bean);//改变汇率
    }

/*    public void updateUI(AskBean bean) {
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
        }, System.out::println);
    }
    @Override
    protected LevelMainFragmentPresenter getPresenter() {
        return new LevelMainFragmentPresenter();
    }
    int num;
    int num1;
    public void setRisk(TotalRiskBean totalRiskBean) {
        tvNum.setText(totalRiskBean.getPl());
        num++;
        String risk = new BigDecimal(totalRiskBean.getRate()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+" %";
        tv_fengxian.setText(risk);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                num1++;
                if(num==num1){
                    if(tvNum!=null&&tv_fengxian!=null){
                        tvNum.setText("0");
                        tv_fengxian.setText("0");
                    }

                }
            }
        },1500);

    }

    @Override
    public void onDestroy() {
        mPresenter.closeWebSocket();
        super.onDestroy();

    }
}
