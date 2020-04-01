package finan.heng.com.apps.app.home.activity;

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

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.SetBuyPasswordResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class SetJiaoYiMimaActivity extends BaseActivity implements View.OnClickListener,TextWatcher {


    private EditText mEt_newkey;
    private Button mBt_sure;
    private ImageView mIvIcon;
    private boolean isShow = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setjiaoyimima);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("设置交易密码");
        initView();
        init();
    }

    private void init() {
        mBt_sure.setOnClickListener(this);
        mEt_newkey.addTextChangedListener(this);
        mIvIcon.setOnClickListener(this);
    }

    private void initView() {
        mEt_newkey = findViewById(R.id.et_newkey);
        mBt_sure = findViewById(R.id.bt_sure);
        mIvIcon = findViewById(R.id.activity_setjiaoyimima_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sure:
                new HttpHelper(this).setBuyPassword(mEt_newkey.getText().toString()).subscribe(new Action1<SetBuyPasswordResponse>() {
                    @Override
                    public void call(SetBuyPasswordResponse setBuyPasswordResponse) {
                        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                        cacheData.result.userPaypassword = setBuyPasswordResponse.result.getTradePassword();
                        DataCache.instance.saveCacheData("heng", "MyUserInfo",cacheData);
                        ViewHelper.showToast(SetJiaoYiMimaActivity.this, "设置交易密码成功");
                        setResult(1002);
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(SetJiaoYiMimaActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
                break;
            case R.id.activity_setjiaoyimima_icon:
                if (isShow) {
                    //设置EditText文本为可见的
                    mEt_newkey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIvIcon.setImageResource(R.mipmap.cansee);
                } else {
                    //设置EditText文本为隐藏的
                    mEt_newkey.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIvIcon.setImageResource(R.drawable.mimayan);
                }
                isShow = !isShow;
                mEt_newkey.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = mEt_newkey.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEt_newkey.getText().toString().length()>0){
            mBt_sure.setEnabled(true);
            mBt_sure.setBackgroundResource(R.drawable.bg_submit_red);

        }else {
            mBt_sure.setEnabled(false);
            mBt_sure.setBackgroundResource(R.drawable.bg_submit_gray);

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
