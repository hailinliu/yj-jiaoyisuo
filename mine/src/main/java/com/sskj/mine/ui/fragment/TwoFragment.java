package com.sskj.mine.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.TwoFragmentPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.TWOFRAGMENT)
public class TwoFragment extends BaseFragment<TwoFragmentPresenter> {
    private SlimAdapter slimAdapter;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_detail1;
    }

    @Override
    protected void initData() {
        //super.initData();
        mPresenter.getFaBi("0","");
        LiveDataBus.get().with(RxBusCode.REFRESH).observe(this,this::refresh);
    }

    private void refresh(Object o) {
        mPresenter.getFaBi("0","");
    }

    @Override
    protected void initView() {
        //super.initView();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libTextGray)));
        slimAdapter = SlimAdapter.<FaBiBean.DataBean>create().register(R.layout.mine_recy_item_bibi, new SlimInjector<FaBiBean.DataBean>() {
            @Override
            public void onInject(FaBiBean.DataBean bean, IViewInjector iViewInjector, List list) {
                ImageView imageView = (ImageView)iViewInjector.findViewById(R.id.image);
                ImageUtil.setImage(HttpConfig.BASE_URL+bean.getCoin().getImgUrl(),imageView);
                iViewInjector.text(R.id.tv_name,bean.getCoin().getUnit())
                        .text(R.id.tv_num,new BigDecimal(bean.getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .clicked(R.id.ll_zong,(v)->{

                        });
                // iViewInjector.image(R.id.image,H)
            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected TwoFragmentPresenter getPresenter() {
        return new TwoFragmentPresenter();
    }

    public void setFaBi(List<FaBiBean.DataBean> data) {
        if(data!=null&&data.size()>0)
        slimAdapter.updateData(data);
    }
}
