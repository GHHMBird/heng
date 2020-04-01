package finan.heng.com.apps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 10:42
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeNotice {

    @Expose
    @SerializedName("articleId")
    public String articleId;

    @Expose
    @SerializedName("hotUrl")
    public String hotUrl;
    @Expose
    @SerializedName("synopsis")
    public String synopsis;
    @Expose
    @SerializedName("createTime")
    public String createTime;
    @Expose
    @SerializedName("articleImage")
    public String articleImage;
    @Expose
    @SerializedName("articleTitle")
    public String articleTitle;

     /*{
        "articleId": "1",
        "articleImage": "http://admin.99landscape.com/upload/image/20170413110405363.jpg",
        "articleTitle": "融行业协会和网贷之家共同主办。会议云集了各方优秀代表，就新时期中国",
        "createTime": "2017-04-18 16:50",
        "synopsis": "1111222",
        "hotUrl": "3333"
      }],
    "pageNo": "1",
    "totalPage": "1",
    "pageSize": "20"
*/
}
