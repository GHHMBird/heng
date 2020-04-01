package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import finan.heng.com.apps.http.ResponseData;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/15 20:21
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RuleNameResponse extends ResponseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose
    @SerializedName("result")
    public RuleNameModel result;

}
