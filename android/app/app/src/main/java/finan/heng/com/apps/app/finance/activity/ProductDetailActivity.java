package finan.heng.com.apps.app.finance.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mcxiaoke.bus.annotation.BusReceiver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.finance.fragment.ProductDetailFragment;
import finan.heng.com.apps.app.finance.fragment.ShouYiYuGuFragment;
import finan.heng.com.apps.app.finance.fragment.TouZiJiLuFragment;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.ProductDetailModel;
import finan.heng.com.apps.model.ProductDetailResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MySeekBar;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 16:48
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTimeType, tvWan, tvHuanKuan, tvPbar, mTvRemark, mTvRate, mTvTime, mTvMuJiTime, mTvMoney, mTvQiTou, mTvLeft, mTvQiXiTime, mTvIsShow, mTvJieKuan;
    private ImageView mIvIsShow;
    private MySeekBar mPbar;
    private LinearLayout mLlIsShow;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private ShouYiYuGuFragment shouYiYuGuFragment;
    private LinearLayout mViewShow;
    private Button mBtn;
    private ProductDetailFragment mProductDetailFragment1, mProductDetailFragment2;
    private TouZiJiLuFragment mTouZiJiLuFragment;
    private int id;
    private ProductDetailResponse response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        id = getIntent().getIntExtra("id", -1);
        setBarTitle("");
        initView();
        init();
    }

    private void init() {
        mBtn.setOnClickListener(this);
        mTvIsShow.setOnClickListener(this);
        mIvIsShow.setOnClickListener(this);
        mPbar.setOnSeekBarChangeListener(MyDistanceListener);
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("ProductDetailActivity")) {
            initData();
        }
    }

    private void initData() {
        new HttpHelper(this).getProduct(id).subscribe(new Action1<ProductDetailResponse>() {
            @Override
            public void call(ProductDetailResponse productDetailResponse) {

                DataCache.instance.saveCacheData("heng", "ProductDetailResponse" + id, productDetailResponse);

                setData(productDetailResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ProductDetailActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void setData(ProductDetailResponse response) {
        this.response = response;
        mPbar.setMax(100);
        ProductDetailModel.ProductsBean products = response.result.getProducts();
        setBarTitle(products.getTitle());
        mTvRemark.setText(products.getDescription());
        DecimalFormat df = new DecimalFormat("0.00");
        mTvRate.setText(df.format(Double.parseDouble(products.getMinProfit()) * 100));
        if ("1".equals(products.getPlstimeLimitType())) {
            mTvTime.setText(products.getPlstimeLimitValue());
            tvTimeType.setText("个月");
        } else if ("0".equals(products.getPlstimeLimitType())) {
            mTvTime.setText(products.getPlstimeLimitValue());
            tvTimeType.setText("天");
        }
        switch (products.getStatus()) {// //-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
            case "-41":
                mBtn.setText("复审未通过");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "-11":
                mBtn.setText("初审未通过");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "10":
                mBtn.setText("待初审");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "11":
                mBtn.setText("初审通过");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "20":
                mBtn.setText("预告中");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "30":
                mBtn.setText("立即购买");
                mBtn.setEnabled(true);
                mBtn.setBackgroundResource(R.drawable.btn_click);
                break;
            case "40":
                mBtn.setText("待复审");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "41":
                mBtn.setText("复审通过");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "50":
                mBtn.setText("还款中");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "60":
                mBtn.setText("已回款");
                mBtn.setEnabled(false);
                mBtn.setBackgroundResource(R.drawable.btn_unclick);
                break;
        }
        double v1 = Double.parseDouble(products.getProductsScale());
        if (v1 < 10000) {
            mTvMoney.setText((int) v1 + "");
            tvWan.setText("元");
        } else {
            double v = v1 / 10000;
            String s = v + "";
            String[] split = s.split("\\.");
            char[] chars = split[1].toCharArray();
            String sss = "";
            boolean isFirst = true;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (isFirst) {
                    if (chars[i] == '0') {
                        sss += "";
                    } else {
                        isFirst = false;
                        sss += chars[i];
                    }
                } else {
                    sss += chars[i];
                }
            }
            if (TextUtils.isEmpty(sss)) {
                mTvMoney.setText(split[0] + "");
                tvWan.setText("万元");
            } else {
                String ssss = "";
                char[] chars1 = sss.toCharArray();
                for (int i = chars1.length - 1; i >= 0; i--) {
                    ssss += chars1[i];
                }
                mTvMoney.setText(split[0] + "." + ssss);
                tvWan.setText("万元");
            }
        }
        double i22 = Double.parseDouble(products.getInvestBegin());
        if (i22 < 10000) {
            mTvQiTou.setText("起投" + (int) i22 + "元");
        } else {
            mTvQiTou.setText("起投" + i22 / 10000 + "万元");
        }
        double i1 = Double.parseDouble(products.getSurplusAmount());
        if (i1 < 10000) {
            mTvLeft.setText("剩余" + (int) i1 + "元");
        } else {
            double v = i1 / 10000;
            String s = v + "";
            String[] split = s.split("\\.");
            char[] chars = split[1].toCharArray();
            String sss = "";
            boolean isFirst = true;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (isFirst) {
                    if (chars[i] == '0') {
                        sss += "";
                    } else {
                        isFirst = false;
                        sss += chars[i];
                    }
                } else {
                    sss += chars[i];
                }
            }
            if (TextUtils.isEmpty(sss)) {
                mTvLeft.setText("剩余" + split[0] + "万元");
            } else {
                String ssss = "";
                char[] chars1 = sss.toCharArray();
                for (int i = chars1.length - 1; i >= 0; i--) {
                    ssss += chars1[i];
                }
                mTvLeft.setText("剩余" + split[0] + "." + ssss + "万元");
            }
        }
        if (products.getCalInterestType().equals("0")) {
            mTvJieKuan.setText("募集满标后计息");
            mTvQiXiTime.setText(products.getBeginDate().length() > 10 ? products.getBeginDate().substring(0, 10) : products.getBeginDate());
        } else if (products.getCalInterestType().equals("1")) {
            mTvJieKuan.setText("即投即计息");
            mTvQiXiTime.setText(products.getPublishDate().length() > 10 ? products.getPublishDate().substring(0, 10) : products.getPublishDate());
        }
        if ("0".equals(products.getRepaymentType())) {
            tvHuanKuan.setText("一次性还本付息");
        } else if ("1".equals(products.getRepaymentType())) {
            tvHuanKuan.setText("每月付款,到期还本");
        } else if ("2".equals(products.getRepaymentType())) {
            tvHuanKuan.setText("等额本息");
        } else if ("3".equals(products.getRepaymentType())) {
            tvHuanKuan.setText("等额本金");
        }
        mTvMuJiTime.setText((products.getPublishDate().length() > 10 ? products.getPublishDate().substring(0, 10) : products.getPublishDate()) + "至" + (products.getBeginDate().length() > 10 ? products.getBeginDate().substring(0, 10) : products.getBeginDate()));
        initViewPager();
        mPbar.setProgress(0);
        mPbar.setProgress(100);
        mPbar.setProgress((int) (100 * response.result.getProducts().getInvestPercent()));
    }

    private void initViewPager() {
        String s1 = URLHelper.getInstance().URL + "products/productsDetails?id=" + id;
        String s2 = URLHelper.getInstance().URL + "products/productsReport?id=" + id;
        ArrayList<Fragment> fragments = new ArrayList<>();
        shouYiYuGuFragment = new ShouYiYuGuFragment(id);
        mProductDetailFragment1 = new ProductDetailFragment(s1);
        mProductDetailFragment2 = new ProductDetailFragment(s2);
        mTouZiJiLuFragment = new TouZiJiLuFragment(id);
        fragments.add(shouYiYuGuFragment);
        fragments.add(mProductDetailFragment1);
        fragments.add(mProductDetailFragment2);
        fragments.add(mTouZiJiLuFragment);
        ArrayList<String> title = new ArrayList<>();
        title.add("收益预估");
        title.add("项目详情");
        title.add("评估报告");
        title.add("投资记录");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);
        mViewpager.setOffscreenPageLimit(4);
        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        mViewpager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mViewShow.setVisibility(View.VISIBLE);
                        break;
                    default:
                        mViewShow.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void initView() {
        mTvRemark = findViewById(R.id.activity_product_detail_remark);
        mTvRate = findViewById(R.id.activity_product_detail_rate);
        mTvTime = findViewById(R.id.activity_product_detail_time);
        mTvMoney = findViewById(R.id.activity_product_detail_money);
        mPbar = findViewById(R.id.activity_product_detail_progressbar);
        mTvQiTou = findViewById(R.id.activity_product_detail_qitou);
        mTvLeft = findViewById(R.id.activity_product_detail_left);
        mTvQiXiTime = findViewById(R.id.activity_product_detail_qixishijian);
        mTvIsShow = findViewById(R.id.activity_product_detail_tv_isshow);
        mIvIsShow = findViewById(R.id.activity_product_detail_iv_isshow);
        mTvJieKuan = findViewById(R.id.activity_product_detail_jiekuanfangshi);
        mTvMuJiTime = findViewById(R.id.activity_product_detail_mujishijian);
        mLlIsShow = findViewById(R.id.activity_product_detail_ll_isshow);
        mTabLayout = findViewById(R.id.activity_product_detail_tab);
        mViewpager = findViewById(R.id.activity_product_detail_viewpager);
        mViewShow = findViewById(R.id.activity_product_detail_isshow);
        mBtn = findViewById(R.id.activity_product_detail_btn);
        tvPbar = findViewById(R.id.activity_product_detail_tv_progress);
        tvTimeType = findViewById(R.id.activity_product_detail_time_type);
        tvHuanKuan = findViewById(R.id.activity_product_detail_huankuan);
        tvWan = findViewById(R.id.activity_pda_wan);
        mLlIsShow.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_product_detail_tv_isshow:
            case R.id.activity_product_detail_iv_isshow:
                if (mLlIsShow.isShown()) {
                    mLlIsShow.setVisibility(View.GONE);
                    mTvIsShow.setText("展开");
                } else {
                    mLlIsShow.setVisibility(View.VISIBLE);
                    mTvIsShow.setText("收起");
                }
                break;
            case R.id.activity_product_detail_btn:
                MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                if (cacheData == null) {
                    Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if ("0".equals(cacheData.result.realnameStatus)) {
                    startActivity(new Intent(ProductDetailActivity.this, ShiMingRenZhengActivity.class));
                } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                    startActivity(new Intent(ProductDetailActivity.this, SetJiaoYiMimaActivity.class));
                } else {
                    Intent intent = new Intent(ProductDetailActivity.this, TouziActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, 10020);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10020 && resultCode == 10021) {
            finish();
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;
        private List<String> list_Title;

        public MyFragmentPagerAdapter(FragmentManager fm, List<String> list_Title, List<Fragment> list_fragment) {
            super(fm);
            this.list_Title = list_Title;
            this.list_fragment = list_fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }

        @Override
        public Fragment getItem(int position) {

            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private SeekBar.OnSeekBarChangeListener MyDistanceListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvPbar.setText(progress + "%");
            int position = mPbar.getProgress(); //seekbar当前进度
            tvPbar.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int measuredWidth = tvPbar.getMeasuredWidth();//textView宽度
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            int width = metric.widthPixels;//屏幕宽度（像素）
            int px = dip2px(ProductDetailActivity.this, 25);
            int pbarWeight = width - px * 2;//pbar宽度
            tvPbar.setX((pbarWeight * position) / 100 + px - measuredWidth / 2 + 50 - progress);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
    };
}
