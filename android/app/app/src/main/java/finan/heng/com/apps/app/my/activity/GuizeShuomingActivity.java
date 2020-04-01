package finan.heng.com.apps.app.my.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/5.
 */
public class GuizeShuomingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guizeshuoming);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("规则说明");
    }
}
