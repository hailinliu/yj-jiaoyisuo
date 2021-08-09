package com.sskj.lib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sskj.lib.bean.SafeSettingBean;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by lv on 18-5-7.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM SafeSettingBean")
    LiveData<List<SafeSettingBean>> getAll();


    @Insert(onConflict = REPLACE)
    void save(SafeSettingBean user);


    @Update
    void update(SafeSettingBean... users);

    @Insert
    void insertAll(List<SafeSettingBean> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SafeSettingBean user);

    @Query("DELETE  FROM SafeSettingBean")
    void deleteAll();
    @Delete
    void delete(SafeSettingBean user);
}
