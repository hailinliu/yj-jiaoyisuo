package com.sskj.level.presenter;

import android.app.Activity;

import com.sskj.level.http.HttpService;
import com.sskj.lib.base.IBaseView;
import com.sskj.lib.base.IPresenter;


/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:03
 */
public class BasePresenter<VIEW extends IBaseView> extends IPresenter<VIEW> {
    protected HttpService httpService;
    @Override
    public void attachView(VIEW view, Activity context) {
        super.attachView(view, context);
        httpService=new HttpService();
    }
}
