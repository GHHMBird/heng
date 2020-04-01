package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/15 20:22
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RuleNameModel implements Serializable {
    @Expose
    @SerializedName("realNameStatus")
    public String realNameStatus;//实名状态
    @Expose
    @SerializedName("realName")
    public String realName;//实名
    @Expose
    @SerializedName("idCard")
    public String idCard;//身份证号
    @Expose
    @SerializedName("cardStatus")
    public String cardStatus;//银行卡绑定状态


}
