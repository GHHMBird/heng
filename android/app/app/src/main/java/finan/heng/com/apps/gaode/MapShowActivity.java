package finan.heng.com.apps.gaode;
/*
 * Created by hhm on 2017/7/17.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.gaode_demo.BusRouteOverlay;
import finan.heng.com.apps.gaode_demo.DrivingRouteOverlay;
import finan.heng.com.apps.gaode_demo.PoiOverlay;
import finan.heng.com.apps.gaode_demo.RideRouteOverlay;
import finan.heng.com.apps.gaode_demo.WalkRouteOverlay;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.utils.AMapUtil;

public class MapShowActivity extends Activity implements AMap.OnMyLocationChangeListener, AMap.OnMapClickListener, LocationSource, AMap.OnPOIClickListener, AMap.OnMarkerClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener, PoiSearch.OnPoiSearchListener, GeocodeSearch.OnGeocodeSearchListener, View.OnClickListener, RouteSearch.OnRouteSearchListener {

    private MapView mapView;
    private boolean isFirst = true;
    private AMap aMap;
    private EditText etSearch, etCity, etStart, etEnd;
    private RadioGroup radioGroup;
    private ImageView ivImage;
    private RadioButton rb1, rb2, rb3, goRb1, goRb2, goRb3, goRb4, goRba, goRbb, goRbc;
    private MarkerOptions markOptiopns;
    private TextView tvSimple, tvGuide, tvNight, tvStar;
    private String cityString = "";
    private PoiSearch.Query query;
    private String nowCity;
    private PoiOverlay poiOverlay;
    private ArrayList<Marker> markerLists = new ArrayList<>();
    private boolean isDaoHang = false;
    private RelativeLayout rlSelect, rlDaoHnag;
    private Button btnSearch;
    private LinearLayout llTuCeng;
    private RouteSearch mRouteSearch;
    private GeocodeSearch geocoderSearch;
    private LatLonPoint startPlace, endPlace;
    private DrivingRouteOverlay drivingRouteOverlay;
    private boolean getFirstPlace, getEndPlace;
    private EditText etDaoHnagCity;
    private TextView tvBottoom;
    private String daoHangPlace;
    private BusRouteOverlay mBusrouteOverlay;
    private WalkRouteOverlay walkRouteOverlay;
    private RideRouteOverlay rideRouteOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);
        initView();
        mapView.onCreate(savedInstanceState);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED && permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    10);
        } else if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    10);
        } else if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    10);
        } else {
            getLocation();
        }
    }

    private void getLocation() {
        //实时定位，1秒1次
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);//设置
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        setHistoryLocation();
//        aMap.setLocationSource(this);
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setOnPOIClickListener(this);
        aMap.setOnMarkerClickListener(this);
    }

//    private void setHistoryLocation() {
//        //获取手机记录的最后地理位置并定位
//        AMapLocationClient aMapLocationClient = new AMapLocationClient(this);
//        AMapLocation location = aMapLocationClient.getLastKnownLocation();
//        if (location != null) {
//            String result = LocationStringUtils.getLocationStr(location);
//            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());//先填纬度，再填经度
//            aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 0)));//18和30控制地图的放大程度
//            aMap.addMarker(new MarkerOptions().position(latLng));
//            setText(latLng, "手机记录的最后地理位置");
//        }
//    }

//    private void setText(LatLng latLng, String text) {
//        //设置文字给与提示
//        MarkerOptions markOptiopns = new MarkerOptions();
//        markOptiopns.position(latLng);
//        final TextView textView = new TextView(this);
//        textView.setText(text);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.BLACK);
//        textView.setBackgroundResource(R.drawable.custom_info_bubble);
//        markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));
//        aMap.addMarker(markOptiopns);
//    }

    private void initView() {
        llTuCeng = findViewById(R.id.activity_gaode_ll_tuceng);
        rlSelect = findViewById(R.id.activity_gaode_select_rl);
        rlDaoHnag = findViewById(R.id.activity_gaode_daohang_rl);
        ivImage = findViewById(R.id.activity_gaode_image);
        tvSimple = findViewById(R.id.activity_gaode_tv_simple);
        tvGuide = findViewById(R.id.activity_gaode_tv_guidemap);
        tvNight = findViewById(R.id.activity_gaode_tv_nightmap);
        tvStar = findViewById(R.id.activity_gaode_tv_starmap);
        mapView = findViewById(R.id.activity_gaode_mapview);
        etSearch = findViewById(R.id.activity_gaode_et);
        etStart = findViewById(R.id.activity_daohang_top_et1);
        etEnd = findViewById(R.id.activity_daohang_top_et2);
        radioGroup = findViewById(R.id.activity_gaode_rg);
        etCity = findViewById(R.id.activity_gaode_choose_city_et);
        rb1 = findViewById(R.id.activity_gaode_rb1);
        rb2 = findViewById(R.id.activity_gaode_rb2);
        rb3 = findViewById(R.id.activity_gaode_rb3);
        btnSearch = findViewById(R.id.activity_gaode_btn);
        goRb1 = findViewById(R.id.activity_gaode_daohang_rb1);
        goRb2 = findViewById(R.id.activity_gaode_daohang_rb2);
        goRb3 = findViewById(R.id.activity_gaode_daohang_rb3);
        goRb4 = findViewById(R.id.activity_gaode_daohang_rb4);
        etDaoHnagCity = findViewById(R.id.activity_daohang_city);
        goRba = findViewById(R.id.activity_gaode_daohang_rba);
        goRbb = findViewById(R.id.activity_gaode_daohang_rbb);
        goRbc = findViewById(R.id.activity_gaode_daohang_rbc);
        tvBottoom = findViewById(R.id.activity_gaode_bottom_text);
        goRbc.setOnCheckedChangeListener(this);
        btnSearch.setOnClickListener(this);
        ivImage.setOnClickListener(this);
        etSearch.addTextChangedListener(this);
        etStart.addTextChangedListener(this);
        etEnd.addTextChangedListener(this);
        rb3.setOnCheckedChangeListener(this);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setLogoBottomMargin(-ViewHelper.dp2px(this, 14));//高德地图logo
        uiSettings.setScaleControlsEnabled(true);//比例尺是否显示
        uiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        aMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    public void simplemap(View view) {
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        tvGuide.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvNight.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvSimple.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvStar.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvBottoom.setTextColor(ContextCompat.getColor(this, R.color.black));
    }

    public void guidemap(View view) {
        aMap.setMapType(AMap.MAP_TYPE_NAVI);
        tvGuide.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvNight.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvSimple.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvStar.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvBottoom.setTextColor(ContextCompat.getColor(this, R.color.black));
    }

    public void nightmap(View view) {
        aMap.setMapType(AMap.MAP_TYPE_NIGHT);
        tvGuide.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvNight.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvSimple.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvStar.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvBottoom.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    public void starmap(View view) {
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        tvGuide.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvNight.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvSimple.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvStar.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvBottoom.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    public void search(View view) {
        if (rb1.isChecked()) {
            cityString = "";
        } else if (rb2.isChecked()) {
            if (!TextUtils.isEmpty(nowCity)) {
                cityString = nowCity;
            }
        } else if (rb3.isChecked()) {
            if (TextUtils.isEmpty(etCity.getText().toString().trim())) {
                ViewHelper.showToast(MapShowActivity.this, "请先输入指定的城市");
                return;
            } else {
                cityString = etCity.getText().toString().trim();
            }
        }
        int currentPage = 0;
        query = new PoiSearch.Query(etSearch.getText().toString().trim(), "", cityString);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    //当用户的定位改变时
    @Override
    public void onMyLocationChange(Location location) {
        if (isFirst) {//第一次进来的时候   定位
            isFirst = false;
            //先填纬度，再填经度
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 0)));//18和30控制地图的放大程度

            if (geocoderSearch == null) {
                geocoderSearch = new GeocodeSearch(this);
            }
            geocoderSearch.setOnGeocodeSearchListener(this);
            // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(location.getLatitude(), location.getLongitude()), 200, GeocodeSearch.AMAP);
            geocoderSearch.getFromLocationAsyn(query);
        }

    }

    //当在地图点击的时候
    @Override
    public void onMapClick(LatLng latLng) {
        clearMarks();
        yinCangShuRuFa();
    }

    public void yinCangShuRuFa() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0); //强制隐藏键盘
    }

    //设置了定位的监听,这里要实现LocationSource接口
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    //设置了定位的监听,这里要实现LocationSource接口
    @Override
    public void deactivate() {

    }

    //地图poi点点击
    @Override
    public void onPOIClick(Poi poi) {
        if (markOptiopns == null) {
            markOptiopns = new MarkerOptions();
        } else {
            clearMarks();
        }
        markOptiopns.position(poi.getCoordinate());
        TextView textView = new TextView(getApplicationContext());
        textView.setText(poi.getName());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.custom_info_bubble);
        markOptiopns.icon(BitmapDescriptorFactory.fromView(textView));
        Marker marker = aMap.addMarker(markOptiopns);
        marker.setObject("mark_point");
        markerLists.add(marker);
    }

    private void clearMarks() {
        for (int i = 0; i < markerLists.size(); i++) {
            Marker marker = markerLists.get(i);
            if ("mark_point".equals(marker.getObject())) {
                marker.remove();
            }
        }
        if (poiOverlay != null) {
            poiOverlay.removeFromMap();
        }
        if (drivingRouteOverlay != null) {
            drivingRouteOverlay.removeFromMap();
            tvBottoom.setText("");
        }
        if (mBusrouteOverlay != null) {
            mBusrouteOverlay.removeFromMap();
            tvBottoom.setText("");
        }
        if (walkRouteOverlay != null) {
            walkRouteOverlay.removeFromMap();
            tvBottoom.setText("");
        }
        if (rideRouteOverlay != null) {
            rideRouteOverlay.removeFromMap();
            tvBottoom.setText("");
        }
    }

    //标记点击事件
    @Override
    public boolean onMarkerClick(final Marker marker) {
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(marker.getPosition(), 18, 30, 0)));//18和30控制地图的放大程度
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String trim = etSearch.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            radioGroup.setVisibility(View.VISIBLE);
            if (rb3.isChecked()) {
                etCity.setVisibility(View.VISIBLE);
            } else {
                etCity.setVisibility(View.GONE);
            }
        } else {
            radioGroup.setVisibility(View.GONE);
            etCity.setVisibility(View.GONE);
        }
        String tStart = etStart.getText().toString().trim();
        String tEnd = etEnd.getText().toString().trim();
        if (!TextUtils.isEmpty(tStart) && !TextUtils.isEmpty(tEnd)) {
            btnSearch.setEnabled(true);
            btnSearch.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            btnSearch.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            btnSearch.setEnabled(false);
            btnSearch.setBackgroundColor(ContextCompat.getColor(this, R.color.gary));
            btnSearch.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //指定城市搜索选中时
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (rb3.isChecked()) {
            etCity.setVisibility(View.VISIBLE);
        } else {
            etCity.setVisibility(View.GONE);
        }
        if (goRbc.isChecked()) {
            etDaoHnagCity.setVisibility(View.VISIBLE);
        } else {
            etDaoHnagCity.setVisibility(View.GONE);
        }
    }

    //poi搜索回掉
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    // 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    List<SuggestionCity> suggestionCities = result.getSearchSuggestionCitys();

                    if (poiItems != null && poiItems.size() > 0) {
                        poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        clearMarks();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ViewHelper.showToast(MapShowActivity.this, "没有获取到数据");
                    }
                }
            } else {
                ViewHelper.showToast(MapShowActivity.this, "没有获取到数据");
            }
        } else {
            ViewHelper.showToast(this, "" + rCode);
        }
    }

    //poi搜索回掉
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "\n";
        }
        ViewHelper.showToast(this, "您搜索的数据在多个城市都有发现，请指定城市");
        ViewHelper.showToast(this, infomation);
    }

    //逆地理编码(经纬度转地理位置)
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null && result.getRegeocodeAddress().getFormatAddress() != null) {
                nowCity = result.getRegeocodeAddress().getCity();
            } else {
                ViewHelper.showToast(MapShowActivity.this, "未查询到所在城市");
            }
        } else {
            ViewHelper.showToast(MapShowActivity.this, "" + rCode);
        }
    }

    //地理编码(地理位置转经纬度)
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
//        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
//            if (result != null && result.getGeocodeAddressList() != null && result.getGeocodeAddressList().size() > 0) {
//                GeocodeAddress address = result.getGeocodeAddressList().get(0);
//                "经纬度值:" + address.getLatLonPoint()

//            } else {
//                ViewHelper.showToast(this, "未找到结果");
//            }
//        } else {
//            ViewHelper.showToast(this, rCode + "");
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_gaode_image:
                if (isDaoHang) {
                    ivImage.setImageResource(R.mipmap.way_one);
                    rlDaoHnag.setVisibility(View.GONE);
                    rlSelect.setVisibility(View.VISIBLE);
                    llTuCeng.setVisibility(View.VISIBLE);
                } else {
                    ivImage.setImageResource(R.mipmap.way_two);
                    rlDaoHnag.setVisibility(View.VISIBLE);
                    rlSelect.setVisibility(View.GONE);
                    llTuCeng.setVisibility(View.GONE);
                }
                isDaoHang = !isDaoHang;
                break;
            case R.id.activity_gaode_btn:
                if (mRouteSearch == null) {
                    mRouteSearch = new RouteSearch(this);
                    mRouteSearch.setRouteSearchListener(this);
                }

                String start = etStart.getText().toString().trim();
                String end = etEnd.getText().toString().trim();

                daoHangPlace = null;
                if (goRba.isChecked()) {
                    daoHangPlace = "";
                } else if (goRbb.isChecked()) {
                    daoHangPlace = TextUtils.isEmpty(nowCity) ? "" : nowCity;
                } else if (goRbc.isChecked()) {
                    daoHangPlace = etDaoHnagCity.getText().toString().trim();
                }

                getStartPlaceInformation(start);
                getEndPlaceInformation(end);
                break;
        }
    }

    private void getEndPlaceInformation(String end) {
        GeocodeQuery query = new GeocodeQuery(end, daoHangPlace == null ? "" : daoHangPlace);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        GeocodeSearch geocoderSearchStart = new GeocodeSearch(this);
        geocoderSearchStart.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {

            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult result, int rCode) {
                if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getGeocodeAddressList() != null
                            && result.getGeocodeAddressList().size() > 0) {
                        GeocodeAddress address = result.getGeocodeAddressList().get(0);

//                        aMap.addMarker(new MarkerOptions()
//                                .position(AMapUtil.convertToLatLng(address.getLatLonPoint()))
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.end)));

                        endPlace = address.getLatLonPoint();
                        getFirstPlace = true;
                        if (getEndPlace) {
                            gotoGuide();
                            getEndPlace = false;
                        }
                    } else {
                        ViewHelper.showToast(MapShowActivity.this, "未找到终点位置信息");
                    }
                } else {
                    ViewHelper.showToast(MapShowActivity.this, "" + rCode);
                }
            }
        });
        geocoderSearchStart.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }

    private void getStartPlaceInformation(String start) {
        GeocodeQuery query = new GeocodeQuery(start, daoHangPlace == null ? "" : daoHangPlace);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        GeocodeSearch geocoderSearchStart = new GeocodeSearch(this);
        geocoderSearchStart.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult result, int rCode) {
                if (rCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getGeocodeAddressList() != null
                            && result.getGeocodeAddressList().size() > 0) {
                        GeocodeAddress address = result.getGeocodeAddressList().get(0);

//                        aMap.addMarker(new MarkerOptions()
//                                .position(AMapUtil.convertToLatLng(address.getLatLonPoint()))
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.start)));

                        startPlace = address.getLatLonPoint();
                        getEndPlace = true;
                        if (getFirstPlace) {
                            gotoGuide();
                            getFirstPlace = false;
                        }
                    } else {
                        ViewHelper.showToast(MapShowActivity.this, "未找到起点位置信息");
                    }
                } else {
                    ViewHelper.showToast(MapShowActivity.this, "" + rCode);
                }
            }
        });
        geocoderSearchStart.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }

    @Override
    public void onBusRouteSearched(BusRouteResult result, int errorCode) {
        clearMarks();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {

                    mBusrouteOverlay = new BusRouteOverlay(this, aMap, result.getPaths().get(0), result.getStartPos(), result.getTargetPos());
                    mBusrouteOverlay.setNodeIconVisibility(true);//设置节点marker是否显示
                    mBusrouteOverlay.addToMap();
                    mBusrouteOverlay.zoomToSpan();

                    tvBottoom.setText(AMapUtil.getBusPathDes(result.getPaths().get(0))+"\n大约时常"+ AMapUtil.getFriendlyTime((int) result.getPaths().get(0).getDuration()));
//                    BusResultListAdapter mBusResultListAdapter = new BusResultListAdapter(this, result);
//                    mBusResultList.setAdapter(mBusResultListAdapter);

                } else if (result != null && result.getPaths() == null) {
                    ViewHelper.showToast(this, "没有找到公交车数据");
                }
            } else {
                ViewHelper.showToast(this, "没有找到公交车数据");
            }
        } else {
            ViewHelper.showToast(this, "" + errorCode);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        clearMarks();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {

                    final DrivePath drivePath = result.getPaths()
                            .get(0);
                    drivingRouteOverlay = new DrivingRouteOverlay(
                            this, aMap, drivePath,
                            startPlace,
                            endPlace, null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                    int dis = (int) drivePath.getDistance();
                    int dur = (int) drivePath.getDuration();
//                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    int taxiCost = (int) result.getTaxiCost();

                    tvBottoom.setText("当前路线大概耗时" + AMapUtil.getFriendlyTime(dur) + "\n导肮距离" + AMapUtil.getFriendlyLength(dis) + "\n打车费用约" + taxiCost + "元");

//                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext,
//                                    DriveRouteDetailActivity.class);
//                            intent.putExtra("drive_path", drivePath);
//                            intent.putExtra("drive_result", result);
//                            startActivity(intent);
//                        }
//                    });

                } else if (result != null && result.getPaths() == null) {
                    ViewHelper.showToast(this, "没找到路线数据");
                }

            } else {
                ViewHelper.showToast(this, "没找到路线数据");
            }
        } else {
            ViewHelper.showToast(this, "" + errorCode);
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        clearMarks();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    final WalkPath walkPath = result.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            this, aMap, walkPath,
                            startPlace,
                            endPlace);
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();

                    tvBottoom.setText(AMapUtil.getFriendlyTime((int) walkPath.getDistance()) + "(" + AMapUtil.getFriendlyLength((int) walkPath.getDuration()) + ")");
//                    mBottomLayout.setVisibility(View.VISIBLE);
//                    int dis = (int) walkPath.getDistance();
//                    int dur = (int) walkPath.getDuration();
//                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
//                    mRotueTimeDes.setText(des);
//                    mRouteDetailDes.setVisibility(View.GONE);
//                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext,
//                                    WalkRouteDetailActivity.class);
//                            intent.putExtra("walk_path", walkPath);
//                            intent.putExtra("walk_result",
//                                    mWalkRouteResult);
//                            startActivity(intent);
//                        }
//                    });
                } else if (result != null && result.getPaths() == null) {
                    ViewHelper.showToast(this, "没找到路线数据");
                }
            } else {
                ViewHelper.showToast(this, "没找到路线数据");
            }
        } else {
            ViewHelper.showToast(this, "" + errorCode);
        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult result, int errorCode) {
        clearMarks();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    final RidePath ridePath = result.getPaths()
                            .get(0);
                    rideRouteOverlay = new RideRouteOverlay(
                            this, aMap, ridePath,
                            startPlace,
                            endPlace);
                    rideRouteOverlay.addToMap();
                    rideRouteOverlay.zoomToSpan();

                    tvBottoom.setText(AMapUtil.getFriendlyTime((int) ridePath.getDistance())+"("+AMapUtil.getFriendlyLength((int) ridePath.getDuration())+")");
//                    mBottomLayout.setVisibility(View.VISIBLE);
//                    int dis = (int) ridePath.getDistance();
//                    int dur = (int) ridePath.getDuration();
//                    String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+")";
//                    mRotueTimeDes.setText(des);
//                    mRouteDetailDes.setVisibility(View.GONE);
//                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext,
//                                    RideRouteDetailActivity.class);
//                            intent.putExtra("ride_path", ridePath);
//                            intent.putExtra("ride_result",
//                                    mRideRouteResult);
//                            startActivity(intent);
//                }
//            });
                } else if (result != null && result.getPaths() == null) {
                    ViewHelper.showToast(this, "没找到路线数据");
                }
            } else {
                ViewHelper.showToast(this, "没找到路线数据");
            }
        } else {
            ViewHelper.showToast(this, "" + errorCode);
        }
    }

    private void gotoGuide() {
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPlace, endPlace);
        if (goRb1.isChecked()) {
            RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null, null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
            mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
        } else if (goRb2.isChecked()) {
            if (TextUtils.isEmpty(daoHangPlace)) {
                ViewHelper.showToast(this, "你这坐公交,还全国查询公交车,我是真不知道怎么坐过去");
                return;
            }
            RouteSearch.BusRouteQuery query = new RouteSearch.BusRouteQuery(fromAndTo, RouteSearch.BusDefault, daoHangPlace, 0);// 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
            mRouteSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
        } else if (goRb3.isChecked()) {
            RouteSearch.RideRouteQuery query = new RouteSearch.RideRouteQuery(fromAndTo, RouteSearch.RidingDefault);
            mRouteSearch.calculateRideRouteAsyn(query);// 异步路径规划骑行模式查询
        } else if (goRb4.isChecked()) {
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WalkDefault);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }

        isDaoHang = false;
        ivImage.setImageResource(R.mipmap.way_one);
        rlDaoHnag.setVisibility(View.GONE);
        rlSelect.setVisibility(View.VISIBLE);
        llTuCeng.setVisibility(View.VISIBLE);
        etStart.setText("");
        etEnd.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0); //强制隐藏键盘
    }
}
