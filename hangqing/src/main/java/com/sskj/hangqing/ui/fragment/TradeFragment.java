package com.sskj.hangqing.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.PushTradeBean;
import com.sskj.hangqing.bean.TradeBean;
import com.sskj.hangqing.bean.TurnoverBean;
import com.sskj.hangqing.bean.rxbus.MarketCoinBean;
import com.sskj.hangqing.presenter.TradeFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.enums.BuySellEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.HANG_FRAGMENT_TRADE)
public class TradeFragment extends BaseFragment<TradeFragmentPresenter> {
    @BindView(R2.id.tvTime)
    TextView tvTime;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @Autowired
    int type;
    @Autowired
    String code;
    private SlimAdapter slimAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_trade;
    }

    @Override
    public TradeFragmentPresenter getPresenter() {
        return new TradeFragmentPresenter();
    }

    @Override
    public void initView() {  
        ARouter.getInstance().inject(this);
        initRecy();
    }

    @Override
    public void initData() {
        mPresenter.getTurnoverAmount("20",code);
        //mPresenter.getData(code,type);
        mPresenter.initSocket(code);
        LiveDataBus.get().with(RxBusCode.CHANGE_MARKET_CODE,MarketCoinBean.class)
                .observe(this,this::changeCoin);
        tvPrice.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment1),code.replace("_","/").toUpperCase().split("/")[1]));
        tvNum.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2),code.replace("_","/").toUpperCase().split("/")[0]));
    }

    private void initRecy() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_trade, new SlimInjector<TurnoverBean>() {
            @Override
            public void onInject(TurnoverBean data, IViewInjector injector, List payloads) {
                injector.text(R.id.tvTime, TimeFormatUtil.SF_FORMAT_H.format(new Date(data.getTime())));
                injector.text(R.id.tvPrice, data.getPrice());
                injector.textColor(R.id.tvPrice,ContextCompat.getColor(App.INSTANCE, "BUY".equals(data.getDirection())?
                        BuySellEnum.BUY.getColorRes() : BuySellEnum.SELL.getColorRes()));
                injector.text(R.id.tvNum, data.getAmount());
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
    }
    public void changeCoin(MarketCoinBean marketCoinBean) {
        code = marketCoinBean.getCode();
        if (tvNum == null) {
            return;
        }
        String replace = code.replace("_", "/");
       // mPresenter.getData(code,type);
        mPresenter.sendCode(code);
        tvPrice.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment1),code.replace("_","/").toUpperCase().split("/")[1]));
        tvNum.setText(String.format(App.INSTANCE.getString(R.string.hangqingtradeFragment2),code.replace("_","/").toUpperCase().split("/")[1]));

    }

    public void updateUI(List<TradeBean> data) {
        if (slimAdapter == null)
            return;
        slimAdapter.updateData(data);
    }

    @Override
    public void onDestroy() {
        mPresenter.closeWebSocket();
        super.onDestroy();
    }

    public void updateData(PushTradeBean data) {
        if (data.getCode().equalsIgnoreCase(code)) {
            Flowable.fromIterable(data.getData())
                    .take(20)
                    .toList()
                    .subscribe((tradeBeans, throwable) -> {
                        slimAdapter.updateData(tradeBeans);
                    });

        }
    }
    List<TurnoverBean> bean1 =new ArrayList<>();
    public void updata(List<TurnoverBean> bean) {
        if(bean1.size()<=20){
            bean1.addAll(bean);
        } else {
            for(int i=0;i<bean.size();i++){
                bean1.remove(0);
            }
            int size = bean1.size();
            bean1.addAll(bean);
        }


        slimAdapter.updateData(bean1);
    }
}
