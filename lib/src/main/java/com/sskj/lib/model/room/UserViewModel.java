package com.sskj.lib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sskj.common.base.App;
import com.sskj.lib.bean.NewBaseBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.model.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by lv on 18-5-7.
 */

public class UserViewModel extends ViewModel {

    UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserViewModel() {
        this.userRepository = new UserRepository(App.INSTANCE);
    }

    public LiveData<List<SafeSettingBean>> getUsers() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
            return null;
        }
        return userRepository.getAllUser();
    }


    public SafeSettingBean getUser() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
            return null;
        }
        List<SafeSettingBean> dataList = userRepository.getAllUser().getValue();
        if (dataList != null && dataList.size() > 0) {
            return dataList.get(0);
        }
        return null;
    }

    public void insert(SafeSettingBean user) {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.insert(user);
    }

    public void clear() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.clear();
    }

    public void update(SafeSettingBean user) {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.update(user);
    }

    public void update() {
        if (userRepository == null) {
            Log.e("---->", "isNull");
        }
        userRepository.update();
    }


}
