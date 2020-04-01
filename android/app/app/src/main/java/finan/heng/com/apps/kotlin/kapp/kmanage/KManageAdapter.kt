package finan.heng.com.apps.kotlin.kapp.kmanage

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter

/*
 * Created by hhm on 2017/6/9.
 */
class KManageAdapter(fm: FragmentManager, var list: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size
}