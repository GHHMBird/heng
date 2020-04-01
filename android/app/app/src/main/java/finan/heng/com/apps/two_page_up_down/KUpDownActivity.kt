package finan.heng.com.apps.two_page_up_down

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.two_page_up_down_tool.SlideDetailsLayout
import kotlinx.android.synthetic.main.activity_kup_down_page.*

/*
 * Created by hhm on 2017/9/15.
 */
class KUpDownActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finan.heng.com.apps.R.layout.activity_kup_down_page)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("上下分页")
        init()
    }

    fun init() {
        activity_kup_down_page_tv1.text="向下获取更多详情"

        activity_kup_down_page_recycler2.layoutManager = LinearLayoutManager(this)

        val list = ArrayList<String>()
        (1..50).mapTo(list) { it.toString() }
        activity_kup_down_page_recycler2.adapter = MyAdapter(this, list)

        activity_kup_down_page_sdl.setOnSlideDetailsListener {
            if (it==SlideDetailsLayout.Status.OPEN) {
                activity_kup_down_page_tv1.text="现在向下拉"
            }else if (it==SlideDetailsLayout.Status.CLOSE) {
                activity_kup_down_page_tv1.text="向下获取更多详情"
            }
        }
    }

    class MyAdapter(var context: Context, var list: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun getItemCount(): Int = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(context, R.layout.item_text, null))

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv.text = list[position]
        }

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val tv = view.findViewById(R.id.item_text_tv) as TextView
        }
    }
}