package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class GetTradeListModel implements Serializable{


    /**
     * pageNo : 1
     * pageSize : 10
     * totalPage : 1
     * tradeDetailList : [{"title":"购买支付","time":"2017/05/20 20:04:47","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 19:51:44","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 19:49:45","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 19:48:18","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 19:45:42","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 19:45:07","amount":"-10000"},{"title":"购买支付","time":"2017/05/20 17:43:28","amount":"-1000"}]
     */

    private String pageNo;
    private String pageSize;
    private String totalPage;
    /**
     * title : 购买支付
     * time : 2017/05/20 20:04:47
     * amount : -10000
     */

    private ArrayList<TradeDetailListBean> tradeDetailList;

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

    public ArrayList<TradeDetailListBean> getTradeDetailList() {
        return tradeDetailList;
    }

    public void setTradeDetailList(ArrayList<TradeDetailListBean> tradeDetailList) {
        this.tradeDetailList = tradeDetailList;
    }

    public static class TradeDetailListBean {
        private String title;
        private String time;
        private String amount;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
