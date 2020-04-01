package finan.heng.com.apps.utils;
/*
 * Created by hhm on 2017/8/22.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;

public class ImageHeader extends PtrClassicDefaultHeader {

    private ImageView imageView;

    public ImageHeader(Context context) {
        super(context);
    }

    public ImageHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
