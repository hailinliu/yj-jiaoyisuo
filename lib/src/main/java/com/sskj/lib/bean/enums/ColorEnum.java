package com.sskj.lib.bean.enums;

import com.sskj.lib.R;

public enum ColorEnum {
    UP(R.color.libGreen), DOWN(R.color.libRed);
    private int color;

    ColorEnum(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
