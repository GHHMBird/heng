package finan.heng.com.apps.app.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.model.SurePayResponse;

/**
 * Created by Administrator on 2017/5/3.
 */
public class TouziSuccessActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout back_btn;
    private Button         btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_touzisuccess);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("投资成功");
        initView();
    }

    private void initView() {
        back_btn = findViewById(R.id.back_btn);
        btn = findViewById(R.id.bt_touzisuccess);
        back_btn.setOnClickListener(this);
        btn.setOnClickListener(this);
        SurePayResponse body = (SurePayResponse) getIntent().getSerializableExtra("body");
        TextView tvJine = findViewById(R.id.tv_jine);
        TextView tvQiXian = findViewById(R.id.tv_qixian);
        TextView tvShouYi = findViewById(R.id.tv_zongshouyi);
        tvJine.setText(body.result.getAmount());
        if ("1".equals(body.result.getPrdtimeLimitType())) {
            tvQiXian.setText(body.result.getPlstimeLimitValue() + "个月");
        } else if ("0".equals(body.result.getPrdtimeLimitType())) {
            tvQiXian.setText(body.result.getPlstimeLimitValue() + "天");
        }
        tvShouYi.setText(body.result.getProfit());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bt_touzisuccess:
                finish();
                break;
        }
    }
}
