package com.sskj.invite.bean;

import android.text.TextUtils;

import com.sskj.lib.util.NumberUtil;

import java.util.List;

public class TeamBean {


    private String id;
    private String username;
    private String tel;
    private String email;
    private String level;
    private String userUid;
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        if (!TextUtils.isEmpty(userUid)) {
            return userUid;
        }
        if (!TextUtils.isEmpty(tel)) {
            return tel;
        } else {
            return email;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
