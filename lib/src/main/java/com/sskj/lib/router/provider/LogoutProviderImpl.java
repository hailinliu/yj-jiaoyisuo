package com.sskj.lib.router.provider;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.sskj.common.base.AppManager;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.component.DaggerUserDataComponent;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.TipUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@Route(path = "/lib/service/logout")
public class LogoutProviderImpl implements LogoutProvider {
    @Inject
    UserViewModel userViewModel;

    private boolean isShow = false;
    private MaterialDialog exitTip;
    private MaterialDialog tip;
    private boolean isShowTip;

    @Override
    public void logout() {
        isShow = false;
        DaggerUserDataComponent.create().inject(this);
        SPUtil.clear();
        HttpHeaders commonHeaders = OkGo.getInstance().getCommonHeaders();
        commonHeaders.remove(SPConfig.TOKEN);
        commonHeaders.remove(SPConfig.ID);
        commonHeaders.remove(SPConfig.STOCK_USER_ID);
        HttpParams commonParams = OkGo.getInstance().getCommonParams();
        commonParams.remove(SPConfig.ID);
        commonParams.remove(SPConfig.STOCK_USER_ID);
        userViewModel.clear();

        AppManager.getAppManager().finishAllActivity("com.sskj.lightning.ui.activity.MainActivity");
        ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
    }


    @Override
    public void logout(String msg) {
        if ((exitTip != null && exitTip.isShowing()) || isShow) {
            return;
        }
        isShow = true;
        Activity activity = AppManager.getAppManager().currentActivity();
        if (activity instanceof LifecycleOwner) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) activity;
            LiveDataBus.get().with(RxBusCode.LOGOUT).observe(lifecycleOwner, o -> {
                AndroidSchedulers.mainThread().scheduleDirect(() -> {
                    if ((exitTip != null && exitTip.isShowing())) {
                        return;
                    }
                    exitTip = TipUtil.getExitTip(activity, msg, () -> {
                        exitTip.dismiss();
                        logout();
                    });
                });
            });
            LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
        }


    }


    @Override
    public void init(Context context) {

    }
}
