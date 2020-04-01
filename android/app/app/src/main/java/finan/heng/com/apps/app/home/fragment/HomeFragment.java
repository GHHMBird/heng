package finan.heng.com.apps.app.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.transformer.DefaultTransformer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.finance.activity.ProductDetailActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.HomeBannner;
import finan.heng.com.apps.model.HomeInfo;
import finan.heng.com.apps.model.HomeNoticeList;
import finan.heng.com.apps.model.HomeResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.GlideImageLoader;
import finan.heng.com.apps.utils.ScollTextView;
import rx.functions.Action1;

/*
 * Created by hhm on 2017/4/22.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Banner banner;
//    private Button mBt_touzi;
    private ScollTextView mScollTextView;
    private RelativeLayout rlPro;
    private LinearLayout mJianjie;
    private String mComponyUrl;
    private String status;
    private String mSafeUrl;
    private ArrayList<HomeBannner> mBannerList = new ArrayList<>();
    private int id;
    private LinearLayout mSafe;
    private LinearLayout mLlScroll;
    private ArrayList<HomeNoticeList> mArticleList = new ArrayList<>();
    private TextView  mProductRate, mProductTime, mProductNumber;
    private View hhmTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        initCacheData();
        initUpData();
    }

    private void initCacheData() {
        HomeResponse cacheData = DataCache.instance.getCacheData("heng", "HomeResponse");
        if (cacheData != null) {
            HomeInfo result = cacheData.result;
            mComponyUrl = result.componyUrl;
            mSafeUrl = result.safeUrl;
            mArticleList = result.articleList;
            mBannerList = result.bannerList;
            setData(cacheData);
        }
    }

    private void initView(View view) {
        banner = view.findViewById(R.id.banner);
        mLlScroll = view.findViewById(R.id.fragment_home_scroll);
        mJianjie = view.findViewById(R.id.fragment_home_jianjie);
//        mBt_touzi = (Button) view.findViewById(R.id.bt_touzi);
        rlPro = view.findViewById(R.id.home_fragment_pro);
        rlPro.setOnClickListener(this);
        mSafe = view.findViewById(R.id.fragment_home_safe);
        mScollTextView = view.findViewById(R.id.fragment_home_scolltextview);
//        mTvProductTitle = (TextView) view.findViewById(R.id.tv_title);
        mProductRate = view.findViewById(R.id.tv_percent);
        mProductTime = view.findViewById(R.id.tv_title12);
        hhmTextView = view.findViewById(R.id.tv_hhm_textview);
        mProductNumber = view.findViewById(R.id.tv__number);
        view.findViewById(R.id.fragment_home_right_arrow).setOnClickListener(this);
        view.findViewById(R.id.tongzhi).setOnClickListener(this);
        initClick();
    }

    private void initClick() {
//        mBt_touzi.setOnClickListener(this);
        mJianjie.setOnClickListener(this);
        mSafe.setOnClickListener(this);
        mLlScroll.setOnClickListener(this);
    }

    public void initUpData() {
        new HttpHelper(getActivity()).getHomeScroll().subscribe(new Action1<HomeResponse>() {
            @Override
            public void call(HomeResponse homeResponse) {
                HomeInfo result = homeResponse.result;
                mComponyUrl = result.componyUrl;
                mSafeUrl = result.safeUrl;
                mArticleList = result.articleList;
                mBannerList = result.bannerList;
                if (mComponyUrl != null && mSafeUrl != null && mArticleList != null && mBannerList != null) {
                    DataCache.instance.saveCacheData("heng", "HomeResponse", homeResponse);
                }
                setData(homeResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (getActivity() != null) {
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                }
            }
        });
    }

    private void setData(HomeResponse homeResponse) {



        if (mArticleList != null) {
            ArrayList<String> objects = new ArrayList<>();
            for (int i = 0; i < mArticleList.size(); i++) {
                objects.add(mArticleList.get(i).hotTitle);
            }
            mScollTextView.setData(objects);
            mLlScroll.setVisibility(View.VISIBLE);
        } else {
            mLlScroll.setVisibility(View.GONE);
        }
        setBannerData();
        int isShow = homeResponse.result.isShow;
        if (isShow == 1) {
            rlPro.setVisibility(View.VISIBLE);
            id = homeResponse.result.productsId;
            status = homeResponse.result.status;
            if (status.equals("20")) {
//                mBt_touzi.setText("预告中");
//                mBt_touzi.setBackgroundResource(R.drawable.btn_unclick);
            } else if (status.equals("30")) {
//                mBt_touzi.setText("立即投资");
//                mBt_touzi.setBackgroundResource(R.drawable.btn_click);
            }
//            mTvProductTitle.setText(homeResponse.result.title);
            DecimalFormat df = new DecimalFormat("0.00");
            mProductRate.setText(df.format(100 * Double.parseDouble(homeResponse.result.minProfit)));
            if ("1".equals(homeResponse.result.plstimeLimitType)) {
                mProductTime.setText("项目期限:" + homeResponse.result.plstimeLimitValue + "个月");
            } else if ("0".equals(homeResponse.result.plstimeLimitType)) {
                mProductTime.setText("项目期限:" + homeResponse.result.plstimeLimitValue + "天");
            }
            mProductNumber.setText(Html.fromHtml("已有<font color='#fc291d'>" + homeResponse.result.investCount + "</font>人投资 共投资<font color='#fc291d'>" + homeResponse.result.investAmount + "</font>元"));
        }
    }

    private void setBannerData() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        ArrayList<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < mBannerList.size(); i++) {
            imgUrls.add(mBannerList.get(i).bannerImgae);
        }
        banner.setImages(imgUrls);
        //设置banner动画效果
        List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();
        transformers.add(DefaultTransformer.class);
        banner.setBannerAnimation(transformers.get(0));
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //position标记是从1开始的
                HomeBannner homeBannner = mBannerList.get(position - 1);
                if (TextUtils.isEmpty(homeBannner.imgaeLinkUrl)) {
                    return;
                }
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("title", homeBannner.imgTitle);
                intent.putExtra("url", homeBannner.imgaeLinkUrl);
                startActivity(intent);
            }
        });
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_home_scroll:
            case R.id.fragment_home_right_arrow:
            case R.id.tongzhi:
                //跳转  详情
                //                startActivity(new Intent(getActivity(), HomeScrollTextActivity.class));
                break;
            case R.id.fragment_home_safe:
                Intent intent2 = new Intent(getActivity(), WebActivity.class);
                intent2.putExtra("title", "安全保障");
                intent2.putExtra("url", mSafeUrl);
                startActivity(intent2);
                break;
            case R.id.fragment_home_jianjie:
                Intent intent3 = new Intent(getActivity(), WebActivity.class);
                intent3.putExtra("title", "公司简介");
                intent3.putExtra("url", mComponyUrl);
                startActivity(intent3);
                break;
            case R.id.bt_touzi:
                if ((status.equals("30"))) {
                    MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                    if (cacheData == null) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    } else if ("0".equals(cacheData.result.realnameStatus) || "0".equals(cacheData.result.cardStatus)) {
                        Intent intent = new Intent(getActivity(), ShiMingRenZhengActivity.class);
                        startActivity(intent);
                    } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                        Intent intent = new Intent(getActivity(), SetJiaoYiMimaActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), TouziActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                break;
            case R.id.home_fragment_pro:
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
