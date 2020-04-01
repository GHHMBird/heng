package finan.heng.com.apps.two_page_refresh_load

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity

/*
 * Created by hhm on 2017/8/4.
 */
class KPicAnimationActivity : finan.heng.com.apps.base.BaseActivity() {

    lateinit var imageView: android.widget.ImageView

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(finan.heng.com.apps.R.layout.activity_kmetral_design2)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("图片交互页")
        init()
    }

    fun init() {
        imageView = findViewById<android.widget.ImageView>(finan.heng.com.apps.R.id.activity_kmetral_design2_image_icon)
        com.bumptech.glide.Glide.with(this).load(intent.getIntExtra("resId", 0)).into(imageView)
    }
}