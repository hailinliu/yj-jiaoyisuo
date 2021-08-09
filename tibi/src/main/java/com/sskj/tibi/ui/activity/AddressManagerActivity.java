package com.sskj.tibi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.ImageUtil;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.NewAddressListBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.presenter.AddressManagerActivityPresenteer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = RConfig.ADDRESSMANAGERACTIVITY)
public class AddressManagerActivity extends BaseActivity<AddressManagerActivityPresenteer> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @Autowired
    String type;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;
    private MVCCoolHelper<List<NewAddressListBean.DataBean.ContentBean>> mvcHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_address_list2;
    }

    @Override
    protected void initData() {
        //  super.initData();
        mPresenter.getAddress("0", "10", type);
        LiveDataBus.get().with(RxBusCode.REFRESHADDRESS,Integer.class).observe(this,this::refresh);
    }

    private void refresh(Integer integer) {
        mvcHelper.refresh();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibi_address));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create().register(R.layout.tibi_address_list_item, new SlimInjector<NewAddressListBean.DataBean.ContentBean>() {
            @Override
            public void onInject(NewAddressListBean.DataBean.ContentBean data, IViewInjector iViewInjector, List list) {
               ImageView imageView=  (ImageView) iViewInjector.findViewById(R.id.image);
                ImageUtil.setImage(HttpConfig.BASE_URL+data.getImgUrl(),imageView);
                iViewInjector.text(R.id.tv_remark,data.getRemark())
                        .text(R.id.tv_content,data.getAddress())
                        .clicked(R.id.ll_address, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                             Intent intent =new Intent();
                             intent.putExtra("data",data);
                             setResult(2,intent);
                             finish();
                            }
                        });
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<NewAddressListBean.DataBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<NewAddressListBean.DataBean.ContentBean>)
                page -> mPresenter.getAddress(String.valueOf(page - 1), "20", type), 20);
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    @Override
    protected void initEvent() {
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.TIBI_ADD_ADDRESS).withString("type", type).navigation();
            }
        });
    }

    @Override
    public AddressManagerActivityPresenteer getPresenter() {
        return new AddressManagerActivityPresenteer();
    }


}
