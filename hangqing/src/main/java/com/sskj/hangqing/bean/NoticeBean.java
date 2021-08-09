package com.sskj.hangqing.bean;

import java.util.List;

public class NoticeBean {


    /**
     * data : {"content":[{"id":9,"title":"dasasfsa","content":"<p>saffasdasdasdsad<\/p>","createTime":"2020-11-21 11:45:36","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"},{"id":8,"title":"fasfasf","content":"<p>fsafasffaf<\/p>","createTime":"2020-11-21 11:34:52","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"},{"id":7,"title":"康康康康康康康康康康康康","content":"<p>康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康<br><\/p>","createTime":"2020-11-21 11:04:03","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"}],"number":0,"size":10,"totalElements":3}
     */


        /**
         * content : [{"id":9,"title":"dasasfsa","content":"<p>saffasdasdasdsad<\/p>","createTime":"2020-11-21 11:45:36","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"},{"id":8,"title":"fasfasf","content":"<p>fsafasffaf<\/p>","createTime":"2020-11-21 11:34:52","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"},{"id":7,"title":"康康康康康康康康康康康康","content":"<p>康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康康<br><\/p>","createTime":"2020-11-21 11:04:03","isShow":true,"imgUrl":null,"sort":0,"isTop":"1","locale":"zh-cn"}]
         * number : 0
         * size : 10
         * totalElements : 3
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
             * id : 9
             * title : dasasfsa
             * content : <p>saffasdasdasdsad</p>
             * createTime : 2020-11-21 11:45:36
             * isShow : true
             * imgUrl : null
             * sort : 0
             * isTop : 1
             * locale : zh-cn
             */

            private int id;
            private String title;
            private String content;
            private String createTime;
            private boolean isShow;
            private Object imgUrl;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public Object getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(Object imgUrl) {
                this.imgUrl = imgUrl;
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

