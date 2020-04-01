package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/4/27.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcxiaoke.bus.Bus;

import java.text.SimpleDateFormat;
import java.util.Date;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class ChongZhiOrTiXianSuccessActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvMoney, mTvBank, tvOne, tvThree, tvTwo;
    private ImageView mIvBank, mIvOne, mIvTwo, mIvThree;
    private View lineOne, lineTwo;
    private Button         btn;
    private RelativeLayout rlFinish;
    private int            type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi_success);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            setBarTitle("充值成功");
        } else if (type == 2) {
            setBarTitle("提现申请");
        }
        initView();
        init();
    }

    private void init() {
        rlFinish.setOnClickListener(this);
        btn.setOnClickListener(this);
        initData();
    }

    private void initData() {
        if (type == 1) {
            Glide.with(this).load(getIntent().getStringExtra("iconUrl")).into(mIvBank);
            mTvMoney.setText(getIntent().getStringExtra("amount") + "元");
            mTvBank.setText("尾号" + getIntent().getStringExtra("bankCode"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(new Date());
            Date date = new Date();
            tvOne.setText(format);
            tvTwo.setText(format);
            tvThree.setText( format);

            mIvOne.setImageResource(R.mipmap.select);
            mIvTwo.setImageResource(R.mipmap.select);
            mIvThree.setImageResource(R.mipmap.select);
            lineOne.setBackgroundColor(Color.parseColor("#40d859"));
            lineTwo.setBackgroundColor(Color.parseColor("#40d859"));
        } else if (type == 2) {
            Glide.with(this).load(getIntent().getStringExtra("iconUrl")).into(mIvBank);
            mTvMoney.setText(getIntent().getStringExtra("amount") + "元");
            mTvBank.setText("尾号" + getIntent().getStringExtra("bankCode"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(new Date());
            Date date = new Date();
            long time = date.getTime();
            time = time + 1000 * 60 * 60 * 24;
            date.setTime(time);
            String format1 = sdf.format(date);
            tvOne.setText(format);
            tvTwo.setText(format);
            tvThree.setText("预计到账时间：" + format1);

            mIvOne.setImageResource(R.mipmap.select);
            mIvTwo.setImageResource(R.mipmap.select);
            mIvThree.setImageResource(R.mipmap.unselect);
            lineOne.setBackgroundColor(Color.parseColor("#40d859"));
            lineTwo.setBackgroundColor(Color.parseColor("#999999"));
        }

        Bus.getDefault().post("PAY_SUCCESS");
    }

    private void initView() {
        mTvMoney = findViewById(R.id.chongzhi_success_money);
        mTvBank = findViewById(R.id.chongzhi_success_bank);
        mIvBank = findViewById(R.id.chongzhi_success_bankicon);
        rlFinish = findViewById(R.id.menu_layout);

        //图表显示进度
        mIvOne = findViewById(R.id.iv_step_one);
        mIvTwo = findViewById(R.id.iv_step_two);
        mIvThree = findViewById(R.id.iv_step_three);
        lineOne = findViewById(R.id.view_line_one);
        lineTwo = findViewById(R.id.view_line_two);

        tvThree = findViewById(R.id.chongzhi_success_tv_three);
        tvTwo = findViewById(R.id.chongzhi_success_tv_two);
        tvOne = findViewById(R.id.chongzhi_success_tv_one);

        btn = findViewById(R.id.activity_chongzhi_success_btn);

        if (type == 1) {
            rlFinish.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.activity_chongzhi_success_one)).setText("充值金额");
            ((TextView) findViewById(R.id.acticity_chongzhi_success_two)).setText("充值银行卡");
            ((TextView) findViewById(R.id.activity_chongzhi_success_three)).setText("余额充值");
            ((TextView) findViewById(R.id.activity_chongzhi_four)).setText("充值成功");
            btn.setText("去赚钱");
        } else if (type == 2) {
            rlFinish.setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.activity_chongzhi_success_one)).setText("提现金额");
            ((TextView) findViewById(R.id.acticity_chongzhi_success_two)).setText("提现至银行卡");
            ((TextView) findViewById(R.id.activity_chongzhi_success_three)).setText("申请提现");
            ((TextView) findViewById(R.id.activity_chongzhi_four)).setText("提现成功");
            btn.setText("完成");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_layout:
                finish();
                break;
            case R.id.activity_chongzhi_success_btn://去赚钱
                finish();
                break;
        }
    }
}
