package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class GetInvestHistoryDetailModel implements Serializable {

    /**
     * investDetailInfo : {"productId":"197","addTime":"2017-05-21","title":"合肥江先生资金周转-2017520","amount":"1000.00","minProfit":"11%","showStatus":"待利息","endTime":"2018-05-31","tzqx":"12个月","ygsy":"1110.04","hbsy":"未使用","jxqsy":"未使用"}
     */

    private InvestDetailInfoBean investDetailInfo;

    public InvestDetailInfoBean getInvestDetailInfo() {
        return investDetailInfo;
    }

    public void setInvestDetailInfo(InvestDetailInfoBean investDetailInfo) {
        this.investDetailInfo = investDetailInfo;
    }

    public static class InvestDetailInfoBean {
        /**
         * productId : 197
         * addTime : 2017-05-21
         * title : 合肥江先生资金周转-2017520
         * amount : 1000.00
         * minProfit : 11%
         * showStatus : 待利息
         * endTime : 2018-05-31
         * tzqx : 12个月
         * ygsy : 1110.04
         * hbsy : 未使用
         * jxqsy : 未使用
         */

        private String productId;
        private String addTime;
        private String title;
        private String amount;
        private String minProfit;
        private String showStatus;
        private String endTime;
        private String tzqx;
        private String ygsy;
        private String hbsy;
        private String jxqsy;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMinProfit() {
            return minProfit;
        }

        public void setMinProfit(String minProfit) {
            this.minProfit = minProfit;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTzqx() {
            return tzqx;
        }

        public void setTzqx(String tzqx) {
            this.tzqx = tzqx;
        }

        public String getYgsy() {
            return ygsy;
        }

        public void setYgsy(String ygsy) {
            this.ygsy = ygsy;
        }

        public String getHbsy() {
            return hbsy;
        }

        public void setHbsy(String hbsy) {
            this.hbsy = hbsy;
        }

        public String getJxqsy() {
            return jxqsy;
        }

        public void setJxqsy(String jxqsy) {
            this.jxqsy = jxqsy;
        }
    }
}
