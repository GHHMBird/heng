package finan.heng.com.apps.kotlin.kapp.kassets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import kotlinx.android.synthetic.main.kotlin_assetsfragment.*

@Suppress("SYNTHETIC_UNRESOLVED_WIDGET_TYPE")
/*
 * Created by hhm on 2017/6/7.
 */
class KAssetsFragment : BaseFragment() {
    override fun getScrollableView(): View {
        TODO("not implemented")
    }

    override fun refreshFinish() {

    }

    override fun refreshData() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflate = inflater!!.inflate(R.layout.kotlin_assetsfragment, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        kotlin_assetsfragment_button.setOnClickListener {
            startActivity(Intent(activity, KProductDetailActivity::class.java))
        }

        cv_countdownViewTest1.start(100000000)


    }
}