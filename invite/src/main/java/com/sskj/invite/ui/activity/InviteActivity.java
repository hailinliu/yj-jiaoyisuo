package com.sskj.invite.ui.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.sskj.common.util.ClickUtil;
import com.sskj.invite.R;
import com.sskj.invite.R2;
import com.sskj.invite.bean.MineInviteBean;
import com.sskj.invite.presenter.InvitePresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.NumberUtil;

import butterknife.BindView;

/**
 * 邀请返佣主页
 */
@Route(path = RConfig.INVITE_MENU)
public class InviteActivity extends BaseActivity<InvitePresenter> {
    @BindView(R2.id.team_tv)
    TextView teamTv;

    @BindView(R2.id.reward_tv)
    TextView rewardTv;

    @BindView(R2.id.menu_invite)
    SuperTextView menuInvite;
    @BindView(R2.id.menu_account)
    SuperTextView menuAccount;
    @BindView(R2.id.menu_money)
    SuperTextView menuMoney;
    @BindView(R2.id.today_reward_tv)
    TextView todayRewardTv;
    @BindView(R2.id.tv_team_add)
    TextView tvTeamAdd;
    @BindView(R2.id.tv_push_person_number)
    TextView tvPushPersonNumber;
    @BindView(R2.id.tv_team_person_number)
    TextView tvTeamPersonNumber;


    @Override
    public int getLayoutId() {
        return R.layout.invite_activity_invite_menu;
    }

    @Override
    public InvitePresenter getPresenter() {
        return new InvitePresenter();
    }

    @Override
    public void initView() {
        setTitle("邀请返佣");
        ClickUtil.click(menuInvite, () -> {
            MineInviteActivity.start(this);
        });

        ClickUtil.click(menuAccount, () -> {
            TeamActivity.start(this);
        });

        ClickUtil.click(menuMoney, () -> {
            RewardActivity.start(this);
        });
    }

    @Override
    public void initData() {
        mPresenter.getShareInfo();
    }

    public void updateUi(MineInviteBean inviteInfo) {
        teamTv.setText(inviteInfo.todayMoney);
        tvTeamAdd.setText(inviteInfo.directUserToday);
        todayRewardTv.setText(inviteInfo.yesterdayMoney);
        rewardTv.setText(inviteInfo.totalMoney);
        tvPushPersonNumber.setText(inviteInfo.directUser);
        tvTeamPersonNumber.setText(inviteInfo.totalDirectUser);
    }

}
