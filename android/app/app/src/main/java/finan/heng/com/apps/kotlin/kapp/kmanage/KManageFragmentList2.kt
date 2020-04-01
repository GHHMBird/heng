package finan.heng.com.apps.kotlin.kapp.kmanage

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import finan.heng.com.apps.utils.MyLayoutManager
import kotlinx.android.synthetic.main.kotlin_managefragment2.*

/*
 * Created by hhm on 2017/6/9.
 */
class KManageFragmentList2 : BaseFragment(), View.OnClickListener {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(activity).inflate(R.layout.kotlin_managefragment2, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        kotlin_managefragment2_recyclerview.layoutManager = MyLayoutManager(activity)
        val list = arrayListOf(R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20, R.drawable.p21)
        kotlin_managefragment2_recyclerview.adapter = MyAdapter(activity, list)

        kotlin_managefragment2_btn1.setOnClickListener(this)
        kotlin_managefragment2_btn2.setOnClickListener(this)
        kotlin_managefragment2_btn3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            kotlin_managefragment2_btn1 -> {
                kotlin_managefragment2_recyclerview.layoutManager = MyLayoutManager(activity)
            }
            kotlin_managefragment2_btn2 -> {
                kotlin_managefragment2_recyclerview.layoutManager = GridLayoutManager(activity, 3)
            }
            kotlin_managefragment2_btn3 -> {
                kotlin_managefragment2_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }

    class MyAdapter(val context: Context, var list: ArrayList<Int>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.imageView!!.setImageResource(list[position])
            holder.textView!!.text = "" + position + 1
        }

        override fun getItemCount(): Int = list.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(context, R.layout.kotlin_pokemon_item, null))

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var imageView: ImageView? = null
            var textView: TextView? = null

            init {
                imageView = view.findViewById(R.id.kotlin_pokemon_img) as ImageView
                textView = view.findViewById(R.id.kotlin_pokemon_name) as TextView
            }
        }
    }
}