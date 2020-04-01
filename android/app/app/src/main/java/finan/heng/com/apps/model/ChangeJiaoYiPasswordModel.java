package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/15 21:07
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ChangeJiaoYiPasswordModel implements Serializable {

    @Expose
    @SerializedName("TradePassword")
    public String TradePassword;//新的交易密码
}
