package finan.heng.com.apps.kotlin.kapp.kmy

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import finan.heng.com.apps.R
import finan.heng.com.apps.base.BaseActivity
import kotlinx.android.synthetic.main.kotlin_activity_pic_show.*

/*
 * Created by hhm on 2017/6/20.
 */
class KPicShowActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity_pic_show)
        setUpToolbar()
        supportActionBar!!.title = ""
        setBarTitle("头像")
        init()
    }

    private fun init() {
        val select = intent.getIntExtra("select", -1)
        when (select) {
            -1, 101 -> {
                (kotlin_activity_pic_show_imageview as ImageView).setImageResource(intent.getIntExtra("id", R.mipmap.ic_user_photo))
            }
            0, 1 -> {
                (kotlin_activity_pic_show_imageview as ImageView).setImageBitmap(intent.getParcelableExtra("bitmap"))
            }
        }

        val resId = intent.getIntExtra("resId", 0)
        if (resId == 0) {
            return
        }
        Glide.with(this).load(resId).into(kotlin_activity_pic_show_imageview)
    }
}