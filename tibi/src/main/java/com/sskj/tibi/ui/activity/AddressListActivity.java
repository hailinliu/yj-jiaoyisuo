package com.sskj.tibi.ui.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.TipUtil;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.AddressBean;
import com.sskj.tibi.bean.AddressListBean;
import com.sskj.tibi.presenter.AddressListActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 提币地址列表
 */
@Route(path = RConfig.TIBI_ADDRESS_LIST)
public class AddressListActivity extends BaseActivity<AddressListActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @Autowired
    boolean isSelect;
    private SlimAdapter slimAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_address_list;
    }

    @Override
    public AddressListActivityPresenter getPresenter() {
        return new AddressListActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibiaddAddressActivity1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.tibi_recy_item_address, new SlimInjector<AddressListBean>() {
            @Override
            public void onInject(AddressListBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvCoin, data.getCode())
                        .clicked(R.id.ivAdd, v -> {
                            ARouter.getInstance().build(RConfig.TIBI_ADD_ADDRESS).withString(Constans.TYPE, "2").navigation();
                        })
                        .with(R.id.recyclerViewItem, view -> {
                            RecyclerView recyclerViewItem = (RecyclerView) view;
                            recyclerViewItem.setLayoutManager(new LinearLayoutManager(AddressListActivity.this));
                            SlimAdapter<AddressBean> slimAdapterItem = SlimAdapter.create().register(R.layout.tibi_recy_item_address_item, new SlimInjector<AddressBean>() {
                                @Override
                                public void onInject(AddressBean o, IViewInjector iViewInjector, List list) {

                                    iViewInjector
                                            .text(R.id.tvName, o.getRemark())
                                            .text(R.id.tvAddress, o.getToAddr())
                                            .clicked(R.id.tvDelete, v -> {
                                                TipUtil.getSureTip(AddressListActivity.this, null, App.INSTANCE.getString(R.string.tibiaddressListActivity2), () -> {
                                                    mPresenter.deleteAddress(o.getId() + "");
                                                });
                                            });
                                }
                            }).attachTo(recyclerViewItem).updateData(data.getList());
                            ItemClickSupport.addTo(recyclerViewItem)
                                    .setOnItemClickListener((recyclerView1, i, view1) -> {
                                        if (isSelect) {
                                            LiveDataBus.get().with(RxBusCode.ADDRESS_POST,String.class)
                                                    .postValue(slimAdapterItem.getDataItem(i).getToAddr());
                                            finish();
                                        }
                                    });
                        });
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ArrayList<AddressListBean> addressListBeans = new ArrayList<>();
        addressListBeans.add(new AddressListBean("USDT", new ArrayList<>()));
        slimAdapter.updateData(addressListBeans);

    }

    @Override
    protected void initData() {
        mPresenter.getAddressList();
    }

    public void updateUI(List<AddressListBean> addressListBeans) {
        slimAdapter.updateData(addressListBeans);
    }

    public void deleteSuccess() {
        mPresenter.getAddressList();
    }

    @Override
    protected void onResume() {
        if (mPresenter!=null) {
            mPresenter.getAddressList();
        }
        super.onResume();
    }
}
