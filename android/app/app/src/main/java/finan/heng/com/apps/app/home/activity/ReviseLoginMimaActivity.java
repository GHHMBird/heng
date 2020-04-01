package finan.heng.com.apps.app.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.ChangeLoginPasswordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class ReviseLoginMimaActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditText mEtOld, mEtNew;
    private Button    mBtn;
    private ImageView mIv1;
    private ImageView mIv2;
    private boolean   oldShow, newShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviseloginmima);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("修改登录密码");
        initView();
        init();
    }

    private void init() {
        mBtn.setOnClickListener(this);
        mIv1.setOnClickListener(this);
        mIv2.setOnClickListener(this);
        mEtNew.addTextChangedListener(this);
        mEtOld.addTextChangedListener(this);
    }

    private void initView() {
        mEtOld = findViewById(R.id.et_oldkey);
        mEtNew = findViewById(R.id.et_newkey);
        mBtn = findViewById(R.id.bt_revise);
        mIv1 = findViewById(R.id.activity_reviseloginmima1);
        mIv2 = findViewById(R.id.activity_reviseloginmima2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_revise:
                String s = mEtOld.getText().toString();
                String s1 = mEtNew.getText().toString();
                new HttpHelper(this).changeLoginPassword(s, s1).subscribe(new Action1<ChangeLoginPasswordResponse>() {
                    @Override
                    public void call(ChangeLoginPasswordResponse changeLoginPasswordResponse) {
                        ViewHelper.showToast(ReviseLoginMimaActivity.this, "修改登录密码成功");
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(ReviseLoginMimaActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
                break;
            case R.id.activity_reviseloginmima1:
                if (oldShow) {
                    mEtOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIv1.setImageResource(R.mipmap.cansee);
                    mEtOld.setSelection(mEtOld.getText().toString().length());
                } else {
                    mEtOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIv1.setImageResource(R.drawable.mimayan);
                    mEtOld.setSelection(mEtOld.getText().toString().length());
                }
                oldShow = !oldShow;
                break;
            case R.id.activity_reviseloginmima2:
                if (newShow) {
                    mEtNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIv2.setImageResource(R.mipmap.cansee);
                    mEtNew.setSelection(mEtNew.getText().toString().length());
                } else {
                    mEtNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIv2.setImageResource(R.drawable.mimayan);
                    mEtNew.setSelection(mEtNew.getText().toString().length());
                }
                newShow = !newShow;
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEtOld.getText().toString().length() > 0 && mEtNew.getText().toString().length() > 0) {
            mBtn.setEnabled(true);
            mBtn.setBackgroundResource(R.drawable.bg_submit_red);

        } else {
            mBtn.setEnabled(false);
            mBtn.setBackgroundResource(R.drawable.bg_submit_gray);

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
