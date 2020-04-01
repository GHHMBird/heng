package finan.heng.com.apps;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

import finan.heng.com.apps.app.my.activity.ForgetPasswordActivity;
import finan.heng.com.apps.app.my.activity.RegisterActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.base.LoadingFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.StringUtils;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/6 9:09
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditText mEtPhone, mEtPwd;
    private Button mBtn;
    private TextView mTvForget, mTvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpToolbar();
        RelativeLayout backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        getSupportActionBar().setTitle("");
        setBarTitle("登录");
        initView();
        init();
    }

    private void init() {
        mBtn.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mEtPhone.addTextChangedListener(this);
        mEtPwd.addTextChangedListener(this);
    }

    private void initView() {
        mEtPhone = findViewById(R.id.cet_input_account);
        mEtPwd = findViewById(R.id.cet_input_pwd);
        mBtn = findViewById(R.id.bt_login);
        mTvForget = findViewById(R.id.tv_forget_pwd);
        mTvRegister = findViewById(R.id.tv_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if (!StringUtils.isPhone(mEtPhone.getText().toString())) {
                    ViewHelper.showToast(LoginActivity.this, "请输入正确的账号");
                    return;
                }
                addLoadingFragment(R.id.activity_login_fl, "HENG_LoginActivity");
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("HENG_LoginActivity")) {
            login();
        }
    }

    private void login() {
        new HttpHelper(this).login(mEtPhone.getText().toString(), "个推ID", mEtPwd.getText().toString()).subscribe(new Action1<LoginResponse>() {
            @Override
            public void call(LoginResponse loginResponse) {
                LoadingFragment loadingFragment = findLoadingFragment();
                if (loadingFragment != null) {
                    loadingFragment.removeSelf(getFragmentManager());
                }

                if (loginResponse.errorCode.equals("0")) {
                    DataCache.instance.saveCacheData("heng", "LoginResponse", loginResponse);
                    Log.e("hhm", loginResponse.result.sessionId);
                    Bus.getDefault().post("LoginSuccess");
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LoadingFragment loadingFragment = findLoadingFragment();
                if (loadingFragment != null) {
                    loadingFragment.removeSelf(getFragmentManager());
                }

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(LoginActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPhone.getText().toString().length() > 0 && mEtPwd.getText().toString().length() > 0) {
            mBtn.setEnabled(true);
            mBtn.setBackgroundResource(R.drawable.btn_click);
        } else {
            mBtn.setEnabled(false);
            mBtn.setBackgroundResource(R.drawable.btn_unclick);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
