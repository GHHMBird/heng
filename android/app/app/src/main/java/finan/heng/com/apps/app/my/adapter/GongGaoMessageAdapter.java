package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.model.GetGongGaoModel;

/**
 * Created by Administrator on 2017/5/6.
 */
public class GongGaoMessageAdapter extends BaseAdapter {
    private ArrayList<GetGongGaoModel.ArticleListBean> list;
    private Context                                    context;
    private boolean                                    httpOK;

    public GongGaoMessageAdapter(Context context, ArrayList<GetGongGaoModel.ArticleListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gonggaomessagecenter, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mJiaxiaoname.setText(list.get(position).getArticleTitle());
        holder.mStartdate.setText(list.get(position).getCreateTime());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context, WebActivity.class);
                intent2.putExtra("title", list.get(position).getArticleTitle());
                intent2.putExtra("url", list.get(position).getHotUrl());
                context.startActivity(intent2);
            }
        });
        return view;
    }

    static class ViewHolder {

        TextView       mJiaxiaoname;
        TextView       mStartdate;
        RelativeLayout rl;

        ViewHolder(View view) {
            mJiaxiaoname = view.findViewById(R.id.item_jiaxiaoname);
            mStartdate = view.findViewById(R.id.startdate);
            rl = view.findViewById(R.id.rl_item);
        }
    }
}
