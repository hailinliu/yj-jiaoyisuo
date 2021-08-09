package com.sskj.lib.bean;


import com.flyco.tablayout.listener.CustomTabEntity;

public class TabItem implements CustomTabEntity {

    String title;
    int selectedIcon;
    int unSelectedIcon;

    public TabItem(String title, int unSelectedIcon, int selectedIcon) {
        this.title = title;
        this.unSelectedIcon = unSelectedIcon;
        this.selectedIcon = selectedIcon;
    }
    public TabItem(String title) {
        this.title = title;

    }
    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}