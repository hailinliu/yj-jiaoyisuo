package com.sskj.fabi.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.FabiMainFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;


@Route(path = RConfig.FABI_FRAGMENT_FABI_MAIN)
public class FabiMainFragmentActivity extends BaseActivity<FabiMainFragmentPresenter> {
    private SafeSettingBean userData;
    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.rgArea)
    RadioGroup rgArea;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.ivBack)
    ImageView ivBack;
    @BindView(R2.id.tv_xin)
    TextView tv_xin;
    private Disposable rgDispo;
    private boolean isQuickBuy;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_main;
    }

    @Override
    public FabiMainFragmentPresenter getPresenter() {
        return new FabiMainFragmentPresenter();
    }

    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
            // initData();
            // initWebView();
        });
        isQuickBuy = true;
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_QUICK_BUY).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT).navigation());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        DisposUtil.close(rgDispo);
        rgDispo = RxRadioGroup.checkedChanges(rgArea)
                .subscribe(integer -> {
                    if (integer == R.id.rbQuickBuy) {
                       if(userData==null){
                           ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                          return;
                       }
                        viewPager.setCurrentItem(0);
                    } else if (integer == R.id.rbFabi) {
                        viewPager.setCurrentItem(1);
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    //ToastUtil.showShort(App.INSTANCE.getString(R.string.fabi_quick));
                    rgArea.check(R.id.rbQuickBuy);
//                    rgArea
                } else if (position == 1) {
                    rgArea.check(R.id.rbFabi);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LiveDataBus.get().with(RxBusCode.CHANGE_QUICK_BUY)
                .observe(this, o -> {
                    rgArea.check(R.id.rbQuickBuy);
                });
        LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB)
                .observe(this, o -> {
                    rgArea.check(R.id.rbFabi);
                });

    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(ivBack, this::finish);
        tv_xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.AGREEMENTACTIVITY).withInt("isflag",2).navigation();
            }
        });
    }

    @Override
    public void onDestroy() {
        DisposUtil.close(rgDispo);
        super.onDestroy();
    }
}
