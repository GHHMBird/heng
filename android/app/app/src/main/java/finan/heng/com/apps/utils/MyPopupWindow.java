package finan.heng.com.apps.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import finan.heng.com.apps.R;
import finan.heng.com.apps.app.home.adapter.Adapter_youhuiquan;
import finan.heng.com.apps.model.GetProductRedPackModel;


/**
 * Created by Administrator on 2017/3/13.
 */
public class MyPopupWindow implements View.OnClickListener {

    private String                                         money;
    private OnSelectItemListener                           listener;
    private Context                                        mContext;
    private String                                         mname;
    private ListView                                       lv_youhui;
    private TextView                                       pop_close;
    private PopupWindow                                    mPopWindow;
    private List<GetProductRedPackModel.BonusesByTypeBean> mList;
    private Adapter_youhuiquan                             mainGetPopCouAdapter;
    public static final int POP_WINDOW_ITEM_1 = 1;
    public static final int POP_WINDOW_ITEM_2 = 2;
    public static final int POP_WINDOW_ITEM_3 = 3;

    public void setOnSelectItemListener(OnSelectItemListener listener) {
        this.listener = listener;
    }

    public MyPopupWindow(Activity context, List<GetProductRedPackModel.BonusesByTypeBean> list, String name, String money) {
        mContext = context;
        mList = new ArrayList<>();
        mList = list;
        mname = name;
        this.money = money;
        initPopupWindow();                  //初始化弹窗
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popwindow_youhuiquan, null);
        lv_youhui = view.findViewById(R.id.youhuiquan_list);
        pop_close = view.findViewById(R.id.pop_close);
        TextView youhui_name = view.findViewById(R.id.youhui_name);
        youhui_name.setText(mname);
        mPopWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, 1500, true);

        mPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha((Activity) mContext, 0.5f);
        //添加pop窗口关闭事件
        mPopWindow.setOnDismissListener(new poponDismissListener());
        pop_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });


        mainGetPopCouAdapter = new Adapter_youhuiquan(mContext, mList, mname, money);
        lv_youhui.setAdapter(mainGetPopCouAdapter);
        lv_youhui.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (Integer.parseInt(money) >= ((int) Double.parseDouble(mList.get(position).getPrerequisite()))) {

                    GetProductRedPackModel.BonusesByTypeBean bean = mList.get(position);
                    if (bean.isSelect) {
                        bean.isSelect = false;
                        mainGetPopCouAdapter.notifyDataSetChanged();

                        if (listener != null) {
                            //mPopWindow.dismiss();
                            listener.selectItem("", POP_WINDOW_ITEM_1);     //回调接口
                            listener.selectItem("", POP_WINDOW_ITEM_2);     //回调接口
                        }
                    } else {

                        for (int i = 0; i < mList.size(); i++) {
                            mList.get(i).isSelect = false;
                        }

                        bean.isSelect = true;
                        mainGetPopCouAdapter.notifyDataSetChanged();

                        if (listener != null) {
                            //mPopWindow.dismiss();
                            listener.selectItem(mList.get(position).getBonuses(), POP_WINDOW_ITEM_1);     //回调接口
                            listener.selectItem(mList.get(position).getId(), POP_WINDOW_ITEM_2);     //回调接口
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public void backgroundAlpha(Activity mContext, float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha((Activity) mContext, 1f);
        }

    }

}
