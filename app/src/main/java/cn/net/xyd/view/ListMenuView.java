package cn.net.xyd.view;

import java.util.List;

import cn.net.xyd.entity.ListMenu;

/**
 * @FileName:cn.net.xyd.view.base.ListMenuView.java
 * @author:Phoenix
 * @date:2015-09-23 09:55
 * @Version:V1.0
 */
public interface ListMenuView {
    void initializeViews(List<ListMenu> menus);
}
