package finan.heng.com.apps.kotlin

import android.os.Bundle
import android.view.View
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.kotlin.kapp.kassets.KAssetsFragment
import finan.heng.com.apps.kotlin.kapp.khome.KHomeFragment
import finan.heng.com.apps.kotlin.kapp.kmanage.KManageFragment
import finan.heng.com.apps.kotlin.kapp.kmy.KMyFragment
import kotlinx.android.synthetic.main.kotlin_main.*

/*
 * Created by hhm on 2017/6/7.
 */
class KotlinMain : BaseActivity(), View.OnClickListener {

    var homeFragment: KHomeFragment? = null//fragment1
    var manageFragment: KManageFragment? = null//fragment2
    var assetsFragment: KAssetsFragment? = null//fragment3
    var myFragment: KMyFragment? = null//fragment4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_main)
        init()
        setSelect(0)
    }

    private fun setSelect(i: Int) {
        clearClick()
        val bt = fragmentManager.beginTransaction()
        hideFragment()
        when (i) {
            0 -> {
                kotlin_main_iv_one.setImageResource(R.mipmap.tab_homepager_selected)
                kotlin_main_tv_one.setTextColor(resources.getColor(R.color.bg_mainColor))
                if (homeFragment == null) {
                    homeFragment = KHomeFragment()
                    bt.add(R.id.kotlin_main_fl, homeFragment)
                } else {
                    bt.show(homeFragment)
                }
            }
            1 -> {
                kotlin_main_iv_two.setImageResource(R.mipmap.tab_licai_selected)
                kotlin_main_tv_two.setTextColor(resources.getColor(R.color.bg_mainColor))
                if (manageFragment == null) {
                    manageFragment = KManageFragment()
                    bt.add(R.id.kotlin_main_fl, manageFragment)
                } else {
                    bt.show(manageFragment)
                }
            }
            2 -> {
                kotlin_main_iv_three.setImageResource(R.mipmap.tab_assets_selected)
                kotlin_main_tv_three.setTextColor(resources.getColor(R.color.bg_mainColor))
                if (assetsFragment == null) {
                    assetsFragment = KAssetsFragment()
                    bt.add(R.id.kotlin_main_fl, assetsFragment)
                } else {
                    bt.show(assetsFragment)
                }
            }
            3 -> {
                kotlin_main_iv_four.setImageResource(R.mipmap.tab_my_select)
                kotlin_main_tv_four.setTextColor(resources.getColor(R.color.bg_mainColor))
                if (myFragment == null) {
                    myFragment = KMyFragment()
                    bt.add(R.id.kotlin_main_fl, myFragment)
                } else {
                    bt.show(myFragment)
                }
            }
        }
        bt.commit()
    }

    private fun hideFragment() {
        val bt = fragmentManager.beginTransaction()
        if (homeFragment != null) {
            bt.hide(homeFragment)
        }
        if (manageFragment != null) {
            bt.hide(manageFragment)
        }
        if (assetsFragment != null) {
            bt.hide(assetsFragment)
        }
        if (myFragment != null) {
            bt.hide(myFragment)
        }
        bt.commit()
    }

    fun init() {
        kotlin_main_llone.setOnClickListener(this)
        kotlin_main_lltwo.setOnClickListener(this)
        kotlin_main_llthree.setOnClickListener(this)
        kotlin_main_llfour.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            kotlin_main_llone -> {
                setSelect(0)
            }
            kotlin_main_lltwo -> {
                setSelect(1)
            }
            kotlin_main_llthree -> {
                setSelect(2)
            }
            kotlin_main_llfour -> {
                setSelect(3)
            }
        }
    }

    fun clearClick() {
        kotlin_main_iv_one.setImageResource(R.mipmap.tab_homepager_normal)
        kotlin_main_iv_two.setImageResource(R.mipmap.tab_licai_normal)
        kotlin_main_iv_three.setImageResource(R.mipmap.tab_assets_normal)
        kotlin_main_iv_four.setImageResource(R.mipmap.tab_my_normal)
        kotlin_main_tv_one.setTextColor(resources.getColor(R.color.color_999999))
        kotlin_main_tv_two.setTextColor(resources.getColor(R.color.color_999999))
        kotlin_main_tv_three.setTextColor(resources.getColor(R.color.color_999999))
        kotlin_main_tv_four.setTextColor(resources.getColor(R.color.color_999999))
    }
}
