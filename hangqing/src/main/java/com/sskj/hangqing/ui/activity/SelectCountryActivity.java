package com.sskj.hangqing.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.CountryBean;
import com.sskj.hangqing.presenter.SelectCountryActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.SELECTCOUNTRYACTIVITY)
public class SelectCountryActivity extends BaseActivity<SelectCountryActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView rv;
    SlimAdapter<CountryBean> slimAdapter ;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_select_list;
    }

    @Override
    protected void initView(){
       // super.initView();
        setTitle(App.INSTANCE.getString(R.string.hang_select_country));
        rv.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create().register(R.layout.hang_activity_select_country_item, new SlimInjector<CountryBean>() {
            @Override
            public void onInject(CountryBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_content,"("+bean.getEnName()+")"+"+"+bean.getAreaCode())
                .clicked(R.id.ll_container, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("country",bean.getZhName()+"("+bean.getEnName()+")"+"+"+bean.getAreaCode());
                    setResult(1,intent);
                    finish();
                    }
                });

            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initData() {
       // super.initData();
       mPresenter.getCountry();
       // rv.setAdapter();
    }

    @Override
    public SelectCountryActivityPresenter getPresenter() {
        return new SelectCountryActivityPresenter();
    }

    public void setUI(List<CountryBean> data) {
        if(data!=null&&data.size()>0){
            slimAdapter.updateData(data);
        }

    }
}
