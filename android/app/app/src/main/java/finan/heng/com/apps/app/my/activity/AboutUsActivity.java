package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/2.
 */

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetCompanyInfoResponse;
import finan.heng.com.apps.save.DataCache;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private TextView     tv;
    private LinearLayout llJianJie, llDaFen, llYiJian;
    private TextView     mTvCompany,mInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("关于我们");
        initView();
        init();
    }

    private void init() {
        llJianJie.setOnClickListener(this);
        llDaFen.setOnClickListener(this);
        llYiJian.setOnClickListener(this);
        initData();
    }

    private void initData() {
        try {
            String versinName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            tv.setText("恒利来 v" + versinName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        GetCompanyInfoResponse ca = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");
        if (ca!=null) {
            mInfo.setText(ca.result.recordNum);
            mTvCompany.setText(ca.result.compony);
        }
    }

    private void initView() {
        mTvCompany = findViewById(R.id.activity_about_us_company);
        mInfo = findViewById(R.id.activity_about_us_info);
        tv = findViewById(R.id.activity_about_us_tv);
        llJianJie = findViewById(R.id.activity_about_us_jianjie);
        llDaFen = findViewById(R.id.activity_about_us_dafen);
        llYiJian = findViewById(R.id.activity_about_us_yijian);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_about_us_jianjie:
                Intent intent = new Intent(AboutUsActivity.this, WebActivity.class);//hotspot/compony
                intent.putExtra("title", "关于我们");
                intent.putExtra("url", URLHelper.getInstance().URL + "hotspot/compony");
                startActivity(intent);
                break;
            case R.id.activity_about_us_dafen:
                //调应用市场
                boolean isHad = false;
                final PackageManager packageManager = getPackageManager();
                List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
                if (pinfo != null) {
                    for (int i = 0; i < pinfo.size(); i++) {
                        String pn = pinfo.get(i).packageName;
                        if (TextUtils.equals("com.tencent.android.qqdownloader",pn)) {
                            isHad = true;
                        }
                    }
                }
                if (isHad) {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent in = new Intent(Intent.ACTION_VIEW, uri);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                } else {
                    ViewHelper.showToast(AboutUsActivity.this,"请先安装应用宝");
                }
                break;
            case R.id.activity_about_us_yijian:
                startActivity(new Intent(AboutUsActivity.this, YiJianFanKuiActivity.class));
                break;
        }
    }
}
