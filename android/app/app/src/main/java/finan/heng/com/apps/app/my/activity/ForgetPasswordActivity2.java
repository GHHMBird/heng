package finan.heng.com.apps.app.my.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.getPassWordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.TimeCount;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 18:47
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ForgetPasswordActivity2 extends BaseActivity implements View.OnClickListener, TextWatcher {

    private ImageView mIvLook;
    private EditText mEtPwd, etMsg;
    private boolean isShow;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword2);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("找回登录密码");
        initView();
        init();
    }

    private void init() {
        mBtn.setOnClickListener(this);
        mIvLook.setOnClickListener(this);
    }

    private void initView() {
        TextView tvPhone = findViewById(R.id.activity_find_password_2_phone);
        String phone = getIntent().getStringExtra("phone");
        tvPhone.setText(Html.fromHtml("已向手机<font color='#FFA014'>" + phone + "</font>发送短信"));

        TextView mTvMsgCode = findViewById(R.id.activity_find_password_2_tvmsg);
        TimeCount mTimeCount = new TimeCount(60000, 1000, mTvMsgCode, ForgetPasswordActivity2.this, 0);
        mTimeCount.start();

        mIvLook = findViewById(R.id.activity_find_password_2_ivlook);
        mEtPwd = findViewById(R.id.activity_find_password_2_etpassword);
        etMsg = findViewById(R.id.activity_find_password_2_etmsg);
        mBtn = findViewById(R.id.activity_find_password_2_btn);

        mEtPwd.addTextChangedListener(this);
        etMsg.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_find_password_2_btn:
                change();
                break;
            case R.id.activity_find_password_2_ivlook:
                if (isShow) {
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIvLook.setImageResource(R.mipmap.cansee);
                    mEtPwd.setSelection(mEtPwd.getText().toString().length());
                } else {
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIvLook.setImageResource(R.drawable.mimayan);
                    mEtPwd.setSelection(mEtPwd.getText().toString().length());
                }
                isShow = !isShow;
                break;
        }
    }

    private void change() {
        new HttpHelper(this).findPassWord(getIntent().getStringExtra("phone"), etMsg.getText().toString(), mEtPwd.getText().toString()).subscribe(new Action1<getPassWordResponse>() {
            @Override
            public void call(getPassWordResponse getPassWordResponse) {
                ViewHelper.showToast(ForgetPasswordActivity2.this, "找回登陆密码成功");
                finish();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ForgetPasswordActivity2.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPwd.getText().toString().length() > 0 && etMsg.getText().toString().length() > 0) {
            mBtn.setBackgroundResource(R.drawable.btn_click);
            mBtn.setEnabled(true);
        } else {
            mBtn.setBackgroundResource(R.drawable.btn_unclick);
            mBtn.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
