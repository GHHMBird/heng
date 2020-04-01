package finan.heng.com.apps.app.my.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.fragment.TouZiFragment;
import finan.heng.com.apps.base.BaseActivity;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:25
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiJiLuActivity extends BaseActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touzijilu);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("投资记录");
        initView();
        init();
    }

    private void init() {

    }

    private void initView() {
        tableLayout = findViewById(R.id.activity_touzi_tab);
        viewPager = findViewById(R.id.activity_touzi_viewpager);
        viewPager.setOffscreenPageLimit(3);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(new TouZiFragment(i));
        }
        ArrayList<String> title = new ArrayList<>();
        title.add("募集中");
        title.add("还款中");
        title.add("已回款");
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        tableLayout.setupWithViewPager(viewPager);
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
