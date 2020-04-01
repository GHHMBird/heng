package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/4/27.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.PayH5Activity;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.ChongZhiResponse;
import finan.heng.com.apps.model.GetWithdrawInitResponse;
import finan.heng.com.apps.model.SureWithdrawResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.TrueButton;
import finan.heng.com.apps.utils.WithdrawlDialog;
import rx.functions.Action1;

public class ChongZhiOrTiXianActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private ImageView ivBankIcon, tvChoose;
    private TextView tvBankName, tvBankNum, tvTiShi;
    private EditText etMoney;
    private Button btn;
    private String mTxyhkCode;
    boolean isSelect = true;
    private int type;
    private String mTxyhImg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            setBarTitle("充值");
        } else if (type == 2) {
            setBarTitle("提现");
        }
        initView();
        init();
    }

    private void init() {
        tvChoose.setOnClickListener(this);
        etMoney.addTextChangedListener(this);
        btn.setOnClickListener(this);
        initData();
    }


    private void initData() {
        new HttpHelper(this).getWithdrawInit().subscribe(new Action1<GetWithdrawInitResponse>() {

            @Override
            public void call(GetWithdrawInitResponse getWithdrawInitResponse) {
                mTxyhImg = getWithdrawInitResponse.result.getTxyhImg();
                Glide.with(ChongZhiOrTiXianActivity.this).load(mTxyhImg).into(ivBankIcon);
                tvBankName.setText(getWithdrawInitResponse.result.getTxyhkName());
                mTxyhkCode = getWithdrawInitResponse.result.getTxyhkCode();
                tvBankNum.setText("**** **** **** " + mTxyhkCode);
                if (type == 1) {
                    etMoney.setHint("最少充值100元");
                    tvTiShi.setText(getWithdrawInitResponse.result.getCzwxts());
                } else {
                    tvTiShi.setText(getWithdrawInitResponse.result.getTxwxts());
                    etMoney.setHint("最多可提" + getWithdrawInitResponse.result.getTxye() + "元");
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initView() {
        ivBankIcon = findViewById(R.id.tixian_bank_icon);
        tvBankName = findViewById(R.id.tixian_bank_name);
        findViewById(R.id.activity_chongzhi_xieyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChongZhiOrTiXianActivity.this, WebActivity.class);
                intent2.putExtra("title", "恒利来快捷支付服务协议");
                intent2.putExtra("url", URLHelper.getInstance().URL + "paymentProtocol");
                startActivity(intent2);
            }
        });
        tvBankNum = findViewById(R.id.tixian_bank_num);
        tvChoose = findViewById(R.id.chongzhi_choose);
        etMoney = findViewById(R.id.chongzhi_money);
        btn = findViewById(R.id.activity_chongzhi_btn);
        tvTiShi = findViewById(R.id.activity_chongzhi_tishi);

        if (type == 1) {
            ((TextView) findViewById(R.id.activity_chongzhi_tvone)).setText("充值银行卡");
            findViewById(R.id.activity_chonghi_ll).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.activity_chongzhi_tv_two)).setText("充值金额（元）");
            btn.setText("确认充值");
        } else {
            ((TextView) findViewById(R.id.activity_chongzhi_tvone)).setText("提现至银行卡");
            findViewById(R.id.activity_chonghi_ll).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.activity_chongzhi_tv_two)).setText("提现金额（元）");
            btn.setText("确认提现");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chongzhi_choose:
                if (isSelect) {
                    tvChoose.setImageResource(R.mipmap.unselect);
                } else {
                    tvChoose.setImageResource(R.mipmap.select);
                }
                isSelect = !isSelect;
                break;
            case R.id.activity_chongzhi_btn:
                if (type == 1) {//充值
                    if (checkValue2()) {
                        String code = getIntent().getStringExtra("code");
                        new HttpHelper(this).chongZhi(etMoney.getText().toString(), code).subscribe(new Action1<ChongZhiResponse>() {
                            @Override
                            public void call(ChongZhiResponse chongZhiResponse) {
                                String s = chongZhiResponse.result.getStrHTML();
                                Intent intent = new Intent(ChongZhiOrTiXianActivity.this, PayH5Activity.class);
                                intent.putExtra("html", s);
                                intent.putExtra("amount", etMoney.getText().toString());
                                intent.putExtra("bankCode", mTxyhkCode);
                                intent.putExtra("iconUrl", mTxyhImg);
                                startActivity(intent);
                                finish();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                if (throwable instanceof RequestErrorThrowable) {
                                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                    ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                                }
                            }
                        });
                    }
                } else if (type == 2) {//提现
                    if (checkValue()) {
                        showDiaLog();
                    }
                }
                break;
        }
    }

    private boolean checkValue2() {
        if (!isSelect) {
            ViewHelper.showToast(ChongZhiOrTiXianActivity.this, "请阅读并同意《恒利来快捷支付服务协议》");
            return false;
        }
        if (Integer.parseInt(etMoney.getText().toString()) < 100) {
            ViewHelper.showToast(ChongZhiOrTiXianActivity.this, "最少充值100元");
            return false;
        }
        return true;
    }

    private void showDiaLog() {
        WithdrawlDialog withdrawlDialog = new WithdrawlDialog(this, R.style.MyAlertDialog, R.layout.dialog_trader_password_key, "￥" + etMoney.getText().toString(), type);
        withdrawlDialog.show();
        withdrawlDialog.setTrueButton(new TrueButton() {
            @Override
            public void getText(String text) {
                new HttpHelper(ChongZhiOrTiXianActivity.this).sureWithdraw(etMoney.getText().toString(), text).subscribe(new Action1<SureWithdrawResponse>() {
                    @Override
                    public void call(SureWithdrawResponse sureWithdrawResponse) {
                        Intent intent = new Intent(ChongZhiOrTiXianActivity.this, ChongZhiOrTiXianSuccessActivity.class);
                        intent.putExtra("type", 2);
                        intent.putExtra("amount", etMoney.getText().toString());
                        intent.putExtra("bankCode", mTxyhkCode);
                        intent.putExtra("iconUrl", mTxyhImg);
                        startActivity(intent);
                        finish();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(ChongZhiOrTiXianActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
            }
        });
    }

    private boolean checkValue() {
        if (Double.parseDouble(etMoney.getText().toString()) < 100) {
            Toast.makeText(this, "最少提现100元", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etMoney.getText().toString().length() > 0) {
            btn.setEnabled(true);
            btn.setBackgroundResource(R.drawable.btn_click);
        } else {
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.btn_unclick);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
