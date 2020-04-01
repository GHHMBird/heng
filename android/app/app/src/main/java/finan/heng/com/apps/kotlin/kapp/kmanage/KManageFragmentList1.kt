package finan.heng.com.apps.kotlin.kapp.kmanage

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import finan.heng.com.apps.utils.MyLayoutManager
import kotlinx.android.synthetic.main.kotlin_managefragment1.*

/*
 * Created by hhm on 2017/6/9.
 */
class KManageFragmentList1 : BaseFragment() {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(activity).inflate(R.layout.kotlin_managefragment1, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        val llManager = MyLayoutManager(activity)
        kotlin_managefragment1_recyclerview.layoutManager = llManager

        val list: ArrayList<KManageFragment1AdapterBean> = ArrayList()
        val bean = KManageFragment1AdapterBean()
        bean.peogress = 10
        bean.rate = "7.00%"
        bean.time = "100天"
        bean.title = "测试"
        list.add(bean)

        val kadapter = KManageFragment1Adapter(activity, list)
        kotlin_managefragment1_recyclerview.adapter = kadapter

        kotlin_managefragment1_pull.setOnRefreshListener {
            Handler().postDelayed({
                Log.e("hhm", "refresh")
                kotlin_managefragment1_pull.setRefreshing(false)
            }, 1000)
        }
    }
}