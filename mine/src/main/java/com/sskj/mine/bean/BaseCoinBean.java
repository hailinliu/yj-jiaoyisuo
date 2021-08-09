package com.sskj.mine.bean;

import java.io.Serializable;

public class BaseCoinBean implements Serializable {
    private String imageUrl;
    private String simpletype;
    private String type;

    public BaseCoinBean(String imageUrl, String simpletype, String type) {
        this.imageUrl = imageUrl;
        this.simpletype = simpletype;
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSimpletype() {
        return simpletype;
    }

    public void setSimpletype(String simpletype) {
        this.simpletype = simpletype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
