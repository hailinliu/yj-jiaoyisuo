package com.sskj.hangqing.ui.activity;


import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ImgUtil;
import com.sskj.common.util.PermissionTipUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.presenter.MarketActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.LevelCoinType;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.NumberUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.HANG_MARKET)
public class MarketActivity extends BaseActivity<MarketActivityPresenter> {
    @Autowired
    String code;
    @Autowired
    String qu;
    @Autowired
     boolean isLevel;
    @Autowired
    boolean isSolo;
    int type = 1;
    @BindView(R2.id.frameLayoutChart)
    FrameLayout frameLayoutChart;
    @BindView(R2.id.frameLayoutBottom)
    FrameLayout frameLayoutBottom;
    @BindView(R2.id.slidingTabLayout)
    CommonTabLayout slidingTabLayout;
    @BindView(R2.id.btTrade)
    Button btTrade;
    @BindView(R2.id.btSell)
    Button btSell;
    @BindView(R2.id.tvUSDT)
    TextView tvUSDT;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    @BindView(R2.id.tvHigh)
    TextView tvHigh;
    @BindView(R2.id.tvRMB)
    TextView tvRMB;
    @BindView(R2.id.tvRate)
    TextView tvRate;
    @BindView(R2.id.tvLow)
    TextView tvLow;
    @BindView(R2.id.tvTitleName)
    TextView tvTitleName;
    @BindView(R2.id.image2)
    ImageView image2;
    private HashMap<Integer, Fragment> bottomMap;
    private int curPos;
    private String rate;


    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_market_scroll;
    }

    @Override
    public MarketActivityPresenter getPresenter() {
        return new MarketActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        RxBus.getDefault().register(this);
        tvTitleName.setText(code);
       /* ClickUtil.click(tvTitleName, () -> {
            LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                    .postValue(1);
            ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
                finish();
            //ARouter.getInstance().build()
        });*/
        ClickUtil.click(btTrade, () -> {
            if(isSolo){
               ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
                LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                        .postValue(2);
            }

            if(isLevel){
                LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                        .postValue(3);
            }else{
                SPUtil.put("newcode",code);
                LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                        .postValue(2);
            }

            //LiveDataBus.get().with(RxBusCode.PUSH_LEVEL_COIN_TYPE, LevelCoinType.class).postValue(new LevelCoinType(code,""));
          //  RxBus.getDefault().post(new BibiCoinType(code));

            finish();
        });
        ClickUtil.click(btSell, () -> {
            if(isSolo){
                ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
                LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN2,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB1, Integer.class)
                        .postValue(2);
            }
            if(isLevel){
                LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN2,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                        .postValue(3);
            }else{
                SPUtil.put("newcode",code);
                LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN2,BibiCoinType.class).postValue(new BibiCoinType(code));
                LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB1, Integer.class)
                        .postValue(2);
            }

           // LiveDataBus.get().with(RxBusCode.PUSH_LEVEL_COIN_TYPE, LevelCoinType.class).postValue(new LevelCoinType(code,""));
            //  RxBus.getDefault().post(new BibiCoinType(code));

            finish();
        });
        ClickUtil.click(image2,()->{
                Bitmap map=capture(this);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                View inflate = getLayoutInflater().inflate(R.layout.hang_dialog_bottom, null, false);
                TextView tvCancel = inflate.findViewById(R.id.tv_cancel);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView imageView = inflate.findViewById(R.id.image);
                imageView.setImageBitmap(map);
                //设置二维码图片
                TextView tv =  inflate.findViewById(R.id.tv_save);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new RxPermissions(MarketActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(agree -> {
                                    if (agree) {
                                        if (ImgUtil.saveImageToGallery(MarketActivity.this, imageView) != null) {
                                            ToastUtil.showShort("保存成功");
                                        } else {
                                            ToastUtil.showShort("保存失败");
                                        }
                                    } else {
                                        PermissionTipUtil.tip(MarketActivity.this,"存储");
                                    }
                                });
                        //ImgUtil.saveImageToGallery(NewRechargeActivity.this, imageView);
                    }
                });
                bottomSheetDialog.setContentView(inflate);
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();

        });
        if(isLevel){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayoutChart, (Fragment) ARouter.getInstance()
                            .build(RConfig.NEWCHARTFRAGMENT)
                            .withString(Constans.CODE, code)
                            .withString(Constans.GOOD_TYPE, "minute")
                            .withBoolean(Constans.IS_CANDLE, false)
                            .withInt("type",type)
                            .navigation()
                    )
                    .commitAllowingStateLoss();
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayoutChart, (Fragment) ARouter.getInstance()
                            .build(RConfig.HANG_FRAGMENT_CHART)
                            .withString(Constans.CODE, code)
                            .withString(Constans.GOOD_TYPE, "minute")
                            .withBoolean(Constans.IS_CANDLE, false)
                            .withInt("type",type)
                            .navigation()
                    )
                    .commitAllowingStateLoss();
        }


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance()
                .build(RConfig.HANG_FRAGMENT_DEEPTH)
                .withString(Constans.CODE, code)
                .withBoolean("isLevel",isLevel)
                .withInt("type",type)
                .navigation());
        fragments.add((Fragment) ARouter.getInstance()
                .build(RConfig.HANG_FRAGMENT_TRADE)
                .withString(Constans.CODE, code)
                .withBoolean("isLevel",isLevel)
                .withInt("type",type)
                .navigation());
   /*     fragments.add((Fragment) ARouter.getInstance()
                .build(RConfig.HANG_FRAGMENT_SUMMARY)
                .withString(Constans.CODE, code)
                .withInt("type",type)
                .navigation());*/
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayoutBottom, fragments.get(0))
                .show(fragments.get(0))
                .commitAllowingStateLoss();
        bottomMap = new HashMap<>();
        bottomMap.put(0, fragments.get(0));
        curPos = 0;
        ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqingmarketActivity1), 0, 0));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.hangqingmarketActivity2), 0, 0));
       // tabItems.add(new TabItem("info", 0, 0));
        slidingTabLayout.setTabData(tabItems);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (bottomMap.get(position) == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayoutBottom, fragments.get(position))
                            .hide(fragments.get(curPos))
                            .show(fragments.get(position))
                            .commitAllowingStateLoss();
                    bottomMap.put(position, fragments.get(position));
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(fragments.get(curPos))
                            .show(fragments.get(position))
                            .commitAllowingStateLoss();
                }
                curPos = position;
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }
    public static Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }
    public boolean equalZero(BigDecimal decimal){
        return   decimal.compareTo(BigDecimal.ZERO)==0;
    }
    @Override
    protected void initData() {
        mPresenter.getRate("USD","CNY");



    }


    public void refreshCoin(CoinBean1 data) {
        if (data.getSymbol().equals(code)) {
            BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
            BigDecimal b = new BigDecimal(Integer.toString(100));
            String changerate = a.multiply(b).doubleValue()>0?"+"+a.multiply(b).doubleValue()+"%":a.multiply(b).doubleValue()+"%";
            tvUSDT.setText((equalZero(new BigDecimal(data.getClose()))?"0":new BigDecimal(data.getClose()).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()));
            tvRMB.setText(String.format("≈%s "," ￥"+new BigDecimal(rate).multiply(new BigDecimal(data.getClose())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
            tvRate.setText(changerate);
            tvNum.setText(new BigDecimal(data.getVolume()).compareTo(new BigDecimal(1000))<0?new BigDecimal(data.getVolume()).setScale(4, RoundingMode.DOWN).stripTrailingZeros().toPlainString():
                    new BigDecimal(data.getVolume()).divide(new BigDecimal(1000)).setScale(2, RoundingMode.DOWN).stripTrailingZeros().toPlainString()+"K");
            tvLow.setText(new BigDecimal(data.getLow()).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
            tvHigh.setText(new BigDecimal(data.getHigh()).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
            tvUSDT.setTextColor(ContextCompat.getColor(App.INSTANCE, data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
            tvRate.setTextColor(ContextCompat.getColor(App.INSTANCE, data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
        }
    }

    public void setRate(RateBean bean) {
        rate = bean.getRate();
        mPresenter.getData(code,isLevel);
        if(isLevel){
            LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1,CoinBean1.class).observe(this, this::refreshCoin);

           //mPresenter.initNewSocket1();
        }else {
          // mPresenter.initNewSocket();
            LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class).observe(this, this::refreshCoin);
        }

     /*   LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class)
                .observe(this, this::refreshCoin);*/
    }

    public void updateUI(List<CoinBean1> list) {
        for(CoinBean1 data:list){
            if(data.getSymbol().equals(code)){
                BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
                BigDecimal b = new BigDecimal(Integer.toString(100));
                String changerate = a.multiply(b).doubleValue()>0?"+"+a.multiply(b).doubleValue()+"%":a.multiply(b).doubleValue()+"%";
                tvUSDT.setText((equalZero(new BigDecimal(data.getClose()))?"0":new BigDecimal(data.getClose()).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()));
                tvRMB.setText(String.format("≈%s ","≈ ￥"+new BigDecimal(rate).multiply(new BigDecimal(data.getClose())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
                tvRate.setText(changerate);
                tvNum.setText(new BigDecimal(data.getVolume()).compareTo(new BigDecimal(1000))<0?new BigDecimal(data.getVolume()).setScale(4, RoundingMode.DOWN).stripTrailingZeros().toPlainString():
                        new BigDecimal(data.getVolume()).divide(new BigDecimal(1000)).setScale(2, RoundingMode.DOWN).stripTrailingZeros().toPlainString()+"K");
                tvLow.setText(new BigDecimal(data.getLow()).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                tvHigh.setText(new BigDecimal(data.getHigh()).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                tvUSDT.setTextColor(ContextCompat.getColor(App.INSTANCE, data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
                tvRate.setTextColor(ContextCompat.getColor(App.INSTANCE, data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));

            }
        }
    }

    @Override
    protected void onDestroy() {
       // mPresenter.closeSocket();
        if(bottomMap!=null){
            bottomMap.clear();
            bottomMap = null;
        }
        super.onDestroy();
    }
}

