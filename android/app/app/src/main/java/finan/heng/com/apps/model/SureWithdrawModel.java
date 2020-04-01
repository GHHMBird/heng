package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class SureWithdrawModel implements Serializable {


    /**
     * code : 2
     * msg : 提现金额超过账户可用余额！
     */

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
