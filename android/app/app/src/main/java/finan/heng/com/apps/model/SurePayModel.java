package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import java.io.Serializable;

public class SurePayModel implements Serializable {


    /**
     * paidTime : 2017-05-20 20:53:32
     * orderId : 201705202053321852
     */

    private String paidTime;
    private String orderId;
    // 投资金额
    private String amount;
    // 投资期限
    private String plstimeLimitValue;
    // 投资期限类型
    private String prdtimeLimitType;
    /// 预估总收益
    private String profit;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPlstimeLimitValue() {
        return plstimeLimitValue;
    }

    public void setPlstimeLimitValue(String plstimeLimitValue) {
        this.plstimeLimitValue = plstimeLimitValue;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPrdtimeLimitType() {
        return prdtimeLimitType;
    }

    public void setPrdtimeLimitType(String prdtimeLimitType) {
        this.prdtimeLimitType = prdtimeLimitType;
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
}
