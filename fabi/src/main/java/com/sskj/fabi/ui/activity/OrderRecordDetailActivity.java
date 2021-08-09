package com.sskj.fabi.ui.activity;


import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binaryfork.spanny.Spanny;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.IntentUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.FabiOrderStatusEnum;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.presenter.OrderRecordDetailActivityPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.util.TipUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * 交易订单详情
 */
@Route(path = RConfig.FABI_ORDER_RECORD_DETAIL)
public class OrderRecordDetailActivity extends BaseActivity<OrderRecordDetailActivityPresenter> {

    @BindView(R2.id.tvStatus)
    TextView tvStatus;
    @BindView(R2.id.llPhone)
    LinearLayout llPhone;
    @BindView(R2.id.tvDesc)
    TextView tvDesc;
    @BindView(R2.id.tvAllMoney)
    TextView tvAllMoney;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    @BindView(R2.id.tvName)
    TextView tvName;
    @BindView(R2.id.tvPhone)
    TextView tvPhone;
    @BindView(R2.id.ivCopy)
    ImageView ivCopy;
    @BindView(R2.id.tvOrderNum)
    TextView tvOrderNum;
    @BindView(R2.id.tvTime)
    TextView tvTime;
    @BindView(R2.id.tvCode)
    TextView tvCode;
    @BindView(R2.id.tvBank)
    TextView tvBank;
    @BindView(R2.id.tvRemark)
    TextView tvRemark;
    @BindView(R2.id.tvUserInfo)
    TextView tvUserInfo;
    @BindView(R2.id.tvPayInfo)
    TextView tvPayInfo;
    @BindView(R2.id.llPayType)
    LinearLayout llPayType;
    @BindView(R2.id.btLeft)
    Button btLeft;
    @BindView(R2.id.btRight)
    Button btRight;
    @BindView(R2.id.ivQRCode)
    ImageView ivQRCode;
    @BindView(R2.id.ivPayType)
    ImageView ivPayType;
    @BindView(R2.id.llControl)
    LinearLayout llControl;
    @BindView(R2.id.llPayInfo)
    LinearLayout llPayInfo;
    @BindView(R2.id.llOrderNumInfo)
    LinearLayout llOrderNumInfo;
    private OrderDetailBean data;
    private Disposable timerDispo;
    private MaterialDialog tipCancleDialog;
    private Disposable detailDispo;
    private MaterialDialog codeDialog;
    private String orderId;
    @Autowired
    boolean isBuyer;
    @Autowired
    String orderNum;
    private MaterialDialog materialDialogAppeal;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_order_record_detail;
    }

    @Override
    public OrderRecordDetailActivityPresenter getPresenter() {
        return new OrderRecordDetailActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity1));
        ARouter.getInstance().inject(this);
        mPresenter.setOrderNum(orderNum);
    }

    @Override
    protected void initData() {

        mPresenter.getOrderInfo(orderNum);
    }

    public void updateUI(OrderDetailBean data) {
        this.data = data;
        orderId = data.getId();
        DisposUtil.close(timerDispo);
        Map<String, String> buyerMap = new HashMap<>();
        Map<String, String> sellerMap = new HashMap<>();
        buyerMap.put("1", FabiOrderStatusEnum.待付款.getTitle());
        buyerMap.put("2", FabiOrderStatusEnum.等待对方放币.getTitle());
        buyerMap.put("3", FabiOrderStatusEnum.已完成.getTitle());
        buyerMap.put("4", FabiOrderStatusEnum.申诉中.getTitle());
        buyerMap.put("5", FabiOrderStatusEnum.已取消.getTitle());
        sellerMap.put("1", FabiOrderStatusEnum.等待对方付款.getTitle());
        sellerMap.put("2", FabiOrderStatusEnum.待放币.getTitle());
        sellerMap.put("3", FabiOrderStatusEnum.已完成.getTitle());
        sellerMap.put("4", FabiOrderStatusEnum.申诉中.getTitle());
        sellerMap.put("5", FabiOrderStatusEnum.已取消.getTitle());
        tvStatus.setText(isBuyer ? buyerMap.get(String.valueOf(data.getStatus())) : sellerMap.get(String.valueOf(data.getStatus())));
        tvOrderNum.setText(data.getDealEntrustNo());
        tvPrice.setText(String.format("¥%s", CoinUtil.keepRMB(data.getPrice())));
        tvNum.setText(String.format("%s USDT", CoinUtil.keepUSDT(data.getTotalNum())));
        tvAllMoney.setText(String.format("¥%s", CoinUtil.keepRMB(data.getTotalPrice())));
        tvPhone.setText(isBuyer ? data.getSellPhone() : data.getBuyPhone());
        tvName.setText(isBuyer ? data.getSellUsername() : data.getBuyUsername());
        tvTime.setText(data.getCreateTime());
        tvCode.setText(data.getRefer());
        tvRemark.setText(data.getRemarkInfo());
        ClickUtil.click(ivCopy, () -> {
            CopyUtil.copy(isBuyer ? data.getSellPhone() : data.getBuyPhone());
        });
        ClickUtil.click(llPhone, () -> {
            TipFabiUtil.showCall(this, isBuyer ? data.getSellPhone() : data.getBuyPhone(), (str) -> {
                IntentUtil.call(this, str[0]);
            });
        });
        Flowable.fromIterable(data.getStockUserPayWays())
                .filter(stockUserPayWaysBean -> data.getPayType().contains(stockUserPayWaysBean.getPayType()))
                .subscribe(stockUserPayWaysBean -> {
                    switch (stockUserPayWaysBean.getPayType()) {
                        case "1"://微信
                            tvBank.setText(App.INSTANCE.getString(R.string.fabipublishActivity20) + stockUserPayWaysBean.getWxAccount());
                            tvUserInfo.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity3));
                            ivQRCode.setVisibility(View.VISIBLE);
                            ImageUtil.setImage(R.mipmap.fabi_icon_wx, ivPayType);
                            ClickUtil.click(ivQRCode, () -> {
                                showQR(stockUserPayWaysBean.getWxImg(), stockUserPayWaysBean.getWxAccount(), App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity4));
                            });
                            tvPayInfo.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity5) + stockUserPayWaysBean.getWxAccount());
                            break;
                        case "2"://支付宝
                            tvBank.setText(App.INSTANCE.getString(R.string.fabipublishActivity21) + stockUserPayWaysBean.getAlipayAccount());
                            tvUserInfo.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity3));
                            ivQRCode.setVisibility(View.VISIBLE);
                            ImageUtil.setImage(R.mipmap.fabi_icon_alipay, ivPayType);
                            ClickUtil.click(ivQRCode, () -> {
                                showQR(stockUserPayWaysBean.getAliImg(), stockUserPayWaysBean.getAlipayAccount(), App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity7));
                            });
                            tvPayInfo.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity8) + stockUserPayWaysBean.getAlipayAccount());

                            break;
                        case "3"://银行卡
                            tvBank.setText(stockUserPayWaysBean.getBankCardOpenBank() + " " + stockUserPayWaysBean.getBankCardNo());
                            tvUserInfo.setText(data.getSellUsername() + " " + stockUserPayWaysBean.getBankCardUnionNo());
                            ivQRCode.setVisibility(View.GONE);
                            ImageUtil.setImage(R.mipmap.fabi_icon_bank, ivPayType);
                            tvPayInfo.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity9) + stockUserPayWaysBean.getBankCardNo());

                            break;
                    }
                });
        DisposUtil.close(timerDispo);

        if (isBuyer) {//买家页面
            switch (data.getStatus()) {
                case 1://未支付
                    buyerUnPay();
                    break;
                case 2://等待放币
                    buyerWaitCoin();
                    break;
                case 4://卖家申诉中
                    buyerAndSellerAppeal();
                    break;
                case 5://订单取消
                    orderCancel();
                    break;
                case 3://订单完成
                    orderComplete();
                    break;
            }
        } else {//卖家页面
            switch (data.getStatus()) {
                case 1://待付款
                    sellerUnPay();
                    break;
                case 2://待放币
                    sellerWaitCoin();
                    break;
                case 4://卖家申诉中
                    buyerAndSellerAppeal();
                    break;
                case 5://订单取消
                    orderCancel();
                    break;
                case 3://订单完成
                    orderComplete();
                    break;

            }
        }
    }

    /**
     * 卖家等待放币
     */
    private void sellerWaitCoin() {
        llOrderNumInfo.setVisibility(View.VISIBLE);
        llPayInfo.setVisibility(View.VISIBLE);
        llPayType.setVisibility(View.GONE);
        llControl.setVisibility(View.VISIBLE);
        String time = TimeFormatUtil.parseTime(data.getCountDownTime());

        Spanny spanny = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity10))
                .append(time, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity11));

        tvDesc.setText(spanny);
        timerDispo = Flowable.intervalRange(1, data.getCountDownTime(), 0, 1, TimeUnit.SECONDS)
                .map(i -> data.getCountDownTime() - i)
                .compose(RxSchedulersHelper.transformer())
                .subscribe(i -> {
                    String timeIn = TimeFormatUtil.parseTime(i);
                    Spanny spannyIn = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity10))
                            .append(timeIn, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                            .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity11));
                    tvDesc.setText(spannyIn);
                });
        btLeft.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity12));
        btRight.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity13));
        ClickUtil.click(btLeft, () -> {//申诉
            showAppeal();
        });
        ClickUtil.click(btRight, () -> {//放币
            showPwdBottom();
        });
        refreshTimerOrder();
    }

    /**
     * 显示申诉弹窗
     */
    private void showAppeal() {
        materialDialogAppeal = TipFabiUtil.showAppealTip(this, str -> {
            materialDialogAppeal.dismiss();
            mPresenter.allegeOrder(orderId, str[0]);
        });
        materialDialogAppeal.show();


    }

    /**
     * 放币资金密码弹窗
     */
    private void showPwdBottom() {

        codeDialog = TipFabiUtil.sendCoin(this, str -> {
            codeDialog.dismiss();
            mPresenter.letGo(orderId, str[0]);
        });
        codeDialog.show();
    }

    /**
     * 卖家未支付
     */
    private void sellerUnPay() {
        llOrderNumInfo.setVisibility(View.VISIBLE);
        llPayInfo.setVisibility(View.VISIBLE);
        llControl.setVisibility(View.GONE);
        llPayType.setVisibility(View.GONE);
        String time = TimeFormatUtil.parseTime(data.getCountDownTime());
        Spanny spanny = new Spanny(String.format(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity14), data.getBuyUsername()))
                .append(time, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity15));
        tvDesc.setText(spanny);
        timerDispo = Flowable.intervalRange(1, data.getCountDownTime(), 0, 1, TimeUnit.SECONDS)
                .map(i -> data.getCountDownTime() - i)
                .compose(RxSchedulersHelper.transformer())
                .subscribe(i -> {
                    String timeIn = TimeFormatUtil.parseTime(i);
                    Spanny spannyIn = new Spanny(String.format(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity14), data.getBuyUsername()))
                            .append(timeIn, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                            .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity15));
                    tvDesc.setText(spannyIn);
                });
        refreshTimerOrder();
    }


    /**
     * 订单完成
     */
    private void orderComplete() {
        llOrderNumInfo.setVisibility(View.GONE);
        llPayInfo.setVisibility(View.GONE);
        llPayType.setVisibility(View.GONE);
        llControl.setVisibility(View.GONE);
        tvDesc.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity16));
        DisposUtil.close(detailDispo);
    }

    /**
     * 订单取消
     */
    private void orderCancel() {
        llOrderNumInfo.setVisibility(View.GONE);
        llPayInfo.setVisibility(View.GONE);
        llPayType.setVisibility(View.GONE);
        llControl.setVisibility(View.GONE);
        tvDesc.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity17));
        DisposUtil.close(detailDispo);

    }

    /**
     * 申诉
     */
    private void buyerAndSellerAppeal() {
        llOrderNumInfo.setVisibility(View.GONE);
        llPayInfo.setVisibility(View.GONE);
        llPayType.setVisibility(View.GONE);
        llControl.setVisibility(View.GONE);
        tvDesc.setText(String.format(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity18), data.getCommand()));
        DisposUtil.close(detailDispo);
    }

    /**
     * 买家未支付
     */
    private void buyerUnPay() {
        llOrderNumInfo.setVisibility(View.VISIBLE);
        llPayInfo.setVisibility(View.GONE);
        llControl.setVisibility(View.VISIBLE);
        llPayType.setVisibility(View.VISIBLE);
        String time = TimeFormatUtil.parseTime(data.getCountDownTime());

        Spanny spanny = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity19))
                .append(time, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity20));
        tvDesc.setText(spanny);
        btLeft.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity21));
        btRight.setText(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity22));
        timerDispo = Flowable.intervalRange(1, data.getCountDownTime(), 0, 1, TimeUnit.SECONDS)
                .map(i -> data.getCountDownTime() - i)
                .compose(RxSchedulersHelper.transformer())
                .subscribe(i -> {
                    String timeIn = TimeFormatUtil.parseTime(i);
                    Spanny spannyIn = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity19))
                            .append(timeIn, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                            .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity20));
                    tvDesc.setText(spannyIn);
                });
        refreshTimerOrder();
        ClickUtil.click(btRight, () -> {//标记付款
            showPayTip();
        });
        ClickUtil.click(btLeft, () -> {//取消订单
            showCancelTip();
        });
    }

    private void showPayTip() {
        mPresenter.markPay(0, orderId);
    }

    /**
     * 买家等待放币
     */
    private void buyerWaitCoin() {
        llOrderNumInfo.setVisibility(View.VISIBLE);
        llPayInfo.setVisibility(View.VISIBLE);
        llControl.setVisibility(View.GONE);
        llPayType.setVisibility(View.GONE);
        String time = TimeFormatUtil.parseTime(data.getCountDownTime());
        Spanny spanny = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity23))
                .append(time, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity24));
        tvDesc.setText(spanny);

        timerDispo = Flowable.intervalRange(1, data.getCountDownTime(), 0, 1, TimeUnit.SECONDS)
                .map(i -> data.getCountDownTime() - i)
                .compose(RxSchedulersHelper.transformer())
                .subscribe(i -> {
                    String timeIn = TimeFormatUtil.parseTime(i);
                    Spanny spannyIn = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity23))
                            .append(timeIn, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                            .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity24));
                    tvDesc.setText(spannyIn);
                });
        refreshTimerOrder();

    }


    /**
     * 显示二维码弹窗
     */
    private void showQR(String img, String account, String title) {
        TipFabiUtil.showQR(this, title, account, img);
    }

    /**
     * 显示撤单提示
     */
    private void showCancelTip() {
        if (tipCancleDialog == null) {
            tipCancleDialog = TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity21), App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity25), () -> {
                mPresenter.cancelOrder(orderId);
            });

        }
        tipCancleDialog.show();
    }


    /**
     * 定时刷新订单信息
     */
    private void refreshTimerOrder() {
        if (detailDispo == null || detailDispo.isDisposed()) {
            detailDispo = Flowable.interval(0, 5, TimeUnit.SECONDS)
                    .subscribe(aLong -> {
                        mPresenter.getOrderInfo(orderNum);
                    });
        }
    }

    @Override
    protected void onDestroy() {
        DisposUtil.close(timerDispo);
        DisposUtil.close(detailDispo);
        super.onDestroy();
    }
}
