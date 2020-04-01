package finan.heng.com.apps.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/17 22:12
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GetGongGaoModel implements Serializable {


    /**
     * articleList : [{"articleId":"18","articleImage":"http://192.168.1.203:8080/landscape-admin/upload/image/20170513120027495.jpg","articleTitle":"融行业协会和网贷之家共同主办。会议云集了各方优秀代表，就新时期中国","createTime":"2017-05-13 12:16","synopsis":"新时期中国","hotUrl":"http://192.168.1.203:8080/landscape-admin/website/class/compony"}]
     * pageNo : 1
     * totalPage : 1
     * pageSize : 20
     */

    private String pageNo;
    private String totalPage;
    private String pageSize;
    /**
     * articleId : 18
     * articleImage : http://192.168.1.203:8080/landscape-admin/upload/image/20170513120027495.jpg
     * articleTitle : 融行业协会和网贷之家共同主办。会议云集了各方优秀代表，就新时期中国
     * createTime : 2017-05-13 12:16
     * synopsis : 新时期中国
     * hotUrl : http://192.168.1.203:8080/landscape-admin/website/class/compony
     */

    private ArrayList<ArticleListBean> articleList;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<ArticleListBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleListBean> articleList) {
        this.articleList = articleList;
    }

    public static class ArticleListBean implements Serializable{
        private String articleId;
        private String articleImage;
        private String articleTitle;
        private String createTime;
        private String synopsis;
        private String hotUrl;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getArticleImage() {
            return articleImage;
        }

        public void setArticleImage(String articleImage) {
            this.articleImage = articleImage;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getHotUrl() {
            return hotUrl;
        }

        public void setHotUrl(String hotUrl) {
            this.hotUrl = hotUrl;
        }
    }
}
