package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

public class GetProductDetailH5Response extends ResponseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("result")
    public GetProductDetailH5Model result;

}
