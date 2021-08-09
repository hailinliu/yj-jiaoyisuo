package com.sskj.hangqing.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.NewSlideActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = RConfig.BIBI_NE_SLIDE)
public class NewSlideActivity extends BaseActivity<NewSlideActivityPresenter> {
    /*  @BindView(R2.id.ivClose)
      ImageView ivClose;
      @BindView(R2.id.viewPager)
      ViewPager viewPager;
      @BindView(R2.id.tvTitle)
      TextView tvTitle;
      @BindView(R2.id.slidingTabLayout)
      SlidingTabLayout slidingTabLayout;
      @BindView(R2.id.llRoot)
      LinearLayout llRoot;*/
    @BindView(R2.id.llRoot)
    LinearLayout llRoot;
    @BindView(R2.id.ll_content)
    LinearLayout llContent;
    @BindView(R2.id.ll_setting)
    LinearLayout ll_setting;
    @BindView(R2.id.ll_zhanghuanquan)
    LinearLayout ll_zhanghuanquan;
    @BindView(R2.id.tv_phone)
    TextView tv_phone;
    @BindView(R2.id.tv_first)
    TextView tv_first;
    @BindView(R2.id.tv_shiming)
    TextView tv_shiming;
    @BindView(R2.id.imageyou)
    ImageView imageView;
    @BindView(R2.id.ll_congbi)
    LinearLayout llCongbi;
    @BindView(R2.id.ll_tibi)
    LinearLayout llTibi;
    @BindView(R2.id.ll_huazhuan)
    LinearLayout llHuazhuan;
    @BindView(R2.id.ll_xiaoxi)
    LinearLayout llXiaoxi;
    @BindView(R2.id.ll_lianxikefu)
    LinearLayout llLianxikefu;
    @BindView(R2.id.ll_yijianfankui)
    LinearLayout llYijianfankui;
    @BindView(R2.id.ll_gonggao)
    LinearLayout llGonggao;
    @BindView(R2.id.ll_guanyuwomen)
    LinearLayout llGuanyuwomen;
    @BindView(R2.id.ll_bibi)
    LinearLayout llBibi;
    @BindView(R2.id.ll_fabi)
    LinearLayout llFabi;
    @BindView(R2.id.ll_zijinliushui)
    LinearLayout llZijinliushui;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean user;
    // private LoginBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_slide;
    }

    @Override
    public NewSlideActivityPresenter getPresenter() {
        return new NewSlideActivityPresenter();
    }


    @Override
    public boolean getOrientation() {
        return false;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
//        overridePendingTransition(R.anim.fabi_slide_left_in, R.anim.bibi_slide_no_move);
        // RxBus.getDefault().register(this);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);//需要添加的语句
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                user = users.get(0);
                rebuilder(user);
            }
        });
        LiveDataBus.get().with(1101).observe(this,this::fresh);
      /*  ClickUtil.click(ivClose, () -> {
            llRoot.setBackgroundColor(Color.TRANSPARENT);
            finish();
        });*/

//        titles.add("USDT");
//        titles.add("BTC");
//        titles.add("ETH");
//        Disposable disposable = Flowable.fromIterable(titles)
//                .onBackpressureDrop()
//                .map(s -> "/" + s)
//                .subscribe(item -> fragments.add((Fragment) ARouter.getInstance()
//                        .build(RConfig.BIBI_FRAGMENT_SLIDE)
//                        .withString(Constans.QU, item)
//                        .navigation()));

        // LiveDataBus.get().with(RxBusCode.ISLOGIN, SafeSettingBean.class).observe(this,this::rebuilder);
    }

    private void fresh(Object o) {
        tv_first.setVisibility(View.INVISIBLE);
        tv_phone.setText(App.INSTANCE.getString(R.string.hang_zhuce));
        tv_shiming.setText(App.INSTANCE.getString(R.string.hangMitcion));
    }

    private void rebuilder(SafeSettingBean bean) {
        imageView.setVisibility(View.VISIBLE);
        tv_first.setVisibility(View.VISIBLE);
        tv_first.setText(StringUtil.getFirstName(bean.getUsername()));
        tv_phone.setText(bean.getUsername());
        tv_phone.setTextSize(14);
        tv_phone.setTextColor(getResources().getColor(R.color.libTextWhite));
        tv_shiming.setText(bean.getRealVerified() == 2 ? App.INSTANCE.getString(R.string.hang_yishiming) : App.INSTANCE.getString(R.string.hang_weishiming));
        tv_shiming.setTextColor(getResources().getColor(R.color.libRed));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getRawX() > llContent.getMeasuredWidth()) {
            llRoot.setBackgroundColor(Color.TRANSPARENT);
            finish();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void initData() {
    mPresenter.getSecuritySetting();
    }

    /*   @Subscribe(threadMode = ThreadMode.MAIN,code=13579)
       public void updateLogin(LoginBean bean){
           tv_phone.setText(bean.getEmail().isEmpty()?bean.getMobile():bean.getEmail());
           tv_phone.setTextSize(16);
           tv_shiming.setText(bean.getRealName().isEmpty()?"未实名认证":"已实名认证");
           tv_shiming.setTextColor(getResources().getColor(R.color.libRed));

       }*/
    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(ll_zhanghuanquan, () -> {
            if (user != null) {
                ARouter.getInstance().build(RConfig.ACCOUNTSAFETYACTIVITY).navigation();
            } else {
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }

        });
        ClickUtil.click(tv_phone, () -> {
            if (user != null) {
                return;
            } else {
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }

        });
        ClickUtil.click(tv_shiming, () -> {
            if (tv_shiming.getText().equals(App.INSTANCE.getString(R.string.hang_weishiming))) {
                ARouter.getInstance().build(RConfig.IDENTIFYACTIVITY).navigation();
            }

        });
        ClickUtil.click(llHuazhuan,()->{
            ARouter.getInstance().build(RConfig.MINER_TRANSFER).navigation();
        });
        ClickUtil.click(ll_setting, () -> {
            ARouter.getInstance().build(RConfig.NEWSETTINGACTIVITY).navigation();
        });
        llCongbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.TIBI_ACTIVITY_RECHARGE).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llTibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.TIBI_WITHDRAW).withString("email",email).withString("phone",phone).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();

            }
        });
        llBibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coin = SPUtil.get("cointype","BTC/USDT");
                if (user != null) {
                    ARouter.getInstance().build(RConfig.BIBI_RECORD).withString("code",coin).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llFabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llZijinliushui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.CONGRECORDACTIVITY).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llLianxikefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.CONTACTSERVICEACTIVITY).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }

        });
        llXiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.INFORMATIONCENTERACTIVITY).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llYijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.FEEDBACKACTIVITY).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
        llGonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    ARouter.getInstance().build(RConfig.GONGGAOACTIIVITY).navigation();
                } else
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        //注释掉activity本身的过渡动画
        overridePendingTransition(0, R.anim.bibi_slide_left_out);
    }

 String email;
 String phone;
    public void setUI(SafeSettingBean data) {
        tv_shiming.setTextColor(getResources().getColor(R.color.libRed));
        tv_shiming.setText(data.getRealVerified() == 2 ? App.INSTANCE.getString(R.string.hang_yishiming) : App.INSTANCE.getString(R.string.hang_weishiming));
        email = data.getEmail();
        phone = data.getMobilePhone();
    }
}
