package com.sskj.hangqing.ui.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.facebook.stetho.common.LogUtil;
import com.google.gson.internal.$Gson$Preconditions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.NewSettingActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lib.util.AppUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CommonUtil;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@Route(path = RConfig.NEWSETTINGACTIVITY)
public class NewSettingActivity extends BaseActivity<NewSettingActivityPresenter> {
    @BindView(R2.id.logout_btn)
    Button logout_btn;
    @BindView(R2.id.image)
    ImageView imageView;
    @BindView(R2.id.menu_version)
    SuperTextView menu_version;
    @BindView(R2.id.menu_language)
    SuperTextView menu_language;
    @Inject
    UserViewModel userViewModel;
    private Disposable languageDispo;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_setting;
    }

    @Override
    protected void initData(){
        super.initData();
    }

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        menu_language.setRightString(LanguageUtil.getAppLanguage(this));
        menu_version.setRightString(APKVersionCodeUtils.getVerName(this));
       // super.initView();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBus.get().with(1101).postValue(1);
                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                logoutProvider.logout();

               /* SPUtil.clear();
                userViewModel.clear();
                HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
                httpHeaders.clear();
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();*/
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        menu_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ClickUtil.click(menu_language, this::selectLanguage);
    }
    private BottomSheetDialog bottomSheet;
    private void changeLanguage(Locale locale) {
        LanguageUtil.saveLanguageSetting(App.INSTANCE, locale);
        AppUtil.changeLanguage();

    }
    private void selectLanguage() {
        languageDispo = Flowable.fromIterable(LanguageUtil.locales)
                .map(locale -> CommonUtil.dealLanguage(locale.toString()))
                .toList()
                .subscribe((strings, throwable) -> {
                    bottomSheet = BottomSheetUtil.getBottomSheet(this, null, (recyclerView, position, view) -> {
                        bottomSheet.dismiss();
                        changeLanguage(LanguageUtil.getAppLocaleByToString(CommonUtil.rdealLanguage1(strings.get(position))));
                        menu_language.setRightString(strings.get(position));
                    }, strings);
                    bottomSheet.show();
                });
    }
    @Override
    public NewSettingActivityPresenter getPresenter() {
        return new NewSettingActivityPresenter();
    }
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public int getTitleBarHeight(Context context) {
        Rect out = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(out);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        LogUtil.e("huizhi高:"+dm.heightPixels);
       /* DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        LogUtil.e("屏幕高:"+dm.heightPixels);
       int status_height = dm.heightPixels-out.height();//状态栏高度=屏高-应用区高度
        LogUtil.e("状态栏高度:"+status_height);*/
        //绘制区高度

        //View绘制区域
      /*  Rect outRect2 = new Rect();
        getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect2);//错误方法
        int viewheight =outRect2.height();*/
       int viewheight =getWindow().getDecorView().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        LogUtil.e("绘制区域高度:"+viewheight);
        //状态栏高度：=屏高-状态栏高度-绘制区域高度
        LogUtil.e("标题栏高度:"+(out.height()-viewheight));
        return out.height()-viewheight;
    }

  /*  @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int dd = getTitleBarHeight(this);
            ToastUtil.showShort("title的长度:"+dd);
        }
    }*/
}
