package com.sskj.mine.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.base.BaseActivityLifecycleCallbacks;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.AppManager;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.ui.fragment.AppUpDateFragment;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lib.util.AppUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.NightModeConfig;
import com.sskj.lib.util.TipUtil;
import com.sskj.mine.BuildConfig;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.presenter.SettingPresenter;

import java.util.Locale;
import java.util.Stack;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import sskj.lee.appupdatelibrary.BaseUpdateDialogFragment;
import sskj.lee.appupdatelibrary.BaseVersion;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;

/**
 * @author Hey
 * Create at  2019/05/03
 */

/**
 * 设置界面SettingActivity
 */
public class SettingActivity extends BaseActivity<SettingPresenter> {

    @BindView(R2.id.menu_language)
    SuperTextView menuLanguage;
    @BindView(R2.id.menu_version)
    SuperTextView menuVersion;
    @BindView(R2.id.ccBack)
    ConstraintLayout ccBack;
    @BindView(R2.id.logout_btn)
    Button logout_btn;
    @BindView(R2.id.switchBtn)
    Switch aSwitch;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private BottomSheetDialog bottomSheet;
    private Disposable languageDispo;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_setting;
    }

    @Override
    public SettingPresenter getPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        RxBus.getDefault().register(this);
     //   SPUtil.put("isSelected",false);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
                logout_btn.setVisibility(View.VISIBLE);
            } else {
                userData = null;
                logout_btn.setVisibility(View.GONE);
            }
        });
       boolean b = SPUtil.get("isflag",false);
       aSwitch.setChecked(b);
      //  setTitle(App.INSTANCE.getString(R.string.minesettingActivity1));
        menuLanguage.setRightString(CommonUtil.dealLanguage(LanguageUtil.getAppLocale(App.INSTANCE).toString()));

    }

    /*@Override
    public void onBackPressed() {
        finish();
      //  super.onBackPressed();
    }*/

    @Override
    protected void initEvent() {
       ClickUtil.click(ccBack, ()->{
          // ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
       /*  boolean b = SPUtil.get("isSelected",false);
         if(b){
             RxBus.getDefault().send(1109);
         }
           RxBus.getDefault().send(1109);*/
         /*  if(SPUtil.get("isSelected",false)){

           }*/
           LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class).postValue(3);
           finish();
       });
        ClickUtil.click(menuLanguage, this::selectLanguage);
        int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(currentMode != Configuration.UI_MODE_NIGHT_YES){
            aSwitch.setChecked(false);
        }else {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtil.put("isflag",isChecked);
                SPUtil.put("bairi",true);
                //获取当前的模式，设置相反的模式，这里只使用了，夜间和非夜间模式
               /* int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentMode != Configuration.UI_MODE_NIGHT_YES) {

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(),false);
                }*/


                if(isChecked){
                    //保存夜间模式状态,Application中可以根据这个值判断是否设置夜间模式
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    //ThemeConfig主题配置，这里只是保存了是否是夜间模式的boolean值
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(),true);
                    RxBus.getDefault().send(1989);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    NightModeConfig.getInstance().setNightMode(getApplicationContext(),false);
                    RxBus.getDefault().send(1988);
                }
                //BaseActivityLifecycleCallbacks callbacks= (BaseActivityLifecycleCallbacks)SPUtil.get("callbacks",null);
               // App.INSTANCE.unregisterActivityLifecycleCallbacks(callbacks);
                Stack<Activity> allActivities = AppManager.getAppManager().getAllActivities();
                for(Activity a:allActivities){
                    a.recreate();
                }

              //  isSelected = true;
             /* runOnUiThread(new Runnable() {
                  @Override
                  public void run() {

                  }
              });*/

              //  recreate();//需要recreate才能生效
            }
        });
        ClickUtil.click(logout_btn, () -> {
            TipUtil.getSureTip(this, App.INSTANCE.getString(R.string.minesettingActivity2), App.INSTANCE.getString(R.string.minesettingActivity3), App.INSTANCE.getString(R.string.minesettingActivity4), () -> {

                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                logoutProvider.logout();
            });
        });


    }


    private void selectLanguage() {
        languageDispo = Flowable.fromIterable(LanguageUtil.locales)
                .map(locale -> CommonUtil.dealLanguage(locale.toString()))
                .toList()
                .subscribe((strings, throwable) -> {
                    bottomSheet = BottomSheetUtil.getBottomSheet(this, null, (recyclerView, position, view) -> {
                        bottomSheet.dismiss();
                        changeLanguage(LanguageUtil.getAppLocaleByToString(CommonUtil.rdealLanguage(strings.get(position))));
                    }, strings);
                    bottomSheet.show();
                });
    }

    private void changeLanguage(Locale locale) {

        LanguageUtil.saveLanguageSetting(App.INSTANCE, locale);

        AppUtil.changeLanguage();

    }


    @Override
    public void initData() {
        String versionName = BuildConfig.VERSION_NAME;
        menuVersion.setRightString("V" + versionName);
        ClickUtil.click(menuVersion, () -> {
            mPresenter.checkVersion();
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,code =1100)
    public void setLayout(){
       logout_btn.setVisibility(View.INVISIBLE);
    }
    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    public void updateUi() {
        userViewModel.update();
    }


    public void onVersionDataSuccess(AppVersionBean versionBean) {
        if (versionBean == null) {
            return;
        }
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
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DisposUtil.close(languageDispo);
        RxBus.getDefault().unregister(this);
    }


}
