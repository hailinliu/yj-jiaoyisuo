package com.sskj.level.bean;

import com.sskj.common.base.App;
import com.sskj.level.R;

public enum BuySellEnum {

    BUY("2", App.INSTANCE.getString(R.string.levelbuySellEnum1), R.color.levelGreen),

    SELL("1",App.INSTANCE.getString(R.string.levelbuySellEnum2),R.color.levelRed);

    private String type;
    private String title;
    private int colorRes;

    BuySellEnum(String type, String title, int colorRes) {
        this.type = type;
        this.title = title;
        this.colorRes = colorRes;
    }

    BuySellEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        if (type.equals(BUY.getType())){
            return  App.INSTANCE.getString(R.string.levelbuySellEnum1);
        }else {
            return  App.INSTANCE.getString(R.string.levelbuySellEnum2);
        }
    }
    public static BuySellEnum getByType(String type){
        if (type==null){
            return BUY;
        }
        if (type.equals(BUY.getType())){
            return BUY;
        }else if (type.equals(SELL.getType())){
            return SELL;
        }
        return BUY;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }
}
