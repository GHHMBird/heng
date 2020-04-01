package finan.heng.com.apps.kotlin.kapp.khome

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import finan.heng.com.apps.R

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
/*
 * Created by hhm on 2017/6/8.
 */
class KHomeIconAdapter(var context: Context, var list: ArrayList<KHomeIconBean>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = View.inflate(context, R.layout.kotlin_homeiconadapter_item, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            view = convertView
        }
        Glide.with(context).load(list[position].imgUrl).into(viewHolder.iv)
        viewHolder.tv.text = list[position].name
        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

    class ViewHolder(view: View) {
        val iv = view.findViewById(R.id.kotlin_homeiconadapter_item_imageview) as ImageView
        val tv = view.findViewById(R.id.kotlin_homeiconadapter_item_textview) as TextView
    }
}