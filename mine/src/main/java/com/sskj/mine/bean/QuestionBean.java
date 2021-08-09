package com.sskj.mine.bean;

import com.sskj.common.util.LanguageUtil;

public class QuestionBean {
    private String title;
    private String usTitle;
    private String content;
    private String usContent;
    private boolean isShow=false;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getTitle() {
        return LanguageUtil.isSimpleChinese()?title:usTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsTitle() {
        return usTitle;
    }

    public void setUsTitle(String usTitle) {
        this.usTitle = usTitle;
    }

    public String getContent() {
        return LanguageUtil.isSimpleChinese()?content:usContent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsContent() {
        return usContent;
    }

    public void setUsContent(String usContent) {
        this.usContent = usContent;
    }
}
