package cn.net.xyd.view.base;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public interface BaseView {
    /**
     * 显示加载信息
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏加载信息
     */
    void hiddenLoading();

    /**
     * 显示错误信息
     * @param msg
     */
    void showError(String msg);

    /**
     * 显示异常信息
     * @param msg
     */
    void showException(String msg);

    /**
     * 显示网络错误信息
     */
    void showNetError();
}
