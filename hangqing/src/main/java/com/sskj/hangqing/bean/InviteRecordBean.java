package com.sskj.hangqing.bean;

import java.util.List;

public class InviteRecordBean {

    /**
     * data : {"content":[{"createTime":"2021-03-25 10:52:32","username":"155****9213","level":0}],"number":1,"size":20,"totalElements":1}
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private DataBean data;
    private int code;
    private int errCode;
    private String message;
    private Object total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * content : [{"createTime":"2021-03-25 10:52:32","username":"155****9213","level":0}]
         * number : 1
         * size : 20
         * totalElements : 1
         */

        private int number;
        private int size;
        private int totalElements;
        private List<ContentBean> content;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * createTime : 2021-03-25 10:52:32
             * username : 155****9213
             * level : 0
             */

            private String createTime;
            private String username;
            private int level;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }
        }
    }
}
