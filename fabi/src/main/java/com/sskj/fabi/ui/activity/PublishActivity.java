package com.sskj.fabi.ui.activity;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.PublishActivityPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.FabiBuySellEnum;
import com.sskj.lib.model.room.UserViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.FABI_PUBLISH)
public class PublishActivity extends BaseActivity<PublishActivityPresenter> {
    @BindView(R2.id.tvDesc)
    TextView tvDesc;
    @BindView(R2.id.etNum)
    EditText etNum;
    @BindView(R2.id.etMin)
    EditText etMin;
    @BindView(R2.id.etMax)
    EditText etMax;
    @BindView(R2.id.etPrice)
    EditText etPrice;
    @BindView(R2.id.etRemark)
    EditText etRemark;
    @BindView(R2.id.remark_layout)
    LinearLayout remarkLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.tvAddPayType)
    TextView tvAddPayType;
    @BindView(R2.id.llPayType)
    LinearLayout llPayType;
    @BindView(R2.id.btOrder)
    Button btOrder;
    @BindView(R2.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    @BindView(R2.id.tvPayType)
    TextView tvPayType;
    @Autowired
    boolean isBuy = true;

    private String[] titles = {App.INSTANCE.getString(R.string.fabipublishActivity1) + FabiBuySellEnum.BUY.getTitle(), App.INSTANCE.getString(R.string.fabipublishActivity1) + FabiBuySellEnum.SELL.getTitle()};
    private SlimAdapter slimAdapter;
    private HashMap<String, Boolean> payMap = new HashMap<>();
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private BottomSheetDialog priceModeSheet;
    private String limitNum;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_publish;
    }

    @Override
    public PublishActivityPresenter getPresenter() {
        return new PublishActivityPresenter();
    }

    @Override
    protected void initView() {
        ArrayList<CustomTabEntity> titlesTab = new ArrayList<>();
        titlesTab.add(new TabItem(titles[0], 0, 0));
        titlesTab.add(new TabItem(titles[1], 0, 0));
        commonTabLayout.setTabData(titlesTab);
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
        initRecy();
        ClickUtil.click(btOrder, () -> {
            if (checkParam()) {
                StringBuilder stringBuilder = new StringBuilder();
                Flowable.fromIterable(payMap.keySet())
                        .map(s -> {
                            if (payMap.get(s))
                                stringBuilder.append("," + s);
                            return stringBuilder;
                        })
                        .subscribe();

                System.out.println(stringBuilder);
                TipFabiUtil.showPublish(this, isBuy,
                        etPrice.getText().toString(),
                        etNum.getText().toString(), str -> {
                            mPresenter.publish(isBuy, etRemark.getText().toString(),
                                    etNum.getText().toString(),
                                    etMin.getText().toString(),
                                    etMax.getText().toString(),
                                    etPrice.getText().toString(),
                                    stringBuilder.substring(1), str[0]);
                        });
            }
        });
        setRightImg(R.mipmap.fabi_icon_publish_record, v -> {

            ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD).withBoolean(Constans.IS_PUBLISH,true).navigation();
        });

        ClickUtil.click(tvAddPayType, () -> {
            ARouter.getInstance().build(RConfig.MINE_SECURITY_CENTER).navigation();
        });
        etMin.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(2)});
        etMax.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(2)});
        etPrice.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(2)});

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    isBuy = true;
                    changeUI();
                } else if (position == 1) {
                    isBuy = false;
                    changeUI();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        commonTabLayout.setCurrentTab(isBuy ? 0 : 1);
        changeUI();

    }

    private void changeUI() {
        btOrder.setText(isBuy ? App.INSTANCE.getString(R.string.fabipublishActivity2) : App.INSTANCE.getString(R.string.fabipublishActivity3));
        tvPayType.setText(isBuy ? App.INSTANCE.getString(R.string.fabipublishActivity4) : App.INSTANCE.getString(R.string.fabipublishActivity5));
        tvAddPayType.setText(isBuy ? App.INSTANCE.getString(R.string.fabipublishActivity6) : App.INSTANCE.getString(R.string.fabipublishActivity7));
        etNum.setHint(String.format(App.INSTANCE.getString(R.string.fabipublishActivity8), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
        etPrice.setHint(String.format(App.INSTANCE.getString(R.string.fabipublishActivity9), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
        tvDesc.setText(String.format(App.INSTANCE.getString(R.string.fabipublishActivity10), isBuy ? FabiBuySellEnum.BUY.getTitle() : FabiBuySellEnum.SELL.getTitle()));
    }

    @Override
    protected void onResume() {
        if (mPresenter != null) {
            mPresenter.requestPayWayList();
        }
        super.onResume();
    }

    @Override
    protected void initData() {
        mPresenter.requestPayWayList();
        mPresenter.requestUnitPrice();
        mPresenter.requestLimitNum();
    }

    private boolean checkParam() {
        if (TextUtils.isEmpty(etNum.getText())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity11));
            return false;
        }
        if (Double.valueOf(limitNum) > Double.valueOf(etNum.getText().toString())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity12) + limitNum);
            return false;
        }
        if (TextUtils.isEmpty(etMin.getText())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity13));
            return false;
        }
        if (Double.valueOf(etMin.getText().toString()) == 0) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity14));
            return false;
        }
        if (TextUtils.isEmpty(etMax.getText())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity15));
            return false;
        }
        if (Double.valueOf(etMax.getText().toString()) == 0) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity16));
            return false;
        }
        double allMoney = new BigDecimal(etNum.getText().toString()).multiply(new BigDecimal(etPrice.getText().toString())).doubleValue();
        if (allMoney < Double.valueOf(etMin.getText().toString())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity17));
            return false;
        }

        if (Double.valueOf(etMin.getText().toString()) > Double.valueOf(etMax.getText().toString())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity18));
            return false;
        }

        if ((payMap.get("1") == null || !payMap.get("1")) && (payMap.get("2") == null || !payMap.get("2")) && (payMap.get("3") == null || !payMap.get("3"))) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabipublishActivity19));
            return false;
        }

        return true;
    }

    private void initRecy() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libDivider))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.fabi_recy_item_pay_way, new SlimInjector<PayWayItem>() {
            @Override
            public void onInject(PayWayItem data, IViewInjector injector, List payloads) {
                injector.text(R.id.tvPayType, data.getPayType() == 1 ? App.INSTANCE.getString(R.string.fabipublishActivity20) : data.getPayType() == 2 ? App.INSTANCE.getString(R.string.fabipublishActivity21) : App.INSTANCE.getString(R.string.fabipublishActivity22))
                        .image(R.id.ivPayType, data.getPayType() == 1 ? R.mipmap.fabi_icon_wx : data.getPayType() == 2 ? R.mipmap.fabi_icon_alipay : R.mipmap.fabi_icon_bank)
                        .text(R.id.tvNumber, data.getAccount())
                        .with(R.id.checkbox, view -> {
                            CheckBox checkBox = (CheckBox) view;
//                            if (injector.pos() == 0) {
                            payMap.put(data.getPayType() == 1 ? "1" : data.getPayType() == 2 ? "2" : "3", true);
                            checkBox.setChecked(false);
//                            }
                            checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.fabi_selector_checkbox_rect, 0);
                        })
                        .checkChangeListener(R.id.checkbox, (buttonView, isChecked) -> {
                            payMap.put(data.getPayType() == 1 ? "1" : data.getPayType() == 2 ? "2" : "3", isChecked);
                        });
            }
        });
        slimAdapter.attachTo(recyclerView);

    }

    public void updatePayWay(List<PayWayItem> data) {
        payMap.clear();
        if (data==null){
            return;
        }
        Flowable.fromIterable(data)
                .filter(fabiPayWayBean -> !fabiPayWayBean.isStatus())
                .map(fabiPayWayBean -> {
                    payMap.put(fabiPayWayBean.getPayType() + "", true);
                    return fabiPayWayBean;
                })
                .toList()
                .subscribe(fabiPayWayBeans -> {
                    if (fabiPayWayBeans.size() == 3) {
                        tvAddPayType.setVisibility(View.GONE);
                    }
                    slimAdapter.updateData(fabiPayWayBeans);
                }, e -> {
                });
    }

    public void publishSuccess() {
        finish();
    }

    public void updatePrice(String price) {
        etPrice.setText(price);
    }

    public void cancelShopSuccess() {
        finish();
    }

    public void updateLimitNum(String value) {
        limitNum = value;
    }
}
