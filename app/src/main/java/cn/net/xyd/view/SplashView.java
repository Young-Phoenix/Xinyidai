package cn.net.xyd.view;

import android.view.animation.Animation;

/**
 * @FileName:cn.net.xyd.view.base.SplashView.java
 * @author:Phoenix
 * @date:2015-09-09 16:35
 * @Version:V1.0
 */
public interface SplashView {
    /**
     * 设置背景动画
     * @param animation
     */
    void setBackgroundImageAnimation(Animation animation);

    /**
     * 初始化view
     * @param versionName
     * @param copyright
     * @param backgroundResId
     */
    void initializeViews(String versionName,String copyright,int backgroundResId);
    void initializeUmengConfig();
    void navigateToHomePage();
}
