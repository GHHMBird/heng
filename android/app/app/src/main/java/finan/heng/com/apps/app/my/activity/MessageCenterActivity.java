package finan.heng.com.apps.app.my.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.fragment.GongGaoMessageFragment;
import finan.heng.com.apps.app.my.fragment.JiaoYiMessageFragment;
import finan.heng.com.apps.app.my.fragment.XianJinHongBaoFragment;
import finan.heng.com.apps.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/6.
 */
public class MessageCenterActivity extends BaseActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messagecenter);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("消息中心");

        initView();
    }

    private void initView() {
        tableLayout = findViewById(R.id.activity_touzi_tab);
        viewPager = findViewById(R.id.activity_touzi_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new JiaoYiMessageFragment());
        fragments.add(new GongGaoMessageFragment());

        ArrayList<String> title = new ArrayList<>();
        title.add("交易");
        title.add("公告");
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
