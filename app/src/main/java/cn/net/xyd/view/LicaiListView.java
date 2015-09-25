package cn.net.xyd.view;

import cn.net.xyd.entity.LicaiProductEntity;
import cn.net.xyd.entity.ResponseLiCaiProductEntity;
import cn.net.xyd.view.base.BaseView;

/**
 * @FileName:cn.net.xyd.view.base.LicaiListView.java
 * @author:Phoenix
 * @date:2015-09-23 16:35
 * @Version:V1.0
 */
public interface LicaiListView extends BaseView {

    void refreshListData(ResponseLiCaiProductEntity responseImagesListEntity);

    void addMoreListData(ResponseLiCaiProductEntity responseImagesListEntity);

    void navigateToImagesDetail(int position, LicaiProductEntity entity, int x, int y, int width, int height);
}
