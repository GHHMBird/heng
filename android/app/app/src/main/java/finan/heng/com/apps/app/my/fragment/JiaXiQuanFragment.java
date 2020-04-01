package finan.heng.com.apps.app.my.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.adapter.JiaxiQuanAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.base.LoadingFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetRedPackListModel;
import finan.heng.com.apps.model.GetRedPackListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:38
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class JiaXiQuanFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshView pullToRefreshView;
    private RecyclerView recyclerView;
    private JiaxiQuanAdapter recyclerAdapter;
    private int type;
    private boolean isLoad = false;
    private boolean hasMoreItems = true;
    private boolean isShixiaoOrKeyong = true;
    private int pageIndex = 1;
    private TextView mTv_nomore;
    private TextView mTv_chakanshixiao;
    private String ksy = "ksy";

    public JiaXiQuanFragment(int i) {
        this.type = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_youhuiquan, container, false);
        initView(view);
        mTv_nomore.setText("已无更多可用加息券");
        init();
        return view;
    }

    private void init() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 12;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = myLayoutManager.getItemCount();
                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    pageIndex++;
                    isLoad = true;
                    recyclerAdapter.httpOK = true;
                    initKsyData();

                }
            }
        });
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
                initKsyData();

            }
        });
        initKsyData();

        mTv_chakanshixiao.setOnClickListener(this);
        mTv_nomore.setOnClickListener(this);
    }

    private void initKsyData() {
        new HttpHelper(getActivity()).getRedPackList(pageIndex+"", "10", "" + type, ksy).subscribe(new Action1<GetRedPackListResponse>() {
            @Override
            public void call(GetRedPackListResponse getRedPackListResponse) {
                LoadingFragment loadingFragment = findLoadingFragment();
                if (loadingFragment != null) {
                    loadingFragment.removeSelf(getFragmentManager());
                }
                pullToRefreshView.setRefreshing(false);

                if (pageIndex == 1) {
//                    DataCache.instance.saveCacheData("heng", "GetRedPackListResponse", getRedPackListResponse);
                    analyseData(getRedPackListResponse, 2, ksy);
                } else {
                    isLoad = false;
                    if (getRedPackListResponse.result.getMyredPackList().size() > 0) {
                        analyseData(getRedPackListResponse, 1, ksy);
                    } else {//没有更多数据了
                        hasMoreItems = false;
                        recyclerAdapter.httpOK = false;
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LoadingFragment loadingFragment = findLoadingFragment();
                if (loadingFragment != null) {
                    loadingFragment.removeSelf(getFragmentManager());
                }

                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (getActivity() != null) {
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                }
            }
        });

    }

    private void initView(View view) {
        pullToRefreshView = view.findViewById(R.id.pulltorefreshview);
        recyclerView = view.findViewById(R.id.fragment_touzi_recycler);
        mTv_nomore = view.findViewById(R.id.tv_nomore);
        mTv_chakanshixiao = view.findViewById(R.id.tv_chakanshixiao);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_chakanshixiao:
                if (isShixiaoOrKeyong) {
                    mTv_nomore.setText("已无更多失效加息券");
                    mTv_chakanshixiao.setText("查看可使用");
                    isShixiaoOrKeyong = false;
                    pageIndex = 1;
                    ksy = "ysy";
                    initKsyData();

                } else {
                    mTv_nomore.setText("已无更多可用加息券");
                    mTv_chakanshixiao.setText("查看已失效");
                    isShixiaoOrKeyong = true;
                    pageIndex = 1;
                    ksy = "ksy";
                    initKsyData();
                }

                break;
        }

    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetRedPackListResponse response, int type, String ksy) {
        ArrayList<GetRedPackListModel.MyredPackListBean> myredPackList = (ArrayList<GetRedPackListModel.MyredPackListBean>) response.result.getMyredPackList();
        if (myredPackList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new JiaxiQuanAdapter(getActivity(), myredPackList, ksy);
                    recyclerView.setAdapter(recyclerAdapter);
                    break;
                case 1:
                    recyclerAdapter.addData(myredPackList, ksy);
                    break;
                case 2:
                    if (recyclerAdapter == null) {
                        recyclerAdapter = new JiaxiQuanAdapter(getActivity(), myredPackList, ksy);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerAdapter.setData(myredPackList, ksy);
                    }
                    break;
            }
        }
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
