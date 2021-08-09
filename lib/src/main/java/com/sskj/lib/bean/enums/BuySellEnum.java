package com.sskj.lib.bean.enums;

import com.sskj.common.base.App;
import com.sskj.lib.R;

public enum BuySellEnum {

    BUY("1", App.INSTANCE.getString(R.string.libbuySellEnum21), R.color.libGreen),

    SELL("2", App.INSTANCE.getString(R.string.libbuySellEnum11), R.color.libRed);

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
        switch (this) {
            case SELL:
                return App.INSTANCE.getString(R.string.libbuySellEnum11);
            case BUY:
                return App.INSTANCE.getString(R.string.libbuySellEnum21);
            default:
                return App.INSTANCE.getString(R.string.libbuySellEnum21);
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
