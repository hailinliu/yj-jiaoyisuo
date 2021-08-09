package com.sskj.mine.ui.activity;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.presenter.HelpCenterActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助中心
 */
@Route(path = RConfig.MINE_HELP_CENTER)
public class HelpCenterActivity extends BaseActivity<HelpCenterActivityPresenter> {
    @BindView(R2.id.stvQuestion)
    SuperTextView stvQuestion;
    @BindView(R2.id.stvRule)
    SuperTextView stvRule;
    @BindView(R2.id.stvAgreement)
    SuperTextView stvAgreement;
    @BindView(R2.id.stvAntiMoney)
    SuperTextView stvAntiMoney;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_help_center;
    }

    @Override
    public HelpCenterActivityPresenter getPresenter() {
        return new HelpCenterActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.minehelpCenterActivity1));
        ClickUtil.click(stvQuestion, () -> {
            ARouter.getInstance().build(RConfig.MINE_QUESTION_LIST).navigation();
        });
        ClickUtil.click(stvAgreement, () -> {
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean(Constans.IS_PRIVATE, true).navigation();
        });
        ClickUtil.click(stvRule, () -> {
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean(Constans.IS_RULE, true).navigation();

        });
        ClickUtil.click(stvAntiMoney, () -> {
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean(Constans.IS_ANTI, true).navigation();
        });
    }
}
