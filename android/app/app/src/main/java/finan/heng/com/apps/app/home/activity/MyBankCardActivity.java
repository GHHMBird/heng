package finan.heng.com.apps.app.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.BankCardInfo;
import finan.heng.com.apps.model.BankCardResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */
public class MyBankCardActivity extends BaseActivity {

    private TextView mTv_bank_name;
    private TextView mTv_bank_num;
    private ImageView mIv_bank_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybankcard);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("我的银行卡");
        initView();
        init();
    }

    private void init() {
        new HttpHelper(this).myBankCard().subscribe(new Action1<BankCardResponse>() {
            @Override
            public void call(BankCardResponse bankCardResponse) {
                BankCardInfo result = bankCardResponse.result;
                mTv_bank_name.setText(result.getBankName());
                String bankCode = result.getBankCode();
                String s = "";
                char[] chars = bankCode.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (i < chars.length - 4) {
                        s += "*";
                    } else {
                        s += chars[i];
                    }
                }
                mTv_bank_num.setText(s);
                mTv_bank_num.setText(result.getBankCode());
                Glide.with(MyBankCardActivity.this).load(result.getBankImg()).into(mIv_bank_icon);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MyBankCardActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initView() {
        mTv_bank_name = findViewById(R.id.tv_bank_name);
        mTv_bank_num = findViewById(R.id.tv_bank_num);
        mIv_bank_icon = findViewById(R.id.iv_bank_icon);

    }
}
