package finan.heng.com.apps.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import java.text.DecimalFormat;
import java.util.List;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.model.GetProductRedPackModel;
import finan.heng.com.apps.model.GetProductRedPackResponse;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.model.SureBuyResponse;
import finan.heng.com.apps.model.SurePayResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyPopupWindow;
import finan.heng.com.apps.utils.OnSelectItemListener;
import finan.heng.com.apps.utils.TrueButton;
import finan.heng.com.apps.utils.WithdrawlDialog;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/24.
 */
public class TouziActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_jiaxiquan, rl_youhuiquan;
    private MyPopupWindow myPopupWindow;
    private TextView tvChongZhi, tv_jiaxi, tvRedPac, tvTimeType, tv_qixian, tv_yuan, tv_shouyilv, tv_num, tvMoney;
    private Button mBt_touzi;
    private int id;
    private EditText etTouZi;
    private int couId;
    private int startMoney;
    private CheckBox checkBox;
    private int redId;
    private TextView mTvXieYi;
    private List<GetProductRedPackModel.BonusesByTypeBean> mHongbaoList;
    private List<GetProductRedPackModel.BonusesByTypeBean> mJiaxiList;
    private int mRedMoney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_touzi);
        setUpToolbar();
        id = getIntent().getIntExtra("id", -1);
        getSupportActionBar().setTitle("");
        setBarTitle("");
        initView();
    }

    private void initView() {
        etTouZi = findViewById(R.id.activity_home_touzi_et);
        rl_jiaxiquan = findViewById(R.id.rl_jiaxiquan);
        rl_youhuiquan = findViewById(R.id.rl_youhuiquan);
        mBt_touzi = findViewById(R.id.bt_touzi);
        mTvXieYi = findViewById(R.id.tv_xieyi);
        tvMoney = findViewById(R.id.activity_home_touzi_money);
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        tvMoney.setText(Html.fromHtml("可用金额:<font color='#Fc291d'>" + cacheData.result.getAvailableAmount() + "</font>"));
        tvTimeType = findViewById(R.id.activity_home_touzi_timesss);
        tv_qixian = findViewById(R.id.tv_qixian);
        checkBox = findViewById(R.id.activity_home_touzi_check_box_btn);
        tvRedPac = findViewById(R.id.tv_lingqu);
        tvChongZhi = findViewById(R.id.activity_home_touzi_chonghzi);
        tv_jiaxi = findViewById(R.id.tv_jiaxi);
        tv_num = findViewById(R.id.tv_num);
        tv_yuan = findViewById(R.id.tv_yuan);
        tv_shouyilv = findViewById(R.id.tv_shouyilv);
        mBt_touzi.setOnClickListener(this);
        tvChongZhi.setOnClickListener(this);
        rl_youhuiquan.setOnClickListener(this);
        mTvXieYi.setOnClickListener(this);
        rl_jiaxiquan.setOnClickListener(this);
        etTouZi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etTouZi.getText().toString().length() > 0) {
                    mBt_touzi.setEnabled(true);
                    mBt_touzi.setBackgroundResource(R.drawable.btn_click);
                } else {
                    mBt_touzi.setEnabled(false);
                    mBt_touzi.setBackgroundResource(R.drawable.btn_unclick);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                redId = 0;
                mRedMoney = 0;
                couId = 0;
                rl_jiaxiquan.setClickable(true);
                rl_youhuiquan.setClickable(true);
                tvRedPac.setText("");
                tv_jiaxi.setText("");
                String s1 = etTouZi.getText().toString();
                if (TextUtils.isEmpty(s1)) {
                    tv_yuan.setText("0");
                    return;
                }
                new HttpHelper(TouziActivity.this).getProductDetailMoney(id, Integer.parseInt(s1)).subscribe(new Action1<GetProductDetailMoneyResponse>() {
                    @Override
                    public void call(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        tv_yuan.setText(df.format(Double.parseDouble(getProductDetailMoneyResponse.result.profit)) + "");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof RequestErrorThrowable) {
                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                        }
                    }
                });
            }
        });

        int id = getIntent().getIntExtra("id", -1);
        new HttpHelper(this).sureBuy(id).subscribe(new Action1<SureBuyResponse>() {
            @Override
            public void call(SureBuyResponse sureBuyResponse) {
                setData(sureBuyResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_jiaxiquan:
                if (TextUtils.isEmpty(etTouZi.getText().toString())) {
                    ViewHelper.showToast(TouziActivity.this, "请先输入投资金额");
                    return;
                }
                ShowPop();
                break;
            case R.id.activity_home_touzi_chonghzi:
                startActivity(new Intent(TouziActivity.this, ChongZhiListActivity.class));
                break;
            case R.id.tv_xieyi:
                Intent intent = new Intent(TouziActivity.this, WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL + "investmentProtocol?id=" + id);
                intent.putExtra("title", "恒利来投资协议");
                startActivity(intent);
                break;
            case R.id.rl_youhuiquan:
                if (TextUtils.isEmpty(etTouZi.getText().toString())) {
                    ViewHelper.showToast(TouziActivity.this, "请先输入投资金额");
                    return;
                }
                ShowPop2();
                break;
            case R.id.bt_touzi:
                if (checkValue()) {
                    WithdrawlDialog withdrawlDialog = new WithdrawlDialog(this, R.style.MyAlertDialog, R.layout.dialog_trader_password_key, "投资￥<font color='#Fc291d'>" + etTouZi.getText().toString() + "</font>元", 0);
                    withdrawlDialog.show();
                    withdrawlDialog.setTrueButton(new TrueButton() {
                        @Override
                        public void getText(String text) {//优惠券id，红包id，投资金额,投资金额-红包金额，
                            if (redId != 0 && couId != 0) {
                                new HttpHelper(TouziActivity.this).surePay(id, couId, redId, etTouZi.getText().toString(), "" + (Integer.parseInt(etTouZi.getText().toString()) - mRedMoney), text).subscribe(new Action1<SurePayResponse>() {
                                    @Override
                                    public void call(SurePayResponse surePayResponse) {
                                        touZiSuccess(surePayResponse);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        if (throwable instanceof RequestErrorThrowable) {
                                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                                        }
                                    }
                                });
                            } else if (redId != 0 && couId == 0) {
                                new HttpHelper(TouziActivity.this).surePayNoCouple(id, redId, etTouZi.getText().toString(), "" + (Integer.parseInt(etTouZi.getText().toString()) - mRedMoney), text).subscribe(new Action1<SurePayResponse>() {
                                    @Override
                                    public void call(SurePayResponse surePayResponse) {
                                        touZiSuccess(surePayResponse);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        if (throwable instanceof RequestErrorThrowable) {
                                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                                        }
                                    }
                                });
                            } else if (redId == 0 && couId != 0) {
                                new HttpHelper(TouziActivity.this).surePayNoRed(id, couId, etTouZi.getText().toString(), etTouZi.getText().toString(), text).subscribe(new Action1<SurePayResponse>() {
                                    @Override
                                    public void call(SurePayResponse surePayResponse) {
                                        touZiSuccess(surePayResponse);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        if (throwable instanceof RequestErrorThrowable) {
                                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                                        }
                                    }
                                });
                            } else {
                                new HttpHelper(TouziActivity.this).surePayAllNo(id, etTouZi.getText().toString(), etTouZi.getText().toString(), text).subscribe(new Action1<SurePayResponse>() {
                                    @Override
                                    public void call(SurePayResponse surePayResponse) {
                                        touZiSuccess(surePayResponse);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        if (throwable instanceof RequestErrorThrowable) {
                                            RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                            ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                                        }
                                    }
                                });
                            }


                        }
                    });
                }
                break;
        }
    }

    private void touZiSuccess(SurePayResponse surePayResponse) {
        Intent intent = new Intent(TouziActivity.this, TouziSuccessActivity.class);
        intent.putExtra("body", surePayResponse);
        startActivity(intent);
        setResult(10021);
        Bus.getDefault().post("PAY_SUCCESS");
        finish();
    }

    private void ShowPop2() {//红包
        new HttpHelper(TouziActivity.this).getProductRedPack(0).subscribe(new Action1<GetProductRedPackResponse>() {
            @Override
            public void call(GetProductRedPackResponse getProductRedPackResponse) {
                mHongbaoList = getProductRedPackResponse.result.getBonusesByType();
                if (mHongbaoList.size() > 0) {
                    tvRedPac.setText("未使用红包");
                    myPopupWindow = new MyPopupWindow(TouziActivity.this, mHongbaoList, "红包", etTouZi.getText().toString());
                    myPopupWindow.setOnSelectItemListener(new OnSelectItemListener() {
                        @Override
                        public void selectItem(String name, int type) {

                            switch (type) {
                                case MyPopupWindow.POP_WINDOW_ITEM_1:
                                    if (TextUtils.isEmpty(name)) {
                                        tvRedPac.setText("");
                                        mRedMoney = 0;
                                    } else {
                                        tvRedPac.setText("新手好礼 -" + ((int) Double.parseDouble(name)) + "元红包");
                                        mRedMoney = (int) Double.parseDouble(name);
                                    }
                                    break;
                                case MyPopupWindow.POP_WINDOW_ITEM_2:
                                    if (TextUtils.isEmpty(name)) {
                                        redId = 0;
                                    } else {
                                        redId = Integer.parseInt(name);
                                    }
                                    break;

                            }
                        }
                    });
                } else {
                    tvRedPac.setText("无可用红包");
                    rl_youhuiquan.setClickable(false);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private boolean checkValue() {
        if (!checkBox.isChecked()) {
            ViewHelper.showToast(this, "请阅读并同意《恒利来投资协议》");
            return false;
        }
        if (Integer.parseInt(etTouZi.getText().toString()) < startMoney) {
            ViewHelper.showToast(this, "投资金额必须大于一百元");
            return false;
        }
        if (Integer.parseInt(etTouZi.getText().toString()) % 100 != 0) {
            ViewHelper.showToast(this, "投资金额必须必须是一百元的整数倍");
            return false;
        }
        return true;
    }

    private void ShowPop() {//加息券
        new HttpHelper(this).getProductRedPack(1).subscribe(new Action1<GetProductRedPackResponse>() {
            @Override
            public void call(GetProductRedPackResponse getProductRedPackResponse) {
                mJiaxiList = getProductRedPackResponse.result.getBonusesByType();
                if (mJiaxiList.size() > 0) {
                    tv_jiaxi.setText("未使用加息券");
                    myPopupWindow = new MyPopupWindow(TouziActivity.this, mJiaxiList, "加息券", etTouZi.getText().toString());
                    myPopupWindow.setOnSelectItemListener(new OnSelectItemListener() {
                        @Override
                        public void selectItem(String name, int type) {

                            switch (type) {
                                case MyPopupWindow.POP_WINDOW_ITEM_1:
                                    if (TextUtils.isEmpty(name)) {
                                        tv_jiaxi.setText("");
                                    } else {
                                        tv_jiaxi.setText("加息" + Double.parseDouble(name) * 100 + "%");
                                    }
                                    break;
                                case MyPopupWindow.POP_WINDOW_ITEM_2:
                                    if (TextUtils.isEmpty(name)) {
                                        couId = 0;
                                    } else {
                                        couId = Integer.parseInt(name);
                                        String s1 = etTouZi.getText().toString();
                                        new HttpHelper(TouziActivity.this).getProductDetailMoney(id, Integer.parseInt(s1), couId).subscribe(new Action1<GetProductDetailMoneyResponse>() {
                                            @Override
                                            public void call(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
                                                DecimalFormat df = new DecimalFormat("0.00");
                                                tv_yuan.setText(df.format(Double.parseDouble(getProductDetailMoneyResponse.result.profit)) + "");
                                            }
                                        }, new Action1<Throwable>() {
                                            @Override
                                            public void call(Throwable throwable) {
                                                if (throwable instanceof RequestErrorThrowable) {
                                                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                                    ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                                                }
                                            }
                                        });
                                    }
                                    break;

                            }
                        }
                    });
                } else {
                    tv_jiaxi.setText("无可用加息券");
                    rl_jiaxiquan.setClickable(false);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(TouziActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });


    }

    public void setData(SureBuyResponse data) {
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        setBarTitle(data.result.getProducts().getTitle());
        if ("1".equals(data.result.getProducts().getPlstimeLimitType())) {
            tv_qixian.setText(data.result.getProducts().getPlstimeLimitValue());
            tvTimeType.setText("个月");
        } else if ("0".equals(data.result.getProducts().getPlstimeLimitType())) {
            tv_qixian.setText(data.result.getProducts().getPlstimeLimitValue());
            tvTimeType.setText("天");
        }
        tv_num.setText(data.result.getProducts().getSurplusAmount());
        DecimalFormat df = new DecimalFormat("0.00");
        tvMoney.setText(Html.fromHtml("可用余额<font color='#Fc291d'>" + cacheData.result.getAvailableAmount() + "</font>"));
        tv_shouyilv.setText(df.format(Double.parseDouble(data.result.getProducts().getMinProfit()) * 100));
        startMoney = (int) Double.parseDouble(data.result.getProducts().getInvestBegin());
        etTouZi.setHint("￥" + startMoney + "元起投，每100元累加");
    }
}
