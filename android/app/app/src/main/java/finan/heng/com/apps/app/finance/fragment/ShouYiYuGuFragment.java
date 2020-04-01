package finan.heng.com.apps.app.finance.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mcxiaoke.bus.Bus;

import java.text.DecimalFormat;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetProductDetailMoneyResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/30 8:55
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ShouYiYuGuFragment extends BaseFragment {

    private int        id;
    private ScrollView ruler;
    private TextView   user_height_value, user_height_earns;
    private LinearLayout rulerlayout;

    public ShouYiYuGuFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View mView = inflater.inflate(R.layout.fragment_shouyiyugu, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View view) {
        user_height_value = view.findViewById(R.id.user_height_value);
        user_height_earns = view.findViewById(R.id.user_height_earns);
        user_height_value.setText("0");

        ruler = view.findViewById(R.id.vruler);
        ruler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int scrollY = ruler.getScrollY();
                if (scrollY % 40 == 0) {
                    user_height_value.setText(String.valueOf((int) Math.ceil((25 * ruler.getScrollY()))));
                } else {
                    user_height_value.setText(String.valueOf((int) Math.ceil((25 * (scrollY - scrollY % 40)))));
                }
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int scrollY = ruler.getScrollY();
                                if (scrollY % 40 == 0) {
                                    user_height_value.setText(String.valueOf((int) Math.ceil((25 * ruler.getScrollY()))));
                                } else {
                                    user_height_value.setText(String.valueOf((int) Math.ceil((25 * (scrollY - scrollY % 40)))));
                                }
                                if (Integer.parseInt(user_height_value.getText().toString()) <= 0) {
                                    user_height_earns.setText("0");
                                    user_height_value.setText("0");
                                } else {
                                    new HttpHelper(getActivity()).getProductDetailMoney(id, Integer.parseInt(user_height_value.getText().toString())).subscribe(new Action1<GetProductDetailMoneyResponse>() {
                                        @Override
                                        public void call(GetProductDetailMoneyResponse getProductDetailMoneyResponse) {
                                            DecimalFormat df = new DecimalFormat("0.00");
                                            user_height_earns.setText(df.format(Double.parseDouble(getProductDetailMoneyResponse.result.profit)) + "");
                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            if (throwable instanceof RequestErrorThrowable) {
                                                RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                                                if (getActivity() != null) {
                                                    ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                                                }
                                            }
                                        }
                                    });
                                }

                            }
                        }, 100);
                        break;
                }
                return false;
            }

        });

        rulerlayout = view.findViewById(R.id.vruler_layout);
        new Handler().postDelayed((new Runnable() {
            @Override
            public void run() {
                constructRuler();
            }
        }), 300);
    }

    private void constructRuler() {
        int rulerHeight = ruler.getHeight();

        View topview = LayoutInflater.from(getActivity()).inflate(R.layout.blankvrulerunit, null);
        topview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rulerHeight / 4));
        rulerlayout.addView(topview);
        for (int i = 0; i < 100; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.vrulerunit, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            TextView tv = view.findViewById(R.id.vrulerunit);
            tv.setText(String.valueOf(i * 5000));
            rulerlayout.addView(view);
        }
        View bottomview = LayoutInflater.from(getActivity()).inflate(R.layout.blankvrulerunit, null);
        bottomview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rulerHeight / 4));
        rulerlayout.addView(bottomview);
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
}
