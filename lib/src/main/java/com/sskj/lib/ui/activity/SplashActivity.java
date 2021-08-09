package com.sskj.lib.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.R;
import com.sskj.lib.R2;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.enums.AreaCodeEnum;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.receiver.HomeBroadcastReceiver;
import com.sskj.lib.receiver.ScreenBroadcastReceiver;
import com.sskj.lib.util.ImageUtil;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;


public class SplashActivity extends Activity {
    @BindView(R2.id.ivBg)
    ImageView ivBg;
    private HomeBroadcastReceiver mHomeReceiver;
    private ScreenBroadcastReceiver mScreenReceiver;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }

      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                StompClient mStompClient;
                mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "wss://www.yolocoin.uk/market/market-ws/websocket");

                mStompClient.lifecycle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(lifecycleEvent -> {
                    lifecycleEvent.getType();
                });
                mStompClient.topic("/topic/market/trade-plate/BTC/USDT").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe((StompMessage topicMessage)->{
                            LogUtil.i("数据是:"+topicMessage.getPayload());
                        },throwable -> {
                            LogUtil.e("链接错误",throwable);
                        });
                mStompClient.connect();
            }
        }).start();*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//        setNavigationBarColor();
      NavigationBarStatusBar(this,true);
        setContentView(R.layout.lib_activity_splash);
        ButterKnife.bind(this);
        ImageUtil.setOriginImage(R.mipmap.lib_splash, ivBg);
        if (mHomeReceiver == null) {
            mHomeReceiver = new HomeBroadcastReceiver();
            mHomeReceiver.registerScreenBroadcastReceiver(App.INSTANCE);
        }
        if (mScreenReceiver == null) {
            mScreenReceiver = new ScreenBroadcastReceiver();
            mScreenReceiver.registerScreenBroadcastReceiver(App.INSTANCE);
        }
       /* OkGo.<HttpData<List<AreaCodeEnum>>>post(BaseHttpConfig.BASE_URL + "/app/user/getAreaCode")
                .execute(new JsonCallBack<HttpData<List<AreaCodeEnum>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<AreaCodeEnum>>> response) {
                        HttpData<List<AreaCodeEnum>> httpData = response.body();

                        SPUtil.putBean(SPConfig.AREA_CODE_LIST, (ArrayList) httpData.getData());
                    }
                });*/
        disposable = Flowable.timer(3000, TimeUnit.MILLISECONDS).compose(RxSchedulersHelper.transformer()).subscribe(aLong -> {
            /*if(SPUtil.get(SPConfig.ISLOGIN,false)){
                ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
            }else {
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }*/
             ARouter.getInstance().build(RConfig.APP_MAIN).navigation();
            finish();
        });

    }

    /**
     * 导航栏，状态栏隐藏
     * @param activity
     */
    public static void NavigationBarStatusBar(Activity activity,boolean hasFocus){
        if (hasFocus && Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
