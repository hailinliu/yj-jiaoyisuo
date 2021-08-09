package com.sskj.ident.ui.activity;


import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.ident.R;
import com.sskj.ident.R2;
import com.sskj.ident.component.DaggerUserDataComponent;
import com.sskj.ident.presenter.VerificationMenuActivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.model.room.UserViewModel;

import javax.inject.Inject;

import butterknife.BindView;


@Route(path = RConfig.IDENT_VERIFICATION_MENU)
public class VerificationMenuActivity extends BaseActivity<VerificationMenuActivityPresenter> {
    @BindView(R2.id.stvFirst)
    SuperTextView stvFirst;
    @BindView(R2.id.stvSecond)
    SuperTextView stvSecond;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;

    @Override
    protected int getLayoutId() {
        return R.layout.ident_activity_verification_menu;
    }

    @Override
    public VerificationMenuActivityPresenter getPresenter() {
        return new VerificationMenuActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.identverificationMenuActivity1));
        DaggerUserDataComponent.create().inject(this);
        ClickUtil.click(stvFirst, () -> {

        });
        ClickUtil.click(stvSecond, () -> {

        });
    }

    @Override
    protected void initData() {
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                updateUI();
            } else {
                userData = null;
            }
        });
    }

    private void updateUI() {
      /*  switch (userData.getBasicAuthenticationStatus()) {
            case 0://未认证
                stvFirst.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));

                stvFirst.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity2));
                ClickUtil.click(stvFirst, () -> {
                    ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_FIRST).navigation();

                });
                ClickUtil.click(stvSecond, () -> {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.identverificationMenuActivity3));
                });
                break;
            case 1://初级认证
                stvFirst.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity4));
                stvFirst.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.UP.getColor()));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
                ClickUtil.click(stvFirst, () -> {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.identverificationMenuActivity5));
                });
                ClickUtil.click(stvSecond, () -> {
                    ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_SECOND).navigation();
                });

                break;
        }
        stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
        switch (userData.getHighAuthenticationStatus()) {
            case 1://高级未认证
                stvSecond.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity2));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
                break;
            case 2://高级认证待审核
                stvFirst.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.UP.getColor()));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
                stvSecond.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity6));

                ClickUtil.click(stvSecond, () -> {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.identverificationMenuActivity7));
                });
                break;
            case 3://高级认证通过
                stvFirst.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.UP.getColor()));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.UP.getColor()));
                stvSecond.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity4));
                ClickUtil.click(stvSecond, () -> {
                    ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_RESULT)
                            .withBoolean(Constans.IS_SUCCESS, true)
                            .navigation();
                });
                break;
            case 4://高级认证拒绝
                stvFirst.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.UP.getColor()));
                stvSecond.setRightTextColor(ContextCompat.getColor(App.INSTANCE, ColorEnum.DOWN.getColor()));
                stvSecond.setRightString(App.INSTANCE.getString(R.string.identverificationMenuActivity8));

                ClickUtil.click(stvSecond, () -> {
                    ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_RESULT)
                            .withBoolean(Constans.IS_SUCCESS, false)
                            .navigation();
                });
                break;
        }
*/
    }
}
