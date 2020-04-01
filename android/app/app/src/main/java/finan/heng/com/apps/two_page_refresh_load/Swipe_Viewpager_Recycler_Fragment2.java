package finan.heng.com.apps.two_page_refresh_load;
/*
 * Created by hhm on 2017/8/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseFragment;

import static finan.heng.com.apps.R.id.fragment_swipe_viewpager_recycler_fragment1_recycler;

public class Swipe_Viewpager_Recycler_Fragment2 extends BaseFragment {

    private SetNumberListener listener;
    private int page = 1;
    private RecyclerView recyclerView;
    private RightAdapter rightAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_swipe_viewpager_recycler_fragment1, container, false);
        initView(view);
        init();
        return view;
    }

    public Swipe_Viewpager_Recycler_Fragment2( SetNumberListener listener) {
        this.listener = listener;
    }

    private void init() {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            rightAdapter = new RightAdapter(new ArrayList<String>());
            recyclerView.setAdapter(rightAdapter);
            rightAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    getRightData(page);
                }
            }, recyclerView);
            getRightData(page);
    }

    public void getRightData(int page) {
        this.page=page;
        ((SwipeRefreshLayout) getActivity().findViewById(R.id.activity_kscroll_bar_swipe)).setRefreshing(false);
        if (page == 1) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 1; i <= page; i++) {
                list.add("第" + page + "页,第" + i + "条");
            }
            rightAdapter.setNewData(list);
        } else {
            rightAdapter.loadMoreComplete();
            ArrayList<String> list = new ArrayList<>();
            for (int i = 1; i <= page; i++) {
                list.add("第" + page + "页,第" + i + "条");
            }
            rightAdapter.addData(list);
        }
        listener.setNumber(1, rightAdapter.getItemCount()-1);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(fragment_swipe_viewpager_recycler_fragment1_recycler);
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

    private class RightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        RightAdapter(@Nullable List<String> data) {
            super(R.layout.item_text, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_text_tv, item);
        }
    }

   public interface SetNumberListener {
        void setNumber(int index, int number);
    }
}
