package com.sskj.tibi.ui.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.presenter.AddAddressActivityPresenter;

import butterknife.BindView;


@Route(path = RConfig.TIBI_ADD_ADDRESS)//添加提币地址
public class AddAddressActivity extends BaseActivity<AddAddressActivityPresenter> {
    @BindView(R2.id.etAddress)
    EditText etAddress;
    @BindView(R2.id.ivScan)
    ImageView ivScan;
    @BindView(R2.id.etNotes)
    EditText etNotes;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @Autowired
    String type;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_add_address;
    }

    @Override
    public AddAddressActivityPresenter getPresenter() {
        return new AddAddressActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibiaddAddressActivity1));
        ClickUtil.click(btSubmit, () -> {
            if (checkParam()) {
                mPresenter.addAddress(type, etAddress.getText().toString(), etNotes.getText().toString());
            }
        });
        ClickUtil.click(ivScan, () -> {
            ARouter.getInstance().build(RConfig.QUICKMARK_QRCODE).navigation(this,Constans.SACN_QR);
        });
    }

    private boolean checkParam() {
        if (TextUtils.isEmpty(etAddress.getText())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.tibiaddAddressActivity2));
            return false;
        }
        return true;
    }

    public void addSuccess() {
        LiveDataBus.get().with(RxBusCode.REFRESHADDRESS,Integer.class).postValue(1);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constans.SACN_QR) {
            String scan_result = data.getStringExtra("scan_result");
            etAddress.setText(TextUtils.isEmpty(scan_result) ? "" : scan_result);
        }
    }
}
