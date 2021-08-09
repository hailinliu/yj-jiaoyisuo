package com.sskj.fabi.ui.activity;


import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.base.App;
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.BuySellBean;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.presenter.OrderActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.SchemeUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * 下单页面
 */
@Route(path = RConfig.FABI_ORDER)
public class OrderActivity extends BaseActivity<OrderActivityPresenter> {
    @Autowired
    boolean isBuy;
    @Autowired
    BuySellBean.ContextBean data;
    @Autowired
    ArrayList<PayTypeEnum> payTypeBean;
    @BindView(R2.id.tvLimit)
    TextView tvLimit;
    @BindView(R2.id.tvName)
    TextView tvName;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    @BindView(R2.id.rbWX)
    RadioButton rbWX;
    @BindView(R2.id.rbAlipay)
    RadioButton rbAlipay;
    @BindView(R2.id.rbBank)
    RadioButton rbBank;
    @BindView(R2.id.rgPayType)
    RadioGroup rgPayType;
    @BindView(R2.id.tvNumTitle)
    TextView tvNumTitle;
    @BindView(R2.id.etNum)
    EditText etNum;
    @BindView(R2.id.tvAll)
    TextView tvAll;
    @BindView(R2.id.etMoney)
    EditText etMoney;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.tvMoney)
    TextView tvMoney;
    @BindView(R2.id.btCancel)
    Button btCancel;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.tvNameTitle)
    TextView tvNameTitle;
    @BindView(R2.id.llPwd)
    LinearLayout llPwd;
    private Disposable timeDispo;
    private Disposable numDispo;
    private Disposable moneyDispo;
    private ToastNextInputs inputs;
    private final int TIME = 45;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_order;
    }

    @Override
    public OrderActivityPresenter getPresenter() {
        return new OrderActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        updateUI();
        inputs = new ToastNextInputs();
        inputs
                .add(etNum, SchemeUtil.notEmpty(etNum))
                .add(etMoney, SchemeUtil.notEmpty(etMoney));
        if (!isBuy) {
            inputs.add(etPwd, SchemeUtil.notEmpty(etPwd));
        }
        DisposUtil.close(timeDispo);
        etNum.setFilters(new InputFilter[]{new MoneyValueFilter(4)});
        etMoney.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        timeDispo = Flowable.intervalRange(0, TIME + 1, 0, 1, TimeUnit.SECONDS)
                .compose(RxSchedulersHelper.transformer())
                .map(aLong -> TIME - aLong)
                .subscribe(aLong -> {
                    btCancel.setText(aLong + App.INSTANCE.getString(R.string.fabiorderActivity1));
                }, e -> {
                }, this::onBackPressed);


    }

    private void updateUI() {
        setTitle(isBuy ? App.INSTANCE.getString(R.string.fabiorderActivity2) : App.INSTANCE.getString(R.string.fabiorderActivity3));
        tvNameTitle.setText(isBuy ? App.INSTANCE.getString(R.string.fabiorderActivity4) : App.INSTANCE.getString(R.string.fabiorderActivity5));
        tvNumTitle.setText(isBuy ? App.INSTANCE.getString(R.string.fabiorderActivity66) : App.INSTANCE.getString(R.string.fabiorderActivity77));
        etNum.setHint(isBuy ? App.INSTANCE.getString(R.string.fabiorderActivity8) : App.INSTANCE.getString(R.string.fabiorderActivity9));
        etMoney.setHint(isBuy ? App.INSTANCE.getString(R.string.fabiorderActivity10) : App.INSTANCE.getString(R.string.fabiorderActivity11));
        llPwd.setVisibility(isBuy ? View.GONE : View.VISIBLE);
        tvNum.setText(CoinUtil.keepUSDT(data.getRemainAmount()));
        tvLimit.setText(data.getMinLimit() + "-" + data.getMaxLimit());
        tvPrice.setText(data.getPrice()+"");
        tvName.setText(data.getMemberName());

        for (int i = 0; i < payTypeBean.size(); i++) {
            if (payTypeBean.get(i) == PayTypeEnum.BANK) {
                rbBank.setVisibility(View.VISIBLE);
            }
            if (payTypeBean.get(i) == PayTypeEnum.ALIPAY) {
                rbAlipay.setVisibility(View.VISIBLE);

            }
            if (payTypeBean.get(i) == PayTypeEnum.WX) {
                rbWX.setVisibility(View.VISIBLE);
            }
        }
        for (int i = 0; i < rgPayType.getChildCount(); i++) {
            if (rgPayType.getChildAt(0).getVisibility() == View.VISIBLE) {
                rgPayType.check(rgPayType.getChildAt(i).getId());
                break;
            }
        }
        DisposUtil.close(numDispo);
        numDispo = RxTextView.afterTextChangeEvents(etNum)
                .subscribe(textViewAfterTextChangeEvent -> {
                    if (!etNum.isFocused()) {
                        return;
                    }
                    String text = textViewAfterTextChangeEvent.editable().toString();
                    if (TextUtils.isEmpty(text)) {
                        etMoney.setText("");
                        tvMoney.setText("0.00");
                        return;
                    }
                    String s = CoinUtil.keepRMB(new BigDecimal(text).multiply(new BigDecimal(data.getPrice())).toPlainString());
                    etMoney.setText(s);
                    tvMoney.setText(s);
                });
        DisposUtil.close(moneyDispo);
        moneyDispo = RxTextView.afterTextChangeEvents(etMoney)
                .subscribe(textViewAfterTextChangeEvent -> {
                    if (!etMoney.isFocused()) {
                        return;
                    }
                    String text = textViewAfterTextChangeEvent.editable().toString();
                    if (TextUtils.isEmpty(text)) {
                        etNum.setText("");
                        tvMoney.setText("0.00");
                        return;
                    }
                    String s = CoinUtil.keepUSDT(new BigDecimal(text).divide(new BigDecimal(data.getPrice()), 8, RoundingMode.HALF_UP).toPlainString());
                    etNum.setText(s);
                    tvMoney.setText(CoinUtil.keepRMB(text));

                });
        ClickUtil.click(tvAll, () -> {
            etMoney.requestFocus();
            String price = calculateAll(true, data.getPrice()+"", data.getRemainAmount()+"", data.getMaxLimit());
            etMoney.setText(price);
        });
        ClickUtil.click(btCancel, this::onBackPressed);
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                if (Double.valueOf(etMoney.getText().toString()) > Double.valueOf(data.getMaxLimit())) {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.fabiorderActivity12));
                    return;
                }
                String payType = null;
                if (rgPayType.getCheckedRadioButtonId() == R.id.rbBank) {
                    payType = PayTypeEnum.BANK.getType();
                }
                if (rgPayType.getCheckedRadioButtonId() == R.id.rbAlipay) {
                    payType = PayTypeEnum.ALIPAY.getType();
                }
                if (rgPayType.getCheckedRadioButtonId() == R.id.rbWX) {
                    payType = PayTypeEnum.WX.getType();
                }
/*
                mPresenter.order(payType, etPwd.getText().toString(),
                        data.getEntrustNo(), etNum.getText().toString(), isBuy, data.getMinPrice(), data.getMaxPrice(), "1", etMoney.getText().toString(), data.getPrice());*/

            }

        });
    }

    public void orderSuccess(OrderDetailBean orderDetailBean) {
        onBackPressed();
        ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD_DETAIL)
                .withString(Constans.ORDER_NUM, orderDetailBean.getDealEntrustNo())
                .withBoolean(Constans.IS_BUYER, isBuy)
                .navigation();
    }

    /**
     * 根据法币计价和出售数量计算总的数量与价格
     */
    private static String calculateAll(boolean isPrice, String price, String leftNum, String maxLimit) {

        String num = "0.0000";
        BigDecimal allMoney = new BigDecimal(leftNum).multiply(new BigDecimal(price));//最多购买金额
        BigDecimal allNum = new BigDecimal(maxLimit).divide(new BigDecimal(price), 8, BigDecimal.ROUND_DOWN);

        if (isPrice) {
            if (!TextUtils.isEmpty(price)) {
                double buyPrice = Math.min(Double.valueOf(maxLimit), allMoney.doubleValue());
                return CoinUtil.keepRMB(buyPrice);
            }
        } else {
            if (!TextUtils.isEmpty(price)) {
                double buyNum = Math.min(allNum.doubleValue(), Double.valueOf(leftNum));
                return CoinUtil.keepUSDT(buyNum);
            }
        }
        return num;
    }

    @Override
    protected void onDestroy() {
        DisposUtil.close(timeDispo);
        DisposUtil.close(moneyDispo);
        DisposUtil.close(numDispo);
        super.onDestroy();
    }
}
