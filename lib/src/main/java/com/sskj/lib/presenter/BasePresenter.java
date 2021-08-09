package com.sskj.lib.presenter;

import android.app.Activity;

import com.sskj.lib.base.IBaseView;
import com.sskj.lib.base.IPresenter;

public class BasePresenter<VIEW extends IBaseView> extends IPresenter<VIEW> {

    @Override
    public void attachView(VIEW view, Activity context) {
        super.attachView(view, context);
    }
}
