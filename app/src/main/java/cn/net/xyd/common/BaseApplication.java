package cn.net.xyd.common;

import android.app.Activity;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import cn.net.xyd.base.BaseActivity;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class BaseApplication extends Application {
    /**
     * Activity集合
     */
    private static ArrayList<BaseActivity> activitys = new ArrayList<BaseActivity>();
    public RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        // volley初始化配置
        mQueue = Volley.newRequestQueue(this);
        // imageloader初始化配置
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 添加Activity到ArrayList<Activity>管理集合
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        String className = activity.getClass().getName();
        for (Activity at : activitys) {
            if (className.equals(at.getClass().getName())) {
                activitys.remove(at);
                break;
            }
        }
        activitys.add(activity);
    }

    /**
     * 退出应用程序的时候，手动调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseActivity activity : activitys) {
            activity.defaultFinish();
        }
    }
}
