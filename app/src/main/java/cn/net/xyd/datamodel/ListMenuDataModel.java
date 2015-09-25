package cn.net.xyd.datamodel;

import android.content.Context;

import java.util.List;

import cn.net.xyd.entity.ListMenu;

/**
 * @FileName:cn.net.xyd.datamodel.ListMenuDataModel.java
 * @author:Phoenix
 * @date:2015-09-23 09:57
 * @Version:V1.0
 */
public interface ListMenuDataModel {
    List<ListMenu> getListMenu(Context context,int type);
}
