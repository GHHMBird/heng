package finan.heng.com.apps.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

import finan.heng.com.apps.save.DataCache;


/*
 * Created by hhm on 2016/12/22.
 */

public class BaseApplication extends MultiDexApplication {

    public static BaseApplication instance;
    public Context applicationContext;
    public static ComponentName defaultComponentName;
    public static ArrayList<Activity> list = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initImageLoader(getApplicationContext());
    }

    private void init() {
        applicationContext = this;
        instance = this;
        DataCache.instance.init(BaseApplication.this);
        DataCache.instance.clearCacheData("haili", "BankListResponse");
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                context);
        config.threadPoolSize(3);
        config.memoryCacheExtraOptions(480, 640);// 限制缓存图片大小为640*480
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheExtraOptions(480, 640, null);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static BaseApplication getApplication() {
        return instance;
    }

    /*
     * 恒利来

     南洋联合信息科技有限公司（以下简称：南洋联合），公司注册于上海自贸区，注册资金为一亿元人民币。
     “南洋联合”是由海内外金融及实业界精英联合创建，专注上海“长三角优质存量资产项目”的并购、投资、处置、租赁以及物业管理。

     个推
     个推账号：HengYouCai  密码：HengYouCai123     应用标识：com.hengll.henglilai

     shareSDK
     shareSDK账号:yrxiang@126.com
     shareSDK密码：HengYouCai123
     iOS：appKey：1e051b164d8c4  appSecret：f957415a8a9868d1bf854dbc5ceafd54
     安卓：appKey：1e0520b264d38 appSecret：c72354b6f82dc7f46eb3695d86f75094
     QQ
     QQ开放平台账号：3323651242
     QQ开放平台密码：hzw123456
     iOS：appKey： 1106177116  appSecret：J6CIzXYAL5bdSCsb
     安卓：appKey：1106101741 appSecret：kL8XVvMP4KkNMHeT
     微信
     微信开放平台账号：yrxiang@126.com
     微信开放平台密码：HengYouCai123
     iOS和安卓：   appKey： 审核中。。。  appSecret：审核中。。。（预计5.21）


     com.hengll.henglilai
     */
}