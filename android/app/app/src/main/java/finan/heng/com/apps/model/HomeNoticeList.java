package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 10:55
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeNoticeList implements Serializable {

    @Expose
    @SerializedName("hotTitle")
    public String hotTitle;
    @Expose
    @SerializedName("time")
    public String time;
    @Expose
    @SerializedName("url")
    public String url;


    //    "hotTitle": "融行业协会和网贷之家共同主办。会议云集了各方优秀代表，就新时期中国",
    //            "time": "2017-04-18 16:50",
    //            "url": "3333"
}
