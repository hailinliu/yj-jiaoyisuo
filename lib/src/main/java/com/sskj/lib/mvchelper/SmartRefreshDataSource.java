package com.sskj.lib.mvchelper;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-07-27 08:27
 */
public abstract class SmartRefreshDataSource<T> implements IAsyncDataSource<List<T>> {

    private SmartRefreshLayout smartRefreshLayout;

    public SmartRefreshDataSource(SmartRefreshLayout smartRefreshLayout) {
        this.smartRefreshLayout = smartRefreshLayout;
    }

    public SmartRefreshDataSource() {
    }

    @Override
    public RequestHandle refresh(ResponseSender<List<T>> sender) throws Exception {
        DisposableSubscriber<List<T>> subscriber = new DisposableSubscriber<List<T>>() {
            @Override
            public void onNext(List<T> data) {
                sender.sendData(data);
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.finishRefresh();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                sender.sendError(new Exception(e));
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.finishRefresh();


                    }
                }
            }

            @Override
            public void onComplete() {
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.finishRefresh();
                    }
                }
            }
        };
        loadData().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return new RequestHandle() {
            @Override
            public void cancle() {
                if (!subscriber.isDisposed()) {
                    subscriber.dispose();
                }
            }

            @Override
            public boolean isRunning() {
                return !subscriber.isDisposed();
            }
        };
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<T>> sender) throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    public abstract Flowable<List<T>> loadData();
}
