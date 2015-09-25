package cn.net.xyd.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import cn.net.xyd.R;
import cn.net.xyd.BaseApplication;
import cn.net.xyd.common.NetUtils;
import cn.net.xyd.common.ToastUtils;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public abstract class BaseActivity extends FragmentActivity implements IBaseActivity{
    /**
     * LOG打印标签
     */
    private static final String TAG = BaseActivity.class.getSimpleName();
    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    /**
     * 全局的Context {@link #mContext = this.getApplicationContext();}
     */
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            // 删除窗口背景
            getWindow().setBackgroundDrawable(null);
        }

        mContext = this.getApplicationContext();
        ((BaseApplication) this.getApplication()).addActivity(this);

        // 向用户展示信息前的准备工作在这个方法里处理
        preliminary();
    }

    /**
     * 向用户展示信息前的准备工作在这个方法里处理
     */
    protected void preliminary() {
        // 初始化组件
        setupViews();

        // 初始化数据
        initialized();
    }

    /**
     * 获取全局的Context
     * @return {@link #mContext = this.getApplicationContext();}
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_in);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }


    /**
     * 带有右进右出动画的退出
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    /**
     * 默认退出
     */
    public void defaultFinish() {
        super.finish();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
        }
    }

    // 横竖屏切换，键盘等
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 可以添加多个Action捕获
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_NETWORK_CHANGE);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理各种情况
            String action = intent.getAction();
            if (ACTION_NETWORK_CHANGE.equals(action)) { // 网络发生变化
                // 处理网络问题
                if(!NetUtils.isConnected(mContext)){
                    ToastUtils.showShort(mContext, R.string.net_not_connect);
                }
            }
        }
    };
}
