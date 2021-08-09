package com.sskj.login.bean.rxbus;

import java.util.List;

public class AgreementBean {

    /**
     * data : [{"id":10,"title":"隐私条款","sysHelpClassification":4,"imgUrl":"","createTime":"2020-12-14 23:39:55","status":0,"content":"<p>隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款<\/p>","author":"admin","sort":1,"isTop":"1","locale":"zh-cn"},{"id":12,"title":"注册协议","sysHelpClassification":4,"imgUrl":"","createTime":"2020-12-14 16:55:02","status":0,"content":"<p>欢迎注册<\/p>","author":"admin","sort":2,"isTop":"1","locale":"zh-cn"},{"id":13,"title":"法律声明","sysHelpClassification":4,"imgUrl":"","createTime":"2020-12-14 17:07:06","status":0,"content":"<p>法律声明法律声明法律声明法律声明法律声明法律声明法律声明法律声明法律声明法律声明<br><\/p>","author":"admin","sort":3,"isTop":"1","locale":"zh-cn"},{"id":14,"title":"费率","sysHelpClassification":4,"imgUrl":"","createTime":"2020-12-14 17:07:44","status":0,"content":"<p>费率费率费率费率费率费率费率费率费率费率费率费率费率<br><\/p>","author":"admin","sort":4,"isTop":"1","locale":"zh-cn"}]
     * code : 0
     * errCode : 200
     * message : 成功
     * total : null
     */

    private int code;
    private int errCode;
    private String message;
    private Object total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * title : 隐私条款
         * sysHelpClassification : 4
         * imgUrl :
         * createTime : 2020-12-14 23:39:55
         * status : 0
         * content : <p>隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款隐私条款</p>
         * author : admin
         * sort : 1
         * isTop : 1
         * locale : zh-cn
         */

        private int id;
        private String title;
        private int sysHelpClassification;
        private String imgUrl;
        private String createTime;
        private int status;
        private String content;
        private String author;
        private int sort;
        private String isTop;
        private String locale;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSysHelpClassification() {
            return sysHelpClassification;
        }

        public void setSysHelpClassification(int sysHelpClassification) {
            this.sysHelpClassification = sysHelpClassification;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIsTop() {
            return isTop;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }
    }
}
