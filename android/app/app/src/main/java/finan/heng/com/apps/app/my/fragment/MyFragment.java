package finan.heng.com.apps.app.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v13.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.my.activity.AboutUsActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiListActivity;
import finan.heng.com.apps.app.my.activity.ChongZhiOrTiXianActivity;
import finan.heng.com.apps.app.my.activity.InviteFriendActivity;
import finan.heng.com.apps.app.my.activity.JiaoYiJiLuActivity;
import finan.heng.com.apps.app.my.activity.LayoutActivity;
import finan.heng.com.apps.app.my.activity.MessageCenterActivity;
import finan.heng.com.apps.app.my.activity.SettingActivity;
import finan.heng.com.apps.app.my.activity.TouZiJiLuActivity;
import finan.heng.com.apps.app.my.activity.YouHuiQuanActivity;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.gaode.MapShowActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.kotlin.KotlinMain;
import finan.heng.com.apps.model.LogOutResponse;
import finan.heng.com.apps.model.LoginResponse;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.model.MyWalletResponse;
import finan.heng.com.apps.red_pag_rain.QRedPacActivity;
import finan.heng.com.apps.red_pag_rain.WXRedPacActivity;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.two_page_refresh_load.KScrollBarLayoutActivity;
import finan.heng.com.apps.two_page_refresh_load.KScrollBarLayoutTitleStyleActivity;
import finan.heng.com.apps.two_page_refresh_load.KSwipe_Viewpager_RecyclerView_Activity;
import finan.heng.com.apps.two_page_up_down.KUpDownActivity;
import rx.functions.Action1;

/*

 * Created by Administrator on 2017/4/23.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView mGridview;
    private SimpleAdapter sim_adapter;
    private int[] icon = {R.mipmap.reccord, R.mipmap.reccord, R.mipmap.deal, R.mipmap.news, R.mipmap.invite, R.mipmap.discountcoupon, R.mipmap.help, R.mipmap.contact, R.mipmap.about, R.mipmap.kotlin, R.mipmap.gaode, R.mipmap.android_5, R.mipmap.and_scrollbarlayout, R.mipmap.timg, R.mipmap.timg, R.mipmap.timg};
    private String[] iconName = {"布局展示", "投资记录", "交易流水", "消息中心", "邀请好友", "优惠券", "帮助中心", "关于我们", "产品详情页(头布局)", "Kotlin交互", "地图展示", "产品详情页(可滑动)", "产品详情(不能滑动)", "上下分页布局", "微信红包雨", "抢红包--红包雨"};
    private List<Map<String, Object>> data_list;
    private Button mBtnChongZhi, mBtnTiXian, btnLogin;
    private ImageView mIv_setting;
    private RelativeLayout rlUnLogin, rlLogin;
    private TextView keYong, ShouYi, zongZiChan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        init();
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        MyUserInfo cacheData2 = DataCache.instance.getCacheData("heng", "MyUserInfo");
        if (cacheData == null || cacheData2 == null) {
            //没登陆
            unLoginState();
        } else {
            //登陆了
            loginState();
        }
        return view;
    }

    private void initView(View view) {
        btnLogin = view.findViewById(R.id.myfragment_bt_login);
        rlUnLogin = view.findViewById(R.id.rl_top_unlogin);
        rlLogin = view.findViewById(R.id.rl_top);
        mGridview = view.findViewById(R.id.mygridview);
        mBtnChongZhi = view.findViewById(R.id.fragment_my_chongzhi);
        mBtnTiXian = view.findViewById(R.id.fragment_my_tixian);
        mIv_setting = view.findViewById(R.id.iv_setting);
        keYong = view.findViewById(R.id.tv_jine);
        ShouYi = view.findViewById(R.id.tv_shouyi);
        zongZiChan = view.findViewById(R.id.tv_zongzichan);
        ViewCompat.setNestedScrollingEnabled(mGridview, true);
    }

    private void init() {
        data_list = new ArrayList<>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.iv_icon, R.id.tv_iconname};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item_myfragment_gridview, from, to);
        //配置适配器
        mGridview.setAdapter(sim_adapter);
        mGridview.setOnItemClickListener(this);

        mBtnChongZhi.setOnClickListener(this);
        mBtnTiXian.setOnClickListener(this);
        mIv_setting.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshFinish() {

    }

    public List<Map<String, Object>> getData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("getMyWalletFinish")) {
            loginState();
        } else if (event.equals("exit") || event.equals("return_401")) {
            unLoginState();
        }
    }

    private void loginState() {
        //这里对ui进行操作   展示登陆过的界面
        rlLogin.setVisibility(View.VISIBLE);
        rlUnLogin.setVisibility(View.GONE);
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        if (cacheData != null) {
            keYong.setText(cacheData.result.getAvailableAmount());
            ShouYi.setText(cacheData.result.getTotalInterest());
            zongZiChan.setText(cacheData.result.getAllAssets());
        }
    }


    private void unLoginState() {
        //这里对ui进行操作   没有登陆过的界面
        rlUnLogin.setVisibility(View.VISIBLE);
        rlLogin.setVisibility(View.GONE);
        MyWalletResponse cacheData = DataCache.instance.getCacheData("heng", "MyWalletResponse");
        MyUserInfo cacheData2 = DataCache.instance.getCacheData("heng", "MyUserInfo");
        LoginResponse cacheData3 = DataCache.instance.getCacheData("heng", "LoginResponse");
        if (cacheData != null || cacheData2 != null || cacheData3 != null) {
            new HttpHelper(getActivity()).logout().subscribe(new Action1<LogOutResponse>() {
                @Override
                public void call(LogOutResponse logOutResponse) {
                    DataCache.instance.clearCacheData("heng", "MyUserInfo");
                    DataCache.instance.clearCacheData("heng", "LoginResponse");
                    DataCache.instance.clearCacheData("heng", "MyWalletResponse");
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    if (throwable instanceof RequestErrorThrowable) {
                        RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }

                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        switch (v.getId()) {
            case R.id.fragment_my_chongzhi:
                if (cacheData.result.cardStatus.equals("0")) {
                    startActivity(new Intent(getActivity(), ShiMingRenZhengActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), ChongZhiListActivity.class));
                }
                break;
            case R.id.myfragment_bt_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.fragment_my_tixian://判断是否绑卡
                if (cacheData.result.cardStatus.equals("0")) {
                    startActivity(new Intent(getActivity(), ShiMingRenZhengActivity.class));
                } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                    startActivity(new Intent(getActivity(), SetJiaoYiMimaActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), ChongZhiOrTiXianActivity.class).putExtra("type", 2));
                }
                break;
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LoginResponse cacheData = DataCache.instance.getCacheData("heng", "LoginResponse");
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), LayoutActivity.class));
                break;
            case 1:
                if (cacheData == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), TouZiJiLuActivity.class));
                }
                break;
            case 2:
                if (cacheData == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), JiaoYiJiLuActivity.class));
                }
                break;
            case 3:
                if (cacheData == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MessageCenterActivity.class));
                }
                break;
            case 4:
                if (cacheData == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), InviteFriendActivity.class));
                }
                break;
            case 5:
                if (cacheData == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), YouHuiQuanActivity.class));
                }
                break;
            case 6:
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", URLHelper.getInstance().URL + "hotspot/index1h5");
                intent.putExtra("title", "帮助中心");
                startActivity(intent);
                break;
            case 7:
//                联系客服
//                GetCompanyInfoResponse cacheData1 = DataCache.instance.getCacheData("heng", "GetCompanyInfoResponse");
//                if (cacheData1 != null) {
//                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cacheData1.result.phone));
//                    startActivity(phoneIntent);
//                }
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case 8:
                startActivity(new Intent(getActivity(), KScrollBarLayoutTitleStyleActivity.class));
                break;
            case 9:
                startActivity(new Intent(getActivity(), KotlinMain.class));
                break;
            case 10:
                startActivity(new Intent(getActivity(), MapShowActivity.class));
                break;
            case 11:
                startActivity(new Intent(getActivity(), KScrollBarLayoutActivity.class));
                break;
            case 12:
                startActivity(new Intent(getActivity(), KSwipe_Viewpager_RecyclerView_Activity.class));
                break;
            case 13:
                startActivity(new Intent(getActivity(), KUpDownActivity.class));
                break;
            case 14:
                startActivity(new Intent(getActivity(), WXRedPacActivity.class));
                break;
            case 15:
                startActivity(new Intent(getActivity(), QRedPacActivity.class));
                break;
        }
    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
