package finan.heng.com.apps.kotlin.kapp.kmanage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import finan.heng.com.apps.R

/*
 * Created by hhm on 2017/6/9.
 */
class KManageFragment1Adapter(var context: Context, var list: ArrayList<KManageFragment1AdapterBean>) : Adapter<KManageFragment1Adapter.MyViewHolder>() {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title
        holder.tvRate.text = list[position].rate
        holder.tvTime.text = list[position].time
        holder.tvProgress.max = 100
        holder.tvProgress.progress = list[position].peogress!!
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(context, R.layout.kotlin_managefragment1adapter_item, null))

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.kotlin_managefragment1adapter_item_title) as TextView
        val tvRate: TextView = view.findViewById(R.id.kotlin_managefragment1adapter_item_rate) as TextView
        val tvTime: TextView = view.findViewById(R.id.kotlin_managefragment1adapter_item_time) as TextView
        val tvProgress = view.findViewById(R.id.kotlin_managefragment1adapter_item_progress) as ProgressBar
    }
}