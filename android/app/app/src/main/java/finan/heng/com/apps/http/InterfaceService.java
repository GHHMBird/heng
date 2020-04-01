package finan.heng.com.apps.http;

/*
 * Created by lfu on 2017/2/21.
 */

import finan.heng.com.apps.model.BankCardResponse;
import finan.heng.com.apps.model.BankListResponse;
import finan.heng.com.apps.model.ChangeJiaoYiPasswordResponse;
import finan.heng.com.apps.model.ChangeLoginPasswordResponse;
import finan.heng.com.apps.model.CheckForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.ChongZhiResponse;
import finan.heng.com.apps.model.FinanceTitleResponse;
import finan.heng.com.apps.model.ForgetBuyPasswordMsgCodeResponse;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.model.GetGongGaoResponse;
import finan.heng.com.apps.model.GetInvestHistoryDetailResponse;
import finan.heng.com.apps.model.GetInvestHistoryListResponse;
import finan.heng.com.apps.model.GetInviteHistoryResponse;
import finan.heng.com.apps.model.GetInviteInvestListResponse;
import finan.heng.com.apps.model.GetJiaoYiListResponse;
import finan.heng.com.apps.model.GetMessageCodeResponse;
import finan.heng.com.apps.model.GetPayStyleResponse;
import finan.heng.com.apps.model.GetProductDetailListResponse;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.GetProductListResponse;
import finan.heng.com.apps.model.GetProductRedPackResponse;
import finan.heng.com.apps.model.GetRedPackListResponse;
import finan.heng.com.apps.model.GetRenZhengMessageResponse;
import finan.heng.com.apps.model.GetTradeListResponse;
import finan.heng.com.apps.model.GetWithdrawInitResponse;
import finan.heng.com.apps.model.HomeNoticeResponse;
import finan.heng.com.apps.model.HomeResponse;
import finan.heng.com.apps.model.LogOutResponse;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.ProductDetailResponse;
import finan.heng.com.apps.model.ReBackResponse;
import finan.heng.com.apps.model.RegisterResponse;
import finan.heng.com.apps.model.RuleNameResponse;
import finan.heng.com.apps.model.SetBuyPasswordResponse;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.model.SureWithdrawResponse;
import finan.heng.com.apps.model.getPassWordResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface InterfaceService {

    String FINANCE_TITLE                      = "products/genreList";//理财列表的标题
    String PRODUCT_DETAIL                     = "products/details";//获取理财产品详情
    String HOME_SCROLL_TEXT_DETAIL            = "mallHotspot";//获取首页滚动消息详情
    String HOME_SCROLL_TEXT                   = "mall/home";//获取首页消息
    String MESSAGE_CODE                       = "getMobileCode";//获取验证码
    String IMAGE_CODE                         = "generateImage";//获取图片验证码
    String REGISTER                           = "sign";//注册
    String LOGIN                              = "loginCheck";//登陆
    String USER_INFO                          = "userInfo";//用户信息
    String FIND_PASSWORD_MSG_CODE             = "getReSetPwdCode";//找回登陆密码的获取验证码
    String FIND_PASSWORD                      = "reSetPwd";//找回登陆密码
    String SHIMING_MSG_CODE                   = "accountAction/sendMobileSms";//实名认证的短信验证码
    String BANK_LIST                          = "accountAction/bankList";//银行卡列表
    String RULE_NAME                          = "accountAction/realNameAuthentication";//实名认证
    String MY_BANK                            = "accountAction/myBank";//我的银行卡
    String CHANGE_LOGIN_PASSWORD              = "accountAction/updateLoginPassword";//修改登录密码
    String CHANGE_JIAOYI_PASSWORD             = "accountAction/updateTradePassword";//修改交易密码
    String SET_BUY_PASSWORD                   = "accountAction/setTradePassword";//设置交易密码
    String FORGET_BUY_PASSWORD_MSG_CODE       = "accountAction/sendResetPaySms";//忘记交易密码的短信验证码
    String CHECK_FORGET_BUY_PASSWORD_MSG_CODE = "accountAction/verifyResetPayCode";//验证忘记交易密码的短信验证码
    String PRODUCT_LIST                       = "products/productsList";//产品列表
    String PRODUCT_MONEY_ANALYSE              = "products/calculationProfit";//收益预估
    String PRODUCT_DETAI_LIST                 = "products/orderList";//获取投资详情
    String SURE_BUY                           = "products/state";//确认投资
    String SURE_PAY                           = "products/payment";//确认支付

    //投资结束调用
    String SHOW_WALLET                = "myWalletAction/showWallet";//我的钱包
    String TRADE_LIST                 = "myWalletAction/tradedetailList";//交易流水全部
    String RECHARGE_LIST              = "myWalletAction/rechargeList";//充值流水
    String WITHDRAW_LIST              = "myWalletAction/withdrawList";//提现流水
    String INVEST_LIST                = "myWalletAction/investList";//投资流水
    String RECEIVE_LIST               = "myWalletAction/receivedList";//回款流水
    String REWARD_LIST                = "myWalletAction/rewardList";//奖励流水
    String INVEST_HISTORY_LIST        = "myWalletAction/investDetailList";//投资记录查询
    String INVEST_HISTORY_DETAIL_LIST = "myWalletAction/investDetailInfo";//投资记录详情查询
    String COMPANY_INFO               = "commonParam";//公司信息


    String INVITE_HISTORY        = "accountAction/invateRegList";//邀请记录（已注册）
    String INVITE_INVEST_HISTORY = "accountAction/invateInvestList";//邀请记录（已投资）
    String RED_PACK_LIST         = "myWalletAction/myredPackList";//优惠券查询

    String WHTIDRAW_INIT      = "withdrawAction/withdrawInit";//提现页面
    String SURE_WHTIDRAW_INIT = "withdrawAction/withdrawSub";//确认提现页面
    String NEWS_JIAOYI        = "accountAction/sysAlertMessage";//我的消息 交易
    String NEWS_GONGGAO       = "hotspot/mallHotspot";//我的消息 公告

    String PAY_STYLE                = "paymentAction/findPayMentList";//支付方式取得
    String PAY                      = "paymentAction/recharge";//充值接口
    String REBACK                   = "saveFeedBack";//反馈
    String LOGOUT                   = "logout";//退出
    String QUEREN_HONGBAO_JIAXIQUAN = "bonuses/findBonusesByType";//获取奖励信息（类型：0.红包；1.加息券；2.抵扣金；）

    @POST(FINANCE_TITLE)
    Observable<FinanceTitleResponse> getFinanceTitle();//理财列表标题

    @POST(LOGOUT)
    Observable<LogOutResponse> logout();//退出

    @POST(PAY_STYLE)
    Observable<GetPayStyleResponse> getPayStyle();//支付方式取得

    @POST(NEWS_GONGGAO)
    Observable<GetGongGaoResponse> getGongGao();//我的消息 公告

    @POST(NEWS_JIAOYI)
    Observable<GetJiaoYiListResponse> getJiaoYiList();//我的消息 交易

    @POST(COMPANY_INFO)
    Observable<GetCompanyInfoResponse> getCompanyInfo();//公司信息

    @POST(SHOW_WALLET)
    Observable<MyWalletResponse> getMyWallet();//我的钱包

    @FormUrlEncoded
    @POST(PRODUCT_DETAIL)
    Observable<ProductDetailResponse> getProduct(@Field("id") int id);//获取理财产品详情

    @FormUrlEncoded
    @POST(QUEREN_HONGBAO_JIAXIQUAN)
    Observable<GetProductRedPackResponse> getProductRedPack(@Field("type") int type);//获取优惠信息

    @FormUrlEncoded
    @POST(PAY)
    Observable<ChongZhiResponse> chongZhi(@Field("amount") String amount, @Field("paymentCode") String paymentCode);//充值接口

    @FormUrlEncoded
    @POST(REBACK)
    Observable<ReBackResponse> reBack(@Field("content") String content, @Field("deviceType") int deviceType);//反馈

    @POST(HOME_SCROLL_TEXT_DETAIL)
    Observable<HomeNoticeResponse> getHomeScrollTextDetail();//获取首页滚动消息详情

    @POST(HOME_SCROLL_TEXT)
    Observable<HomeResponse> getHomeScroll();//获取首页消息

    @POST(USER_INFO)
    Observable<MyUserInfo> getUserInfo();//获取首页消息

    @POST(WHTIDRAW_INIT)
    Observable<GetWithdrawInitResponse> getWithdrawInit();//提现页面

    @POST(BANK_LIST)
    Observable<BankListResponse> getBankInfo();//获取银行列表

    @POST(MY_BANK)
    Observable<BankCardResponse> myBankCard();//获取银行卡信息

    @FormUrlEncoded
    @POST(MESSAGE_CODE)
    Observable<GetMessageCodeResponse> getMessageCode(@Field("mobile") String mobile, @Field("captcha") String captcha);//获取图形验证码

    @FormUrlEncoded
    @POST(TRADE_LIST)
    Observable<GetTradeListResponse> getTradeList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//交易流水全部

    @FormUrlEncoded
    @POST(SURE_WHTIDRAW_INIT)
    Observable<SureWithdrawResponse> sureWithdraw(@Field("amount") String amount, @Field("payPwd") String payPwd);//确认提现

    @FormUrlEncoded
    @POST(INVITE_HISTORY)
    Observable<GetInviteInvestListResponse> getInviteInvestList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//邀请记录（已投资）

    @FormUrlEncoded
    @POST(RECEIVE_LIST)
    Observable<GetTradeListResponse> getReceiveList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//回款全部

    @FormUrlEncoded
    @POST(INVITE_INVEST_HISTORY)
    Observable<GetInviteHistoryResponse> getInviteHistory(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//邀请记录

    @FormUrlEncoded
    @POST(INVEST_HISTORY_LIST)
    Observable<GetInvestHistoryListResponse> getInvestHistoryList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize, @Field("orderStatus") int orderStatus);//投资记录

    @FormUrlEncoded
    @POST(REWARD_LIST)
    Observable<GetTradeListResponse> getRewardList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//奖励全部

    @FormUrlEncoded
    @POST(INVEST_LIST)
    Observable<GetTradeListResponse> getinvestListList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//投资流水

    @FormUrlEncoded
    @POST(WITHDRAW_LIST)
    Observable<GetTradeListResponse> getWithDrawList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//交易流水全部

    @FormUrlEncoded
    @POST(RECHARGE_LIST)
    Observable<GetTradeListResponse> getRechargeList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);//充值流水全部

    @FormUrlEncoded
    @POST(PRODUCT_LIST)
    Observable<GetProductListResponse> getProductList(@Field("genreId") String genreId, @Field("pageNo") String pageNo);//获取产品列表

    @FormUrlEncoded
    @POST(PRODUCT_DETAI_LIST)
    Observable<GetProductDetailListResponse> getProductDetailList(@Field("id") int genreId, @Field("pageNo") String pageNo);//获取投资详情

    @FormUrlEncoded
    @POST(SET_BUY_PASSWORD)
    Observable<SetBuyPasswordResponse> setBuyPassword(@Field("tradePassword") String tradePassword);//设置交易密码

    @FormUrlEncoded
    @POST(PRODUCT_MONEY_ANALYSE)
    Observable<GetProductDetailMoneyResponse> getProductDetailMoney(@Field("id") int genreId, @Field("amount") int amount);//收益预估

    @FormUrlEncoded
    @POST(PRODUCT_MONEY_ANALYSE)
    Observable<GetProductDetailMoneyResponse> getProductDetailMoney(@Field("id") int genreId, @Field("amount") int amount, @Field("couponId") int couponId);//收益预估

    @FormUrlEncoded
    @POST(SURE_BUY)
    Observable<SureBuyResponse> sureBuy(@Field("id") int genreId);//收益预估

    @FormUrlEncoded
    @POST(INVEST_HISTORY_DETAIL_LIST)
    Observable<GetInvestHistoryDetailResponse> getInvestHistoryDetail(@Field("orderId") int orderId);//投资记录详情

    @FormUrlEncoded
    @POST(CHECK_FORGET_BUY_PASSWORD_MSG_CODE)
    Observable<CheckForgetBuyPasswordMsgCodeResponse> checkForgetBuyPasswordMsgCode(@Field("code") String msgCode);//验证忘记交易密码的短信验证码

    @FormUrlEncoded
    @POST(FORGET_BUY_PASSWORD_MSG_CODE)
    Observable<ForgetBuyPasswordMsgCodeResponse> forgetBuyPasswordMsgCode(@Field("idCard") String idCard);//忘记交易密码的短信验证码

    @FormUrlEncoded
    @POST(CHANGE_LOGIN_PASSWORD)
    Observable<ChangeLoginPasswordResponse> changeLoginPassword(@Field("oldPassword") String newPassword, @Field("newPassword") String oldPassword);//修改登录密码

    @FormUrlEncoded
    @POST(CHANGE_JIAOYI_PASSWORD)
    Observable<ChangeJiaoYiPasswordResponse> changeJiaoyiPassword(@Field("oldTradePassword") String oldPassword, @Field("newTradePassword") String newPassword);//修改交易密码

    @FormUrlEncoded
    @POST(FIND_PASSWORD_MSG_CODE)
    Observable<GetMessageCodeResponse> findPasswordMessageCode(@Field("mobile") String mobile, @Field("captcha") String captcha);//获取短信验证码

    @FormUrlEncoded
    @POST(SHIMING_MSG_CODE)
    Observable<GetRenZhengMessageResponse> renzhengMessageCode(@Field("mobile") String mobile);//获取实名认证短信验证码

    @FormUrlEncoded
    @POST(FIND_PASSWORD)
    Observable<getPassWordResponse> findPassWord(@Field("mobile") String mobile, @Field("mobileCode") String mobileCode, @Field("password") String password);//召回登陆密码

    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginResponse> login(@Field("userAccount") String userAccount, @Field("clientId") String clientId, @Field("passwd") String passwd);//登陆

    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegisterResponse> register(@Field("mobile") String mobile, @Field("passwd") String passwd, @Field("mobileCode") String mobileCode, @Field("signCode") String signCode, @Field("source") String source);//注册

    @FormUrlEncoded
    @POST(RED_PACK_LIST)
    Observable<GetRedPackListResponse> getRedPackList(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize, @Field("type") String type, @Field("flag") String flag);//红包或加息券

    @FormUrlEncoded
    @POST(RULE_NAME)
    Observable<RuleNameResponse> ruleName(@Field("name") String name, @Field("bankId") String bankId, @Field("idCard") String idCard, @Field("cardCode") String cardCode, @Field("phone") String phone, @Field("captcha") String captcha);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePay(@Field("productsId") int productsId, @Field("couponId") int couponId, @Field("redId") int redId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayNoRed(@Field("productsId") int productsId, @Field("couponId") int couponId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayNoCoupon(@Field("productsId") int productsId, @Field("redId") int redId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证

    @FormUrlEncoded
    @POST(SURE_PAY)
    Observable<SurePayResponse> surePayAllNo(@Field("productsId") int productsId, @Field("amount") String amount, @Field("actualAmount") String actualAmount, @Field("paypassword") String paypassword);//实名认证


}
