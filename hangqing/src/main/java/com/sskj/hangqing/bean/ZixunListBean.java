package com.sskj.hangqing.bean;

import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;

public class ZixunListBean {


    /**
     * id : 1
     * bmDate : 1561910400000
     * bmTitle : 为什么比特币近期涨幅中近一半发生在周末 ？
     * picAddr : https://img.jinse.com/2011522_small.png
     * pageViews : 11649
     * username : 比推BitpushNews
     * abstracts : 据彭博社报道，市值最高的加密货币比特币每周7天、每天24小时开放交易，但其价格在周末往往会飙升。来自彭博社的数据显示，自5月初以来比特币在周末的涨幅占到总涨幅的40%以上。
     * context : https://m.jinse.com/blockchain/401473.html
     * content : <div
     * issueTime : 1561951082000
     * timestamp : 1561952723000
     */

    private int id;
    private long bmDate;
    private String bmTitle;
    private String picAddr;
    private String pageViews;
    private String username;
    private String abstracts;
    private String context;
    private String content;
    private long issueTime;
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBmDate() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(bmDate)));
    }

    public void setBmDate(long bmDate) {
        this.bmDate = bmDate;
    }

    public String getBmTitle() {
        return bmTitle;
    }

    public void setBmTitle(String bmTitle) {
        this.bmTitle = bmTitle;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr;
    }

    public String getPageViews() {
        return pageViews;
    }

    public void setPageViews(String pageViews) {
        this.pageViews = pageViews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssueTime() {
        return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(issueTime)));
    }

    public void setIssueTime(long issueTime) {
        this.issueTime = issueTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
