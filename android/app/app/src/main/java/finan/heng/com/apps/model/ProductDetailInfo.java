package finan.heng.com.apps.model;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 8:15
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailInfo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 产品分类
     */
    private Integer genreId;

    /**
     * 产品标题
     */
    private String title;

    /**
     * 借款单位
     */
    private String loanCompany;

    /**
     * 募集规模
     */
    private String productsScale;

    /**
     * 最低投资额
     */
    private String investBegin;

    /**
     * 年化收益
     */
    private String minProfit;

    /**
     * 投资期限类型：0.天；1.月；
     */
    private Integer plstimeLimitType;

    /**
     * 投资期限
     */
    private Integer plstimeLimitValue;

    /**
     * 募集期限类型：0.天；1.月；
     */
    private Integer prdtimeLimitType;

    /**
     * 募集期限
     */
    private Integer prdtimeLimitValue;

    /**
     * 预告日期
     */
    private String forenoticeDate;

    /**
     * 发行日期
     */
    private String publishDate;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 还款开始日期
     */
    private String repaymentBeginDate;

    /**
     * 还款结束日期
     */
    private String repaymentEndDate;

    /**
     * 应还金额
     */
    private String repaymentCapital;

    /**
     * 应还利息
     */
    private String repaymentInterest;

    /**
     * 逾期利息
     */
    private String overdueInterest;

    /**
     * 实际还款日期
     */
    private String repaymentDate;

    /**
     * 起息方式：0.募集满标后计息；1.即投计息；
     */
    private Integer calInterestType;

    /**
     * 还款方式：0.一次性还本付息；1.每月付款,到期还本；2.等额本息；3.等额本金；
     */
    private Integer repaymentType;

    /**
     * 募集类型：-1.自动截标；0.可流标；1.自动满标；
     */
    private Integer raiseType;

    /**
     * 产品状态：-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
     */
    private Integer status;

    /**
     * 添加用户
     */
    private String addUser;

    /**
     * 添加时间
     */
    private String addTime;

    /**
     * 初审用户
     */
    private String verifyTrialUser;

    /**
     * 初审时间
     */
    private String verifyTrialTime;

    /**
     * 初审备注
     */
    private String verifyTrialRemark;

    /**
     * 复审用户
     */
    private String verifyReviewUser;

    /**
     * 复审时间
     */
    private String verifyReviewTime;

    /**
     * 复审备注
     */
    private String verifyReviewRemark;

    /**
     * 排序
     */
    private Integer sequence;

}
