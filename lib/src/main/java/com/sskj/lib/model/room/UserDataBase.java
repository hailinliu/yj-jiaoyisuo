package com.sskj.lib.model.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.SelfCoinBean;
import com.sskj.lib.bean.UserData;


/**
 * Created by lv on 18-5-7.
 */
@Database(entities = {SafeSettingBean.class, SelfCoinBean.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    private static UserDataBase INSTANCE;

    public abstract UserDao getUserDao();
    public abstract SelfCoinDao getSelfCoinDao();
    public static UserDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDataBase.class, "leidun_db")
                            .addMigrations()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user "
                    + " ADD COLUMN phone_num TEXT");
        }
    };

}
