package finan.heng.com.apps.kotlin.kapp.kmanage

import android.app.Fragment
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import kotlinx.android.synthetic.main.kotlin_managefragment.*

/*
 * Created by hhm on 2017/6/7.
 */
class KManageFragment : BaseFragment(), View.OnClickListener, ViewPager.OnPageChangeListener {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflate = inflater!!.inflate(R.layout.kotlin_managefragment, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        kotlin_managefragment_tv_one.setOnClickListener(this)
        kotlin_managefragment_tv_two.setOnClickListener(this)

        val list = arrayListOf<Fragment>(KManageFragmentList1(), KManageFragmentList2())
        val kmanageAdapter = KManageAdapter(childFragmentManager, list)
        kotlin_managefragment_viewpager.adapter = kmanageAdapter
        kotlin_managefragment_viewpager.addOnPageChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            kotlin_managefragment_tv_one -> {
                setSetlect(0)
                kotlin_managefragment_viewpager.currentItem = 0
            }
            kotlin_managefragment_tv_two -> {
                setSetlect(1)
                kotlin_managefragment_viewpager.currentItem = 1
            }
        }
    }

    private fun setSetlect(type: Int) {
        if (type == 0) {
            kotlin_managefragment_tv_one.setTextColor(resources.getColor(R.color.bg_mainColor))
            kotlin_managefragment_tv_one.setBackgroundResource(R.drawable.bg_white_bo)
            kotlin_managefragment_tv_two.setTextColor(resources.getColor(R.color.white))
            kotlin_managefragment_tv_two.setBackgroundResource(R.drawable.bg_main_color_right_bo)
        } else if (type == 1) {
            kotlin_managefragment_tv_one.setTextColor(resources.getColor(R.color.white))
            kotlin_managefragment_tv_one.setBackgroundResource(R.drawable.bg_main_color_bo)
            kotlin_managefragment_tv_two.setTextColor(resources.getColor(R.color.bg_mainColor))
            kotlin_managefragment_tv_two.setBackgroundResource(R.drawable.bg_white_right_bo)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setSetlect(position)
    }
}