package com.sskj.level.bean;

import java.io.Serializable;
import java.util.List;

public class ImageBean implements Serializable {



    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 46
         * imgUrl : /file/1591341435390e884b655-7475-48da-a586-91d227e99cd9.jpg
         * imgUrlUs : null
         * createTime : 1591339690000
         * type : 1
         * timestamp : 1591341440000
         */

        private String id;
        private String imgUrl;
        private String imgUrlUs;
        private long createTime;
        private int type;
        private long timestamp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgUrlUs() {
            return imgUrlUs;
        }

        public void setImgUrlUs(String imgUrlUs) {
            this.imgUrlUs = imgUrlUs;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
}