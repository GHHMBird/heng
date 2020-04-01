package finan.heng.com.apps.app.home.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.my.activity.BankListActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetRenZhengMessageResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.RuleNameModel;
import finan.heng.com.apps.model.RuleNameResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.StringUtils;
import finan.heng.com.apps.utils.TimeCount;
import finan.heng.com.apps.utils.TrueButton;
import finan.heng.com.apps.utils.WithdrawlDialog;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/3.
 */
public class ShiMingRenZhengActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditText mEt_name;
    private EditText mEt_shenfennum;
    private EditText mEt_yinhangcard;
    private EditText mEt_yuliu_phone;
    private EditText mEt_yanzhengma;
    private Button mBt_shenfenyanzheng;
    private TextView mTv_getyanzhengma;
    private RelativeLayout mRl_item;
    private String mBankId;
    private String mBankName;
    private CheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shimingrenzheng);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("实名认证");
        initView();
        init();
    }

    private void init() {
        mEt_name.addTextChangedListener(this);
        mEt_shenfennum.addTextChangedListener(this);
        mEt_yinhangcard.addTextChangedListener(this);
        mEt_yuliu_phone.addTextChangedListener(this);
        mEt_yanzhengma.addTextChangedListener(this);
        mBt_shenfenyanzheng.setOnClickListener(this);
        mTv_getyanzhengma.setOnClickListener(this);
        mRl_item.setOnClickListener(this);
    }

    private void initView() {
        mEt_name = findViewById(R.id.et_name);
        TextView tvXieYi = findViewById(R.id.tv_xieyi);
        mEt_shenfennum = findViewById(R.id.et_shenfennum);
        mEt_yinhangcard = findViewById(R.id.et_yinhangcard);
        mEt_yuliu_phone = findViewById(R.id.et_yuliu_phone);
       checkBox= findViewById(R.id.iv_select4);
        mEt_yanzhengma = findViewById(R.id.et_yanzhengma);
        mBt_shenfenyanzheng = findViewById(R.id.bt_shenfenyanzheng);
        mTv_getyanzhengma = findViewById(R.id.tv_getyanzhengma);
        mRl_item = findViewById(R.id.rl_item);
        tvXieYi.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEt_name.getText().toString().length() > 0 &&
                mEt_shenfennum.getText().toString().length() > 0 &&
                mEt_yinhangcard.getText().toString().length() > 0 &&
                mEt_yuliu_phone.getText().toString().length() > 0 &&
                mEt_yanzhengma.getText().toString().length() > 0) {
            mBt_shenfenyanzheng.setEnabled(true);
            mBt_shenfenyanzheng.setBackgroundResource(R.drawable.bg_submit_red);


        } else {
            mBt_shenfenyanzheng.setEnabled(false);
            mBt_shenfenyanzheng.setBackgroundResource(R.drawable.bg_submit_gray);

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_shenfenyanzheng:

                ruleName();

                break;
            case R.id.tv_xieyi:
                Intent intent = new Intent(ShiMingRenZhengActivity.this, WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL+"paymentProtocol");
                intent.putExtra("title","支付开通说明");
                startActivity(intent);
                break;
            case R.id.tv_getyanzhengma:
                String phone = mEt_yuliu_phone.getText().toString();
                if (StringUtils.isPhone(phone)) {
                    getCaptcha(phone);
                } else {
                    ViewHelper.showToast(ShiMingRenZhengActivity.this, "手机号码格式不正确");
                }

                break;
            case R.id.rl_item:
                startActivityForResult(new Intent(getBaseContext(), BankListActivity.class), 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == 101) {
            mBankId = data.getStringExtra("bankId");
            mBankName = data.getStringExtra("bankName");
            ((TextView) findViewById(R.id.activity_home_shiming_kaihu)).setText(mBankName);
        }
    }

    private void ruleName() {
        if (!checkBox.isChecked()) {
            ViewHelper.showToast(ShiMingRenZhengActivity.this,"请阅读并同意《支付开通说明》");
            return;
        }
        if (mEt_shenfennum.getText().toString().length() == 18) {
            new HttpHelper(this).ruleName(mEt_name.getText().toString(), mBankId, mEt_shenfennum.getText().toString(), mEt_yinhangcard.getText().toString(), mEt_yuliu_phone.getText().toString(), mEt_yanzhengma.getText().toString()).subscribe(new Action1<RuleNameResponse>() {
                @Override
                public void call(RuleNameResponse ruleNameResponse) {
                    RuleNameModel result = ruleNameResponse.result;
                    MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                    cacheData.result.userRealname = result.realName;
                    cacheData.result.realnameStatus = result.realNameStatus;
                    cacheData.result.cardStatus = result.cardStatus;
                    cacheData.result.userIdCard = result.idCard;
                    DataCache.instance.saveCacheData("heng", "MyUserInfo", cacheData);
                    setResult(1001);
                    finish();
//                    showNormalDialog();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        ViewHelper.showToast(ShiMingRenZhengActivity.this, requestErrorThrowable.getMessage());
                    }
                }
            });
        } else {
            ViewHelper.showToast(ShiMingRenZhengActivity.this, "请输入正确的身份证号");
        }

    }

    private void getCaptcha(String phone) {
        new HttpHelper(this).renzhengMessageCode(phone).subscribe(new Action1<GetRenZhengMessageResponse>() {
            @Override
            public void call(GetRenZhengMessageResponse getRenZhengMessageResponse) {
                TimeCount mTimeCount = new TimeCount(60000, 1000, mTv_getyanzhengma, ShiMingRenZhengActivity.this, 0);
                mTimeCount.start();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ShiMingRenZhengActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });

    }


    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ShiMingRenZhengActivity.this);
        normalDialog.setTitle("认证成功");
        normalDialog.setMessage("恭喜您已成功完成实名认证，设置交易密码后即可投资赚钱");
        normalDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        normalDialog.setNegativeButton("前去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMimaDiaLog();
            }
        });
        // 显示
        normalDialog.show();
    }

    private void showMimaDiaLog() {
        WithdrawlDialog withdrawlDialog = new WithdrawlDialog(this, R.style.MyAlertDialog, R.layout.dialog_trader_password_key, "200", 3);
        withdrawlDialog.show();
        withdrawlDialog.setTrueButton(new TrueButton() {
            @Override
            public void getText(String text) {
                //Toast.makeText(ShiMingRenZhengActivity.this, "六位密码输完啦！！！", Toast.LENGTH_SHORT).show();
                showJiaoyiMimaDialog();
            }
        });
    }

    private void showJiaoyiMimaDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ShiMingRenZhengActivity.this);
        normalDialog.setTitle("认证成功");
        normalDialog.setMessage("为保证你的账户安全，你在投资前设置交易密码");
        normalDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        normalDialog.setNegativeButton("前去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
                //跳转设置交易密码
                startActivity(new Intent(getBaseContext(), SetJiaoYiMimaActivity.class));
            }
        });
        // 显示
        normalDialog.show();
    }


}
