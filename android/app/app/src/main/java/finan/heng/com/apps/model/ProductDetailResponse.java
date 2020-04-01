package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import finan.heng.com.apps.http.ResponseData;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/11 20:39
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailResponse extends ResponseData {

    private static final long serialVersionUID = -8426339414555997935L;

    @Expose
    @SerializedName("result")
    public ProductDetailModel result;

}
