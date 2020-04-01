package finan.heng.com.apps.helper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import finan.heng.com.apps.R;
import finan.heng.com.apps.utils.ToastCompat;

/*
 * Created by lfu on 2017/2/21.
 */

public class ViewHelper {

    public interface OnPositiveBtnClickedListener {
        void onPositiveBtnClicked(MaterialDialog dialog);
    }

    public interface OnNegativeBtnClickedListener {
        void onNegativeBtnClicked(MaterialDialog dialog);
    }


    public static void showToast(Context context, String content) {
        ToastCompat.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static MaterialDialog buildDialog(Context context, String title, String content, final OnPositiveBtnClickedListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(title);
        builder.content(content);
        if (listener != null) {
            builder.positiveText("");
            builder.negativeText("");
            builder.positiveColorRes(R.color.colorAccent);
            builder.negativeColorRes(R.color.colorAccent);
            builder.autoDismiss(false);
            builder.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
            builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    listener.onPositiveBtnClicked(dialog);
                }
            });
        }
        return builder.build();
    }

    public static MaterialDialog buildNoTitleTextDialog(Context context, String content, final OnPositiveBtnClickedListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.positiveText("");
        builder.negativeText("");
        builder.positiveColorRes(R.color.colorAccent);
        builder.negativeColorRes(R.color.colorAccent);
        builder.content(content);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                listener.onPositiveBtnClicked(dialog);
            }
        });
        return builder.build();
    }

    public static MaterialDialog buildNoTitleTextDialog(Activity activity, String content) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        builder.negativeText("");
        builder.content(content);
        builder.positiveColorRes(R.color.colorAccent);
        builder.negativeColorRes(R.color.colorAccent);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
        return builder.build();
    }

    public static MaterialDialog buildNoTitleTextDialog2(Activity activity, String content, final OnPositiveBtnClickedListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        builder.negativeText("");
        builder.content(content);
        builder.cancelable(false);
        builder.positiveColorRes(R.color.colorAccent);
        builder.negativeColorRes(R.color.colorAccent);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                listener.onPositiveBtnClicked(dialog);
            }
        });
        return builder.build();
    }

    public static MaterialDialog buildNoTitleTextDialog3(Context context, String content, final OnPositiveBtnClickedListener listener, final OnNegativeBtnClickedListener myListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.positiveText("");
        builder.negativeText("");
        builder.positiveColorRes(R.color.colorAccent);
        builder.negativeColorRes(R.color.colorAccent);
        builder.content(content);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                myListener.onNegativeBtnClicked(dialog);
            }
        });
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                listener.onPositiveBtnClicked(dialog);
            }
        });
        return builder.build();
    }

    public static MaterialDialog buildNoTitleTextDialog4(Context context, String content, final OnPositiveBtnClickedListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.positiveText("好的");
        builder.negativeText("暂不设置");
        builder.positiveColorRes(R.color.colorAccent);
        builder.negativeColorRes(R.color.colorAccent);
        builder.content(content);
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                listener.onPositiveBtnClicked(dialog);
            }
        });
        return builder.build();
    }

    /**
     * dp 转化为 px
     * @param context
     * @param dpValue
     * @return
     */

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px 转化为 dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
