package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
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
import finan.heng.com.apps.model.ChangeJiaoYiPasswordResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class ReviseJiaoYiMimaActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private TextView mTv_forget_jiaoyimima;
    private EditText mEt_oldkey;
    private EditText mEt_newkey;
    private Button mBtn;
    private ImageView mPswshow;
    private boolean isShow=true;
    private ImageView mShownew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisejiaoyimima);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("修改交易密码");
        initView();
        init();
    }

    private void init() {
        mTv_forget_jiaoyimima.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mPswshow.setOnClickListener(this);
        mShownew.setOnClickListener(this);
        mEt_newkey.addTextChangedListener(this);
        mEt_oldkey.addTextChangedListener(this);

    }

    private void initView() {
        mTv_forget_jiaoyimima = findViewById(R.id.tv_forget_jiaoyimima);
        mEt_oldkey = findViewById(R.id.et_oldkey);
        mEt_newkey = findViewById(R.id.et_newkey);
        mBtn = findViewById(R.id.bt_revise);
        mPswshow = findViewById(R.id.pswshow);
        mShownew = findViewById(R.id.shownew);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==101&&resultCode==10010) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_jiaoyimima:
                startActivityForResult(new Intent(getBaseContext(), FindJiaoYiMimaActivity.class),101);
                break;
            case R.id.bt_revise:
                new HttpHelper(this).changeJiaoyiPassword(mEt_oldkey.getText().toString(), mEt_newkey.getText().toString()).subscribe(new Action1<ChangeJiaoYiPasswordResponse>() {
                    @Override
                    public void call(ChangeJiaoYiPasswordResponse changeJiaoYiPasswordResponse) {
                        ViewHelper.showToast(ReviseJiaoYiMimaActivity.this, "交易密码修改成功");
                        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                        cacheData.result.userPaypassword = changeJiaoYiPasswordResponse.result.TradePassword;
                        DataCache.instance.saveCacheData("heng", "MyUserInfo", cacheData);
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(ReviseJiaoYiMimaActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });

                break;
            case R.id.pswshow:
                if (isShow) {
                    //设置EditText文本为可见的
                    mEt_oldkey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPswshow.setImageResource(R.mipmap.cansee);
                } else {
                    //设置EditText文本为隐藏的
                    mEt_oldkey.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPswshow.setImageResource(R.drawable.mimayan);
                }
                isShow = !isShow;
                mEt_oldkey.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mEt_oldkey.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

                break;
            case R.id.shownew:
                if (isShow) {
                    //设置EditText文本为可见的
                    mEt_newkey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mShownew.setImageResource(R.mipmap.cansee);
                } else {
                    //设置EditText文本为隐藏的
                    mEt_newkey.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mShownew.setImageResource(R.drawable.mimayan);
                }
                isShow = !isShow;
                mEt_newkey.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence1 = mEt_newkey.getText();
                if (charSequence1 instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence1;
                    Selection.setSelection(spanText, charSequence1.length());
                }

                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEt_oldkey.getText().toString().length() > 0 && mEt_newkey.getText().toString().length() > 0) {
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
