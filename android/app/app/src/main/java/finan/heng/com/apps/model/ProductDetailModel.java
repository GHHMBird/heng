package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/11 20:42
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailModel implements Serializable {

    /**
     * products : {"id":"177","genreId":"5","title":"测试A","loanCompany":"南洋科技","productsScale":"100000.00","investBegin":"100.00","minProfit":"0.08000","plstimeLimitType":"0","plstimeLimitValue":"1","prdtimeLimitType":"0","prdtimeLimitValue":"1","forenoticeDate":"2017-05-09 00:00:00.0","publishDate":"2017-05-10 00:00:00.0","beginDate":"2017-05-10 00:00:00.0","endDate":"2017-05-11 00:00:00.0","repaymentBeginDate":"","repaymentEndDate":"","repaymentCapital":"0.00","repaymentInterest":"0.00","overdueInterest":"0.00","repaymentDate":"","calInterestType":"0","repaymentType":"1","raiseType":"0","status":"60","addUser":"admin","addTime":"2017-05-10 15:08:39.0","verifyTrialUser":"admin","verifyTrialTime":"2017-05-08 17:34:21.0","verifyTrialRemark":"","verifyReviewUser":"admin","verifyReviewTime":"2017-05-10 15:08:39.0","verifyReviewRemark":"","sequence":"0","genreTitle":"抵押标","genreIcon":"","investCount":"12","investAmount":"100000.00","surplusAmount":"0.00","investPercent":1,"content":"","img":"","repaymentSource":"","usageOfLoan":""}
     */

    private ProductsBean products;

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * id : 177
         * genreId : 5
         * title : 测试A
         * loanCompany : 南洋科技
         * productsScale : 100000.00
         * investBegin : 100.00
         * minProfit : 0.08000
         * plstimeLimitType : 0
         * plstimeLimitValue : 1
         * prdtimeLimitType : 0
         * prdtimeLimitValue : 1
         * forenoticeDate : 2017-05-09 00:00:00.0
         * publishDate : 2017-05-10 00:00:00.0
         * beginDate : 2017-05-10 00:00:00.0
         * endDate : 2017-05-11 00:00:00.0
         * repaymentBeginDate :
         * repaymentEndDate :
         * repaymentCapital : 0.00
         * repaymentInterest : 0.00
         * overdueInterest : 0.00
         * repaymentDate :
         * calInterestType : 0
         * repaymentType : 1
         * raiseType : 0
         * status : 60
         * addUser : admin
         * addTime : 2017-05-10 15:08:39.0
         * verifyTrialUser : admin
         * verifyTrialTime : 2017-05-08 17:34:21.0
         * verifyTrialRemark :
         * verifyReviewUser : admin
         * verifyReviewTime : 2017-05-10 15:08:39.0
         * verifyReviewRemark :
         * sequence : 0
         * genreTitle : 抵押标
         * genreIcon :
         * investCount : 12
         * investAmount : 100000.00
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
        private String description;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
