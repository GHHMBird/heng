package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.home.activity.MyBankCardActivity;
import finan.heng.com.apps.app.home.activity.ReviseJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ReviseLoginMimaActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingPassActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.LogOutResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/6.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRl_exit;
    private RelativeLayout mRl_shiming;
    private RelativeLayout mRl_my_yinhangka;
    private RelativeLayout mRl_loginmima;
    private RelativeLayout mRl_jiaoyimima;
    private TextView mTv_shiming_name;
    private MyUserInfo mCacheData;
    private TextView mIsSet;
    private TextView mBindphone;
    private TextView mTv_isbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("个人设置");
        initView();
        init();
    }

    private void init() {
        mRl_exit.setOnClickListener(this);
        mRl_shiming.setOnClickListener(this);
        mRl_my_yinhangka.setOnClickListener(this);
        mRl_loginmima.setOnClickListener(this);
        mRl_jiaoyimima.setOnClickListener(this);
    }

    private void initView() {
        mRl_exit = findViewById(R.id.rl_exit);
        mRl_shiming = findViewById(R.id.rl_shiming);
        mRl_my_yinhangka = findViewById(R.id.rl_my_yinhangka);
        mRl_loginmima = findViewById(R.id.rl_loginmima);
        mRl_jiaoyimima = findViewById(R.id.rl_jiaoyimima);
        mTv_shiming_name = findViewById(R.id.tv_shiming_name);
        mIsSet = findViewById(R.id.isSet);
        mBindphone = findViewById(R.id.bindphone);
        mTv_isbind = findViewById(R.id.tv_isbind);

        mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        if ("0".equals(mCacheData.result.realnameStatus)) {
            mTv_shiming_name.setText("未认证");
        } else if ("1".equals(mCacheData.result.realnameStatus)) {
            String name = mCacheData.result.userRealname.substring(0, 1);
            mTv_shiming_name.setText(name + "**");
        }
        if ("0".equals(mCacheData.result.cardStatus)) {
            mTv_isbind.setText("未绑定");
        } else if ("1".equals(mCacheData.result.cardStatus)) {
            mTv_isbind.setText("已绑定");
        }
        if (TextUtils.isEmpty(mCacheData.result.userPaypassword)) {
            mIsSet.setText("未设置");
        } else {
            mIsSet.setText("已设置");
        }
        String substring = mCacheData.result.userAccount.substring(0, 3);
        String substring1 = mCacheData.result.userAccount.substring(mCacheData.result.userAccount.length() - 4);
        mBindphone.setText(substring + "****" + substring1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        if (requestCode == 1001 && resultCode == 1001) {
            mTv_shiming_name.setText(mCacheData.result.userRealname);
            mTv_isbind.setText("已绑定");
            return;
        }
        if (requestCode == 1002 && resultCode == 1002) {
            mIsSet.setText("已设置");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_exit:
                showPointDialog();
                break;
            case R.id.rl_shiming:
                if ("0".equals(mCacheData.result.realnameStatus)) {
                    mTv_shiming_name.setText("未认证");
                    startActivityForResult(new Intent(SettingActivity.this, ShiMingRenZhengActivity.class), 1001);
                } else if ("1".equals(mCacheData.result.realnameStatus)) {
                    Intent intent = new Intent(SettingActivity.this, ShiMingPassActivity.class);
                    intent.putExtra("realname", mCacheData.result.userRealname);
                    intent.putExtra("idcard", mCacheData.result.userIdCard);
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_yinhangka:
                if ("0".equals(mCacheData.result.realnameStatus)) {
                    mTv_isbind.setText("未绑定");
                    startActivityForResult(new Intent(SettingActivity.this, ShiMingRenZhengActivity.class), 1001);
                } else if ("1".equals(mCacheData.result.realnameStatus)) {
                    mTv_isbind.setText("已绑定");
                    startActivity(new Intent(SettingActivity.this, MyBankCardActivity.class));
                }
                break;
            case R.id.rl_loginmima:
                startActivity(new Intent(SettingActivity.this, ReviseLoginMimaActivity.class));
                break;
            case R.id.rl_jiaoyimima:
                if (TextUtils.isEmpty(mCacheData.result.userPaypassword)) {
                    mIsSet.setText("未设置");
                    startActivityForResult(new Intent(SettingActivity.this, SetJiaoYiMimaActivity.class), 1002);
                } else {
                    mIsSet.setText("已设置");
                    startActivity(new Intent(SettingActivity.this, ReviseJiaoYiMimaActivity.class));
                }
                break;
        }

    }

    private void showPointDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SettingActivity.this);
        View inflate = View.inflate(SettingActivity.this, R.layout.loginout_dialog, null);
        normalDialog.setView(inflate);
        // 显示
        final AlertDialog alertDialog = normalDialog.create();
        alertDialog.show();
        inflate.findViewById(R.id.loginout_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.loginout_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpHelper(SettingActivity.this).logout().subscribe(new Action1<LogOutResponse>() {
                    @Override
                    public void call(LogOutResponse logOutResponse) {
                        DataCache.instance.clearCacheData("heng", "MyUserInfo");
                        DataCache.instance.clearCacheData("heng", "LoginResponse");
                        DataCache.instance.clearCacheData("heng", "MyWalletResponse");
                        Bus.getDefault().post("exit");
                        finish();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(SettingActivity.this, requestErrorThrowable.getMessage());
                        }

                    }
                });
            }
        });
    }
}
