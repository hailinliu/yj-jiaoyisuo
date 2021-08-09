package com.sskj.lib.bean.enums;

import com.sskj.common.base.App;
import com.sskj.lib.R;

public enum FabiBuySellEnum {
    BUY(R.color.libGreen, App.INSTANCE.getString(R.string.libfabiBuySellEnum1)),
    SELL(R.color.libRed, App.INSTANCE.getString(R.string.libfabiBuySellEnum2));
    private int color;
    private String title;

    FabiBuySellEnum(int color, String title) {
        this.color = color;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        switch (this) {
            case BUY:
                return App.INSTANCE.getString(R.string.libfabiBuySellEnum1);
            case SELL:
                return App.INSTANCE.getString(R.string.libfabiBuySellEnum2);
        }
        return title;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
