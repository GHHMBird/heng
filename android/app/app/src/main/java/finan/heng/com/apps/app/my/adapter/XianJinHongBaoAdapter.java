package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.model.GetRedPackListModel;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 15:52
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class XianJinHongBaoAdapter extends RecyclerView.Adapter<XianJinHongBaoAdapter.MyViewHolder> {

    private ArrayList<GetRedPackListModel.MyredPackListBean> list;
    private Context context;
    public boolean httpOK;
    private String isuser;

    public XianJinHongBaoAdapter(Context context, ArrayList<GetRedPackListModel.MyredPackListBean> list, String useOrNoUse) {
        this.context = context;
        this.list = list;
        this.isuser = useOrNoUse;
    }

    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.youhuiquan_item, parent, false);
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
        holder.tv_jine.setText(((int) Double.parseDouble(list.get(position).getBonuses())) + "");
        holder.tv_date.setText(list.get(position).getDays());
        holder.tv_youxiaoqi.setText(list.get(position).getEndtime());
        holder.xinshou.setText(list.get(position).getTitle());
        holder.tv_danbitouzi.setText(list.get(position).getHbcondition());
        if (isuser.equals("ysy")) {
            holder.rl_hongbao_bg.setBackgroundResource(R.mipmap.discountcoupon4);
        } else {
            holder.rl_hongbao_bg.setBackgroundResource(R.mipmap.discountcoupon1);
        }

    }

    @Override
    public int getItemCount() {
        if (httpOK) {
            return list.size() + 1;
        }
        return list.size();
    }

    public void setData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String useOrNoUse) {
        this.list = al;
        this.isuser = useOrNoUse;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetRedPackListModel.MyredPackListBean> al, String useOrNoUse) {
        this.list.addAll(al);
        this.isuser = useOrNoUse;
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

        private ImageView image;
        private TextView tv_jine, tv_date, tv_youxiaoqi, xinshou, tv_danbitouzi;
        private RelativeLayout rl_hongbao_bg;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv_jine = itemView.findViewById(R.id.tv_jine);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_youxiaoqi = itemView.findViewById(R.id.tv_youxiaoqi);
            xinshou = itemView.findViewById(R.id.xinshou);
            tv_danbitouzi = itemView.findViewById(R.id.tv_danbitouzi);
            rl_hongbao_bg = itemView.findViewById(R.id.rl_hongbao_bg);
        }
    }
}
