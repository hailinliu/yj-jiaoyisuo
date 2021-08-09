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
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.IntentUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.NewOrderDetailBean;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.presenter.PayOrderActivityPresenter;
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

@Route(path = RConfig.PAYORDERACTIVITY)
public class PayOrderActivity extends BaseActivity<PayOrderActivityPresenter> {
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
    private Disposable timerDispo;
    private long num;
    private String phone;
    private String erweima;

    public void updataUI(NewOrderDetailBean data) {
        DisposUtil.close(timerDispo);
        phone = data.getOtherSide();
    if(data.getStatus()==0){
    tv_status.setText("订单已取消");
    tv_timeLimit.setVisibility(View.INVISIBLE);
        ll_end.setVisibility(View.GONE);
    }else if(data.getStatus()==1){
        tv_status.setText("待支付");
        tv_timeLimit.setVisibility(View.VISIBLE);

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
                    if(i==0){
                        mPresenter.preOrder(orderSn);
                    }
                });
        ll_end.setVisibility(View.VISIBLE);
        //定时器
    }else if(data.getStatus()==2){
        tv_status.setText("已付款");
        tv_timeLimit.setVisibility(View.INVISIBLE);
        ll_end.setVisibility(View.GONE);
        tv_cancel.setText("申诉");
        tv_sure.setText("确认收款并放行");
    }else if(data.getStatus()==3){
        tv_status.setText("已完成");
        tv_timeLimit.setVisibility(View.INVISIBLE);
        ll_end.setVisibility(View.GONE);
    } else if (data.getStatus()==4) {
        tv_status.setText("申诉中");
        tv_timeLimit.setVisibility(View.INVISIBLE);
        ll_end.setVisibility(View.INVISIBLE);
    }
    tvTotalPrice.setText(data.getMoney()+" CNY");
    tvPrice.setText(data.getPrice()+" CNY");
    tv_num.setText(data.getAmount()+" "+data.getUnit());
    tv_username.setText(data.getPayInfo().getRealName());
    tv_order_num.setText(data.getOrderSn());
    tv_name.setText("您必须用实名为"+data.getPayInfo().getRealName()+"的账户向以下账户转账");
    if(data.getPayInfo().getAlipay()!=null){
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
        tv_other_payname.setText("支付宝账号");
        tv_payCode.setText(data.getPayInfo().getAlipay().getAliNo());
        ivPay.setImageDrawable(getResources().getDrawable(R.mipmap.lib_icon_alipay));
        paymode ="支付宝";
        tvPayName.setText("支付宝");
        erweima = data.getPayInfo().getAlipay().getQrCodeUrl();
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
        paymode ="银联";
        tvPayName.setText("银联");
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
        tv_other_payname.setText("微信账号");
        paymode ="微信";
        tvPayName.setText("微信");
        erweima = data.getPayInfo().getWechatPay().getQrWeCodeUrl();
        tv_payCode.setText(data.getPayInfo().getWechatPay().getWechat());
        ivPay.setImageDrawable(getResources().getDrawable(R.mipmap.lib_icon_wx));
    }
    //ivPay.setImageDrawable();
    }

    //银行名称
    @BindView(R2.id.ll_bankname)
    LinearLayout llBankName;
    @BindView(R2.id.bankname)
    TextView tv_bankname;
    @BindView(R2.id.ivCopy2)
    ImageView ivCopy2;
    @BindView(R2.id.view1)
    View view1;
    //卡号
    @BindView(R2.id.ll_cardnum)
    LinearLayout ll_cardnum;
    @BindView(R2.id.tv_bankcode)
    TextView tv_bankcode;
    @BindView(R2.id.ivCopy3)
    ImageView ivCopy3;
    @BindView(R2.id.view2)
    View view2;
    //开户行
    @BindView(R2.id.ll_bankkaihu)
    LinearLayout ll_bankKaiHu;
   @BindView(R2.id.tv_kaihuhangname)
   TextView tv_kaihuhangname;
   @BindView(R2.id.ivCopy4)
   ImageView ivCopy4;
   @BindView(R2.id.view3)
   View view3;
   //支付宝，微信二维码
    @BindView(R2.id.ll_erweima)
    LinearLayout ll_erweima;
    @BindView(R2.id.tv_chakan)
    TextView tv_chakan;
    @BindView(R2.id.view5)
    View view5;
    //支付宝，微信账号
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
    //订单编号
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
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean user;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_pay_activity_order;
    }

    @Override
    public PayOrderActivityPresenter getPresenter() {
        return new PayOrderActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        mPresenter.initSocket(orderSn);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        userViewModel.getUsers().observe(this,users->{
            if (users != null&&users.size()>0) {
                user = users.get(0);
            }
        });
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
                    ToastUtil.showShort("你还没有绑定电话!");
                }else {
                    //弹窗
                    TipFabiUtil.showCall(PayOrderActivity.this, phone, new TipFabiUtil.OnInputList() {
                        @Override
                        public void onSure(String... str) {
                            IntentUtil.call(PayOrderActivity.this, str[0]);
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
                //复制
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test", tvTotalPrice.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test", tv_username.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_bankname.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_bankcode.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_kaihuhangname.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_payCode.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClipData = ClipData.newPlainText("Simple test", tv_order_num.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
       tv_chakan.setOnClickListener(new View.OnClickListener() {//二维码
           @Override
           public void onClick(View v) {
               MaterialDialog dialog = new RudessMaterialDialog.Builder(PayOrderActivity.this)
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
               if(tv_cancel.getText().toString().contains("取消")){
                   mPresenter.cancelOrder(orderSn);
               }else if(tv_cancel.getText().toString().contains("申诉")){
                   BottomSheetUtil.getBottomSheetShenSu(PayOrderActivity.this, "申诉", new BottomSheetUtil.OnInputList() {
                       @Override
                       public void onSure(String... str) {
                           String content = str[0];
                           String type = str[1];
                           mPresenter.getShenru(orderSn,content,type);
                       }
                   }).show();
               }

           }
       });
       tv_sure.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            mPresenter.sureOrder(orderSn,paymode);
           }
       });
    }

    @Override
    protected void initData() {
        //mPresenter.query("SELL",1 , 10,"");
        mPresenter.preOrder(orderSn);
    }


    public void cancelOrder(String message) {
        ToastUtil.showShort(message);
        mPresenter.preOrder(orderSn);
    }

    public void sureOrder(String message) {
        ToastUtil.showShort(message);
        mPresenter.preOrder(orderSn);
    }

    public void sucess(String msg) {
        mPresenter.preOrder(orderSn);
        ToastUtil.showShort(msg);

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
