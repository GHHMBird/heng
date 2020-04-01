package finan.heng.com.apps.app.my.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/6.
 */
public class GongGaoDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gonggaodetail);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("公告详情");
    }
}
