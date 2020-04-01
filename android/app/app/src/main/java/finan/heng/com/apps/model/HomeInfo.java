package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 10:50
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeInfo implements Serializable {
    @Expose
    @SerializedName("title")
    public String title;
    @Expose
    @SerializedName("loanCompany")
    public String loanCompany;
    @Expose
    @SerializedName("plstimeLimitType")
    public String plstimeLimitType;
    @Expose
    @SerializedName("minProfit")
    public String minProfit;
    @Expose
    @SerializedName("plstimeLimitValue")
    public String plstimeLimitValue;
    @Expose
    @SerializedName("investAmount")
    public String investAmount;
    @Expose
    @SerializedName("investCount")
    public String investCount;
    @Expose
    @SerializedName("status")
    public String status;
    @Expose
    @SerializedName("componyUrl")
    public String componyUrl;
    @Expose
    @SerializedName("safeUrl")
    public String safeUrl;
    @Expose
    @SerializedName("productsId")
    public int productsId;
    @Expose
    @SerializedName("hotTitle")
    public String hotTitle;
    @Expose
    @SerializedName("description")
    public String description;
    @Expose
    @SerializedName("isShow")
    public int isShow;
    @Expose
    @SerializedName("bannerList")
    public ArrayList<HomeBannner> bannerList;
    @Expose
    @SerializedName("articleList")
    public ArrayList<HomeNoticeList> articleList;
}
