package com.sskj.lib.model.repository;


import android.arch.lifecycle.LiveData;

import com.sskj.lib.bean.SelfCoinBean;
import com.sskj.lib.model.room.SelfCoinDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by lv on 18-5-7.
 */
//@Singleton
public class SelfCoinRepository {


    public SelfCoinDao selfCoinDao;

    @Inject
    public SelfCoinRepository(@Named("SelfCoinDao") SelfCoinDao SelfCoinDao) {
        this.selfCoinDao = SelfCoinDao;
    }

    public LiveData<List<SelfCoinBean >> getAllUser() {
        return selfCoinDao.getAll();
    }



    public void insert(SelfCoinBean user) {
        Schedulers.newThread().scheduleDirect(() -> selfCoinDao.insert(user));
    }
    public void delete(SelfCoinBean user) {
        Schedulers.newThread().scheduleDirect(() -> selfCoinDao.delete(user));
    }

    public void clear() {
        Schedulers.newThread().scheduleDirect(() -> selfCoinDao.deleteAll());
    }

    public void update(SelfCoinBean user) {
        Schedulers.newThread().scheduleDirect(() -> selfCoinDao.update(user));
    }
}
