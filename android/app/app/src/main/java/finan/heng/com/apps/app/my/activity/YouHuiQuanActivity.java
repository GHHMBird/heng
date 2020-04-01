package finan.heng.com.apps.app.my.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.my.fragment.JiaXiQuanFragment;
import finan.heng.com.apps.app.my.fragment.XianJinHongBaoFragment;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;

/**
 * Created by Administrator on 2017/5/5.
 */
public class YouHuiQuanActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private TextView mTv_guizeshuoming;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_youhuiquan);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("优惠券");

        initView();
        init();
    }

    private void init() {

    }

    private void initView() {

        mTv_guizeshuoming = findViewById(R.id.right_text);
        mTv_guizeshuoming.setVisibility(View.VISIBLE);
        mTv_guizeshuoming.setOnClickListener(this);

        tableLayout = findViewById(R.id.activity_touzi_tab);
        viewPager = findViewById(R.id.activity_touzi_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new XianJinHongBaoFragment(0));
        fragments.add(new JiaXiQuanFragment(1));

        ArrayList<String> title = new ArrayList<>();
        title.add("现金红包");
        title.add("加息券");
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        tableLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text://
                Intent intent2 = new Intent(YouHuiQuanActivity.this, WebActivity.class);
                intent2.putExtra("title", "红包奖励规则");
                intent2.putExtra("url", URLHelper.getInstance().URL + "hotspot/ hbgz");
                startActivity(intent2);
                break;
        }

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;
        private ArrayList<String>   title;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<String> title, ArrayList<Fragment> list) {
            super(fm);
            this.title = title;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
