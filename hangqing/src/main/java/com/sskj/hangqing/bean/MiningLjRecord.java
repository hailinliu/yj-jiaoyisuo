package com.sskj.hangqing.bean;

import java.util.List;

public class MiningLjRecord {
    private String sum;
    private List<LJRecord> data;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<LJRecord> getData() {
        return data;
    }

    public void setData(List<LJRecord> data) {
        this.data = data;
    }

    public  class LJRecord {
        private String id;
        private String userid;
        private String task;
        private String type;
        private String num;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
