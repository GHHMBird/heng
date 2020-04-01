package finan.heng.com.apps.app.home.activity;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.http.RetrofitClient;
import finan.heng.com.apps.model.HomeNotice;
import finan.heng.com.apps.model.HomeNoticeResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import finan.heng.com.apps.utils.MyLayoutManager;
import finan.heng.com.apps.utils.lgdpulltorefresh.PullToRefreshView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/13 12:51
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeScrollTextActivity extends BaseActivity {

    private PullToRefreshView mPullToRefreshView;
    private RecyclerView      mRecyclerView;
    private int               pageIndex;
    private boolean           hasMoreItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("");
        initView();
        init();
    }

    private void init() {

        initData();
    }

    private void initData() {
        new RetrofitClient().getInterfaceService().getHomeScrollTextDetail().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<HomeNoticeResponse>() {
            @Override
            public void call(HomeNoticeResponse homeNoticeResponse) {
                HomeScrollTextAdapter homeScrollTextAdapter = new HomeScrollTextAdapter(HomeScrollTextActivity.this, homeNoticeResponse.result.articleList);
                mRecyclerView.setAdapter(homeScrollTextAdapter);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(HomeScrollTextActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initView() {
        mPullToRefreshView = findViewById(R.id.activity_scroll_text_pull);
        mRecyclerView = findViewById(R.id.activity_scroll_text_recyclerview);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 10;
            }
        });
        final MyLayoutManager myLayoutManager = new MyLayoutManager(this);
        mRecyclerView.setLayoutManager(myLayoutManager);
//                mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//                        int lastVisibleItem = myLayoutManager.findLastVisibleItemPosition();
//                        int totalItemCount = myLayoutManager.getItemCount();
//                        if (!isLoad && hasMoreItems && lastVisibleItem >= totalItemCount - 1 && dy > 0) {//加载更多
//
//                        }
//                    }
//                });
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            public void onRefresh() {
                pageIndex = 1;
                hasMoreItems = true;
            }
        });
    }

    class HomeScrollTextAdapter extends RecyclerView.Adapter<HomeScrollTextAdapter.ViewHolder> {

        private ArrayList<HomeNotice> arrayList;
        private Context                   context;
        private boolean httpOK;

        public HomeScrollTextAdapter(Context content, ArrayList<HomeNotice> list) {
            this.context = content;
            this.arrayList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.home_scroll_text_item, parent, false);
                return new ViewHolder(inflate);
            } else {
                View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
                return new ViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == arrayList.size() && httpOK) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.image.getLayoutParams();
                float density = context.getResources().getDisplayMetrics().density;
                lp.height = Math.round((float) 60 * density);
                holder.image.setLayoutParams(lp);
                holder.image.setImageResource(R.drawable.refresh_animlist);
                AnimationDrawable animationDrawable = (AnimationDrawable) holder.image.getDrawable();
                animationDrawable.start();
                return;
            }
            Glide.with(context).load(arrayList.get(position).articleImage).into(holder.imgPic);
            holder.mTvTime.setText(arrayList.get(position).createTime);
            holder.mTvTitle.setText(arrayList.get(position).articleTitle);
        }

        @Override
        public int getItemViewType(int position) {
            if (position == arrayList.size() && httpOK) {
                return 1;
            }
            return 0;
        }

        public void setData(ArrayList<HomeNotice> al) {
            this.arrayList = al;
            notifyDataSetChanged();
        }

        public void addData(ArrayList<HomeNotice> al) {
            this.arrayList.addAll(al);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (httpOK) {
                return arrayList.size() + 1;
            }
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTvTime, mTvTitle;
            private ImageView image, imgPic;

            public ViewHolder(View itemView) {
                super(itemView);
                image= itemView.findViewById(R.id.image);
                mTvTime = itemView.findViewById(R.id.home_scroll_time);
                mTvTitle = itemView.findViewById(R.id.home_scroll_title);
                imgPic = itemView.findViewById(R.id.home_scroll_imageview);
            }
        }
    }

}
