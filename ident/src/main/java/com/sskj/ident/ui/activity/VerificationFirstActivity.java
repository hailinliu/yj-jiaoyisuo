package com.sskj.ident.ui.activity;


import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.ident.R;
import com.sskj.ident.R2;
import com.sskj.ident.component.DaggerUserDataComponent;
import com.sskj.ident.presenter.VerificationFirstActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.SchemeUtil;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * 初级认证
 */
@Route(path = RConfig.IDENT_VERIFICATION_FIRST)
public class VerificationFirstActivity extends BaseActivity<VerificationFirstActivityPresenter> {
    @BindView(R2.id.etName)
    EditText etName;
    @BindView(R2.id.etIDCard)
    EditText etIDCard;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @Inject
    UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.ident_activity_verification_first;
    }

    @Override
    public VerificationFirstActivityPresenter getPresenter() {
        return new VerificationFirstActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.identverificationFirstActivity1));
        DaggerUserDataComponent.create().inject(this);
        ToastNextInputs inputs = new ToastNextInputs();
        inputs.clear();
        inputs
                .add(etName, SchemeUtil.notEmpty(etName))
                .add(etIDCard, SchemeUtil.notEmpty(etIDCard));
        ClickUtil.click(btSubmit, () -> {
            if (inputs.test()) {
                mPresenter.verificationFirst(etName.getText().toString(), etIDCard.getText().toString());
            }
        });
    }

    public void certificationSuccess() {
        userViewModel.update();
        finish();
    }


}
