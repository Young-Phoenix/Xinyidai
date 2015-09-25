package cn.net.xyd.presenter;

import cn.net.xyd.entity.LicaiProductEntity;

/**
 * @FileName:cn.net.xyd.presenter.ListPresenter.java
 * @author:Phoenix
 * @date:2015-09-23 16:47
 * @Version:V1.0
 */
public interface ListPresenter{
    void loadListData(String requestTag, int event_tag, String keywords, int page, boolean isSwipeRefresh);

    void onItemClickListener(int position, LicaiProductEntity entity);
}
