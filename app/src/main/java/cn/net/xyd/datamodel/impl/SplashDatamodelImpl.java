package cn.net.xyd.datamodel.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Calendar;

import cn.net.xyd.R;
import cn.net.xyd.datamodel.SplashDatamodel;

/**
 * @FileName:cn.net.xyd.datamodel.impl.SplashDatamodelImpl.java
 * @author:Phoenix
 * @date:2015-09-09 16:49
 * @Version:V1.0
 */
public class SplashDatamodelImpl implements SplashDatamodel {
    private static final String TAG = "SplashDatamodelImpl";

    @Override
    public int getBackgroundImageResId() {
        /*int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.drawable.morning;
        } else if (hour > 12 && hour <= 18) {
            resId = R.drawable.afternoon;
        } else {
            resId = R.drawable.night;
        }*/
        return R.drawable.splash;
    }

    @Override
    public String getCopyright(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }

    @Override
    public String getVersionName(Context context) {
        String versionName=null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(context.getResources().getString(R.string.splash_version),versionName);
    }

    @Override
    public Animation getBackgroundImageAnimate(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash);
    }
}
