package com.sskj.lib.bean;

import com.sskj.common.util.LanguageUtil;

public class GonggaoBean {

    /**
     * id : 1
     * tile : 1
     * details : 1
     * timestamp : 1561470255000
     */

    private int id;
    private String tile;
    private String tileUS;
    private String details;
    private String detailsUS;
    private long timestamp;

    public String getTileUS() {
        return tileUS;
    }

    public void setTileUS(String tileUS) {
        this.tileUS = tileUS;
    }

    public String getDetailsUS() {
        return detailsUS;
    }

    public void setDetailsUS(String detailsUS) {
        this.detailsUS = detailsUS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTile() {
        return LanguageUtil.isSimpleChinese() ? tile : tileUS;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getDetails() {
        return LanguageUtil.isSimpleChinese() ? details : detailsUS;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
