package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Created by Monkey on 2017/3/6.
 */

public class HomeNoticeInfo implements Serializable {

    @Expose
    @SerializedName("articleList")
    public ArrayList<HomeNotice> articleList;

    @Expose
    @SerializedName("pageNo")
    public String pageNo;
    @Expose
    @SerializedName("totalPage")
    public String totalPage;
    @Expose
    @SerializedName("pageSize")
    public String pageSize;

}
