package com.sskj.pay.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hey.lib.HeySpinner;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UploadBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.pay.R;
import com.sskj.pay.R2;
import com.sskj.pay.bean.PayTypeBean;
import com.sskj.pay.component.DaggerUserDataComponent;
import com.sskj.pay.http.HttpConfig;
import com.sskj.pay.presenter.AddPayTypeActivityPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * //添加或修改支付方式
 */
@Route(path = RConfig.PAY_ADD_PAY_TYPE)
public class AddPayTypeActivity extends BaseActivity<AddPayTypeActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivClose)
    ImageView ivClose;
    @BindView(R2.id.heySpinner)
    HeySpinner heySpinner;
    @BindView(R2.id.tvName)
    TextView tvName;
    @BindView(R2.id.etWx)
    EditText etWx;
    @BindView(R2.id.ivQRWx)
    ImageView ivQRWx;
    @BindView(R2.id.rlWx)
    RelativeLayout rlWx;
    @BindView(R2.id.llWx)
    LinearLayout llWx;
    @BindView(R2.id.etAlipay)
    EditText etAlipay;
    @BindView(R2.id.ivQRAlipay)
    ImageView ivQRAlipay;
    @BindView(R2.id.rlAlipay)
    RelativeLayout rlAlipay;
    @BindView(R2.id.llAlipay)
    LinearLayout llAlipay;
    @BindView(R2.id.etBankName)
    EditText etBankName;
    @BindView(R2.id.etBankNum)
    EditText etBankNum;
    @BindView(R2.id.etBranch)
    EditText etBranch;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.tvCancel)
    TextView tvCancel;
    @BindView(R2.id.tvSure)
    TextView tvSure;
    @BindView(R2.id.llBank)
    LinearLayout llBank;
    @Autowired
    PayTypeBean payTypeBean;
    private String filePath;
    private String imgUrl;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private BottomSheetDialog bottomSheetPayType;
    private int curPos;

    @Override
    protected int getLayoutId() {
        return R.layout.pay_activity_add_pay_type;
    }

    @Override
    public AddPayTypeActivityPresenter getPresenter() {
        return new AddPayTypeActivityPresenter();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        ClickUtil.click(rlWx, this::selectPic);
        ClickUtil.click(rlAlipay, this::selectPic);

        Flowable.fromIterable(PayTypeEnum.list())
                .map(PayTypeEnum::getName)
                .toList()
                .subscribe((strings, throwable) -> {
                    if (!payTypeBean.isAdd()) {
                        heySpinner.setEnabled(true);
                        heySpinner.setClickable(true);
                        heySpinner.setFocusable(true);
                        ClickUtil.click(heySpinner, () -> {
                            bottomSheetPayType = BottomSheetUtil.getBottomSheet(this, null, (recyclerView, position, view) -> {
                                bottomSheetPayType.dismiss();
                                heySpinner.setText(strings.get(position));
                                curPos = position;
                                switch (position) {
                                    case 0:

                                        imgUrl = null;
                                        etAlipay.setText("");
                                        etBankName.setText("");
                                        etBankNum.setText("");
                                        etBranch.setText("");
                                        ivQRAlipay.setImageDrawable(null);
                                        ivQRAlipay.setVisibility(View.GONE);
                                        llWx.setVisibility(View.VISIBLE);
                                        llAlipay.setVisibility(View.GONE);
                                        llBank.setVisibility(View.GONE);
                                        payTypeBean = new PayTypeBean(PayTypeEnum.WX);
                                        break;
                                    case 1:
                                        etWx.setText("");
                                        etBankName.setText("");
                                        etBankNum.setText("");
                                        etBranch.setText("");
                                        ivQRWx.setImageDrawable(null);
                                        ivQRWx.setVisibility(View.GONE);
                                        llWx.setVisibility(View.GONE);
                                        llAlipay.setVisibility(View.VISIBLE);
                                        llBank.setVisibility(View.GONE);
                                        payTypeBean = new PayTypeBean(PayTypeEnum.ALIPAY);

                                        break;
                                    case 2:
                                        imgUrl = null;
                                        etWx.setText("");
                                        etAlipay.setText("");
                                        llWx.setVisibility(View.GONE);
                                        llAlipay.setVisibility(View.GONE);
                                        llBank.setVisibility(View.VISIBLE);
                                        payTypeBean = new PayTypeBean(PayTypeEnum.BANK);

                                        break;
                                }
                            }, strings);
                            bottomSheetPayType.show();
                        });
                    }
                });
        heySpinner.setText(payTypeBean.getPayTypeEnum().getName());
        curPos=PayTypeEnum.list().indexOf(payTypeBean.getPayTypeEnum());

        if (payTypeBean.isAdd()) {
            switch (payTypeBean.getPayTypeEnum()) {
                case WX:
                    imgUrl = payTypeBean.getImg();
                    llWx.setVisibility(View.VISIBLE);
                    llAlipay.setVisibility(View.GONE);
                    llBank.setVisibility(View.GONE);
                    ivQRWx.setVisibility(View.VISIBLE);

                    etWx.setText(payTypeBean.getAccount());
                    ImageUtil.setImage(HttpConfig.BASE_IMG_URL + payTypeBean.getImg(), ivQRWx);
                    break;
                case ALIPAY:
                    imgUrl = payTypeBean.getImg();
                    llWx.setVisibility(View.GONE);
                    llAlipay.setVisibility(View.VISIBLE);
                    llBank.setVisibility(View.GONE);
                    ivQRAlipay.setVisibility(View.VISIBLE);
                    ImageUtil.setImage(HttpConfig.BASE_IMG_URL + payTypeBean.getImg(), ivQRAlipay);
                    etAlipay.setText(payTypeBean.getAccount());
                    break;
                case BANK:
                    llWx.setVisibility(View.GONE);
                    llAlipay.setVisibility(View.GONE);
                    llBank.setVisibility(View.VISIBLE);
                    etBankNum.setText(payTypeBean.getAccount());
                    etBranch.setText(payTypeBean.getBranch());
                    etBankName.setText(payTypeBean.getBank());
                    break;
            }
        }

        ClickUtil.click(ivClose, () -> {
            finish();
        });
        ClickUtil.click(tvCancel, () -> {
            finish();
        });
        ClickUtil.click(tvSure, () -> {
            String account = null;
            switch (curPos) {
                case 0:
                    if (TextUtils.isEmpty(etWx.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity1));
                        return;
                    }
                    if (TextUtils.isEmpty(imgUrl)) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity2));
                        return;
                    }
                    if (TextUtils.isEmpty(etPwd.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity3));
                        return;
                    }
                    account = etWx.getText().toString();
                    break;
                case 1:
                    if (TextUtils.isEmpty(etAlipay.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity4));
                        return;
                    }

                    if (TextUtils.isEmpty(imgUrl)) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity5));
                        return;
                    }
                    if (TextUtils.isEmpty(etPwd.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity3));
                        return;
                    }
                    account = etAlipay.getText().toString();

                    break;
                case 2:
                    if (TextUtils.isEmpty(etBankName.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity6));
                        return;
                    }
                    if (TextUtils.isEmpty(etBankNum.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity7));
                        return;
                    }
                    if (TextUtils.isEmpty(etBranch.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity8));
                        return;
                    }
                    if (TextUtils.isEmpty(etPwd.getText())) {
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.payaddPayTypeActivity3));
                        return;
                    }
                    account = etBankNum.getText().toString();
                    break;
            }
            mPresenter.addOrEditPayType(!payTypeBean.isAdd(), payTypeBean.getPayTypeEnum().getType(), userData.getUsername(),
                    account, imgUrl, etBankName.getText().toString(), etBranch.getText().toString(), etPwd.getText().toString());
        });
    }

    @Override
    protected void initData() {
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                tvName.setText(userData.getUsername());

            } else {
                userData = null;
            }
        });
    }

    private void selectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .minimumCompressSize(200)
                .cropWH(400, 400)
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = selectList.get(0);
                    if (media.isCompressed()) {
                        filePath = media.getCompressPath();
                    } else {
                        filePath = media.getPath();
                    }
                    if (curPos == 0) {
                        ivQRWx.setVisibility(View.VISIBLE);
                        ImageUtil.setImage(filePath, ivQRWx);
                    } else {
                        ivQRAlipay.setVisibility(View.VISIBLE);
                        ImageUtil.setImage(filePath, ivQRAlipay);
                    }
                    mPresenter.upload(filePath);
                    break;
            }

        }
    }

    public void uploadSuccess(String data) {
        if (curPos == 0) {
            ivQRWx.setVisibility(View.VISIBLE);

            ImageUtil.setImage(filePath, ivQRWx);
        } else {
            ivQRAlipay.setVisibility(View.VISIBLE);
            ImageUtil.setImage(filePath, ivQRAlipay);
        }
        imgUrl = data;
    }

    public void addOrEditSuccess() {
        finish();
    }
}
