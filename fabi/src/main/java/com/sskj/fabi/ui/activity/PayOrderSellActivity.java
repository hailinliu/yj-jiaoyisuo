package com.sskj.fabi.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binaryfork.spanny.Spanny;
import com.sskj.common.base.App;
import com.sskj.common.util.IntentUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.NewOrderDetailBean;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.presenter.PayOrderSellActivityPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.TimeFormatUtil;
import com.sskj.lib.widget.RudessMaterialDialog;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@Route(path = RConfig.PAYORDERSELLACTIVITY)
public class PayOrderSellActivity extends BaseActivity<PayOrderSellActivityPresenter> {
    @Autowired
    String orderSn;
    @BindView(R2.id.image)
    ImageView imageView;
    @BindView(R2.id.ivPhone)
    ImageView ivPhone;
    @BindView(R2.id.ivChat)
    ImageView ivChat;
    @BindView(R2.id.tv_status)
    TextView tv_status;
    @BindView(R2.id.tv_timelimit)
    TextView tv_timeLimit;
    @BindView(R2.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R2.id.ivcopy)
    ImageView ivcopy;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_num)
    TextView tv_num;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.ivPay)
    ImageView ivPay;
    @BindView(R2.id.payName)
    TextView tvPayName;
    @BindView(R2.id.tv_username)
    TextView tv_username;
    @BindView(R2.id.ivCopy1)
    ImageView ivCopy1;
    //????????????
    @BindView(R2.id.ll_bankname)
    LinearLayout llBankName;
    @BindView(R2.id.bankname)
    TextView tv_bankname;
    @BindView(R2.id.ivCopy2)
    ImageView ivCopy2;
    @BindView(R2.id.view1)
    View view1;
    //??????
    @BindView(R2.id.ll_cardnum)
    LinearLayout ll_cardnum;
    @BindView(R2.id.tv_bankcode)
    TextView tv_bankcode;
    @BindView(R2.id.ivCopy3)
    ImageView ivCopy3;
    @BindView(R2.id.view2)
    View view2;
    //?????????
    @BindView(R2.id.ll_bankkaihu)
    LinearLayout ll_bankKaiHu;
    @BindView(R2.id.tv_kaihuhangname)
    TextView tv_kaihuhangname;
    @BindView(R2.id.ivCopy4)
    ImageView ivCopy4;
    @BindView(R2.id.view3)
    View view3;
    //???????????????????????????
    @BindView(R2.id.ll_erweima)
    LinearLayout ll_erweima;
    @BindView(R2.id.tv_chakan)
    TextView tv_chakan;
    @BindView(R2.id.view5)
    View view5;
    //????????????????????????
    @BindView(R2.id.ll_otherpay)
    LinearLayout ll_otherpay;
    @BindView(R2.id.tv_payCode)
    TextView tv_payCode;
    @BindView(R2.id.ivCopy5)
    ImageView ivCopy5;
    @BindView(R2.id.other_payname)
    TextView tv_other_payname;
    @BindView(R2.id.view4)
    View view4;
    //????????????
    @BindView(R2.id.tv_ordernum)
    TextView tv_order_num;
    @BindView(R2.id.ivCopy6)
    ImageView ivCopy6;
    @BindView(R2.id.ll_end)
    LinearLayout ll_end;
    @BindView(R2.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R2.id.tv_sure)
    TextView tv_sure;
    String paymode;
    //????????????????????????
    private ClipboardManager mClipboardManager;
    //?????????Data??????
    private ClipData mClipData;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean user;
    private String phone;
    private String erweima;

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        userViewModel.getUsers().observe(this,users->{
            if (users != null&&users.size()>0) {
                user = users.get(0);
            }
        });
        tv_sure.setClickable(true);
        tv_sure.setEnabled(true);
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user.getMobilePhone())){
                    ToastUtil.showShort("????????????????????????!");
                }else {
                    //??????
                    TipFabiUtil.showCall(PayOrderSellActivity.this, phone, new TipFabiUtil.OnInputList() {
                        @Override
                        public void onSure(String... str) {
                            IntentUtil.call(PayOrderSellActivity.this, str[0]);
                        }
                    });
                }

            }
        });
        ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.CHARTACTIVITY).navigation();
            }
        });
        ivcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //??????
                //????????????????????????clip??????
                mClipData = ClipData.newPlainText("Simple test", tvTotalPrice.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????????????????clip??????
                mClipData = ClipData.newPlainText("Simple test", tv_username.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_bankname.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_bankcode.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_kaihuhangname.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_payCode.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_order_num.getText().toString());
                //???clip????????????????????????
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "???????????????????????????",
                        Toast.LENGTH_SHORT).show();
            }
        });
        tv_chakan.setOnClickListener(new View.OnClickListener() {//?????????
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new RudessMaterialDialog.Builder(PayOrderSellActivity.this)
                        .customView(R.layout.fabi_activity_erweima, false)
                        .autoDismiss(true)
                        .cancelable(true)
                        .show();
                View customView = dialog.getCustomView();
                ImageView imageView=customView.findViewById(R.id.imageerweima);
                ImageUtil.setImage(HttpConfig.BASE_URL+erweima,imageView);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BottomSheetUtil.getBottomSheetShenSu(PayOrderSellActivity.this, "??????", new BottomSheetUtil.OnInputList() {
                   @Override
                   public void onSure(String... str) {
                       String content = str[0];
                       String type = str[1];
                        mPresenter.getShenru(orderSn,content,type);
                   }
               }).show();
               // mPresenter.cancelOrder(orderSn);
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetUtil.showYanZhengMa1(PayOrderSellActivity.this,"???????????????????????????","????????????", new BottomSheetUtil.NewOnSure() {
                    @Override
                    public void onSure(String code) {
                        if(code!=null){
                         mPresenter.orderRealease(code,orderSn);
                        }
                    }
                }, new BottomSheetUtil.GetTime() {
                    @Override
                    public void getTime() {
                    }
                });
                //mPresenter.surePay(orderSn,paymode);
               // mPresenter.sureOrder(orderSn,paymode);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.preOrder(orderSn);
        mPresenter.initSocket(orderSn);
    }
    private Disposable timerDispo;
    private long num;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_pay_activity_order1;
    }

    @Override
    public PayOrderSellActivityPresenter getPresenter() {
        return new PayOrderSellActivityPresenter();
    }

    public void sucess(String data) {
        mPresenter.preOrder(orderSn);
        ToastUtil.showShort(data);

    }
    public void updataUI(NewOrderDetailBean data) {
        phone = data.getOtherSide();
        if(data.getStatus()==0){
            tv_status.setText("???????????????");
            tv_timeLimit.setVisibility(View.INVISIBLE);
            ll_end.setVisibility(View.GONE);
        }else if(data.getStatus()==1){
            tv_status.setText("?????????");
            tv_timeLimit.setVisibility(View.VISIBLE);
            ll_end.setVisibility(View.VISIBLE);
            tv_cancel.setVisibility(View.INVISIBLE);
            tv_sure.setText("??????????????????");
            tv_sure.setClickable(false);
            tv_sure.setEnabled(false);
            //?????????
            num =Integer.parseInt(data.getTimeLimit())*60-(data.getServerTime()-data.getCreateTime())/1000;
            timerDispo = Flowable.intervalRange(1, num, 0, 1, TimeUnit.SECONDS)
                    .map(i -> num - i)
                    .compose(RxSchedulersHelper.transformer())
                    .subscribe(i -> {
                        String timeIn = TimeFormatUtil.parseTime(i);
                        Spanny spannyIn = new Spanny(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity10))
                                .append(timeIn, new ForegroundColorSpan(ContextCompat.getColor(App.INSTANCE, R.color.fabiBg)))
                                .append(App.INSTANCE.getString(R.string.fabiorderRecordDetailActivity11));
                        tv_timeLimit.setText(spannyIn);
                        if(i<=0){
                            mPresenter.preOrder(orderSn);
                        }
                    });
        }else if(data.getStatus()==2){
            tv_status.setText("?????????");
            tv_timeLimit.setVisibility(View.INVISIBLE);
            ll_end.setVisibility(View.VISIBLE);
            tv_cancel.setText("??????");
            tv_sure.setText("?????????????????????");
            tv_cancel.setVisibility(View.VISIBLE);
            tv_sure.setClickable(true);
            tv_sure.setEnabled(true);
        }else if(data.getStatus()==3){
            tv_status.setText("?????????");
            tv_timeLimit.setVisibility(View.INVISIBLE);
            ll_end.setVisibility(View.GONE);
        } else if (data.getStatus()==4) {
            tv_status.setText("?????????");
            tv_timeLimit.setVisibility(View.INVISIBLE);
            ll_end.setVisibility(View.GONE);
        }
        tvTotalPrice.setText(data.getMoney()+" CNY");
        tvPrice.setText(data.getPrice()+" CNY");
        tv_num.setText(data.getAmount()+" "+data.getUnit());
        tv_username.setText(data.getPayInfo().getRealName());
        tv_order_num.setText(data.getOrderSn());
        tv_name.setText("?????????????????????"+data.getPayInfo().getRealName()+"??????????????????????????????");
        if(data.getPayInfo().getAlipay()!=null){
            llBankName.setVisibility(View.GONE);
            ll_cardnum.setVisibility(View.GONE);
            ll_bankKaiHu.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);
            ll_erweima.setVisibility(View.VISIBLE);
            erweima = data.getPayInfo().getAlipay().getQrCodeUrl();
            view5.setVisibility(View.VISIBLE);
            ll_otherpay.setVisibility(View.VISIBLE);
            view4.setVisibility(View.VISIBLE);
            tv_other_payname.setText("???????????????");
            tv_payCode.setText(data.getPayInfo().getAlipay().getAliNo());
            ivPay.setImageDrawable(getResources().getDrawable(R.mipmap.lib_icon_alipay));
            paymode ="?????????";
            tvPayName.setText("?????????");
        }else if(data.getPayInfo().getBankInfo()!=null){
            llBankName.setVisibility(View.VISIBLE);
            ll_cardnum.setVisibility(View.VISIBLE);
            ll_bankKaiHu.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.VISIBLE);
            ll_erweima.setVisibility(View.GONE);
            view5.setVisibility(View.GONE);
            ll_otherpay.setVisibility(View.GONE);
            view4.setVisibility(View.GONE);
            paymode ="??????";
            tvPayName.setText("??????");
            tv_bankname.setText(data.getPayInfo().getBankInfo().getBank());
            tv_bankcode.setText(data.getPayInfo().getBankInfo().getCardNo());
            tv_kaihuhangname.setText(data.getPayInfo().getBankInfo().getBranch());
            ivPay.setImageDrawable(getResources().getDrawable(R.mipmap.fabi_yinhangka));
        }else if(data.getPayInfo().getWechatPay()!=null){
            llBankName.setVisibility(View.GONE);
            ll_cardnum.setVisibility(View.GONE);
            ll_bankKaiHu.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);
            ll_erweima.setVisibility(View.VISIBLE);
            view5.setVisibility(View.VISIBLE);
            ll_otherpay.setVisibility(View.VISIBLE);
            view4.setVisibility(View.VISIBLE);
            tv_other_payname.setText("????????????");
            erweima = data.getPayInfo().getWechatPay().getQrWeCodeUrl();
            tv_payCode.setText(data.getPayInfo().getWechatPay().getWechat());
            paymode ="??????";
            tvPayName.setText("??????");
            ivPay.setImageDrawable(getResources().getDrawable(R.mipmap.lib_icon_wx));
        }
        //ivPay.setImageDrawable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeWebSocket();
    }

    public void setUI() {
        try {
            Thread.sleep(500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mPresenter.preOrder(orderSn);
        }

    }
}
