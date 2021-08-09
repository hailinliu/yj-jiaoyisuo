package com.sskj.lib.dagger.module;


import com.sskj.common.base.App;
import com.sskj.lib.model.room.SelfCoinDao;
import com.sskj.lib.model.room.SelfCoinDao_Impl;
import com.sskj.lib.model.room.UserDao;
import com.sskj.lib.model.room.UserDao_Impl;
import com.sskj.lib.model.room.UserDataBase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lv on 18-5-7.
 */


@Module
public class UserModule {
    @Provides
    @Named("UserDao")
    UserDao provideUserDao() {
        return new UserDao_Impl(UserDataBase.getInstance(App.INSTANCE));
    }

    @Provides
    @Named("SelfCoinDao")
    SelfCoinDao provideSelfCoinDao() {

        return new SelfCoinDao_Impl(UserDataBase.getInstance(App.INSTANCE));
    }
}
