package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class BankInfo implements Serializable {
    @Expose
    @SerializedName("bankName")
    public String bankName;//银行名字
    @Expose
    @SerializedName("bankImage")
    public String bankImage;//银行图标
    @Expose
    @SerializedName("bankId")
    public String bankId;//银行ID，返回
}
