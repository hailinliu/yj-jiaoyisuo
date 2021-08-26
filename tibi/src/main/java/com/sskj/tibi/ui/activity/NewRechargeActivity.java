package com.sskj.tibi.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ImgUtil;
import com.sskj.common.util.PermissionTipUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.QRCodeUtil;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.sskj.tibi.R;

import com.sskj.tibi.R2;
import com.sskj.tibi.bean.CoinAddressBean;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.bean.RechargeBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.presenter.NewRechargeActivityPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@Route(path = RConfig.TIBI_ACTIVITY_RECHARGE)//充币
public class NewRechargeActivity extends BaseActivity<NewRechargeActivityPresenter>{
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.tv_type)
    TextView tv_type;
    @BindView(R2.id.tv_type_quan)
    TextView tv_type_quan;
    @BindView(R2.id.tv_content)
    TextView tv_content;
    @BindView(R2.id.tv_address)
    TextView tvAddress;
    @BindView(R2.id.tv_copy)
    TextView tvCopy;
    @BindView(R2.id.tv_chakan)
    TextView tv_chakan;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.ll_select)
    LinearLayout llSelect;
    @BindView(R2.id.ivRight)
    ImageView imageView;
    @BindView(R2.id.radiogroup)
    RadioGroup group;
    private BottomSheetDialog bottomSheetCoin;
    private List<String> strings = new ArrayList<>();
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    private Bitmap bitmap1;
    private CoinListBean.DataBean bean;
    private SlimAdapter slimAdapter;
    List<CoinAddressBean.DataBean> data;
    @Override
    protected int getLayoutId(){
        return R.layout.tibi_fragment_recharge;
    }

    @Override
    public NewRechargeActivityPresenter getPresenter(){
        return new NewRechargeActivityPresenter();
    }
    public void setVisibility(boolean flag){
        if (flag){
         /*   tvLeixing.setVisibility(View.VISIBLE);
            llBt.setVisibility(View.VISIBLE);*/
        }else {
           /* tvLeixing.setVisibility(View.GONE);
            llBt.setVisibility(View.GONE);*/
        }

    }
    @Override
    protected void initView(){
        //spTitle.setRightTextIsBold(true);
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibi_cong));
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.tibi_icon_record);
        setVisibility(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create().register(R.layout.tibi_cong_record_item, new SlimInjector<CongRecordBean.DataBean.ContentBean>() {
            @Override
            public void onInject(CongRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_num,""+bean.getAmount())
                        .text(R.id.tv_status,""+(bean.getStatus()==0?App.INSTANCE.getString(R.string.tibi_sure):bean.getStatus()==1?App.INSTANCE.getString(R.string.tibi_already):bean.getStatus()==2?App.INSTANCE.getString(R.string.tibi_fail):""))
                        .text(R.id.tv_time,""+bean.getCreateTime());
            }
        }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initEvent() {
ClickUtil.click(tvCopy,()->{
    //创建一个新的文本clip对象
    mClipData = ClipData.newPlainText("Simple test",tvAddress.getText().toString());
    //把clip对象放在剪贴板中
    mClipboardManager.setPrimaryClip(mClipData);
    Toast.makeText(getApplicationContext(), App.INSTANCE.getString(R.string.tibi_fuzhi_success),
            Toast.LENGTH_SHORT).show();
});

ClickUtil.click(tv_chakan,()->{
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
    View inflate = getLayoutInflater().inflate(R.layout.tibi_dialog_bottom, null, false);
    TextView tvCancel = inflate.findViewById(R.id.tv_cancel);
    TextView textType = inflate.findViewById(R.id.tv_type);
    tvCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            bottomSheetDialog.dismiss();
        }
    });
    textType.setText(tv_type.getText().toString());
    ImageView imageView = inflate.findViewById(R.id.image);
    imageView.setImageBitmap(bitmap1);
    //设置二维码图片
   TextView tv =  inflate.findViewById(R.id.tv_save);
   tv.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v) {
           new RxPermissions(NewRechargeActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   .subscribe(agree -> {
                       if (agree) {
                           if (ImgUtil.saveImageToGallery(NewRechargeActivity.this, imageView) != null){
                               ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_save_success));
                           } else {
                               ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_save_fail));
                           }
                       } else {
                           PermissionTipUtil.tip(NewRechargeActivity.this,App.INSTANCE.getString(R.string.tibi_cunchu));
                       }
                   });
           //ImgUtil.saveImageToGallery(NewRechargeActivity.this, imageView);
       }
   });
    bottomSheetDialog.setContentView(inflate);
    bottomSheetDialog.setCancelable(true);
    bottomSheetDialog.setCanceledOnTouchOutside(true);
   bottomSheetDialog.show();
});
ClickUtil.click(llSelect,()->{
    ARouter.getInstance().build(RConfig.SELECTCOINACTIVITY).withSerializable("list",list).navigation(this,1);
});
ClickUtil.click(imageView,()->{
    ARouter.getInstance().build(RConfig.CONGRECORDACTIVITY).withSerializable("list",list).withInt("flag",1).withString("type",tv_type.getText().toString()).navigation();
});

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                tv_chakan.setClickable(false);
                tv_chakan.setEnabled(false);
                if(button.getText().toString().contains("TRC")){
                    tvAddress.setText("");
                    for(CoinAddressBean.DataBean bean:data){
                        if(bean.getCoinKey().contains("TRC")){
                            tv_chakan.setClickable(true);
                            tv_chakan.setEnabled(true);
                            tvAddress.setText(bean.getAddress());
                            QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                                @Override
                                public void onAnalyzeSuccess(Bitmap bitmap) {
                                    bitmap1 = bitmap;
                                }
                                @Override
                                public void onAnalyzeFailed() {

                                }
                            });
                        }
                    }
                }
                if(button.getText().toString().contains("ERC")){
                    tvAddress.setText("");
                    for(CoinAddressBean.DataBean bean:data){
                        if(bean.getCoinKey().contains("ERC")){
                            tv_chakan.setClickable(true);
                            tv_chakan.setEnabled(true);
                            tvAddress.setText(bean.getAddress());
                            QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                                @Override
                                public void onAnalyzeSuccess(Bitmap bitmap) {
                                    bitmap1 = bitmap;
                                }
                                @Override
                                public void onAnalyzeFailed() {

                                }
                            });
                        }
                    }
               /*     if(TextUtils.isEmpty(tvAddress.getText().toString())){
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jijiang));
                    }*/
                }
                if(button.getText().toString().contains("OMNI")){
                    tvAddress.setText("");
                    for(CoinAddressBean.DataBean bean:data){
                        if(bean.getCoinKey().contains("OMNI")){
                            tv_chakan.setClickable(true);
                            tv_chakan.setEnabled(true);
                            tvAddress.setText(bean.getAddress());
                            QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                                @Override
                                public void onAnalyzeSuccess(Bitmap bitmap) {
                                    bitmap1 = bitmap;
                                }
                                @Override
                                public void onAnalyzeFailed() {

                                }
                            });
                        }
                    }
                  /*  if(TextUtils.isEmpty(tvAddress.getText().toString())){
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jijiang));
                    }*/
                }
            }
    });
    }
    @Override
    protected void initData() {
        strings.add("ETH");
        strings.add("BTC");
        strings.add("USDT");
      //  mPresenter.getCoinAdress("BTC");
        mPresenter.getCoin("0");
    }

    ArrayList<CoinListBean.DataBean> list = new ArrayList<>();
    public void setData(ArrayList<CoinListBean.DataBean> data) {
        for(CoinListBean.DataBean bean:data){
            if(bean.getCoin().getUnit().equals("USDT")){
                ImageUtil.setImage(HttpConfig.BASE_URL+bean.getCoin().getImgUrl(),image);
                tv_type.setText(bean.getCoin().getUnit());
                tv_type_quan.setText(bean.getCoin().getName());
                mPresenter.getCoinAdress(bean.getCoin().getUnit());
                mPresenter.getRecord(bean.getCoin().getName(),"1","10");
            }
        }

        this.list = data;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            bean = (CoinListBean.DataBean) data.getSerializableExtra("data");
            ImageUtil.setImage(HttpConfig.BASE_URL+ bean.getCoin().getImgUrl(),image);
            if(bean.getCoin().getUnit().contains("USDT")){
                group.setVisibility(View.VISIBLE);
                RadioButton button=  group.findViewById(R.id.radio_trc20);
                button.setChecked(true);
            }else {
                group.setVisibility(View.INVISIBLE);
            }
            tv_type.setText(bean.getCoin().getUnit());
            tv_type_quan.setText(bean.getCoin().getName());
            mPresenter.getCoinAdress(bean.getCoin().getUnit());
            mPresenter.getRecord(bean.getCoin().getName(),"1","10");
        }
    }

    public void setAddress(List<CoinAddressBean.DataBean> data) {
        this.data = data;
        if(data!=null&&data.size()>0){
            tvAddress.setText(data.get(0).getAddress());
            QRCodeUtil.createImage(data.get(0).getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                @Override
                public void onAnalyzeSuccess(Bitmap bitmap) {
                    bitmap1 = bitmap;
                }
                @Override
                public void onAnalyzeFailed() {

                }
            });
            for(CoinAddressBean.DataBean bean:data){
                if(bean.getCoinKey().contains("TRC")){
                    tvAddress.setText(bean.getAddress());
                    QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                        @Override
                        public void onAnalyzeSuccess(Bitmap bitmap) {
                            bitmap1 = bitmap;
                        }
                        @Override
                        public void onAnalyzeFailed() {

                        }
                    });
                }
          /*  if(tv_type.getText().toString().equals("ETH")&&bean.getCoinKey().contains("ERC")){
                tvAddress.setText(bean.getAddress());
                QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                    @Override
                    public void onAnalyzeSuccess(Bitmap bitmap) {
                        bitmap1 = bitmap;
                    }
                    @Override
                    public void onAnalyzeFailed() {

                    }
                });
            }
            if(tv_type.getText().toString().equals("BTC")&&bean.getCoinKey().contains("OMNI")){
                tvAddress.setText(bean.getAddress());
                QRCodeUtil.createImage(bean.getAddress(),200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                    @Override
                    public void onAnalyzeSuccess(Bitmap bitmap) {
                        bitmap1 = bitmap;
                    }
                    @Override
                    public void onAnalyzeFailed() {

                    }
                });*/
            }
        }else {
            tvAddress.setText("");
            QRCodeUtil.createImage("1",200,new QRCodeUtil.OnEncodeQRCodeCallback(){
                @Override
                public void onAnalyzeSuccess(Bitmap bitmap) {
                    bitmap1 = bitmap;
                }
                @Override
                public void onAnalyzeFailed() {

                }
            });
        }

        }



    public void setRecord(CongRecordBean bean) {
        slimAdapter.updateData(bean.getData().getContent());
    }
}
