package finan.heng.com.apps;
/*
 * Created by hhm on 2017/5/26.
 */

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.util.Calendar;

import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.base.BaseApplication;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_welcome);

        ImageView imageView = findViewById(R.id.welcome_imageview);
        imageView.setImageResource(R.mipmap.welcome);

        Log.d("hhm", "default" + getComponentName());
        BaseApplication.defaultComponentName = getComponentName();

        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    };
}
