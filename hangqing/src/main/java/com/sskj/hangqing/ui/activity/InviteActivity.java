package com.sskj.hangqing.ui.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.ImgUtil;
import com.sskj.common.util.PermissionTipUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.InviteBean;
import com.sskj.hangqing.bean.InviteRecordBean;
import com.sskj.hangqing.presenter.InviteActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.QRCodeUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.INVITEACTIVITY)
public class InviteActivity extends BaseActivity<InviteActivityPresenter> {
    @BindView(R2.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R2.id.tv_invite_lianjie)
    TextView tvInviteLianjie;
    @BindView(R2.id.super_total)
    SuperTextView superTotal;
    @BindView(R2.id.super_renzhengg)
    SuperTextView superRenzhengg;
    @BindView(R2.id.bt_sure)
    Button btSure;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.iv_copy_code)
    ImageView ivCopyCode;
    @BindView(R2.id.iv_copy_lianjie)
    ImageView ivCopyLianjie;
    private SlimAdapter<InviteRecordBean.DataBean.ContentBean> slimAdapter;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_invite;
    }

    @Override
    public InviteActivityPresenter getPresenter() {
        return new InviteActivityPresenter();
    }

    @Override
    protected void initView() {
        //super.initView();
        setTitle(App.INSTANCE.getString(R.string.hang_yao));
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mPresenter.getInviteCode();
        mPresenter.getPro("1", "20");
        rv.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create()
                .register(R.layout.hang_activity_invite_item, new SlimInjector<InviteRecordBean.DataBean.ContentBean>() {
                    @Override
                    public void onInject(InviteRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                        iViewInjector.text(R.id.tv_username, bean.getUsername())
                                .text(R.id.tv_register_time, bean.getCreateTime());
                    }
                }).attachTo(rv).updateData(new ArrayList());
    }

    @Override
    protected void initEvent() {
        // super.initEvent();
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(InviteActivity.this);
                View inflate = getLayoutInflater().inflate(R.layout.hang_dialog_bottom, null, false);
                TextView tvCancel = inflate.findViewById(R.id.tv_cancel);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView imageView = inflate.findViewById(R.id.image);
                if (!TextUtils.isEmpty(tvInviteLianjie.getText().toString())) {
                    QRCodeUtil.createImage(url, 100, new QRCodeUtil.OnEncodeQRCodeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onAnalyzeFailed() {

                        }
                    });
                }
                //  imageView.setImageBitmap(map);
                //设置二维码图片
                TextView tv = inflate.findViewById(R.id.tv_save);
                tv.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        new RxPermissions(InviteActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(agree -> {
                                    if (agree) {
                                        if (ImgUtil.saveImageToGallery(InviteActivity.this, imageView) != null) {
                                            ToastUtil.showShort("保存成功");
                                        } else {
                                            ToastUtil.showShort("保存失败");
                                        }
                                    } else {
                                        PermissionTipUtil.tip(InviteActivity.this, "存储");
                                    }
                                });
                        //ImgUtil.saveImageToGallery(NewRechargeActivity.this, imageView);
                    }
                });
                bottomSheetDialog.setContentView(inflate);
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();
            }
        });
        ivCopyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test",tvInviteCode.getText().toString());
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), App.INSTANCE.getString(R.string.hang_fuzhi_success),
                        Toast.LENGTH_SHORT).show();
            }
        });
        ivCopyLianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test",url);
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), App.INSTANCE.getString(R.string.hang_fuzhi_success),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    String url;
    public void setUI(InviteBean bean) {
        tvInviteCode.setText(bean.getInviteCode());
        tvInviteLianjie.setText(bean.getPromotePrefix());
        url = bean.getPromotePrefix();
        superTotal.setCenterBottomString(bean.getCount() + "");
        superRenzhengg.setCenterBottomString(bean.getIndirectCount() + "");
    }

    public void setData(List<InviteRecordBean.DataBean.ContentBean> content){
        slimAdapter.updateData(content);
    }


}
