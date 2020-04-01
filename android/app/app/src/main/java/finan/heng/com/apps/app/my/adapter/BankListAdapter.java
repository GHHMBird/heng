package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.model.BankInfo;

/**
 * Created by Administrator on 2017/5/15.
 */
public class BankListAdapter extends BaseAdapter {

    private ArrayList<BankInfo> list;
    private Context context;
    private boolean httpOK;

    public BankListAdapter(Context context, ArrayList<BankInfo> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_banklist, parent, false);
            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bank_name.setText(list.get(position).bankName);
        Glide.with(context).load(list.get(position).bankImage).into(holder.bank_icon);
        return view;
    }

    static class ViewHolder {

        ImageView bank_icon;
        TextView bank_name;

        ViewHolder(View view) {
            bank_icon = view.findViewById(R.id.bank_icon);
            bank_name = view.findViewById(R.id.bank_name);
        }
    }
}
