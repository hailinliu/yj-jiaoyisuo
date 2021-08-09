package com.sskj.tibi.bean;

public class AddressBean {

    /**
     * id : 3
     * uid : 10
     * toAddr : 0xebe67f6e558ac3a25e48f2f96b8eccb09ed0a47b
     * remark : ok
     * type : 2
     * created_at : 2019-07-24 17:12:12
     * updated_at : 2019-07-24 17:12:12
     */

    private int id;

    private String code;
    private String toAddr;
    private String remark;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
