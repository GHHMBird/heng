package finan.heng.com.apps.model;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/15 21:25
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SetBuyPasswordModel implements Serializable {


    /**
     * tradePassword : 123456
     */

    private String tradePassword;

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
