package finan.heng.com.apps;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianSuccessActivity;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/16.
 */
public class PayH5Activity extends BaseActivity {

    private WebView webView;
    private String urls="https://jzh.fuiou.com/app/app500001_res_reto.action";
    private String strHTML = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h5_activity);
        setUpToolbar();
        if (getIntent() != null) {
            strHTML = getIntent().getStringExtra("html");
        }
        getSupportActionBar().setTitle("");
        setBarTitle("充值");
        getData();
        initView();
    }

    public void initView() {
        webView.getSettings().setJavaScriptEnabled(true);// 设置支持javascript脚本
        webView.getSettings().setSupportZoom(false);// 支持缩放
        webView.getSettings().setBuiltInZoomControls(false);// 设置显示缩放按钮
        webView.getSettings().setUseWideViewPort(true);//关键点
        webView.getSettings().setLoadWithOverviewMode(true);//最小显示
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new webViewClient());
    }

    public void getData() {
        webView = findViewById(R.id.h5_act_webView);
        webView.loadDataWithBaseURL("about:blank", strHTML, "text/html", "utf-8", null);
    }

    private class webViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            String successUrl = "http://api.hengll.com/paymentaction/fuyoupaygoldsuccess";
            if (urls.contains(url)) {
                Intent intent = new Intent(PayH5Activity.this, ChongZhiOrTiXianSuccessActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("amount",getIntent().getStringExtra("amount"));
                intent.putExtra("bankCode",getIntent().getStringExtra("bankCode"));
                intent.putExtra("iconUrl",getIntent().getStringExtra("iconUrl"));
                startActivity(intent);
                finish();
            }
            if (url.toLowerCase().contains(successUrl)) {
                Intent intent = new Intent(PayH5Activity.this, ChongZhiOrTiXianSuccessActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("amount",getIntent().getStringExtra("amount"));
                intent.putExtra("bankCode",getIntent().getStringExtra("bankCode"));
                intent.putExtra("iconUrl",getIntent().getStringExtra("iconUrl"));
                startActivity(intent);
                finish();
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }
}