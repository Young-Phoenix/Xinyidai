package cn.net.xyd.datamodel;

import android.content.Context;

import java.util.List;

import cn.net.xyd.entity.BaseEntity;

/**
 * @FileName:cn.net.xyd.datamodel.ContainerDataModel.java
 * @author:Phoenix
 * @date:2015-09-24 15:53
 * @Version:V1.0
 */
public interface ContainerDataModel {
    List<BaseEntity> getCommonCategoryList(Context context);
}
