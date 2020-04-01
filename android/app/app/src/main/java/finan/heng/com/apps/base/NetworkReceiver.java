package finan.heng.com.apps.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("hhm", "intent============>>>>" + intent.toString());
        //小米5splus              Intent { act=android.net.conn.CONNECTIVITY_CHANGE flg=0x4000010 (has extras) }
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //网络状态发生改变
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {

                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                boolean isWifiConn = networkInfo.isConnected();
                networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                boolean isMobileConn = networkInfo.isConnected();

                if (isWifiConn) {//WiFi链接
                    String name = networkInfo.getTypeName();
                    Log.d("hhm", "当前网络名称：" + name);
                }
                if (isMobileConn) {//流量链接
                    String name = networkInfo.getTypeName();
                    Log.d("hhm", "当前网络名称：" + name);
                }
                if (!isWifiConn && !isMobileConn) {//其他网络连接
                    Log.d("hhm", "其他网络连接");
                }

            } else {
                Log.d("hhm", "没有可用网络");
            }
        }
    }
}