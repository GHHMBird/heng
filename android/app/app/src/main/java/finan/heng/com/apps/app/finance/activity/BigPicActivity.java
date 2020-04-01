package finan.heng.com.apps.app.finance.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

/*
 * created by hhm
 */
public class BigPicActivity extends BaseActivity {

    private ViewPager mViewpager;
    private int[] in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_pic);
        setUpToolbar();
        View rightView = setRightText("确认");
        getSupportActionBar().setTitle("");
        String size = getIntent().getStringExtra("size");
        String num = getIntent().getStringExtra("num");
        in = getIntent().getIntArrayExtra("list");
        setBarTitle(num + "/" + size);
        initView();
        init();
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(in[mViewpager.getCurrentItem()]);
                finish();
            }
        });
    }

    private void init() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int anIn : in) {
            integers.add(anIn);
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this, integers);
        mViewpager.setAdapter(myPagerAdapter);
        mViewpager.setCurrentItem(Integer.parseInt(getIntent().getStringExtra("num")) - 1);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBarTitle((position + 1) + "/" + getIntent().getStringExtra("size"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewpager = findViewById(R.id.viewpager);
    }

    private class MyPagerAdapter extends PagerAdapter {

        private Context context;
        private ArrayList<Integer> list;

        MyPagerAdapter(Context context, ArrayList<Integer> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(list.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
