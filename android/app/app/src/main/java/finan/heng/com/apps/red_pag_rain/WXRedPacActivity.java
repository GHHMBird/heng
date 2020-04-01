package finan.heng.com.apps.red_pag_rain;
/*
 * Created by hhm on 2017/12/19.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class WXRedPacActivity extends BaseActivity {

    private EmotionsView ev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wx_red_pack);
        showEmotionsView();
    }

    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (ev == null || ev.isEnd()) {
                return;
            }
            ev.addRandomEmotion();
            ev.invalidate();
            sleep(50);
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }



    public void update() {
        ev.setEnd(false);
        ev.clearAllEmotions();
        ev.addRandomEmotion();
        mRedrawHandler.removeMessages(0);
        mRedrawHandler.sleep(200);
    }

    private void showEmotionsView() {
        // 获得表情雨视图,加载icon到内存(在布局文件中置入自定义EmotionsView)
        ev = findViewById(R.id.emotion_view);        // 此处可实现表情图片的更替，具体判断来自发送的文本内容
        int intDrawable = R.mipmap.ic_pay_sucess;

        ev.LoadEmotionImage(intDrawable);
//        BV.invalidate();
        ev.setVisibility(View.VISIBLE);        // 获取当前屏幕的高和宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ev.setView(dm.heightPixels, dm.widthPixels);
        update();
    }
}
