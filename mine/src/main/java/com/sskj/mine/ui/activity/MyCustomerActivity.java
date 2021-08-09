package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.CustomerBean;
import com.sskj.mine.presenter.MyCustomerActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@Route(path = RConfig.MYCUSTOMERACTIVITY)
public class MyCustomerActivity extends BaseActivity<MyCustomerActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;

    @BindView(R2.id.rv)
    RecyclerView rv;
    String id = SPUtil.get(SPConfig.ID,"");
    private SlimAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_promotion_my_guess;
    }

    @Override
    protected void initData() {
       mPresenter.getMyCustomer(id);
    }

    @Override
    protected void initView() {
        tvTitle.setText(App.INSTANCE.getString(R.string.mine_my_guess));
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.mine_activity_tuiguang_customer_item, new SlimInjector<CustomerBean.DataBean>() {
            @Override
            public void onInject(CustomerBean.DataBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_zhanghao, TextUtils.isEmpty(bean.getTel())?bean.getEmail():bean.getTel())
                            .text(R.id.tv_name,bean.getUsername())
                            .text(R.id.tv_time,bean.getCreateTime().split(" ")[0]);

            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public MyCustomerActivityPresenter getPresenter() {
        return new MyCustomerActivityPresenter();
    }


    public void setCustomer(List<CustomerBean.DataBean> list) {

            adapter.updateData(list);


    }
}
