package finan.heng.com.apps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import finan.heng.com.apps.base.BaseActivity;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 11:05
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WebActivity extends BaseActivity {

    private MyWebView webView;
    private String mUrl;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        mUrl = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
        setBarTitle(mTitle);
        initView();
        init();
    }

    private void init() {

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
        webView.loadUrl(mUrl);
    }

    private void initView() {
        webView = findViewById(R.id.activity_web_web);
    }


}
