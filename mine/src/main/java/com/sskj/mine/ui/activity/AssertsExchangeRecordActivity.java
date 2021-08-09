package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.sskj.lib.util.DateUtils;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.ExchangeRecordBean;
import com.sskj.mine.presenter.AssertsExchangeRecordActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.ASSERTS_EXCHANGE_RECORD_ACTIVITY)
public class AssertsExchangeRecordActivity extends BaseActivity<AssertsExchangeRecordActivityPresenter> {
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
        return R.layout.mine_activity_transfer_record;
    }

    @Override
    protected void initView() {
        tvTitle.setText(App.INSTANCE.getString(R.string.mine_conver_record));
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
    protected void initData() {
        mPresenter.getChangeRecord(id);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.mine_activity_transfer_record_item, new SlimInjector<ExchangeRecordBean.ListBean>() {
             @Override
             public void onInject(ExchangeRecordBean.ListBean bean, IViewInjector iViewInjector, List list) {
                 iViewInjector.text(R.id.tv_code,bean.getBaseCode())
                         .text(R.id.tv_transfer_num,bean.getDealNum()+"")
                         .text(R.id.tv_transfer_time, DateUtils.timeStamp2Date(bean.getCreateTime()+"",""));
             }
         }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    public AssertsExchangeRecordActivityPresenter getPresenter() {
        return new AssertsExchangeRecordActivityPresenter();
    }


    public void upDateUI(List<ExchangeRecordBean.ListBean> list) {
        adapter.updateData(list);
    }
}
