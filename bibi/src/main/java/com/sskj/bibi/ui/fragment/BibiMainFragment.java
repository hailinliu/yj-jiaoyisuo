package com.sskj.bibi.ui.fragment;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.component.DaggerUserDataComponent;
import com.sskj.bibi.presenter.BibiMainFragmentPresenter;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.depth.bean.DepthData;
import com.sskj.depth.bean.IDepthData;
import com.sskj.depth.view.DepthMapView;
import com.sskj.hangqing.bean.AskBean;
import com.sskj.hangqing.bean.BidBean;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.BIBI_FRAGMENT_MAIN)
public class BibiMainFragment extends BaseFragment<BibiMainFragmentPresenter> {
    @BindView(R2.id.ivTopLeft)
    ImageView ivTopLeft;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.tv_name)
    TextView tvName;
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
    private SafeSettingBean userData;
    private BottomSheetDialog priceModeSheet;
    private boolean isflag=false;
    private ArrayList<BottomSheetUtil.ItemBean> list = new ArrayList<>();
    private Flowable<List<IDepthData>> sellFlow;
    private Flowable<List<IDepthData>> buyFlow;

    @Override
    protected int getLayoutId() {
        return R.layout.bibi_fragment_bibi_main;
    }

    @Override
    public BibiMainFragmentPresenter getPresenter() {
        return new BibiMainFragmentPresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
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
      //  code = SPUtil.get("newcode",code);
        tvTitle.setText(TextUtils.isEmpty(code) ? "" : code.replace("_", "/").toUpperCase());
        SPUtil.put("cointype",TextUtils.isEmpty(code) ? "" : code.replace("_", "/").toUpperCase());
       // ImageUtil.setImage("https://v.qq.com/x/page/q0897fvyjg9.html?vframe/jpg/offset/0",ivEntrustAll);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        Fragment pankouFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.BIBI_FRAGMENT_PANKOU)
                .withString(Constans.CODE, code)
                .navigation();
        Fragment buyAndSellFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.BIBI_FRAGMENT_BUY_AND_SELL)
                .withString(Constans.CODE, code)
                .navigation();
        Fragment entrustFragment = (Fragment) ARouter.getInstance()
                .build(RConfig.BIBI_FRAGMENT_CURRENT_ENTRUST)
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
                    .withString("code",code)
                    .navigation();
        });
        ClickUtil.click(tvEntrustAll, () -> {
            if (AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                ARouter.getInstance().build(RConfig.BIBI_RECORD).withString("code",code)
                        .navigation();
            }
        });
        ClickUtil.click(llClick,()->{
            ARouter.getInstance().build(RConfig.NEWHANGACTIVITY).navigation();
           // LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB).postValue(1);
        });
        ClickUtil.click(llButton, () -> {
            if(isflag){
                // getActivity().overridePendingTransition(0, R.anim.bibi_slide_top_in);
                depthMapView.setVisibility(View.VISIBLE);
            }else {
                //getActivity().overridePendingTransition(0, R.anim.bibi_slide_top_out);
                depthMapView.setVisibility(View.GONE);
            }
            isflag = !isflag;
            /*Animation animation = new Animation() {

                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if(isflag){
                        // getActivity().overridePendingTransition(0, R.anim.bibi_slide_top_in);
                        depthMapView.setVisibility(View.VISIBLE);
                    }else {
                        //getActivity().overridePendingTransition(0, R.anim.bibi_slide_top_out);
                        depthMapView.setVisibility(View.GONE);
                    }
                    isflag = !isflag;
                    super.applyTransformation(interpolatedTime, t);
                }
            };
            animation.setDuration(300);*/

        });
        ClickUtil.click(ivTopRight, () -> {
            priceModeSheet = BottomSheetUtil.getBottomSheet1(getActivity(), App.INSTANCE.getString(R.string.lib_xuanze), (recyclerView, position, v) -> {
                priceModeSheet.dismiss();
                mPresenter.getRate("USD",list.get(position).content);
                imageView.setImageResource(list.get(position).id);
                textView.setText(list.get(position).content);
            },list);
            priceModeSheet.show();
         /*   ARouter.getInstance().build(RConfig.HANG_MARKET)
                    .withString(Constans.CODE, code)
                    .withInt(Constans.TYPE,1)
                    .navigation();*/
        });
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            /*if(userData==null){
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }*/
            LiveDataBus.get().with(RxBusCode.BIBI_FRESH).postValue(1);
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
       // mPresenter.getProduct(code);
        mPresenter.getPankou(code);
        mPresenter.initSocket(code);
        mPresenter.getRate("USD","USD");
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN,BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN2,BibiCoinType.class).observe(this, this::changeCoin);

        //LiveDataBus.get().with(RxBusCode.DEPTH,WSFiveBean1.class).observe(this,this::updateFive);
        //mPresenter.initSocket(code);
    }


    /**
     * 更换币种
     *
     * @param coinType
     */
    public void changeCoin(BibiCoinType coinType) {

        code = coinType.getCode();
        tvTitle.setText(code);
        tvName.setText(code+App.INSTANCE.getString(R.string.bibi_shendu));
        initData();

        //mPresenter.getPankou(coinType.getCode());

    }

    public void setUI(RateBean bean) {
      // String rate = bean.getRate();
       LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).postValue(bean);


    }


    public void updateUI(AskBean bean) {
        double num =0.00;
        for(AskBean.ItemsBean data:bean.getItems()){
            num = new BigDecimal(data.getAmountStr()).add(new BigDecimal(num)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().doubleValue();
            data.setAmountStr(String.valueOf(num));
        }
        sellFlow = Flowable.fromIterable(bean.getItems())
                .map(fiveBean -> (IDepthData)new DepthData(fiveBean.getAmountStr(), fiveBean.getPriceStr()))
                .toList()
                .toFlowable();
    }

    public void updateUI(BidBean bean1) {
        double num =0.00;
        for(BidBean.ItemsBeanX data:bean1.getItems()){
            num = new BigDecimal(data.getAmountStr()).add(new BigDecimal(num)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().doubleValue();
            data.setAmountStr(String.valueOf(num));
        }
        buyFlow = Flowable.fromIterable(bean1.getItems())
                .map(fiveBean -> (IDepthData)new DepthData(fiveBean.getAmountStr(),String.valueOf(fiveBean.getPrice())))
                .toList()
                .toFlowable();
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
}
