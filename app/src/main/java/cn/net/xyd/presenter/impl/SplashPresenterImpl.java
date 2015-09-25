package cn.net.xyd.presenter.impl;

import android.content.Context;
import android.view.animation.Animation;

import cn.net.xyd.datamodel.SplashDatamodel;
import cn.net.xyd.datamodel.impl.SplashDatamodelImpl;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.view.SplashView;

/**
 * @FileName:cn.net.xyd.presenter.impl.SplashPresenterImpl.java
 * @author:Phoenix
 * @date:2015-09-09 16:46
 * @Version:V1.0
 */
public class SplashPresenterImpl implements Presenter {
    private static final String TAG = "SplashPresenterImpl";
    private Context mContext;
    private SplashView mSplashView;
    private SplashDatamodel mSpalshDatemodel;

    public SplashPresenterImpl(Context context, SplashView splashView) {
        if (null == splashView) {
            throw new IllegalArgumentException("Constructor's parameters must not be NULL");
        }
        this.mContext = context;
        this.mSplashView = splashView;
        this.mSpalshDatemodel = new SplashDatamodelImpl();
    }

    @Override
    public void initialization() {
        mSplashView.initializeUmengConfig();
        mSplashView.initializeViews(mSpalshDatemodel.getVersionName(mContext),
                mSpalshDatemodel.getCopyright(mContext),
                mSpalshDatemodel.getBackgroundImageResId());
        Animation animation = mSpalshDatemodel.getBackgroundImageAnimate(mContext);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSplashView.navigateToHomePage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSplashView.setBackgroundImageAnimation(animation);
    }
}
