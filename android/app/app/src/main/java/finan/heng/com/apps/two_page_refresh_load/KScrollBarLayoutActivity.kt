package finan.heng.com.apps.two_page_refresh_load

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.base.BaseFragment
import kotlinx.android.synthetic.main.activity_kmetral_design.*

class KScrollBarLayoutActivity : BaseActivity(), View.OnClickListener, KScrollBarLayoutFragment1.SetNumberListener, KScrollBarLayoutFragment2.SetNumberListener, ViewPager.OnPageChangeListener, PtrHandler {
    override fun onRefreshBegin(frame: PtrFrameLayout?) {
        list[activity_kmetral_design_viewpager.currentItem].refreshData()
    }

    override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
        if (activity_kmetral_design_scrollbarlayout.isCanPullToRefresh) {
            return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
        }
        return false
    }

    val list = ArrayList<BaseFragment>()

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        activity_kmetral_design_scrollbarlayout.helper.setCurrentScrollableContainer(list[position])
        if (position == 0) {
            activity_kmetral_design_fl1.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
            activity_kmetral_design_fl2.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        } else if (position == 1) {
            activity_kmetral_design_fl2.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
            activity_kmetral_design_fl1.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    override fun setNumber(index: Int, number: Int) {
        if (index==0) {
            activity_kmetral_design_tv1.text = "商家介绍($number)"
        }else if (index==1) {
            activity_kmetral_design_tv2.text = "评价($number)"
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            activity_kmetral_design_fl1 -> {
                activity_kmetral_design_fl1.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
                activity_kmetral_design_fl2.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                activity_kmetral_design_viewpager.currentItem = 0
            }
            activity_kmetral_design_fl2 -> {
                activity_kmetral_design_fl2.setBackgroundColor(ContextCompat.getColor(this, R.color.color_0f90e2))
                activity_kmetral_design_fl1.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                activity_kmetral_design_viewpager.currentItem = 1
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmetral_design)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("产品详情页")
        init()
    }

    override fun refreshFinish() {
        activity_kmetral_design_ptr.refreshComplete()
    }

    private fun init() {
        activity_kmetral_design_ptr.isEnabledNextPtrAtOnce = true
        activity_kmetral_design_ptr.setLastUpdateTimeRelateObject(this)
        activity_kmetral_design_ptr.setPtrHandler(this)
        activity_kmetral_design_ptr.isKeepHeaderWhenRefresh = true

        activity_kmetral_design_fl1.setOnClickListener(this)
        activity_kmetral_design_fl2.setOnClickListener(this)

        list.add(KScrollBarLayoutFragment1(this))
        list.add(KScrollBarLayoutFragment2(this))
        activity_kmetral_design_viewpager.adapter = MyAdapter(fragmentManager, list)

        activity_kmetral_design_scrollbarlayout.helper.setCurrentScrollableContainer(list[0])

        activity_kmetral_design_viewpager.addOnPageChangeListener(this)
    }

    class MyAdapter(fm: android.app.FragmentManager, val list: ArrayList<BaseFragment>) : android.support.v13.app.FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): BaseFragment = list[position]
        override fun getCount(): Int = list.size
    }
}
