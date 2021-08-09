package com.sskj.lib.bean.enums;

import com.sskj.common.base.App;
import com.sskj.lib.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PayTypeEnum implements Serializable {
    WX("1", App.INSTANCE.getString(R.string.libpayTypeEnum1), R.mipmap.lib_icon_wx,"wx"),
    ALIPAY("2", App.INSTANCE.getString(R.string.libpayTypeEnum2), R.mipmap.lib_icon_alipay,"alipay"),
    BANK("3", App.INSTANCE.getString(R.string.libpayTypeEnum3), R.mipmap.lib_icon_bank,"bank");
    private static List<PayTypeEnum> list;
    String type;
    String quickPayType;
    String name;
    int res;

    PayTypeEnum(String type, String name, int res,String quickPayType) {
        this.type = type;
        this.name = name;
        this.res = res;
        this.quickPayType = quickPayType;
    }

    public String getQuickPayType() {
        return quickPayType;
    }

    public void setQuickPayType(String quickPayType) {
        this.quickPayType = quickPayType;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        if (this == WX) {
            return App.INSTANCE.getString(R.string.libpayTypeEnum1);
        } else if (this == ALIPAY) {
            return App.INSTANCE.getString(R.string.libpayTypeEnum2);
        } else {
            return App.INSTANCE.getString(R.string.libpayTypeEnum3);
        }
    }
    public static List<PayTypeEnum> list(){
        if (list ==null){
            list = Arrays.asList(PayTypeEnum.values());
        }
        return list;
    }

    public int getRes() {
        return res;
    }

    public static PayTypeEnum getByType(String type) {
        if (WX.getType().equals(type)) {
            return WX;
        }
        if (ALIPAY.getType().equals(type)) {
            return ALIPAY;
        }
        if (BANK.getType().equals(type)) {
            return BANK;
        }
        return WX;
    }
}
