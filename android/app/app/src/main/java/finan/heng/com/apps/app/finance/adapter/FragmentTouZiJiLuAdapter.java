package finan.heng.com.apps.app.finance.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.model.GetProductDetailListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 23:10
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class FragmentTouZiJiLuAdapter extends RecyclerView.Adapter<FragmentTouZiJiLuAdapter.MyViewHolder> {


    private Context                  context;
    private ArrayList<GetProductDetailListModel.UserOrderListBean> list;
    public boolean httpOK;

    public FragmentTouZiJiLuAdapter(Context context, ArrayList<GetProductDetailListModel.UserOrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_touzijilu_item, parent, false);
            return new MyViewHolder(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == list.size() && httpOK) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.image.getLayoutParams();
            float density = context.getResources().getDisplayMetrics().density;
            lp.height = Math.round((float) 60 * density);
            holder.image.setLayoutParams(lp);
            holder.image.setImageResource(R.drawable.refresh_animlist);
            AnimationDrawable animationDrawable = (AnimationDrawable) holder.image.getDrawable();
            animationDrawable.start();
            return;
        }
        GetProductDetailListModel.UserOrderListBean userOrderListBean = list.get(position);
        holder.tvMoney.setText(userOrderListBean.getAmount());
        holder.tvTime.setText(userOrderListBean.getAddTime().length()>10?userOrderListBean.getAddTime().substring(0,10):userOrderListBean.getAddTime());
        holder.tvUser.setText(userOrderListBean.getUserAccount().substring(0,3)+"****"+userOrderListBean.getUserAccount().substring(7,11));
    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return list.size() + 1;
        }
        return list.size();
    }

    public void setData(ArrayList<GetProductDetailListModel.UserOrderListBean> al) {
        this.list = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetProductDetailListModel.UserOrderListBean> al) {
        this.list.addAll(al);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size() && httpOK) {
            return 1;
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View      itemView;
        private ImageView image;
        private TextView  tvUser, tvTime, tvMoney;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = itemView.findViewById(R.id.image);
            tvUser = itemView.findViewById(R.id.fragment_touzijili_item_user);
            tvTime = itemView.findViewById(R.id.fragment_touzijili_item_time);
            tvMoney = itemView.findViewById(R.id.fragment_touzijili_item_money);
        }
    }
}
