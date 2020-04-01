package finan.heng.com.apps.app.my.fragment;
/*
 * Created by hhm on 2017/5/5.
 */

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
import finan.heng.com.apps.app.my.adapter.InviteFriendRegisterAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetInviteHistoryModel;
import finan.heng.com.apps.model.GetInviteHistoryResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.functions.Action1;

public class InviteFriendRegisterFragment extends BaseFragment {

    private PullToRefreshView pullToRefreshView;
    private RecyclerView      recyclerView;
    private boolean hasMoreItems = true;
    private boolean isLoad       = false;
    private InviteFriendRegisterAdapter recyclerAdapter;
    private int     pageIndex    = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_invite_friend_register, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 1;
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
                    isLoad = true;
                    pageIndex++;
                    recyclerAdapter.httpOK = true;
                    getData();
                }
            }
        });
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
                getData();
            }
        });
        getData();
    }

    private void getData() {
        new HttpHelper(getActivity()).getInviteHistory("" + pageIndex, "10").subscribe(new Action1<GetInviteHistoryResponse>() {
            @Override
            public void call(GetInviteHistoryResponse getInviteHistoryResponse) {
                pullToRefreshView.setRefreshing(false);

                if (pageIndex == 1) {
                    DataCache.instance.saveCacheData("heng", "GetInviteHistoryResponse", getInviteHistoryResponse);
                    analyseData(getInviteHistoryResponse, 2);
                } else {
                    isLoad = false;
                    if (getInviteHistoryResponse.result.getInvateRegList().size() > 0) {
                        analyseData(getInviteHistoryResponse, 1);
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
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                }
            }
        });
    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetInviteHistoryResponse response, int type) {
        ArrayList<GetInviteHistoryModel.Bean> productsList = response.result.getInvateRegList();
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new InviteFriendRegisterAdapter(getActivity(), productsList);
                    recyclerView.setAdapter(recyclerAdapter);
                    break;
                case 1:
                    recyclerAdapter.addData(productsList);
                    break;
                case 2:
                    if (recyclerAdapter == null) {
                        recyclerAdapter = new InviteFriendRegisterAdapter(getActivity(), productsList);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerAdapter.setData(productsList);
                    }
                    break;
            }
        }
    }

    private void initView(View view) {
        pullToRefreshView = view.findViewById(R.id.fragment_invite_friend_register_pulltorefresh);
        recyclerView = view.findViewById(R.id.fragment_invite_friend_register_rv);
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
