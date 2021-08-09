package com.sskj.tibi.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.util.ImageUtil;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.presenter.SelectCoinActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.SELECTCOINACTIVITY)
public class SelectCoinActivity extends BaseActivity<SelectCoinActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView rv;
    @Autowired
    ArrayList<CoinListBean.DataBean> list;
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_address_list1;
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        setTitle(App.INSTANCE.getString(R.string.tibi_bi_type));
        SlimAdapter slimAdapter = SlimAdapter.create().register(R.layout.tibi_recy_address_item, new SlimInjector<CoinListBean.DataBean>() {
            @Override
            public void onInject(CoinListBean.DataBean bean, IViewInjector iViewInjector, List list) {
          ImageView imageView =  (ImageView) iViewInjector.findViewById(R.id.image);
                ImageUtil.setImage(HttpConfig.BASE_URL+bean.getCoin().getImgUrl(),imageView);
                iViewInjector.text(R.id.tv_jiancheng,bean.getCoin().getUnit())
                        .text(R.id.tv_quancheng,"("+bean.getCoin().getName()+")")
                        .clicked(R.id.ll_coinlist,(v)->{
                        Intent intent = new Intent();
                        intent.putExtra("data",bean);
                        setResult(1,intent);
                        finish();
                        });
            }

        }).attachTo(rv).updateData(list);
    }

    @Override
    public SelectCoinActivityPresenter getPresenter() {
        return new SelectCoinActivityPresenter();
    }
}
