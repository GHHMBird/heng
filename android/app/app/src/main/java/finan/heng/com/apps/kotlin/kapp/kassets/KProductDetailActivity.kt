package finan.heng.com.apps.kotlin.kapp.kassets

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.kotlin.kapp.khome.KHomeFragment
import finan.heng.com.apps.kotlin.kapp.kmanage.KManageFragment
import finan.heng.com.apps.kotlin.kapp.kmy.KMyFragment
import finan.heng.com.apps.utils.MyLayoutManager
import kotlinx.android.synthetic.main.activity_kpdactivity.*
import kotlinx.android.synthetic.main.kotlin_scroll1.*
import kotlinx.android.synthetic.main.kotlin_scroll2.*

/*
 * Created by hhm on 2017/6/16.
 */
class KProductDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kpdactivity)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("kPDA")
        init()
    }

    fun init() {
        val f1 = KHomeFragment()
        val f2 = KManageFragment()
        val f3 = KMyFragment()
        val arr = arrayListOf<Fragment>(f1, f2, f3)
        kotlin_scroll2_viewpager.adapter = MyPage(fragmentManager, arr)
        kotlin_activity_kpdactivity_pulltoloadmore.topOrBottpmListener {
            level ->
            if (level == 0) {
                korlin_scroll1_text.visibility = View.GONE
                kotlin_scroll2_text.visibility = View.VISIBLE
                kotlin_scroll1_body.visibility = View.VISIBLE
                kotlin_scroll2_body.visibility = View.GONE
            } else if (level == 1) {
                korlin_scroll1_text.visibility = View.VISIBLE
                kotlin_scroll2_text.visibility = View.GONE
                kotlin_scroll1_body.visibility = View.GONE
                kotlin_scroll2_body.visibility = View.VISIBLE
            }
        }

        kotlin_scroll1_recycler.layoutManager = MyLayoutManager(this)
        val list = arrayListOf("text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text")
        kotlin_scroll1_recycler.adapter = MyAdapter(this, list)
    }

    class MyPage(fm: FragmentManager, val list: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment = list[position]
        override fun getCount(): Int = list.size
    }

    class MyAdapter(val context: Context, var list: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun getItemCount(): Int = list.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(context, R.layout.kpda_up_item, null))

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv.text = list[position] + position
        }

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            var tv: TextView

            init {
                tv = view.findViewById(R.id.kpda_up_item_texxtview) as TextView
            }
        }
    }
}