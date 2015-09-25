package cn.net.xyd.datamodel.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.net.xyd.R;
import cn.net.xyd.datamodel.ContainerDataModel;
import cn.net.xyd.entity.BaseEntity;

/**
 * @FileName:cn.net.xyd.datamodel.impl.ContainerDataModelImpl.java
 * @author:Phoenix
 * @date:2015-09-24 15:54
 * @Version:V1.0
 */
public class ContainerDataModelImpl implements ContainerDataModel {
    private static final String TAG = "ContainerDataModelImpl";

    @Override
    public List<BaseEntity> getCommonCategoryList(Context context) {
        List<BaseEntity> resultData = new ArrayList<>();
        String[] licaiCategoryArray = context.getResources().getStringArray(R.array.licai_category_list);
        for(int i=0;i<licaiCategoryArray.length;i++){
            resultData.add(new BaseEntity(licaiCategoryArray[i], licaiCategoryArray[i]));
        }
        return resultData;
    }
}
