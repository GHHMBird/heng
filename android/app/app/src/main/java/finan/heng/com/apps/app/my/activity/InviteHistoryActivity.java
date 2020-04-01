package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/5.
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
import finan.heng.com.apps.app.my.fragment.InviteFriendFinanceFragment;
import finan.heng.com.apps.app.my.fragment.InviteFriendRegisterFragment;
import finan.heng.com.apps.base.BaseActivity;

public class InviteHistoryActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_history);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("邀请记录");
        initView();
        init();
    }

    private void init() {

    }

    private void initView() {
        tabLayout = findViewById(R.id.activity_invite_history_tab);
        viewPager = findViewById(R.id.activity_invite_history_viewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new InviteFriendFinanceFragment());//已注册
        fragments.add(new InviteFriendRegisterFragment());//已投资
        ArrayList<String> title = new ArrayList<>();
        title.add("已注册");
        title.add("已投资");
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(), title, fragments);

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager加载adapter
        viewPager.setAdapter(myFragmentPagerAdapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
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
