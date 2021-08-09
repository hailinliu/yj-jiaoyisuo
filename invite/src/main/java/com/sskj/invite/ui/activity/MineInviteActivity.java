package com.sskj.invite.ui.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ImgUtil;
import com.sskj.common.util.PermissionTipUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.invite.R;
import com.sskj.invite.R2;
import com.sskj.invite.bean.InviteInfo;
import com.sskj.invite.presenter.MineInviteActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.util.ImageUtil;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;

@Route(path = RConfig.INVITE_URL)//邀请返佣二维码
public class MineInviteActivity extends BaseActivity<MineInviteActivityPresenter> {

    @BindView(R2.id.ivQRCode)
    ImageView qrCode;
    @BindView(R2.id.tvCode)
    TextView inviteCode;
    @BindView(R2.id.tvCopy)
    TextView copyCode;


    @Override
    protected int getLayoutId() {
        return R.layout.invite_activity_invite;
    }

    @Override
    public MineInviteActivityPresenter getPresenter() {
        return new MineInviteActivityPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("我的邀请码");
        ClickUtil.click(copyCode, () -> {
            if (TextUtils.isEmpty(inviteCode.getText().toString().trim())) {
                return;
            }
            CopyUtil.copy(inviteCode.getText().toString().trim());
        });

        qrCode.setOnLongClickListener(v -> {
            new RxPermissions(MineInviteActivity.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(agree -> {
                        if (agree) {
                            if (ImgUtil.saveImageToGallery(MineInviteActivity.this, qrCode) != null) {
                                ToastUtil.showShort("保存成功");
                            } else {
                                ToastUtil.showShort("保存失败");
                            }
                        } else {
                            PermissionTipUtil.tip(this,"存储");
                        }
                    });

            return true;
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getShareInfo();
    }

    public void updateUi(InviteInfo inviteInfo) {
        inviteCode.setText(inviteInfo.getInvitationCode());
        ImageUtil.setImage(inviteInfo.getUrl(), qrCode);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MineInviteActivity.class);
        context.startActivity(intent);
    }
}
