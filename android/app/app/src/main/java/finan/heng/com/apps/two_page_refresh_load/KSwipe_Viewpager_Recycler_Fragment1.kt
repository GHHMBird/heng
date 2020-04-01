package finan.heng.com.apps.two_page_refresh_load

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import kotlinx.android.synthetic.main.activity_kscroll_bar.*
import kotlinx.android.synthetic.main.fragment_swipe_viewpager_recycler_fragment1.*

/*
 * Created by hhm on 2017/8/15.
 */
class KSwipe_Viewpager_Recycler_Fragment1(val listener: SetNumberListener) : BaseFragment() {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {

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
        this.page = page
        (activity as KSwipe_Viewpager_RecyclerView_Activity).activity_kscroll_bar_swipe.isRefreshing = false
        if (page == 4) {
            leftAdapter.loadMoreEnd()
        } else if (page == 1) {
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
        } else if (page == 2) {
            leftAdapter.loadMoreComplete()
            val list = ArrayList<Int>()
            list.add(R.mipmap.g11)
            list.add(R.mipmap.g12)
            list.add(R.mipmap.g13)
            list.add(R.mipmap.g14)
            list.add(R.mipmap.g15)
            list.add(R.mipmap.g16)
            list.add(R.mipmap.g17)
            list.add(R.mipmap.g18)
            list.add(R.mipmap.g19)
            list.add(R.mipmap.g20)
            list.add(R.mipmap.g11)
            list.add(R.mipmap.g12)
            list.add(R.mipmap.g13)
            list.add(R.mipmap.g14)
            list.add(R.mipmap.g15)
            list.add(R.mipmap.g16)
            list.add(R.mipmap.g17)
            list.add(R.mipmap.g18)
            list.add(R.mipmap.g19)
            list.add(R.mipmap.g20)
            leftAdapter.addData(list)
        } else if (page == 3) {
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
        }
        listener.setNumber(0, leftAdapter.itemCount - 1)
    }

    class LeftAdapter(val context: Activity, data: ArrayList<Int>) : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_image, data) {
        override fun convert(helper: BaseViewHolder?, item: Int) {
            Glide.with(context).load(item).into(helper!!.getView(R.id.item_image_iv))
            helper.getView<View>(R.id.item_image_iv).setOnClickListener {
                val conpat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, helper.getView<View>(R.id.item_image_iv), "KSwipe_Viewpager_Recycler_Fragment1_item_image_iv")
                context.startActivity(Intent(context, KPicAnimationActivity::class.java).putExtra("resId",item),conpat.toBundle())
            }
        }
    }

    interface SetNumberListener {
        fun setNumber(index: Int, number: Int)
    }
}