package cn.net.xyd.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/8/21 0021.
 * Fragment接口
 * @author 李成凤
 * @version 1.0
 */
public interface IBaseFragment {
    /**
     * 绑定渲染视图的布局文件
     * @return 布局文件资源id
     */
    int bindLayout();

    /**
     * 初始化控件
     */
    void initView(final View view);

    /**
     * 业务处理操作（onCreateView方法中调用）
     * @param mContext  当前Activity对象
     */
    void doBusiness(Context mContext);
}
