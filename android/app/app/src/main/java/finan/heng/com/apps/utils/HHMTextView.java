package finan.heng.com.apps.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

import finan.heng.com.apps.R;

public class HHMTextView extends View {

    private int colorRes;
    private int textSize;
    private String text;
    private Paint paint;
    private Rect rect;

    public HHMTextView(Context context) {
        this(context, null);
    }

    public HHMTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HHMTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HHMTextView, defStyleAttr, 0);

        for (int i = 0; i < array.getIndexCount(); i++) {
            int index = array.getIndex(i);
            switch (index) {
                case R.styleable.HHMTextView_HHMTextView_hhm_text:
                    text = array.getString(index);
                    break;
                case R.styleable.HHMTextView_HHMTextView_hhm_textColor:
                    //默认为黑色
                    colorRes = array.getColor(index, Color.BLACK);
                    break;
                case R.styleable.HHMTextView_HHMTextView_hhm_textSize:
                    //默认为14sp
                    textSize = array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
                    break;
            }
        }

        array.recycle();

        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(colorRes);
        rect = new Rect();//设置文字的范围
        paint.getTextBounds(text, 0, text.length(), rect);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                text = "" + random.nextInt(10000);
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {//如果确切写明宽度
            width = widthSize;
        } else {//为确切写明宽度，系统按照MATCH_PARENT计算
            paint.setTextSize(textSize);
            paint.getTextBounds(text, 0, text.length(), rect);
            int rectWidth = rect.width();
            width = getPaddingLeft() + getPaddingRight() + rectWidth;//计算text的rect宽度
        }

        if (heightMode == MeasureSpec.EXACTLY) {//如果确切写明宽度
            height = heightSize;
        } else {//为确切写明宽度，系统按照MATCH_PARENT计算
            paint.setTextSize(textSize);
            paint.getTextBounds(text, 0, text.length(), rect);
            int rectHeight = rect.height();
            height = getPaddingTop() + getPaddingBottom() + rectHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);//画出hhmtextview的背景
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(colorRes);//画出text的范围
        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
    }
}
