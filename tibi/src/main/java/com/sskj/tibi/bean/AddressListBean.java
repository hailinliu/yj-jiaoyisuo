package com.sskj.tibi.bean;

import java.util.List;

public class AddressListBean {
    String code;
    String type;
    List<AddressBean> list;

    public AddressListBean(String code,List<AddressBean> list) {
        this.code = code;
        this.type = code.equals("ETH")?"2":"1";
        this.list=list;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AddressBean> getList() {
        return list;
    }

    public void setList(List<AddressBean> list) {
        this.list = list;
    }
}
