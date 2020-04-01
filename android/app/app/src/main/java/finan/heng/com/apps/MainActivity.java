package finan.heng.com.apps;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

import finan.heng.com.apps.app.finance.fragment.FinanceFragment;
import finan.heng.com.apps.app.home.fragment.HomeFragment;
import finan.heng.com.apps.app.my.fragment.MyFragment;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.base.MyService;
import finan.heng.com.apps.base.NetworkReceiver;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llHome, llLicai, llMy;
    private ImageView ivHome, ivLicai, ivMy;
    private TextView tvMy, tvLicai, tvHome;
    private FrameLayout flMain;
    private boolean isFirst = true, isOne = true;
    private HomeFragment mHomeFragment;
    private FinanceFragment mfinanceFragment;
    private MyFragment mMyFragment;
    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_main);
        initView();
        setSelect(0);

        //注册一个广播
        registerMyReceiver();

        Log.d("hhm", "onCreate - 获取该进程的ID = " + android.os.Process.myPid());
        Log.d("hhm", "onCreate - 获取该线程的ID = " + android.os.Process.myTid());
        Log.d("hhm", "onCreate - 获取该进程的用户ID = " + android.os.Process.myUid());

        //启动一个服务
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    private void initView() {
        llHome = findViewById(R.id.main_home_ll);
        ivHome = findViewById(R.id.main_home_iv);
        tvHome = findViewById(R.id.main_home_tv);
        llLicai = findViewById(R.id.main_licai_ll);
        ivLicai = findViewById(R.id.main_licai_iv);
        tvLicai = findViewById(R.id.main_licai_tv);
        llMy = findViewById(R.id.main_my_ll);
        ivMy = findViewById(R.id.main_my_iv);
        tvMy = findViewById(R.id.main_my_tv);
        llHome.setOnClickListener(this);
        llLicai.setOnClickListener(this);
        llMy.setOnClickListener(this);

        flMain = findViewById(R.id.fragment_ui);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_home_ll:
                setSelect(0);
                break;
            case R.id.main_licai_ll:
                setSelect(1);
                break;
            case R.id.main_my_ll:
                setSelect(2);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        clearClick();
        switch (i) {
            case 0:
                ivHome.setImageResource(R.mipmap.homered);
                tvHome.setTextColor(getResources().getColor(R.color.text_Fc291d));
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_ui, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                if (!isOne) {
                    mHomeFragment.initUpData();
                } else {
                    isOne = false;
                }
                break;
            case 1:
                ivLicai.setImageResource(R.mipmap.managemoneyred);
                tvLicai.setTextColor(getResources().getColor(R.color.text_Fc291d));
                if (mfinanceFragment == null) {
                    mfinanceFragment = new FinanceFragment();
                    transaction.add(R.id.fragment_ui, mfinanceFragment);
                } else {
                    transaction.show(mfinanceFragment);
                }
                if (!isFirst) {
                    Bus.getDefault().post("heng_AssetFragment0");
                    Bus.getDefault().post("heng_AssetFragment1");
                    Bus.getDefault().post("heng_AssetFragment2");
                    Bus.getDefault().post("heng_AssetFragment3");
                } else {
                    isFirst = false;
                }
                break;
            case 2:
                ivMy.setImageResource(R.mipmap.personagered);
                tvMy.setTextColor(getResources().getColor(R.color.text_Fc291d));
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    transaction.add(R.id.fragment_ui, mMyFragment);
                } else {
                    transaction.show(mMyFragment);
                }
                MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                if (cacheData != null) {
                    getWallet2();
                }
                break;
        }
        transaction.commit();
    }

    public Drawable tintDrawable(int imageId, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(getResources().getDrawable(imageId).mutate());
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    private void clearClick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivHome.setImageDrawable(tintDrawable(R.mipmap.homered, ColorStateList.valueOf(Color.GRAY)));
            ivMy.setImageDrawable(tintDrawable(R.mipmap.personagered, ColorStateList.valueOf(Color.GRAY)));
            ivLicai.setImageDrawable(tintDrawable(R.mipmap.managemoneyred, ColorStateList.valueOf(Color.GRAY)));
        } else {
            ivHome.setImageResource(R.mipmap.homegray);
            ivMy.setImageResource(R.mipmap.personage2gray);
            ivLicai.setImageResource(R.mipmap.managemoney2gray);
        }
        tvHome.setTextColor(getResources().getColor(R.color.gray_969ba5));
        tvLicai.setTextColor(getResources().getColor(R.color.gray_969ba5));
        tvMy.setTextColor(getResources().getColor(R.color.gray_969ba5));
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }

        if (mfinanceFragment != null) {
            transaction.hide(mfinanceFragment);
        }

        if (mMyFragment != null) {
            transaction.hide(mMyFragment);
        }

    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("LoginSuccess")) {
            getUserInfo();
            getWallet();
        } else if ("PAY_SUCCESS".equals(event)) {
            getWallet();
        }
    }

    public void getUserInfo() {
        new HttpHelper(this).getUserInfo().subscribe(new Action1<MyUserInfo>() {
            @Override
            public void call(MyUserInfo myUserInfo) {
                DataCache.instance.saveCacheData("heng", "MyUserInfo", myUserInfo);//保存用户信息
                Bus.getDefault().post("getUserInfoDone");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void getWallet() {
        new HttpHelper(this).getMyWallet().subscribe(new Action1<MyWalletResponse>() {
            @Override
            public void call(MyWalletResponse myWalletResponse) {
                DataCache.instance.saveCacheData("heng", "MyWalletResponse", myWalletResponse);
                Bus.getDefault().post("getMyWalletFinish");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    public void getWallet2() {
        new HttpHelper(this).getMyWallet().subscribe(new Action1<MyWalletResponse>() {
            @Override
            public void call(MyWalletResponse myWalletResponse) {
                DataCache.instance.saveCacheData("heng", "MyWalletResponse", myWalletResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(MainActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void registerMyReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        registerReceiver(receiver, filter);
    }
}
