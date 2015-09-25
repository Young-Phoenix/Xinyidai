package cn.net.xyd.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import cn.net.xyd.R;
import cn.net.xyd.base.BaseFragmentActivity;
import cn.net.xyd.ui.fragment.ContainerFragment;
import cn.net.xyd.ui.fragment.TabListFragment;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;

/**
 * @FileName:cn.net.xyd.ui.activity.boot.LicaiActivity.java
 * @author:Phoenix
 * @date:2015-09-23 16:03
 * @Version:V1.0
 */
public class LicaiActivity extends BaseFragmentActivity{
    private static final String TAG = "LicaiActivity";
    private String title;
    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_licai;
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content, new ContainerFragment());
        transaction.commit();
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

}
