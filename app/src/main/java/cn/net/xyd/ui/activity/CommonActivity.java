package cn.net.xyd.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.net.xyd.R;
import cn.net.xyd.base.BaseFragmentActivity;
import cn.net.xyd.ui.fragment.AboutFragment;
import github.phoenix.library.eventbus.EventCenter;
import github.phoenix.library.netstatus.NetUtils;

/**
 * @FileName:cn.net.xyd.ui.activity.CommonActivity.java
 * @author:Phoenix
 * @date:2015-09-22 14:21
 * @Version:V1.0
 */
public class CommonActivity extends BaseFragmentActivity {
    private static final String TAG = "CommonActivity";
    private int type;
    @Override
    protected void getBundleExtras(Bundle extras) {
        type = extras.getInt("type");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_common;
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
        switch (type){
            case 1:
                transaction.add(R.id.fl_content, new AboutFragment());
                break;
        }
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
