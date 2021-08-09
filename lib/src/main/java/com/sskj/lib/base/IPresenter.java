package com.sskj.lib.base;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.OkGo;

import java.lang.ref.WeakReference;

/**
 * eated by Lee on 2018/1/25 0025.
 */
public class IPresenter<VIEW extends IBaseView> {
    public VIEW mView;
    private WeakReference<VIEW> mReference;
    public Activity mContext;

    public Context getContext() {
        return mContext;
    }

    /**
     * 绑定View
     *
     * @param view
     * @param context
     */
    public void attachView(VIEW view, Activity context) {
        mReference = new WeakReference<VIEW>(view);
        mView = mReference.get();
        mContext = context;
    }

    public void showLoading() {
        if (mContext != null) {
            if (mContext instanceof BaseActivity) {
                ((BaseActivity) mContext).showLoading();
            }
        }
    }

    public void hideLoading() {
        if (mContext != null) {
            if (mContext instanceof BaseActivity) {
                ((BaseActivity) mContext).hideLoading();
            }
        }
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        if (mReference != null) {
            mReference.clear();
        }
    }

    public void cancelRequest() {
        OkGo.getInstance().cancelTag(this);
    }

}
