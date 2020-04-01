package finan.heng.com.apps.kotlin.kapp.kassets

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import finan.heng.com.apps.R

/*
 * Created by hhm on 2017/6/15.
 */
class KDialogRecyclerViewAdapter(val content: Context, val list: ArrayList<String>) : RecyclerView.Adapter<KDialogRecyclerViewAdapter.MyViewHolder>() {

    var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textV!!.text = list[position]
        holder.view.setOnClickListener {
            if (mListener != null) {
                mListener!!.OnItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(View.inflate(content, R.layout.text_item, null))

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var textV: TextView? = null

        init {
            textV = view.findViewById(R.id.text_item_tv) as TextView
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }

    fun setListener(listener: OnItemClickListener) {
        mListener = listener
    }
}