package finan.heng.com.apps.utils;
/*
 * Created by hhm on 2017/12/23.
 */

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

import finan.heng.com.apps.R;

public class HHMButton extends View {

    private int textColor;
    private Rect rect;
    private int textSize;
    private String text;
    private Paint paint;

    public HHMButton(Context context) {
        this(context, null);
    }

    public HHMButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HHMButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HHMButton, defStyleAttr, 0);

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.HHMButton_HHMButton_hhm_text:
                    text = typedArray.getString(index);
                    break;
                case R.styleable.HHMButton_HHMButton_hhm_textColor:
                    textColor = typedArray.getColor(index, Color.BLACK);//默认黑色
                    break;
                case R.styleable.HHMButton_HHMButton_hhm_textSize:
                    textSize = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.HHMButton_HHMButton_hhm_clickable:
                    typedArray.getBoolean(index, true);
                    break;
            }
        }

        typedArray.recycle();

        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);

        rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
//            paint.setTextSize(textSize);
//            paint.getTextBounds(text, 0, text.length(), rect);
//            int rectWidth = rect.width();
//            width = getPaddingLeft() + getPaddingRight() + rectWidth;//计算text的rect宽度
            width = getPaddingLeft() + getPaddingRight() + getMeasuredWidth();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingTop() + getPaddingBottom() + getMeasuredHeight();
//            paint.setTextSize(textSize);
//            paint.getTextBounds(text, 0, text.length(), rect);
//            int rectHeight = rect.height();
//            height = getPaddingTop() + getPaddingBottom() + rectHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(textColor);
        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
    }
}
