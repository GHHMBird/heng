package finan.heng.com.apps.app.finance.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.finance.adapter.RecyclerAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.base.LoadingFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductListModel;
import finan.heng.com.apps.model.GetProductListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/23 15:15
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AssetFragment extends BaseFragment {

    private  int httpNum;
    private String id;
    private RecyclerView mRecyclerView;
    private PullToRefreshView mPullToRefreshView;
    private boolean isLoad = false;
    private boolean hasMoreItems = true;
    private RecyclerAdapter recyclerAdapter;
    private int pageIndex = 1;

    public AssetFragment(int i, String id) {
        this.id = id;
        this.httpNum = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {

    }

    @BusReceiver
    public void StringEvent(String s){
        if (s.equals("heng_AssetFragment"+httpNum)) {
            pageIndex = 1;
            hasMoreItems = true;
            initData();
        }
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mPullToRefreshView = view.findViewById(R.id.pulltorefreshview);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 10;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = myLayoutManager.getItemCount();
                if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {//加载更多
                    pageIndex++;
                    isLoad = true;
                    recyclerAdapter.httpOK = true;
                    initData();
                }
            }
        });
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
                initData();
            }
        });
        initData();
    }

    private void initData() {
        new HttpHelper(getActivity()).getProductList(id, pageIndex + "")
                .subscribe(new Action1<GetProductListResponse>() {
                    @Override
                    public void call(GetProductListResponse getProductListResponse) {
                        LoadingFragment loadingFragment = findLoadingFragment();
                        if (loadingFragment != null) {
                            loadingFragment.removeSelf(getFragmentManager());
                        }
                        mPullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetProductListResponse", getProductListResponse);
                            analyseData(getProductListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getProductListResponse.result.getProductsList().size() > 0) {
                                analyseData(getProductListResponse, 1);
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

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetProductListResponse response, int type) {
        ArrayList<GetProductListModel.ProductsListBean> productsList = response.result.getProductsList();
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new RecyclerAdapter(getActivity(), productsList);
                    mRecyclerView.setAdapter(recyclerAdapter);
                    break;
                case 1:
                    recyclerAdapter.addData(productsList);
                    break;
                case 2:
                    if (recyclerAdapter == null) {
                        recyclerAdapter = new RecyclerAdapter(getActivity(), productsList);
                        mRecyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerAdapter.setData(productsList);
                    }
                    break;
            }
        }
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

    @Override
    public View getScrollableView() {
        return null;
    }
}
