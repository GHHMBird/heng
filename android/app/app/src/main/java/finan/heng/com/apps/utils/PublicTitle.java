package finan.heng.com.apps.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import finan.heng.com.apps.R;


/**
 * Created by Administrator on 2016/8/18.
 */
public class PublicTitle extends RelativeLayout {
    ImageView      ivLeft;
    TextView       tvTitle;
    ImageView      ivRight;
    RelativeLayout rlTitle;
    private TextView tv_right;

    public PublicTitle(Context context) {
        super(context);
        init(context);
    }

    public PublicTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        ivLeft = findViewById(R.id.iv_title_left);
        ivRight = findViewById(R.id.iv_title_right);
        tvTitle = findViewById(R.id.tv_title);
        rlTitle = findViewById(R.id.rl_title);
        tv_right = findViewById(R.id.tv_right);
    }

    public PublicTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }




   public void setRightOnclick(View.OnClickListener l){
       ivRight.setOnClickListener(l);
   }

    public void setLeftOnclick(View.OnClickListener l){
        ivLeft.setOnClickListener(l);
    }
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setLeftImage(int imageId) {
        ivLeft.setImageResource(imageId);
    }

    public void setRightImage(int imageId) {
        ivRight.setImageResource(imageId);
    }
    public void setRightText(String rightText) {
        tv_right.setText(rightText);
    }

    public void  setTitleBg(int colorID){
        rlTitle.setBackgroundColor(colorID);
    }

    public void setTitleColor(int colorId) {
        tvTitle.setTextColor(colorId);
    }
    public void setTitleRightText(int visible) {
        tv_right.setVisibility(visible);
    }
}
