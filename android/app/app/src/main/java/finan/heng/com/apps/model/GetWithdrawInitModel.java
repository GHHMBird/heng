package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class GetWithdrawInitModel implements Serializable {


    /**
     * txyhImg : http://192.168.1.204:8080/landscape-admin/upload/image/20161024135636718.png
     * txyhkName : 中国工商银行
     * txye : 4907000.00
     * txyhkCode : 7890
     */

    private String txyhImg;
    private String txyhkName;
    private String txye;
    private String txwxts;
    private String txyhkCode;
    private String czwxts;

    public String getCzwxts() {
        return czwxts;
    }

    public void setCzwxts(String czwxts) {
        this.czwxts = czwxts;
    }

    public String getTxwxts() {
        return txwxts;
    }

    public void setTxwxts(String txwxts) {
        this.txwxts = txwxts;
    }

    public String getTxyhImg() {
        return txyhImg;
    }

    public void setTxyhImg(String txyhImg) {
        this.txyhImg = txyhImg;
    }

    public String getTxyhkName() {
        return txyhkName;
    }

    public void setTxyhkName(String txyhkName) {
        this.txyhkName = txyhkName;
    }

    public String getTxye() {
        return txye;
    }

    public void setTxye(String txye) {
        this.txye = txye;
    }

    public String getTxyhkCode() {
        return txyhkCode;
    }

    public void setTxyhkCode(String txyhkCode) {
        this.txyhkCode = txyhkCode;
    }
}
