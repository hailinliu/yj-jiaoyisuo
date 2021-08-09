package com.sskj.pay.bean;



import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.util.HideUtil;

import java.io.Serializable;

public class PayTypeBean implements Serializable {

    private boolean isAdd = false;
    private boolean isOpen = false;
    private String id;
    private String account;
    private String bank;
    private String branch;
    private String name;
    private String img;
    private PayTypeEnum payTypeEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PayTypeBean(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getAccount() {
        return account;

    }

    public void setAccount(String account) {
        this.account = account;
    }
}
