package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:11
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GetPayStyleModel implements Serializable {


    /**
     * paymentCode : KQ
     * paymentName : 银行卡支付
     * paymentDesc : 快钱支付
     * paymentLogo : http://192.168.1.204:8080/landscape-adminhttp://newapi.99kangyx.com/kangyx-api/upload/img/logo/kuaiqian.png
     */

    private ArrayList<PaymentListBean> paymentList;

    public ArrayList<PaymentListBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList<PaymentListBean> paymentList) {
        this.paymentList = paymentList;
    }

    public static class PaymentListBean {
        private String paymentCode;
        private String paymentName;
        private String paymentDesc;
        private String paymentLogo;

        public String getPaymentCode() {
            return paymentCode;
        }

        public void setPaymentCode(String paymentCode) {
            this.paymentCode = paymentCode;
        }

        public String getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(String paymentName) {
            this.paymentName = paymentName;
        }

        public String getPaymentDesc() {
            return paymentDesc;
        }

        public void setPaymentDesc(String paymentDesc) {
            this.paymentDesc = paymentDesc;
        }

        public String getPaymentLogo() {
            return paymentLogo;
        }

        public void setPaymentLogo(String paymentLogo) {
            this.paymentLogo = paymentLogo;
        }
    }
}
