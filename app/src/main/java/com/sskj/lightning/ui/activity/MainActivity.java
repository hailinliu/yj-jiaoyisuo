package com.sskj.lightning.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sskj.bibi.ui.fragment.BibiMainFragment;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.SPUtil;
import com.sskj.hangqing.ui.fragment.HangqingFragment;
import com.sskj.hangqing.ui.fragment.NewHangFragment;
import com.sskj.level.ui.fragment.LevelMainFragment;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.GonggaoBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.TabItem;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.ui.fragment.AppUpDateFragment;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lightning.R;
import com.sskj.lightning.component.DaggerUserDataComponent;
import com.sskj.lightning.presenter.MainActivityPresenter;
import com.sskj.mine.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import sskj.lee.appupdatelibrary.BaseUpdateDialogFragment;
import sskj.lee.appupdatelibrary.BaseVersion;

@Route(path = RConfig.APP_MAIN)
public class MainActivity extends BaseActivity<MainActivityPresenter> {
    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @Inject
    UserViewModel userViewModel;
/*    @BindView(R.id.tv_anniu)
    TextView tv;*/
    @Autowired
    int myflag;
    private SafeSettingBean userData;
    private int curPos = 0;
    private boolean isflag;
    private ArrayList<Fragment> fragments;
    SparseArray<Fragment> tabMap = new SparseArray<>();

    @Override
    protected int getLayoutId() {
        return R.layout.app_activity_main;
    }

    @Override
    public boolean getImmersion() {
        return false;
    }

    @Override
    public MainActivityPresenter getPresenter() {
        return new MainActivityPresenter();
    }
@Subscribe(threadMode = ThreadMode.MAIN,code=RxBusCode.REFRESH_RE)
public void ref(){
        mPresenter.initNewSocket();
       // mPresenter.initNewSocket1();
    /*mPresenter.initNewSocket();
    mPresenter.initNewSocket1();*/
}
    @Override
    protected void dealFirstSaveInstance(Bundle savedInstanceState) {
        FragmentManager mManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            List<Fragment> fragments = mManager.getFragments();
            FragmentTransaction fragmentTransaction = mManager.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                fragmentTransaction.remove(fragments.get(i));
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        RxBus.getDefault().register(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }

        });
        LiveDataBus.get().with(RxBusCode.LOGIN_MAIN,Integer.class).observe(this,this::refresh);
        ArrayList<CustomTabEntity> tabItems = new ArrayList<>();
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.appmainActivity1), R.mipmap.lib_icon_menu_first_unselect, R.mipmap.lib_icon_menu_first_select));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.appmainActivity3), R.mipmap.lib_icon_menu_second_unselect, R.mipmap.lib_icon_menu_second_select));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.appmainActivity7), R.mipmap.lib_icon_menu_six_unselected, R.mipmap.lib_icon_menu_six_select));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.app_heyue), R.mipmap.lib_icon_unselected, R.mipmap.lib_level_selected));
      //  tabItems.add(new TabItem(App.INSTANCE.getString(R.string.appmainActivity4), R.mipmap.lib_icon_menu_four_unselect, R.mipmap.lib_icon_menu_four_select));
        tabItems.add(new TabItem(App.INSTANCE.getString(R.string.appmainActivity5), R.mipmap.lib_icon_menu_five_unselect, R.mipmap.lib_icon_menu_five_select));
        commonTabLayout.setTabData(tabItems);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                    changeTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        fragments = new ArrayList<>();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.HANG_HANGQING).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.NEWHANGFRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.BIBI_FRAGMENT_MAIN).withString(Constans.CODE, "BTC/USDT").withBoolean("isnewflag",isflag).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.LEVELMAINFRAGMENT).withString(Constans.CODE, "BTC/USDT").navigation());
        //fragments.add((Fragment) ARouter.getInstance().build(RConfig.FABI_FRAGMENT_FABI_MAIN).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.MINE_FRAGMENT_MAIN).navigation());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, fragments.get(0))
                .show(fragments.get(0))
                .commitAllowingStateLoss();
        tabMap.put(0, fragments.get(0));
    }

    private void refresh(Integer integer) {
        commonTabLayout.setCurrentTab(0);
        changeTab(0);
    }

/*@Subscribe(threadMode = ThreadMode.MAIN,code =1234)
public void getdemo(){
    commonTabLayout.setCurrentTab(1);
    changeTab(1);
}*/


    @Override
    protected void onResume() {
        super.onResume();
       // changeTab(2);

    }

    public void changeTab(int position) {
        if (tabMap.get(position) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, fragments.get(position))
                    .hide(fragments.get(curPos))
                    .show(fragments.get(position))
                    .commitAllowingStateLoss();
            tabMap.put(position, fragments.get(position));
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(fragments.get(curPos))
                    .show(fragments.get(position))
                    .commitAllowingStateLoss();
            if(position==0){
               HangqingFragment fragment=(HangqingFragment)fragments.get(position);
               fragment.initData();
            }else if(position==1){
             LiveDataBus.get().with(RxBusCode.REFRESHHANGQING,Integer.class).postValue(1);
            }
            else if(position==2){
                BibiMainFragment fragment=(BibiMainFragment)fragments.get(position);
                fragment.initData();
            }else if(position==3){
                LevelMainFragment fragment=(LevelMainFragment)fragments.get(position);
                fragment.initData();
            }
            else if(position==4){
               MineFragment fragment=  (MineFragment)fragments.get(position);
               fragment.initData();
            }
        }
        curPos = position;
    }


    @Override
    protected void initData() {
      /*  tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.initNewSocket();
            }
        });*/
      /*new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              mPresenter.initNewSocket();
          }
      },10000);*/
     /* new Thread(new Runnable() {
          @Override
          public void run() {
              mPresenter.initNewSocket();
          }
      }).start();*/
     runOnUiThread(new Runnable() {
         @Override
         public void run() {
             mPresenter.initNewSocket();
         }
     });
       // mPresenter.initWebSocket();
        //mPresenter.initWebSocket1();
        //mPresenter.initNewSocket();
        //mPresenter.initNewSocket1();
        mPresenter.getRate("USD","CNY");
        mPresenter.getNewVersion();
  /*      LiveDataBus.get().with(RxBusCode.SHOW_GONGGAO, Integer.class)
                .observe(this, integer -> showGonggao());*/
        LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class)
                .observe(this, integer -> {
                    commonTabLayout.setCurrentTab(integer);
                    changeTab(integer);
                });
        LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB1, Integer.class)
                .observe(this, integer -> {
                    if(tabMap.get(integer)==null){
                        isflag = true;
                        fragments.set(integer,(Fragment) ARouter.getInstance().build(RConfig.BIBI_FRAGMENT_MAIN).withString(Constans.CODE, "BTC/USDT").withBoolean("isnewflag",isflag).navigation());
                    }else {
                        RxBus.getDefault().send(1234);
                    }
                    commonTabLayout.setCurrentTab(integer);
                    changeTab(integer);
                });
        LiveDataBus.get().with(RxBusCode.SHOW_VERSION, AppVersionBean.class)
                .observe(this, versionBean -> {
                    assert versionBean != null;
                    showVersion(versionBean);
                });
    }
    public void showGonggao() {
        mPresenter.getGonggao();
    }

    public void showGonggao(GonggaoBean data) {
        if (data == null || TextUtils.isEmpty(data.getDetails())) {
            return;
        }
       /* if (!TextUtils.isEmpty(data.getDetails().trim())) {
            TipUtil.showGonggao(this, data.getTile(), data.getDetails());
        }*/
    }
    private void showVersion(AppVersionBean versionBean) {
        String verName = APKVersionCodeUtils.getVerName(this);
        versionBean.setViewStyle(BaseVersion.NOTIFYCATION_STYLE);

        if (APKVersionCodeUtils.compareVersion(versionBean.getVersion(), verName) == 1) {
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            getFragmentManager().beginTransaction();
            AppUpDateFragment appUpDateFragment = new AppUpDateFragment();//自定义UI
            Bundle bundle = new Bundle();
            bundle.putSerializable(BaseUpdateDialogFragment.INTENT_KEY, versionBean);
            appUpDateFragment.setArguments(bundle);
            appUpDateFragment.show(fragmentTransaction, "update");
        } else {
            mPresenter.getGonggao();
        }
    }



    public void onVersionDataSuccess(AppVersionBean versionBean) {

        if (versionBean == null) {
            mPresenter.getGonggao();
            return;
        }

        LiveDataBus.get().with(RxBusCode.SHOW_VERSION)
                .postValue(versionBean);

    }

    @Override
    protected void onDestroy() {
        RxBus.getDefault().unregister(this);
        tabMap = null;
        super.onDestroy();
    }
}
