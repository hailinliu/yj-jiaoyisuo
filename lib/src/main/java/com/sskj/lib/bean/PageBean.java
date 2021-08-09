package com.sskj.lib.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageBean<T>{
    @SerializedName(value = "list",alternate = {"res","data","lists","context","content"})
    List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
