package com.sskj.level.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.TradeInfoBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.TradeActivityPresenter;
import com.sskj.level.util.CoinUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.TipUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@Route(path = RConfig.LEVEL_TRADE)//交易单界面
public class TradeActivity extends BaseActivity<TradeActivityPresenter> {
    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.slidingTabLayout)
    CommonTabLayout slidingTabLayout;
    ArrayList<Fragment> mFragments;
    private Disposable subscribe;
    @BindView(R2.id.level_all_pc)
    TextView levelAllPc;
    @Autowired
    public String code;
    private MaterialDialog allTip;
    private int curPos = 0;

    private BigDecimal winLossMoney;

    private List<TradeListBean> list;
    private TradeInfoBean bean;
    private Disposable changePriceDispo;
    private SafeSettingBean userData;

    @Override
    protected int getLayoutId() {

        return R.layout.level_activity_trade;
    }

    @Override
    public TradeActivityPresenter getPresenter() {
        return new TradeActivityPresenter();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
       // code = null;
        setTitle(App.INSTANCE.getString(R.string.leveltradeActivity1));
        ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.leveltradeActivity3), 0, 0));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.bibi_recordActivity4), 0, 0));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.level_cedan), 0, 0));
        mFragments = new ArrayList<>();
        mFragments.add((Fragment) ARouter.getInstance().build(RConfig.LEVEL_FRAGMENT_ENTRUST)
                .withString("code", code)
                .navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(RConfig.LEVEL_FRAGMENT_DEAL)
                .withString("code", code)
                .navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(RConfig.CANCELFRAGMENT)
                .withString("code", code)
                .navigation());
        slidingTabLayout.setTabData(tabItems, this, R.id.tradeFrameLayout, mFragments);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                curPos = position;
                if (0 == position) {
                    levelAllPc.setVisibility(View.INVISIBLE);
                } else {
                    levelAllPc.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
              //  mPresenter.getRisk();
               // mPresenter.getAllList();


            } else {
                userData = null;
            }
        });

        ClickUtil.click(levelAllPc, () -> {
            allTip = TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.leveltradeActivity5), () -> {
                allTip.dismiss();
                mPresenter.getAllList();
            });
            allTip.show();
        });
       // startTimer();
    }

    @Override
    protected void initData() {
        super.initData();

    }



    public void updateUI(List<TradeListBean> list) {

        this.list = list;
        updateUI();
    }

    public void updateUI() {
        winLossMoney = new BigDecimal("0");
        for (TradeListBean tradeListBean : list) {
            winLossMoney = winLossMoney.add(new BigDecimal(tradeListBean.getWinLoseMoney()));
        }
        if (bean != null) {
            bean.setFloatWinLoseFee(winLossMoney.toPlainString());
        }
      //  setItem(R.id.trade_item_fdyk, App.INSTANCE.getString(R.string.leveltradeActivity9), CoinUtil.keepUSDT(winLossMoney.toPlainString()), Double.valueOf(winLossMoney.toPlainString()) < 0);

    }

    @Override
    protected void onDestroy() {
        DisposUtil.close(subscribe);

        super.onDestroy();
    }

    public void setUI(String msg) {
        LiveDataBus.get().with(RxBusCode.LEVEL_ALL_PC).postValue(1);
        ToastUtil.showShort(msg);
    }
}
