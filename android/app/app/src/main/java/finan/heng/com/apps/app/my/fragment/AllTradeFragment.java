package finan.heng.com.apps.app.my.fragment;

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
import finan.heng.com.apps.app.my.adapter.AllTradeAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetTradeListModel;
import finan.heng.com.apps.model.GetTradeListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.save.DataCache;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 11:09
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AllTradeFragment extends BaseFragment {

    private int               type;
    private PullToRefreshView pullToRefreshView;
    private RecyclerView      recyclerView;
    private boolean isLoad       = false;
    private AllTradeAdapter recyclerAdapter;
    private boolean hasMoreItems = true;
    private int     pageIndex    = 1;

    public AllTradeFragment(int i) {
        this.type = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_all_trade, container, false);
        initView(view);
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
                    getHttp();
                }
            }
        });
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
                getHttp();
            }
        });
        getHttp();
    }

    private void initView(View view) {
        pullToRefreshView = view.findViewById(R.id.all_trade_pulltorefreshview);
        recyclerView = view.findViewById(R.id.all_trade_recyclerview);
    }

    public void getHttp() {
        switch (type) {
            case 0:
                new HttpHelper(getActivity()).getTradeList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getTradeListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse0", getTradeListResponse);
                            analyseData(getTradeListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getTradeListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getTradeListResponse, 1);
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
                break;
            case 1:
                new HttpHelper(getActivity()).getRechargeList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getRechargeListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse1", getRechargeListResponse);
                            analyseData(getRechargeListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getRechargeListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getRechargeListResponse, 1);
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
                break;
            case 2:
                new HttpHelper(getActivity()).getWithDrawList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getWithDrawListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse2", getWithDrawListResponse);
                            analyseData(getWithDrawListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getWithDrawListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getWithDrawListResponse, 1);
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
                break;
            case 3:
                new HttpHelper(getActivity()).getinvestListList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getInvestListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse3", getInvestListResponse);
                            analyseData(getInvestListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getInvestListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getInvestListResponse, 1);
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
                break;
            case 4:
                new HttpHelper(getActivity()).getReceiveList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getReceiveListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse4", getReceiveListResponse);
                            analyseData(getReceiveListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getReceiveListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getReceiveListResponse, 1);
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
                break;
            case 5:
                new HttpHelper(getActivity()).getRewardList(pageIndex+"", "10").subscribe(new Action1<GetTradeListResponse>() {
                    @Override
                    public void call(GetTradeListResponse getRewardListResponse) {
                        pullToRefreshView.setRefreshing(false);

                        if (pageIndex == 1) {
                            DataCache.instance.saveCacheData("heng", "GetTradeListResponse5", getRewardListResponse);
                            analyseData(getRewardListResponse, 2);
                        } else {
                            isLoad = false;
                            if (getRewardListResponse.result.getTradeDetailList().size() > 0) {
                                analyseData(getRewardListResponse, 1);
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
                break;
        }
    }

    /**
     * @param response 集合数据
     * @param type     添加数据的方式 0代表创建adapter并添加 1代表添加数据（add） 2代表刷新集合
     */
    public void analyseData(GetTradeListResponse response, int type) {
        ArrayList<GetTradeListModel.TradeDetailListBean> productsList = response.result.getTradeDetailList();
        if (productsList.size() >= 0) {
            switch (type) {
                case 0:
                    recyclerAdapter = new AllTradeAdapter(getActivity(), productsList);
                    recyclerView.setAdapter(recyclerAdapter);
                    break;
                case 1:
                    recyclerAdapter.addData(productsList);
                    break;
                case 2:
                    if (recyclerAdapter == null) {
                        recyclerAdapter = new AllTradeAdapter(getActivity(), productsList);
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerAdapter.setData(productsList);
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
