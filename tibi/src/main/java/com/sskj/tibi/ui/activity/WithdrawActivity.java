package com.sskj.tibi.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BuildConfig;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.EncodeUtils;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.CoinDetailBean;
import com.sskj.tibi.bean.NewAddressListBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.presenter.WithdrawActivityPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = RConfig.TIBI_WITHDRAW)//提币
public class WithdrawActivity extends BaseActivity<WithdrawActivityPresenter> {
    @BindView(R2.id.image1)
    ImageView image1;
    @BindView(R2.id.ivRight)
    ImageView imageView;
    @BindView(R2.id.tv_type1)
    TextView tvType1;
    @BindView(R2.id.tv_type_quan)
    TextView tvTypeQuan;
    @BindView(R2.id.ll_select)
    LinearLayout llSelect;
    @BindView(R2.id.tv_keyong)
    TextView tvKeyong;
    @BindView(R2.id.tv_kecoin)
    TextView tvKecoin;
    @BindView(R2.id.tv_xiane)
    TextView tvXiane;
    @BindView(R2.id.et_address)
    EditText etAddress;
    @BindView(R2.id.et_num)
    EditText etNum;
    @BindView(R2.id.et_pwd)
    EditText etPwd;
    @BindView(R2.id.tv_minnum)
    TextView tvMinnum;
    @BindView(R2.id.tv_all)
    TextView tvAll;
    @BindView(R2.id.tv_kuanggongfei)
    TextView tvKuanggongfei;
    @BindView(R2.id.tv_shiji)
    TextView tvShiji;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @BindView(R2.id.ll_addaddress)
    LinearLayout ll_addaddress;
    @Autowired
    String email;
    @Autowired
    String phone;
    private String max;
    private static final String TAG = "WithdrawActivity";
    private String kuanggongfei;
    private String keyong;
    private AndroidNextInputs inputs;
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_withdraw;
    }
    private String num;
    @Override
    public WithdrawActivityPresenter getPresenter() {
        return new WithdrawActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibi_ti));
        imageView.setImageResource(R.mipmap.tibi_icon_record);
        inputs = new ToastNextInputs();
        etAddress.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.tibi_qingshuru),14,true));
        etNum.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.tibi_qingzhuan),14,true));
        etPwd.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.tibi_jiaoyi_input),14,true));
        inputs.add(etAddress, SchemeUtil.notEmpty(etAddress));
        inputs.add(etNum,SchemeUtil.notEmpty(etNum));
        inputs.add(etPwd,SchemeUtil.notEmpty(etPwd));
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ARouter.getInstance().build(RConfig.CONGRECORDACTIVITY).withSerializable("list",list).withInt("flag",2).withString("type",tvType1.getText().toString()).navigation();
            }
        });
        llSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.SELECTCOINACTIVITY).withSerializable("list",list).navigation(WithdrawActivity.this,1);
            }
        });
        ll_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ARouter.getInstance().build(RConfig.ADDRESSMANAGERACTIVITY).withString("type",tvType1.getText().toString()).navigation(WithdrawActivity.this,1);
            }
        });
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNum.setText(keyong);
                BigDecimal bigDecimal=new BigDecimal(keyong).subtract(new BigDecimal(kuanggongfei)).setScale(8,BigDecimal.ROUND_DOWN);
                tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+bigDecimal.stripTrailingZeros().toPlainString()+tvType1.getText().toString());
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputs.test()){
                    BottomSheetUtil.showYanZhengMa2(WithdrawActivity.this, email!=null?email:phone, new BottomSheetUtil.NewOnSure() {
                        @Override
                        public void onSure(String code) {
                            if(!code.isEmpty()){
                                if(EncodeUtils.encodeAES(code).equals(message)){
                                    mPresenter.submit(etAddress.getText().toString(),etNum.getText().toString(),tvType1.getText().toString(),code,etPwd.getText().toString());
                                }else {
                                    ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_yanzheng));
                                }


                                // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                            }
                        }
                    }, new BottomSheetUtil.GetTime() {
                        @Override
                        public void getTime() {
                            if(email!=null){
                                mPresenter.getEmailCode();
                            }else
                                mPresenter.getPhoneCode();
                            // mPresenter.updataUseerPwdCode(email);
                            //  mPresenter.updateUserPwd();
                        }
                    });

                }
                /*if(inputs.test()){
                 mPresenter.submit(etAddress.getText().toString(),etNum.getText().toString(),tvType1.getText().toString());
                }*/
            }
        });
        RxTextView.textChanges(etNum).subscribe(data->{
            if(BuildConfig.DEBUG){
                Log.e(TAG, "initInputChange3:"+data);
            }

                    String text = data.toString();
                    if(BuildConfig.DEBUG){
                    Log.e(TAG, "initInputChange2: "+text);}
                    if (TextUtils.isEmpty(text)) {
                         num= "0";
                         tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+"0 "+tvType1.getText().toString());
                    } else {
                        num = text;
                        BigDecimal bigDecimal=new BigDecimal(num).subtract(new BigDecimal(kuanggongfei)).setScale(8,BigDecimal.ROUND_DOWN);

                                if(bigDecimal.compareTo(BigDecimal.ZERO)>=0){
                                    tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+bigDecimal.stripTrailingZeros().toPlainString()+tvType1.getText().toString());
                                }else {
                                    tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+"0 "+tvType1.getText().toString());
                                }

                    }

                },e-> System.out.println("错误信息为:"+e)
        );
    }
    CoinListBean.DataBean bean;
    NewAddressListBean.DataBean.ContentBean contentBean;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            bean = (CoinListBean.DataBean) data.getSerializableExtra("data");
            ImageUtil.setImage(HttpConfig.BASE_URL+ bean.getCoin().getImgUrl(),image1);
            tvType1.setText(bean.getCoin().getUnit());
            tvTypeQuan.setText(bean.getCoin().getName());
            tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+"0 "+tvType1.getText().toString());
            mPresenter.getwallet("0",bean.getCoin().getUnit());

        }
        if(requestCode==1&&resultCode==2){
           contentBean = (NewAddressListBean.DataBean.ContentBean)data.getSerializableExtra("data");
           etAddress.setText(contentBean.getAddress());
        }
    }
    @Override
    protected void initData() {
       // super.initData();
        mPresenter.getCoin("2");

    }
    ArrayList<CoinListBean.DataBean> list = new ArrayList<>();
    public void setData(ArrayList<CoinListBean.DataBean> data) {
        ImageUtil.setImage(HttpConfig.BASE_URL+data.get(0).getCoin().getImgUrl(),image1);
        tvType1.setText(data.get(0).getCoin().getUnit());
        tvTypeQuan.setText(data.get(0).getCoin().getName());
        tvShiji.setText(App.INSTANCE.getString(R.string.tibi_shiji)+"0 "+tvType1.getText().toString());
        mPresenter.getwallet("0",data.get(0).getCoin().getUnit());
        this.list = data;
    }

    public void updateData(CoinDetailBean bean) {
        keyong = new BigDecimal(bean.getData().get(0).getBalance()).stripTrailingZeros().toPlainString();
        tvKeyong.setText(new BigDecimal(bean.getData().get(0).getBalance()).stripTrailingZeros().toPlainString()+" "+bean.getData().get(0).getCoin().getUnit());
        tvMinnum.setText(App.INSTANCE.getString(R.string.tibi_tibinum)+new BigDecimal(bean.getData().get(0).getCoin().getMinWithdrawAmount()).stripTrailingZeros().toPlainString()+" "+tvType1.getText().toString());
        tvKuanggongfei.setText(App.INSTANCE.getString(R.string.tibi_kuanggong)+bean.getData().get(0).getCoin().getMinTxFee()+" "+tvType1.getText().toString());
        kuanggongfei = bean.getData().get(0).getCoin().getMinTxFee();
        max = bean.getData().get(0).getCoin().getMaxWithdrawAmount();
        tvXiane.setText(new BigDecimal(max).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
        mPresenter.getTodayCoinDetail(tvType1.getText().toString());

    }

    public void getToday(String toString) {
        tvKecoin.setText(new BigDecimal(max).subtract(new BigDecimal(toString)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());

    }

    public void setUpdate(String msg) {
        ToastUtil.showShort(msg);
        ARouter.getInstance().build(RConfig.CONGRECORDACTIVITY).withSerializable("list",list).withInt("flag",2).withString("type",tvType1.getText().toString()).navigation();
       // mPresenter.getCoin("2");
    }
String message;
    public void setP(String msg) {
       message = msg;
    }
}
