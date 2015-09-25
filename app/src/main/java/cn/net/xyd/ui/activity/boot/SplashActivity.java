package cn.net.xyd.ui.activity.boot;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseActivity;
import cn.net.xyd.base.BaseFragmentActivity;
import cn.net.xyd.presenter.Presenter;
import cn.net.xyd.presenter.impl.SplashPresenterImpl;
import cn.net.xyd.view.SplashView;
import github.phoenix.library.base.BaseAppFragmentActivity;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;

/**
 * @FileName:cn.net.xyd.ui.activity.boot.SplashActivity.java
 * @author:Phoenix
 * @date:2015-09-09 17:20
 * @Version:V1.0
 */
public class SplashActivity extends BaseFragmentActivity implements SplashView{
    private static final String TAG = "SplashActivity";
    @Bind(R.id.splash_image)
    ImageView splashImage;
    @Bind(R.id.splash_version_name)
    TextView splashVersionName;
    @Bind(R.id.splash_copyright)
    TextView splashCopyright;

    private Presenter mSplashPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        mSplashPresenter = new SplashPresenterImpl(this,this);
        mSplashPresenter.initialization();
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void setBackgroundImageAnimation(Animation animation) {
        splashImage.startAnimation(animation);
    }

    @Override
    public void initializeViews(String versionName, String copyright, int backgroundResId) {
        splashCopyright.setText(copyright);
        splashVersionName.setText(versionName);
        splashImage.setImageResource(backgroundResId);
    }

    @Override
    public void initializeUmengConfig() {

    }

    @Override
    public void navigateToHomePage() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
