package com.sskj.lightning;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.shizhefei.view.coolrefreshview.IPullHeaderFactory;
import com.shizhefei.view.coolrefreshview.PullHeader;
import com.shizhefei.view.coolrefreshview.header.MaterialHeader;
import com.sskj.common.base.App;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.box.FingerActivityLifecycleCallbacks;
import com.sskj.lib.router.service.CommonService;
import com.sskj.lib.router.service.LibService;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.NightModeConfig;
import com.sskj.lightning.ui.activity.MainActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.zxy.recovery.core.Recovery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-18 08:52
 */

/**
 * 初始BaseApplication
 */
public class BaseApplication extends MultiDexApplication {
    @Autowired
    CommonService commonService;
    @Autowired
    LibService libService;


    @Override
    public void onCreate() {
        super.onCreate();
      //  if(isAppProcess()){
        BaseActivityLifecycleCallbacks callbacks= new BaseActivityLifecycleCallbacks();
        App.INSTANCE.registerActivityLifecycleCallbacks(callbacks);
        SPUtil.put("callbacks",callbacks);
            ArrayList<Locale> locales = new ArrayList<>();
            locales.add(Locale.ENGLISH);
            locales.add(Locale.SIMPLIFIED_CHINESE);
           // locales.add(Locale.TRADITIONAL_CHINESE);
            locales.add(Locale.KOREAN);
            locales.add(Locale.JAPANESE);
            LanguageUtil.setLocales(locales);
            String s = SPUtil.get("language", "", "language");
            if (TextUtils.isEmpty(s)) {
                SPUtil.put("language", Locale.ENGLISH.toString(), "language");
            }
            if(BuildConfig.DEBUG) {
                Recovery.getInstance()
                        .debug(true)
                        .recoverInBackground(false)
                        .recoverStack(true)
                        .mainPage(MainActivity.class)
                        .recoverEnabled(true)
                        .callback(null)
                        .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                        .init(this);
                ARouter.openLog();
                ARouter.openDebug();
            }else {
                Thread.setDefaultUncaughtExceptionHandler((t, e) -> System.out.println(e));
            }
//        Recovery.getInstance()
//                .debug(true)
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(MainActivity.class)
//                .recoverEnabled(true)
//                .callback(null)
//                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
//                .init(this);
//        ARouter.openLog();
//        ARouter.openDebug();
            ARouter.init(this);
            ARouter.getInstance().inject(this);
            commonService.initOkGoHttp(this, true);
            CrashReport.initCrashReport(this, "ca36751b7a", false);


            CoolRefreshView.setPullHeaderFactory(new IPullHeaderFactory() {
                @Override
                public PullHeader made(Context context) {
                    MaterialHeader commonHeader = new MaterialHeader(context);
                    return commonHeader;

                }

                @Override
                public boolean isPinContent() {
                    return true;
                }
            });
            App.INSTANCE.registerActivityLifecycleCallbacks(new FingerActivityLifecycleCallbacks());
            Toasty.Config.getInstance()
                    .apply(); // required

            //根据app上次退出的状态来判断是否需要设置夜间模式,提前在SharedPreference中存了一个是否是夜间模式的boolean值
            boolean isNightMode = NightModeConfig.getInstance().getNightMode(getApplicationContext());
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }

   // }
    public boolean isAppProcess() {
        String processName = getProcessName();
        if (processName == null || !processName.equalsIgnoreCase(this.getPackageName())) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 获取运行该方法的进程的进程名
     * @return 进程名称
     */
    public String getProcessName() {
        int processId = android.os.Process.myPid();
        String processName = null;
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        Iterator iterator = manager.getRunningAppProcesses().iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo processInfo = (ActivityManager.RunningAppProcessInfo) (iterator.next());
            try {
                if (processInfo.pid == processId) {
                    processName = processInfo.processName;
                    return processName;
                }
            } catch (Exception e) {
//                LogD(e.getMessage())
            }
        }
        return processName;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            if (SPUtil.get(SPConfig.IS_FINGER_ON,false)) {
                SPUtil.put(SPConfig.IS_LOCK, true);
            }
        }
    }
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageUtil.attachBaseContext(base));
    }

}
