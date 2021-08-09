package com.sskj.tibi.bean;

import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.TimeFormatUtil;

import java.util.Date;
import java.util.List;

public class ExchangeHistoryEntry {
    /**
     * total : 2
     * list : [{"id":"8709","swiftNo":"15610320973377ahi61xo7","stockUserId":"186","username":"kangwen","baseCode":"ETH","dealCode":"USDT","stockCode":"USDTETH","fee":20,"createTime":1561032097000,"isDeleted":false,"dealNum":100,"dealMoney":980}]
     * pageNum : 2
     * pageSize : 1
     * size : 1
     * startRow : 2
     * endRow : 2
     * pages : 2
     * prePage : 1
     * nextPage : 0
     * isFirstPage : false
     * isLastPage : true
     * hasPreviousPage : true
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1,2]
     * navigateFirstPage : 1
     * navigateLastPage : 2
     */

    private String total;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * id : 8709
         * swiftNo : 15610320973377ahi61xo7
         * stockUserId : 186
         * username : kangwen
         * baseCode : ETH
         * dealCode : USDT
         * stockCode : USDTETH
         * fee : 20
         * createTime : 1561032097000
         * isDeleted : false
         * dealNum : 100
         * dealMoney : 980
         */

        private String id;
        private String swiftNo;
        private String stockUserId;
        private String username;
        private String baseCode;
        private String dealCode;
        private String stockCode;
        private int fee;
        private long createTime;
        private boolean isDeleted;
        private String dealNum;
        private String dealMoney;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSwiftNo() {
            return swiftNo;
        }

        public void setSwiftNo(String swiftNo) {
            this.swiftNo = swiftNo;
        }

        public String getStockUserId() {
            return stockUserId;
        }

        public void setStockUserId(String stockUserId) {
            this.stockUserId = stockUserId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBaseCode() {
            return baseCode;
        }

        public void setBaseCode(String baseCode) {
            this.baseCode = baseCode;
        }

        public String getDealCode() {
            return dealCode;
        }

        public void setDealCode(String dealCode) {
            this.dealCode = dealCode;
        }

        public String getStockCode() {
            return stockCode;
        }

        public void setStockCode(String stockCode) {
            this.stockCode = stockCode;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public String getCreateTime() {
            return TimeFormatUtil.SF_FORMAT_F.format(new Date(Long.valueOf(createTime)));
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public String getDealNum() {
            return CoinUtil.keepCoinNum(baseCode,dealNum);
        }

        public void setDealNum(String dealNum) {
            this.dealNum = dealNum;
        }

        public String getDealMoney() {
            return CoinUtil.keepCoinNum(dealCode,dealMoney);
        }

        public void setDealMoney(String dealMoney) {
            this.dealMoney = dealMoney;
        }
    }
//    /**
//     * num : 20.0000
//     * exnum : 200.0000
//     * pname : KT
//     * expname : USDT
//     * memo : KT兑换USDT
//     * addtime : 1542703376
//     */
//
//    List<Entry> list;
//
//    public List<Entry> getList() {
//        return list;
//    }
//
//    public void setList(List<Entry> list) {
//        this.list = list;
//    }
//
//    public class Entry {
//        private String num;
//        private String exnum;
//        private String pname;
//        private String expname;
//        private String memo;
//        private String addtime;
//
//        public String getNum() {
//            return num;
//        }
//
//        public void setNum(String num) {
//            this.num = num;
//        }
//
//        public String getExnum() {
//            return exnum;
//        }
//
//        public void setExnum(String exnum) {
//            this.exnum = exnum;
//        }
//
//        public String getPname() {
//            return pname;
//        }
//
//        public void setPname(String pname) {
//            this.pname = pname;
//        }
//
//        public String getExpname() {
//            return expname;
//        }
//
//        public void setExpname(String expname) {
//            this.expname = expname;
//        }
//
//        public String getMemo() {
//            return memo;
//        }
//
//        public void setMemo(String memo) {
//            this.memo = memo;
//        }
//
//        public String getAddtime() {
//            return TimeFormatUtil.SF_FORMAT_E.format(new Date(Long.valueOf(addtime) * 1000));
//        }
//
//        public void setAddtime(String addtime) {
//            this.addtime = addtime;
//        }
//    }


}
