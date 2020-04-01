package finan.heng.com.apps.kotlin.kapp.kmy

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import finan.heng.com.apps.R
import finan.heng.com.apps.app.finance.activity.PicShowActivity
import finan.heng.com.apps.base.BaseFragment
import finan.heng.com.apps.kotlin.kapp.kassets.KDialogRecyclerViewAdapter
import finan.heng.com.apps.utils.MyLayoutManager
import kotlinx.android.synthetic.main.kotlin_myfragment.*

/*
 * Created by hhm on 2017/6/7.
 */
class KMyFragment : BaseFragment(), View.OnClickListener {
    override fun getScrollableView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshData() {

    }

    override fun refreshFinish() {

    }

    var nowSelect = -1
    var picId = -1
    lateinit var nowBitmap: Bitmap

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val inflate = inflater!!.inflate(R.layout.kotlin_myfragment, container, false)
        return inflate
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        my_fragment_userid.text = "hhm\n15********6"
        my_is_renzheng.text = "已认证"

        user_info_login.setOnClickListener(this)
        bank_layout.setOnClickListener(this)

        my_icon.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            val view = View.inflate(activity, R.layout.dialog_select, null)
            val mLayoutManager = MyLayoutManager(activity)
            val recycler = view.findViewById(R.id.dialog_select_recyclerview) as RecyclerView
            recycler.layoutManager = mLayoutManager
            builder.setCustomTitle(view)
            builder.setCancelable(false)
            val dialog = builder.create()
            val adapter = KDialogRecyclerViewAdapter(activity, arrayListOf("原生相机", "系统相册", "高清大图", "取消"))
            recycler.adapter = adapter
            dialog.show()
            adapter.setListener(object : KDialogRecyclerViewAdapter.OnItemClickListener {
                override fun OnItemClick(position: Int) {
                    when (position) {
                        0 -> {
                            //原生相机
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, 0)
                        }
                        1 -> {
                            //系统相册
                            val albumIntent = Intent(Intent.ACTION_PICK, null)
                            albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                            startActivityForResult(albumIntent, 1)
                        }
                        2 -> {
                            //高清大图
                            val intent = Intent(activity, KPicShowActivity::class.java)
                            when (nowSelect) {
                                -1 -> {
                                    intent.putExtra("select", nowSelect)
                                    intent.putExtra("id", R.mipmap.ic_user_photo)
                                }
                                0, 1 -> {
                                    intent.putExtra("select", nowSelect)
                                    intent.putExtra("bitmap", nowBitmap)
                                }
                                101 -> {
                                    intent.putExtra("select", nowSelect)
                                    intent.putExtra("id", picId)
                                }
                            }
                            startActivity(intent)
                        }
                    }
                    dialog.dismiss()
                }
            })
        }

        my_icon.setOnLongClickListener {
            val intent = Intent(activity, PicShowActivity::class.java)
            startActivityForResult(intent, 101)
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        when (v) {
            user_info_login -> {
                Toast.makeText(context, "aaa", Toast.LENGTH_LONG).show()
            }
            bank_layout -> {
                Toast.makeText(context, "bbb", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) { //原生相机
            if (data == null) {
                return
            }
            val extra = data.extras ?: return
            val mBitmap = extra.get("data") as Bitmap
            my_icon.setImageBitmap(mBitmap)
            nowBitmap = mBitmap
            nowSelect = 0
        }
        if (requestCode == 1) {//原生相册
            if (data == null) {
                return
            }
            val uri = data.data
            val cursor = activity.contentResolver.query(uri, null, null, null, null)
            cursor.moveToFirst()
            var imgNo = cursor.getString(0)// 图片编号
            val imgPath = cursor.getString(1)// 图片文件路径
            var imgSize = cursor.getString(2)// 图片大小
            var imgName = cursor.getString(3)// 图片文件名
            cursor.close()
            val options = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeFile(imgPath, options)
            my_icon.setImageBitmap(bitmap)
            nowBitmap = bitmap
            nowSelect = 1
        }
        if (requestCode == 101) {
            if (resultCode != 0) {
                my_icon.setImageResource(resultCode)
                picId = resultCode
                nowSelect = 101
            }
        }
    }
}