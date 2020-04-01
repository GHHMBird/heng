package finan.heng.com.apps.utils;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

/**
 *
 * Created by Monkey on 2017/1/9.
 */

public class ScollTextView extends BaseScollTextView<String> {

    private Context context;

    public ScollTextView(Context context) {
        super(context);
        this.context = context;
    }

    public ScollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public String getItemText(List<String> data, int postion) {
        return data.get(postion);
    }
}
