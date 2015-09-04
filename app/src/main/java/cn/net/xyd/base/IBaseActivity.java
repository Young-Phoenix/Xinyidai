package cn.net.xyd.base;

/**
 * Created by Administrator on 2015/8/21 0021.
 */
public interface IBaseActivity {
    /**
     * 布局文件ID
     * @return
     */
    int getLayoutId();
    /**
     * 初始化组件
     */
    void setupViews();
    /**
     * 初始化数据
     */
    void initialized();
}
