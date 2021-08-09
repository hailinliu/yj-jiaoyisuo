package com.sskj.lib.bean;

import android.arch.persistence.room.Entity;

import javax.annotation.Nullable;

@Entity(tableName = "country", primaryKeys = {"id_country"})
public class CountryBean {
    @Nullable
    private Integer id_country;

    @Nullable
    public Integer getId_country() {
        return id_country;
    }

    public void setId_country(@Nullable Integer id_country) {
        this.id_country = id_country;
    }

    private String zhName;
    private String enName;
    private String areaCode;
    private String language;
    private String localCurrency;
    private int sort;

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
