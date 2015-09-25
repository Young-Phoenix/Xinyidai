package cn.net.xyd.datamodel;

import android.content.Context;
import android.view.animation.Animation;

/**
 * @FileName:cn.net.xyd.datamodel.SplashDatamodel.java
 * @author:Phoenix
 * @date:2015-09-09 16:48
 * @Version:V1.0
 */
public interface SplashDatamodel {
    int getBackgroundImageResId();
    String getCopyright(Context context);
    String getVersionName(Context context);
    Animation getBackgroundImageAnimate(Context context);
}
