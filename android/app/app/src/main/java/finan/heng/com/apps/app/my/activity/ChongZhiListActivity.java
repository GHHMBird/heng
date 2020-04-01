package finan.heng.com.apps.app.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetPayStyleModel;
import finan.heng.com.apps.model.GetPayStyleResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/5/18 23:29
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ChongZhiListActivity extends BaseActivity {

    private ListView mListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chongzhi);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.activity_list_chongzhi_lv);

        new HttpHelper(this).getPayStyle().subscribe(new Action1<GetPayStyleResponse>() {
            @Override
            public void call(GetPayStyleResponse getPayStyleResponse) {
                ArrayList<GetPayStyleModel.PaymentListBean> paymentList = getPayStyleResponse.result.getPaymentList();
                MyAdapter myAdapter = new MyAdapter(ChongZhiListActivity.this, paymentList);
                mListView.setAdapter(myAdapter);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ChongZhiListActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        private ArrayList<GetPayStyleModel.PaymentListBean> list;
        private Context                                     context;

        public MyAdapter(Context context, ArrayList<GetPayStyleModel.PaymentListBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.chongzhi_item, parent, false);
            ImageView iv = view.findViewById(R.id.iv_platform_logo);
            TextView name = view.findViewById(R.id.tv_platform_name);
            TextView title = view.findViewById(R.id.tv_platform_hint);
            name.setText(list.get(position).getPaymentName());
            title.setText(list.get(position).getPaymentDesc());
            Glide.with(context).load(list.get(position).getPaymentLogo()).into(iv);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChongZhiOrTiXianActivity.class);
                    intent.putExtra("code",list.get(position).getPaymentCode());
                    intent.putExtra("type", 1);
                    context.startActivity(intent);
                    finish();
                }
            });
            return view;
        }
    }
}
