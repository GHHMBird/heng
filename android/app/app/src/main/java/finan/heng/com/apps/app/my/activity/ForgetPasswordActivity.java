package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.InterfaceService;
import finan.heng.com.apps.model.GetMessageCodeResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/6 9:25
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditText mEtPhone, mEtPicCode;
    private Button    mBtn;
    private ImageView mPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("找回登录密码");
        initView();
        init();
    }

    private void init() {
        mPic.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mEtPicCode.addTextChangedListener(this);
        mEtPhone.addTextChangedListener(this);
        initData();
    }

    private void initData() {
        Picasso.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE).into(mPic);
    }

    private void initView() {
        mPic = findViewById(R.id.activity_find_password_pic);
        mEtPicCode = findViewById(R.id.activity_find_password_etmsg);
        mEtPhone = findViewById(R.id.activity_find_password_phone);
        mBtn = findViewById(R.id.activity_find_password_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_find_password_btn:
                goto2();
                break;
            case R.id.activity_find_password_pic:
                Picasso.with(this).load(URLHelper.getInstance().URL + InterfaceService.IMAGE_CODE + "?" + System.currentTimeMillis()).fit().into(mPic);
                break;
        }
    }

    private void goto2() {
        new HttpHelper(this)
                .findPasswordMessageCode(mEtPhone.getText().toString(), mEtPicCode.getText().toString())
                .subscribe(new Action1<GetMessageCodeResponse>() {
            @Override
            public void call(GetMessageCodeResponse getMessageCodeResponse) {
                Intent intent = new Intent(ForgetPasswordActivity.this, ForgetPasswordActivity2.class);
                intent.putExtra("phone",mEtPhone.getText().toString());
                startActivity(intent);
                finish();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ForgetPasswordActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtPicCode.getText().toString().length() > 0 && mEtPhone.getText().toString().length() > 0) {
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
