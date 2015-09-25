package cn.net.xyd.datamodel;

import android.content.Context;

import java.util.List;

import cn.net.xyd.entity.BaseEntity;

/**
 * @FileName:cn.net.xyd.datamodel.LicaiListViewDataModel.java
 * @author:Phoenix
 * @date:2015-09-23 16:38
 * @Version:V1.0
 */
public interface ListViewDataModel {
    void getCommonListData(String requestTag, final int event_tag, String keywords, int page);
}
