package finan.heng.com.apps.two_page_refresh_load

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import finan.heng.com.apps.base.BaseFragment
import finan.heng.com.apps.kotlin.kapp.kmy.KPicShowActivity
import kotlinx.android.synthetic.main.fragment_swipe_viewpager_recycler_fragment1.*

/*
 * Created by hhm on 2017/8/15.
 */
class KScrollBarLayoutFragment1(val listener: SetNumberListener) : BaseFragment() {
    override fun getScrollableView(): View = fragment_swipe_viewpager_recycler_fragment1_recycler

    override fun refreshData() {
        page = 1
        getLeftData(page)
    }

    override fun refreshFinish() {
        (activity as BaseActivity).refreshFinish()
    }

    var page = 1
    lateinit var leftAdapter: BaseQuickAdapter<Int, BaseViewHolder>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflate = inflater!!.inflate(R.layout.fragment_swipe_viewpager_recycler_fragment1, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        fragment_swipe_viewpager_recycler_fragment1_recycler.layoutManager = GridLayoutManager(activity, 3)
        leftAdapter = LeftAdapter(activity, ArrayList<Int>())
        fragment_swipe_viewpager_recycler_fragment1_recycler.adapter = leftAdapter
        leftAdapter.setOnLoadMoreListener({
            page++
            getLeftData(page)
        }, fragment_swipe_viewpager_recycler_fragment1_recycler)
        getLeftData(page)
    }

    fun getLeftData(page: Int) {
        if (page == 4) {
            leftAdapter.loadMoreEnd()
            listener.setNumber(0, leftAdapter.itemCount - 1)
        } else if (page == 1) {
            refreshFinish()
            val list = ArrayList<Int>()
            list.add(R.mipmap.g1)
            list.add(R.mipmap.g2)
            list.add(R.mipmap.g3)
            list.add(R.mipmap.g4)
            list.add(R.mipmap.g5)
            list.add(R.mipmap.g6)
            list.add(R.mipmap.g7)
            list.add(R.mipmap.g8)
            list.add(R.mipmap.g9)
            list.add(R.mipmap.g10)
            leftAdapter.setNewData(list)
            listener.setNumber(0, leftAdapter.itemCount - 1)
        } else if (page == 2) {
            leftAdapter.loadMoreEnd()
            listener.setNumber(0, leftAdapter.itemCount - 1)
//            Handler().postDelayed({
//                leftAdapter.loadMoreComplete()
//                val list = ArrayList<Int>()
//                list.add(R.mipmap.g11)
//                list.add(R.mipmap.g12)
//                list.add(R.mipmap.g13)
//                list.add(R.mipmap.g14)
//                list.add(R.mipmap.g15)
//                list.add(R.mipmap.g16)
//                list.add(R.mipmap.g17)
//                list.add(R.mipmap.g18)
//                list.add(R.mipmap.g19)
//                list.add(R.mipmap.g20)
//                leftAdapter.addData(list)
//                listener.setNumber(0, leftAdapter.itemCount - 1)
//            },2000)
        } else if (page == 3) {
            Handler().postDelayed({
                leftAdapter.loadMoreComplete()
                val list = ArrayList<Int>()
                list.add(R.mipmap.g21)
                list.add(R.mipmap.g22)
                list.add(R.mipmap.g23)
                list.add(R.mipmap.g24)
                list.add(R.mipmap.g25)
                list.add(R.mipmap.g26)
                list.add(R.mipmap.g27)
                list.add(R.mipmap.g28)
                list.add(R.mipmap.g29)
                list.add(R.mipmap.g30)
                leftAdapter.addData(list)
                listener.setNumber(0, leftAdapter.itemCount - 1)
            },2000)
        }
    }

    class LeftAdapter(val context: Activity, data: ArrayList<Int>) : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_image, data) {
        override fun convert(helper: BaseViewHolder?, item: Int) {
            Glide.with(context).load(item).into(helper!!.getView(R.id.item_image_iv))
            helper.getView<View>(R.id.item_image_iv).setOnClickListener {
                val conpat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, helper.getView<View>(R.id.item_image_iv), "KSwipe_Viewpager_Recycler_Fragment1_item_image_iv")
                context.startActivity(Intent(context, KPicAnimationActivity::class.java).putExtra("resId", item), conpat.toBundle())
            }
            helper.getView<View>(R.id.item_image_iv).setOnLongClickListener {
                val conpat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, helper.getView<View>(R.id.item_image_iv), "KSwipe_Viewpager_Recycler_Fragment1_item_image_iv")
                context.startActivity(Intent(context, KPicShowActivity::class.java).putExtra("resId", item), conpat.toBundle())
                 false
            }
        }
    }

    interface SetNumberListener {
        fun setNumber(index: Int, number: Int)
    }
}