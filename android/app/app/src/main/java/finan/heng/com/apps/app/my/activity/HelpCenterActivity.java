package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/4.
 */

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class HelpCenterActivity extends BaseActivity {

    private ImageView ivOne, ivTwo, ivThree, ivFour, ivFive, ivSix, ivSeven, ivEight;
    private LinearLayout llOne, llTwo, llThree, llFour, llFive, llSix, llSeven, llEight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("帮助中心");
        initView();
        init();
    }

    private void init() {

    }

    private void initView() {
        llOne = findViewById(R.id.activity_help_center_ll_one);
        ivOne = findViewById(R.id.activity_help_center_iv_one);
        llTwo = findViewById(R.id.activity_help_center_ll_two);
        ivTwo = findViewById(R.id.activity_help_center_iv_two);
        llThree = findViewById(R.id.activity_help_center_ll_three);
        ivThree = findViewById(R.id.activity_help_center_iv_three);
        llFour = findViewById(R.id.activity_help_center_ll_four);
        ivFour = findViewById(R.id.activity_help_center_iv_four);
        llFive = findViewById(R.id.activity_help_center_ll_five);
        ivFive = findViewById(R.id.activity_help_center_iv_five);
        llSix = findViewById(R.id.activity_help_center_ll_six);
        ivSix = findViewById(R.id.activity_help_center_iv_six);
        llSeven = findViewById(R.id.activity_help_center_ll_seven);
        ivSeven = findViewById(R.id.activity_help_center_iv_seven);
        llEight = findViewById(R.id.activity_help_center_ll_eight);
        ivEight = findViewById(R.id.activity_help_center_iv_eight);
    }

}
