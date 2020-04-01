package finan.heng.com.apps.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.app.home.activity.FindJiaoYiMimaActivity;
import finan.heng.com.apps.app.my.adapter.InputPawAdapter;


/**
 * Created by Administrator on 2016/9/19.
 */
public class WithdrawlDialog extends Dialog {


    private String          number;
    private int             type;
    private Context         context;
    private GridView        key_input_grid;
    private InputPawAdapter inputPawAdapter;


    public WithdrawlDialog(Context context, int themeResId, int layoutId, String number, int type) {
        super(context, themeResId);
        this.context = context;
        this.number = number;
        this.type = type;
        setContentView(layoutId);
        TextView tvType = findViewById(R.id.dialog_trader_password_ct);
        if (type == 1) {
            tvType.setText("充值");
        } else if (type == 2) {
            tvType.setText("提现");
        }

        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context);
        Window dialogWindow = getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.transparent)));
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.BOTTOM);
        initView();
        setClick();
    }

    private ArrayList<Integer> passwords = new ArrayList<>();

    private void setClick() {
        inputPawAdapter.setNumberItemClick(new InputPawAdapter.NumberItemClick() {
            @Override
            public void itemOnClick(int position) {
                if (position != 11 && passwords.size() < 6) {
                    passwords.add(position);
                } else if (passwords.size() > 0 && 11 == position) {
                    passwords.remove(passwords.size() - 1);
                }
                if (passwords.size() == 6) {
                    if (trueButton != null) {
                        String pwd = "";
                        for (int i = 0; i < passwords.size(); i++) {
                            pwd += passwords.get(i);
                        }
                        trueButton.getText(pwd);
                    }
                    cancel();
                }

                setPassWord(passwords);

            }
        });

    }

    private ArrayList<TextView> textViews = new ArrayList<>();

    private void setPassWord(ArrayList<Integer> passwords) {
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setText("");
        }
        for (int i = 0; i < passwords.size(); i++) {
            textViews.get(i).setText(passwords.get(i) + "");
        }
    }

    private TrueButton trueButton;

    public void setTrueButton(TrueButton trueButton) {
        this.trueButton = trueButton;
    }

    private void initView() {
        TextView tvMoney = findViewById(R.id.dialog_trader_password_money);
        tvMoney.setText(Html.fromHtml(number));
        key_input_grid = findViewById(R.id.key_input_grid);
        TextView position_one = findViewById(R.id.position_one);
        TextView position_two = findViewById(R.id.position_two);
        TextView position_three = findViewById(R.id.position_three);
        TextView position_four = findViewById(R.id.position_four);
        TextView position_five = findViewById(R.id.position_five);
        TextView position_six = findViewById(R.id.position_six);

        ImageView iv_back = findViewById(R.id.iv_back);
        TextView forgetPaw = findViewById(R.id.tv_forget_pasword);

        textViews.add(position_one);
        textViews.add(position_two);
        textViews.add(position_three);
        textViews.add(position_four);
        textViews.add(position_five);
        textViews.add(position_six);
        inputPawAdapter = new InputPawAdapter(context);
        key_input_grid.setAdapter(inputPawAdapter);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        forgetPaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,FindJiaoYiMimaActivity.class));
            }
        });

    }
}
