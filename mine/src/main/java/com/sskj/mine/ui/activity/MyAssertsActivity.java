package com.sskj.mine.ui.activity;


import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.utils.HttpUtils;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.shizhefei.view.coolrefreshview.SimpleOnPullListener;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.MyAssertsActivityPresenter;
import com.sskj.mine.ui.fragment.MyAssertFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.HttpUrl;

@Route(path =RConfig.MINER_ASSERTS_ACTIVITY)
public class MyAssertsActivity extends BaseActivity<MyAssertsActivityPresenter> {
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.tv_US)
    TextView tv_US;
    @BindView(R2.id.cbSee)
    CheckBox cbSee;
    @BindView(R2.id.ivSee)
    ImageView imageView;
    private boolean isSee = true;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @BindView(R2.id.tv_chongbi)
    LinearLayout tvRecharge;
    @BindView(R2.id.tv_tibi)
    LinearLayout tvWithdraw;
    private SlimAdapter adapter;
    String id = SPUtil.get(SPConfig.ID,"");
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_my_assert;
    }

    @Override
    public MyAssertsActivityPresenter getPresenter() {
        return new MyAssertsActivityPresenter();
    }

    @Override
    protected void initView() {
       // super.initView();
        RxBus.getDefault().register(this);
        DaggerUserDataComponent.create().inject(this);
        tvTitle.setText(App.INSTANCE.getString(R.string.mine_my_asset));
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.mine_fragment_asserts_item, new SlimInjector<MyAssertBean.FundBean>() {
            @Override
            public void onInject(MyAssertBean.FundBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_type_name,bean.getStockCode())
                        .text(R.id.tv_keyong,bean.getUsableFund()+"")
                        .text(R.id.tv_dongjie,bean.getFrozen()+"")
                        .text(R.id.tv_zehe,bean.getToUsdt())
                        .clicked(R.id.ll_asserts, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build(RConfig.MYASSERTDETAILSACTIVITY)
                                        .withString("code",bean.getStockCode())
                                        .withString("keyong",bean.getUsableFund()+"")
                                        .withString("dongjie",bean.getFrozen()+"")
                                        .withString("zehe",bean.getToUsdt())
                                        .withInt("type",1)
                                        .navigation();
                            }
                        });
                if(bean.getStockCode().equals("USDT")){
                   ImageView imageView =  (ImageView) iViewInjector.findViewById(R.id.image);
                 imageView.setImageDrawable(getResources().getDrawable(R.mipmap.mine_usdt));
                }else {
                    ImageUtil.setImage(HttpConfig.BASE_URL+bean.getImg(),(ImageView) iViewInjector.findViewById(R.id.image));
                }
            }
        }).attachTo(rv);
    }

    @Override
    protected void initData() {
       /* userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);

            } else {
                userData = null;
            }

        });*/
      //  super.initData();
        mPresenter.getData(id,1);
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        coolRefreshView.addOnPullListener(new SimpleOnPullListener() {
            @Override
            public void onRefreshing(CoolRefreshView refreshView) {
                mPresenter.getData(id,1);
            }
        });
        ClickUtil.click(tvRecharge, () -> {
            if (!AppCircleCheckUtil.checkLogin(this, userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.TIBI_RECHARGE).navigation();
        });


        ClickUtil.click(tvWithdraw, () -> {
            if (!AppCircleCheckUtil.checkLogin(this, userData)) {
                return;
            }

            if (!AppCircleCheckUtil.checkHighAuth(this, userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkTradePwd(this, userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.TIBI_WITHDRAW).navigation();
        });
    }
    public void getAssert(MyAssertBean data) {
        if (coolRefreshView != null && coolRefreshView.isRefreshing()) {
            coolRefreshView.setRefreshing(false);
        }
        BigDecimal bigDecimal =  new BigDecimal(String.valueOf(Double.valueOf(data.getTotalUsdt())));
        tv_US.setText(bigDecimal+"USDT");
        cbSee.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isSee = isChecked;
            tv_US.setText(isSee ? (data != null ?bigDecimal+"": "----") : "****");
            //  BigDecimal bigDecimal =  new BigDecimal(Double.valueOf(data.getTotalUsdt()));
               /* if(data.getTotalUsdt().compareTo(new BigDecimal("0"))==0){
                    tvUSDT.setText(isSee ? (data != null ?0+"": "----") : "****");
                }else{
                    tvUSDT.setText(isSee ? (data != null ?data.getTotalUsdt()+"": "----") : "****");
                }*/

        });
        tv_US.setText(isSee ? (data != null ?bigDecimal+"": "----") : "****");
      //  BigDecimal bigDecimal1 =  new BigDecimal(String.valueOf(Double.valueOf(data.getTotalCny())));
        adapter.updateData(data.getFund());
    }
}
