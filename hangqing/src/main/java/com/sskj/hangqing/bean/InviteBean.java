package com.sskj.hangqing.bean;

public class InviteBean {

    /**
     * promotePrefix : https://www.bitflnex.pro/register/index.html?inviteCode=MU000119FR
     * indirectCount : 0
     * inviteCode : MU000119FR
     * count : 0
     */

    private String promotePrefix;
    private int indirectCount;
    private String inviteCode;
    private int count;
    private int code;
    private int errCode;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getPromotePrefix() {
        return promotePrefix;
    }

    public void setPromotePrefix(String promotePrefix) {
        this.promotePrefix = promotePrefix;
    }

    public int getIndirectCount() {
        return indirectCount;
    }

    public void setIndirectCount(int indirectCount) {
        this.indirectCount = indirectCount;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
