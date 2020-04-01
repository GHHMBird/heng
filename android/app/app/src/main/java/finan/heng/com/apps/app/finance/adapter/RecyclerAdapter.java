package finan.heng.com.apps.app.finance.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import finan.heng.com.apps.LoginActivity;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.finance.activity.ProductDetailActivity;
import finan.heng.com.apps.app.home.activity.SetJiaoYiMimaActivity;
import finan.heng.com.apps.app.home.activity.ShiMingRenZhengActivity;
import finan.heng.com.apps.app.home.activity.TouziActivity;
import finan.heng.com.apps.model.GetProductListModel;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.save.DataCache;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/23 16:26
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    public ArrayList<GetProductListModel.ProductsListBean> arrayList = new ArrayList<>();
    public boolean httpOK;

    public RecyclerAdapter(Context context, ArrayList<GetProductListModel.ProductsListBean> al) {
        this.context = context;
        this.arrayList = al;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.finance_adapter_item, parent, false);
            return new ViewHolder(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_foot_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
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
        final GetProductListModel.ProductsListBean productsListBean = arrayList.get(position);
        holder.imgIsSelect.setVisibility(View.GONE);//精选
        holder.tvName.setText(productsListBean.getTitle());
        holder.tvContent.setText(productsListBean.getDescription());
        switch (productsListBean.getStatus()) {// //-41.复审未通过；-11.初审未通过；10.待初审；11.初审通过；20.预告中；30.募集中；40.待复审；41.复审通过；50.还款中（计息中）；60.已完成；
            case "-41":
                holder.btnBuy.setText("复审未通过");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "-11":
                holder.btnBuy.setText("初审未通过");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "10":
                holder.btnBuy.setText("待初审");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "11":
                holder.btnBuy.setText("初审通过");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "20":
                holder.btnBuy.setText("预告中");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "30":
                holder.btnBuy.setText("立即购买");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_click);
                break;
            case "40":
                holder.btnBuy.setText("待复审");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "41":
                holder.btnBuy.setText("复审通过");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "50":
                holder.btnBuy.setText("还款中");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
            case "60":
                holder.btnBuy.setText("已回款");
                holder.btnBuy.setBackgroundResource(R.drawable.btn_unclick);
                break;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        holder.tvRate.setText(df.format(Double.parseDouble(productsListBean.getMinProfit()) * 100));
        if ("1".equals(productsListBean.getPlstimeLimitType())) {
            holder.tvTime.setText(productsListBean.getPlstimeLimitValue());
            holder.tvTimeDW.setText("个月");
        } else if ("0".equals(productsListBean.getPlstimeLimitType())) {
            holder.tvTime.setText(productsListBean.getPlstimeLimitValue());
            holder.tvTimeDW.setText("天");
        }
        double i1 = Double.parseDouble(productsListBean.getSurplusAmount());
        if (i1 < 10000) {
            holder.tvLeft.setText("剩余" + (int) i1 + "元");
        } else {
            double v = i1 / 10000;
            String s = v + "";
            String[] split = s.split("\\.");
            char[] chars = split[1].toCharArray();
            String sss = "";
            boolean isFirst = true;
            for (int i = chars.length - 1; i >= 0; i--) {
                if (isFirst) {
                    if (chars[i] == '0') {
                        sss += "";
                    } else {
                        isFirst = false;
                        sss += chars[i];
                    }
                } else {
                    sss += chars[i];
                }
            }
            if (TextUtils.isEmpty(sss)) {
                holder.tvLeft.setText("剩余" + split[0] + "万元");
            } else {
                String ssss = "";
                char[] chars1 = sss.toCharArray();
                for (int i = chars1.length - 1; i >= 0; i--) {
                    ssss += chars1[i];
                }
                holder.tvLeft.setText("剩余" + split[0] + "." + ssss + "万元");
            }
        }
        double i2 = Double.parseDouble(productsListBean.getInvestBegin());
        if (i2 < 10000) {
            holder.tvMin.setText("起投" + (int) i2 + "元");
        } else {
            holder.tvMin.setText("起投" + i2 / 10000 + "万元");

        }
        holder.pBar.setMax(100);
        holder.pBar.setProgress((int) (100 * productsListBean.getInvestPercent()));
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((productsListBean.getStatus().equals("30"))) {
                    MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
                    if (cacheData == null) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    } else if ("0".equals(cacheData.result.realnameStatus) || "0".equals(cacheData.result.cardStatus)) {
                        Intent intent = new Intent(context, ShiMingRenZhengActivity.class);
                        context.startActivity(intent);
                    } else if (TextUtils.isEmpty(cacheData.result.userPaypassword)) {
                        Intent intent = new Intent(context, SetJiaoYiMimaActivity.class);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, TouziActivity.class);
                        intent.putExtra("id", productsListBean.getId());
                        context.startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("id", productsListBean.getId());
                    context.startActivity(intent);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id", productsListBean.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == arrayList.size() && httpOK) {
            return 1;
        }
        return 0;
    }

    public void setData(ArrayList<GetProductListModel.ProductsListBean> al) {
        this.arrayList = al;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<GetProductListModel.ProductsListBean> al) {
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

        private TextView tvName, tvContent, tvRate, tvTime, tvMin, tvLeft, tvTimeDW;
        //        private ImageView icon;
        private ImageView image, imgIsSelect;
        private Button btnBuy;
        private ProgressBar pBar;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = itemView.findViewById(R.id.image);
            imgIsSelect = itemView.findViewById(R.id.finance_adapter_item_isjingxuan);//是不是精选产品
            tvName = itemView.findViewById(R.id.finance_adapter_item_name);//产品名称
            tvContent = itemView.findViewById(R.id.finance_adapter_item_content);//产品介绍
            tvTime = itemView.findViewById(R.id.finance_adapter_item_month);//产品介绍
            tvRate = itemView.findViewById(R.id.finance_adapter_item_rate);//产品利率
            tvMin = itemView.findViewById(R.id.finance_adapter_item_min);//起投金额
            tvTimeDW = itemView.findViewById(R.id.finance_adapter_item_times);
            tvLeft = itemView.findViewById(R.id.finance_adapter_item_left);//剩余金额
            btnBuy = itemView.findViewById(R.id.finance_adapter_item_btn);
            pBar = itemView.findViewById(R.id.finance_adapter_item_pbar);
        }
    }
}
