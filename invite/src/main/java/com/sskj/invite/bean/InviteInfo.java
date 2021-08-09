package com.sskj.invite.bean;

public class InviteInfo {
    private String all_money;//  直推人数
    private String teamCount;//   团队人数;
    private String url;
    private String qrc;
    private String account;
    private String tgno;
    /**
     * downUrl : http://127.0.0.1:80/apk/bi.exe
     * invitationCode : RQDHA190
     */

    private String downUrl;
    private String invitationCode;

    public String getAll_money() {
        return all_money;
    }

    public void setAll_money(String all_money) {
        this.all_money = all_money;
    }

    public String getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(String teamCount) {
        this.teamCount = teamCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrc() {
        return qrc;
    }

    public void setQrc(String qrc) {
        this.qrc = qrc;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTgno() {
        return tgno;
    }

    public void setTgno(String tgno) {
        this.tgno = tgno;
    }


    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
