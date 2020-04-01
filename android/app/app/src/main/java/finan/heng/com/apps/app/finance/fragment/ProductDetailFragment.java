package finan.heng.com.apps.app.finance.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.MyWebView;
import finan.heng.com.apps.MyWebViewClient;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseFragment;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 22:43
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailFragment extends BaseFragment {

    private  String url;
    private TextView mTvOne, mTvTwo, mTvThree;
    private LinearLayout llOne, llTwo;
    private ImageView    mIvPic;
    private LinearLayout mLlOne, mLlTwo;
    private MyWebView mMyWebView;
    private MyWebView webView;

    public ProductDetailFragment(String url) {
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        initView(view);
        init();
        initData();
        return view;
    }

    private void init() {
        //        llOne.setOnClickListener(this);
        //        llTwo.setOnClickListener(this);
        //        mLlOne.setOnClickListener(this);
        //        mLlTwo.setOnClickListener(this);
        //        initData();
    }

    private void initData() {
        webView.getSettings().setJavaScriptEnabled(true);// 设置支持javascript脚本
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportZoom(true);// 支持缩放
        webView.getSettings().setBuiltInZoomControls(true);// 设置显示缩放按钮
        webView.getSettings().setUseWideViewPort(true);//关键点
        webView.getSettings().setLoadWithOverviewMode(true);//最小显示
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setWebViewClient(new MyWebViewClient() {
            @Override//点击时的
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.contains("Error")) {
                    webView.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl(url);
    }

    private void initView(View view) {
        webView = view.findViewById(R.id.fragment_detail_product_webView);
        //        mTvOne = (TextView) view.findViewById(R.id.fragment_detail_product_tv_one);
        //        mIvPic = (ImageView) view.findViewById(R.id.fragment_detail_product_iv);
        //        mTvTwo = (TextView) view.findViewById(R.id.fragment_detail_product_tv_two);
        //        mTvThree = (TextView) view.findViewById(R.id.fragment_detail_product_tv_three);
        //        mLlOne = (LinearLayout) view.findViewById(R.id.fragment_detail_product_horione);
        //        mLlTwo = (LinearLayout) view.findViewById(R.id.fragment_detail_product_horitwo);
        //
        //        llOne = (LinearLayout) view.findViewById(R.id.fragment_detail_product_llone);
        //        llTwo = (LinearLayout) view.findViewById(R.id.fragment_detail_product_lltwo);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
