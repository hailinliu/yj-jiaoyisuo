package com.sskj.mine.ui.activity;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.presenter.PersonPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 个人信息
 */
@Route(path = RConfig.MINE_PERSON)
public class PersonActivity extends BaseActivity<PersonPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.right_tv)
    TextView rightTv;
    @BindView(R2.id.mobile_tv)
    TextView mobileTv;
    @BindView(R2.id.transition_id_tv)
    TextView transitionIdTv;
    @BindView(R2.id.name_tv)
    TextView nameTv;
    @BindView(R2.id.id_card_tv)
    TextView idCardTv;
    @BindView(R2.id.grade_tv)
    TextView grade_tv;
    @BindView(R2.id.tv_leavl)
    TextView tvLeavl;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_person;
    }

    @Override
    public PersonPresenter getPresenter() {
        return new PersonPresenter();
    }

    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        setTitle(App.INSTANCE.getString(R.string.minepersonActivity1));
    }

    @Override
    public void initData() {
        userViewModel.getUsers().observe(this,users->{
            if (users!=null&&users.size()>0){
                userData = users.get(0);
                updateUserTitle(userData);
            }else {
                 userData=null;
            }
        });
    }


    public void updateUserTitle(SafeSettingBean userData) {
  /*      grade_tv.setText(TextUtils.isEmpty(userData.getVipLevel()) ? "----" : "V" + userData.getVipLevel());
        mobileTv.setText(SPUtil.get(SPConfig.USER_ACCOUNT, ""));
        transitionIdTv.setText(userData.getUserUid());
        nameTv.setText(TextUtils.isEmpty(userData.getUsername()) ? "----" : userData.getUsername());
        idCardTv.setText(TextUtils.isEmpty(userData.getIdCard()) ? "----" : userData.getIdCard());
        tvLeavl.setText(TextUtils.isEmpty(userData.getVipLevel()) ? App.INSTANCE.getString(R.string.minepersonActivity2) : userData.getVipLevel().equals("0") ? App.INSTANCE.getString(R.string.minepersonActivity2) :
                userData.getVipLevel() + App.INSTANCE.getString(R.string.minepersonActivity3));*/
    }

}
