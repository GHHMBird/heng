package finan.heng.com.apps.app.finance.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.finance.adapter.FragmentTouZiJiLuAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductDetailListModel;
import finan.heng.com.apps.model.GetProductDetailListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 22:46
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class TouZiJiLuFragment extends BaseFragment {

    private int               id;
    private PullToRefreshView mPullToRefreshView;
    private RecyclerView      mRecyclerView;
    private int     pageIndex    = 1;
    private boolean hasMoreItems = true, isLoad = false;
    private FragmentTouZiJiLuAdapter mFragmentTouZiJiLuAdapter;

    public TouZiJiLuFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_touzijilu, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 1;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = myLayoutManager.getItemCount();
                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {
                    pageIndex++;
                    isLoad = true;
                    mFragmentTouZiJiLuAdapter.httpOK = true;
                    getData();

                }
            }
        });
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
                getData();
            }
        });
        getData();
    }

    private void getData() {
        new HttpHelper(getActivity()).getProductDetailList(id, pageIndex + "").subscribe(new Action1<GetProductDetailListResponse>() {
            @Override
            public void call(GetProductDetailListResponse getProductDetailListResponse) {
                mPullToRefreshView.setRefreshing(false);

                if (pageIndex == 1) {
                    DataCache.instance.saveCacheData("heng", "GetProductListResponse", getProductDetailListResponse);
                    analyseData(getProductDetailListResponse, 2);
                } else {
                    isLoad = false;
                    if (getProductDetailListResponse.result.getUserOrderList().size() > 0) {
                        analyseData(getProductDetailListResponse, 1);
                    } else {//没有更多数据了
                        hasMoreItems = false;
                        mFragmentTouZiJiLuAdapter.httpOK = false;
                        mFragmentTouZiJiLuAdapter.notifyDataSetChanged();
                    }
                }

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

    private void initView(View view) {
        mPullToRefreshView = view.findViewById(R.id.fragment_touzijilu_pulltorefreshview);
        mRecyclerView = view.findViewById(R.id.fragment_touzijilu_recyclerview);
    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetProductDetailListResponse response, int type) {
        ArrayList<GetProductDetailListModel.UserOrderListBean> userOrderList = response.result.getUserOrderList();
        if (userOrderList.size() >= 0) {
            switch (type) {
                case 0:
                    mFragmentTouZiJiLuAdapter = new FragmentTouZiJiLuAdapter(getActivity(), userOrderList);
                    mRecyclerView.setAdapter(mFragmentTouZiJiLuAdapter);
                    break;
                case 1:
                    mFragmentTouZiJiLuAdapter.addData(userOrderList);
                    break;
                case 2:
                    if (mFragmentTouZiJiLuAdapter == null) {
                        mFragmentTouZiJiLuAdapter = new FragmentTouZiJiLuAdapter(getActivity(), userOrderList);
                        mRecyclerView.setAdapter(mFragmentTouZiJiLuAdapter);
                    } else {
                        mFragmentTouZiJiLuAdapter.setData(userOrderList);
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
