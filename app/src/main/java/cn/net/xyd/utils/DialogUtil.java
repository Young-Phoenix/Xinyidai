package cn.net.xyd.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.net.xyd.R;

public class DialogUtil {
    public static Dialog createLoadingDialog(Context context, String str) {
        Object localObject = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        LinearLayout localLinearLayout = (LinearLayout) ((View) localObject).findViewById(R.id.dialog_view);
        ImageView localImageView = (ImageView) ((View) localObject).findViewById(R.id.img);
        localObject = (TextView) ((View) localObject).findViewById(R.id.tipTextView);
        localImageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.loading_animation));
        ((TextView) localObject).setText(str);
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setCancelable(false);
        dialog.setContentView(localLinearLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return dialog;
    }
}