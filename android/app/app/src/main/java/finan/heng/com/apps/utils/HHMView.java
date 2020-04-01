package finan.heng.com.apps.utils;
/*
 * Created by hhm on 2017/12/25.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import finan.heng.com.apps.R;

public class HHMView extends View {
    private String text;
    private Paint paint;
    private Rect rect;

    public HHMView(Context context) {
        this(context, null);
    }

    public HHMView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HHMView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HHMView, defStyleAttr, 0);

        for (int i = 0; i < array.getIndexCount(); i++) {
            int index = array.getIndex(i);
            if (index == R.styleable.HHMView_HHMView_text) {
                text = array.getString(index);
            }
        }

        array.recycle();

        paint = new Paint();
        paint.setColor(Color.BLACK);

        rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureSize(getSuggestedMinimumWidth(), widthMeasureSpec), getMeasureSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    private int getMeasureSize(int suggestedMinimumSize, int measureSpec) {
        int measureSize = MeasureSpec.getSize(measureSpec);
        int measureMode = MeasureSpec.getMode(measureSpec);

        int size;

        switch (measureMode) {
            case MeasureSpec.EXACTLY:
                size = measureSize;
                break;
            default:
                size = suggestedMinimumSize;
                break;
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);//画出hhmtextview的背景
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(Color.BLACK);//画出text的范围
        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);

    }
}
