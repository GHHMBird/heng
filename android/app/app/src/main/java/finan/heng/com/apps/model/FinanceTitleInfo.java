package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/11.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FinanceTitleInfo implements Serializable {
    /*
      "id" : "6",
      "genreTitle" : "流转标",
      "genreContent" : "大约4.80% ~ 7.80%",
      "genreStatus" : "0",
      "genreAdduser" : "admin",
      "genreAddtime" : "2017-02-27 03:09:49.0",
      "icon" : "/upload/image/20170421132332925.png",
      "productsCount" : "5"

}
     */
    @Expose
    @SerializedName("id")
    public String id;
    @Expose
    @SerializedName("genreTitle")
    public String genreTitle;
    @Expose
    @SerializedName("genreContent")
    public String genreContent;
    @Expose
    @SerializedName("genreStatus")
    public String genreStatus;
    @Expose
    @SerializedName("genreAdduser")
    public String genreAdduser;
    @Expose
    @SerializedName("genreAddtime")
    public String genreAddtime;
    @Expose
    @SerializedName("icon")
    public String icon;
    @Expose
    @SerializedName("productsCount")
    public String productsCount;
}
