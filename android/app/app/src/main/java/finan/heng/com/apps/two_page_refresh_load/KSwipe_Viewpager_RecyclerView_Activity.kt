package finan.heng.com.apps.two_page_refresh_load

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.MotionEvent
import android.view.View
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import kotlinx.android.synthetic.main.activity_kscroll_bar.*

class KSwipe_Viewpager_RecyclerView_Activity : BaseActivity(), View.OnClickListener, ViewPager.OnPageChangeListener, KSwipe_Viewpager_Recycler_Fragment1.SetNumberListener, View.OnTouchListener, Swipe_Viewpager_Recycler_Fragment2.SetNumberListener, SwipeRefreshLayout.OnRefreshListener {

    val list = ArrayList<Fragment>()

    override fun onRefresh() {
        if (activity_kscroll_bar_viewpager.currentItem == 0) {
            (list[0] as KSwipe_Viewpager_Recycler_Fragment1).getLeftData(1)
        } else if (activity_kscroll_bar_viewpager.currentItem == 1) {
            (list[1] as Swipe_Viewpager_Recycler_Fragment2).getRightData(1)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                activity_kscroll_bar_swipe.isEnabled = false
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                activity_kscroll_bar_swipe.isEnabled = true
            }
        }
        return false
    }

    override fun setNumber(index: Int, number: Int) {
        if (index == 0) {
            activity_kscroll_bar_tv1.text = "商家介绍" + number
        } else if (index == 1) {
            activity_kscroll_bar_tv2.text = "评价" + number
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == 0) {
            activity_kscroll_left_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.color_40d859))
            activity_kscroll_right_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        } else if (position == 1) {
            activity_kscroll_right_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.color_40d859))
            activity_kscroll_left_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            activity_kscroll_bar_ic_back -> {
                finish()
            }
            activity_kscroll_left_fl -> {
                activity_kscroll_left_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
                activity_kscroll_right_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                activity_kscroll_bar_viewpager.currentItem = 0
            }
            activity_kscroll_right_fl -> {
                activity_kscroll_right_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
                activity_kscroll_left_fl.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                activity_kscroll_bar_viewpager.currentItem = 1
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kscroll_bar)
        init()
    }

    fun init() {
        activity_kscroll_bar_ic_back.setOnClickListener(this)
        activity_kscroll_left_fl.setOnClickListener(this)
        activity_kscroll_right_fl.setOnClickListener(this)

        val f1 = KSwipe_Viewpager_Recycler_Fragment1(this)
        val f2 = Swipe_Viewpager_Recycler_Fragment2(this)
        list.add(f1)
        list.add(f2)
        val mAdapter = KScrollVPAdapter(fragmentManager, list)
        activity_kscroll_bar_viewpager.adapter = mAdapter
        activity_kscroll_bar_viewpager.addOnPageChangeListener(this)
        activity_kscroll_bar_viewpager.setOnTouchListener(this)
        activity_kscroll_bar_swipe.setOnRefreshListener(this)
    }

    class KScrollVPAdapter(fm: FragmentManager, val list: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = list[position]
        override fun getCount(): Int = list.size
    }
}
