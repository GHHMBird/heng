package finan.heng.com.apps.kotlin.kapp.kmanage

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.utils.MyLayoutManager
import kotlinx.android.synthetic.main.activity_kwateractivity.*

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
/*
 * Created by hhm on 2017/6/21.
 */
class KWaterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kwateractivity)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("安全卫士")
        init()
    }

    fun init() {
        kotlin_activity_kwateractivity_recyclerview.layoutManager = MyLayoutManager(this)
        val list = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16")
        kotlin_activity_kwateractivity_recyclerview.adapter = MyAdapter(this, list)
        val adapter = ListAdapter(this, list)
        kotlin_activity_kwateractivity_listview.adapter = adapter
        val headView = TextView(this)
        headView.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 500)
        headView.text = "HEAD_VIEW"
        headView.gravity = Gravity.CENTER
        kotlin_activity_kwateractivity_listview.addHeaderView(headView)
//        kotlin_activity_kwateractivity_listview.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 800)
//        kotlin_activity_kwateractivity_scrollview.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 800)
    }

    class MyAdapter(val context: Context, var list: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.iv!!.setImageResource(R.mipmap.arrow_right)
            holder.tv!!.text = list[position]
        }

        override fun getItemCount(): Int = list.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(context, R.layout.kwater_adapter_item, null))

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            var iv: ImageView? = null
            var tv: TextView? = null

            init {
                iv = view.findViewById(R.id.kwater_adapter_item_iv) as ImageView
                tv = view.findViewById(R.id.kwater_adapter_item_tv) as TextView
            }
        }
    }

    class ListAdapter(val context: Context, var list: ArrayList<String>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var viewholder: ViewHolder? = null
            var view = convertView
            if (view == null) {
                view = View.inflate(context, R.layout.kwater_adapter_item, null)
                viewholder = ViewHolder(view)
                view.tag = viewholder
            } else {
                viewholder = view.tag as ViewHolder
            }
            viewholder.iv!!.setImageResource(R.mipmap.arrow_right)
            viewholder.tv!!.text = list[position]
            return view!!
        }

        override fun getItem(position: Int): Any = list[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getCount(): Int = list.size

        class ViewHolder(val view: View) {
            var iv: ImageView? = null
            var tv: TextView? = null

            init {
                iv = view.findViewById(R.id.kwater_adapter_item_iv) as ImageView
                tv = view.findViewById(R.id.kwater_adapter_item_tv) as TextView
            }
        }
    }
}