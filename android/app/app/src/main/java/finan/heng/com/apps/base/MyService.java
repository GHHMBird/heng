package finan.heng.com.apps.base;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

@SuppressLint("Registered")
public class MyService extends Service {


    @Override
    public void onCreate() {
        Log.d("hhm", "onCreate - Thread ID = " + Thread.currentThread().getId());
        Log.d("hhm", "onCreate - 获取该进程的ID = " + android.os.Process.myPid());
        Log.d("hhm", "onCreate - 获取该线程的ID = " + android.os.Process.myTid());
        Log.d("hhm", "onCreate - 获取该进程的用户ID = " + android.os.Process.myUid());
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("hhm", "onStart - Thread ID = " + Thread.currentThread().getId());
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("hhm", "onStartCommand - Thread ID = " + Thread.currentThread().getId());

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("hhm", "onBind - Thread ID = " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("hhm", "onDestroy - Thread ID = " + Thread.currentThread().getId());
        super.onDestroy();
    }


}
