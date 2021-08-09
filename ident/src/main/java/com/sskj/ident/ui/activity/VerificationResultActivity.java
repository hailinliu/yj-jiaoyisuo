package com.sskj.ident.ui.activity;


import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binaryfork.spanny.Spanny;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.ident.R;
import com.sskj.ident.R2;
import com.sskj.ident.component.DaggerUserDataComponent;
import com.sskj.ident.presenter.VerificationResultActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.model.room.UserViewModel;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 认证结果
 */
@Route(path = RConfig.IDENT_VERIFICATION_RESULT)
public class VerificationResultActivity extends BaseActivity<VerificationResultActivityPresenter> {
    @Autowired
    boolean isSuccess;
    @BindView(R2.id.llSuccess)
    LinearLayout llSuccess;
    @BindView(R2.id.tvRetry)
    TextView tvRetry;
    @BindView(R2.id.ivStatus)
    ImageView ivStatus;
    @BindView(R2.id.tvResult)
    TextView tvResult;
    @BindView(R2.id.llFail)
    LinearLayout llFail;
    @Inject
    UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.ident_activity_verification_result;
    }

    @Override
    public VerificationResultActivityPresenter getPresenter() {
        return new VerificationResultActivityPresenter();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        setTitle(isSuccess ? App.INSTANCE.getString(R.string.identverificationResultActivity1) : App.INSTANCE.getString(R.string.identverificationMenuActivity8));
        if (isSuccess) {
            llSuccess.setVisibility(View.VISIBLE);
        } else {

            ivStatus.setImageResource(R.mipmap.ident_icon_fail);
            llFail.setVisibility(View.VISIBLE);
            Spanny spanny = new Spanny(App.INSTANCE.getString(R.string.identverificationResultActivity3))
                    .append(App.INSTANCE.getString(R.string.identverificationResultActivity4), new ForegroundColorSpan(ColorEnum.DOWN.getColor()))
                    .append("!");
            tvRetry.setText(spanny);
            userViewModel.getUsers().observe(this, users -> {
                if (users != null && users.size() > 0) {
                    SafeSettingBean userData = users.get(0);
                   // tvResult.setText(App.INSTANCE.getString(R.string.ident_verificationn_result100) + userData.getReason());
                }
            });
        }
        ClickUtil.click(tvRetry, () -> {
            ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_SECOND)
                    .withBoolean(Constans.IS_SUCCESS, false)
                    .navigation();
            finish();
        });


    }


}
