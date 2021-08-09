package com.sskj.fabi.bean;

import com.sskj.common.base.App;
import com.sskj.fabi.R;

public enum FabiOrderStatusEnum {
    待付款(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum1)),
    等待对方付款(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum2)),
    待放币(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum3)),
    已完成(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum4)),
    申诉中(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum5)),
    已取消(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum6)),
    冻结中(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum7)),
    等待对方放币(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum8)),
    对方已付款(App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum9)),
    ;

    private String title;

    FabiOrderStatusEnum(String title) {

        this.title = title;
    }


    public String getTitle() {
        switch (this) {
            case 冻结中:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum7);
            case 已取消:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum6);
            case 已完成:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum4);
            case 等待对方付款:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum2);
            case 待放币:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum3);
            case 申诉中:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum5);
            case 对方已付款:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum9);
            case 等待对方放币:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum8);
            case 待付款:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum1);
            default:
                return App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum4);
        }
    }


}
