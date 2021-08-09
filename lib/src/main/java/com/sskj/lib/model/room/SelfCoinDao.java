package com.sskj.lib.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sskj.lib.bean.SelfCoinBean;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by lv on 18-5-7.
 */
@Dao
public interface SelfCoinDao {
    @Query("SELECT * FROM SelfCoinBean")
    LiveData<List<SelfCoinBean>> getAll();


    @Insert(onConflict = REPLACE)
    void save(SelfCoinBean selfCoinBean);


    @Update
    void update(SelfCoinBean... users);

    @Insert
    void insertAll(List<SelfCoinBean> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SelfCoinBean user);

    @Query("DELETE  FROM SelfCoinBean")
    void deleteAll();
    @Delete
    void delete(SelfCoinBean user);
}
