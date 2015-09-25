package cn.net.xyd.view;

import java.util.List;

import cn.net.xyd.entity.BaseEntity;

/**
 * @FileName:cn.net.xyd.view.CommonContainerView.java
 * @author:Phoenix
 * @date:2015-09-24 15:51
 * @Version:V1.0
 */
public interface CommonContainerView {
    void initializePagerViews(List<BaseEntity> categoryList);
}
