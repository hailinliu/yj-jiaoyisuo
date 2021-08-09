package com.sskj.mine.ui.activity;


import android.graphics.Bitmap;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.QRCodeUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.GoogleCode;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.VerifyGoogleActivityPresenter;

import butterknife.BindView;


public class VerifyGoogleActivity extends BaseActivity<VerifyGoogleActivityPresenter> {
    @BindView(R2.id.iv_qr_code)
    ImageView mIvQrCode;
    @BindView(R2.id.tv_code)
    TextView mTvCode;
    @BindView(R2.id.tv_code_copy)
    TextView mTvCodeCopy;
    @BindView(R2.id.btn_next)
    Button mBtnNext;


    private GoogleCode obj;

    private BottomSheetDialog sheetDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_verify_google;
    }

    @Override
    public VerifyGoogleActivityPresenter getPresenter() {
        return new VerifyGoogleActivityPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
RxBus.getDefault().register(this);
        setTitle(App.INSTANCE.getString(R.string.mineverifyGoogleActivity1));

        mPresenter.requestData();

        ClickUtil.click(mTvCodeCopy, () -> {
            String trim = mTvCode.getText().toString().trim();
            CopyUtil.copy(trim);
        });
        ClickUtil.click(mBtnNext, this::showSmsVerifyDialog);
    }

    /**
     * 显示手机验证码Dialog
     */
    private void showSmsVerifyDialog() {
        sheetDialog = BottomSheetUtil.showCheck(this, App.INSTANCE.getString(R.string.mineverifyGoogleActivity2), false, true, true, CodeTypeEnum.STATUS_5, (pwd, googleCode, smsCode) -> {
            sheetDialog.dismiss();
            mPresenter.submit(googleCode, smsCode, obj.getSecret(), obj.getQrcode());
        });
        sheetDialog.show();
    }

    public void onGoogleCodeSuccess(GoogleCode obj) {
        this.obj = obj;
        mTvCode.setText(obj.getSecret());
        if (TextUtils.isEmpty(obj.getQrcode())) {
            return;
        }
        if (obj.getQrcode().startsWith("/")) {

            ImageUtil.setImage(HttpConfig.BASE_URL + obj.getQrcode(), mIvQrCode);
        } else {
            QRCodeUtil.createImage(obj.getQrcode(), 400, new QRCodeUtil.OnEncodeQRCodeCallback() {
                @Override
                public void onAnalyzeSuccess(Bitmap bitmap) {
                    mIvQrCode.setImageBitmap(bitmap);
                }

                @Override
                public void onAnalyzeFailed() {

                }
            });
        }
    }

    public void onBindSuccess() {
        RxBus.getDefault().send(1456);
        onBackPressed();
    }


}
