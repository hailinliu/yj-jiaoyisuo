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
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.bean.LevelBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.ThirdFragmentPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.THIRDFRAGMENT)
public class ThirdFragment extends BaseFragment<ThirdFragmentPresenter> {
    @BindView(R2.id.rv)
    RecyclerView rv;
    private SlimAdapter slimAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_detail1;
    }

    @Override
    protected ThirdFragmentPresenter getPresenter() {
        return new ThirdFragmentPresenter();
    }

    @Override
    protected void initView() {
        //super.initView();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libTextGray)));
        slimAdapter = SlimAdapter.<LevelBean.DataBean>create().register(R.layout.mine_recy_item_bibi, new SlimInjector<LevelBean.DataBean>() {
            @Override
            public void onInject(LevelBean.DataBean bean, IViewInjector iViewInjector, List list) {
              ImageView  imageView= (ImageView)iViewInjector.findViewById(R.id.image);
              imageView.setImageResource(R.mipmap.mine_usdt);
                iViewInjector.text(R.id.tv_name,bean.getCoinId())
                        .text(R.id.tv_num,App.INSTANCE.getString(R.string.mine_availble)+":"+new BigDecimal(bean.getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.tv_suo,"锁仓:"+new BigDecimal(bean.getLockBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                // iViewInjector.image(R.id.image,H)
            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initData() {
        mPresenter.getData("0","");
        LiveDataBus.get().with(RxBusCode.REFRESH).observe(this,this::refresh);
        //super.initData();
    }

    private void refresh(Object o) {
        mPresenter.getData("0","");
    }

    List<LevelBean.DataBean> list = new ArrayList<>();
    public void setLevel(LevelBean.DataBean data) {
        if(data!=null){
            list.clear();
            list.add(data);
            slimAdapter.updateData(list);
        }

    }


}
