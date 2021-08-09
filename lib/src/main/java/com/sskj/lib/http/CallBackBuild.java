package com.sskj.lib.http;

import android.arch.lifecycle.LifecycleOwner;

public abstract class CallBackBuild<T> {
    private IBaseViewLife iBaseViewLife;
    private LifecycleOwner lifecycleOwner;

    /**
     * 显示加载框和界面生命周期绑定
     *
     * @param iBaseViewLife
     * @return
     */
    public CallBackBuild<T> loadBind(IBaseViewLife iBaseViewLife) {
        this.iBaseViewLife = iBaseViewLife;
        return this;
    }

    /**
     * 不显示加载框和界面生命周期绑定
     *
     * @return
     */
    public CallBackBuild<T> unLoadBind(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    /**
     * 不显示加载框不和界面生命周期绑定
     *
     * @return
     */
    public CallBackBuild<T> unLoadunBind() {
        return this;
    }

    public CallBackOption build(ILoadBind<T> iLoadBind) {
        CallBackOption<T> callBackOption = new CallBackOption<T>() {
        };
        if (iBaseViewLife != null) {
            callBackOption.loadBind(iBaseViewLife).execute(iLoadBind);
        }
        if (lifecycleOwner != null) {
            callBackOption.unLoadBind(iBaseViewLife).execute(iLoadBind);
        }
        if (iBaseViewLife == null && lifecycleOwner == null) {
            callBackOption.unLoadunBind().execute(iLoadBind);
        }
        return callBackOption;
    }
}
