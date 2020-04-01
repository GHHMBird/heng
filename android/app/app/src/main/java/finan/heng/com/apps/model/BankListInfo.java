package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/15.
 */
public class BankListInfo implements Serializable {

     @Expose
    @SerializedName("bankList")
    public ArrayList<BankInfo> bankList;//银行ID，返回
}
