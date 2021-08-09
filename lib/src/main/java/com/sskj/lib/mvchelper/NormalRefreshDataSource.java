package com.sskj.lib.mvchelper;

import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;

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
public abstract class NormalRefreshDataSource<T> implements IAsyncDataSource<T> {

    private CoolRefreshView smartRefreshLayout;

    public NormalRefreshDataSource(CoolRefreshView smartRefreshLayout) {
        this.smartRefreshLayout = smartRefreshLayout;
    }

    public NormalRefreshDataSource() {
    }

    @Override
    public RequestHandle refresh(ResponseSender<T> sender) throws Exception {
        DisposableSubscriber<T> subscriber = new DisposableSubscriber<T>() {
            @Override
            public void onNext(T data) {
                sender.sendData(data);
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                sender.sendError(new Exception(e));
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onComplete() {
                if (smartRefreshLayout != null) {
                    if (smartRefreshLayout.isRefreshing()) {
                        smartRefreshLayout.setRefreshing(false);

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
    public RequestHandle loadMore(ResponseSender<T> sender) throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    public abstract Flowable<T> loadData();
}
