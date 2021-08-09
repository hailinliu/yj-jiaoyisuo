package com.sskj.lib.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity()
public class SelfCoinBean {
    @Nullable
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String code;

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
