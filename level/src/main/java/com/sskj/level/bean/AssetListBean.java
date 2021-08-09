package com.sskj.level.bean;

import java.io.Serializable;
import java.util.List;

public class AssetListBean {
    /**
     * asset : []
     * wallone : 0.0000
     */

    public String wallone;
    public List<AseetBean> asset;
    public static class AseetBean implements Serializable {
        public String mark;
        public double usable;
        public double frost;
        public String uptime;
        public int pid;
        public String pname;
        public double cny;

    }
}
