package finan.heng.com.apps.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class MySeekBar extends SeekBar {

    public MySeekBar(Context context) {
        super(context);
        init(context);
    }

    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    private void init(Context context) {

    }
}