package com.sskj.hangqing.bean;


import com.sskj.common.util.LanguageUtil;
import com.sskj.lib.util.ChineseUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class GuideListBean {

    /**
     * id : 1
     * title : 1
     * content : 1
     * showImg : 1
     * createTime : null
     * type : 2
     * timestamp : 1561528952000
     */

    private String id;
    private String titleUS;
    private String titleTW;

    public String getTitleTW() {
        return titleTW;
    }

    public void setTitleTW(String titleTW) {
        this.titleTW = titleTW;
    }

    private String title;
    private String content;
    private String contentUS;
    private String contentTW;

    public String getContentTW() {
        return contentTW;
    }

    public void setContentTW(String contentTW) {
        this.contentTW = contentTW;
    }

    private String showImg;
    private String createTime;
    private int type;
    private long timestamp;

    public String getTitleUS() {
        return titleUS;
    }

    public void setTitleUS(String titleUS) {
        this.titleUS = titleUS;
    }

    public String getContentUS() {
        return contentUS;
    }

    public void setContentUS(String contentUS) {
        this.contentUS = contentUS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return ChineseUtil.isChinese() ? (LanguageUtil.isSimpleChinese()?title:titleTW) : titleUS;
        //return LanguageUtil.isSimpleChinese()?title:titleUS;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return ChineseUtil.isChinese() ? (LanguageUtil.isSimpleChinese()?content:contentTW) : contentUS;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getCreateTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
