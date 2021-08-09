package com.sskj.mine.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.shizhefei.view.coolrefreshview.OnPullListener;
import com.shizhefei.view.coolrefreshview.SimpleOnPullListener;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.presenter.MyAssertFragmentPresenter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


@Route(path = RConfig.MINER_ASSERTS)
public class MyAssertFragment extends BaseFragment<MyAssertFragmentPresenter> {
    @Autowired
    int type;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.tvCNY)
    TextView tvCNY;
    @BindView(R2.id.tv_US)
    TextView tv_US;
    @BindView(R2.id.tv_huazhuan)
    TextView tvHuazhuan;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter adapter;
    DecimalFormat df = new DecimalFormat("0");
    String id = SPUtil.get(SPConfig.ID,"");
    //  ArrayList<MyAssertBean> myList = new ArrayList<MyAssertBean>();

    @Override
    protected int getLayoutId() {
        ARouter.getInstance().inject(this);
        return R.layout.mine_fragment_asserts;
    }

    @Override
    protected MyAssertFragmentPresenter getPresenter() {
        return new MyAssertFragmentPresenter();
    }

    @Override
    public void initData() {

         mPresenter.getData(id,type);
    }

    @Override
    protected void initEvent() {
        tvHuazhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.MINER_TRANSFER).withInt("type",type).navigation();
            }
        });
    coolRefreshView.addOnPullListener(new SimpleOnPullListener() {
        @Override
        public void onRefreshing(CoolRefreshView refreshView) {
            mPresenter.getData(id,type);
        }
    });
    }

    @Override
    protected void initView() {
        RxBus.getDefault().register(this);
       rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = SlimAdapter.create().register(R.layout.mine_fragment_asserts_item, new SlimInjector<MyAssertBean.FundBean>() {
            @Override
            public void onInject(MyAssertBean.FundBean bean, IViewInjector iViewInjector, List list) {
            iViewInjector.text(R.id.tv_type_name,bean.getStockCode())
                        .text(R.id.tv_keyong,bean.getUsableFund()+"")
                        .text(R.id.tv_dongjie,bean.getFrozen()+"")
                        .text(R.id.tv_zehe,bean.getToCny())
                        .clicked(R.id.ll_asserts, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        ARouter.getInstance().build(RConfig.MYASSERTDETAILSACTIVITY)
                                .withString("code",bean.getStockCode())
                                .withString("keyong",bean.getUsableFund()+"")
                                .withString("dongjie",bean.getFrozen()+"")
                                .withString("zehe",bean.getToCny())
                                .withInt("type",type)
                                .navigation();
                        }
                    });
            }
        }).attachTo(rv);

    }

    public void getAssert(MyAssertBean data) {
        if (coolRefreshView != null && coolRefreshView.isRefreshing()) {
            coolRefreshView.setRefreshing(false);
        }
        BigDecimal bigDecimal =  new BigDecimal(String.valueOf(Double.valueOf(data.getTotalUsdt())));
        tv_US.setText(bigDecimal+"USDT");
        BigDecimal bigDecimal1 =  new BigDecimal(String.valueOf(Double.valueOf(data.getTotalCny())));
        tvCNY.setText(bigDecimal1+"");
     /*  double d = Double.valueOf(data.getTotalUsdt());
        BigDecimal bigDecimal =  new BigDecimal(d,8,true);
        BigDecimal bigDecimal1 =  new BigDecimal(Double.valueOf(data.getTotalCny()));*/

   /*  if(data.getTotalUsdt().compareTo(new BigDecimal("0"))==0){
         tv_US.setText(0+"USDT");
         tvCNY.setText("≈"+0+"CNY");
     }*/


        adapter.updateData(data.getFund());
    }
}
