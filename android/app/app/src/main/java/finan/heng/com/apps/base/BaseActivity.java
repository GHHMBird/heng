package finan.heng.com.apps.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import finan.heng.com.apps.R;

/*
 * Created by hhm on 2016/12/23.
 */

public class BaseActivity extends AppCompatActivity {

    private TextView titleText, rightText;
    private RelativeLayout backBtn;
    private PackageManager mPm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.getDefault().register(this);
        FlymeSetStatusBarLightMode(getWindow(), true);
        MIUISetStatusBarLightMode(getWindow(), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        applyTheme();
        BaseApplication.list.add(this);
    }

    public void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        titleText = findViewById(R.id.title_text);
        rightText = findViewById(R.id.right_text);
        backBtn = findViewById(R.id.back_btn);
        View actionView = toolbar.getRootView();
        toolbar.getBackground().setAlpha(255);
        setSupportActionBar(toolbar);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setBarTitle(String title) {
        titleText.setText(title);
    }

    public View setRightText(String text) {
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(text);
        return rightText;
    }

    public void showBackBtn(boolean isShow) {
        if (isShow) {
            backBtn.setVisibility(View.VISIBLE);
        } else {
            backBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /*
     * 显示透明状态栏
     */
    protected void applyTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    public void addLoadingFragment(int viewId, String event) {
        LoadingFragment mLoadingFragment = LoadingFragment.newInstance(event);
        getFragmentManager().beginTransaction().replace(viewId, mLoadingFragment, LoadingFragment.TAG).commitAllowingStateLoss();
    }

    public void removeLoadingFragment() {
        LoadingFragment mLoadingFragment = findLoadingFragment();
        if (mLoadingFragment != null) {
            mLoadingFragment.removeSelf(getFragmentManager());
        }
    }


    public LoadingFragment findLoadingFragment() {
        Fragment fragment = getFragmentManager().findFragmentByTag(LoadingFragment.TAG);
        if (fragment != null) {
            return (LoadingFragment) fragment;
        }
        return null;
    }

    /*
     * 显示键盘
     */
    public void showkeybord(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /*
     * 隐藏键盘
     */
    public void hintkeybord(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Bus.getDefault().unregister(this);
        BaseApplication.list.remove(this);
        if (BaseApplication.list.size() == 0) {
            changeIconAndName();
        }
        super.onDestroy();
    }

    public void refreshFinish() {
    }

    private void changeIconAndName() {
        mPm = getPackageManager();

        disableComponent(BaseApplication.defaultComponentName);

        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1://周日
                ComponentName seven = new ComponentName(this, "finan.heng.com.apps.Xingqiqi");
                enableComponent(seven);
                break;
            case 2://周一
                ComponentName one = new ComponentName(this, "finan.heng.com.apps.Xingqiyi");
                enableComponent(one);
                break;
            case 3://周二
                ComponentName two = new ComponentName(this, "finan.heng.com.apps.Xingqier");
                enableComponent(two);
                break;
            case 4://周三
                ComponentName three = new ComponentName(this, "finan.heng.com.apps.Xingqisan");
                enableComponent(three);
                break;
            case 5://周四
                ComponentName four = new ComponentName(this, "finan.heng.com.apps.Xingqisi");
                enableComponent(four);
                break;
            case 6://周五
                ComponentName five = new ComponentName(this, "finan.heng.com.apps.Xingqiwu");
                enableComponent(five);
                break;
            case 7://周六
                ComponentName six = new ComponentName(this, "finan.heng.com.apps.Xingqiliu");
                enableComponent(six);
                break;
            default:
                break;
        }
    }

    private void enableComponent(ComponentName componentName) {
        int state = mPm.getComponentEnabledSetting(componentName);
        if (PackageManager.COMPONENT_ENABLED_STATE_ENABLED == state) {
            //已经启用
            return;
        }
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        int state = mPm.getComponentEnabledSetting(componentName);
        if (PackageManager.COMPONENT_ENABLED_STATE_DISABLED == state) {
            //已经禁用
            return;
        }
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
