package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;
import java.util.List;

public class GetRedPackListModel implements Serializable {


    /**
     * pageSize : 10
     * pageNo : 1
     * start : 0
     * toUrl :
     * totalPage : 1
     * result :
     * totalRows : 3
     * orderBy : 0
     * condition : {"id":"","userId":"1603","title":"","type":"0","source":"","bonuses":"","prerequisite":"","serviceConditions":"","beginTime":"","endTime":"","receiveTime":"","paidTime":"","orderId":"","status":"","statusCondition":["0"],"userAccount":""}
     * pageCount : 1
     */

    private PagerBean pager;
    /**
     * pager : {"pageSize":"10","pageNo":"1","start":"0","toUrl":"","totalPage":"1","result":"","totalRows":"3","orderBy":"0","condition":{"id":"","userId":"1603","title":"","type":"0","source":"","bonuses":"","prerequisite":"","serviceConditions":"","beginTime":"","endTime":"","receiveTime":"","paidTime":"","orderId":"","status":"","statusCondition":["0"],"userAccount":""},"pageCount":"1"}
     * totalRows : 3
     * pageNo : 1
     * pageSize : 10
     * totalPage : 1
     * myredPackList : [{"bonuses":"100.000","title":"新手好礼 - 100元红包","days":"20天后过期","hbcondition":"单笔投资满5000.00元可用","endtime":"有效期至2017/04/30"},{"bonuses":"200.000","title":"新手好礼 - 200元红包","days":"20天后过期","hbcondition":"单笔投资满10000.00元可用","endtime":"有效期至2017/04/30"},{"bonuses":"500.000","title":"新手好礼 - 500元红包","days":"20天后过期","hbcondition":"单笔投资满20000.00元可用","endtime":"有效期至2017/04/30"}]
     */

    private String    totalRows;
    private String pageNo;
    private String pageSize;
    private String totalPage;
    /**
     * bonuses : 100.000
     * title : 新手好礼 - 100元红包
     * days : 20天后过期
     * hbcondition : 单笔投资满5000.00元可用
     * endtime : 有效期至2017/04/30
     */

    private List<MyredPackListBean> myredPackList;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
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

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<MyredPackListBean> getMyredPackList() {
        return myredPackList;
    }

    public void setMyredPackList(List<MyredPackListBean> myredPackList) {
        this.myredPackList = myredPackList;
    }

    public static class PagerBean {
        private String pageSize;
        private String pageNo;
        private String start;
        private String toUrl;
        private String totalPage;
        private String result;
        private String totalRows;
        private String orderBy;
        /**
         * id :
         * userId : 1603
         * title :
         * type : 0
         * source :
         * bonuses :
         * prerequisite :
         * serviceConditions :
         * beginTime :
         * endTime :
         * receiveTime :
         * paidTime :
         * orderId :
         * status :
         * statusCondition : ["0"]
         * userAccount :
         */

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
            private String       id;
            private String       userId;
            private String       title;
            private String       type;
            private String       source;
            private String       bonuses;
            private String       prerequisite;
            private String       serviceConditions;
            private String       beginTime;
            private String       endTime;
            private String       receiveTime;
            private String       paidTime;
            private String       orderId;
            private String       status;
            private String       userAccount;
            private List<String> statusCondition;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getBonuses() {
                return bonuses;
            }

            public void setBonuses(String bonuses) {
                this.bonuses = bonuses;
            }

            public String getPrerequisite() {
                return prerequisite;
            }

            public void setPrerequisite(String prerequisite) {
                this.prerequisite = prerequisite;
            }

            public String getServiceConditions() {
                return serviceConditions;
            }

            public void setServiceConditions(String serviceConditions) {
                this.serviceConditions = serviceConditions;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(String receiveTime) {
                this.receiveTime = receiveTime;
            }

            public String getPaidTime() {
                return paidTime;
            }

            public void setPaidTime(String paidTime) {
                this.paidTime = paidTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUserAccount() {
                return userAccount;
            }

            public void setUserAccount(String userAccount) {
                this.userAccount = userAccount;
            }

            public List<String> getStatusCondition() {
                return statusCondition;
            }

            public void setStatusCondition(List<String> statusCondition) {
                this.statusCondition = statusCondition;
            }
        }
    }

    public static class MyredPackListBean {
        private String bonuses;
        private String title;
        private String days;
        private String hbcondition;
        private String endtime;

        public String getBonuses() {
            return bonuses;
        }

        public void setBonuses(String bonuses) {
            this.bonuses = bonuses;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getHbcondition() {
            return hbcondition;
        }

        public void setHbcondition(String hbcondition) {
            this.hbcondition = hbcondition;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
