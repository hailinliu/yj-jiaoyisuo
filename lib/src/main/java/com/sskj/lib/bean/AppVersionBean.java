package com.sskj.lib.bean;


import com.sskj.lib.R;

import sskj.lee.appupdatelibrary.BaseVersion;

/**
 * ProjectName：new_bfex
 * DESC: (类描述)
 * Created by 李岩 on 2018/7/6 0006
 * updateName:(修改人名称)
 * updateTime:(修改时间)
 * updateDesc:(修改内容)
 */
public class AppVersionBean extends BaseVersion {
    /**
     * id : 1
     * title : 1
     * appVersion : 1.0
     * details : 1231
     * downUrl : 123
     * appType : 0
     * isForcedUpdate : false
     * createTime : 1561470335000
     * isDeleted : false
     * timestamp : 1561470403000
     */

    private String id;
    private String title;
    private String version;
    private String details;
    private String downloadUrl;
    private int force;
    private int appType;
    private boolean isForcedUpdate;
    private long createTime;
    private boolean isDeleted;
    private long timestamp;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return remark;
    }

    @Override
    public String getUrl() {
        return downloadUrl;
    }

    @Override
    public int getNotifyIcon() {
        return R.mipmap.ic_launcher;
    }

    @Override
    public boolean isMustUp() {
        return force == 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public boolean isIsForcedUpdate() {
        return isForcedUpdate;
    }

    public void setIsForcedUpdate(boolean isForcedUpdate) {
        this.isForcedUpdate = isForcedUpdate;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
//
//
//    /**
//     * addr : http://www.icc868.com/index.php/Admin/Index/index.html
//     * version : 3.0
//     * title : 三号
//     * content : 2,123
//     * 1,媕娿呢
//     * uptime : 1528382164
//     * uptype : 1
//     */
//
//    private String downUrl;
//    private String version;
//    private String title;
//    private String details;
//    private String uptime;
//    private boolean isForcedUpdate;
//
//    @Override
//    public String getTitle() {
//        return title;
//    }
//
//    @Override
//    public String getContent() {
//        return details;
//    }
//
//    @Override
//    public String getUrl() {
//        return downUrl;
//    }
//
//    @Override
//    public boolean isMustUp() {
//        return isForcedUpdate;
//    }
//
//    @Override
//    public int getNotifyIcon() {
//        return R.mipmap.app_splash;
//    }
//
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDownUrl() {
//        return downUrl;
//    }
//
//    public void setDownUrl(String downUrl) {
//        this.downUrl = downUrl;
//    }
//
//    public String getDetails() {
//        return details;
//    }
//
//    public void setDetails(String details) {
//        this.details = details;
//    }
//
//    public String getUptime() {
//        return uptime;
//    }
//
//    public void setUptime(String uptime) {
//        this.uptime = uptime;
//    }
//
//    public boolean isForcedUpdate() {
//        return isForcedUpdate;
//    }
//
//    public void setForcedUpdate(boolean forcedUpdate) {
//        isForcedUpdate = forcedUpdate;
//    }


    @Override
    public String toString() {
        return "AppVersionBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", appVersion='" + version + '\'' +
                ", details='" + details + '\'' +
                ", downUrl='" + downloadUrl + '\'' +
                ", appType=" + appType +
                ", isForcedUpdate=" + isForcedUpdate +
                ", createTime=" + createTime +
                ", isDeleted=" + isDeleted +
                ", timestamp=" + timestamp +
                '}';
    }
}

