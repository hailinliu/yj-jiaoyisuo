package com.sskj.hangqing.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.SoloBean;
import com.sskj.hangqing.presenter.SoloActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.SOLOACTIVITY)
public class SoloActivity extends BaseActivity<SoloActivityPresenter> {
    @BindView(R2.id.cancel)
    TextView tv_cancel;
    @BindView(R2.id.et_solo)
    EditText etSolo;
    @BindView(R2.id.rv)
    RecyclerView rv;
    private SlimAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_solo;
    }

    @Override
    protected void initData() {
       //super.initData();
        mPresenter.getSolo("");
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.hang_activity_solo_item, new SlimInjector<SoloBean>() {
            @Override
            public void onInject(SoloBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_content,bean.getSymbol())
                .clicked(R.id.ll_content,(v)->{
                    ARouter.getInstance().build(RConfig.HANG_MARKET).withString("code",bean.getSymbol()).withBoolean("isSolo",true).navigation();
                });
            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RxTextView.textChanges(etSolo).subscribe(data->{
            if(data==null){
                mPresenter.getSolo("");
            }else {
                mPresenter.getSolo(data.toString());
            }

        });
    }

    @Override
    public SoloActivityPresenter getPresenter() {
        return new SoloActivityPresenter();
    }


    public void setSata(List<SoloBean> list) {
        adapter.updateData(list);
    }
}
