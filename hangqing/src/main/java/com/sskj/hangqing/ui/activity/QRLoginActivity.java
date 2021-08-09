package com.sskj.hangqing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.QRLoginActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.QRLOGINACTIVITY)
public class QRLoginActivity extends BaseActivity<QRLoginActivityPresenter> {
    @BindView(R2.id.tv_denglu)
    TextView tvDenglu;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;
    @Autowired
    String code;
    @Override
    protected int getLayoutId() {
        return R.layout.hangqing_activity_qr_login;
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
        tvDenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saomaLogin(code);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
       // super.initView();
    }

    @Override
    protected void initData() {
        //super.initData();
    }

    @Override
    public QRLoginActivityPresenter getPresenter() {
        return new QRLoginActivityPresenter();
    }
    public void setMessage(String msg) {
        ToastUtil.showShort(msg);
        if(msg.contains("成功")){
            finish();
        }
    }

}
