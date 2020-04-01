package finan.heng.com.apps.kotlin.kapp.khome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.banner.BannerConfig
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseFragment
import finan.heng.com.apps.kotlin.kapp.kmanage.KWaterActivity
import finan.heng.com.apps.utils.DefaultTransformer
import finan.heng.com.apps.utils.GlideImageLoader
import kotlinx.android.synthetic.main.kotlin_homefragment.*


/*
 * Created by hhm on 2017/6/7.
 */
class KHomeFragment : BaseFragment() {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflate = inflater!!.inflate(R.layout.kotlin_homefragment, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        val bannerUrl: ArrayList<String> = ArrayList()
        bannerUrl.add("http://180.169.108.180:8087/gateway-web/resources/img/banner1.png")
        bannerUrl.add("http://180.169.108.180:8087/gateway-web/resources/img/banner2.png")
        bannerUrl.add("http://180.169.108.180:8087/gateway-web/resources/img/banner3.png")
        kotlin_homefragment_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//banner样式
        kotlin_homefragment_banner.setImageLoader(GlideImageLoader())//图片加载器
        kotlin_homefragment_banner.setImages(bannerUrl)
        kotlin_homefragment_banner.setBannerAnimation(DefaultTransformer::class.java)
        kotlin_homefragment_banner.isAutoPlay(true)
        kotlin_homefragment_banner.setDelayTime(2000)//设置轮播时间
        kotlin_homefragment_banner.setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置（当banner模式中有指示器时）
        kotlin_homefragment_banner.start()

        val textList: ArrayList<String> = ArrayList()
        textList.add("hhm万岁")
        textList.add("hhm大天才")
        kotlin_homefragment_scolltextview.setData(textList)

        val iconList = ArrayList<KHomeIconBean>()

        val bean1 = KHomeIconBean()
        bean1.imgUrl = "http://180.169.108.180:8087/gateway-web/resources/img/shouye_icon1.png"
        bean1.name = "Icon1"
        val bean2 = KHomeIconBean()
        bean2.imgUrl = "http://180.169.108.180:8087/gateway-web/resources/img/shouye_icon2.png"
        bean2.name = "Icon2"
        val bean3 = KHomeIconBean()
        bean3.imgUrl = "http://180.169.108.180:8087/gateway-web/resources/img/shouye_icon3.png"
        bean3.name = "Icon3"
        val bean4 = KHomeIconBean()
        bean4.imgUrl = "http://180.169.108.180:8087/gateway-web/resources/img/shouye_icon4.png"
        bean4.name = "Icon4"

        iconList.add(bean1)
        iconList.add(bean2)
        iconList.add(bean3)
        iconList.add(bean4)

        val adapter = KHomeIconAdapter(activity, iconList)
        kotlin_homefragment_gridview.adapter = adapter

        kotlin_homefragment_btn.setOnClickListener {
            startActivity(Intent(activity, KWaterActivity::class.java))
        }
    }
}