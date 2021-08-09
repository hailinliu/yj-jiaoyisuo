package com.sskj.lib.mvchelper;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by lvzhihao on 17-3-30.
 */

public class ModelRx2DataSource<T> extends Rx2DataSource<List<T>> {
    private int pageSize=20;
    private boolean isMore = false;
    private int mPage = 1;

    private OnLoadSource onLoadSource;
    public ModelRx2DataSource(OnLoadSource onLoadSource,int pageSize) {
        this.pageSize=pageSize;
        this.onLoadSource=onLoadSource;
    }
    public ModelRx2DataSource(OnLoadSource onLoadSource) {
        this.onLoadSource=onLoadSource;
    }

    public interface OnLoadSource<T>{
        Flowable<List<T>> loadSource(final int page);
    }
    private Flowable load(final int page) throws Exception {

        return onLoadSource.loadSource(page)
                .flatMap((Function<List<T>, Flowable<List<T>>>) s -> {
                    isMore = s != null && s.size() >= pageSize;
                   return Flowable.just(s);
                });
    }

    @Override
    public boolean hasMore() {
        return isMore;
    }

    @Override
    public Flowable<List<T>> refreshRX() throws Exception {
        mPage = 1;
        return load(mPage);
    }

    @Override
    public Flowable<List<T>> loadMoreRX() throws Exception {
        return load(++mPage);
    }

}