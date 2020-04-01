package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

public class MyWalletResponse extends ResponseData implements Serializable {

    private static final long serialVersionUID = -8426339414555997935L;

    @Expose
    @SerializedName("result")
    public MyWalletModel result;

}
