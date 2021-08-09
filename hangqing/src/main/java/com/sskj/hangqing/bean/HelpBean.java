package com.sskj.hangqing.bean;

import com.sskj.common.util.LanguageUtil;

public class HelpBean {

    /**
     * id : 10
     * content : <p>法币交易</p>
     * contentUs : <p>sahasdf</p>
     * updateTime : 0
     * type : 10
     * timestamp : 1561691440000
     */

    private String id;
    private String content;
    private String contentUs;
    private String updateTime;
    private int type;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return LanguageUtil.isSimpleChinese()?content:contentUs;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentUs() {
        return contentUs;
    }

    public void setContentUs(String contentUs) {
        this.contentUs = contentUs;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
