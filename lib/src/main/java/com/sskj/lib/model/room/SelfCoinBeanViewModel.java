package com.sskj.lib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sskj.lib.bean.SelfCoinBean;
import com.sskj.lib.model.repository.SelfCoinRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by lv on 18-5-7.
 */

public class SelfCoinBeanViewModel extends ViewModel {

    SelfCoinRepository selfCoinRepository;

    @Inject
    public SelfCoinBeanViewModel(SelfCoinRepository selfCoinRepository) {
        this.selfCoinRepository = selfCoinRepository;
    }

    public LiveData<List<SelfCoinBean>> getUsers() {
        if (selfCoinRepository == null) {
        //   Log.e("---->", "isNull");
            return null;
        }
        return selfCoinRepository.getAllUser();
    }


    public void insert(SelfCoinBean user) {
        if (selfCoinRepository == null) {
         //   Log.e("---->", "isNull");
        }
        selfCoinRepository.insert(user);
    }
    public void delete(SelfCoinBean user) {
        if (selfCoinRepository == null) {
          //  Log.e("---->", "isNull");
        }
        selfCoinRepository.delete(user);
    }

    public void clear() {
        if (selfCoinRepository == null) {
           // Log.e("---->", "isNull");
        }
        selfCoinRepository.clear();
    }

    public void update(SelfCoinBean user) {
        if (selfCoinRepository == null) {
           // Log.e("---->", "isNull");
        }
        selfCoinRepository.update(user);
    }
}
