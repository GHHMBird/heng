package finan.heng.com.apps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import finan.heng.com.apps.http.RetrofitClient;
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
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.rx.RxHttpHelper;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
public class HttpHelper {

    public HttpHelper(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();

        if (isWifiConn) {//WiFi链接
            Log.d("mark", "httphelper_wifi");
        }
        if (isMobileConn) {//流量链接
            Log.d("mark", "httphelper_4g");
        }
        if (!isWifiConn && !isMobileConn) {//无网络连接
            Log.d("mark", "httphelper_none");
        }
    }

    //理财页签设置
    public Observable<FinanceTitleResponse> getFinanceTitle() {
        return new RetrofitClient().getInterfaceService().getFinanceTitle().onErrorResumeNext(new Func1<Throwable, Observable<? extends FinanceTitleResponse>>() {
            @Override
            public Observable<? extends FinanceTitleResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<FinanceTitleResponse, Observable<? extends FinanceTitleResponse>>() {
            @Override
            public Observable<? extends FinanceTitleResponse> call(FinanceTitleResponse financeTitleResponse) {
                if (!financeTitleResponse.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", financeTitleResponse.errorMsg));
                } else {
                    return Observable.just(financeTitleResponse);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //产品详情
    public Observable<ProductDetailResponse> getProduct(int id) {
        return new RetrofitClient().getInterfaceService().getProduct(id).onErrorResumeNext(new Func1<Throwable, Observable<? extends ProductDetailResponse>>() {
            @Override
            public Observable<? extends ProductDetailResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ProductDetailResponse, Observable<? extends ProductDetailResponse>>() {
            @Override
            public Observable<? extends ProductDetailResponse> call(ProductDetailResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //首页信息
    public Observable<HomeResponse> getHomeScroll() {
        return new RetrofitClient().getInterfaceService().getHomeScroll().onErrorResumeNext(new Func1<Throwable, Observable<? extends HomeResponse>>() {
            @Override
            public Observable<? extends HomeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<HomeResponse, Observable<? extends HomeResponse>>() {
            @Override
            public Observable<? extends HomeResponse> call(HomeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取验证码
    public Observable<GetMessageCodeResponse> getMessageCode(String phone, String picCode) {
        return new RetrofitClient().getInterfaceService().getMessageCode(phone, picCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetMessageCodeResponse, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(GetMessageCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //注册
    public Observable<RegisterResponse> register(String phone, String password, String msgCode, String signCode) {
        return new RetrofitClient().getInterfaceService().register(phone, password, msgCode, signCode, "1").onErrorResumeNext(new Func1<Throwable, Observable<? extends RegisterResponse>>() {
            @Override
            public Observable<? extends RegisterResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RegisterResponse, Observable<? extends RegisterResponse>>() {
            @Override
            public Observable<? extends RegisterResponse> call(RegisterResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //登陆
    Observable<LoginResponse> login(String phone, String clientId, String password) {
        return new RetrofitClient().getInterfaceService().login(phone, clientId, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends LoginResponse>>() {
            @Override
            public Observable<? extends LoginResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<LoginResponse, Observable<? extends LoginResponse>>() {
            @Override
            public Observable<? extends LoginResponse> call(LoginResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //用户信息
    Observable<MyUserInfo> getUserInfo() {
        return new RetrofitClient().getInterfaceService().getUserInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends MyUserInfo>>() {
            @Override
            public Observable<? extends MyUserInfo> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<MyUserInfo, Observable<? extends MyUserInfo>>() {
            @Override
            public Observable<? extends MyUserInfo> call(MyUserInfo response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //银行列表信息
    public Observable<BankListResponse> getBankInfo() {
        return new RetrofitClient().getInterfaceService().getBankInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends BankListResponse>>() {
            @Override
            public Observable<? extends BankListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<BankListResponse, Observable<? extends BankListResponse>>() {
            @Override
            public Observable<? extends BankListResponse> call(BankListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //召回登陆密码
    public Observable<getPassWordResponse> findPassWord(String phone, String msgCode, String password) {
        return new RetrofitClient().getInterfaceService().findPassWord(phone, msgCode, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends getPassWordResponse>>() {
            @Override
            public Observable<? extends getPassWordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<getPassWordResponse, Observable<? extends getPassWordResponse>>() {
            @Override
            public Observable<? extends getPassWordResponse> call(getPassWordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取短信验证码(找回密码)
    public Observable<GetMessageCodeResponse> findPasswordMessageCode(String phone, String code) {
        return new RetrofitClient().getInterfaceService().findPasswordMessageCode(phone, code).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetMessageCodeResponse, Observable<? extends GetMessageCodeResponse>>() {
            @Override
            public Observable<? extends GetMessageCodeResponse> call(GetMessageCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取短信验证码(实名认证)
    public Observable<GetRenZhengMessageResponse> renzhengMessageCode(String yuliuphone) {
        return new RetrofitClient().getInterfaceService().renzhengMessageCode(yuliuphone).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetRenZhengMessageResponse>>() {
            @Override
            public Observable<? extends GetRenZhengMessageResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetRenZhengMessageResponse, Observable<? extends GetRenZhengMessageResponse>>() {
            @Override
            public Observable<? extends GetRenZhengMessageResponse> call(GetRenZhengMessageResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //实名认证
    public Observable<RuleNameResponse> ruleName(String name, String bankId, String idCard, String cardCode, String phone, String captcha) {
        return new RetrofitClient().getInterfaceService().ruleName(name, bankId, idCard, cardCode, phone, captcha).onErrorResumeNext(new Func1<Throwable, Observable<? extends RuleNameResponse>>() {
            @Override
            public Observable<? extends RuleNameResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<RuleNameResponse, Observable<? extends RuleNameResponse>>() {
            @Override
            public Observable<? extends RuleNameResponse> call(RuleNameResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的银行卡
    public Observable<BankCardResponse> myBankCard() {
        return new RetrofitClient().getInterfaceService().myBankCard().onErrorResumeNext(new Func1<Throwable, Observable<? extends BankCardResponse>>() {
            @Override
            public Observable<? extends BankCardResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<BankCardResponse, Observable<? extends BankCardResponse>>() {
            @Override
            public Observable<? extends BankCardResponse> call(BankCardResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //修改登录密码
    public Observable<ChangeLoginPasswordResponse> changeLoginPassword(String oldWord, String newWord) {
        return new RetrofitClient().getInterfaceService().changeLoginPassword(oldWord, newWord).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChangeLoginPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeLoginPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChangeLoginPasswordResponse, Observable<? extends ChangeLoginPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeLoginPasswordResponse> call(ChangeLoginPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //修改交易密码
    public Observable<ChangeJiaoYiPasswordResponse> changeJiaoyiPassword(String oldWord, String newWord) {
        return new RetrofitClient().getInterfaceService().changeJiaoyiPassword(oldWord, newWord).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChangeJiaoYiPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeJiaoYiPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChangeJiaoYiPasswordResponse, Observable<? extends ChangeJiaoYiPasswordResponse>>() {
            @Override
            public Observable<? extends ChangeJiaoYiPasswordResponse> call(ChangeJiaoYiPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //设置交易密码
    public Observable<SetBuyPasswordResponse> setBuyPassword(String word) {
        return new RetrofitClient().getInterfaceService().setBuyPassword(word).onErrorResumeNext(new Func1<Throwable, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SetBuyPasswordResponse, Observable<? extends SetBuyPasswordResponse>>() {
            @Override
            public Observable<? extends SetBuyPasswordResponse> call(SetBuyPasswordResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //忘记交易密码的短信验证码
    public Observable<ForgetBuyPasswordMsgCodeResponse> forgetBuyPasswordMsgCode(String idCard) {
        return new RetrofitClient().getInterfaceService().forgetBuyPasswordMsgCode(idCard).onErrorResumeNext(new Func1<Throwable, Observable<? extends ForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends ForgetBuyPasswordMsgCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ForgetBuyPasswordMsgCodeResponse, Observable<? extends ForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends ForgetBuyPasswordMsgCodeResponse> call(ForgetBuyPasswordMsgCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //验证忘记交易密码的短信验证码
    public Observable<CheckForgetBuyPasswordMsgCodeResponse> checkForgetBuyPasswordMsgCode(String msgCode) {
        return new RetrofitClient().getInterfaceService().checkForgetBuyPasswordMsgCode(msgCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends CheckForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends CheckForgetBuyPasswordMsgCodeResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<CheckForgetBuyPasswordMsgCodeResponse, Observable<? extends CheckForgetBuyPasswordMsgCodeResponse>>() {
            @Override
            public Observable<? extends CheckForgetBuyPasswordMsgCodeResponse> call(CheckForgetBuyPasswordMsgCodeResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //获取产品列表
    public Observable<GetProductListResponse> getProductList(String geneId, String pageNo) {
        return new RetrofitClient().getInterfaceService().getProductList(geneId, pageNo).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductListResponse>>() {
            @Override
            public Observable<? extends GetProductListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductListResponse, Observable<? extends GetProductListResponse>>() {
            @Override
            public Observable<? extends GetProductListResponse> call(GetProductListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //收益预估
    public Observable<GetProductDetailMoneyResponse> getProductDetailMoney(int geneId, int amount) {
        return new RetrofitClient().getInterfaceService().getProductDetailMoney(geneId, amount).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailMoneyResponse, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(GetProductDetailMoneyResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //收益预估
    public Observable<GetProductDetailMoneyResponse> getProductDetailMoney(int geneId, int amount, int couponId) {
        return new RetrofitClient().getInterfaceService().getProductDetailMoney(geneId, amount, couponId).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailMoneyResponse, Observable<? extends GetProductDetailMoneyResponse>>() {
            @Override
            public Observable<? extends GetProductDetailMoneyResponse> call(GetProductDetailMoneyResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<GetProductDetailListResponse> getProductDetailList(int geneId, String pageNo) {
        return new RetrofitClient().getInterfaceService().getProductDetailList(geneId, pageNo).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductDetailListResponse>>() {
            @Override
            public Observable<? extends GetProductDetailListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductDetailListResponse, Observable<? extends GetProductDetailListResponse>>() {
            @Override
            public Observable<? extends GetProductDetailListResponse> call(GetProductDetailListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<LogOutResponse> logout() {
        return new RetrofitClient().getInterfaceService().logout().onErrorResumeNext(new Func1<Throwable, Observable<? extends LogOutResponse>>() {
            @Override
            public Observable<? extends LogOutResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<LogOutResponse, Observable<? extends LogOutResponse>>() {
            @Override
            public Observable<? extends LogOutResponse> call(LogOutResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认购买
    public Observable<SureBuyResponse> sureBuy(int geneId) {
        return new RetrofitClient().getInterfaceService().sureBuy(geneId).onErrorResumeNext(new Func1<Throwable, Observable<? extends SureBuyResponse>>() {
            @Override
            public Observable<? extends SureBuyResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SureBuyResponse, Observable<? extends SureBuyResponse>>() {
            @Override
            public Observable<? extends SureBuyResponse> call(SureBuyResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePay(int geneId, int couId, int redId, String amount, String actualAmount, String password) {
        return new RetrofitClient().getInterfaceService().surePay(geneId, couId, redId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayNoCouple(int geneId, int redId, String amount, String actualAmount, String password) {
        return new RetrofitClient().getInterfaceService().surePayNoCoupon(geneId, redId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayNoRed(int geneId, int couId, String amount, String actualAmount, String password) {
        return new RetrofitClient().getInterfaceService().surePayNoRed(geneId, couId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认支付
    public Observable<SurePayResponse> surePayAllNo(int geneId, String amount, String actualAmount, String password) {
        return new RetrofitClient().getInterfaceService().surePayAllNo(geneId, amount, actualAmount, password).onErrorResumeNext(new Func1<Throwable, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SurePayResponse, Observable<? extends SurePayResponse>>() {
            @Override
            public Observable<? extends SurePayResponse> call(SurePayResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的钱包
    public Observable<MyWalletResponse> getMyWallet() {
        return new RetrofitClient().getInterfaceService().getMyWallet().onErrorResumeNext(new Func1<Throwable, Observable<? extends MyWalletResponse>>() {
            @Override
            public Observable<? extends MyWalletResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<MyWalletResponse, Observable<? extends MyWalletResponse>>() {
            @Override
            public Observable<? extends MyWalletResponse> call(MyWalletResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //交易流水
    public Observable<GetTradeListResponse> getTradeList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getTradeList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //充值流水
    public Observable<GetTradeListResponse> getRechargeList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getRechargeList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //提现流水
    public Observable<GetTradeListResponse> getWithDrawList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getWithDrawList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资流水
    public Observable<GetTradeListResponse> getinvestListList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getinvestListList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //回款流水
    public Observable<GetTradeListResponse> getReceiveList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getReceiveList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //奖励流水
    public Observable<GetTradeListResponse> getRewardList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getRewardList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetTradeListResponse, Observable<? extends GetTradeListResponse>>() {
            @Override
            public Observable<? extends GetTradeListResponse> call(GetTradeListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资记录
    public Observable<GetInvestHistoryListResponse> getInvestHistoryList(String pageNo, String pageSize, int orderStatus) {
        return new RetrofitClient().getInterfaceService().getInvestHistoryList(pageNo, pageSize, orderStatus).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInvestHistoryListResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInvestHistoryListResponse, Observable<? extends GetInvestHistoryListResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryListResponse> call(GetInvestHistoryListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //投资详情
    public Observable<GetInvestHistoryDetailResponse> getInvestHistoryDetail(int id) {
        return new RetrofitClient().getInterfaceService().getInvestHistoryDetail(id).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInvestHistoryDetailResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryDetailResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInvestHistoryDetailResponse, Observable<? extends GetInvestHistoryDetailResponse>>() {
            @Override
            public Observable<? extends GetInvestHistoryDetailResponse> call(GetInvestHistoryDetailResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //邀请记录(已注册)
    public Observable<GetInviteHistoryResponse> getInviteHistory(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getInviteHistory(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInviteHistoryResponse>>() {
            @Override
            public Observable<? extends GetInviteHistoryResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInviteHistoryResponse, Observable<? extends GetInviteHistoryResponse>>() {
            @Override
            public Observable<? extends GetInviteHistoryResponse> call(GetInviteHistoryResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //邀请记录(已投资)
    public Observable<GetInviteInvestListResponse> getInviteInvestList(String pageNo, String pageSize) {
        return new RetrofitClient().getInterfaceService().getInviteInvestList(pageNo, pageSize).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetInviteInvestListResponse>>() {
            @Override
            public Observable<? extends GetInviteInvestListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetInviteInvestListResponse, Observable<? extends GetInviteInvestListResponse>>() {
            @Override
            public Observable<? extends GetInviteInvestListResponse> call(GetInviteInvestListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //红包或加息券
    public Observable<GetRedPackListResponse> getRedPackList(String pageNo, String pageSize, String type, String flag) {
        return new RetrofitClient().getInterfaceService().getRedPackList(pageNo, pageSize, type, flag).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetRedPackListResponse>>() {
            @Override
            public Observable<? extends GetRedPackListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetRedPackListResponse, Observable<? extends GetRedPackListResponse>>() {
            @Override
            public Observable<? extends GetRedPackListResponse> call(GetRedPackListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //提现界面
    public Observable<GetWithdrawInitResponse> getWithdrawInit() {
        return new RetrofitClient().getInterfaceService().getWithdrawInit().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetWithdrawInitResponse>>() {
            @Override
            public Observable<? extends GetWithdrawInitResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetWithdrawInitResponse, Observable<? extends GetWithdrawInitResponse>>() {
            @Override
            public Observable<? extends GetWithdrawInitResponse> call(GetWithdrawInitResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //确认提现
    public Observable<SureWithdrawResponse> sureWithdraw(String money, String pwd) {
        return new RetrofitClient().getInterfaceService().sureWithdraw(money, pwd).onErrorResumeNext(new Func1<Throwable, Observable<? extends SureWithdrawResponse>>() {
            @Override
            public Observable<? extends SureWithdrawResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<SureWithdrawResponse, Observable<? extends SureWithdrawResponse>>() {
            @Override
            public Observable<? extends SureWithdrawResponse> call(SureWithdrawResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的消息 交易
    public Observable<GetJiaoYiListResponse> getJiaoYiList() {
        return new RetrofitClient().getInterfaceService().getJiaoYiList().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetJiaoYiListResponse>>() {
            @Override
            public Observable<? extends GetJiaoYiListResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetJiaoYiListResponse, Observable<? extends GetJiaoYiListResponse>>() {
            @Override
            public Observable<? extends GetJiaoYiListResponse> call(GetJiaoYiListResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //我的消息 公告
    public Observable<GetGongGaoResponse> getGongGao() {
        return new RetrofitClient().getInterfaceService().getGongGao().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetGongGaoResponse>>() {
            @Override
            public Observable<? extends GetGongGaoResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetGongGaoResponse, Observable<? extends GetGongGaoResponse>>() {
            @Override
            public Observable<? extends GetGongGaoResponse> call(GetGongGaoResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //支付方式取得
    public Observable<GetPayStyleResponse> getPayStyle() {
        return new RetrofitClient().getInterfaceService().getPayStyle().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetPayStyleResponse>>() {
            @Override
            public Observable<? extends GetPayStyleResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetPayStyleResponse, Observable<? extends GetPayStyleResponse>>() {
            @Override
            public Observable<? extends GetPayStyleResponse> call(GetPayStyleResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //支付
    public Observable<ChongZhiResponse> chongZhi(String amount, String paymentCode) {
        return new RetrofitClient().getInterfaceService().chongZhi(amount, paymentCode).onErrorResumeNext(new Func1<Throwable, Observable<? extends ChongZhiResponse>>() {
            @Override
            public Observable<? extends ChongZhiResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ChongZhiResponse, Observable<? extends ChongZhiResponse>>() {
            @Override
            public Observable<? extends ChongZhiResponse> call(ChongZhiResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //反馈
    public Observable<ReBackResponse> reBack(String content) {
        return new RetrofitClient().getInterfaceService().reBack(content, 0).onErrorResumeNext(new Func1<Throwable, Observable<? extends ReBackResponse>>() {
            @Override
            public Observable<? extends ReBackResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<ReBackResponse, Observable<? extends ReBackResponse>>() {
            @Override
            public Observable<? extends ReBackResponse> call(ReBackResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //反馈
    public Observable<GetProductRedPackResponse> getProductRedPack(int type) {
        return new RetrofitClient().getInterfaceService().getProductRedPack(type).onErrorResumeNext(new Func1<Throwable, Observable<? extends GetProductRedPackResponse>>() {
            @Override
            public Observable<? extends GetProductRedPackResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetProductRedPackResponse, Observable<? extends GetProductRedPackResponse>>() {
            @Override
            public Observable<? extends GetProductRedPackResponse> call(GetProductRedPackResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //公司信息
    public Observable<GetCompanyInfoResponse> getCompanyInfo() {
        return new RetrofitClient().getInterfaceService().getCompanyInfo().onErrorResumeNext(new Func1<Throwable, Observable<? extends GetCompanyInfoResponse>>() {
            @Override
            public Observable<? extends GetCompanyInfoResponse> call(Throwable throwable) {
                return Observable.error(RxHttpHelper.convertIOEError(throwable));
            }
        }).flatMap(new Func1<GetCompanyInfoResponse, Observable<? extends GetCompanyInfoResponse>>() {
            @Override
            public Observable<? extends GetCompanyInfoResponse> call(GetCompanyInfoResponse response) {
                if (!response.errorCode.equals("0")) {
                    return Observable.error(new RequestErrorThrowable("-1", response.errorMsg));
                } else {
                    return Observable.just(response);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
