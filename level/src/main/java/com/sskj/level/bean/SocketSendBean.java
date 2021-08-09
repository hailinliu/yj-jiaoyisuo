package com.sskj.level.bean;

public class SocketSendBean {
    /**
     * self_select 自选数据推送 需要在list传入自选代码 如：list:['BTC/USDT','ETC/USDT']
     * vb_depth_chanKey  区块链买五 卖五 推送
     * vb_ticke_chanKey  区块链 每种商品的推送
     */
    String code;


    public SocketSendBean(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
