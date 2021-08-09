package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.presenter.TransferRecordActivityPresenter;
import butterknife.BindView;


@Route(path = RConfig.MINE_ACTIVITY_TRANSFER_RECORD)
public class TransferRecordActivity extends BaseActivity<TransferRecordActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_transfer_record;
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
      /*  SlimAdapter.create().register(R.layout.mine_fragment_asserts_item, new SlimInjector() {
            @Override
            public void onInject(Object o, IViewInjector iViewInjector, List list) {

            }
        }).attachTo(rv).updateData(new ArrayList());*/
    }

    @Override
    public TransferRecordActivityPresenter getPresenter() {
        return new TransferRecordActivityPresenter();
    }


}
