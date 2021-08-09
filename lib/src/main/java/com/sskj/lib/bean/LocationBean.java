package com.sskj.lib.bean;

import android.arch.persistence.room.Entity;

@Entity(tableName = "location", primaryKeys = {"id_location"})
public class LocationBean {

    private Integer id_location;
    private String country;
    private String province;
    private String city;
    private String district;
    public Integer getId_location() {
        return id_location;
    }

    public void setId_location(Integer id_location) {
        this.id_location = id_location;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
