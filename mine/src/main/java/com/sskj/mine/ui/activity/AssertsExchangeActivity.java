package com.sskj.mine.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.MD5Util;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MD5Utils;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.ExchangeRecordBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.presenter.AssertsExchangeActivityPresenter;
import com.zzhoujay.richtext.ext.MD5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

@Route(path = RConfig.ASSERTS_EXCHANGE_ACTIVITY)
public class AssertsExchangeActivity extends BaseActivity<AssertsExchangeActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.ivRight)
    ImageView ivRight;
    @BindView(R2.id.ll_exchange)
    LinearLayout llExchange;
    @BindView(R2.id.tv_usdt)
    TextView tvUsdt;
    @BindView(R2.id.tv_all)
    TextView tvAll;
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.et_transfernum)
    EditText etTransfernum;
    @BindView(R2.id.tv_keyong)
    TextView tvKeyong;
    @BindView(R2.id.btOrder)
    Button btOrder;
    private BottomSheetDialog bottomSheet;
    private List<String> list = new ArrayList<>();
    private String d;
    String id = SPUtil.get(SPConfig.ID,"");
    private AlertDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_asserts_exchange;
    }

    @Override
    protected void initView() {
       ivRight.setImageResource(R.mipmap.mine_icon_record);
       tvTitle.setText(App.INSTANCE.getString(R.string.mine_assert_conver));
       tvKeyong.setText("可用"+"0"+tvType.getText().toString());
       tvAll.setText(tvType.getText().toString().trim()+App.INSTANCE.getString(R.string.mine_assert_conver_all));
    }

    @Override
    protected void initData() {
      list.add("BTC");
      list.add("ETH");
      mPresenter.getData(id,1);
        LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN, CoinBean.class)
                .observe(this, this::updateCoin);
    }
    public void updateCoin(CoinBean coinBean) {

        if(coinBean.getCode().contains("/USDT")){
            String[] arr = coinBean.getCode().split("/USDT");
           if(tvType.getText().toString().equals(arr[0]) ){
               tvUsdt.setText(coinBean.getPrice()+"USDT");
           }

        }

    }
    @Override
    protected void initEvent() {
        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etTransfernum.getText())){
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_conver_hint));
                    return;
                }
                BigDecimal bigDecimal1 = new BigDecimal(etTransfernum.getText().toString());
                BigDecimal bigDecimal2 = new BigDecimal(d);
               if(bigDecimal1.compareTo(bigDecimal2)>0){
                   ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_conver_show));
                   return;
               }
                AlertDialog.Builder builder = new AlertDialog.Builder(AssertsExchangeActivity.this);
                dialog = builder.create();
                View view = View.inflate(AssertsExchangeActivity.this,R.layout.mine_bottom_sheet_paypwd,null);
                TextView tv = view.findViewById(R.id.tv_cancel);
                EditText et =  view.findViewById(R.id.et_pwd);
                TextView tv_submit =  view.findViewById(R.id.tv_submit);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                tv_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.submitExchange(id,tvType.getText().toString(),"USDT",etTransfernum.getText().toString(),et.getText().toString());
                    }
                });
                dialog.setView(view);
                dialog.show();

            }
        });
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTransfernum.setText(d);
                etTransfernum.setSelection(etTransfernum.getText().toString().length());
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //此处为资产记录
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.ASSERTS_EXCHANGE_RECORD_ACTIVITY).navigation();
            }
        });
        bottomSheet = BottomSheetUtil.getBottomSheet( this, null,  (recyclerView, i, view) -> {
            bottomSheet.dismiss();
            tvType.setText(((TextView)view).getText().toString());
            tvAll.setText(tvType.getText().toString().trim()+App.INSTANCE.getString(R.string.mine_assert_conver_all));
            mPresenter.getData(id,1);
        },list);
        llExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheet == null) {
                    return;
                }
                bottomSheet.show();
            }
        });
    }

    @Override
    public AssertsExchangeActivityPresenter getPresenter() {
        return new AssertsExchangeActivityPresenter();
    }

    public void getAssert(List<MyAssertBean.FundBean> fund) {
        for(MyAssertBean.FundBean bean:fund){
            if(tvType.getText().toString().equals(bean.getStockCode())){
               tvKeyong.setText(App.INSTANCE.getString(R.string.mine_availble)+bean.getUsableFund()+tvType.getText().toString());
                d = bean.getUsableFund();
            }

        }
    }

    public void isSuccess(String msg) {
        ToastUtil.showShort(msg);
        if(msg.contains("成功")){
            dialog.dismiss();
        }
        mPresenter.getData(id,1);
    }

}
