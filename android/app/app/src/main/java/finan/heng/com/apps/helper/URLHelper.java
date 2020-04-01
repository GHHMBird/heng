package finan.heng.com.apps.helper;

import android.text.TextUtils;

import finan.heng.com.apps.http.BusinessEnum;

/*
 * Created by lfu on 2017/2/21.
 */

public class URLHelper {

//      public String URL = "http://192.168.1.203:8080/landscape-api/";//
    //public String URL = "http://api.hengll.com/";
//public String URL = "http://192.168.1.204:8080/landscape-api/";
    public String URL = "http://api.hengll.com/";
//    public String URL = "http://192.168.1.205:8080/landscape-api/";
//    public String URL = "http://1673m8b814.51mypc.cn/landscape-api/";

    public String getRequestUrl(BusinessEnum model, String methodName) {
        if (TextUtils.isEmpty(methodName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        switch (model) {
            case BUSINESS_USER:
                sb.append("user");
                break;
        }
        sb.append(methodName);
        return sb.toString();
    }

    public static URLHelper getInstance() {
        return Singleton.instance;
    }

    private static final class Singleton {
        public static final URLHelper instance = new URLHelper();
    }

}
