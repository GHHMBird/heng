package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/4/25.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.fragment.AllTradeFragment;
import finan.heng.com.apps.base.BaseActivity;

public class JiaoYiJiLuActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaoyijilu);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("交易记录");
        initView();
        init();
    }

    private void init() {

    }

    private void initView() {
        tab = findViewById(R.id.activity_jiaoyi_tab);
        viewPager = findViewById(R.id.activity_jiaoyijilu_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new AllTradeFragment(0));
        fragments.add(new AllTradeFragment(1));
        fragments.add(new AllTradeFragment(2));
        fragments.add(new AllTradeFragment(3));
        fragments.add(new AllTradeFragment(4));
        fragments.add(new AllTradeFragment(5));

        ArrayList<String> title = new ArrayList<>();
        title.add("全部");
        title.add("充值");
        title.add("提现");
        title.add("投资");
        title.add("回款");
        title.add("奖励");
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tab.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        tab.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(6);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;
        private List<String> list_Title;

        public MyFragmentPagerAdapter(FragmentManager fm, List<String> list_Title, List<Fragment> list_fragment) {
            super(fm);
            this.list_Title = list_Title;
            this.list_fragment = list_fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }
    }
}
