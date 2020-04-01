package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetProductDetailMoneyModel implements Serializable {
    @Expose
    @SerializedName("profit")
    public String profit;

}
