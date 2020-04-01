package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.InterfaceService;
import finan.heng.com.apps.model.GetMessageCodeResponse;
import finan.heng.com.apps.model.RegisterResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.utils.TimeCount;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/6 9:20
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    private EditText mEtPhone, mEtPicCode, mEtCode, mEtPwdOne, mEtPwdTwo, mEtTuiJian;
    private Button    mBtn;
    private ImageView mIvPic;
    private TextView  mTvGetMsgCode;
    private TimeCount mTimeCount;
    private CheckBox  mCheckBox;
    private TextView mTv_xieyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("注册");
        initView();
        init();
    }

    private void init() {
        mIvPic.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mTvGetMsgCode.setOnClickListener(this);
        mEtPicCode.addTextChangedListener(this);
        mEtPhone.addTextChangedListener(this);
        mEtCode.addTextChangedListener(this);
        mEtPwdOne.addTextChangedListener(this);
        mEtPwdTwo.addTextChangedListener(this);
        mTv_xieyi.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(this);
    }

    private void initView() {
        mBtn = findViewById(R.id.bt_submit);//提交按钮
        mTv_xieyi = findViewById(R.id.tv_xieyi);
        mEtPhone = findViewById(R.id.cet_input_account);//手机号码
        mEtPicCode = findViewById(R.id.cet_input_tupian);//图片验证码
        mIvPic = findViewById(R.id.bt_iv_yangzhen);//图片
        mEtCode = findViewById(R.id.cet_input_yanzhenma);//短信验证码
        mTvGetMsgCode = findViewById(R.id.btr_getyanzhengma);//获取验证码
        mEtPwdOne = findViewById(R.id.cet_input_password);//密码输入框
        mEtPwdTwo = findViewById(R.id.cet_isok_password);//密码输入框
        mCheckBox = findViewById(R.id.cb_agreed);//同意协议
        mEtTuiJian = findViewById(R.id.cet_tuijian_code);//推荐码框
        Picasso.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE).fit().into(mIvPic);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_iv_yangzhen:
                Picasso.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).fit().into(mIvPic);
                break;
            case R.id.tv_xieyi:
                Intent intent = new Intent(RegisterActivity.this, WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL+"registerProtocol");
                intent.putExtra("title","用户注册协议");
                startActivity(intent);
                break;
            case R.id.bt_submit://提交
                if (!StringUtils.isPhone(mEtPhone.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入正确的电话号码");
                    return;
                }
                if (mEtCode.getText().toString().length() == 0) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入短信验证码");
                    return;
                }
                if (!mEtPwdOne.getText().toString().equals(mEtPwdTwo.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "两次密码输入不一致");
                    return;
                }
                if (!mCheckBox.isChecked()) {
                    ViewHelper.showToast(RegisterActivity.this, "请同意签署协议");
                    return;
                }
                doRegister();
                break;
            case R.id.btr_getyanzhengma://获取短信验证码
                if (!StringUtils.isPhone(mEtPhone.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入正确的电话号码");
                    return;
                }
                if (TextUtils.isEmpty(mEtPicCode.getText().toString())) {
                    ViewHelper.showToast(RegisterActivity.this, "请输入图形验证码");
                    return;
                }
                getMsgCode();
                break;
        }
    }

    private void doRegister() {
        new HttpHelper(this).register(mEtPhone.getText().toString(), mEtPwdOne.getText().toString(), mEtCode.getText().toString(), mEtTuiJian.getText().toString()).subscribe(new Action1<RegisterResponse>() {
            @Override
            public void call(RegisterResponse registerResponse) {
                if (registerResponse.errorCode.equals("0")) {
                    ViewHelper.showToast(RegisterActivity.this, "注册成功");
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(RegisterActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPicCode.getText().toString().length() > 0 && mEtPhone.getText().toString().length() > 0 && mEtCode.getText().toString().length() > 0 && mEtPwdOne.getText().toString().length() > 0 && mEtPwdTwo.getText().toString().length() > 0 && mCheckBox.isChecked()) {
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mEtPicCode.getText().toString().length() > 0 && mEtPhone.getText().toString().length() > 0 && mEtCode.getText().toString().length() > 0 && mEtPwdOne.getText().toString().length() > 0 && mEtPwdTwo.getText().toString().length() > 0 && mCheckBox.isChecked()) {
            mBtn.setBackgroundResource(R.drawable.btn_click);
            mBtn.setEnabled(true);
        } else {
            mBtn.setBackgroundResource(R.drawable.btn_unclick);
            mBtn.setEnabled(false);
        }
    }

    public void getMsgCode() {
        new HttpHelper(this).getMessageCode(mEtPhone.getText().toString(), mEtPicCode.getText().toString()).subscribe(new Action1<GetMessageCodeResponse>() {
            @Override
            public void call(GetMessageCodeResponse getMessageCodeResponse) {
                mTimeCount = new TimeCount(60000, 1000, mTvGetMsgCode, RegisterActivity.this, 0);
                mTimeCount.start();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Picasso.with(RegisterActivity.this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).fit().into(mIvPic);
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(RegisterActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }
}
