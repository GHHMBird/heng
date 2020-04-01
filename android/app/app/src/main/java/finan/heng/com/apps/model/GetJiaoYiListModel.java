package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/17 21:43
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GetJiaoYiListModel implements Serializable {

    @Expose
    @SerializedName("smgList")
    public ArrayList<GetJiaoYi> smgList;
}
