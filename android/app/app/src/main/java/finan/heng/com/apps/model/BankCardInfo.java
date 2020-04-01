package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/15 20:53
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class BankCardInfo implements Serializable {


    /**
     * bankImg : http://admin.hengll.com/upload/image/20170524153929279.png
     * bankName : 中国农业银行
     * bankCode : 6228480039009470277
     */

    private String bankImg;
    private String bankName;
    private String bankCode;

    public String getBankImg() {
        return bankImg;
    }

    public void setBankImg(String bankImg) {
        this.bankImg = bankImg;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
