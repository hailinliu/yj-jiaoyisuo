package com.sskj.hangqing.bean;

public class MiningQPBean {
    private String id;
    private String usdt_num;
    private String gtc_num;
    private String usdt;
    private String gct;
    private String userid;
    private String nums;
    private String type;
    private String time;
    private boolean usdtOrGct;  // 先试试usdt 或者GCT (true -->USDT)
    private int status;
    private String force;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MiningQPBean(MiningQPBean miningQPBean) {
        this.id = miningQPBean.getId();
        this.usdt_num = miningQPBean.getUsdt_num();
        this.gtc_num = miningQPBean.getGtc_num();
        this.usdt = miningQPBean.getUsdt();
        this.gct = miningQPBean.getGct();
        this.userid = miningQPBean.getUserid();
        this.nums = miningQPBean.getNums();
        this.type = miningQPBean.getType();
        this.time = miningQPBean.getTime();
        this.usdtOrGct = miningQPBean.isUsdtOrGct();
        this.status = miningQPBean.getStatus();
    }

    public boolean isUsdtOrGct() {
        return usdtOrGct;
    }

    public void setUsdtOrGct(boolean usdtOrGct) {
        this.usdtOrGct = usdtOrGct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsdt_num() {
        return usdt_num;
    }

    public void setUsdt_num(String usdt_num) {
        this.usdt_num = usdt_num;
    }

    public String getGtc_num() {
        return gtc_num;
    }

    public void setGtc_num(String gtc_num) {
        this.gtc_num = gtc_num;
    }

    public String getUsdt() {
        return usdt;
    }

    public void setUsdt(String usdt) {
        this.usdt = usdt;
    }

    public String getGct() {
        return gct;
    }

    public void setGct(String gct) {
        this.gct = gct;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }
}
