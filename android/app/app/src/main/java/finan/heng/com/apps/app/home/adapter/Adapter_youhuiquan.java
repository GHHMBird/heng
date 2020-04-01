package finan.heng.com.apps.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.model.GetProductRedPackModel;

/**
 * Created by Administrator on 2017/4/28.
 */
public class Adapter_youhuiquan extends BaseAdapter {

    private String                                         money;
    private String                                         name;
    private Context                                        context;
    private List<GetProductRedPackModel.BonusesByTypeBean> list;

    /**
     * 创建接口
     */
    public Adapter_youhuiquan(Context context, List<GetProductRedPackModel.BonusesByTypeBean> list, String mname, String money) {
        this.context = context;
        this.list = list;
        this.name = mname;
        this.money = money;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_youhuiquan, viewGroup, false);
        TextView tv_jine = view.findViewById(R.id.tv_jine);
        TextView tv_youxiaoqi = view.findViewById(R.id.tv_date);
        TextView tv_date = view.findViewById(R.id.tv_youxiaoqi);
        TextView xinshou = view.findViewById(R.id.xinshou);
        ImageView isselect = view.findViewById(R.id.isselect);
        RelativeLayout rlbg = view.findViewById(R.id.item_yoouhui_quan_hg);
        TextView tv_danbitouzi = view.findViewById(R.id.tv_danbitouzi);
        if (name.equals("红包")) {
            tv_jine.setText(((int) Double.parseDouble(list.get(position).getBonuses())) + "");
        } else if (name.equals("加息券")) {
            int i = (int) (Double.parseDouble(list.get(position).getBonuses()) * 100);
            tv_jine.setText(i + "%");
        }

        if (list.get(position).isSelect) {
            isselect.setVisibility(View.VISIBLE);
        } else {
            isselect.setVisibility(View.GONE);
        }

        tv_date.setText("有效期至" + list.get(position).getEndTime());
        tv_youxiaoqi.setText(list.get(position).getTimeLimit() + "天后过期");
        xinshou.setText(list.get(position).getTitle());
        tv_danbitouzi.setText("单笔投资满" + ((int) Double.parseDouble(list.get(position).getPrerequisite())) + "元可用");
        int i = Integer.parseInt(money);
        if (i < ((int) Double.parseDouble(list.get(position).getPrerequisite()))) {
            rlbg.setBackgroundResource(R.mipmap.discountcoupon4);
        } else {
            if (name.equals("红包")) {
                rlbg.setBackgroundResource(R.mipmap.discountcoupon1);
            } else if (name.equals("加息券")) {
                rlbg.setBackgroundResource(R.mipmap.discountcoupon2);
            }
        }
        return view;
    }
}
