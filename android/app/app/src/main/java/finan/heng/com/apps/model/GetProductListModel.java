package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class GetProductListModel implements Serializable {

    /**
     * pager : {"pageSize":"10","pageNo":"1","start":"0","toUrl":"","totalPage":"1","result":"","totalRows":"10","orderBy":"0","condition":{"repaymentType":"","status":"","plstimeLimit":"","genreId":"6"},"pageCount":"1"}
     * repaymentType : -1
     * productsList : [{"id":"184","genreId":"6","title":"测试2","loanCompany":"南洋科技","productsScale":"200000.00","investBegin":"100.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-10 00:00:00.0","publishDate":"2017-05-11 00:00:00.0","beginDate":"2017-05-12 00:00:00.0","endDate":"2017-05-13 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-09 15:06:39.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-09 15:06:39.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"55","investAmount":"200000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"195","genreId":"6","title":"测满标","loanCompany":"南洋科技","productsScale":"1000000.00","investBegin":"200.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-16 00:00:00.0","publishDate":"2017-05-17 00:00:00.0","beginDate":"2017-05-18 00:00:00.0","endDate":"2017-05-19 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"50","addUser":"admin","addTime":"2017-05-16 14:11:13.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-16 14:11:13.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"48","investAmount":"1000000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"182","genreId":"6","title":"测试F","loanCompany":"南洋科技","productsScale":"100000.00","investBegin":"100.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-09 00:00:00.0","publishDate":"2017-05-10 00:00:00.0","beginDate":"2017-05-11 00:00:00.0","endDate":"2017-05-12 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"1","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-08 17:34:38.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-08 17:34:38.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"0","investAmount":"0.00","surplusAmount":"100000.00","investPercent":0,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"193","genreId":"6","title":"5.15号3","loanCompany":"南洋科技","productsScale":"500000.00","investBegin":"85.00","minProfit":"0.06000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-15 00:00:00.0","publishDate":"2017-05-16 00:00:00.0","beginDate":"2017-05-17 00:00:00.0","endDate":"2017-05-18 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"50","addUser":"admin","addTime":"2017-05-15 12:00:57.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-15 12:00:57.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"53","investAmount":"500000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"181","genreId":"6","title":"测试E","loanCompany":"南洋科技","productsScale":"1000000.00","investBegin":"100.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-09 00:00:00.0","publishDate":"2017-05-10 00:00:00.0","beginDate":"2017-05-11 00:00:00.0","endDate":"2017-05-12 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-08 17:34:34.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-08 17:34:34.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"47","investAmount":"1000000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"192","genreId":"6","title":"5.15号2","loanCompany":"南洋科技","productsScale":"1500000.00","investBegin":"182.00","minProfit":"0.09000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-15 00:00:00.0","publishDate":"2017-05-16 00:00:00.0","beginDate":"2017-05-17 00:00:00.0","endDate":"2017-05-18 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"50","addUser":"admin","addTime":"2017-05-15 12:00:54.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-15 12:00:54.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"7","investAmount":"1500000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"191","genreId":"6","title":"5.15号","loanCompany":"南洋科技","productsScale":"1000000.00","investBegin":"155.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-15 00:00:00.0","publishDate":"2017-05-16 00:00:00.0","beginDate":"2017-05-17 00:00:00.0","endDate":"2017-05-18 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"0","raiseType":"0","status":"50","addUser":"admin","addTime":"2017-05-15 11:58:36.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-15 11:58:36.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"4","investAmount":"1000000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"187","genreId":"6","title":"测试5","loanCompany":"南洋科技","productsScale":"500000.00","investBegin":"155.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"4","prdtimeLimitType":"0","prdtimeLimitValue":"2","forenoticeDate":"2017-05-10 00:00:00.0","publishDate":"2017-05-11 00:00:00.0","beginDate":"2017-05-13 00:00:00.0","endDate":"2017-05-17 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"1","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-09 16:47:14.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-09 16:47:14.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"11","investAmount":"500000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"186","genreId":"6","title":"测试4","loanCompany":"南洋科技","productsScale":"15000000.00","investBegin":"100.00","minProfit":"0.07000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-10 00:00:00.0","publishDate":"2017-05-11 00:00:00.0","beginDate":"2017-05-12 00:00:00.0","endDate":"2017-05-13 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"1","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-09 15:06:50.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-09 15:06:50.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"14","investAmount":"15000000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""},{"id":"196","genreId":"6","title":"很有才2","loanCompany":"南洋科技","productsScale":"1000000.00","investBegin":"158.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-16 00:00:00.0","publishDate":"2017-05-17 00:00:00.0","beginDate":"2017-05-18 00:00:00.0","endDate":"2017-05-19 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"1","repaymentType":"1","raiseType":"0","status":"50","addUser":"admin","addTime":"2017-05-16 14:11:17.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-16 14:11:17.0","verifyTrialRemark":"","verifyReviewUser":"","verifyReviewTime":"","verifyReviewRemark":"","sequence":"0","description":"实力雄厚 实力极佳 实力极佳","genreTitle":"流转标","genreIcon":"/upload/image/20170421132332925.png","investCount":"1","investAmount":"20000.00","surplusAmount":"980000.00","investPercent":0.02,"content":"","img":"","repaymentSource":"","usageOfLoan":""}]
     * totalRows : 10
     * status : -1
     * pageNo : 1
     * pageSize : 10
     * plstimeLimit : -1
     * totalPage : 1
     * genreId : 6
     */

    private PagerBean pager;
    private String repaymentType;
    private String totalRows;
    private String status;
    private String pageNo;
    private String pageSize;
    private String plstimeLimit;
    private String totalPage;
    private String genreId;
    private ArrayList<ProductsListBean> productsList;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPlstimeLimit() {
        return plstimeLimit;
    }

    public void setPlstimeLimit(String plstimeLimit) {
        this.plstimeLimit = plstimeLimit;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public ArrayList<ProductsListBean> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<ProductsListBean> productsList) {
        this.productsList = productsList;
    }

    public static class PagerBean {
        /**
         * pageSize : 10
         * pageNo : 1
         * start : 0
         * toUrl :
         * totalPage : 1
         * result :
         * totalRows : 10
         * orderBy : 0
         * condition : {"repaymentType":"","status":"","plstimeLimit":"","genreId":"6"}
         * pageCount : 1
         */

        private String pageSize;
        private String pageNo;
        private String start;
        private String toUrl;
        private String totalPage;
        private String result;
        private String totalRows;
        private String orderBy;
        private ConditionBean condition;
        private String pageCount;

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getToUrl() {
            return toUrl;
        }

        public void setToUrl(String toUrl) {
            this.toUrl = toUrl;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(String totalRows) {
            this.totalRows = totalRows;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public String getPageCount() {
            return pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public static class ConditionBean {
            /**
             * repaymentType :
             * status :
             * plstimeLimit :
             * genreId : 6
             */

            private String repaymentType;
            private String status;
            private String plstimeLimit;
            private String genreId;

            public String getRepaymentType() {
                return repaymentType;
            }

            public void setRepaymentType(String repaymentType) {
                this.repaymentType = repaymentType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPlstimeLimit() {
                return plstimeLimit;
            }

            public void setPlstimeLimit(String plstimeLimit) {
                this.plstimeLimit = plstimeLimit;
            }

            public String getGenreId() {
                return genreId;
            }

            public void setGenreId(String genreId) {
                this.genreId = genreId;
            }
        }
    }

    public static class ProductsListBean {
        /**
         * id : 184
         * genreId : 6
         * title : 测试2
         * loanCompany : 南洋科技
         * productsScale : 200000.00
         * investBegin : 100.00
         * minProfit : 0.08000
         * plstimeLimitType : 0
         * plstimeLimitValue : 1
         * prdtimeLimitType : 0
         * prdtimeLimitValue : 1
         * forenoticeDate : 2017-05-10 00:00:00.0
         * publishDate : 2017-05-11 00:00:00.0
         * beginDate : 2017-05-12 00:00:00.0
         * endDate : 2017-05-13 00:00:00.0
         * repaymentBeginDate :
         * repaymentEndDate :
         * repaymentCapital : 0.00
         * repaymentInterest : 0.00
         * overdueInterest : 0.00
         * repaymentDate :
         * calInterestType : 1
         * repaymentType : 0
         * raiseType : 0
         * status : 60
         * addUser : admin
         * addTime : 2017-05-09 15:06:39.0
         * verifyTrialUser : admin
         * verifyTrialTime : 2017-05-09 15:06:39.0
         * verifyTrialRemark :
         * verifyReviewUser :
         * verifyReviewTime :
         * verifyReviewRemark :
         * sequence : 0
         * description : 实力雄厚 实力极佳 实力极佳
         * genreTitle : 流转标
         * genreIcon : /upload/image/20170421132332925.png
         * investCount : 55
         * investAmount : 200000.00
         * surplusAmount : 0.00
         * investPercent : 1.0
         * content :
         * img :
         * repaymentSource :
         * usageOfLoan :
         */

        private int id;
        private String genreId;
        private String title;
        private String loanCompany;
        private String productsScale;
        private String investBegin;
        private String minProfit;
        private String plstimeLimitType;
        private String plstimeLimitValue;
        private String prdtimeLimitType;
        private String prdtimeLimitValue;
        private String forenoticeDate;
        private String publishDate;
        private String beginDate;
        private String endDate;
        private String repaymentBeginDate;
        private String repaymentEndDate;
        private String repaymentCapital;
        private String repaymentInterest;
        private String overdueInterest;
        private String repaymentDate;
        private String calInterestType;
        private String repaymentType;
        private String raiseType;
        private String status;
        private String addUser;
        private String addTime;
        private String verifyTrialUser;
        private String verifyTrialTime;
        private String verifyTrialRemark;
        private String verifyReviewUser;
        private String verifyReviewTime;
        private String verifyReviewRemark;
        private String sequence;
        private String description;
        private String genreTitle;
        private String genreIcon;
        private String investCount;
        private String investAmount;
        private String surplusAmount;
        private double investPercent;
        private String content;
        private String img;
        private String repaymentSource;
        private String usageOfLoan;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGenreId() {
            return genreId;
        }

        public void setGenreId(String genreId) {
            this.genreId = genreId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLoanCompany() {
            return loanCompany;
        }

        public void setLoanCompany(String loanCompany) {
            this.loanCompany = loanCompany;
        }

        public String getProductsScale() {
            return productsScale;
        }

        public void setProductsScale(String productsScale) {
            this.productsScale = productsScale;
        }

        public String getInvestBegin() {
            return investBegin;
        }

        public void setInvestBegin(String investBegin) {
            this.investBegin = investBegin;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getPlstimeLimitType() {
            return plstimeLimitType;
        }

        public void setPlstimeLimitType(String plstimeLimitType) {
            this.plstimeLimitType = plstimeLimitType;
        }

        public String getPlstimeLimitValue() {
            return plstimeLimitValue;
        }

        public void setPlstimeLimitValue(String plstimeLimitValue) {
            this.plstimeLimitValue = plstimeLimitValue;
        }

        public String getPrdtimeLimitType() {
            return prdtimeLimitType;
        }

        public void setPrdtimeLimitType(String prdtimeLimitType) {
            this.prdtimeLimitType = prdtimeLimitType;
        }

        public String getPrdtimeLimitValue() {
            return prdtimeLimitValue;
        }

        public void setPrdtimeLimitValue(String prdtimeLimitValue) {
            this.prdtimeLimitValue = prdtimeLimitValue;
        }

        public String getForenoticeDate() {
            return forenoticeDate;
        }

        public void setForenoticeDate(String forenoticeDate) {
            this.forenoticeDate = forenoticeDate;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getRepaymentBeginDate() {
            return repaymentBeginDate;
        }

        public void setRepaymentBeginDate(String repaymentBeginDate) {
            this.repaymentBeginDate = repaymentBeginDate;
        }

        public String getRepaymentEndDate() {
            return repaymentEndDate;
        }

        public void setRepaymentEndDate(String repaymentEndDate) {
            this.repaymentEndDate = repaymentEndDate;
        }

        public String getRepaymentCapital() {
            return repaymentCapital;
        }

        public void setRepaymentCapital(String repaymentCapital) {
            this.repaymentCapital = repaymentCapital;
        }

        public String getRepaymentInterest() {
            return repaymentInterest;
        }

        public void setRepaymentInterest(String repaymentInterest) {
            this.repaymentInterest = repaymentInterest;
        }

        public String getOverdueInterest() {
            return overdueInterest;
        }

        public void setOverdueInterest(String overdueInterest) {
            this.overdueInterest = overdueInterest;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public String getCalInterestType() {
            return calInterestType;
        }

        public void setCalInterestType(String calInterestType) {
            this.calInterestType = calInterestType;
        }

        public String getRepaymentType() {
            return repaymentType;
        }

        public void setRepaymentType(String repaymentType) {
            this.repaymentType = repaymentType;
        }

        public String getRaiseType() {
            return raiseType;
        }

        public void setRaiseType(String raiseType) {
            this.raiseType = raiseType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getVerifyTrialUser() {
            return verifyTrialUser;
        }

        public void setVerifyTrialUser(String verifyTrialUser) {
            this.verifyTrialUser = verifyTrialUser;
        }

        public String getVerifyTrialTime() {
            return verifyTrialTime;
        }

        public void setVerifyTrialTime(String verifyTrialTime) {
            this.verifyTrialTime = verifyTrialTime;
        }

        public String getVerifyTrialRemark() {
            return verifyTrialRemark;
        }

        public void setVerifyTrialRemark(String verifyTrialRemark) {
            this.verifyTrialRemark = verifyTrialRemark;
        }

        public String getVerifyReviewUser() {
            return verifyReviewUser;
        }

        public void setVerifyReviewUser(String verifyReviewUser) {
            this.verifyReviewUser = verifyReviewUser;
        }

        public String getVerifyReviewTime() {
            return verifyReviewTime;
        }

        public void setVerifyReviewTime(String verifyReviewTime) {
            this.verifyReviewTime = verifyReviewTime;
        }

        public String getVerifyReviewRemark() {
            return verifyReviewRemark;
        }

        public void setVerifyReviewRemark(String verifyReviewRemark) {
            this.verifyReviewRemark = verifyReviewRemark;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGenreTitle() {
            return genreTitle;
        }

        public void setGenreTitle(String genreTitle) {
            this.genreTitle = genreTitle;
        }

        public String getGenreIcon() {
            return genreIcon;
        }

        public void setGenreIcon(String genreIcon) {
            this.genreIcon = genreIcon;
        }

        public String getInvestCount() {
            return investCount;
        }

        public void setInvestCount(String investCount) {
            this.investCount = investCount;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getSurplusAmount() {
            return surplusAmount;
        }

        public void setSurplusAmount(String surplusAmount) {
            this.surplusAmount = surplusAmount;
        }

        public double getInvestPercent() {
            return investPercent;
        }

        public void setInvestPercent(double investPercent) {
            this.investPercent = investPercent;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRepaymentSource() {
            return repaymentSource;
        }

        public void setRepaymentSource(String repaymentSource) {
            this.repaymentSource = repaymentSource;
        }

        public String getUsageOfLoan() {
            return usageOfLoan;
        }

        public void setUsageOfLoan(String usageOfLoan) {
            this.usageOfLoan = usageOfLoan;
        }
    }
}
