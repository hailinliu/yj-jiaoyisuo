package com.sskj.pay.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.HideUtil;
import com.sskj.pay.R;
import com.sskj.pay.R2;
import com.sskj.pay.bean.PayTypeBean;
import com.sskj.pay.component.DaggerUserDataComponent;
import com.sskj.pay.presenter.PayTypeFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


@Route(path = RConfig.PAY_PAY_TYPE)//支付方式
public class PayTypeFragment extends BaseFragment<PayTypeFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.tvAdd)
    TextView tvAdd;
    private SlimAdapter slimAdapter;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;

    @Override
    protected int getLayoutId() {
        return R.layout.pay_activity_pay_type;
    }

    @Override
    public PayTypeFragmentPresenter getPresenter() {
        return new PayTypeFragmentPresenter();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.transparent))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.pay_recy_item_pay_type, new SlimInjector<PayTypeBean>() {
            @Override
            public void onInject(PayTypeBean data, IViewInjector injector, List payloads) {
                String account;
                if (data.getPayTypeEnum() == PayTypeEnum.BANK) {
                    account = HideUtil.getBankCardHide(data.getAccount());
                } else {
                    account = HideUtil.getWxAlipayHide(data.getAccount());
                }
                injector
                        .text(R.id.tvPayType, data.getPayTypeEnum().getName())
                        .text(R.id.tvAccount, account)
                        .text(R.id.tvName, userData.getUsername())
                        .image(R.id.ivPayType, data.getPayTypeEnum().getRes())
                        .checked(R.id.checkbox, data.isOpen())
                        .clicked(R.id.llCheckbox, (v) -> {
                            mPresenter.togglePayType(!data.isOpen(), data.getId());
                        })
                        .clicked(R.id.tvEdit, v -> {
                            if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {
                                return;
                            }
                            ARouter.getInstance().build(RConfig.PAY_ADD_PAY_TYPE).withSerializable(Constans.PAY_TYPE, data).navigation();

                        })
                ;
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ClickUtil.click(tvAdd, () -> {
            if (!AppCircleCheckUtil.checkAuth(getActivity(), userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.PAY_ADD_PAY_TYPE).withSerializable(Constans.PAY_TYPE, new PayTypeBean(PayTypeEnum.WX)).navigation();

        });
    }


    @Override
    protected void initData() {
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                mPresenter.getData();
            } else {
                userData = null;
            }
        });
        LiveDataBus.get().with(RxBusCode.PAY_TYPE,Object.class)
                .observe(this,s -> {
                    updateData((ArrayList<PayTypeBean>) s);
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.getData();
        }
    }

    public void updateData(ArrayList<PayTypeBean> payTypeBeans) {
        if (userData != null) {
            for (PayTypeBean payTypeBean : payTypeBeans) {
                payTypeBean.setName(userData.getUsername());
            }
        }
        if (payTypeBeans.size() >= 3) {
            tvAdd.setVisibility(View.GONE);
        }else {
            tvAdd.setVisibility(View.VISIBLE);

        }
        slimAdapter.updateData(payTypeBeans);
    }

    public void toggleSuccess() {
        mPresenter.getData();
    }
}

