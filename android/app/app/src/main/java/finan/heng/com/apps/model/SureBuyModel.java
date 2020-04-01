package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;
import java.util.List;

public class SureBuyModel implements Serializable {


    /**
     * userId : 1605
     * collectionCapital : 3553.00
     * availableMoney : 4996447.00
     * id : 33
     * fygoldAccount :
     * totalRewardMoney : 0.00
     * collectionInterest : 11.77
     * allMoney : 0.00
     * unavailableMoney : 0.00
     * totalInterest : 0.00
     */

    private UserAccountBean userAccount;
    /**
     * prdtimeLimitType : 0
     * genreIcon :
     * calInterestType : 0
     * sequence : 0
     * id : 194
     * usageOfLoan :
     * verifyTrialTime : 2017-05-16 15:08:21.0
     * publishDate : 2017-05-17 00:00:00.0
     * repaymentType : 0
     * investAmount : 5861.00
     * description : 实力雄厚 实力极佳 实力极佳
     * surplusAmount : 94139.00
     * forenoticeDate : 2017-05-16 00:00:00.0
     * investBegin : 100.00
     * addTime : 2017-05-16 15:08:21.0
     * overdueInterest : 0.00
     * productsScale : 100000.00
     * raiseType : 0
     * prdtimeLimitValue : 5
     * verifyTrialRemark :
     * loanCompany : 南洋科技
     * addUser : admin
     * repaymentEndDate :
     * minProfit : 0.11000
     * verifyReviewUser :
     * status : 30
     * investPercent : 0.06
     * repaymentSource :
     * repaymentInterest : 0.00
     * repaymentDate :
     * investCount : 1
     * verifyReviewRemark :
     * genreId : 5
     * content :
     * genreTitle : 抵押标
     * plstimeLimitType : 0
     * verifyTrialUser : admin
     * img :
     * verifyReviewTime :
     * repaymentBeginDate :
     * title : 南洋科技
     * plstimeLimitValue : 11
     * beginDate : 2017-05-22 00:00:00.0
     * repaymentCapital : 0.00
     * endDate : 2017-06-02 00:00:00.0
     */

    private ProductsBean    products;
    /**
     * redList : []
     * couponList : []
     * userAccount : {"userId":"1605","collectionCapital":"3553.00","availableMoney":"4996447.00","id":"33","fygoldAccount":"","totalRewardMoney":"0.00","collectionInterest":"11.77","allMoney":"0.00","unavailableMoney":"0.00","totalInterest":"0.00"}
     * products : {"prdtimeLimitType":"0","genreIcon":"","calInterestType":"0","sequence":"0","id":"194","usageOfLoan":"","verifyTrialTime":"2017-05-16 15:08:21.0","publishDate":"2017-05-17 00:00:00.0","repaymentType":"0","investAmount":"5861.00","description":"实力雄厚 实力极佳 实力极佳","surplusAmount":"94139.00","forenoticeDate":"2017-05-16 00:00:00.0","investBegin":"100.00","addTime":"2017-05-16 15:08:21.0","overdueInterest":"0.00","productsScale":"100000.00","raiseType":"0","prdtimeLimitValue":"5","verifyTrialRemark":"","loanCompany":"南洋科技","addUser":"admin","repaymentEndDate":"","minProfit":"0.11000","verifyReviewUser":"","status":"30","investPercent":0.06,"repaymentSource":"","repaymentInterest":"0.00","repaymentDate":"","investCount":"1","verifyReviewRemark":"","genreId":"5","content":"","genreTitle":"抵押标","plstimeLimitType":"0","verifyTrialUser":"admin","img":"","verifyReviewTime":"","repaymentBeginDate":"","title":"南洋科技","plstimeLimitValue":"11","beginDate":"2017-05-22 00:00:00.0","repaymentCapital":"0.00","endDate":"2017-06-02 00:00:00.0"}
     */

    private List<?>         redList;
    private List<?> couponList;

    public UserAccountBean getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountBean userAccount) {
        this.userAccount = userAccount;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public List<?> getRedList() {
        return redList;
    }

    public void setRedList(List<?> redList) {
        this.redList = redList;
    }

    public List<?> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<?> couponList) {
        this.couponList = couponList;
    }

    public static class UserAccountBean {
        private String userId;
        private String collectionCapital;
        private String availableMoney;
        private String id;
        private String fygoldAccount;
        private String totalRewardMoney;
        private String collectionInterest;
        private String allMoney;
        private String unavailableMoney;
        private String totalInterest;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCollectionCapital() {
            return collectionCapital;
        }

        public void setCollectionCapital(String collectionCapital) {
            this.collectionCapital = collectionCapital;
        }

        public String getAvailableMoney() {
            return availableMoney;
        }

        public void setAvailableMoney(String availableMoney) {
            this.availableMoney = availableMoney;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFygoldAccount() {
            return fygoldAccount;
        }

        public void setFygoldAccount(String fygoldAccount) {
            this.fygoldAccount = fygoldAccount;
        }

        public String getTotalRewardMoney() {
            return totalRewardMoney;
        }

        public void setTotalRewardMoney(String totalRewardMoney) {
            this.totalRewardMoney = totalRewardMoney;
        }

        public String getCollectionInterest() {
            return collectionInterest;
        }

        public void setCollectionInterest(String collectionInterest) {
            this.collectionInterest = collectionInterest;
        }

        public String getAllMoney() {
            return allMoney;
        }

        public void setAllMoney(String allMoney) {
            this.allMoney = allMoney;
        }

        public String getUnavailableMoney() {
            return unavailableMoney;
        }

        public void setUnavailableMoney(String unavailableMoney) {
            this.unavailableMoney = unavailableMoney;
        }

        public String getTotalInterest() {
            return totalInterest;
        }

        public void setTotalInterest(String totalInterest) {
            this.totalInterest = totalInterest;
        }
    }

    public static class ProductsBean {
        private String prdtimeLimitType;
        private String genreIcon;
        private String calInterestType;
        private String sequence;
        private String id;
        private String usageOfLoan;
        private String verifyTrialTime;
        private String publishDate;
        private String repaymentType;
        private String investAmount;
        private String description;
        private String surplusAmount;
        private String forenoticeDate;
        private String investBegin;
        private String addTime;
        private String overdueInterest;
        private String productsScale;
        private String raiseType;
        private String prdtimeLimitValue;
        private String verifyTrialRemark;
        private String loanCompany;
        private String addUser;
        private String repaymentEndDate;
        private String minProfit;
        private String verifyReviewUser;
        private String status;
        private double investPercent;
        private String repaymentSource;
        private String repaymentInterest;
        private String repaymentDate;
        private String investCount;
        private String verifyReviewRemark;
        private String genreId;
        private String content;
        private String genreTitle;
        private String plstimeLimitType;
        private String verifyTrialUser;
        private String img;
        private String verifyReviewTime;
        private String repaymentBeginDate;
        private String title;
        private String plstimeLimitValue;
        private String beginDate;
        private String repaymentCapital;
        private String endDate;

        public String getPrdtimeLimitType() {
            return prdtimeLimitType;
        }

        public void setPrdtimeLimitType(String prdtimeLimitType) {
            this.prdtimeLimitType = prdtimeLimitType;
        }

        public String getGenreIcon() {
            return genreIcon;
        }

        public void setGenreIcon(String genreIcon) {
            this.genreIcon = genreIcon;
        }

        public String getCalInterestType() {
            return calInterestType;
        }

        public void setCalInterestType(String calInterestType) {
            this.calInterestType = calInterestType;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsageOfLoan() {
            return usageOfLoan;
        }

        public void setUsageOfLoan(String usageOfLoan) {
            this.usageOfLoan = usageOfLoan;
        }

        public String getVerifyTrialTime() {
            return verifyTrialTime;
        }

        public void setVerifyTrialTime(String verifyTrialTime) {
            this.verifyTrialTime = verifyTrialTime;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getRepaymentType() {
            return repaymentType;
        }

        public void setRepaymentType(String repaymentType) {
            this.repaymentType = repaymentType;
        }

        public String getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(String investAmount) {
            this.investAmount = investAmount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSurplusAmount() {
            return surplusAmount;
        }

        public void setSurplusAmount(String surplusAmount) {
            this.surplusAmount = surplusAmount;
        }

        public String getForenoticeDate() {
            return forenoticeDate;
        }

        public void setForenoticeDate(String forenoticeDate) {
            this.forenoticeDate = forenoticeDate;
        }

        public String getInvestBegin() {
            return investBegin;
        }

        public void setInvestBegin(String investBegin) {
            this.investBegin = investBegin;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getOverdueInterest() {
            return overdueInterest;
        }

        public void setOverdueInterest(String overdueInterest) {
            this.overdueInterest = overdueInterest;
        }

        public String getProductsScale() {
            return productsScale;
        }

        public void setProductsScale(String productsScale) {
            this.productsScale = productsScale;
        }

        public String getRaiseType() {
            return raiseType;
        }

        public void setRaiseType(String raiseType) {
            this.raiseType = raiseType;
        }

        public String getPrdtimeLimitValue() {
            return prdtimeLimitValue;
        }

        public void setPrdtimeLimitValue(String prdtimeLimitValue) {
            this.prdtimeLimitValue = prdtimeLimitValue;
        }

        public String getVerifyTrialRemark() {
            return verifyTrialRemark;
        }

        public void setVerifyTrialRemark(String verifyTrialRemark) {
            this.verifyTrialRemark = verifyTrialRemark;
        }

        public String getLoanCompany() {
            return loanCompany;
        }

        public void setLoanCompany(String loanCompany) {
            this.loanCompany = loanCompany;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public String getRepaymentEndDate() {
            return repaymentEndDate;
        }

        public void setRepaymentEndDate(String repaymentEndDate) {
            this.repaymentEndDate = repaymentEndDate;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getVerifyReviewUser() {
            return verifyReviewUser;
        }

        public void setVerifyReviewUser(String verifyReviewUser) {
            this.verifyReviewUser = verifyReviewUser;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getInvestPercent() {
            return investPercent;
        }

        public void setInvestPercent(double investPercent) {
            this.investPercent = investPercent;
        }

        public String getRepaymentSource() {
            return repaymentSource;
        }

        public void setRepaymentSource(String repaymentSource) {
            this.repaymentSource = repaymentSource;
        }

        public String getRepaymentInterest() {
            return repaymentInterest;
        }

        public void setRepaymentInterest(String repaymentInterest) {
            this.repaymentInterest = repaymentInterest;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public String getInvestCount() {
            return investCount;
        }

        public void setInvestCount(String investCount) {
            this.investCount = investCount;
        }

        public String getVerifyReviewRemark() {
            return verifyReviewRemark;
        }

        public void setVerifyReviewRemark(String verifyReviewRemark) {
            this.verifyReviewRemark = verifyReviewRemark;
        }

        public String getGenreId() {
            return genreId;
        }

        public void setGenreId(String genreId) {
            this.genreId = genreId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGenreTitle() {
            return genreTitle;
        }

        public void setGenreTitle(String genreTitle) {
            this.genreTitle = genreTitle;
        }

        public String getPlstimeLimitType() {
            return plstimeLimitType;
        }

        public void setPlstimeLimitType(String plstimeLimitType) {
            this.plstimeLimitType = plstimeLimitType;
        }

        public String getVerifyTrialUser() {
            return verifyTrialUser;
        }

        public void setVerifyTrialUser(String verifyTrialUser) {
            this.verifyTrialUser = verifyTrialUser;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVerifyReviewTime() {
            return verifyReviewTime;
        }

        public void setVerifyReviewTime(String verifyReviewTime) {
            this.verifyReviewTime = verifyReviewTime;
        }

        public String getRepaymentBeginDate() {
            return repaymentBeginDate;
        }

        public void setRepaymentBeginDate(String repaymentBeginDate) {
            this.repaymentBeginDate = repaymentBeginDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlstimeLimitValue() {
            return plstimeLimitValue;
        }

        public void setPlstimeLimitValue(String plstimeLimitValue) {
            this.plstimeLimitValue = plstimeLimitValue;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getRepaymentCapital() {
            return repaymentCapital;
        }

        public void setRepaymentCapital(String repaymentCapital) {
            this.repaymentCapital = repaymentCapital;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
