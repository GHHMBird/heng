package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:16
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ChongZhiModel implements Serializable{
    /**
     * strHTML : <!DOCTYPE HTML><html><head><meta charset="UTF-8"></head><body><form id="myform" name="pay" action="https://jzh.fuiou.com/app/500001.action" method="post" ><input name="mchnt_cd" type="hidden" value="0002900F0041270"  /><input name="mchnt_txn_ssn" type="hidden" value="R2017052316160932564"  /><input name="login_id" type="hidden" value="15000921406"  /><input name="amt" type="hidden" value="12300"  /><input name="page_notify_url" type="hidden" value="http://api.hengll.com/payment/fuyouPayGoldSuccess"  /><input name="back_notify_url" type="hidden" value="http://api.hengll.com/payment/fyGoldPaymentBackApi"  /><input name="signature" type="hidden" value="ms5L0AzHwLqGrbkC8xt70al0o54HKUxUg3CG53TjGYnZJ+uoOueDWKCzq8H0uKnviat/QMOIIvKNGOimVC8OTTHFjx0VLcXSJDlKNHXeiMVzl7yEKvgxAbL1pQNUVUgT/aMhc6cyhVZcou3Oz5Mo4p1TCnVoYpKS50uWowFbm9o="  /></form><script type="text/javascript">function payFy(){document.forms.pay.submit();}window.load=payFy();</script></body></html>
     */

    private String strHTML;

    public String getStrHTML() {
        return strHTML;
    }

    public void setStrHTML(String strHTML) {
        this.strHTML = strHTML;
    }
}
